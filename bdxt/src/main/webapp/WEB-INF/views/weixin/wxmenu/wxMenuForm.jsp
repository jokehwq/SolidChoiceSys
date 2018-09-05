<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%-- <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.js" type="text/javascript"></script> --%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
 	   var menuparentId = "${wxMenu.parent.id}";
 	   var menutype = "${wxMenu.menutype}";
 	   var menuid = "${wxMenu.id}";
		$(function() {			  
			$("#name").focus();
			
			if(menuparentId!=''){
				
			var rules = {
					     name:{required:true,rangelength:[1,7]},
		                 menutype:{required:true},
					     url:{url:true,rangelength:[0,1024]},
					     menukey:{rangelength:[0,64]}
			  }
			}else{
				 if(menuid.trim()!=''&&menutype.trim()==''){
				 $('#menuType').attr("disabled",true); 
				 }
				 rules = {
					     name:{required:true,rangelength:[1,4]},
		                 menutype:{required:true},
					     url:{url:true,rangelength:[0,1024]},
		                 menukey:{rangelength:[0,64]}
			  }		
			}
			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
	            rules:rules,
	            messages:{
	                name:{
	                    required:"必填",
	                    rangelength: $.format("菜单标题最小长度:{0}, 最大长度:{1}。")
	                },
	            menutype:{
	            	required:"必选"
	            },	
	                url:{
	                	url:"请输入正确的跳转地址",
	                	rangelength: $.format("跳转地址最大长度:{1}。")
	                },
	            menukey:{
	            	rangelength: $.format("菜单回复最大长度:{1}。")
	            }
	            
	            },
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			  	
			    menuid = "${wxMenu.id}";
			    menutype = $('#menuType option:selected').val();
			    if(menutype == 'click'){
					   $("#menuKeyword").show();
				   }else if(menutype == 'view'){
					   $("#menuUrl").show(); 
				   }		    
			    
			    $(".thide").hide(); 
			    
			    $('#chooseMsgType').on('click',function(e){
					$('.msgPop').show();
				});
			    $('#chooseMsgTypeCancel').on('click',function(e){
					$('.msgPop').hide();
				});
			    $('#chooseMsgTypeSave').on('click',function(e){
			    	var content = $('input:radio:checked').val();
					var id = $('input:radio:checked').data('id');
					$('#msgId').val(content);
					$('#msgId').attr('data-id',id);
					$('#keyword').val(content);
					$('.msgPop').hide();
					
				});
			    
		});
		
		
		
		   function chooseMenuEvent(){
			  menutype = $('#menuType option:selected').val();
			  $(".hide").hide(); 
			   if(menutype == 'click'){
				   $("#menuKeyword").show();
			   }else if(menutype == 'view'){
				   $("#menuUrl").show(); 
			   }
		   }
			 function getValue(page,size){
				    var accountid=$('#accountid').val();
			        var str = $('#msgPopSelect-type').val();   //获得选中的值
			        var page = page || 1;
			        var size = size || 10;
			       
			        $.ajax({
			            type:"post",
			            dataType:"json",
			            url:"${ctx}/wxkeyword/wxKeyword/getKeyword",
			            data:{'accountid':accountid,page:page,pagesize:size},
			            success:function(data){
			               var listHtmlArr = [];
			               var listHtml = '';
			               var pagination =  data.html;
			               $('.pagination').html(pagination);
			               if(data && data.list && data.list.length > 0){
			            	   for(var i = 0,len = data.list.length; i < len; i++){
			            		   console.log(data);
				            	   listHtmlArr.push('<tr>');
				            		   listHtmlArr.push('<td><input type="radio" name="type" data-id="' + data.list[i].id + '"  value="' + data.list[i].keyword + '"/></td>');
				            		   listHtmlArr.push('<td>'+data.list[i].keyword+'</td>');
				            	   listHtmlArr.push('</tr>');
				               }
				               listHtml = listHtmlArr.join('');
			               }else{
			            	   listHtml = '';
			               }
			               $('#msgPopListContent').html(listHtml);
			            }
			        });
			    }
			 function page(page,size){
				 var _this = this;
				 if(page && size){
					 _this.getValue(page,size);
				 }
			 }		   
	</script>
	<style>
	.chooseMsgType{
			width: 90px;
		    height: 30px;
		    background: #3daae9;
		    padding: 5px;
		    text-align: center;
		    line-height: 20px;
		    border-radius: 5px;
		    color: #fff;
		    margin: 10px;
		    margin-left: 75px;
		   	border:none;
		}
		.msgPopBtn{
			width: 90px;
		    height: 30px;
		    background: #3daae9;
		    padding: 5px;
		    text-align: center;
		    line-height: 20px;
		    border-radius: 5px;
		    color: #fff;
		   	border:none;
		}
		.msgPopBtnl{
			margin-left:50px;
			background:#546a79;
		}
		.msgPop{
			width: 100%;
		    height: 100%;
		    background: rgba(58,63,81,0.5);
		    position: absolute;
		    top: 0px;
		    right: 0px;
		    left: 0px;
		    bottom: 0px;
		    display:none;
		}
		.msgPopContent{
			width: 80%;
		    height: 500px;
		    background-color: #fff;
		    position: absolute;
		    left: 50%;
		    top: 50%;
		    margin-left: -40%;
		    margin-top: -250px;
		    border-radius:10px;
		}
		.msgPopBtns{
		    position: absolute;
		    left: 0px;
		    right: 0px;
		    bottom: 20px;
		    margin: 0 auto;
		    text-align: center;
		}
		.msgPopBtn button{
			margin:0px;
		}
		.msgPopSelect{
			margin:25px;
		}
		.pagination{
			position: absolute;
		    bottom: 50px;
		    right: 0px;
    	}
    	.table th, .table td {
    		text-align:center;
    	}
    	#msgId,.msgId1{
    		margin-top:10px;
    	}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/wxmenu/wxMenu/?accountId=${accountId}">菜单列表</a></li>
		<li class="active"><a href="${ctx}/wxmenu/wxMenu/form?id=${wxMenu.id}&parent.id=${wxMenuparent.id}">菜单<shiro:hasPermission name="wxmenu:wxMenu:edit">${not empty wxMenu.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="wxmenu:wxMenu:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxMenu" action="${ctx}/wxmenu/wxMenu/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="accountid" id='accountid' value="${accountId}"/>
		<sys:message content="${errormessage}" type="error"/>		
		<div class="control-group ">
			<label class="control-label">菜单类型：</label>
			<div class="controls">
				<form:select id="menuType" path="menutype" class="input-xlarge " onchange="chooseMenuEvent()" >
					<%-- <form:option value="" label=""/> --%>
					<form:options items="${fns:getDictList('wx_menu_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">菜单名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="60" class="input-xlarge "/>
			</div>
		</div>
		<div id="menuUrl" class="control-group hide"  style="display:none;">
			<label class="control-label">url：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
	    <div class="control-group">
			<label class="control-label">上级父级菜单:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${wxMenu.parent.id}" labelName="parent.name" labelValue="${wxMenu.parent.name}"
					title="父级菜单" url="/wxmenu/wxMenu/treeData" extId="${wxMenu.id}" cssClass="" allowClear="false" disabled="disabled" hideBtn="true"/>
			</div>
		</div>
		<div class="controls">
				<input type="hidden" name="menukey" id='keyword' value="${wxMenu.menukey}"/>
			</div> 
		<div id="menuKeyword" class="control-group hide"  style="display:none;">
			<!-- <label class="control-label">回复关键词：</label>
			 <div class="controls">
				<form:input path="menukey" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div> -->
				<label class="control-label msgId1">关键字：</label>
			<div class="controls">
				<input id="msgId" type='text' maxlength="255" value="${wxMenu.menukey}"  readonly class="input-xlarge "/>
			</div> 
			<button class='chooseMsgType' id='chooseMsgType' type="button" onclick="getValue()">选择关键字</button>
		</div>
		
		<div class='msgPop'>
			<div class='msgPopContent'>
				<div class='msgPopSelect'>
				</div>
				<div class='msgPopList'>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>关键字</th>
			</tr>
		</thead>
		<tbody id='msgPopListContent'>
		</tbody>
		</table>
	  		<div class="pagination"></div>
		</div>
		<div class='msgPopBtns'>
				<button class='msgPopBtn' id='chooseMsgTypeSave' type="button" >保存</button>
				<button class='msgPopBtn msgPopBtnl' id='chooseMsgTypeCancel' type="button" >取消</button>
		</div>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="wxmenu:wxMenu:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>