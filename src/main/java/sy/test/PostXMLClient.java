package sy.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.File;
import java.io.FileInputStream;

public class PostXMLClient {
    public static void main(String[] args) throws Exception {
        File input = new File("test.xml");
        PostMethod post = new PostMethod("http://localhost:8080/httpclient/xml.jsp");

        // 设置请求的内容直接从文件中读取
        post.setRequestBody( new FileInputStream(input));
        if (input.length() < Integer.MAX_VALUE) {
            post.setRequestContentLength(input.length());
        } else {
            post.setRequestContentLength(EntityEnclosingMethod.CONTENT_LENGTH_CHUNKED);
        }

        // 指定请求内容的类型
        post.setRequestHeader( "Content-type" , "text/xml; charset=GBK" );
        HttpClient httpclient = new HttpClient();
        int result = httpclient.executeMethod(post);
        System.out.println( "Response status code: " + result);
        System.out.println( "Response body: " );
        System.out.println(post.getResponseBodyAsString());
        post.releaseConnection();
    }
}
