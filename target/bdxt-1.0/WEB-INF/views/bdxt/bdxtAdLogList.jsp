<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告素材管理</title>
	<meta name="decorator" content="default"/>
</head>
<script type="text/javascript">
    function page(n, s) {
        if (n) $("#pageNo").val(n);
        if (s) $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/bdxt/bdxtAdLog/list");
        $("#searchForm").submit();
        return false;
    }
</script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtAdLog/">广告点击记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtAdLog" action="${ctx}/bdxt/bdxtAdLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>素材标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>素材类型：</label>
				<form:select path="type" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:options items="${fns:getDictList('bdxt_ad_material')}" itemLabel="label" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option  value="" >请选择</form:option>
					<form:option value="1" label="有效"/>
					<form:option value="2" label="重复"/>
				</form:select>
			</li>
			<li><label>点击人：</label>
				<form:input path="clickname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bdxtAdLog.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bdxtAdLog.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>素材标题</th>
				<th>素材类型</th>
				<th>点击日期</th>
				<th>状态</th>
				<th>点击人</th>
				<th>点击时间</th>
				<%--<shiro:hasPermission name="bdxt:bdxtAdLog:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtAdLog" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="${ctx}/bdxt/bdxtAdLog/form?id=${bdxtAdLog.id}">
					${bdxtAdLog.title}
				</a></td>
				<td>
					${bdxtAdLog.typeName}
				</td>
				<td>
					<fmt:formatDate value="${bdxtAdLog.createTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:if test="${bdxtAdLog.status == 1}">
						有效
					</c:if>
					<c:if test="${bdxtAdLog.status == 2}">
						重复
					</c:if>
				</td>
				<td>
					${bdxtAdLog.clickname}
				</td>
				<td>
					<fmt:formatDate value="${bdxtAdLog.createTime}" pattern="HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
        $("#btnExport").click(function () {
            top.$.jBox.confirm("确认要导出记录吗？", "系统提示", function (v, h, f) {
                if (v == "ok") {
                    $("#searchForm").attr("action", "${ctx}/bdxt/bdxtAdLog/exportExcel");
                    $("#searchForm").submit();
                }
            }, {buttonsFocus: 1});
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });

	</script>
</body>
</html>