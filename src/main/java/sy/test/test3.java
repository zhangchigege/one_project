package sy.test;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class test3 {
    public static String loginurl = "https://weibo.com/";
    static Cookie[] cookies = {};

    static HttpClient httpClient = new HttpClient();
    //http://www.bjrbj.gov.cn/csibiz/indinfo/passwordSetAction!getTelSafeCode

    static String email = "13020551541";//你的email
    static String psw = "ZHANGchi2012";//你的密码
    // 消息发送的action
    //String url = "http://www.kaixin001.com/home/";

    public static void getUrlContent() throws Exception {
        //参数设置
        HttpClientParams httparams = new HttpClientParams();
        //超时连接
        httparams.setSoTimeout(30000);
        httpClient.setParams(httparams);
        //访问端口
        httpClient.getHostConfiguration().setHost("https://weibo.com/", 80);
        //编码
        httpClient.getParams().setParameter(
               HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        //浏览器头编码信息
        PostMethod login = new PostMethod(loginurl);
        login.addRequestHeader("Content-Type",
                "text/html;charset=utf-8");

        NameValuePair Email = new NameValuePair("loginemail", email);// 邮箱
        NameValuePair password = new NameValuePair("password", psw);// 密码


        NameValuePair[] data = { Email, password };
        login.setRequestBody(data);

        httpClient.executeMethod(login);
        int statuscode = login.getStatusCode();
        System.out.println(statuscode + "-----------");
        String result = login.getResponseBodyAsString();
        System.out.println(result+"++++++++++++");

        cookies = httpClient.getState().getCookies();
        System.out.println("==========Cookies============");
        int i = 0;
        for (Cookie c : cookies) {
            System.out.println(++i + ":   " + c);
        }
        httpClient.getState().addCookies(cookies);

        // 当state为301或者302说明登陆页面跳转了，登陆成功了
        if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY)
                || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                || (statuscode == HttpStatus.SC_SEE_OTHER)
                || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
            // 读取新的 URL 地址
            Header header = login.getResponseHeader("location");
            System.out.println("=========================="+header);
            // 释放连接
            login.releaseConnection();
            //System.out.println("获取到跳转header>>>" + header);
            if (header != null) {
                String newuri = header.getValue();
                if ((newuri == null) || ("".equals(newuri))) {
                    newuri = "/";
                }
                GetMethod redirect = new GetMethod(newuri);
                // ////////////
                redirect.setRequestHeader("Cookie", cookies.toString());
                httpClient.executeMethod(redirect);
                System.out.println(redirect.getResponseBodyAsString());
               // System.out.println("Redirect:" + redirect.getStatusLine().toString());
                redirect.releaseConnection();

            } else {
                System.out.println("Invalid redirect");
            }
        } else {
            // 用户名和密码没有被提交，当登陆多次后需要验证码的时候会出现这种未提交情况
            System.out.println("用户没登陆");
            System.exit(1);
        }

    }






    public static void main(String[] args) throws Exception {
        getUrlContent();
    }
}
