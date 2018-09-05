<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全球地区管理</title>
	<meta name="decorator" content="default"/>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtDistrict/">全球地区列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtDistrict:edit"><li><a href="${ctx}/bdxt/bdxtDistrict/form">全球地区添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtDistrict" action="${ctx}/bdxt/bdxtDistrict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>地区名称：</label>
				<form:input path="districtname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>区域代码：</label>
				<form:input path="districtcode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>地区名称</th>
				<th>层级</th>
				<th>区域代码</th>
				<th>父级id</th>
				<shiro:hasPermission name="bdxt:bdxtDistrict:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtDistrict">
			<tr>
				<td><a href="${ctx}/bdxt/bdxtDistrict/form?id=${bdxtDistrict.id}">
					${bdxtDistrict.districtname}
				</a></td>
				<td>
					${bdxtDistrict.level}
				</td>
				<td>
					${bdxtDistrict.districtcode}
				</td>
				<td>
					${bdxtDistrict.parent.id}
				</td>
				<shiro:hasPermission name="bdxt:bdxtDistrict:edit"><td>
    				<a href="${ctx}/bdxt/bdxtDistrict/form?id=${bdxtDistrict.id}">修改</a>
					<a href="${ctx}/bdxt/bdxtDistrict/delete?id=${bdxtDistrict.id}" onclick="return confirmx('确认要删除该全球地区吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>