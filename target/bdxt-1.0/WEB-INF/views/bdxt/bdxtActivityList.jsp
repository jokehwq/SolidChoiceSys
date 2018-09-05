<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
         			if(n) $("#pageNo").val(n);
         			if(s) $("#pageSize").val(s);
         			$("#searchForm").submit();
         			return false;
         		}
         		 $(document).ready(function () {
		           $("#btnExport").click(function () {
                             top.$.jBox.confirm("确认要导出活动记录吗？", "系统提示", function (v, h, f) {
                                 if (v == "ok") {
                                     $("#searchForm").attr("action", "${ctx}/bdxt/bdxtActivity/exportExcel");
                                     $("#searchForm").submit();
                                 }
                             }, {buttonsFocus: 1});
                             top.$('.jbox-body .jbox-icon').css('top', '55px');
                         });
                  });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtActivity/">活动信息列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtActivity:edit"><li><a href="${ctx}/bdxt/bdxtActivity/form">活动信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtActivity" action="${ctx}/bdxt/bdxtActivity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动主题：</label>
				<form:input path="activityName" htmlEscape="false" maxlength="255" class="input-medium" placeholder="请输入活动主题"/>
			</li>
			<li><label>活动类型</label>
				<form:select path="activityType" class="input-medium">
					<form:option value="" disabled="true" selected="selected">请选择</form:option>
					<form:options items="${fns:queryDictListByCondition('ACTIVITY_CODE','活动类型')}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>活动状态</label>
				<form:select path="activityStatus" class="input-medium">
					<form:option value="" disabled="true" selected="selected">请选择</form:option>
					<form:option value="1">招募中</form:option>
					<form:option value="2">进行中</form:option>
					<form:option value="3">已结束</form:option>
					<form:option value="4">暂停</form:option>
				</form:select>
			</li>
			<li><label>性别要求</label>
				<form:select path="genderReq" class="input-medium">
					<form:option value="" disabled="true" selected="selected">请选择</form:option>
					<form:option value="">不限</form:option>
					<form:option value="1">男</form:option>
					<form:option value="2">女</form:option>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>类型</th>
				<th>主题</th>
				<th>状态</th>
				<th>活动时间</th>
				<th>活动地点</th>
				<th>招募人数</th>
				<th>性别要求</th>
				<th>人均预算</th>
				<th>身高</th>
				<shiro:hasPermission name="bdxt:bdxtActivity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtActivity" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${bdxtActivity.dictName}</td>
				<td>${bdxtActivity.activityName}</td>
				<td>
				<c:choose>
					<c:when test="${bdxtActivity.activityStatus ==1}">
						招募中
					</c:when>
					<c:when test="${bdxtActivity.activityStatus ==2}">
						进行中
					</c:when>
					<c:when test="${bdxtActivity.activityStatus ==3}">
						已结束
					</c:when>
					<c:when test="${bdxtActivity.activityStatus ==4}">
						暂停
					</c:when>
					<c:when test="${bdxtActivity.activityStatus ==5}">
						过期
					</c:when>
					<c:otherwise>
						待支付
					</c:otherwise>
				</c:choose>
				</td>
				<td><fmt:formatDate value="${bdxtActivity.activityStartTime}" pattern="yyyy-MM-dd"/> ~
					<fmt:formatDate value="${bdxtActivity.activityEndTime}" pattern="yyyy-MM-dd"/>
			    </td>
				<td>${bdxtActivity.workAddress}</td>
				<td>${bdxtActivity.recruitNum}</td>
				<td>${bdxtActivity.genderReq==1?'男':'女'}</td>
				<td>${bdxtActivity.perBugget}</td>
				<td>${bdxtActivity.heightReqLeft} ~ ${bdxtActivity.heightReqRight}</td>
				<shiro:hasPermission name="bdxt:bdxtActivity:edit"><td>

					<a href="${ctx}/bdxt/bdxtActivity/detail?id=${bdxtActivity.id}">查看</a>
					<c:if test="${bdxtActivity.activityStatus==4}">
						<a href="${ctx}/bdxt/bdxtActivity/form?id=${bdxtActivity.id}">编辑</a>
					</c:if>
					<a href="${ctx}/bdxt/bdxtActivity/update?id=${bdxtActivity.id}&activityStatus=4">暂停</a>
					<a href="${ctx}/bdxt/bdxtActivity/update?id=${bdxtActivity.id}&status=2" onclick="return confirmx('确认要删除该活动信息吗？', this.href)">删除</a>
					<c:if test="${bdxtActivity.activityStatus==1}">
						<a href="${ctx}/bdxt/bdxtActivity/update?id=${bdxtActivity.id}&activityStatus=2" onclick="return confirmx('是否结束招募？', this.href)">结束</a>
					</c:if>
					<c:if test="${bdxtActivity.activityStatus==2}">
						<a href="${ctx}/bdxt/bdxtActivity/update?id=${bdxtActivity.id}&activityStatus=6" onclick="return confirmx('是否结束活动进行费用支付？', this.href)">结束</a>
					</c:if>
					<c:if test="${bdxtActivity.activityStatus==6}">
						<a href="${ctx}/bdxt/bdxtActivity/update?id=${bdxtActivity.id}&activityStatus=3" onclick="return confirmx('是否归档活动？', this.href)">结束</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>