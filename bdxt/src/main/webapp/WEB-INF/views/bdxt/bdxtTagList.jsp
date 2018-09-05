<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标签信息管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtTag/">标签信息列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtTag:edit"><li><a href="${ctx}/bdxt/bdxtTag/form">标签信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtTag" action="${ctx}/bdxt/bdxtTag/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标签名称：</label>
				<form:input path="tagName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>

			<li><label>状态 1-启用 2-停用：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标签名称</th>
				<th>状态 1-启用 2-停用</th>
				<th>创建时间</th>
				<shiro:hasPermission name="bdxt:bdxtTag:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtTag">
			<tr>
				<td><a href="${ctx}/bdxt/bdxtTag/form?id=${bdxtTag.id}">
					${bdxtTag.tagName}
				</a></td>
				<td>
					${fns:getDictLabel(bdxtTag.status, '', '')}
				</td>
				<td>
					<fmt:formatDate value="${bdxtTag.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bdxt:bdxtTag:edit"><td>
    				<a href="${ctx}/bdxt/bdxtTag/form?id=${bdxtTag.id}">修改</a>
					<a href="${ctx}/bdxt/bdxtTag/delete?id=${bdxtTag.id}" onclick="return confirmx('确认要删除该标签信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>