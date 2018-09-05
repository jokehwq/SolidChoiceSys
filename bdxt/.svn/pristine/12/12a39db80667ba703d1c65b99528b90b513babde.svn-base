package com.foreveross.webbase.weixin.sdk.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

import com.foreveross.webbase.weixin.sdk.exception.WeixinException;
import com.sun.net.ssl.internal.www.protocol.https.Handler;
import com.sun.net.ssl.internal.www.protocol.https.HttpsURLConnectionOldImpl;

/**
 * SSLNet工具类
 * 
 * @author 
 * @createTime 
 * @history  1.修改时间,修改;修改内容：
 * 
 */
public class SSLNetProvider {

	private static final Logger logger = Logger.getLogger(SSLNetProvider.class);

	private class TrustAnyTrustManager implements X509TrustManager {
	 
	   public void checkClientTrusted(X509Certificate[] chain, String authType)
	      throws CertificateException {
	   }
	 
	   public void checkServerTrusted(X509Certificate[] chain, String authType)
	      throws CertificateException {
	   }
	 
	   public X509Certificate[] getAcceptedIssuers() {
	     return new X509Certificate[] {};
	   }
	}
	 
	/**
	 * doGet请求
	 * @param url
	 * @return
	 * @throws WeixinException
	 */
	@SuppressWarnings("deprecation")
	public String doGet(String url)throws WeixinException{
		logger.info("doGet url="+url);
	    InputStream in = null;
	    HttpsURLConnectionOldImpl conn=null;
	    try {
	    	SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new SecureRandom());
			URL console = new URL(null,url,new Handler());
			conn = (HttpsURLConnectionOldImpl) console.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			in = conn.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String ret = null;
			String str_return = "";
			while ((ret=br.readLine())!=null) {
				str_return = str_return+ ret+"\n";
			}
			logger.info("result="+str_return);
			return str_return;
	    } catch (ConnectException e) {
	    	logger.error("ConnectException:",e);
	    	throw new WeixinException("链接微信服务器失败");
	    } catch (IOException e) {
	    	logger.error("IOException:",e);
	    	throw new WeixinException("从微信服务器读取数据失败");
	    } catch (Exception e) {
	    	logger.error("Exception:",e);
	    	throw new WeixinException("从微信服务器读取数据失败");
	    } finally {
	    	if(in!=null){
	    		try {
	    	    	in.close();
	    		} catch (Exception e) {
	    			logger.error("Exception:",e);
	    		}
	    	}
	    	if(conn!=null){
	    		try {
					conn.disconnect();
				} catch (Exception e) {
					logger.error("Exception",e);
				}
	    	}
	    }
	}
	
	/**
	 * doPost请求
	 * @param url
	 * @param data
	 * @return
	 * @throws WeixinException
	 */
	@SuppressWarnings("deprecation")
	public String doPost(String url,String data) throws WeixinException{
		logger.info("doPost url="+url);
    	logger.info("data="+data);
	    InputStream is = null;
	    OutputStream out=null;
	    HttpsURLConnectionOldImpl conn = null;
	    try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new java.security.SecureRandom());
			URL console = new URL(null,url,new Handler());
			conn = (HttpsURLConnectionOldImpl) console.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			//conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            out=conn.getOutputStream();
			DataOutputStream httpOut = new DataOutputStream(out);
	        httpOut.write(data.getBytes("UTF-8"));
	        httpOut.flush();
			is = conn.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String ret = null;
			String str_return = "";
			while ((ret=br.readLine())!=null) {
				str_return = str_return+ ret;
			}
			logger.info("result="+str_return);
			return str_return;
	    } catch (ConnectException e) {
	    	throw new WeixinException("链接微信服务器失败");
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	throw new WeixinException("从微信服务器读取数据失败1"+e.getMessage());
	    } catch (Exception e) {
	    	throw new WeixinException("从微信服务器读取数据失败2"+e.getMessage());
	    }finally {
	    	if(out!=null){
	    		try {
	    			out.close();
	    		} catch (Exception e) {
	    			logger.error("Exception:",e);
	    		}
	    	}
	    	if(is!=null){
	    		try {
	    	    	is.close();
	    		} catch (Exception e) {
	    			logger.error("Exception:",e);
	    		}
	    	}
	    	if(conn!=null){
	    		try {
					conn.disconnect();
				} catch (Exception e) {
					logger.error("Exception",e);
				}
	    	}
	    }
	}
	
	/**
	 * doGet请求获取字节数组
	 * @param url
	 * @return
	 * @throws WeixinException
	 */
	@SuppressWarnings("deprecation")
	public byte[] doGetBytes(String url) throws WeixinException{
	    InputStream in = null;
	    ByteArrayOutputStream outStream=null;
	    HttpsURLConnectionOldImpl conn=null;
	    try {
	    	SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },new SecureRandom());
			URL console = new URL(null,url,new Handler());
			conn = (HttpsURLConnectionOldImpl) console.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			in = conn.getInputStream();
			outStream = new ByteArrayOutputStream();
			byte[] b=new byte[1024];
			int length=-1;
			while((length=in.read(b, 0, 1024))!=-1){
				outStream.write(b,0,length);
			}
			return outStream.toByteArray();
	    } catch (ConnectException e) {
	    	throw new WeixinException("链接微信服务器失败");
	    } catch (IOException e) {
	    	throw new WeixinException("从微信服务器读取数据失败");
	    } catch (Exception e) {
	    	throw new WeixinException("从微信服务器读取数据失败");
	    }finally {
	    	if(outStream!=null){
	    		try {
	    			outStream.close();
	    		} catch (Exception e) {
	    		     e.printStackTrace();
	    		}
	    	}
	    	if(in!=null){
	    		try {
	    	    	in.close();
	    		} catch (Exception e) {
	    		     e.printStackTrace();
	    		}
	    	}if(conn!=null){
	    		conn.disconnect();
	    	}
	    }
	}

}
