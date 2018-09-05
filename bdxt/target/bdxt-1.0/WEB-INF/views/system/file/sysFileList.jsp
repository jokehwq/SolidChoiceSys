<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>附件管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/system/file/sysFile/">附件列表</a></li>
		<shiro:hasPermission name="system:file:sysFile:edit"><li><a href="${ctx}/system/file/sysFile/form">附件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysFile" action="${ctx}/system/file/sysFile/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>扩展名：</label>
				<form:input path="ext" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>创建人：</label>
				<form:input path="createby" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>模块：</label>
				<form:input path="module" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>子模块：</label>
				<form:input path="submodule" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文件名</th>
				<th>扩展名</th>
				<th>文件大小</th>
				<th>创建时间</th>
				<th>创建人</th>
				<th>模块</th>
				<th>子模块</th>
				<shiro:hasPermission name="system:file:sysFile:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysFile">
			<tr>
				<td><a href="${ctx}/system/file/sysFile/form?id=${sysFile.id}">
					${sysFile.name}
				</a></td>
				<td>
					${sysFile.ext}
				</td>
				<td>
					${sysFile.sizestr}
				</td>
				<td>
					<fmt:formatDate value="${sysFile.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysFile.createby}
				</td>
				<td>
					${sysFile.module}
				</td>
				<td>
					${sysFile.submodule}
				</td>
				<shiro:hasPermission name="system:file:sysFile:edit"><td>
					<a href="${ctx}/system/file/sysFile/download?id=${sysFile.id}">下载</a>
    				<a href="${ctx}/system/file/sysFile/form?id=${sysFile.id}">修改</a>
					<a href="${ctx}/system/file/sysFile/delete?id=${sysFile.id}" onclick="return confirmx('确认要删除该附件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>