<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>字典信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(function () {
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/bdxt/bdxtDict/">字典信息列表</a></li>
    <li class="active"><a href="${ctx}/bdxt/bdxtDict/form?id=${bdxtDict.id}">字典信息<shiro:hasPermission
            name="bdxt:bdxtDict:edit">${not empty bdxtDict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="bdxt:bdxtDict:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="bdxtDict" action="${ctx}/bdxt/bdxtDict/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">名称：</label>

        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">编码：</label>

        <div class="controls">
            <form:input path="code" htmlEscape="false" maxlength="32" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">上级code：</label>
        <div class="controls">
            <form:select path="parentid">
                <form:option  value="" >请选择上级code</form:option>
                <form:options items="${fns:getParentList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">描述：</label>
        <div class="controls">
            <form:textarea path="remark" htmlEscape="false" maxlength="200" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">排序：</label>
        <div class="controls">
            <form:input path="sort" htmlEscape="false" maxlength="2" class="input-xlarge  digits"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">状态：</label>
        <div class="controls">
            <form:select path="status" class="input-medium">
                <form:option value="1">启用</form:option>
                <form:option value="2">停用</form:option>
            </form:select>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="bdxt:bdxtDict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                              value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回"
               onclick="window.location.href='${ctx}/bdxt/bdxtDict'"/>
    </div>
</form:form>
</body>
</html>