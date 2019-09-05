package test.chsi;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import sy.util.HttpClientPoolUtil;

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
        methord.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
        methord.setHeader("Host", "account.chsi.com.cn");
        methord.setHeader("Origin", "http://www.bjrbj.gov.cn");
        methord.setHeader("Referer", "https://my.chsi.com.cn/archive/index.jsp");
    }

    //登陆地址
    /**
     * 学信网官网:https://www.chsi.com.cn/
     * 学信档案个人查询:https://my.chsi.com.cn/archive/index.jsp
     * 学信档案查询登录页面:https://account.chsi.com.cn/passport/login
     */
    private static String loginUrl = "https://account.chsi.com.cn/passport/login";

}
