<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>素材管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/wxMaterialNews/?accountId=${accountId}">素材列表</a></li>
		<shiro:hasPermission name="weixin:wxMaterialNews:edit"><li><a href="${ctx}/weixin/wxMaterialNews/form?accountId=${accountId}">添加素材</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxMaterialNews" action="${ctx}/weixin/wxMaterialNews/?accountId=${accountId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
 <%-- 			<li><label>图片名字：</label>
				<form:input path="title" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> --%>
			<li class="btns"><a href="${ctx}/weixin/wxMaterialNews/syncimage?accountId=${accountId}"><input id="btnSubmit" class="btn btn-primary" type="button" value="同步图片素材"/></a></li>
			<li class="btns"><a href="${ctx}/weixin/wxMaterialNews/syncnews?accountId=${accountId}"><input id="btnSubmit" class="btn btn-primary" type="button" value="同步图文素材"/></a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<c:forEach items="${page.list}" var="wxMaterialNews">		
		    <div style="float: left;margin-left: 10px" class="example"> 
		     <div style="width:100px;height: 75px;">
		     <img style="height:75px" alt="${wxMaterialNews.digest}" src="${wxMaterialNews.thumbMediaUrl}" width="100%" />
		     </div> 
		     <a href="${ctx}/weixin/wxMaterialNews/delete?id=${wxMaterialNews.id}&&\accountId=${accountId}" onclick="return confirmx('确认要删除该图片吗？', this.href)"">删除</a>	      
		   </div>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
	<link rel="stylesheet" href="${ctxStatic}/weixin/zoomify/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="${ctxStatic}/weixin/zoomify/dist/zoomify.min.css">
	<script src="${ctxStatic}/weixin/zoomify/js/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/weixin/zoomify/dist/zoomify.min.js"></script>
	<script type="text/javascript">
	$('.example img').zoomify();
	function page(n,s){
		var accountId = "${accountId}";
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").attr("action","/webbase5/a/weixin/wxMaterialNews/list?accountId="+accountId);
	$("#searchForm").submit();
	return false;
}
</script>
</body>
</html>