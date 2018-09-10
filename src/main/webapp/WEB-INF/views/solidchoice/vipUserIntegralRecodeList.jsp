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
		<shiro:hasPermission name="solidChoice:vipUser:view"><li><a href="${ctx}/solidChoice/vipUser/">用户列表</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/solidChoice/vipUser/queryIntegralRecord?bdxtUserId=${userId}">积分列表</a></li>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>序号</th>
                <th>积分数量</th>
                <th>积分类型</th>
				<th>备注</th>
				<th>时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vipUserIntegralRecord" varStatus="status">
			<tr>
                <td>${status.count}</td>
                 <td>
					 <c:choose>
						 <c:when test="${vipUserIntegralRecord.integralType ==5}">
							 + ${vipUserIntegralRecord.capital}
						 </c:when>
						 <c:otherwise>
							 - ${vipUserIntegralRecord.capital}
						 </c:otherwise>
					 </c:choose>
                <td>
                				<c:choose>
                					<c:when test="${vipUserIntegralRecord.operationType ==26}">
                						投票
                					</c:when>
									<c:when test="${vipUserIntegralRecord.operationType ==27}">
										签到
									</c:when>
									<c:when test="${vipUserIntegralRecord.operationType ==28}">
										邀请好友
									</c:when>
                					<c:otherwise>
                						任务
                					</c:otherwise>
                				</c:choose>
                </td>
				<td>${vipUserIntegralRecord.note}</td>
				<td>
					<fmt:formatDate value="${vipUserIntegralRecord.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>