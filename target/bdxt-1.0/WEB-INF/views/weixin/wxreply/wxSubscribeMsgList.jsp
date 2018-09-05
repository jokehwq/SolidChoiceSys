<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公众号自动回复管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/reply/wxSubscribeMsg/">公众号自动回复列表</a></li>
		<shiro:hasPermission name="reply:wxSubscribeMsg:edit"><li><a href="${ctx}/reply/wxSubscribeMsg/form">公众号自动回复添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxSubscribeMsg" action="${ctx}/reply/wxSubscribeMsg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<li><label>公众号id：</label>
				<form:input path="accountid" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th>公众号id</th>
		    <th>关注回复内容</th>
		
				<shiro:hasPermission name="reply:wxSubscribeMsg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxSubscribeMsg">
			<tr>
			<td><a href="${ctx}/reply/wxSubscribeMsg/form?id=${wxSubscribeMsg.id}">
				${wxSubscribeMsg.accountid}
			</a></td>
			<td><a href="${ctx}/reply/wxSubscribeMsg/form?id=${wxSubscribeMsg.id}">
				${wxSubscribeMsg.content}
			</a></td>	
				<shiro:hasPermission name="reply:wxSubscribeMsg:edit"><td>
    				<a href="${ctx}/reply/wxSubscribeMsg/form?id=${wxSubscribeMsg.id}">修改</a>
					<a href="${ctx}/reply/wxSubscribeMsg/delete?id=${wxSubscribeMsg.id}" onclick="return confirmx('确认要删除该公众号自动回复吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>