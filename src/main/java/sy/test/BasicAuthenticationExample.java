package sy.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;

public class BasicAuthenticationExample {
    public BasicAuthenticationExample() {
    }

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        client.getState().setCredentials( "www.verisign.com" , "realm" , new UsernamePasswordCredentials( "username" , "password" ) );

        GetMethod get = new GetMethod( "https://www.verisign.com/products/index.html" );
        get.setDoAuthentication( true );
        int status = client.executeMethod( get );
        System.out.println(status+ "\n" + get.getResponseBodyAsString());
        get.releaseConnection();
    }
}
