package com.foreveross.webbase.weixin.api;

import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.common.utils.JsonMessage;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.domain.WeixinPayRequestDomain;
import com.foreveross.webbase.weixin.sdk.util.SSLNetProvider;
import com.foreveross.webbase.weixin.sdk.util.UUIDUtil;
import com.foreveross.webbase.weixin.sdk.util.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@Controller
public class WxPayController extends BaseController {
	
	private Map<String, Object> cache = new HashMap<>();
	
	private static Logger logger = LoggerFactory.getLogger(WxPayController.class);


	/**
	 * 调用微信统一支付接口
	 * @return
	 */
	private Map<String, String> unifiedorder(WeixinPayRequestDomain domain) {
		Map<String, String> resultMap = null;
		try {
//			//通知地址(微信付款成功，微信返回通知地址，就是下面的wxNotify)
//			String notify_url = "http://jsugarm.51vip.biz/hrweixin/WXPay/notify";			
//			String spbill_create_ip = "10.108.1.197";//request.getRemoteAddr();	
//			domain.setSpbill_create_ip(spbill_create_ip);
//			domain.setNotify_url(notify_url);
			//组装微信统一下单参数
			SortedMap<String, String> requestParams = assemblyRequestParam(domain);
			WXPayUtil wxPayUtil = new WXPayUtil();
			//创建MD5签名
			String sign = wxPayUtil.createSign(requestParams, domain.getKey());

			logger.info("-------------wxpay unifiedorder sign:" + sign);
			requestParams.put("sign", sign);

			String requestData = wxPayUtil.parseMapToXML(requestParams);
			//统一下单接口
			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//PropsUtil.getProperty(PropsParam.WEIXIN_PAY_UNIFIEDORDER);
			//调用微信统一支付接口
			logger.info("-------------wxpay unifiedorder url:" + url);
			SSLNetProvider provider = new SSLNetProvider();	
			String resultData = provider.doPost(url, requestData);	
			//转换结果为map对象返回
			resultMap = wxPayUtil.parseXMLToMap(resultData);
		} catch (Exception e) {
			logger.error("Exception", e);			
		}
		return resultMap;
	}

	@RequestMapping("/WXPay/preorder/test")
	public String testPayPage(HttpServletResponse response) throws IOException{
		return "weixin/wechatPayTest";
	} 
	
	private JsonMessage assemblyResult(Map<String, String> resultMap,WeixinPayRequestDomain domain) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		WXPayUtil wxPayUtil = new WXPayUtil();
		JsonMessage result = new JsonMessage();
		JSONObject json = new JSONObject();		
		if (resultMap.containsKey("return_code")
				&& "FAIL".equals(resultMap.get("return_code"))) {
			logger.info("-------------wxpay unifiedorder return_code:"
					+ resultMap.get("return_code") + ";return_msg:"
					+ resultMap.get("return_msg"));
			result.setCode(-1);
			result.setMsg(resultMap.get("return_msg"));
		} else if (resultMap.containsKey("result_code")
				&& "FAIL".equals(resultMap.get("result_code"))) {
			logger.info("-------------wxpay unifiedorder result_code:"
					+ resultMap.get("result_code") + ";err_code:"
					+ resultMap.get("err_code") + ";err_code_des:"
					+ resultMap.get("err_code_des"));
			if(resultMap.containsKey("err_code") & "ORDERPAID".equals(resultMap.get("err_code"))){
				result.setCode(301);
				result.setMsg("订单已支付");
			}else{
				result.setCode(-2);
				result.setMsg(resultMap.get("err_code_des"));
			}
		} else {
			//成功
			String timestamp = Long
					.toString(System.currentTimeMillis() / 1000);
			String nonce_str2 = UUIDUtil.getUUID();
			String packageParams = "prepay_id="
					+ (resultMap.containsKey("prepay_id") ? resultMap
							.get("prepay_id") : "");
			String signType = "MD5";
			//String codeURL = resultMap.containsKey("code_url") ? resultMap.get("code_url") : "";

			SortedMap<String, String> signParams = new TreeMap<String, String>();

			signParams.put("appId", domain.getAppid());
			signParams.put("timeStamp", timestamp);
			signParams.put("nonceStr", nonce_str2);
			signParams.put("package", packageParams);
			signParams.put("signType", signType);
			
			//创建MD5签名
			String paySign = wxPayUtil.createSign(signParams, domain.getKey());

			//logger.info("-------------wxpay jsp api pay sign:" + paySign);

			//构造JS API H5需要的参数的json格式数据
			json.put("appId", domain.getAppid());
			json.put("timeStamp", timestamp);

			json.put("nonceStr", nonce_str2);
			json.put("package", packageParams);
			json.put("signType", signType);
			json.put("paySign", paySign);
			//json.put("codeURL", codeURL);
			result.setCode(200);
			result.setMsg("OK");
			result.setData(json);
		}	
		return result;
	}
	
	private SortedMap<String, String> assemblyRequestParam(WeixinPayRequestDomain domain){
		SortedMap<String, String> requestParams = new TreeMap<String, String>();
		requestParams.put("appid", domain.getAppid());
		requestParams.put("mch_id", domain.getMch_id());
		requestParams.put("device_info", domain.getDevice_info()); 
		requestParams.put("nonce_str", domain.getNonce_str());
		requestParams.put("body", domain.getBody());
		requestParams.put("detail", domain.getDetail());
		requestParams.put("attach", domain.getAttach());
		requestParams.put("out_trade_no", domain.getOut_trade_no());
		requestParams.put("fee_type", domain.getFee_type());
		requestParams.put("total_fee", domain.getTotal_fee());
		requestParams.put("spbill_create_ip", domain.getSpbill_create_ip());
		requestParams.put("time_start", domain.getTime_start()); 
		requestParams.put("time_expire", domain.getTime_expire()); 
		requestParams.put("goods_tag", domain.getGoods_tag());			
		requestParams.put("notify_url", domain.getNotify_url());
		requestParams.put("trade_type", domain.getTrade_type());			
		requestParams.put("product_id", domain.getProduct_id()); 
		requestParams.put("openid", domain.getOpenid());
		return requestParams;
	}
}
