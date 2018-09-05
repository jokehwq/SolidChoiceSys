<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存成功管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/wx_second_subscribe/wxSecondSubscribeMsg/">列表</a></li>
		<shiro:hasPermission name="wx_second_subscribe:wxSecondSubscribeMsg:edit"><li><a href="${ctx}/wx_second_subscribe/wxSecondSubscribeMsg/form">添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxSecondSubscribeMsg" action="${ctx}/wx_second_subscribe/wxSecondSubscribeMsg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>内容</th>
				<th>公众号ID</th>
				<shiro:hasPermission name="wx_second_subscribe:wxSecondSubscribeMsg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxSecondSubscribeMsg">
			<tr>
				<td><a href="${ctx}/wx_second_subscribe/wxSecondSubscribeMsg/form?id=${wxSecondSubscribeMsg.id}">
					${wxSecondSubscribeMsg.content}
				</a></td>
				<td>
					${wxSecondSubscribeMsg.accountid}
				</td>
				<shiro:hasPermission name="wx_second_subscribe:wxSecondSubscribeMsg:edit"><td>
    				<a href="${ctx}/wx_second_subscribe/wxSecondSubscribeMsg/form?id=${wxSecondSubscribeMsg.id}">修改</a>
					<a href="${ctx}/wx_second_subscribe/wxSecondSubscribeMsg/delete?id=${wxSecondSubscribeMsg.id}" onclick="return confirmx('确认要删除该保存成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>