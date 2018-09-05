<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人资金记录管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtUserFundRecord/">个人资金记录列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtUserFundRecord:edit"><li><a href="${ctx}/bdxt/bdxtUserFundRecord/form">个人资金记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtUserFundRecord" action="${ctx}/bdxt/bdxtUserFundRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>银行卡号：</label>
				<form:input path="bankCardNo" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>户主姓名：</label>
				<form:input path="houseHolderName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>状态：1-启用 2-停用 3-冻结：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li><label>资金类型，1:入账，2:出账：</label>
				<form:input path="capitalType" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户账号id</th>
				<th>金额</th>
				<th>银行卡号</th>
				<th>户主姓名</th>
				<th>状态：1-启用 2-停用 3-冻结</th>
				<th>资金类型，1:入账，2:出账</th>
				<th>创建时间</th>
				<shiro:hasPermission name="bdxt:bdxtUserFundRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtUserFundRecord">
			<tr>
				<td><a href="${ctx}/bdxt/bdxtUserFundRecord/form?id=${bdxtUserFundRecord.id}">
					${bdxtUserFundRecord.bxdtUserId}
				</a></td>
				<td>
					${bdxtUserFundRecord.capital}
				</td>
				<td>
					${bdxtUserFundRecord.bankCardNo}
				</td>
				<td>
					${bdxtUserFundRecord.houseHolderName}
				</td>
				<td>
					${bdxtUserFundRecord.status}
				</td>
				<td>
					${bdxtUserFundRecord.capitalType}
				</td>
				<td>
					<fmt:formatDate value="${bdxtUserFundRecord.creatTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bdxt:bdxtUserFundRecord:edit"><td>
    				<a href="${ctx}/bdxt/bdxtUserFundRecord/form?id=${bdxtUserFundRecord.id}">修改</a>
					<a href="${ctx}/bdxt/bdxtUserFundRecord/delete?id=${bdxtUserFundRecord.id}" onclick="return confirmx('确认要删除该个人资金记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>