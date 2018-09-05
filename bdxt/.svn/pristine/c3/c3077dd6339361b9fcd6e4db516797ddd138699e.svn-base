;var settings;
var WXPayPreOrder = app.cfg.base + "/WXPay/preorder.do";
var WXPayRecord = app.cfg.base + "/WXPay/wxpay_record.do";
var preOrderJsonResult;
var preQRCodeJsonResult;
var message = {
		openid:"",//支付账号的openid 必须
		device_info:"",//设备号 
		body:"",//商品描述 Ipad mini  16G  白色 必须
		detail:"",//商品详情
		attach:"",//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
		out_trade_no:"",//商户订单号  必填
		total_fee:0,//总金额 以分这单位 必填
		goods_tag:"",//商品标记
		product_id:"",//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
		trade_type:"JSAPI",//交易类型 //必填
		need_qr:false,//是否需要二维码 如果支付页面需要显示扫描支付的二维码图片，则改为true
		qrCodeName:"",//显示二维码的名称
		qrCodeNameDIV:"",//显示二维码的名称的容器
		qrCodeImage:"",//显示二维码图片的容器
		after:null,//支付之后的回调函数 返回参数成功success 失败error
		payLocation:"",//支付路径方式 1服务号  2扫码支付
		orderId:""
};

$.pay_init = function(options){
	settings = $.extend({},message,options);
	preOrder();	
	if(settings.need_qr){
		preQRCode();
	}
};

//预下订单
function preOrder(){
	settings.trade_type = "JSAPI";
	$.ajax({
		url : WXPayPreOrder,
		data : settings,
		type : "post",
		async : false, //异步
		cache : false,
		beforeSend : function(xhr) {
		},
		success : function(data) {
			if(data.retcode=="0"){
				preOrderJsonResult = JSON.parse(data.json);	
			}else{
				
			}
		}
		,error : function(xhr,status,error){
		}
		,complete:function(xhr){
		}
	});
}

//生成本地Native二维码
function preQRCode(){
	settings.trade_type = "NATIVE";
	settings.total_fee = "1";
	settings.body = "欢乐购订单";
	$.ajax({
		url : WXPayPreOrder,
		data : settings,
		type : "post",
		async : false, //同步
		cache : false,
		beforeSend : function(xhr) {
			
		},
		success : function(data) {			
			if(data.retcode=="0"){
				preQRCodeJsonResult = JSON.parse(data.json);				
			}else{
			}
		}
		,error : function(xhr,status,error){
		}
		,complete:function(xhr){
		}
	});
	var url = preQRCodeJsonResult.codeURL;
	//参数1表示图像大小，取值范围1-10；参数2表示质量，取值范围'L','M','Q','H'
	var qr = qrcode(10, 'M');
	qr.addData(url);
	qr.make();
	if(settings.qrCodeName){
		$("#"+settings.qrCodeNameDiv).append(settings.qrCodeName);
		$("#"+settings.qrCodeImageDiv).append(qr.createImgTag());	
	}
}

//开始支付
$.pay = function(){
	if (typeof WeixinJSBridge == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
					false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	} else {
		onBridgeReady();
	}
};

//支付订单
function onBridgeReady() {
	WeixinJSBridge.invoke('getBrandWCPayRequest', {
		"appId" : preOrderJsonResult.appId,
		"timeStamp" : preOrderJsonResult.timeStamp,
		"nonceStr" : preOrderJsonResult.nonceStr,
		"package" : preOrderJsonResult.packageParams,
		"signType" : preOrderJsonResult.signType,
		"paySign" : preOrderJsonResult.paySign
	}, function(res) {
		var result = "error";
		if (res.err_msg == "get_brand_wcpay_request:ok") {
			//支付成功
			result = "success";
			wxpay_record("1");
		} else if(res.err_msg=="get_brand_wcpay_request:cancel"){
			//支付取消
			result = "cancle";
			wxpay_record("0");
		}else{
			//支付失败
			result = "error";
			wxpay_record("-1");
		}
		if(settings.after){
			var fun = settings.after+"(result)";
			eval(fun);
		}
	});
}

function wxpay_record(flag){
	settings.flag = flag;
	$.ajax({
		url : WXPayRecord,
		data : settings,
		type : "post",
		cache : false,
		success : function(data) {
		}
	});
}
