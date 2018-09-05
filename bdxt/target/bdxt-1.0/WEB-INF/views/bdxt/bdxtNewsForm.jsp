<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯信息管理</title>
	<meta name="decorator" content="default"/>
	<!-- 上传图片并显示-----引入js -->
	<script src="${ctxStatic}/bdxt/imagepreview.js" type="text/javascript"></script>
	<!-- 配置文件 -->
	<script type="text/javascript" src="${ctxUeditor}/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="${ctxUeditor}/ueditor.all.js"></script>
	<%--<link rel="stylesheet" type="text/css" href="${ctxUeditor}/themes/default/css" />--%>
	<script type="text/javascript" charset="utf-8" src="${ctxUeditor}/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
        function myclick(n){
            if(n==1){
                $("#status").val(5);
			}
            if(n==2){
                $("#status").val(0);
            }
        }
		$(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
                   alert(form.id());
				},
                rules:{
                    showStartTime:{required:true},
                    showEndTime:{required:true}
                },
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
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
		<li><a href="${ctx}/bdxt/bdxtNews/">资讯信息列表</a></li>
		<li class="active"><a href="${ctx}/bdxt/bdxtNews/form?id=${bdxtNews.id}">资讯信息<shiro:hasPermission name="bdxt:bdxtNews:edit">${not empty bdxtNews.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bdxt:bdxtNews:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtNews" action="${ctx}/bdxt/bdxtNews/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<input id="newsContents" name="newsContents" type="hidden" value="${bdxtNews.newsContent}"/>
		<form:hidden path="id"/>
		<form:hidden path="newsContentUrl"/>
		<form:hidden path="newsReadsNum"/>
		<form:hidden path="newsCommentNum"/>
		<form:hidden path="newsPublishType"/>
		<form:hidden path="status" id="status"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>资讯标题：</label>
			<div class="controls">
				<form:input path="newsTitle" htmlEscape="false" maxlength="255" class="input-xlarge " style="width:800px" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="newsType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('bdxt_news')}" itemLabel="label" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><span class="red">* </span>资讯封面图：</label>
			<div class="col-sm-9 big-photo">
				<div id="preview">
					<c:choose>
						<c:when test="${bdxtNews.id == '' || bdxtNews.id == null}">
							<c:if test="${bdxtNews.newsImageUrl != null}" >
								<img  style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${ctxStatic}/images/photo_icon.png" onclick="$('#previewImg').click()" width="300px"/>
							</c:if>
							<c:if test="${bdxtNews.newsImageUrl == null}" >
								<img  style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${ctxStatic}/images/photo_icon.png" onclick="$('#previewImg').click()" width="300px" disabled="disabled" />
							</c:if>
						</c:when>
						<c:otherwise>
							<img   style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${bdxtNews.newsImageUrl}" onclick="$('#previewImg').click()">
						</c:otherwise>
					</c:choose>
				</div>
				<input id="filepath" type="hidden" name="url" value="${bdxtNews.id}" accept="image/gif,image/jpeg">
				<input name="file" type="file" onchange="previewImage(this,400,300)" style="display: none;" id="previewImg">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls" style="width:80%">
				<textarea id="newsEditor" name="newsContent" style="height: 80%"> </textarea>
				<script type="text/javascript">

                     var UM = UE.getEditor('newsEditor', {

                        autoHeightEnabled: true,

                        autoFloatEnabled: true,

                        initialFrameWidth: 1220,

                        initialFrameHeight:450

                    });
                    var content = $('#newsContents').val();
                    if(content != null){
                        //渲染的时候先把内容置空  然后再重新放入数据库存放的content值
                        UM.ready(function() {
                           /* //异步回调 重新渲染 @ricky
                            UM.execCommand('insertHtml',content);*/
                            UM.setContent(content);
                        });
					}
                    // var content = UE.getPlainTxt();//content就是编辑器的带格式的内容
				</script>

			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bdxt:bdxtNews:edit"><input id="btnSubmit" class="btn btn-primary" onclick='myclick(2)' type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<c:if test="${bdxtNews.id == null}" >
				<shiro:hasPermission name="bdxt:bdxtNews:edit"><input id="btnSubmit_temp" class="btn btn-primary" onclick='myclick(1)' type="submit" value="保 存 草 稿"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtNews'"/>
		</div>
	</form:form>
</body>
</html>