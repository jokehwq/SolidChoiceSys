<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动信息管理</title>
	<meta name="decorator" content="default"/>
	<!-- 上传图片并显示-----引入js -->
	<script src="${ctxStatic}/bdxt/imagepreview.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
		<li><a href="${ctx}/bdxt/bdxtActivity/">活动信息列表</a></li>
		<li class="active"><a href="${ctx}/bdxt/bdxtActivity/form?id=${bdxtActivity.id}">活动信息<shiro:hasPermission name="bdxt:bdxtActivity:edit">${not empty bdxtActivity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bdxt:bdxtActivity:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtActivity" action="${ctx}/bdxt/bdxtActivity/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">活动主题：</label>
			<div class="controls">
				<form:input path="activityName" htmlEscape="false" maxlength="255" class="input-xlarge" placeholder="请输入活动主题"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动类型：</label>
			<div class="controls">
				<form:select path="activityType" class="input-xlarge ">
					<form:option value="" label="">请选择</form:option>
					<form:options items="${fns:queryDictListByCondition('ACTIVITY_CODE','活动类型')}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动时间：</label>
			<div class="controls">
				<input name="activityStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${bdxtActivity.activityStartTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" placeholder="开始时间"/>&nbsp;&nbsp;&nbsp;&nbsp;
				~ &nbsp;&nbsp;&nbsp;&nbsp;
				<input name="activityEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${bdxtActivity.activityEndTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" placeholder="结束时间"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作地址：</label>
			<div class="controls">
				<form:select path="workAddress"  class="input-medium">
					<form:option  value="" >请选择</form:option>
					<form:options items="${fns:getDistrictList('3000336','中国')}" itemLabel="districtname" itemValue="districtname" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招募人数：</label>
			<div class="controls">
				<form:input path="recruitNum" htmlEscape="false" maxlength="11" class="input-xlarge  digits" placeholder="请填写招募人数"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人均预算：</label>
			<div class="controls">
				<form:input path="perBugget" htmlEscape="false" class="input-xlarge " placeholder="请填写人均预算"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别要求：</label>
			<div class="controls">
				<form:select path="genderReq" class="input-medium">
				<form:option value="1">男</form:option>
				<form:option value="2">女</form:option>
				<form:option value="3">不限</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身高要求：</label>
			<div class="controls">
				<form:select path="heightReqLeft" class="input-medium">
					<form:option value="">请选择</form:option>
					<form:option value="不限">不限</form:option>
					<form:option value="155">155</form:option>
					<form:option value="160">160</form:option>
					<form:option value="165">165</form:option>
					<form:option value="170">170</form:option>
					<form:option value="175">175</form:option>
					<form:option value="180">180</form:option>
				</form:select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				~ &nbsp;&nbsp;&nbsp;&nbsp;
				<form:select path="heightReqRight" class="input-medium">
					<form:option value="">请选择</form:option>
					<form:option value="不限">不限</form:option>
					<form:option value="155">155</form:option>
					<form:option value="160">160</form:option>
					<form:option value="165">165</form:option>
					<form:option value="170">170</form:option>
					<form:option value="175">175</form:option>
					<form:option value="180">180</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否报销差旅费：</label>
			<div class="controls">
				<form:select path="isReimburseTravel" class="input-medium">
					<form:option value="1">是</form:option>
					<form:option value="2">否</form:option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招募内容：</label>
			<div class="controls">
				<form:textarea path="recruitRemark" htmlEscape="false" class="input-xlarge " placeholder="请填写招募内容"/>
			</div>
		</div>
	  <div class="control-group">
			<label class="control-label"><span class="red">*</span>参考样例：</label>
			<div class="col-sm-9 big-photo">
				<div id="preview">
					<c:choose>
						<c:when test="${bdxtActivity.id == '' || bdxtActivity.id == null}">
							<c:if test="${bdxtActivity.referenceSampleUrl == null}" >
								<img  style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${ctxStatic}/images/photo_icon.png" onclick="$('#previewImg').click()" width="300px" disabled="disabled" />
							</c:if>
						</c:when>
						<c:otherwise>
							<img   style="margin-left: 20px;width: 70px;" id="imghead" border="0" src="${bdxtActivity.referenceSampleUrl}" onclick="$('#previewImg').click()">
						</c:otherwise>
					</c:choose>
				</div>
				<input id="filepath" type="hidden" name="url" value="${bdxtActivity.id}" accept="image/gif,image/jpeg">
				<input name="file" type="file" onchange="previewImage(this,400,300)" style="display: none;" id="previewImg">
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bdxt:bdxtActivity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtActivity'"/>
		</div>
	</form:form>
</body>
</html>