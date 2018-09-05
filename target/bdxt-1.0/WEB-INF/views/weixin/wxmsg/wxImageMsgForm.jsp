<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公众号自动回复管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			var type="${wxMsg.msgtype}";
			if(type=="news"){
				$("#image").show();
				//$("#msgId").hide();
			}
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
             
			$('#chooseMsgType').on('click',function(e){
				$('.msgPop').show();
			});
			$('#chooseMsgTypeCancel').on('click',function(e){
				$('.msgPop').hide();
			});
			$('#chooseMsgTypeSave').on('click',function(e){
				var imgurl = $('input:radio:checked').val();
				var id = $('input:radio:checked').data('id');
				$('#msgId').attr('src',imgurl);
				$('#wxMaterId').val(id);
				$('.msgPop').hide();
				
			});
		});
		
		 function getValue(page,size){
			    var accountid=$('#accountid').val();
		        var str = $('#msgPopSelect-type').val();   //获得选中的值
		        var page = page || 1;
		        var size = size || 10;
		        
		        $.ajax({
		            type:"post",
		            dataType:"json",
		            url:"${ctx}/weixin/wxImageMsg/getImage",
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
			            		   listHtmlArr.push('<td><input type="radio" name="type" data-id="' + data.list[i].id + '"  value="' + data.list[i].thumbMediaUrl + '"/></td>');
			            		    console.log(data.list[i].thumbMediaUrl);
			            		   listHtmlArr.push('<td><img style="height:75px" width="75px"  src='+data.list[i].thumbMediaUrl+' /></td>');
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
		    margin-left: 108px;
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
		<li><a href="${ctx}/weixin/wxImageMsg/?accountid=${accountid}">图片列表</a></li>
		<li class="active"><a href="${ctx}/weixin/wxImageMsg/form?id=${wxMsg.id}">图片回复<shiro:hasPermission name="weixin:wxMsg:edit">${not empty wxMsg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="weixin:wxMsg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxImageMsg" action="${ctx}/weixin/wxImageMsg/save" method="post" class="form-horizontal">	
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label msgId1">回复内容：</label>
			<div class="controls">
			 <img style="height:200px" width="200px" id="msgId" alt="" src="${imageurl}" >
			</div> 
			<div class="controls">
				<input type="hidden" name="accountid" id='accountid' value="${accountid}"/>
			</div>
			<button class='chooseMsgType' id='chooseMsgType' type="button" onclick="getValue()" >选择图片</button>
			<div class="controls">
				<input type="hidden" name="wxMaterId" id="wxMaterId" />
			</div>
		</div>
		<div class="control-group">
		</div>
		<div class='msgPop'>
			<div class='msgPopContent'>
				<div class='msgPopSelect'>
				</div>
				<div class='msgPopList'>
	    <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:60px"></th>
				<th >图片</th>
			</tr>
		</thead>
		<tbody id='msgPopListContent'>
		</tbody>
		</table>
	  	<div class="pagination" ></div>
		</div>
		<div class='msgPopBtns'>
				<button class='msgPopBtn' id='chooseMsgTypeSave' type="button" >确定</button>
				<button class='msgPopBtn msgPopBtnl' id='chooseMsgTypeCancel' type="button" >取消</button>
		</div>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="reply:wxSubscribeMsg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/weixin/wxImageMsg/?accountid=${accountid}'"/>
		</div>
	</form:form>
</body>
</html>