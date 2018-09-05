<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理管理</title>
	<meta name="decorator" content="default"/>
</head>
<script type="text/javascript">
    function page(n, s) {
        if (n) $("#pageNo").val(n);
        if (s) $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/bdxt/bdxtProductOrder/list");
        $("#searchForm").submit();
        return false;
    }
</script>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtProductOrder/">订单管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtProductOrder" action="${ctx}/bdxt/bdxtProductOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>兑换人：</label>
				<form:input path="people" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>分类：</label>
				<form:select path="type" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:options items="${fns:getDictList('bdxt_pro_type')}" itemLabel="label" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:option  value="1" >待发货</form:option>
					<form:option  value="2" >已发货</form:option>
				</form:select>
			</li>
			<li><label>兑换时间：</label>
				<input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bdxtProductOrder.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bdxtProductOrder.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品类型</th>
				<th>兑换人</th>
				<th>兑换数量</th>
				<th>所需积分</th>
				<th>兑换日期</th>
				<th>状态</th>
				<th>收件人</th>
				<th>联系电话</th>
				<th>地址</th>
				<shiro:hasPermission name="bdxt:bdxtProductOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtProductOrder" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
					${bdxtProductOrder.code}
				</td>
				<td>
					${bdxtProductOrder.name}
				</td>
				<td>
					${bdxtProductOrder.typename}
				</td>
				<td>
					${bdxtProductOrder.people}
				</td>
				<td>
					${bdxtProductOrder.count}
				</td>
				<td>
					${bdxtProductOrder.scores}
				</td>
				<td>
					<fmt:formatDate value="${bdxtProductOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${bdxtProductOrder.status == 1}">
						待发货
					</c:if>
					<c:if test="${bdxtProductOrder.status == 2}">
						已发货
					</c:if>
				</td>
				<td>
					${bdxtProductOrder.recipients}
				</td>
				<td>
					${bdxtProductOrder.phone}
				</td>
				<td>
					${bdxtProductOrder.addr}
				</td>

				<shiro:hasPermission name="bdxt:bdxtProductOrder:edit"><td>

					<c:if test="${bdxtProductOrder.status == 1}">
						<a href="javascript:frames_fb('${bdxtProductOrder.id}');">发货</a>
					</c:if>
					<c:if test="${bdxtProductOrder.status == 2}">
						<a href="javascript:frames_fb_2('${bdxtProductOrder.exname}','${bdxtProductOrder.exnum}');">查看物流</a>
					</c:if>

					<%--<a href="${ctx}/bdxt/bdxtProductOrder/delete?id=${bdxtProductOrder.id}" onclick="return confirmx('确认要删除该订单管理吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript">

        //弹出一个iframe层
        function frames_fb(orderid) {

            var selectHtml = '<div style="padding: 5px;">' +
				'<select id="selectId" name="interest" lay-filter="aihao">' +
				'<option selected value="EMS">EMS</option> ' +
				'<option value="韵达快递">韵达快递</option> ' +
                '<option value="顺丰快递">顺丰快递</option> ' +
                '<option value="申通快递">申通快递</option> ' +
                '<option value="中通速递">中通速递</option> ' +
                '<option value="圆通快递">圆通快递</option> ' +
                '<option value="百世快递">百世快递</option> ' +
                '<option value="德邦快递">德邦快递</option> ' +
                '<option value="其他">其他</option> ' +
				'</select>' +
				'<div class="layui-form-item layui-form-text"> <label class="layui-form-label">快递单号</label> <div class="layui-input-block"> <input id="beizu" placeholder="请输入快递单号" class="layui-textarea"></input> </div></div></div>';
            layer.open({
                type: 0,
                title: '快递列表',
                closeBtn: 2,
                btn: ['确定', '取消']
                ,yes: function(index, layero){
                    var selectd = $('#selectId').val(); //获取快递名称
                    var inpd = $('#beizu').val();//输入框内容
					/*var data = {
					 status:selectd,
					 explain:inpd
					 }*/
                    $.ajax({
                        type:"GET",
                        url:"${ctx}/bdxt/bdxtProductOrder/audit?id="+orderid+"&exname="+selectd+"&exnum="+inpd,
                        //data: data,
                        success:function(data){
                            //刷新当前页
                            // showTip('操作成功');
                            layer.closeAll();
                            location.reload();
                        },
                        error:function (data) {
                            showTip('操作失败');
                            layer.closeAll();
                        }
                    });
                }
                ,btn2: function(index, layero){

                },
                shadeClose: true,
                skin: '',
                content: selectHtml
            });
        }

        //弹出一个iframe层
        function frames_fb_2(v1,v2) {
            var selectHtml = '<div style="padding: 5px;">' +
                '<input id="beizu"  class="layui-textarea" value="' +v1+
				'" readonly="value"/> '+
                '<div class="layui-form-item layui-form-text"> <label class="layui-form-label">快递单号</label> <div class="layui-input-block"> <input id="beizu" value="' +v2+
				'" readonly="value" class="layui-textarea"></input> </div></div></div>';
            layer.open({
                type: 0,
                title: '快递列表',
                closeBtn: 2,
                btn: ['关闭']
                ,btn2: function(index, layero){
                },
                shadeClose: true,
                skin: '',
                content: selectHtml
            });
        }
        $("#btnExport").click(function () {
            top.$.jBox.confirm("确认要导出记录吗？", "系统提示", function (v, h, f) {
                if (v == "ok") {
                    $("#searchForm").attr("action", "${ctx}/bdxt/bdxtProductOrder/exportExcel");
                    $("#searchForm").submit();
                }
            }, {buttonsFocus: 1});
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });

	</script>
</body>
</html>