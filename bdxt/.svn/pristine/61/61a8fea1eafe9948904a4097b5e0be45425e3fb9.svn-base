<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关键字管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/wxkeyword/wxKeyword/?accountid=${accountid}">关键字列表</a></li>
		<shiro:hasPermission name="wxkeyword:wxKeyword:edit"><li><a href="${ctx}/wxkeyword/wxKeyword/form?accountid=${accountid}">关键字添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxKeyword" action="${ctx}/wxkeyword/wxKeyword/?accountid=${accountid}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>关键字：</label> 
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>关键字</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<shiro:hasPermission name="wxkeyword:wxKeyword:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxKeyword">
			<tr>
				<td><a href="${ctx}/wxkeyword/wxKeyword/form?id=${wxKeyword.id} && accountid=${accountid}">
					${wxKeyword.keyword}
				</a></td>
				<td>
					${wxKeyword.createUser}
				</td>
				<td>
					<fmt:formatDate value="${wxKeyword.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${wxKeyword.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="wxkeyword:wxKeyword:edit"><td>
    				<a href="${ctx}/wxkeyword/wxKeyword/form?id=${wxKeyword.id}&&accountid=${accountid}">修改</a>
					<a href="${ctx}/wxkeyword/wxKeyword/delete?id=${wxKeyword.id}&&accountid=${accountid}" onclick="return confirmx('确认要删除该关键字吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>