package sy.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import sy.util.HttpClientPoolUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class shehui {

    private static CloseableHttpClient httpClient = HttpClientPoolUtil.getHttpClient(true, null, 20000, 20000, 20000);

    public static void headerWrapper(AbstractHttpMessage methord) {
        methord.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0");
        methord.setHeader("accept", "*/*");
        methord.setHeader("accept-language", "zh-CN");
        methord.setHeader("Accept-Encoding", "gzip, deflate");
    }

    public static void main(String[] args) throws IOException {
        HttpGet get = new HttpGet("http://www.bjrbj.gov.cn/csibiz/indinfo/login.jsp");
        headerWrapper(get);
        HttpResponse httpresponseydinfo = httpClient.execute(get);
        HttpEntity entityydperson = httpresponseydinfo.getEntity();
        String ydpersonJson = EntityUtils.toString(entityydperson);
        System.out.println(ydpersonJson);
    }


}
