<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支付通知管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/weixin/wxPayNotice/">支付通知列表</a></li>
		<li class="active"><a href="${ctx}/weixin/wxPayNotice/form?id=${wxPayNotice.id}">支付通知<shiro:hasPermission name="weixin:wxPayNotice:edit">${not empty wxPayNotice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:wxPayNotice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxPayNotice" action="${ctx}/weixin/wxPayNotice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">微信分配的公众账号ID：</label>
			<div class="controls">
				<form:input path="appid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信支付分配的商户号：</label>
			<div class="controls">
				<form:input path="mch_id" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务结果SUCCESS/FAIL：</label>
			<div class="controls">
				<form:input path="result_code" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">错误代码：</label>
			<div class="controls">
				<form:input path="err_code" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户标识：</label>
			<div class="controls">
				<form:input path="openid" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否关注公众账号：</label>
			<div class="controls">
				<form:input path="is_subscribe" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易类型：</label>
			<div class="controls">
				<form:input path="trade_type" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款银行：</label>
			<div class="controls">
				<form:input path="bank_type" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总金额：</label>
			<div class="controls">
				<form:input path="total_fee" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货币种类：</label>
			<div class="controls">
				<form:input path="fee_type" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现金支付金额：</label>
			<div class="controls">
				<form:input path="cash_fee" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现金支付货币类型：</label>
			<div class="controls">
				<form:input path="cash_fee_type" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代金券或立减优惠金额：</label>
			<div class="controls">
				<form:input path="coupon_fee" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代金券或立减优惠使用数量：</label>
			<div class="controls">
				<form:input path="coupon_count" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代金券或立减优惠批次ID：</label>
			<div class="controls">
				<form:input path="coupon_batch_id_index" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代金券或立减优惠ID：</label>
			<div class="controls">
				<form:input path="coupon_id_index" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单个代金券或立减优惠支付金额：</label>
			<div class="controls">
				<form:input path="coupon_fee_index" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信支付订单号：</label>
			<div class="controls">
				<form:input path="transaction_id" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商户订单号：</label>
			<div class="controls">
				<form:input path="out_trade_no" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商家数据包：</label>
			<div class="controls">
				<form:input path="attach" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付完成时间：</label>
			<div class="controls">
				<form:input path="time_end" htmlEscape="false" maxlength="14" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">记录时间：</label>
			<div class="controls">
				<input name="recodeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${wxPayNotice.recodeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付路径方式 1正常支付 2扫码支付：</label>
			<div class="controls">
				<form:input path="payLocation" htmlEscape="false" maxlength="3" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信支付分配的终端设备号：</label>
			<div class="controls">
				<form:input path="device_info" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="weixin:wxPayNotice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/weixin/wxPayNotice'"/>
		</div>
	</form:form>
</body>
</html>