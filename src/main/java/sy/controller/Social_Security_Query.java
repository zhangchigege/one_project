package sy.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sy.model.SbUser;
import sy.service.Social_Security_Query_Service;
import sy.util.HttpClientPoolUtil;
import sy.util.MD5Util;
import sy.util.StampToDate;

/**
 * @program: zxshow
 * @description:爬虫-社保信息获取
 * @author: ZhangChi
 * @create: 2019-03-13
 **/
@RestController
@RequestMapping("/Social_Security_Query")
public class Social_Security_Query {

	@Autowired
	private Social_Security_Query_Service security_Query_Service;
	
    private static CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(true, null, 20000, 20000, 20000);

    /**
     * @Description: 封装httpclient头信息
     * @Param: [methord]
     * @return: void
     * @Author: ZhangChi
     * @Date: 2019/3/13
     */
    public static void headerWrapper(AbstractHttpMessage methord) {
        methord.setHeader("Accept", "*/*");
        methord.setHeader("Accept-Encoding", "gzip, deflate");
        methord.setHeader("Accept-Language", "zh-CN");
        methord.setHeader("Content-Type", "application/x-www-form-urlencoded");
        methord.setHeader("User-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
        methord.setHeader("Host", "www.bjrbj.gov.cn");
        methord.setHeader("Origin", "http://www.bjrbj.gov.cn");
        methord.setHeader("Referer", "http://www.bjrbj.gov.cn/csibiz/indinfo/login.jsp");
    }

    private HttpResponse response;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Map strMap;
    

    //登陆地址
    private static String loginUrl = "http://www.bjrbj.gov.cn/csibiz/indinfo/login_check";
    //验证码下载网址
    private static String checkCodeURL = "http://www.bjrbj.gov.cn/csibiz/indinfo/validationCodeServlet.do";
    //手机验证码地址
    private static String phoneUrl = "http://www.bjrbj.gov.cn/csibiz/indinfo/passwordSetAction!getTelSafeCode";
    //个人登录信息
    private static String j_username = "371523199505011256";
    private static String j_password = Base64.getEncoder().encodeToString("ZHANGchi2012".getBytes());

    /**
     * @Description:用于登录北京社会保险查询网站获取个人信息
     * @Param: []
     * @return: void
     * @Author: ZhangChi
     * @Date: 2019/3/13
     */
    @RequestMapping("/querySocial")
    public String querySocial(HttpServletRequest request) {
    	String sbuserid = "";// 社保id
        try {
            //String checkCode = getCheckCode();
        	String shebaousername = request.getParameter("shebaousername");
        	String shebaopassword = request.getParameter("shebaopassword");
        	String shebaocheckcode = request.getParameter("shebaocheckcode");
        	String shebaophone = request.getParameter("shebaophone");
            //模拟登录
            List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
            HttpPost httpPost = new HttpPost(loginUrl);
            headerWrapper(httpPost);
            //封装登录所需的参数
            loginParams.add(new BasicNameValuePair("type", "1"));
            loginParams.add(new BasicNameValuePair("flag", "3"));
            loginParams.add(new BasicNameValuePair("x", "41"));
            loginParams.add(new BasicNameValuePair("y", "30"));
            loginParams.add(new BasicNameValuePair("j_username", j_username));
            loginParams.add(new BasicNameValuePair("j_password", j_password));
            loginParams.add(new BasicNameValuePair("safecode", shebaocheckcode));
            loginParams.add(new BasicNameValuePair("i_phone", shebaophone));
            httpPost.setEntity(new UrlEncodedFormEntity(loginParams, "UTF-8")); //UrlEncodedFormEntity
            // 发送请求
            HttpResponse response = httpClient.execute(httpPost);
            //----------判断是否重定向开始
            int code = response.getStatusLine().getStatusCode();
            //重定向的路径
            String newUri = null;
            if (code == 302) {
                Header header = response.getFirstHeader("location");
                newUri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
                System.out.println("为重定向" + newUri);
                HttpGet loginGet = new HttpGet(newUri);
                // 发送请求
                HttpResponse httpresponse = httpClient.execute(loginGet);
                if (httpresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    //获取个人信息页面
                    HttpPost getUser = new HttpPost("http://www.bjrbj.gov.cn/csibiz/indinfo/search/ind/indNewInfoSearchAction");
                    headerWrapper(getUser);
                    getUser.setHeader("Referer", "http://www.bjrbj.gov.cn/csibiz/indinfo/search/ind/ind_new_info_index.jsp");
                    getUser.setHeader("X-Requested-With", "XMLHttpRequest");
                    HttpResponse userResponse = httpClient.execute(getUser);
                    String entity = EntityUtils.toString(userResponse.getEntity());
                    entity = entity.replaceAll("<(\\S*?)[^>;]*>.*?|<.*? />", "").replaceAll("&nbsp;", "").replaceAll("-->", "").replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
                    //log.info("entity = {}", entity);
                    strMap = new HashMap();
                    List<String> list = Arrays.asList(entity);
                    writer = new BufferedWriter(new FileWriter(new File("user.txt")));
                    for (int i = 0; i < list.size(); i++) {
                        writer.write(list.get(i));
                        writer.write("\n");
                        writer.flush();
                    }
                    reader = new BufferedReader(new FileReader(new File("user.txt")));
                    int count = 0;
                    String str1 = null;
                    while ((str1 = reader.readLine()) != null) {
                        strMap.put(count, str1.trim());
                        count++;
                    }
                    StampToDate stampToDate = new StampToDate();
            		if ((!"".equals(shebaousername)) && (!"".equals(shebaopassword))) {
            			sbuserid = "sb_" + shebaousername + "_" + MD5Util.string2MD5(shebaopassword) + "_"
            					+ stampToDate.stampToDateM(String.valueOf(System.currentTimeMillis()));
            		}
                    SbUser sbUser = new SbUser();
                    sbUser.setUid(sbuserid);
                    String setUserName =(String) strMap.get(15);
                    sbUser.setUserName(setUserName);
                    String setComPany_Name = (String) strMap.get(0);
                    sbUser.setComPany_Name(setComPany_Name.substring(setComPany_Name.indexOf("：")+2,setComPany_Name.length()));
                    String Unified_Social_Credit_Code = (String) strMap.get(1);
                    sbUser.setUnified_Social_Credit_Code(Unified_Social_Credit_Code.substring(17));
                    String setSocial_Security_Registration_Number = (String) strMap.get(2);
                    sbUser.setSocial_Security_Registration_Number(setSocial_Security_Registration_Number.substring(8));
                    String setCounty = (String) strMap.get(3);
                    sbUser.setCounty(setCounty.substring(7));
                    sbUser.setId_Number((String) strMap.get(17));
                    sbUser.setSex((String) strMap.get(19));
                    sbUser.setDate((String) strMap.get(21));
                    sbUser.setNation((String) strMap.get(23));
                    sbUser.setState((String) strMap.get(25));
                    sbUser.setDate_Of_Employment((String) strMap.get(29));
                    String setAccount_Properties = (String) strMap.get(31);
                    sbUser.setAccount_Properties(setAccount_Properties.substring(6));
                    sbUser.setRegistered_Permanent_Residence((String) strMap.get(33));
                    sbUser.setPlace_Of_Abode((String) strMap.get(37));
                    sbUser.setPhone((String) strMap.get(48));
                    sbUser.setSalary((String) strMap.get(52));
                    sbUser.setBank_Account((String) strMap.get(58));
                    sbUser.setPin((String) strMap.get(27));
                    security_Query_Service.addUser(sbUser);
                    System.out.println(sbUser.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("sbuserid", sbuserid);
        return "tz3";
    }

    /**
     * @Description: 用于获取手机验证码
     * @Param: []
     * @return: void
     * @Author: ZhangChi
     * @Date: 2019/3/13
     */
    @RequestMapping(value="/getshebaoyzm" , method=RequestMethod.POST)
    public void getPhoneCode(@RequestParam("shebaousername") String shebaousername ,
			 @RequestParam("shebaopassword") String shebaopassword ,
			 @RequestParam("shebaocheckcode") String shebaocheckcode) throws UnsupportedEncodingException {
       // String chCode = getCheckCode();
        HttpPost phonePost = new HttpPost(phoneUrl);
        phonePost.setHeader("Connection", "60");
        phonePost.setHeader("Accept", "*/*");
        phonePost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        phonePost.setHeader("Accept-Encoding", "gzip, deflate");
        phonePost.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        //设置参数信息
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("idCode", "371523199505011256"));
        params.add(new BasicNameValuePair("logPass", "ZHANGchi2012"));
        params.add(new BasicNameValuePair("safeCode", shebaocheckcode));
        //发起申请
        try {
            phonePost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response1 = httpClient.execute(phonePost);
            System.out.println(EntityUtils.toString(response1.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description: 用于获取附加码
     * @Param: []
     * @return: java.lang.String
     * @Author: ZhangChi
     * @Date: 2019/3/13
     */
    @RequestMapping(value="/getCheckCode",method=RequestMethod.GET)
    public void getCheckCode() throws UnsupportedEncodingException {
        try {
            HttpGet getCheckCode = new HttpGet(checkCodeURL);
            FileOutputStream fileOutputStream = null;
            response = httpClient.execute(getCheckCode);
            /*验证码写入文件,当前工程的根目录,保存为verifyCode.jped*/
            fileOutputStream = new FileOutputStream(new File("D:\\WorkSpace\\zxshow\\src\\main\\webapp\\img\\verifyCode.jpeg") );
            response.getEntity().writeTo(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
