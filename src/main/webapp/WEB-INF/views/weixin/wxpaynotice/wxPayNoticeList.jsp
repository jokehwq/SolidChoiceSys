<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>支付通知管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/wxPayNotice/">支付通知列表</a></li>
		<shiro:hasPermission name="weixin:wxPayNotice:edit"><li><a href="${ctx}/weixin/wxPayNotice/form">支付通知添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxPayNotice" action="${ctx}/weixin/wxPayNotice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信支付分配的商户号</th>
				<th>交易类型</th>
				<th>付款银行</th>
				<th>总金额</th>
				<th>货币种类</th>
				<th>支付路径方式 1正常支付 2扫码支付</th>
				<shiro:hasPermission name="weixin:wxPayNotice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxPayNotice">
			<tr>
				<td><a href="${ctx}/weixin/wxPayNotice/form?id=${wxPayNotice.id}">
					${wxPayNotice.mch_id}
				</a></td>
				<td>
					${wxPayNotice.trade_type}
				</td>
				<td>
					${wxPayNotice.bank_type}
				</td>
				<td>
					${wxPayNotice.total_fee}
				</td>
				<td>
					${wxPayNotice.fee_type}
				</td>
				<td>
					${wxPayNotice.payLocation}
				</td>
				<shiro:hasPermission name="weixin:wxPayNotice:edit"><td>
    				<a href="${ctx}/weixin/wxPayNotice/form?id=${wxPayNotice.id}">修改</a>
					<a href="${ctx}/weixin/wxPayNotice/delete?id=${wxPayNotice.id}" onclick="return confirmx('确认要删除该支付通知吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>