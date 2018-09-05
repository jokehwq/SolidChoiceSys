<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品管理管理</title>
	<meta name="decorator" content="default"/>
	<!-- 上传图片并显示-----引入js -->
	<style>
		.img-box-show{
			padding-left: 180px;
		}
		.img-item {
			display: inline-block;
			width: 120px;
			margin-right: 20px;
			line-height: 120px;
			text-align: center;
			padding: 20px 10px;
			box-sizing: border-box;
		}

		.img-item img {
			width: auto;
			height: auto;
		}
		.delete-img1 {
			background-image: url(${ctxStatic}/images/delete2.png);
			background-repeat: no-repeat;
			background-size: 20px;
			background-position: 80px 0px;
			cursor: pointer;
		}
	</style>
	<script src="${ctxStatic}/bdxt/imagepreview.js" type="text/javascript"></script>
	<!-- 配置文件 -->
	<script type="text/javascript" src="${ctxUeditor}/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="${ctxUeditor}/ueditor.all.js"></script>
	<%--<link rel="stylesheet" type="text/css" href="${ctxUeditor}/themes/default/css" />--%>
	<script type="text/javascript" charset="utf-8" src="${ctxUeditor}/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		var img_list = [];
		var img_show_list = [];
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

            $("#uploaderInput").bind("change",function(e){
                //可以做一些其他的事，比如图片预览
				if(this.files.length > 0){
                    for (var i = 0;i < this.files.length;i ++){
                        img_list.push(this.files[i])
					}
				}
                showImg(img_list)
            });
            function bingEvent() {
                $('.delete-img1').bind("click", function (e) {
                    var windowURL = window.URL || window.webkitURL;
                    var _this = $(e.currentTarget)
                    var deleteImg = _this.find('img').attr('src');
                    var imgList = $('#img').val()
                    var newImgList = [];
                    var imgHtml = '';
                    if (imgList) {
                        try {
                            imgList = JSON.parse(imgList)
                        } catch (e) {
                            imgList = []
                        }
                    }
                    debugger
                    if(imgList.length == 0 || imgList == ""){
                        imgList = img_show_list
                    }
                    if (imgList.length > 0) {
                        for (var i = 0; i < imgList.length; i++) {
                            if(imgList[i] != deleteImg){
                                newImgList.push(imgList[i])
                                imgHtml += '<div class="img-item delete-img1"> <img src="' + imgList[i] + '" alt=""> </div>';
                            }
                        }
                        $('#img').val(JSON.stringify(newImgList))
                        $('.img-box-show').html('');
                        $('.img-box-show').append(imgHtml);
                        bingEvent()
                    }

                })
            }
            function showImg(list) {
                var _this = this;
				var box = '<div class="img-item"> <img src="" alt=""> </div>';
                var windowURL = window.URL || window.webkitURL;
                var len = list.length;
                img_show_list = [];
				if(len > 0){
                    for (var i = 0; i < len ; i++){
                        var item = list[i];
                        var dataURL = windowURL.createObjectURL(item);
                        img_show_list.push(dataURL)
					}
				}
				if(img_show_list.length > 0){
				    var imgHtml = '';
                    var product_img_list = $('#img').val()
                    var imgArr = [];
                    if(product_img_list){
                        try {
//                            imgArr = JSON.parse(product_img_list);
                            img_show_list = img_show_list.concat(imgArr)
                        }catch (e){
                            imgArr = []
                        }
                    }
                    for (var j = 0; j < img_show_list.length ; j++){
                        var img = img_show_list[j];
						imgHtml += '<div class="img-item delete-img1"> <img src="' + img + '" alt=""> </div>';
                    }
                    $('.img-box-show').html('');
                    $('.img-box-show').append(imgHtml);
				}
                bingEvent();
            }
            function loadImgList() {
				var product_img_list = $('#img').val()
				var imgArr = [];
				if(product_img_list){
				    try {
                        imgArr = JSON.parse(product_img_list);
					}catch (e){
				        imgArr = []
					}
				}
				if(imgArr.length > 0){
                    var imgHtml = '';
                    for (var j = 0; j < imgArr.length ; j++){
                        var img = imgArr[j];
                        imgHtml += '<div class="img-item delete-img1"> <img src="' + img + '" alt=""> </div>';
                    }
                    $('.img-box-show').append(imgHtml);
                    bingEvent()
				}
            }
            loadImgList()
        });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bdxt/bdxtProduct/">商品列表</a></li>
		<li class="active"><a href="${ctx}/bdxt/bdxtProduct/form?id=${bdxtProduct.id}">商品管理<shiro:hasPermission name="bdxt:bdxtProduct:edit">${not empty bdxtProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bdxt:bdxtProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtProduct" action="${ctx}/bdxt/bdxtProduct/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<form:hidden path="code" />
		<form:hidden path="carousel" />
		<form:hidden path="status"/>
		<form:hidden path="isdel"/>
		<form:hidden path="img"/>
		<form:hidden path="contentimg"/>
		<input id="contents" name="contents" type="hidden" value="${bdxtProduct.content}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参考价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-medium ">
					<form:option  value="" >请选择</form:option>
					<form:options items="${fns:getDictList('bdxt_pro_type')}" itemLabel="label" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所需积分：</label>
			<div class="controls">
				<form:input path="scores" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存：</label>
			<div class="controls">
				<form:input path="count" htmlEscape="false" maxlength="8" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="8" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls" style="position: relative;height: 33px">
				<input id="uploaderInput" name="files" class="input-xlarge" type="file"  multiple  style="opacity: 0;position: absolute;z-index: 999;width: 100%"/>

				<div style="height: 30px;position: absolute;top: 0px;display: flex;justify-content: flex-end;
    align-items: center;width: 27.5%;border: 1px solid #ccc;border-radius: 5px;padding-right: 10px;z-index: 1;cursor: pointer">选择</div>
			</div>
			<div class="img-box-show">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:input path="explains" htmlEscape="false" maxlength="600" class="input-xlarge " style="width:1200px"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图文详情：</label>
			<div class="controls" style="width:80%">
				<textarea id="newsEditor" name="content" style="height: 80%"> </textarea>
				<script type="text/javascript">

                    var UM = UE.getEditor('newsEditor', {

                        autoHeightEnabled: true,

                        autoFloatEnabled: true,

                        initialFrameWidth: 1220,

                        initialFrameHeight:450

                    });
                    var content = $('#contents').val();
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
			<shiro:hasPermission name="bdxt:bdxtProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="发 布"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtProduct'"/>
		</div>
	</form:form>
</body>
</html>