
//初始化微信支付的参数：
var options = {
	openid:ogData.payOpenId,//支付账号的openid 必须
	device_info:"",//设备号 
	body:"欢乐购商城订单",//商品描述   必须
	detail:"",//商品详情
	attach:"",//附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	out_trade_no:ogData.orderId,//商户订单号  必填
	total_fee:1,//总金额 以分这单位 必填
	goods_tag:"",//商品标记
	product_id:"",//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	trade_type:"JSAPI",//交易类型 //必填
	need_qr:false,//是否需要二维码 如果支付页面需要显示扫描支付的二维码图片，则改为true
	qrCodeName:"",//显示二码的名称维
	qrCodeNameDIV:"",//显示二维码的名称的容器
	qrCodeImage:"",//显示二维码图片的容器
	after:"payResult",//支付成功之后的回调函数
	payLocation:ogData.payLocation,//支付路径方式 1服务号  2扫码支付
	orderId:ogData.orderId
};

//微信安全支付
$("#wxpayBtn").click(function() {
	
    $.pay_init(options);//调用封装好的微信支付接口生成预订单
	
	$.pay(); //然后调用支付方法
});

