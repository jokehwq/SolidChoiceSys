/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.foreveross.webbase.common.utils;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;

/**
 * http的请求
 * @author L.cm
 * @date 2013-10-9 下午2:40:19
 */ 
public class HttpUtil {
	
private static final String DEFAULT_CHARSET = "UTF-8";
	
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        AsyncHttpClientConfig httpconfig = new AsyncHttpClientConfig.Builder()
        		.setRequestTimeout(10000).setConnectTimeout(10000).build();
		AsyncHttpClient http = new AsyncHttpClient(httpconfig);
		try {
			AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
			builder.setBodyEncoding(DEFAULT_CHARSET);
			if (params != null && !params.isEmpty()) {
			    Set<String> keys = params.keySet();
			    for (String key : keys) {
			        builder.addQueryParam(key, params.get(key));
			    }
			}
			if (headers != null && !headers.isEmpty()) {
			    Set<String> keys = headers.keySet();
			    for (String key : keys) {
			        builder.addHeader(key, params.get(key));
			    }
			}
			Future<Response> f = builder.execute();
			String body = f.get().getResponseBody(DEFAULT_CHARSET);
			return body;
		} finally {
			if(http!=null) {
				http.close();
			}
		}
    }

    public static String get(String url) throws Exception {
        return get(url, null);
    }

    public static String get(String url, Map<String, String> params) throws Exception {
        return get(url, params, null);
    }

    public static String post(String url, Map<String, String> params) throws Exception {
    	AsyncHttpClientConfig httpconfig = new AsyncHttpClientConfig.Builder()
 				.setRequestTimeout(10000).setConnectTimeout(10000).build();
        AsyncHttpClient http = new AsyncHttpClient(httpconfig);
		try {
			AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
			builder.setBodyEncoding(DEFAULT_CHARSET);
			if (params != null && !params.isEmpty()) {
			    Set<String> keys = params.keySet();
			    for (String key : keys) {
			        builder.addQueryParam(key, params.get(key));
			    }
			}
			Future<Response> f = builder.execute();
			String body = f.get().getResponseBody(DEFAULT_CHARSET);
			return body;
		} finally {
			if(http!=null) {
				http.close();
			}
		}
    }

    public static byte[] download(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        AsyncHttpClientConfig httpconfig = new AsyncHttpClientConfig.Builder()
        		.setRequestTimeout(10000).setConnectTimeout(10000).build();
		AsyncHttpClient http = new AsyncHttpClient(httpconfig);
		try {
			AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
			builder.setBodyEncoding(DEFAULT_CHARSET);
			if (params != null && !params.isEmpty()) {
			    Set<String> keys = params.keySet();
			    for (String key : keys) {
			        builder.addQueryParam(key, params.get(key));
			    }
			}
			if (headers != null && !headers.isEmpty()) {
			    Set<String> keys = headers.keySet();
			    for (String key : keys) {
			        builder.addHeader(key, params.get(key));
			    }
			}
			Future<Response> f = builder.execute();
			byte[] body = f.get().getResponseBodyAsBytes();
			return body;
		} finally {
			if(http!=null) {
				http.close();
			}
		}
    }
}