<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
	<meta name="decorator" content="default"/>
</head>
<script type="text/javascript">
    function page(n, s) {
        if (n) $("#pageNo").val(n);
        if (s) $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/bdxt/bdxtProduct/list");
        $("#searchForm").submit();
        return false;
    }
</script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtProduct/">商品列表</a></li>
		<shiro:hasPermission name="bdxt:bdxtProduct:edit"><li><a href="${ctx}/bdxt/bdxtProduct/form">商品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtProduct" action="${ctx}/bdxt/bdxtProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:options items="${fns:getDictList('bdxt_pro_type')}" itemLabel="label" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:option  value="1" >上架</form:option>
					<form:option  value="2" >下架</form:option>
				</form:select>
			</li>
			<li><label>所需积分：</label>
				<form:input path="beginScores" htmlEscape="false" class="input-medium"/> ~
				<form:input path="endScores" htmlEscape="false" class="input-medium"/>
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
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品说明</th>
				<th>商品类型</th>
				<th>所需积分</th>
				<th>状态</th>
				<th>库存</th>

				<shiro:hasPermission name="bdxt:bdxtProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtProduct" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
						${bdxtProduct.code}
				</td>
				<td><a href="${ctx}/bdxt/bdxtProduct/form?id=${bdxtProduct.id}">
					${bdxtProduct.name}
				</a></td>
				<td>
						${bdxtProduct.explains}
				</td>
				<td>
					${bdxtProduct.typename}
				</td>
				<td>
					${bdxtProduct.scores}
				</td>
				<td>
					<c:if test="${bdxtProduct.status == 1}">
						上架
					</c:if>
					<c:if test="${bdxtProduct.status == 2}">
						下架
					</c:if>
				</td>
				<td>
					${bdxtProduct.count}
				</td>
				<shiro:hasPermission name="bdxt:bdxtProduct:edit"><td>
    				<a href="${ctx}/bdxt/bdxtProduct/form?id=${bdxtProduct.id}">编辑</a>
					<c:if test="${bdxtProduct.status == 1}">
						<a href="${ctx}/bdxt/bdxtProduct/down?id=${bdxtProduct.id}&status=2" onclick="return confirmx('确认要下架吗？', this.href)">下架</a>
					</c:if>
					<c:if test="${bdxtProduct.status == 2}">
						<a href="${ctx}/bdxt/bdxtProduct/down?id=${bdxtProduct.id}&status=1" onclick="return confirmx('确认要上架吗？', this.href)">上架</a>
					</c:if>
					<c:if test="${bdxtProduct.carousel == 0}">
						<a href="${ctx}/bdxt/bdxtProduct/carousel?id=${bdxtProduct.id}&carousel=1" onclick="return confirmx('确认要轮播吗？', this.href)">轮播</a>
					</c:if>
					<c:if test="${bdxtProduct.carousel == 1}">
						<a href="${ctx}/bdxt/bdxtProduct/carousel?id=${bdxtProduct.id}&carousel=0" onclick="return confirmx('确认要取消轮播吗？', this.href)">取消轮播</a>
					</c:if>

					<a href="${ctx}/bdxt/bdxtProduct/del?id=${bdxtProduct.id}" onclick="return confirmx('确认要删除该商品管理吗？', this.href)">删除</a>
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
                    $("#searchForm").attr("action", "${ctx}/bdxt/bdxtProduct/exportExcel");
                    $("#searchForm").submit();
                }
            }, {buttonsFocus: 1});
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });

	</script>
</body>
</html>