<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>广告管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
                rules:{
                    material:{required:true},
                    location:{required:true}
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

            //当页面加载完成的时候，自动调用该方法
            window.onload=function(){
                //获得所要回显的值，此处为：100,1001,200,1400
                var checkeds = $("#showdate").val();
                //拆分为字符串数组
                var checkArray =checkeds.split(",");
                //获得所有的复选框对象
                var checkBoxAll = $("input[name='showdatetemp']");
                //获得所有复选框
                for(var i=0;i<checkArray.length;i++){
                    //获取所有复选框对象的value属性，然后，用checkArray[i]和他们匹配，如果有，则说明他应被选中
                    $.each(checkBoxAll,function(j,checkbox){
                        //获取复选框的value属性
                        var checkValue=$(checkbox).val();
                        if(checkArray[i]==checkValue){
                            $(checkbox).attr("checked",true);
                        }
                    })
                }
                var location_id = $('#location_id').val();
                var material_id = $('#material_id').val();

                if(location_id){
                    getLocationList(location_id,material_id)
				}
                getLocation()
            };
			$("#location").change(function (e) {
				var id = $('#location').val();
                var mid = $('#material').val();
                $('#location_id').val(id);
				if(id){
                    getLocationList(id,mid)
				}
            })
            $("#material").change(function (e) {
                var id = $('#material').val();
             	$('#material_id').val(id)
            })
            function getLocation(){
                $.ajax({
                    type:"GET",
                    url:"${ctx}/bdxt/bdxtAd/getlocation",
                    success:function(data){
                        var $disabledResults = $("#location");
                        var optionHtml = '<option value="">请选择</option>';
                        if(data.length > 0){
                            $disabledResults.html('')
                            var location_id = $('#location_id').val()
                            for (var i = 0; i < data.length;i++){
                                if(data[i].id != location_id){
                                    optionHtml += '<option value="'+ data[i].id +'">' + data[i].label + '</option>'
                                }else{
                                    optionHtml += '<option selected value="'+ data[i].id +'">' + data[i].label + '</option>'
                                }
                            }
                        }
                        $disabledResults.append(optionHtml)
                        $disabledResults.select2();
                    },
                    error:function (data) {

                    }
                });
            }
			function getLocationList(id,mid){
                $.ajax({
                    type:"GET",
                    url:"${ctx}/bdxt/bdxtAd/getadlist",
                    data: {id:id,mid:mid},
                    success:function(data){
                        var $disabledResults = $("#material");
                        var optionHtml = '<option value="">请选择</option>';
                        if(data.length > 0){
                            $disabledResults.html('')
                            var material_id = $('#material_id').val()
							for (var i = 0; i < data.length;i++){
								if(data[i].id != material_id){
                                    optionHtml += '<option value="'+ data[i].id +'">' + data[i].title + '</option>'
                                }else{
                                    optionHtml += '<option selected value="'+ data[i].id +'">' + data[i].title + '</option>'
                                    $('#material_id').val(material_id)
								}
							}
						}
                        $disabledResults.append(optionHtml)
                        $disabledResults.select2();
                    },
                    error:function (data) {

                    }
                });
			}
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bdxt/bdxtAd/">广告列表</a></li>
		<li class="active"><a href="${ctx}/bdxt/bdxtAd/form?id=${bdxtAd.id}">广告管理<shiro:hasPermission name="bdxt:bdxtAd:edit">${not empty bdxtAd.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bdxt:bdxtAd:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bdxtAd" action="${ctx}/bdxt/bdxtAd/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="status"/>
		<form:hidden path="showcount"/>
		<form:hidden path="clickcount"/>
		<form:hidden path="material" id="material_id"/>
		<form:hidden path="location" id="location_id"/>
		<sys:message content="${message}"/>
		<form:hidden path="showdate" id="showdate" htmlEscape="false" maxlength="128" class="input-xlarge "/>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>广告位置：</label>
			<div class="controls">
				<select class="js-example-disabled-results input-medium" id="location">
					<option value="">请选择</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>广告素材：</label>
			<div class="controls">
				<select class="js-example-disabled-results input-medium" id="material">
					<option value="">请选择</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">展示时间：</label>
			<div class="box">
					<input type="checkbox" checked="checked" value="1" name="showdatetemp" style="zoom:180%;">星期一
					<input type="checkbox" checked="checked" value="2" name="showdatetemp" style="zoom:180%;">星期二
					<input type="checkbox" checked="checked" value="3" name="showdatetemp" style="zoom:180%;">星期三
					<input type="checkbox" checked="checked" value="4" name="showdatetemp" style="zoom:180%;">星期四
					<input type="checkbox" checked="checked" value="5" name="showdatetemp" style="zoom:180%;">星期五
					<input type="checkbox" checked="checked" value="6" name="showdatetemp" style="zoom:180%;">星期六
					<input type="checkbox" checked="checked" value="7" name="showdatetemp" style="zoom:180%;">星期日

			</div>
			<div style="padding-left: 140px;margin: 20px">
				<span>从</span>
				<input name="showStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${bdxtAd.showStartDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span> - </span>
				<input name="showEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${bdxtAd.showEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>

			</div>
			<div style="padding-left: 147px;margin: 20px 0">
				<span><span class="red">*</span>每天</span>
				<form:input path="showStartTime" value="0" htmlEscape="false" maxlength="2" class="input-xlarge  digits" style="width:163px"/>
				<span> - </span>
				<form:input path="showEndTime" value="24" htmlEscape="false" maxlength="2" class="input-xlarge  digits" style="width:163px"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bdxt:bdxtAd:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="发 布"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/bdxt/bdxtAd'"/>
		</div>
	</form:form>
</body>
</html>