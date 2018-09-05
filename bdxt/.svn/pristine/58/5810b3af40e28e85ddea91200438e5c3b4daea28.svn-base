<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公众号自动回复管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			var type="${wxMsg.msgtype}";
			if(type!="text"){
				$('#image').sty
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
			$('#btnSubmit').on('click',function(e){
				var content = $('input:radio:checked').val();
				var id = $('input:radio:checked').data('id');
				$('#msgId').val(content);
				$('#msgId').attr('data-id',id);
				$('#msgContentId').val(id);
				$('.msgPop').hide();
				
			});
			if("${wxMsg.msgtype}"=="news" || "${wxMsg.msgtype}"=="localnews"){
				$('#msgId').val("${wxMsg.title}");
			}else{
				$('#image').hide();
				$('#msgId').val("${wxMsg.content}");
			}
		});
		
		 function getValue(page,size){
			    var accountid=$('#accountid').val();
		        var str = $('#msgPopSelect-type').val();   //获得选中的值
		        var page = page || 1;
		        var size = size || 10;
		        var url;
		        if(str=="text"){
		        	url="${ctx}/weixin/wxMsg/gettext";
		        }else if(str=="localnews"){
		        	url="${ctx}/wxnewsmsg/wxNewsMsg/getlocalnews";
		        }else{
		        	url="${ctx}/wxnewsmsg/wxNewsMsg/getnews";
		        }
		        
		        $.ajax({
		            type:"post",
		            dataType:"json",
		            url:url,
		            data:{'msgtype':str,'accountid':accountid,page:page,pagesize:size},
		            success:function(data){
		            	console.log(data);
		               var listHtmlArr = [];
		               var listHtml = '';
		               var pagination =  data.html;
		               $('.pagination').html(pagination);
		               if(data && data.list && data.list.length > 0){
		            	   for(var i = 0,len = data.list.length; i < len; i++){
		            		   console.log(data);
			            	   listHtmlArr.push('<tr>');
			            	   if(data.list[0].msgtype=='text'){
			            		   listHtmlArr.push('<td><input type="radio" name="type" data-id="' + data.list[i].id + '"  value="' + data.list[i].content + '"/></td>');
			            		   listHtmlArr.push('<td>'+data.list[i].content+'</td>');
			            	   }
			            	   if(data.list[0].msgtype=='news'){
			            		   listHtmlArr.push('<td><input type="radio" name="type" data-id="' + data.list[i].id + '"  value="' + data.list[i].title + '"/></td>');
			            		   listHtmlArr.push("<td style='width:140px;height:85px'><img src='"+data.list[i].materials[0].thumbMediaUrl+"'></td>");
			            		   listHtmlArr.push('<td>'+data.list[i].title+'</td>');
			            	   }
			            	   if(data.list[0].msgtype=='localnews'){
			            		   listHtmlArr.push('<td><input type="radio" name="type" data-id="' + data.list[i].id + '"  value="' + data.list[i].title + '"/></td>');
			            		   listHtmlArr.push("<td style='width:140px;height:85px'><img src='"+data.list[i].materials[0].thumbMediaUrl+"'></td>");
			            		   listHtmlArr.push('<td>'+data.list[i].title+'</td>');
			            	   }
			            	  
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
		<li class="active"><a href="${ctx}/reply/wxSubscribeMsg/form?id=${wxMsg.id}">关注回复<shiro:hasPermission name="reply:wxSubscribeMsg:edit">${not empty wxSubscribeMsg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="reply:wxSubscribeMsg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxSubscribeMsg" action="${ctx}/reply/wxSubscribeMsg/save" method="post" class="form-horizontal">	
		<sys:message content="${message}"/>	
		<div class="control-group">
			
			<label class="control-label msgId1">回复内容：</label>
			<div class="controls">
			 <img id=image  style="width:140px;height:85px" src="${imageurl}"  type="hidden"><br/>
				<input id="msgId" type='text' maxlength="255"  readonly class="input-xlarge "/>
			</div> 
			<div class="controls">
				<input type="hidden" name="accountid" id='accountid' value="${accountid}"/>
			</div>
			<button class='chooseMsgType' id='chooseMsgType' type="button" >选择消息类型</button>
			<div class="controls">
				<input type="hidden" name="id"  value="${wxSubscribeMsg.id}"/>
			</div>
			<div class="controls">
				<input type="hidden" name="content" id="msgContentId" />
			</div>
		</div>
		<div class="control-group">
		</div>
				<div class='msgPop'>
			<div class='msgPopContent'>
				<div class='msgPopSelect'>
					<select name="type" id="msgPopSelect-type" onchange="getValue()">
		                <option value="">选择消息类型</option>
		                <option value="text">文本回复</option>
		                <option value="news">微信图文回复</option>
		                <option value="localnews">本地图文回复</option>
		               
			        </select>  
				</div>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="width:60px"></th>
				<th>回复内容</th>
			</tr>
		</thead>
		<tbody id='msgPopListContent'>
		</tbody>
		</table>
				<div class='msgPopList'>
		<div class="form-actions">
			<shiro:hasPermission name="reply:wxSubscribeMsg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="location.reload()"/>
		</div>
	</form:form>
</body>
</html>