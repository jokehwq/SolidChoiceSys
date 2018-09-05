<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>报名记录管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        ;
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出报名记录吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/bdxt/bdxtUserQuote/exportExcel");
                        $("#searchForm").submit();
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
        });
        var html = "<div style='padding:10px;'>输入价格：<input type='text' id='quotePrice' name='quotePrice' /><br/>" +
                "输入备注：<input type='text' id='remark' name='remark' /><br/>" +
                "</div>";
        function bargain(id) {
             $.jBox.open(html, "添加议价", 600, 300, {
                buttons: {"确定": "ok", "关闭": true},submit: function (v, h, f) {
                     if (f.quotePrice == '') {
                         $.jBox.tip("请输入价格", 'error', { focusId: "quotePrice" }); // 关闭设置 quotePrice 为焦点
                         return false;
                     }
                     if (f.remark == '') {
                         $.jBox.tip("请输入备注", 'error', { focusId: "remark" }); // 关闭设置 remark 为焦点
                         return false;
                     }
                    if (v == "ok") {
                        var quotePrice=$("#quotePrice").val();
                        var remark=$("#remark").val();
                        $.ajax({
                            type:"POST",
                            url:"${ctx}/bdxt/bdxtUserQuote/update",
                            data: {"id":id,"quotePrice":quotePrice,"remark":remark},
                            success:function(data){
                                alert(data);
                                //刷新当前页
                                location.reload();
                            }
                        });
                    }
                }
            });
        };
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <shiro:hasPermission name="bdxt:bdxtActivity:view"><li><a href="${ctx}/bdxt/bdxtActivity/detail?id=${activityId}">活动详情</a></li></shiro:hasPermission>
    <li class="active"><a href="${ctx}/bdxt/bdxtUserQuote/list?bdxtActivityId=${activityId}">报名记录</a></li>
    <shiro:hasPermission name="bdxt:bdxtUserCard:view"><li><a href="${ctx}/bdxt/bdxtUserCard/list?bdxtActivityId=${activityId}">打卡记录</a></li></shiro:hasPermission>
    <shiro:hasPermission name="bdxt:bdxtActivityOrder:view"><li><a href="${ctx}/bdxt/bdxtActivityOrder/applyList?activityId=${activityId}">支付信息</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="bdxtUserQuote" action="${ctx}/bdxt/bdxtUserQuote/" method="post"
           class="breadcrumb form-search">
    <input type="hidden" name="bdxtActivityId" value="${activityId}"/>
    <ul class="ul-form">
        <li><label>用户名称：</label>
            <form:input path="realname" htmlEscape="false" maxlength="255" class="input-medium" placeholder="请输入用户名称"/>
        </li>
        <li><label>用户类型</label>
            <form:select path="userType" class="input-medium">
                <form:option value="">请选择</form:option>
                <form:option value="1">普通会员</form:option>
                <form:option value="2">人才库会员</form:option>
            </form:select>
        </li>
        <li><label>性别</label>
            <form:select path="gender" class="input-medium">
                <form:option value="" >请选择</form:option>
                <form:option value="1">男</form:option>
                <form:option value="2">女</form:option>
            </form:select>
        </li>
        <li><label>报价状态</label>
            <form:select path="quoteStatus" class="input-medium">
                <form:option value="">请选择</form:option>
                <form:option value="1">待审核</form:option>
                <form:option value="2">已议价</form:option>
                <form:option value="3">已通过</form:option>
                <form:option value="4">未通过</form:option>
            </form:select>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <br/>
        <li><label>身高：</label>
            <form:input path="heightleft" htmlEscape="false" maxlength="50" class="input-medium"/>&nbsp;&nbsp;&nbsp;&nbsp;
            ~ &nbsp;
            <form:input path="heightright" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>体重：</label>
            <form:input path="weightleft" htmlEscape="false" maxlength="50" class="input-medium"/>&nbsp;&nbsp;&nbsp;&nbsp;
            ~ &nbsp;
            <form:input path="weightright" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>胸围：</label>
            <form:input path="bustleft" htmlEscape="false" maxlength="50" class="input-medium"/>&nbsp;&nbsp;&nbsp;&nbsp;
            ~ &nbsp;
            <form:input path="bustright" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>腰围：</label>
            <form:input path="waistleft" htmlEscape="false" maxlength="50" class="input-medium"/>&nbsp;&nbsp;&nbsp;&nbsp;
            ~ &nbsp;
            <form:input path="waistright" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>臀围：</label>
            <form:input path="hiplineleft" htmlEscape="false" maxlength="50" class="input-medium"/>&nbsp;&nbsp;&nbsp;&nbsp;
            ~ &nbsp;
            <form:input path="hiplineright" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>电话</th>
        <th>报价</th>
        <th>性别</th>
        <th>状态</th>
        <th>常住地点</th>
        <th>身高</th>
        <th>胸围</th>
        <th>腰围</th>
        <th>臀围</th>
        <th>申请时间</th>
        <th>申请内容</th>
        <shiro:hasPermission name="bdxt:bdxtActivity:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="userQuote" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${userQuote.realname}</td>
            <td>${userQuote.phone}</td>
            <td>${userQuote.quotePrice}</td>
            <td>${userQuote.gender ==1?'男':'女'}</td>
            <td>
                <c:choose>
                    <c:when test="${userQuote.quoteStatus ==1}">
                        待确认
                    </c:when>
                    <c:when test="${userQuote.quoteStatus ==2}">
                        已议价
                    </c:when>
                    <c:when test="${userQuote.quoteStatus ==3}">
                        已通过
                    </c:when>
                    <c:otherwise>
                        未通过
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${userQuote.city}</td>
            <td>${userQuote.height}</td>
            <td>${userQuote.bust}</td>
            <td>${userQuote.waist}</td>
            <td>${userQuote.hipline}</td>
            <td><fmt:formatDate value="${userQuote.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${userQuote.applyContent}</td>
            <shiro:hasPermission name="bdxt:bdxtUser:edit">
                <td>
                    <a href="${ctx}/bdxt/bdxtUser/detail?id=${userQuote.bdxtUserId}">查看</a>
                    <c:if test="${userQuote.quoteStatus!=3}">
                        <a href="#" onclick="return bargain('${userQuote.id}');">议价</a>
                    </c:if>
                    <c:choose>
                        <c:when test="${userQuote.quoteStatus==3}">
                            <a  onclick="javascript:updates('${userQuote.id}','${userQuote.bdxtActivityId}')" style="cursor: pointer">取消订单</a>
                        </c:when>
                        <c:otherwise>
                            <a  onclick="javascript:adds('${userQuote.bdxtUserId}','${userQuote.bdxtActivityId}','${userQuote.id}')" style="cursor: pointer">接受</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
<script type="text/javascript">
    function adds(id_1,id_2,id_3) {
        $.jBox.confirm('是否接受?','系统提示',function(v,h,f){
            if(v=='ok'){
                $.ajax({
                    type:"GET",
                    url:"${ctx}/bdxt/bdxtActivityOrder/saveActivityOrderInfo?bdxtUserId="+id_1+"&activityId="+id_2+"&userQuoteId="+id_3,
                    success:function(data){
                        //刷新当前页
                        showTip('操作成功');
                        location.reload();
                    },
                    error:function (data) {
                        showTip('操作失败');
                        location.reload();
                    }
                });
            }
        },{buttonsFocus:1, closed:function(){
            if (typeof closed == 'function') {
                closed();
            }
        }});
    }

    function updates(id_1,id_2) {
        $.jBox.confirm('是否取消订单?','系统提示',function(v,h,f){
            if(v=='ok'){
                $.ajax({
                    type:"GET",
                    url:"${ctx}/bdxt/bdxtUserQuote/updateStatus?id="+id_1+"&quoteStatus=1&bdxtActivityId="+id_2,
                    success:function(data){
                        //刷新当前页
                        showTip('操作成功');
                        location.reload();
                    },
                    error:function (data) {
                        showTip('操作失败');
                        location.reload();
                    }
                });
            }
        },{buttonsFocus:1, closed:function(){
            if (typeof closed == 'function') {
                closed();
            }
        }});
    }
</script>
</body>
</html>