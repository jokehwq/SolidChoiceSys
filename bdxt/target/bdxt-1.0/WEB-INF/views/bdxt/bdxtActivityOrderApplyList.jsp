<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动订单支付管理</title>
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
                        top.$.jBox.confirm("确认要导出活动订单支付记录吗？", "系统提示", function (v, h, f) {
                            if (v == "ok") {
                                $("#searchForm").attr("action", "${ctx}/bdxt/bdxtActivityOrder/exportExcel");
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
		<shiro:hasPermission name="bdxt:bdxtActivity:view"><li><a href="${ctx}/bdxt/bdxtActivity/detail?id=${activityId}">活动详情</a></li></shiro:hasPermission>
		<shiro:hasPermission name="bdxt:bdxtUserQuote:view"><li><a href="${ctx}/bdxt/bdxtUserQuote/list?bdxtActivityId=${activityId}">报名记录</a></li></shiro:hasPermission>
		<shiro:hasPermission name="bdxt:bdxtUserCard:view"><li><a href="${ctx}/bdxt/bdxtUserCard/list?bdxtActivityId=${activityId}">打卡记录</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/bdxt/bdxtActivityOrder/applyList?activityId=${activityId}">支付信息</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtActivityOrder" action="${ctx}/bdxt/bdxtActivityOrder/applyList" method="post" class="breadcrumb form-search">
		<input type="hidden" name="activityId" value="${activityId}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label>
				<form:input path="realName" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入用户名称"/>
			</li>
			<li><label>联系方式：</label>
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入联系方式"/>
			</li>
			<li><label>订单状态</label>
				<form:select path="orderStatus" class="input-medium">
				    <form:option value="">请选择</form:option>
					<form:option value="1">未支付</form:option>
					<form:option value="2">待支付</form:option>
					<form:option value="3">已支付</form:option>
					<form:option value="4">已取消</form:option>
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
				<th>用户名</th>
				<th>联系方式</th>
				<th>支付状态</th>
				<th>打卡天数</th>
				<th>报价金额</th>
				<th>支付金额</th>
				<th>支付时间</th>
				<th>备注</th>
				<shiro:hasPermission name="bdxt:bdxtActivityOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtOrderApply" varStatus="status">
			<tr>
				<td>
					${status.count}
				</td>
				<td>${bdxtOrderApply.realName}</td>
				<td>${bdxtOrderApply.phone}</td>
				<td>
					<c:choose>
						<c:when test="${bdxtOrderApply.orderStatus ==1}">
							未支付
						</c:when>
						<c:when test="${bdxtOrderApply.orderStatus ==2}">
							待支付
						</c:when>
						<c:when test="${bdxtOrderApply.orderStatus ==3}">
							已支付
						</c:when>
						<c:otherwise>
							已取消
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${bdxtOrderApply.days == null}">
							/
						</c:when>
						<c:when test="${bdxtOrderApply.days !=null}">
							${bdxtOrderApply.days}
						</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${bdxtOrderApply.quotePrice == null}">
							/
						</c:when>
						<c:when test="${bdxtOrderApply.quotePrice !=null}">
							${bdxtOrderApply.quotePrice}
						</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${bdxtOrderApply.orderAmount == null}">
							/
						</c:when>
						<c:when test="${bdxtOrderApply.orderAmount !=null}">
							${bdxtOrderApply.orderAmount}
						</c:when>
					</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${bdxtOrderApply.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${bdxtOrderApply.remark}</td>
				<shiro:hasPermission name="bdxt:bdxtActivityOrder:edit"><td>
					<c:if test="${bdxtOrderApply.orderStatus==2}">
						<a href="javascript:frames_fb('${bdxtOrderApply.id}');">支付</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
        //弹出一个iframe层
        function frames_fb(id) {
            var selectHtml = ' <label class="layui-form-label">金额</label> <div style="padding: 5px;"><input type="text" id="orderAmount" name="orderAmount" placeholder="请输入金额"/><div class="layui-form-item layui-form-text"> <label class="layui-form-label">备注</label> <div class="layui-input-block"> <textarea id="beizu" placeholder="请输入内容" class="layui-textarea"></textarea> </div></div></div>';
            layer.open({
                type: 0,
                title: '支付',
                closeBtn: 2,
                btn: ['确定', '取消']
                ,yes: function(index, layero){
                    var orderAmount = $('#orderAmount').val(); //2-审核通过 3-审核不通过
                    var remark = $('#beizu').val();//输入框内容
                    $.ajax({
                        type:"GET",
                        url:"${ctx}/bdxt/bdxtActivityOrder/update?id="+id+"&orderAmount="+orderAmount+"&remark="+remark,
                        //data: data,
                        success:function(data){
                            //刷新当前页
                            showTip(data);
                            layer.closeAll();
                            location.reload();
                        },
                        error:function (data) {
                            showTip(data);
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

	</script>
</body>
</html>