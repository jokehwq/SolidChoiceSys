<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" type="text/css" href="base.css" media="all" />
    <style type="text/css">
body { 
  background:url(black-bg.png) repeat 0 0;
} 

.thumb span {
	width:30px;
	float:left;
}

.thumb span img:hover {
	max-width:300px;
}
  </style>

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
		<li class="active"><a href="${ctx}/weixin/wxUser?accountId=${wxUser.accountId}">用户管理列表</a></li>
		<shiro:hasPermission name="weixin:wxUserGroup:edit"><li><a href="${ctx}/weixin/wxUserGroup/form?accountid=${accountId}">用户分组添加</a></li></shiro:hasPermission> 
	</ul>
	<form:form id="searchForm" modelAttribute="wxUser" action="${ctx}/weixin/wxUser?accountId=${wxUser.accountId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>城市：</label>
				<form:input path="city" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>是否关注：</label>
				<form:select path="subscribe" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('user_subscribe')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('user_sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>头像</th>
				<th>昵称</th>
				<th>openid</th>
				<th>性别</th>
				<th>城市</th>
				<th>是否关注</th>
				<th>创建时间</th>
				<%-- <shiro:hasPermission name="weixin:wxUser:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxUser">
			<tr>
				<td class="thumb">			
			        <span><img src="${wxUser.headimgurl}" alt=""></span>
			    </td>
				<td>
					<a href="${ctx}/weixin/wxUser/selectUser?id=${wxUser.id}">${wxUser.nickname}</a>
				</td>
				<td>
					${wxUser.openid}
				</td>
				<td>
					<c:if test="${wxUser.sex==3}">未知</c:if>
					<c:if test="${wxUser.sex==1}">男</c:if>
					<c:if test="${wxUser.sex==2}">女</c:if>
					<c:if test="${fns:getDictLabel(wxUser.sex, '', '')}"></c:if>
				</td>
				<td>
					${wxUser.city}
				</td>
				<td>
					<c:if test="${wxUser.subscribe==1}">已关注</c:if>
					<c:if test="${wxUser.subscribe==0}">未关注</c:if>
					<c:if test="${fns:getDictLabel(wxUser.subscribe, '', '')}"></c:if>
				</td>
				<td>
					<fmt:formatDate value="${wxUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
<%-- 				<shiro:hasPermission name="reply:wxSubscribeMsg:edit"><td>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
		<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/weixin/wxUser?accountId=${wxUser.accountId}");
			$("#searchForm").submit();
			return false;
	    }
	</script>
</body>
</html>