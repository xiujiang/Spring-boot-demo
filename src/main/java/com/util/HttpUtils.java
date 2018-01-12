package com.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

public class HttpUtils {

    static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

    public static String getWebContentByPost(String url) throws Exception {
        return getWebContentByPost(url, "utf-8", null);
    }

    public static String getWebContentByPost(String url, Map params)
            throws Exception {
        return getWebContentByPost(url, "utf-8", params);
    }

    public static String getWebContentByPost(String url, String charset)
            throws Exception {
        return getWebContentByPost(url, charset, null);
    }

    public static String getWebContentByPost(String url, String charset,
                                             Map<String, String> params) throws HttpException, IOException {
        PostMethod post = new PostMethod(url);
        long start = System.currentTimeMillis();

        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                post.setParameter(next, params.get(next));
            }
        }
        if (null!=charset) {
            post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset="+charset);
            post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
        }
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient client = new HttpClient(connectionManager);
        try {
            // 1秒超时
            client.setTimeout(30000);
            int statusCode = client.executeMethod(post);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + post.getStatusLine());
            }
            byte[] responseBody = post.getResponseBody();
            // System.out.println(new String(responseBody, "utf-8"));
            if (null!=charset) {
                return new String(responseBody, charset);
            }else{
                return new String(responseBody);
            }
        } finally {
            System.out.println("excute httpclient times #######"
                    + (System.currentTimeMillis() - start) + "ms");
            post.releaseConnection();
        }

    }

    @SuppressWarnings("deprecation")
    public static String getWebContentByGet(String url, String charset,
                                            Map params) throws HttpException, IOException {
        long start = System.currentTimeMillis();
        GetMethod get = new GetMethod(url);

        if (params != null) {

            HttpMethodParams param = new HttpMethodParams();
            Iterator it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry en = (Map.Entry) it.next();
                param.setParameter((String) en.getKey(), en.getValue());
            }
            get.setParams(param);
        }

        // params.set
        // post.setParams( params);
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient client = new HttpClient(connectionManager);
        try {
            // 1秒超时
            client.setTimeout(30000);
            int statusCode = client.executeMethod(get);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + get.getStatusLine());
            }
            byte[] responseBody = get.getResponseBody();
            // System.out.println(new String(responseBody, "utf-8"));
            return new String(responseBody, charset);
        } finally {
            System.out.println("excute httpclient times #######"
                    + (System.currentTimeMillis() - start) + "ms");
            get.releaseConnection();
        }

    }

    public static String getWebContent(String url, String charset, String body,
                                       Map<String, String> params) throws Exception {
        long start = System.currentTimeMillis();
        PostMethod post = new PostMethod(url);

        if (body != null) {
            post.setRequestEntity(new StringRequestEntity(body, null, "utf-8"));
        }

        if (params != null) {

            Iterator<String> it = params.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                post.setParameter(next, params.get(next));
            }
        }

        // params.set
        // post.setParams( params);
        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient client = new HttpClient(connectionManager);
        try {
            int statusCode = client.executeMethod(post);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + post.getStatusLine());
            }
            byte[] responseBody = post.getResponseBody();
            // System.out.println(new String(responseBody, "utf-8"));
            return new String(responseBody, charset);
        } finally {
            System.out.println("excute httpclient times #######"
                    + (System.currentTimeMillis() - start) + "ms");
            post.releaseConnection();
        }
    }

    public static String getWebContentByJsonPost(String url, String objectString, boolean isProxy, String ip, int port)
            throws Exception {
        long start = System.currentTimeMillis();
        PostMethod post = new PostMethod(url);
        StringRequestEntity stringRequestEntity = new StringRequestEntity(objectString, "application/json", "UTF-8");
        post.setRequestEntity(stringRequestEntity);
//        post.addRequestHeader("Content-type", "application/json; charset=" + "UTF-8");
        post.setRequestHeader("Accept", "application/json");
        post.addRequestHeader("Content-type","application/x-www-form-urlencoded");

        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient client = new HttpClient(connectionManager);
        if (isProxy) {
            // 设置代理服务器的ip地址和端口
            client.getHostConfiguration().setProxy(ip, port);
            // 使用抢先认证
            client.getParams().setAuthenticationPreemptive(true);
        }
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


    public static String getWebContentByJsonGet(String url, String objectString, boolean isProxy, String ip, int port)
            throws Exception {
        long start = System.currentTimeMillis();
        GetMethod get = new GetMethod(url);
//        post.addRequestHeader("Content-type", "application/json; charset=" + "UTF-8");
        get.setRequestHeader("Accept", "application/json");
        get.addRequestHeader("Content-type","application/x-www-form-urlencoded");

        ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
        Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
        HttpClient client = new HttpClient(connectionManager);
        if (isProxy) {
            // 设置代理服务器的ip地址和端口
            client.getHostConfiguration().setProxy(ip, port);
            // 使用抢先认证
            client.getParams().setAuthenticationPreemptive(true);
        }
        try {
            client.setTimeout(30000);
            int statusCode = client.executeMethod(get);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + get.getStatusLine());
                return null;
            }
            byte[] responseBody = get.getResponseBody();
            return new String(responseBody, "UTF-8");
        } finally {
            System.out.println("excute httpclient times #######" + (System.currentTimeMillis() - start) + "ms");
            get.releaseConnection();
        }
    }

    public static String getHttpBodyContent(InputStream is)
            throws RuntimeException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            // 读取HTTP请求内容
            String buffer = null;
            StringBuffer sb = new StringBuffer();
            while ((buffer = br.readLine()) != null) {
                // 在页面中显示读取到的请求参数
                sb.append(buffer + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("getHttpBodyContent Exception", e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException("closeBufferReader IOException",
                            e);
                }
            }
        }
    }

//	public static String getByteContentByPost(String url,
//			Map<String, String> params, Map<String, File> paramfiles)
//			throws Exception {
//		long start = System.currentTimeMillis();
//		PostMethod post = new PostMethod(url);
//		int sum = 0;
//		if (params != null) {
//			sum += params.size();
//			Iterator<String> it = params.keySet().iterator();
//			while (it.hasNext()) {
//				String next = it.next();
//				post.setParameter(next, params.get(next));
//			}
//		}
//		if (paramfiles != null && !paramfiles.isEmpty()) {
//			sum += paramfiles.size();
//			Part[] parts = new Part[sum];
//			int count = 0;
//			for (String name : paramfiles.keySet()) {
//				parts[count] = 	new FilePart(name, paramfiles.get(name));
//				count++;
//			}
//			Iterator<String> it = params.keySet().iterator();
//			while (it.hasNext()) {
//				String next = it.next();
//				parts[count] = new StringPart(next, params.get(next));
//				count++;
//			}
//			post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
//		}
//		ProtocolSocketFactory fcty = new MySecureProtocolSocketFactory();
//		Protocol.registerProtocol("https", new Protocol("https", fcty, 443));
//		HttpClient client = new HttpClient(connectionManager);
//		try {
//			client.setTimeout(30000);
//			int statusCode = client.executeMethod(post);
//			if (statusCode != HttpStatus.SC_OK) {
//				System.err.println("Method failed: " + post.getStatusLine());
//				return null;
//			}
//			byte[] responseBody = post.getResponseBody();
//			return new String(responseBody, "UTF-8");
//		} finally {
//			System.out.println("excute httpclient times #######"
//					+ (System.currentTimeMillis() - start) + "ms");
//			post.releaseConnection();
//		}
//	}
}
