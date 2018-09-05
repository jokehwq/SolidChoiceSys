<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>积分配置管理管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bdxt/bdxtUserFundLog/">提现记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtUserFundLog" action="${ctx}/bdxt/bdxtUserFundLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户姓名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>联系方式：</label>
				<form:input path="phone" htmlEscape="false" maxlength="24" class="input-medium"/>
			</li>
			<li><label>提现状态：</label>
				<form:select path="status" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:option  value="2" >未支付</form:option>
					<form:option  value="1" >已支付</form:option>
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
				<th>用户姓名</th>
				<th>联系方式</th>
				<th>金额</th>
				<th>银行卡号</th>
				<th>提现时间</th>
				<th>提现状态</th>
				<shiro:hasPermission name="bdxt:bdxtUserFundLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtUserFundLog" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>
					${bdxtUserFundLog.userName}
				</td>
				<td>
					${bdxtUserFundLog.phone}
				</td>
				<td>
					${bdxtUserFundLog.capital}
				</td>
				<td>
						${bdxtUserFundLog.bankCardNo}
				</td>
				<td>
					<fmt:formatDate value="${bdxtUserFundLog.creatTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${bdxtUserFundLog.status == 2}">
						未支付
					</c:if>
					<c:if test="${bdxtUserFundLog.status == 1}">
						已支付
					</c:if>
				</td>
				<shiro:hasPermission name="bdxt:bdxtUserFundLog:edit"><td>
					<a href="javascript:frames_fb('${bdxtUserFundLog.id}','${bdxtUserFundLog.remarkes}');">更新状态</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript">

        //弹出一个iframe层
        function frames_fb(id,remarkes) {

            var selectHtml = '<div style="padding: 5px;">' +
                '<select id="selectId" name="interest" lay-filter="aihao">' +
                '<option selected value="2">未支付</option> ' +
                '<option value="1">已支付</option> ' +
                '</select>' +
                '<div class="layui-form-item layui-form-text"> <label class="layui-form-label">备注</label> <div class="layui-input-block"> <textarea id="beizu" class="layui-textarea">'+remarkes+'</textarea> </div></div></div>';
            layer.open({
                type: 0,
                title: '支付信息',
                closeBtn: 2,
                btn: ['确定', '取消']
                ,yes: function(index, layero){
                    var selectd = $('#selectId').val(); //获取支付
                    var inpd = $('#beizu').val();//输入框内容
					/*var data = {
					 status:selectd,
					 explain:inpd
					 }*/
                    $.ajax({
                        type:"GET",
                        url:"${ctx}/bdxt/bdxtUserFundLog/audit?id="+id+"&status="+selectd+"&remarkes="+inpd,
                        //data: data,
                        success:function(data){
                            //刷新当前页
							showTip('操作成功');
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
        $("#btnExport").click(function () {
            top.$.jBox.confirm("确认要导出记录吗？", "系统提示", function (v, h, f) {
                if (v == "ok") {
                    $("#searchForm").attr("action", "${ctx}/bdxt/bdxtUserFundLog/exportExcel");
                    $("#searchForm").submit();
                }
            }, {buttonsFocus: 1});
            top.$('.jbox-body .jbox-icon').css('top', '55px');
        });

	</script>
</body>
</html>