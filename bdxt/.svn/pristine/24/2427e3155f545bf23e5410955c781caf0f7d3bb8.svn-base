<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户登录信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        $(document).ready(function () {
                 $("#btnExport").click(function () {
                     top.$.jBox.confirm("确认要导出用户记录吗？", "系统提示", function (v, h, f) {
                         if (v == "ok") {
                             $("#searchForm").attr("action", "${ctx}/bdxt/bdxtUser/exportExcel");
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
    <li class="active"><a href="${ctx}/bdxt/bdxtUser/">用户列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="bdxtUser" action="${ctx}/bdxt/bdxtUser/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>用户名称：</label>
            <form:input path="realName" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>用户类型：</label>
            <form:select path="userType" class="input-medium">
                <form:option value=""></form:option>
                <form:option value="1">普通会员</form:option>
                <form:option value="2">人才库会员</form:option>
            </form:select>
        </li>
        <li><label>性别</label>
            <form:select path="gender" class="input-medium">
                <form:option value=""></form:option>
                <form:option value="1">男</form:option>
                <form:option value="2">女</form:option>
            </form:select>
        </li>
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
        <th>昵称</th>
        <th>用户名</th>
        <th>联系方式</th>
        <th>类型</th>
        <th>性别</th>
        <th>城市</th>
        <th>当前积分</th>
        <th>职业</th>
        <th>身高</th>
        <th>胸围</th>
        <th>腰围</th>
        <th>臀围</th>
        <th>创建时间</th>
        <th>最后登录时间</th>
        <shiro:hasPermission name="bdxt:bdxtUser:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="bdxtUser" varStatus="status">
        <tr>
            <td>
                    ${status.count}
            </td>
            <td>
                    ${bdxtUser.nickname}
            </td>
            <td>
                    ${bdxtUser.realName}
            </td>
            <td>
                    ${bdxtUser.phone}
            </td>
            <td>
                    ${bdxtUser.userType ==1?'普通会员':'人才库会员'}
            </td>
            <td>
                    ${bdxtUser.gender ==1?'男':'女'}
            </td>
            <td>
                    ${bdxtUser.city}
            </td>
            <td>
                    ${bdxtUser.integral}
            </td>
            <td>
                    ${bdxtUser.job}
            </td>
            <td>
                    ${bdxtUser.height}
            </td>
            <td>
                    ${bdxtUser.bust}
            </td>
            <td>
                    ${bdxtUser.waist}
            </td>
            <td>
                    ${bdxtUser.hipline}
            </td>
            <td>
                <fmt:formatDate value="${bdxtUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${bdxtUser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <shiro:hasPermission name="bdxt:bdxtUser:edit">
                <td>
                    <a href="${ctx}/bdxt/bdxtUser/detail?id=${bdxtUser.id}">查看</a>
                    <a href="${ctx}/bdxt/bdxtUser/queryIntegralRecord?bdxtUserId=${bdxtUser.id}">积分明细</a>
                    <a href="${ctx}/bdxt/bdxtUser/delete?id=${bdxtUser.id}"
                       onclick="return confirmx('确认要删除该用户登录信息吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>