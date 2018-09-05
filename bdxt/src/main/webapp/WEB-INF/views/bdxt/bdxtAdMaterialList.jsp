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
		<li class="active"><a href="${ctx}/bdxt/bdxtAdMaterial/">广告素材列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtAdMaterial:edit"><li><a href="${ctx}/bdxt/bdxtAdMaterial/form">广告素材添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtAdMaterial" action="${ctx}/bdxt/bdxtAdMaterial/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
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
					<form:option value="1" label="上架"/>
					<form:option value="2" label="下架"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bdxtAdMaterial.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bdxtAdMaterial.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>创建人：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>标题</th>
				<th>素材类型</th>
				<th>内容</th>
				<th>状态</th>
				<th>展示数</th>
				<th>点击数</th>
				<th>创建时间</th>
				<th>创建人</th>
				<shiro:hasPermission name="bdxt:bdxtAdMaterial:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtAdMaterial" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="${ctx}/bdxt/bdxtAdMaterial/form?id=${bdxtAdMaterial.id}">
					${bdxtAdMaterial.title}
				</a></td>
				<td>
					${bdxtAdMaterial.typeName}
				</td>
				<td>
					${bdxtAdMaterial.content}
				</td>
				<td>
					<c:if test="${bdxtAdMaterial.status == 1}">
						上架
					</c:if>
					<c:if test="${bdxtAdMaterial.status == 2}">
						下架
					</c:if>
				</td>
				<td>
					${bdxtAdMaterial.showcount}
				</td>
				<td>
					${bdxtAdMaterial.clickcount}
				</td>
				<td>
					<fmt:formatDate value="${bdxtAdMaterial.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bdxtAdMaterial.username}
				</td>
				<shiro:hasPermission name="bdxt:bdxtAdMaterial:edit"><td>
    				<a href="${ctx}/bdxt/bdxtAdMaterial/form?id=${bdxtAdMaterial.id}">编辑</a>

					<c:if test="${bdxtAdMaterial.status == 1}">
						<a href="${ctx}/bdxt/bdxtAdMaterial/down?id=${bdxtAdMaterial.id}&status=2" onclick="return confirmx('确认要下架吗？', this.href)">下架</a>
					</c:if>
					<c:if test="${bdxtAdMaterial.status == 2}">
						<a href="${ctx}/bdxt/bdxtAdMaterial/down?id=${bdxtAdMaterial.id}&status=1" onclick="return confirmx('确认要上架吗？', this.href)">上架</a>
					</c:if>
					<%--<a href="${ctx}/bdxt/bdxtAdLog?id=${bdxtAdMaterial.title}">点击记录</a>--%>
					<a href="${ctx}/bdxt/bdxtAdMaterial/delete?id=${bdxtAdMaterial.id}" onclick="return confirmx('确认要删除该广告素材吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
        $("#btnExport").click(function () {
            top.$.jBox.confirm("确认要导出记录吗？", "系统提示", function (v, h, f) {
                if (v == "ok") {
                    $("#searchForm").attr("action", "${ctx}/bdxt/bdxtAdMaterial/exportExcel");
                    $("#searchForm").submit();
                }
            }, {buttonsFocus: 1});
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });

	</script>
</body>
</html>