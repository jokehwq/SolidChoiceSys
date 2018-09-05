<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图文管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/wxnewsmsg/wxNewsMsg/list">图文管理列表</a></li>
		<shiro:hasPermission name="weixin:wxMsg:edit"><li><a href="${ctx}/wxnewsmsg/wxNewsMsg/form/?accountid=${accountId}">图文新增</a></li></shiro:hasPermission>
	</ul>
		<form:form id="searchForm" modelAttribute="wxMenu"  class="breadcrumb form-search"> 
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	 </form:form>

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">

		<tbody>
		<c:forEach items="${page.list}" var="wxMsg">
	
		          <c:forEach items="${wxMsg.materials}" var="materials">
		          <c:if test="${materials.pId==null}">
		          <tr>
		            <td style="width: 150px"><img style="height:80px;width:120px"  src="${materials.thumbMediaUrl}"/></td>
		            <td style="width: 100px">${materials.title}</td>
		            <td style="width: 100px"><fmt:formatDate value="${materials.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		         <shiro:hasPermission name="weixin:wxMaterialNews:edit"><td>
    				<a href="${ctx}/wxnewsmsg/wxNewsMsg/form?id=${wxMsg.id}&&accountid=${wxMsg.accountid}">修改</a>
					<a href="${ctx}/wxnewsmsg/wxNewsMsg/delete?id=${wxMsg.id}&&accountid=${wxMsg.accountid}" onclick="return confirmx('确认要删除该图文消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
		          </tr>
		          </c:if>
		          </c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
<script type="text/javascript">
		function page(n,s){
		var accountId = "${accountId}";
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").attr("action","/webbase5/a/wxnewsmsg/wxNewsMsg/list?accountid="+accountId);
	$("#searchForm").submit();
	return false;
}
	</script>
</body>
</html>