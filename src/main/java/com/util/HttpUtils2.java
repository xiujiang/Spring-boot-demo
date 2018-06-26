package com.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtils2 {

	static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

	static {
		// 设置最大连接数
		cm.setMaxTotal(200);
		// 设置每个主机地址的并发数
		cm.setDefaultMaxPerRoute(20);
	}

	public static String get(String url) throws Exception {
		CloseableHttpClient httpclient = new SSLClient();

		// 创建http GET请求
		HttpGet httpGet = new HttpGet(url);
		String content = null;
		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				content = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} finally {
			if (response != null) {
				response.close();
			}
			return content;
			// 此处不能关闭httpClient，如果关闭httpClient，连接池也会销毁
			// httpClient.close();
		}
	}

	public static String post(String url, Map<String, Object> params) throws Exception {
		//1. 创建Httpclient对象
		CloseableHttpClient httpclient = new SSLClient();

		//2. 创建http POST请求
		HttpPost httpPost = new HttpPost(url);
		//3. 伪造为Firefox浏览器
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");
//		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Content-Type", "application/vnd.ms-excel");
		//4. 构造form表单
		// 设置2个post参数，一个是scope、一个是q
		List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String next = it.next();
				parameters.add(new BasicNameValuePair(next, String.valueOf(params.get(next))));
			}
		}
		//5. 构造一个form表单式的实体
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
		//6. 将请求实体设置到httpPost对象中
		httpPost.setEntity(formEntity);

		CloseableHttpResponse response = null;
		String content = null;
		try {
			//7. 执行请求
			response = httpclient.execute(httpPost);
			// 判断返回状态是否为200
//			System.out.println("httpresponse:"+response);
			if (response.getStatusLine().getStatusCode() == 200) {
				content = EntityUtils.toString(response.getEntity(), "UTF-8");

			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if (response != null) {
				response.close();
			}
//            httpclient.close();
			return content;
		}
	}


	public static byte[] postEntity(String url, Map<String, Object> params) throws Exception {
		//1. 创建Httpclient对象
		CloseableHttpClient httpclient = new SSLClient();

		//2. 创建http POST请求
		HttpPost httpPost = new HttpPost(url);
		//3. 伪造为Firefox浏览器
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");
//		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Content-Type", "application/vnd.ms-excel");
		//4. 构造form表单
		// 设置2个post参数，一个是scope、一个是q
		List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
		if (params != null) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String next = it.next();
				parameters.add(new BasicNameValuePair(next, String.valueOf(params.get(next))));
			}
		}
		//5. 构造一个form表单式的实体
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
		//6. 将请求实体设置到httpPost对象中
		httpPost.setEntity(formEntity);

		CloseableHttpResponse response = null;
		HttpEntity content = null;
		byte[] bytes = null;
		try {
			response = httpclient.execute(httpPost);
			//7. 执行请求
			bytes = EntityUtils.toByteArray(response.getEntity());
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if (response != null) {
				response.close();
			}
//            httpclient.close();
			return bytes;
		}
	}



	public static String postJson(String url,JSONObject json) throws Exception {
		//1. 创建Httpclient对象
		CloseableHttpClient httpclient = new SSLClient();

		//2. 创建http POST请求
		HttpPost httpPost = new HttpPost(url);
		//3. 伪造为Firefox浏览器
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0");
//		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Accept", "application/json");
		//4. 构造form表单
		// 设置2个post参数，一个是scope、一个是q
		List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
		if (!StringUtils.isEmpty(json)) {
			Iterator<String> it = json.keySet().iterator();
			System.out.println("11111111111111111111111111111111111"+it.toString());
			while (it.hasNext()) {
				String next = it.next();
				parameters.add(new BasicNameValuePair(next, String.valueOf(json.get(next))));
			}
		}
		//5. 构造一个form表单式的实体
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
		//6. 将请求实体设置到httpPost对象中
		httpPost.setEntity(formEntity);

		CloseableHttpResponse response = null;
		String content = null;
		try {
			//7. 执行请求
			response = httpclient.execute(httpPost);
			// 判断返回状态是否为200
//			System.out.println("httpresponse:"+response);
			if (response.getStatusLine().getStatusCode() == 200) {
				content = EntityUtils.toString(response.getEntity(), "UTF-8");

			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if (response != null) {
				response.close();
			}
//            httpclient.close();
			return content;
		}
	}


	public static String getWebContentByJsonPost(String url, String objectString)
			throws Exception {
		long start = System.currentTimeMillis();
		PostMethod post = new PostMethod(url);
		StringRequestEntity stringRequestEntity = new StringRequestEntity(objectString, "application/json", "UTF-8");
		post.setRequestEntity(stringRequestEntity);
		post.addRequestHeader("Content-type", "application/json; charset=" + "UTF-8");
		post.setRequestHeader("Accept", "application/json");

		ProtocolSocketFactory fcty = new com.util.MySecureProtocolSocketFactory();
		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
		HttpClient client = new HttpClient(connectionManager);
	 	try {
			client.setTimeout(30000);
			int statusCode = client.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + post.getStatusLine());
				return null;
			}
			byte[] responseBody = post.getResponseBody();
			return new String(responseBody, "UTF-8");
		} finally {
			System.out.println("excute httpclient times #######" + (System.currentTimeMillis() - start) + "ms");
			post.releaseConnection();
		}
	}

}
