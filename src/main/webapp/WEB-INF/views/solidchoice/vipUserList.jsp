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
    <li class="active"><a href="${ctx}/solidChoice/vipUser/">会员列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="vipUser" action="${ctx}/solidChoice/vipUser/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>会员名称：</label>
            <form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>会员openId：</label>
            <form:input path="openId" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>姓名</th>
        <th>昵称</th>
        <th>性别</th>
        <th>手机号</th>
        <th>邮箱</th>
        <th>微信id</th>
        <th>会员状态</th>
        <th>地区</th>
        <th>创建时间</th>
        <th>最后登录时间</th>
        <shiro:hasPermission name="solidChoice:vipUser:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="vipUser">
        <tr>
            <td>
                    ${vipUser.name}
            </td>
            <td>
                    ${vipUser.nickName}
            </td>
            <td>
                    ${vipUser.gender ==1?'男':'女'}
            </td>
            <td>
                    ${vipUser.phone}
            </td>
            <td>
                    ${vipUser.email}
            </td>
            <td>
                    ${vipUser.openId}
            </td>
            <td>
                    ${vipUser.userStatus ==1?'启用':'禁用'}
            </td>

            <td>
                    ${vipUser.area}
            </td>
            <td>
                <fmt:formatDate value="${vipUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${vipUser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>

            <shiro:hasPermission name="bdxt:bdxtUser:edit">
                <td>
                    <a href="${ctx}/solidChoice/vipUser/detail?id=${vipUser.id}">查看</a>
                    <a href="${ctx}/solidChoice/vipUser/queryIntegralRecord?id=${vipUser.id}">积分明细</a>
                    <c:choose>
                        <c:when test="${vipUser.userStatus ==1}">
                            <a href="${ctx}/solidChoice/vipUser/updateUserStatus?id=${vipUser.id}&userStatus=2
"
                               onclick="return confirmx('确认要禁用该用户吗？', this.href)">禁用</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${ctx}/solidChoice/vipUser/updateUserStatus?id=${vipUser.id}&userStatus=1"
                               onclick="return confirmx('确认要启用该用户吗？', this.href)">启用</a>
                        </c:otherwise>
                    </c:choose>

                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>