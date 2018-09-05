<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动订单信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtActivityOrder/">活动订单信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtActivityOrder" action="${ctx}/bdxt/bdxtActivityOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="orderNumber" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入订单编号"/>
			</li>
			<li><label>活动主题：</label>
				<form:input path="activityName" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入活动主题"/>
			</li>
			<li><label>订单状态</label>
				<form:select path="orderStatus" class="input-medium">
					<form:option value="">选择订单状态</form:option>
					<form:option value="1">未支付</form:option>
					<form:option value="2">待支付</form:option>
					<form:option value="3">已支付</form:option>
					<form:option value="4">已取消</form:option>
				</form:select>
			</li>
			<li><label>用户名：</label>
				<form:input path="realName" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入用户名"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>订单编号</th>
				<th>活动主题</th>
				<th>用户名</th>
				<th>联系方式</th>
				<th>订单金额</th>
				<th>订单状态</th>
				<th>活动地点</th>
				<th>创建时间</th>
				<shiro:hasPermission name="bdxt:bdxtActivityOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtActivityOrder" varStatus="status">
			<tr>
				<td>
					${status.count}
				</td>
				<td>${bdxtActivityOrder.orderNumber}</td>
				<td>${bdxtActivityOrder.activityName}</td>
				<td>${bdxtActivityOrder.realName}</td>
				<td>${bdxtActivityOrder.phone}</td>
				<td>${bdxtActivityOrder.orderAmount}</td>
				<td>
					<c:choose>
						<c:when test="${bdxtActivityOrder.orderStatus ==1}">
							未支付
						</c:when>
						<c:when test="${bdxtActivityOrder.orderStatus ==2}">
							待支付
						</c:when>
						<c:when test="${bdxtActivityOrder.orderStatus ==3}">
							已支付
						</c:when>
						<c:otherwise>
							已取消
						</c:otherwise>
					</c:choose>
				</td>
				<td>${bdxtActivityOrder.address}</td>
				<td>
					<fmt:formatDate value="${bdxtActivityOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bdxt:bdxtActivityOrder:edit"><td>
					<c:choose>
						<c:when test="${bdxtActivityOrder.orderStatus ==1}">
							<a href="${ctx}/bdxt/bdxtUser/detail?id=${bdxtActivityOrder.bdxtUserId}">查看</a> |
							<a href="${ctx}/bdxt/bdxtActivityOrder/?id=${bdxtActivityOrder.id}">取消订单</a>
						</c:when>
						<c:when test="${bdxtActivityOrder.orderStatus ==2}">
							<a href="${ctx}/bdxt/bdxtActivityOrder/form?id=${bdxtActivityOrder.id}">查看</a> |
							<a href="${ctx}/bdxt/bdxtActivityOrder/?id=${bdxtActivityOrder.id}">支付</a> |
							<a href="${ctx}/bdxt/bdxtActivityOrder/delete?id=${bdxtActivityOrder.id}" onclick="return confirmx('确认要取消该活动订单信息吗？', this.href)">取消</a>
						</c:when>
						<c:when test="${bdxtActivityOrder.orderStatus ==3}">
							<a href="${ctx}/bdxt/bdxtActivityOrder/form?id=${bdxtActivityOrder.id}">查看</a>
						</c:when>
					</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>