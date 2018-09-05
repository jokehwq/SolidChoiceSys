package com.foreveross.webbase.weixin.sdk.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.StringUtils;


/**
 * 微信支付工具类
 * 
 * @author lwgang
 * @createTime 2015-03-12 
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class WXPayUtil {	

	/**
	 * 创建MD5签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @param mapParams
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String createSign(SortedMap<String, String> mapParams,
			String key) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		
		if(mapParams==null || StringUtils.isEmpty(key)){
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		Set es = mapParams.entrySet();
		Iterator it = es.iterator();
		Map.Entry entry = null;
		String k = null;
		String v = null;
		while (it.hasNext()) {
			entry = (Map.Entry) it.next();
			k = (String) entry.getKey();
			v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		System.out.println("--------------------------md5 sb string:"
				+ sb.toString());

		MessageDigest crypt = MessageDigest.getInstance("MD5");
		crypt.reset();
		crypt.update(sb.toString().getBytes("UTF-8"));
		String sign = byteToHex(crypt.digest()).toUpperCase();

		return sign;

	}
	
	/**
	 * 对map的value值进行字典排序
	 */
	public static String zdSort(Map<String, String> mapParams){
		if(mapParams==null){
			return "";
		}
		mapParams = MapUtil.sortByValue(mapParams);
		StringBuffer sb = new StringBuffer();
		Set es = mapParams.entrySet();
		Iterator it = es.iterator();
		Map.Entry entry = null;
		String k = null;
		String v = null;
		while (it.hasNext()) {
			entry = (Map.Entry) it.next();
			k = (String) entry.getKey();
			v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(v);
			}
		}
		String returnStr = sb.toString();
		System.out.println("--------------------------md5 sb string:"
				+ returnStr);
		
		return returnStr;
	}

	/**
	 * 字符转换
	 * 
	 * @param hash
	 * @return
	 */
	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 转换成请求的XML数据格式
	 * 
	 * @param mapParams
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String parseMapToXML(SortedMap<String, String> mapParams) {
		StringBuffer sb = new StringBuffer();

		sb.append("<xml>");
		if (mapParams != null) {
			Set es = mapParams.entrySet();
			Iterator it = es.iterator();
			Map.Entry entry = null;
			String k = null;
			String v = null;
			String s = null;
			while (it.hasNext()) {
				entry = (Map.Entry) it.next();
				k = (String) entry.getKey();
				v = (String) entry.getValue();
				if (null != v && !"".equals(v) && !"key".equals(k)) {
					s = (String) mapParams.get(k);
					s = (null == s) ? "" : s;
					sb.append("<" + k + ">" + s + "</" + k + ">\n");
				}
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 解析XML字符串为Map对象
	 * 
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> parseXMLToMap(String xml)
			throws DocumentException {

		Map<String, String> map = new HashMap<String, String>();

		if (!StringUtils.isEmpty(xml)) {

			Document doc = DocumentHelper.parseText(xml);
			Element rootElm = doc.getRootElement();
			List nodes = rootElm.elements();

			for (Iterator it = nodes.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				if (!map.containsKey(elm.getName())) {
					map.put(elm.getName(), elm.getText());
				}
			}
		}

		return map;
	}
	
	/**
	 * 
	 * lhyan3
	 * 2015年3月14日下午3:35:34
	 * TODO 把map对象转化成bean
	 * @param type bean类型
	 * @param map
	 * @return
	 * @throws IntrospectionException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws ParseException 
	 */
	public static Object converMap(Class type,Map map) throws IntrospectionException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object object = type.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for(int i=0;i<propertyDescriptors.length;i++){
			PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
			String propertyName = propertyDescriptor.getName();
			Class class1 = propertyDescriptor.getPropertyType();
			if(map.containsKey(propertyName)){
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				if(class1.equals(Integer.class) && value!=null && !"".equals(value)){
					value = Integer.parseInt(value.toString());
				}else if(class1.equals(Float.class) && value!=null && !"".equals(value)){
					value = Float.parseFloat(value.toString());
				}else if(class1.equals(Double.class) && value!=null && !"".equals(value)){
					value = Double.parseDouble(value.toString());
				}else if(class1.equals(Boolean.class) && value!=null && !"".equals(value)){
					value = Boolean.parseBoolean(value.toString());
				}else if(class1.equals(Date.class) && value!=null && !"".equals(value)){
					value = DateUtil.format(value, "yyyy-MM-dd HH:mm:ss");
				}
				args[0] = value;
				propertyDescriptor.getWriteMethod().invoke(object, args);
			}
		}
		return object;
	}
	
	
	public static void main(String[] args) throws Exception {
		
//		WXPayUtil wxPayUtil = new WXPayUtil();
//		
//		String appid = "wx001ca4cc13bc78b9";
//		String mch_id = "1231422702";
//		
//		//String attach = "支付测试";
//		String body = "JSAPI支付测试-奶粉";
//		
//		String nonce_str = UUIDUtil.getUUID();
//		String notify_url = "http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php";
//		String openid = "owFihjgfozRFULNwxd-TDCvWwyQ0";
//		String out_trade_no = UUIDUtil.getUUID();
//		String spbill_create_ip = "123.123.111.211";
//		String total_fee = "1";
//		//String trade_type = "JSAPI";
//		String trade_type = "NATIVE";
//		String product_id = UUIDUtil.getUUID();
//
//		String key = "F269E014FED94B039A03C1DE6836165D";
//
//		SortedMap<String, String> requestParams = new TreeMap<String, String>();
//
//		requestParams.put("nonce_str", nonce_str);
//		requestParams.put("notify_url", notify_url);
//		requestParams.put("openid", openid);
//		requestParams.put("out_trade_no", out_trade_no);
//		requestParams.put("spbill_create_ip", spbill_create_ip);
//		requestParams.put("total_fee", total_fee);
//		requestParams.put("trade_type", trade_type);
//		requestParams.put("product_id", product_id); 
//
//		requestParams.put("appid", appid);
//		//requestParams.put("attach", attach);
//		requestParams.put("body", body);
//		requestParams.put("mch_id", mch_id); 
//
//		
//		String sign = wxPayUtil.createSign(requestParams, key);
//
//		System.out.println("-------------sign:" + sign);
//		requestParams.put("sign", sign);
//
//		String requestData = wxPayUtil.parseMapToXML(requestParams);
//		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//		SSLNetProvider provider = new SSLNetProvider();
//
//		String result = provider.doPost(url, requestData);
//
//		
//		Map<String, String> resultMap = wxPayUtil.parseXMLToMap(result);
//		
//		if(resultMap.containsKey("prepay_id")){
//			System.out.println("-------------prepay_id:" + resultMap.get("prepay_id"));
//		}
//		
//		if(resultMap.containsKey("code_url")){
//			System.out.println("-------------code_url:" + resultMap.get("code_url"));
//		}		
//		
//		String time_stamp = Long.toString(System.currentTimeMillis() / 1000);
//		
//		SortedMap<String, String> signParams = new TreeMap<String, String>();
//
//		signParams.put("appid", appid);
//		signParams.put("mch_id", mch_id); 
//		signParams.put("nonce_str", nonce_str);
//		signParams.put("time_stamp", time_stamp);
//		signParams.put("product_id", product_id); 		
//
//		
//		String sign2 = wxPayUtil.createSign(signParams, key);
//		
//		String url2 = "weixin://wxpay/bizpayurl?appid="+appid+"&mch_id="+mch_id+"&nonce_str="+nonce_str+"&product_id="+product_id+"&time_stamp="+time_stamp+"&sign="+sign2;
//		System.out.println("-------------url2:" + url2);
//		PayNotice message = new PayNotice();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("trade_type", "fewaf");
//		map.put("time_end", "20141030133525");
//		map.put("coupon_count", "1");
//		message = (PayNotice) converMap(PayNotice.class, map);
//		System.out.println(message.getCoupon_count());
		System.out.println(Double.parseDouble("12.20"));
		
	}
}
