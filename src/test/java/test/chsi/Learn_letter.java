package test.chsi;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sy.util.HttpClientPoolUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: zxshow
 * @description: 学信网爬取
 * @author: ZhangChi
 * @create: 2019-09-05 16:47
 **/
public class Learn_letter {


    private static CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(true, null, 20000, 20000, 20000);

    /**
     * 封装httpclient头信息
     * @param methord
     */
    public static void headerWrapper(AbstractHttpMessage methord) {
        methord.setHeader("Accept", "*/*");
        methord.setHeader("Accept-Encoding", "gzip, deflate");
        methord.setHeader("Accept-Language", "zh-CN");
        methord.setHeader("Content-Type", "application/x-www-form-urlencoded");
        methord.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
        //methord.setHeader("Host", "www.chsi.com.cn");
        //methord.setHeader("Origin", "http://www.bjrbj.gov.cn");
        //methord.setHeader("Referer", "https://my.chsi.com.cn/archive/index.jsp");
    }

    //登陆地址
    /**
     * 学信网官网:https://www.chsi.com.cn/
     * 学信档案个人查询:https://my.chsi.com.cn/archive/index.jsp
     * 学信档案查询登录页面:https://account.chsi.com.cn/passport/login
     */
    private static String loginUrl = "https://www.chsi.com.cn/";

    private static final String USERNAME = "17736189175";
    private static final String PASSWORD = "zhao/19910310";

    /**
     * 登录学信网
     */
    public static void login() throws IOException {
        //模拟登录
        //List<NameValuePair> param = new ArrayList<NameValuePair>();
        HttpGet httpGet = new HttpGet(loginUrl);
        headerWrapper(httpGet);
        //param.add(new BasicNameValuePair("username",USERNAME));
        //param.add(new BasicNameValuePair("password",PASSWORD));

        //发送请求
        HttpResponse httpResponse = httpClient.execute(httpGet);
        //查看返回状态
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            /*//获取页面内容
            String responseResult = EntityUtils.toString(httpResponse.getEntity());
            //解析页面内容获取想要的标签div class="h_m_div3" 查找学历查询<span class="fontBold color333">学历查询</span>本人查询<a href="https://my.chsi.com.cn/" target="_blank">本人查询</a><br/>
            Document document = Jsoup.parse(responseResult);
            Elements elements = document.select("div[class=t_nav]");
            String html = elements.select("body > table > tbody > tr:nth-child(493) > td.line-content").html();*/
            //登录学信档案页面查找登录url  https://my.chsi.com.cn/archive/index.jsp
            HttpGet httpGet1 = new HttpGet("https://my.chsi.com.cn/archive/index.jsp");
            headerWrapper(httpGet1);
            HttpResponse httpResponse1 = httpClient.execute(httpGet1);
            HttpEntity entity = httpResponse1.getEntity();
            String tow = EntityUtils.toString(entity);
            System.out.println(tow);

        }
        System.out.println(statusCode);
    }


    public static void main(String[] args) throws IOException {
        login();
    }









}
