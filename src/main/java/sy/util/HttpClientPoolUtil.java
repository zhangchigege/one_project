package sy.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientPoolUtil{
	public static PoolingHttpClientConnectionManager clientConnectionManager = null;

	private int maxTotal = 400;

	private int defaultMaxPerRoute = 30;

	private HttpClientPoolUtil(int maxTotal, int defaultMaxPerRoute) {
		this.maxTotal = maxTotal;
		this.defaultMaxPerRoute = defaultMaxPerRoute;
		clientConnectionManager.setMaxTotal(maxTotal);
		clientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
	}

	private HttpClientPoolUtil() {
		clientConnectionManager.setMaxTotal(maxTotal);
		clientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
	}

	private static HttpClientPoolUtil poolManager = null;

	public synchronized static HttpClientPoolUtil getInstance() {
		if (poolManager == null) {

			poolManager = new HttpClientPoolUtil();

		}

		return poolManager;
	}

	public synchronized static HttpClientPoolUtil getInstance(int maxTotal, int defaultMaxPerRoute) {
		if (poolManager == null) {
			poolManager = new HttpClientPoolUtil(maxTotal, defaultMaxPerRoute);
		}

		return poolManager;
	}

	/***
	 * 获取CloseableHttpClient 支持https
	 * 
	 * @param proxy
	 *            :代理
	 * @return
	 */
	public static CloseableHttpClient getHttpsClient(String proxy) {
		return getHttpClient(true, proxy, 20000, 20000, 20000);
	}

	/***
	 * 获取CloseableHttpClient
	 * 
	 * @param proxy
	 *            :代理
	 * @return
	 */
	public static CloseableHttpClient getHttpClient(String proxy) {
		return getHttpClient(true, proxy, 20000, 20000, 20000);
	}

	/***
	 * 获取CloseableHttpClient
	 * 
	 * @param ssl
	 *            :是否支持 https
	 * @param proxy
	 *            :代理
	 * @param connectTimeout
	 *            :connect超时，设置 1000-30000之间
	 * @param connectionRequestTimeout
	 *            :request超时，设置 1000-30000之间
	 * @param socketTimeout
	 *            :socket超时，设置 1000-30000之间
	 * @return
	 */
	public static CloseableHttpClient getHttpClient(boolean ssl, String proxy, int connectTimeout, int connectionRequestTimeout, int socketTimeout) {
		return getHttpClient(ssl, null, proxy, connectTimeout, connectionRequestTimeout, socketTimeout);
	}

	/***
	 * 获取CloseableHttpClient
	 * 
	 * @param ssl
	 *            :是否支持 https
	 * @param proxy
	 *            :代理
	 * @param connectTimeout
	 *            :connect超时，设置 1000-30000之间
	 * @param connectionRequestTimeout
	 *            :request超时，设置 1000-30000之间
	 * @param socketTimeout
	 *            :socket超时，设置 1000-30000之间
	 * @return
	 */
	public static CloseableHttpClient getHttpClient(boolean ssl, List<Header> cookieList, String proxy, int connectTimeout, int connectionRequestTimeout,
			int socketTimeout) {
		if (clientConnectionManager == null) {
			//自动判断https  和 http
			//			SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
			//			try {
			//				sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			//				SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
			//				Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("https", socketFactory)
			//						.register("http", new PlainConnectionSocketFactory()).build();
			//				clientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			//
			//			} catch (Exception e) {
			//				log.error("init PoolingHttpClientConnectionManager Error={}", e);
			//			}

			if (ssl) {
				try {
					TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						@Override
						public void checkClientTrusted(X509Certificate[] certs, String authType) {
						}

						@Override
						public void checkServerTrusted(X509Certificate[] certs, String authType) {
						}

					} };

					SSLContext sslcontext = SSLContext.getInstance("SSL");
					sslcontext.init(null, trustAllCerts, new java.security.SecureRandom());
					HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

					// Create all-trusting host name verifier
					HostnameVerifier allHostsValid = new HostnameVerifier() {
						@Override
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};

					SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, allHostsValid);
					Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
							.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();

					clientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

				} catch (KeyManagementException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}

			} else {
				clientConnectionManager = new PoolingHttpClientConnectionManager();
			}

			getInstance();
		}

		HttpClientBuilder httpBuilder = HttpClients.custom().setConnectionManager(clientConnectionManager);
		//失败后 自动重试三次
		httpBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
		// 超时
		int min = 5000;
		int max = 999999999;

		connectTimeout = limitTimeout(connectTimeout, min, max);
		connectionRequestTimeout = limitTimeout(connectionRequestTimeout, min, max);
		socketTimeout = limitTimeout(socketTimeout, min, max);
		RequestConfig.Builder config_builder = RequestConfig.custom();
		config_builder.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout);
		//此redirect 只适用于get请求  post请求 不支持  原因是  http协议 对post的跳转 有限制
		//		config_builder.setCircularRedirectsAllowed(true);
		//		config_builder.setRedirectsEnabled(true);

		httpBuilder.setDefaultRequestConfig(config_builder.build());

		CookieStore store = new BasicCookieStore();
		httpBuilder.setDefaultCookieStore(store); // 3 配置 cookie

		/*
		 * if(proxy != null) httpBuilder.setProxy(proxy);
		 */
		return httpBuilder.build();
	}

	/***
	 * 获取CloseableHttpClient
	 * 
	 * @param ssl
	 *            :是否支持 https
	 * @param proxy
	 *            :代理
	 * @param connectTimeout
	 *            :connect超时，设置 1000-30000之间
	 * @param connectionRequestTimeout
	 *            :request超时，设置 1000-30000之间
	 * @param socketTimeout
	 *            :socket超时，设置 1000-30000之间
	 * @param ip
	 *            : 指定网卡IP去访问
	 * @return
	 */
	public static CloseableHttpClient getHttpClient(boolean ssl, List<Header> cookieList, String proxy, int connectTimeout, int connectionRequestTimeout,
			int socketTimeout, String ip) {
		if (clientConnectionManager == null) {
			if (ssl) {
				try {
					TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
						@Override
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						@Override
						public void checkClientTrusted(X509Certificate[] certs, String authType) {
						}

						@Override
						public void checkServerTrusted(X509Certificate[] certs, String authType) {
						}

					} };

					SSLContext sslcontext = SSLContext.getInstance("SSL");
					sslcontext.init(null, trustAllCerts, new java.security.SecureRandom());
					HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());

					// Create all-trusting host name verifier
					HostnameVerifier allHostsValid = new HostnameVerifier() {
						@Override
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};

					SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, allHostsValid);
					Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
							.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();

					clientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

				} catch (KeyManagementException e) {
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}

			} else {
				clientConnectionManager = new PoolingHttpClientConnectionManager();
			}

			getInstance();
		}

		HttpClientBuilder httpBuilder = HttpClients.custom().setConnectionManager(clientConnectionManager);
		//失败后 自动重试三次
		httpBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));

		// 超时
		int min = 5000;
		int max = 30000;
		connectTimeout = limitTimeout(connectTimeout, min, max);
		connectionRequestTimeout = limitTimeout(connectionRequestTimeout, min, max);
		socketTimeout = limitTimeout(socketTimeout, min, max);
		RequestConfig.Builder config_builder = RequestConfig.custom();
		config_builder.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout);
		//此redirect 只适用于get请求  post请求 不支持  原因是  http协议 对post的跳转 有限制  
		//		config_builder.setCircularRedirectsAllowed(true);
		//		config_builder.setRedirectsEnabled(true);
		if (null != ip && !"".equals(ip)) {
//			log.info("指定网卡访问：" + ip);
			InetAddress inetAddress = null;
			try {
				inetAddress = InetAddress.getByName(ip);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			config_builder.setLocalAddress(inetAddress);
		} else {
			if (proxy == null) {
				/* 循环使用本机IP */
				/*
				 * String strIP = IPProxyUtil.instance().getIpStr(); if (strIP
				 * != null && strIP != "") { log.info("使用本机ip：" + strIP);
				 * InetAddress inetAddress = null; try { inetAddress =
				 * InetAddress.getByName(strIP); } catch (UnknownHostException
				 * e) { e.printStackTrace(); }
				 * config_builder.setLocalAddress(inetAddress);
				 * 
				 * }
				 */
			} else {
				String[] p = proxy.split(":");
				if (p.length == 1) {
//					log.info("使用本机ip：" + p[0]);
					InetAddress inetAddress = null;
					try {
						inetAddress = InetAddress.getByName(p[0]);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					config_builder.setLocalAddress(inetAddress);
				} else if (p.length == 2) {
					config_builder.setProxy(new HttpHost(p[0], Integer.valueOf(p[1])));
				}
			}
		}

		httpBuilder.setDefaultRequestConfig(config_builder.build());

		CookieStore store = new BasicCookieStore();
		httpBuilder.setDefaultCookieStore(store); // 3 配置 cookie

		/*
		 * if(proxy != null) httpBuilder.setProxy(proxy);
		 */
		return httpBuilder.build();
	}

	public static int limitTimeout(int timeout, int min, int max) {
		if (timeout < min) {
            timeout = min;
        }

		if (timeout > max) {
            timeout = max;
        }

		return timeout;
	}
}