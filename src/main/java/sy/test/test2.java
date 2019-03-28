package sy.test;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class test2 {

    private static Logger log = LoggerFactory.getLogger(test2.class);
    private static String Stringnewuri;
    private static String newuri;
    static String email = "13020551541";//你的email
    static String psw = "ZHANGchi2012";//你的密码

    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        HttpMethodRetryHandler myretryhandler = new HttpMethodRetryHandler() {
            //异常处理机制
            @Override
            public boolean retryMethod(
                    final HttpMethod method,
                    final IOException exception,
                    int executionCount) {
                if (executionCount >= 5) {
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                if (!method.isRequestSent()) {
                    return true;
                }
                return false;
            }
        };
        //默认设置,失败重试
        DefaultHttpMethodRetryHandler handler = new DefaultHttpMethodRetryHandler(10, true);
        client.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, handler);
        PostMethod post = new PostMethod("https://weibo.com");
        NameValuePair Email = new NameValuePair("loginemail", email);// 邮箱
        NameValuePair password = new NameValuePair("password", psw);// 密码
        NameValuePair[] data = { Email, password };
        post.setRequestBody(data);

        try {

            client.executeMethod(post);
// 检查是否重定向

            int statuscode = post.getStatusCode();
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY) ||
                    (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
// 读取新的 URL 地址
                Header header = post.getResponseHeader("location");
                if (header != null) {
                    Stringnewuri = header.getValue();
                    if ((newuri == null) || ("".equals(newuri))) {
                        newuri = "/";
                    }
                    GetMethod redirect = new GetMethod(newuri);
                    client.executeMethod(redirect);
                    System.out.println("Redirect:" + redirect.getStatusLine().toString());
                    redirect.releaseConnection();
                }
            } else {
                System.out.println("Invalid redirect");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }

    }

    /**
     * 上传url文件到指定URL
     *
     * @param postUrl 上传路径及参数,注意有些中文参数需要使用预先编码 eg : URLEncoder.encode(appName, "UTF-8")
     * @return
     * @throws IOException
     */
    public static String doUploadFile(String postUrl) throws IOException {
        if (StringUtils.isEmpty(postUrl)) {
            return null;
        }
        String response = "";
        PostMethod postMethod = new PostMethod(postUrl);
        try {
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams()
                    .setConnectionTimeout(50000);// 设置连接时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
            } else {
                response = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        return response;
    }

    /**
     * 上传文件到指定URL
     *
     * @param file
     * @param url
     * @return
     * @throws IOException
     */
    public static String doUploadFile(File file, String url) throws IOException {
        String response = "";
        if (!file.exists()) {
            return "file not exists";
        }
        PostMethod postMethod = new PostMethod(url);
        try {
            //----------------------------------------------
            // FilePart：用来上传文件的类,file即要上传的文件
            FilePart fp = new FilePart("file", file);
            Part[] parts = {fp};

            // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts,
                    postMethod.getParams());
            postMethod.setRequestEntity(mre);
            //---------------------------------------------
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams()
                    .setConnectionTimeout(50000);// 由于要上传的文件可能比较大 , 因此在此设置最大的连接超时时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
            } else {
                response = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
        return response;
    }

}
