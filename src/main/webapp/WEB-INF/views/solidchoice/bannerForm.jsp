<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>字典信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" charset="utf-8" src="${ctxUeditor}/lang/zh-cn/zh-cn.js"></script>
    <script src="${ctxStatic}/bdxt/imagepreview.js" type="text/javascript"></script>
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
    <li><a href="${ctx}/sys/banner/">字典信息列表</a></li>
    <li class="active"><a href="${ctx}/sys/banner/form?id=${banner.id}">字典信息<shiro:hasPermission
            name="sys:banner:edit">${not empty banner.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="sys:banner:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="banner" action="${ctx}/sys/banner/save" method="post"
           class="form-horizontal" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">描述：</label>

        <div class="controls">
            <form:input path="text" htmlEscape="false" maxlength="100" class="input-xlarge "/>
        </div>
    </div>
<%--    <div class="control-group">
        <label class="control-label">上传图片：</label>

        <div class="controls">
            <form:input path="imgUrl" htmlEscape="false" maxlength="32" class="input-xlarge "/>
        </div>
    </div>--%>

    <div class="control-group">
        <label class="control-label">上传图片：</label>
        <div class="col-sm-9 big-photo">
            <div id="preview">
                <c:choose>
                    <c:when test="${banner.id == '' || banner.id == null}">
                        <c:if test="${banner.imgUrl != null}" >
                            <img  style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${ctxStatic}/images/photo_icon.png" onclick="$('#previewImg').click()" width="300px"/>
                        </c:if>
                        <c:if test="${banner.imgUrl == null}" >
                            <img  style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${ctxStatic}/images/photo_icon.png" onclick="$('#previewImg').click()" width="300px" disabled="disabled" />
                        </c:if>
                    </c:when>

                    <c:when test="${banner.id != '' || banner.id != null}">
                        <c:if test="${banner.imgUrl == '' || banner.imgUrl == null}" >
                            <img  style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${ctxStatic}/images/photo_icon.png" onclick="$('#previewImg').click()" width="300px"/>
                        </c:if>
                        <c:if test="${banner.imgUrl != null}" >
                            <img   style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${banner.imgUrl}" onclick="$('#previewImg').click()">
                        </c:if>
                    </c:when>

                    <%--<c:otherwise>
                        <c:if test="${banner.imgUrl == null}" >
                            <img  style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${ctxStatic}/images/photo_icon.png" onclick="$('#previewImg').click()" width="300px"/>
                        </c:if>
                        <c:if test="${banner.imgUrl != null}" >
                            <img   style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${banner.imgUrl}" onclick="$('#previewImg').click()">
                        </c:if>
                    </c:otherwise>--%>
                </c:choose>
            </div>
            <input id="filepath" type="hidden" name="url" value="${banner.id}" accept="image/gif,image/jpeg">
            <input name="file" type="file" onchange="previewImage(this,400,300)" style="display: none;" id="previewImg">
        </div>
    </div>
 <%--   <div class="control-group">
        <label class="control-label">上级code：</label>
        <div class="controls">
            <form:select path="parentid">
                <form:option  value="" >请选择上级code</form:option>
                <form:options items="${fns:getParentList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
            </form:select>
        </div>
    </div>--%>
    <div class="control-group">
        <label class="control-label">跳转路径：</label>
        <div class="controls">
            <form:textarea path="jumpUrl" htmlEscape="false" maxlength="200" class="input-xlarge "/>
        </div>
    </div>
<%--    <div class="control-group">
        <label class="control-label">排序：</label>
        <div class="controls">
            <form:input path="sort" htmlEscape="false" maxlength="2" class="input-xlarge  digits"/>
        </div>
    </div>--%>
    <div class="control-group">
        <label class="control-label">状态：</label>
        <div class="controls">
            <form:select path="type" class="input-medium">
                <form:option value="1">启用</form:option>
                <form:option value="2">停用</form:option>
            </form:select>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="sys:banner:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                              value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回"
               onclick="window.location.href='${ctx}/sys/banner'"/>
    </div>
</form:form>
</body>
</html>