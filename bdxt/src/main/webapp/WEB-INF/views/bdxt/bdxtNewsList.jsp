<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>资讯信息管理</title>
    <meta name="decorator" content="default"/>
</head>
<script type="text/javascript">
    function page(n, s) {
        if (n) $("#pageNo").val(n);
        if (s) $("#pageSize").val(s);
        $("#searchForm").attr("action", "${ctx}/bdxt/bdxtNews/list");
        $("#searchForm").submit();
        return false;
    }
</script>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/bdxt/bdxtNews/">资讯信息列表</a></li>
    <shiro:hasPermission name="bdxt:bdxtNews:edit">
        <li><a href="${ctx}/bdxt/bdxtNews/form">资讯信息添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="bdxtNews" action="${ctx}/bdxt/bdxtNews/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>资讯标题：</label>
            <form:input path="newsTitle" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>资讯分类：</label>
            <form:select path="newsType" class="input-xlarge " style="width:200px">
                <form:option value="" label="请选择"/>
                <form:options items="${fns:getDictList('bdxt_news')}" itemLabel="label" itemValue="id" htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>状态：</label>
            <form:select path="status" class="input-medium">
                <form:option value="" label="请选择"/>
                <form:option value="0" label="已发布"/>
                <form:option value="1" label="待审核"/>
                <form:option value="2" label="审核通过"/>
                <form:option value="3" label="审核不通过"/>
                <form:option value="4" label="已下架"/>
                <form:option value="5" label="草稿"/>
            </form:select>
        </li>
        <li><label>来源：</label>
            <form:select path="newsPublishType" class="input-medium">
                <form:option value="" label="请选择"/>
                <form:option value="1" label="投稿"/>
                <form:option value="2" label="编辑"/>
            </form:select>
        </li>
        <li><label>创建日期：</label>
            <input name="beginCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bdxtNews.beginCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
            <input name="endCreateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${bdxtNews.endCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
        <%--<th><input id="selectall" type="checkbox"></th>--%>
        <th>序号</th>
        <th>资讯标题</th>
        <th>分类</th>
        <th>来源</th>
        <th>发布时间</th>
        <th>状态</th>
        <th>阅读数</th>
        <th>评论数</th>
        <shiro:hasPermission name="bdxt:bdxtNews:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="bdxtNews" varStatus="status">
        <tr>
            <input id="newsId" name="newsId" type="hidden" value="${bdxtNews.id}"/>
                <%--<td><input type="checkbox" name="check" value="${bdxtNews.id}" onchange="checkselect(this)"></td>--%>
            <td>${status.count}</td>
            <td><a href="${ctx}/bdxt/bdxtNews/form?id=${bdxtNews.id}">
                    ${bdxtNews.newsTitle}
            </a></td>
            <td>
                    ${bdxtNews.typeName}
            </td>
            <td>
                <c:if test="${bdxtNews.newsPublishType == 1}">
                    编辑
                </c:if>
                <c:if test="${bdxtNews.newsPublishType == 2}">
                    投稿
                </c:if>
            </td>
            <td>
                <fmt:formatDate value="${bdxtNews.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <c:if test="${bdxtNews.status == 0}">
                    已发布
                </c:if>
                <c:if test="${bdxtNews.status == 1}">
                    待审核
                </c:if>
                <c:if test="${bdxtNews.status == 2}">
                    审核通过
                </c:if>
                <c:if test="${bdxtNews.status == 3}">
                    审核不通过
                </c:if>
                <c:if test="${bdxtNews.status == 4}">
                    已下架
                </c:if>
                <c:if test="${bdxtNews.status == 5}">
                    草稿
                </c:if>

            </td>
            <td>
                    ${bdxtNews.newsReadsNum}
            </td>
            <td>
                    ${bdxtNews.newsCommentNum}
            </td>
            <shiro:hasPermission name="bdxt:bdxtNews:edit">
                <td>
                    <a href="${ctx}/bdxt/bdxtNews/form?id=${bdxtNews.id}">编辑</a>
                    <a href="javascript:frames('${bdxtNews.id}');">评论管理</a>
                    <c:if test="${bdxtNews.status == 0}">
                        <a href="${ctx}/bdxt/bdxtNews/operation?id=${bdxtNews.id}&status=4">下架</a>
                    </c:if>
                    <c:if test="${bdxtNews.status == 1}">
                        <a href="javascript:frames_fb('${bdxtNews.id}');">审核</a>
                    </c:if>
                    <c:if test="${bdxtNews.status > 1}">
                        <a href="${ctx}/bdxt/bdxtNews/operation?id=${bdxtNews.id}&status=0">发布</a>
                    </c:if>

                    <a href="${ctx}/bdxt/bdxtNews/delete?id=${bdxtNews.id}"
                       onclick="return confirmx('确认要删除该资讯信息吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript">
    //弹出一个iframe层
    function frames(newsId) {
        layer.open({
            type: 2,
            title: '资讯评论列表',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['800px', '520px'],
            content: '${ctx}/bdxt/bdxtComment?newsId=' + newsId
        });
    }


    //弹出一个iframe层
    function frames_fb(newsId) {
//        layer.prompt({
//                title: '审核结果',
//                formType: 2
//            },
//            function (text, index) {
//                layer.close(index);
//            });
        var selectHtml = '<div style="padding: 5px;"><select id="selectId" name="interest" lay-filter="aihao"><option selected value="2">审核通过</option> <option value="3">审核不通过</option> </select><div class="layui-form-item layui-form-text"> <label class="layui-form-label">备注</label> <div class="layui-input-block"> <textarea id="beizu" placeholder="请输入内容" class="layui-textarea"></textarea> </div></div></div>';
        layer.open({
            type: 0,
            title: '审核结果',
            closeBtn: 2,
            btn: ['确定', '取消']
            ,yes: function(index, layero){
                var selectd = $('#selectId').val(); //2-审核通过 3-审核不通过
                var inpd = $('#beizu').val();//输入框内容
                /*var data = {
                    status:selectd,
                    explain:inpd
                }*/
                $.ajax({
                    type:"GET",
                    url:"${ctx}/bdxt/bdxtNews/audit?id="+newsId+"&status="+selectd+"&explains="+inpd,
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
    $("#btnExport").click(function () {
        top.$.jBox.confirm("确认要导出记录吗？", "系统提示", function (v, h, f) {
            if (v == "ok") {
                $("#searchForm").attr("action", "${ctx}/bdxt/bdxtNews/exportExcel");
                $("#searchForm").submit();
            }
        }, {buttonsFocus: 1});
        top.$('.jbox-body .jbox-icon').css('top', '55px');
    });

</script>
</body>
</html>