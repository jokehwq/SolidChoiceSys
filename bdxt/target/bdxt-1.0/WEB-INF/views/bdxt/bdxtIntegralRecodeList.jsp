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
		<shiro:hasPermission name="bdxt:bdxtUser:view"><li><a href="${ctx}/bdxt/bdxtUser/">用户列表</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/bdxt/bdxtUser/queryIntegralRecord?bdxtUserId=${userId}">积分列表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>序号</th>
                <th>操作事件</th>
                <th>金额</th>
                <th>积分类型</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtIntegralRecord" varStatus="status">
			<tr>
                <td>${status.count}</td>
                 <td>${bdxtIntegralRecord.operateName}</td>
                 <td>${bdxtIntegralRecord.capital}</td>
                <td>
                				<c:choose>
                					<c:when test="${bdxtIntegralRecord.integralType ==1}">
                						收入
                					</c:when>
                					<c:otherwise>
                						支出
                					</c:otherwise>
                				</c:choose>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>