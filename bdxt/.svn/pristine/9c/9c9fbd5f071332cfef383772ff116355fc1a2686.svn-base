<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告管理管理</title>
	<meta name="decorator" content="default"/>
</head>
<script type="text/javascript">
    function page(n, s) {
        if (n) $("#pageNo").val(n);
        if (s) $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/bdxt/bdxtAd/list");
        $("#searchForm").submit();
        return false;
    }
</script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtAd/">广告列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtAd:edit"><li><a href="${ctx}/bdxt/bdxtAd/form">广告添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtAd" action="${ctx}/bdxt/bdxtAd/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>素材标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>广告位置：</label>
				<form:select path="location" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:options items="${fns:getDictList('bdxt_ad_location')}" itemLabel="label" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>类型：</label>
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
					value="<fmt:formatDate value="${bdxtAd.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bdxtAd.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>广告位置</th>
				<th>素材标题</th>
				<th>素材类型</th>
				<th>状态</th>
				<th>展示数</th>
				<th>点击数</th>
				<th>创建人</th>
				<th>创建时间</th>
				<shiro:hasPermission name="bdxt:bdxtAd:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtAd" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="${ctx}/bdxt/bdxtAd/form?id=${bdxtAd.id}">
					${bdxtAd.locationname}
				</a></td>
				<td>
						${bdxtAd.title}
				</td>
				<td>
						${bdxtAd.type}
				</td>
				<td>
					<c:if test="${bdxtAd.status == 1}">
						上架
					</c:if>
					<c:if test="${bdxtAd.status == 2}">
						下架
					</c:if>
				</td>
				<td>
					${bdxtAd.showcount}
				</td>
				<td>
					${bdxtAd.clickcount}
				</td>
				<td>
					${bdxtAd.username}
				</td>
				<td>
					<fmt:formatDate value="${bdxtAd.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="bdxt:bdxtAd:edit"><td>
    				<a href="${ctx}/bdxt/bdxtAd/form?id=${bdxtAd.id}">编辑</a>
					<c:if test="${bdxtAd.status == 1}">
						<a href="${ctx}/bdxt/bdxtAd/down?id=${bdxtAd.id}&status=2" onclick="return confirmx('确认要下架吗？', this.href)">下架</a>
					</c:if>
					<c:if test="${bdxtAd.status == 2}">
						<a href="${ctx}/bdxt/bdxtAd/down?id=${bdxtAd.id}&status=1" onclick="return confirmx('确认要上架吗？', this.href)">上架</a>
					</c:if>
					<a href="${ctx}/bdxt/bdxtAd/delete?id=${bdxtAd.id}" onclick="return confirmx('确认要删除该广告管理吗？', this.href)">删除</a>
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
                    $("#searchForm").attr("action", "${ctx}/bdxt/bdxtAd/exportExcel");
                    $("#searchForm").submit();
                }
            }, {buttonsFocus: 1});
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });

	</script>
</body>
</html>