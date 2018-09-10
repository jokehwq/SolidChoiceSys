<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典信息管理</title>
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
		<li class="active"><a href="${ctx}/sys/banner/">字典信息列表</a></li>
		<shiro:hasPermission name="sys:banner:edit"><li><a href="${ctx}/sys/banner/form">字典信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="banner" action="${ctx}/sys/banner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="text" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>描述</th>
				<th>图片路径</th>
				<th>跳转路径</th>
				<th>状态</th>
				<shiro:hasPermission name="sys:banner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="banner">
			<tr>
				<td>
						${banner.text}
				</td>
				<td>
						${banner.imgUrl}
				</td>
				<td>
						${banner.jumpUrl}
				</td>
				<td>
						${banner.type ==1?'启用':'停用'}
				</td>
				<shiro:hasPermission name="sys:banner:edit"><td>
    				<a href="${ctx}/sys/banner/form?id=${banner.id}">修改</a>
					<a href="${ctx}/sys/banner/delete?id=${banner.id}" onclick="return confirmx('确认要删除该字典信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>