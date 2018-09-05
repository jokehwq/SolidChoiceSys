<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公众号管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/wxaccount/wxAccount/">公众号列表</a></li>
		<shiro:hasPermission name="wxaccount:wxAccount:edit"><li><a href="${ctx}/wxaccount/wxAccount/form">公众号添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxAccount" action="${ctx}/wxaccount/wxAccount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公众号名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>公众号类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('account_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>公众号原始id</th>
				<th>公众号名称</th>
				<th>appid</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>创建人</th>
				<shiro:hasPermission name="wxaccount:wxAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxAccount">
			<tr>
				<td><a href="${ctx}/wxaccount/wxAccount/form?id=${wxAccount.id}">
					${wxAccount.account}
				</a></td>
				<td>
					${wxAccount.name}
				</td>
				<td>
					${wxAccount.appid}
				</td>
				<td>
					<fmt:formatDate value="${wxAccount.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${wxAccount.modifytime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wxAccount.userid}
				</td>
				<shiro:hasPermission name="wxaccount:wxAccount:edit"><td>
    				<a href="${ctx}/wxaccount/wxAccount/form?id=${wxAccount.id}">修改</a>
					<a href="${ctx}/wxaccount/wxAccount/delete?id=${wxAccount.id}" onclick="return confirmx('确认要删除该公众号吗？', this.href)">删除</a>
		         	<a href="${ctx}/wxaccount/wxAccount/manager?id=${wxAccount.id}" target="_blank">管理</a>	
					<a href="#" onclick="batchget('${wxAccount.id}')">批量获取用户</a>
</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script>
function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").attr("action","/webbase5/a/wxaccount/wxAccount/");
	$("#searchForm").submit();
	return false;
}

function batchget(wxAccountid){
	var href="${ctx}/wxaccount/wxAccount/followList?id="+wxAccountid;
	top.$.jBox.confirm('获取用户可能需要几分钟，要继续吗？','系统提示',function(v,h,f){
		if(v=='ok'){
                loading("正在获取用户，请稍候。。。。");
				location = href;
			}
	},{buttonsFocus:1, closed:function(){
		if (typeof closed == 'function') {
			closed();
		}
	}});
}



</script>
</body>

</html>