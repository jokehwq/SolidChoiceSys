<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分配置管理管理</title>
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
		<li class="active"><a href="${ctx}/bdxt/bdxtIntegralConfig/">积分配置管理列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtIntegralConfig:edit"><li><a href="${ctx}/bdxt/bdxtIntegralConfig/form">积分配置管理添加</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>序号</th>
                <th>积分类型</th>
                <th>积分金额</th>
				<!-- <shiro:hasPermission name="bdxt:bdxtIntegralConfig:edit"><th>操作</th></shiro:hasPermission> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtIntegralConfig" varStatus="status">
			<tr>
                <td>${status.count}</td>
                <td>
                				<c:choose>
                					<c:when test="${bdxtIntegralConfig.operateType ==1}">
                						签到
                					</c:when>
                					<c:when test="${bdxtIntegralConfig.operateType ==2}">
                						投稿审核通过
                					</c:when>
                					<c:otherwise>
                						待确定
                					</c:otherwise>
                				</c:choose>
                </td>
                <td>${bdxtIntegralConfig.operateCapital}</td>
				<!-- <shiro:hasPermission name="bdxt:bdxtIntegralConfig:edit"><td>
    				<a href="${ctx}/bdxt/bdxtIntegralConfig/form?id=${bdxtIntegralConfig.id}">修改</a>
					<a href="${ctx}/bdxt/bdxtIntegralConfig/delete?id=${bdxtIntegralConfig.id}" onclick="return confirmx('确认要删除该积分配置管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission> -->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>