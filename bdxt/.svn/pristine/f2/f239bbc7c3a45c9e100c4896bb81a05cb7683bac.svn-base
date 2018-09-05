<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户标签信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtTypeTag/">用户标签信息列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtTypeTag:edit"><li><a href="${ctx}/bdxt/bdxtTypeTag/form">用户标签信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtTypeTag" action="${ctx}/bdxt/bdxtTypeTag/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标签名称：</label>
				<form:input path="tagName" htmlEscape="false" maxlength="50" class="input-medium"/>
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
				<th>标签名称</th>
				<th>标签类型</th>
				<th>排序</th>
				<th>是否推荐</th>
				<shiro:hasPermission name="bdxt:bdxtTypeTag:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtTypeTag" varStatus="status">
			<tr>
				<td>
						${status.count}
				</td>
				<td>
						${bdxtTypeTag.tagName}
				</td>
				<td>
						${bdxtTypeTag.dictName}
				</td>
				<td>
						${bdxtTypeTag.sort}
				</td>
				<td>
						${bdxtTypeTag.isRecommended ==1?'是':'否'}
				</td>
				<shiro:hasPermission name="bdxt:bdxtTypeTag:edit"><td>
    				<a href="${ctx}/bdxt/bdxtTypeTag/form?id=${bdxtTypeTag.id}">修改</a>
					<a href="${ctx}/bdxt/bdxtTypeTag/delete?id=${bdxtTypeTag.id}" onclick="return confirmx('确认要删除该用户标签信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>