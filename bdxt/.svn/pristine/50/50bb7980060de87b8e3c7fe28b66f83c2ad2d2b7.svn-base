<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时任务管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/system/task/quartzJobDetail/">定时任务列表</a></li>
		<shiro:hasPermission name="system:task:quartzJobDetail:edit"><li><a href="${ctx}/system/task/quartzJobDetail/form">定时任务添加</a></li></shiro:hasPermission>
	</ul>
	
	<form:form id="searchForm" modelAttribute="quartzJobDetail" action="${ctx}/system/task/quartzJobDetail/" method="post" class="breadcrumb form-search">
		<div class="pull-right">
			<c:if test="${schedulerShutdown}">定时调度已关闭</c:if>
			<c:if test="${!schedulerShutdown}">
				<c:if test="${schedulerStandbyMode}">
					<input class="btn btn-success" type="button" value="启动定时调度" onclick="return confirmx('确认要启动定时调度吗？', '${ctx}/system/task/quartzJobDetail/startall')"/>
				</c:if>
				<c:if test="${!schedulerStandbyMode}">
					<input class="btn btn-info" type="button" value="暂停定时调度" onclick="return confirmx('确认要暂停定时调度吗？', '${ctx}/system/task/quartzJobDetail/stop')"/>
				</c:if>
				<input class="btn btn-danger" type="button" value="关闭定时调度" onclick="return confirmx('确认要关闭定时调度吗,关闭后将不能再启动？', '${ctx}/system/task/quartzJobDetail/shutdown')"/>
			</c:if>
		</div>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务名：</label>
				<form:input path="jobName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>组名：</label>
				<form:input path="jobGroup" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>Job类：</label>
				<form:input path="jobClassName" htmlEscape="false" maxlength="250" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务名</th>
				<th>组名</th>
				<th>Job类</th>
				<th>是否持久化</th>
				<th>是否非并发</th>
				<th>是否更新数据</th>
				<th>请求恢复</th>
				<th>cron表达式</th>
				<th>状态</th>
				<shiro:hasPermission name="system:task:quartzJobDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="quartzJobDetail">
			<tr>
				<td>
					<a href="${ctx}/system/task/quartzJobDetail/form?id=${quartzJobDetail.id}">${quartzJobDetail.jobName}</a>
				</td>
				<td>
					${quartzJobDetail.jobGroup}
				</td>
				<td>
					${quartzJobDetail.jobClassName}
				</td>
				<td>
					${quartzJobDetail.isDurable=='1'?'是':'否'}
				</td>
				<td>
					${quartzJobDetail.isNonconcurrent=='1'?'是':'否'}
				</td>
				<td>
					${quartzJobDetail.isUpdateData=='1'?'是':'否'}
				</td>
				<td>
					${quartzJobDetail.requestsRecovery=='1'?'是':'否'}
				</td>
				<td>
					${quartzJobDetail.cron}
				</td>
				<td>
					${quartzJobDetail.remarks}
				</td>
				<shiro:hasPermission name="system:task:quartzJobDetail:edit"><td>
    				<a href="${ctx}/system/task/quartzJobDetail/form?id=${quartzJobDetail.id}">修改</a>
					<a href="${ctx}/system/task/quartzJobDetail/delete?id=${quartzJobDetail.id}" onclick="return confirmx('确认要删除该定时任务吗？', this.href)">删除</a>
					<c:if test="${quartzJobDetail.remarks=='PAUSED'}">
						<a href="${ctx}/system/task/quartzJobDetail/start?id=${quartzJobDetail.id}" onclick="return confirmx('确认要启动该定时任务吗？', this.href)">启动</a>
					</c:if>
					<c:if test="${quartzJobDetail.remarks=='NORMAL'}">
						<a href="${ctx}/system/task/quartzJobDetail/pause?id=${quartzJobDetail.id}" onclick="return confirmx('确认要暂停该定时任务吗？', this.href)">暂停</a>
					</c:if>
					<a href="${ctx}/system/task/quartzJobDetail/exec?id=${quartzJobDetail.id}" onclick="return confirmx('确认要执行该定时任务吗？', this.href)">执行</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>