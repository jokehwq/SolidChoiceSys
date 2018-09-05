<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文本回复管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/wxMsg/">文本回复列表</a></li>
		<shiro:hasPermission name="weixin:wxMsg:edit"><li><a href="${ctx}/weixin/wxMsg/form?accountid=${accountid}">文本回复添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxMsg" action="${ctx}/weixin/wxMsg/?accountid=${accountid}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>回复内容：</label>
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
				<th>文本回复内容</th>
				<th>创建时间</th>
				<th>创建者</th>
				<th>修改时间</th>
				<shiro:hasPermission name="weixin:wxMsg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxMsg">
			<tr>
				<td><a href="${ctx}/weixin/wxMsg/form?id=${wxMsg.id}">
					<c:choose>
		         <c:when test="${fn:length(wxMsg.content)<12}">${wxMsg.content}</c:when>
		         <c:otherwise>${fn:substring(wxMsg.content,0,12)}...</c:otherwise>
		        </c:choose>
					</a>
					
				</td>
				<td>
					<fmt:formatDate value="${wxMsg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wxMsg.createUser}
				</td>
				<td>
					<fmt:formatDate value="${wxMsg.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="weixin:wxMsg:edit"><td>
    				<a href="${ctx}/weixin/wxMsg/form?id=${wxMsg.id}&&accountid=${accountid}">修改</a>
					<a href="${ctx}/weixin/wxMsg/delete?id=${wxMsg.id}&&accountid=${accountid}" onclick="return confirmx('确认要删除该文本回复吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>