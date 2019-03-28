package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import sy.util.HttpClientPoolUtil;

public class MyTest {


    private static CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(true, null, 20000, 20000, 20000);

    public static void headerWrapper(AbstractHttpMessage methord) {
        methord.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0");
        methord.setHeader("accept", "*/*");
        methord.setHeader("accept-language", "zh-CN");
        methord.setHeader("Accept-Encoding", "gzip, deflate");
        methord.setHeader("Connection", "keep-alive");
    }

    /**
     * 获取附加码和手机验证码
     */
    @Test
    public void getCheckCode() {
        try {
            String phoneUrl = "http://www.bjrbj.gov.cn/csibiz/indinfo/passwordSetAction!getTelSafeCode";
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

            //验证码下载网址
            String checkCodeURL = "http://www.bjrbj.gov.cn/csibiz/indinfo/validationCodeServlet.do";
            //获取附加码
            HttpGet getCheckCode = new HttpGet(checkCodeURL);
            FileOutputStream fileOutputStream = null;
            HttpResponse response;
            response = httpClient.execute(getCheckCode);
            /*验证码写入文件,当前工程的根目录,保存为verifyCode.jped*/
            fileOutputStream = new FileOutputStream(new File("verifyCode.jpeg"));
            response.getEntity().writeTo(fileOutputStream);
            //识别验证码
            File imageFile = new File("D:\\WorkSpace\\zxzxshow\\zxshow\\verifyCode.jpeg");
            Tesseract instance = new Tesseract();
            //将验证码图片的内容识别为字符串
            String result = instance.doOCR(imageFile);
            String trim = result.trim();
            System.err.println(imageFile.getName() + " result：" + trim);

            params.add(new BasicNameValuePair("safeCode", trim));

            phonePost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response1 = httpClient.execute(phonePost);
            System.out.println(EntityUtils.toString(response1.getEntity()));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TesseractException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void list() {
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            HttpPost httpGet = new HttpPost("http://www.bjrbj.gov.cn/csibiz/indinfo/login_check");
            headerWrapper(httpGet);
            // 发送请求
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);
            System.out.println(body);
            //----------判断是否重定向开始
            int code = response.getStatusLine().getStatusCode();
            String newuri = "";
            if (code == 302) {
                System.out.println("为重定向");
                Header header = response.getFirstHeader("location");
                newuri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
                System.out.println(newuri);
                HttpPost httpPost = new HttpPost(newuri);
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                params.add(new BasicNameValuePair("j_username", "13020551541"));
                params.add(new BasicNameValuePair("j_password", "ZHANGchi2012"));
                params.add(new BasicNameValuePair("safecode", "q5se"));
                params.add(new BasicNameValuePair("i_phone", "545811"));

                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                // 发送请求
                HttpResponse httpresponse = httpClient.execute(httpPost);
                System.out.println(httpresponse.getStatusLine().getStatusCode());
                if (httpresponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpGet getUser = new HttpGet("http://www.bjrbj.gov.cn/csibiz/indinfo/search/ind/ind_new_info_index.jsp");
                    HttpResponse httpResponse = httpClient.execute(getUser);
                    String body2 = EntityUtils.toString(httpResponse.getEntity());
                    System.out.println("获取到的个人页面是" + body2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void string (){
        Base64.Encoder encoder = Base64.getEncoder();
        String j_password = "ZHANGchi2012";
        String s = encoder.encodeToString(j_password.getBytes());
        System.out.println(s);
    }
}




