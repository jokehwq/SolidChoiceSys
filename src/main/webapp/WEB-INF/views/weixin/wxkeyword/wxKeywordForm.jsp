<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关键字管理</title>
	<meta name="decorator" content="default"/>
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
			
			var msgid = "${wxMsg.id}";
			var msgtype="${wxMsg.msgtype}";
			if(msgid.trim() == ''){
				$(".thide").hide();
			}else if(msgtype.trim()=="text"){
				$("#textcontent").show();
				   $("#news").hide();
				   $("#imagehuifu").hide();
			}
			if(msgtype.trim()=="news"||msgtype.trim()=="localnews"){
				$("#news").show();
				   $("#textcontent").hide();
				   $("#imagehuifu").hide();
			}
			if(msgtype.trim()=="image"){
				$("#imagehuifu").show();
				   $("#news").hide();
				   $("#textcontent").hide();
			}
			$('#chooseMsgType').on('click',function(e){
				$('.msgPop').show();
			});
			$('#chooseMsgTypeCancel').on('click',function(e){
				$('.msgPop').hide();
			});
			$('#chooseMsgTypeSave').on('click',function(e){
				var content = $('input:radio:checked').val();
				var id = $('input:radio:checked').data('id');
				var title = $('input:radio:checked').data('title');
				var msgtype=$('#savemsgtype').val();
				if(msgtype =='news' || msgtype == 'localnews'){
					$('#msgtype').val('图文');
					$('#title').val(title);
					alert(title);
					$('#image').attr('src',content);
					$('#msgContentId').val(id);
				}else if(msgtype == 'text'){
					$('#msgtype').val("文本");
					$('#wenbencontent').val(content);
					$('#msgContentId').val(id);
				}if(msgtype == 'image'){
					$('#msgtype').val("图片");
				}
				/* $('#msgId').val(content);
				$('#title').val(title);
				$('#image').attr('src',content);
				$('#imagemsg').attr('src',content);
				$('#msgId').attr('data-id',id);
				$('#msgContentId').val(id); */
				$('.msgPop').hide();
				  if(msgtype == 'text'){
					   $("#textcontent").show();
					   $("#news").hide();
					   $("#imagehuifu").hide();
				   }else if(msgtype == 'news'||msgtype == 'localnews'){
					   $("#news").show(); 
					   $("#textcontent").hide();
					   $("#imagehuifu").hide();
				   }else if(msgtype == 'image'){
					   $("#imagehuifu").show(); 
					   $("#textcontent").hide();
					   $("#news").hide();
				   }
			});
		});
		function getImage(event){
		     var parentdiv = $(event.currentTarget).parent();
		     var url= parentdiv.data("url");
		     var id= parentdiv.data("id");
		     $('#imagemsg').attr('src',url);
			 $('#msgContentId').val(id);
			 $('.chooseImg').removeClass('sel');
			 $(event.currentTarget).addClass("sel");
		}
		
		 function getValue(page,size){
			    var accountid=$('#accountid').val();
		        var str = $('#msgPopSelect-type').val();   //获得选中的值
		        var page = page || 1;
		        var size = size || 10;
		        if(str == "text"){
		        	$("#contentTable thead").html("<tr><th style='width:100px;'></th><th>回复内容</th></tr>");
		        }else if(str == "image"){
		        	$("#contentTable thead").html("<tr><th>图片</th><></tr>");
		        }else if(str == "news"){
		        	$("#contentTable thead").html("<tr><th style='width:10%;'></th><th style='width:35%;'>封面</th><th style='width:55%;'>图文标题</th></tr>");
		        }else if(str == "localnews"){
		        	$("#contentTable thead").html("<tr><th></th><th>封面</th><th>图文标题</th></tr>");
		        }
		        
		        $.ajax({
		            type:"post",
		            dataType:"json",
		            url:"${ctx}/wxkeyword/wxKeyword/getMsg",
		            data:{'msgtype':str,'accountid':accountid,page:page,pagesize:size},
		            success:function(data){
		               var listHtmlArr = [];
		               var listHtml = '';
		               var pagination =  data.html;
		               $('.pagination').html(pagination);
		               if(data && data.list && data.list.length > 0){
		            	   for(var i = 0,len = data.list.length; i < len; i++){
		            		   console.log(data);
		            		   var temp;
			            	   if(data.list[0].msgtype=='text'){
			            		   listHtmlArr.push('<tr>');
			            		   listHtmlArr.push('<td><input type="radio" name="type" data-id="' + data.list[i].id + '"  value="' + data.list[i].content + '"/></td>');
			            		   listHtmlArr.push('<td>'+data.list[i].content+'</td>');
			            		   $('#savemsgtype').val(data.list[0].msgtype);
			            		   listHtmlArr.push('</tr>');
			            		   
			            	   }
			            	   if(data.list[0].msgtype=='news'){
			            		   listHtmlArr.push('<tr>');
			            		   listHtmlArr.push('<td style="width:10%;"><input type="radio" name="type" data-title="'+data.list[i].title+'"  data-id="' + data.list[i].id + '"  value="' + data.list[i].wxMaterialNews.thumbMediaUrl + '"/></td>');
			            		   listHtmlArr.push('<td style="width:35%;"><img style="height:75px" width="75px"  src='+data.list[i].wxMaterialNews.thumbMediaUrl+' /></td>');
			            		   listHtmlArr.push('<td style="width:55%;">'+data.list[i].title+'</td>');
			            		   $('#savemsgtype').val(data.list[0].msgtype);
			            		   listHtmlArr.push('</tr>');
			            	   }
			            	   if(data.list[0].msgtype=='localnews'){
			            		   listHtmlArr.push('<tr>');
			            		   listHtmlArr.push('<td><input type="radio" name="type" data-title="'+data.list[i].title+'" data-id="' + data.list[i].id + '"  value="' + data.list[i].wxMaterialNews.thumbMediaUrl + '"/></td>');
			            		   listHtmlArr.push('<td><img style="height:75px" width="75px"  src='+data.list[i].wxMaterialNews.thumbMediaUrl+' /></td>');			            		  
			            		   listHtmlArr.push('<td>'+data.list[i].title+'</td>');
			            		   $('#savemsgtype').val(data.list[0].msgtype);
			            		   listHtmlArr.push('</tr>');
			            	   }
			            	   if(data.list[0].msgtype=='image'){

			            		   listHtmlArr.push(' <div style="float: left;margin-left: 10px">');
			            		   listHtmlArr.push('  <div style="width:100px;height: 100px;" data-url="' + data.list[i].wxMaterialNews.thumbMediaUrl + '" data-id="' + data.list[i].id + '">');
			            		   listHtmlArr.push(" <img class='chooseImg' style='height:100px' onclick='getImage(event)' src='"+data.list[i].wxMaterialNews.thumbMediaUrl+"' width='100%'/>");
			            		   listHtmlArr.push('  </div> </div>');
			            		   $('#savemsgtype').val(data.list[0].msgtype);
			            	   }
			            	  
			            	  
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
    	#msgtype,.msgId1{
    		margin-top:10px;
    	}
    	#contentTable thead th:nth-child(1){
    	width:60px;}
    	.sel{
           border:solid 3px #1a99e2;
          }
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/wxkeyword/wxKeyword/?accountid=${accountid}">关键字列表</a></li>
		<li class="active"><a href="${ctx}/wxkeyword/wxKeyword/form?id=${wxKeyword.id}">关键字<shiro:hasPermission name="wxkeyword:wxKeyword:edit">${not empty wxKeyword.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="wxkeyword:wxKeyword:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxKeyword" action="${ctx}/wxkeyword/wxKeyword/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div> 
			<label class="control-label msgId1">消息类型：</label>
			<div class="controls">
				 <input id="msgtype" type='text' maxlength="255" value="${wxMsg.msgtype}"  readonly class="input-xlarge "/>
			    <input id="savemsgtype" type="hidden">
			</div> 
			
			<div id="textcontent" style="margin-left: 94px;margin-top: 4px; class="controls thide">
			<label style="margin-right: 17px;" >回复内容：</label>
				<textarea style="width:270px;height:80px;" id="wenbencontent" type='text' maxlength="255" readonly class="input-xlarge ">${wxMsg.content}</textarea>
			</div>
			 
			<div id="news" class="thide" style="margin-left: 3px;margin-top: 9px;">
			<label style="margin-right: 17px;" class="control-label msgId1">封面：</label>
			<div style="position: relative;display: inline-block;width:200px;">
				<img style="height:200px" width="200px" id="image" alt="" src="${newwxMaterialNews.thumbMediaUrl}">
				<div id="title" style="position: absolute;left:0px;bottom: 0px;width:100%;height:30px;background-color: black;opacity: 0.7;color:white;line-height:30px;padding-left:5px;box-sizing:border-box;">${wxMsg.title}</div>
			</div>
			</div>
			
			<div id="imagehuifu" style="margin-left: 93px;margin-top: 9px;" class="thide">
			<label style="margin-right: 20px;">回复图片：</label>
			<img style="height:200px" width="200px" id="imagemsg" alt="" src="${newwxMaterialNews.thumbMediaUrl}">
			</div>
			
			
			
			<button class='chooseMsgType' id='chooseMsgType' type="button" >选择消息类型</button>
			<div class="controls">
				<input type="hidden" name="accountid" id='accountid' value="${accountid}"/>
			</div>
			<div class="controls">
				<input type="hidden" name="msgId" id='msgContentId' value="${msgId}"/>
			</div>
		</div>
		<div class='msgPop'>
			<div class='msgPopContent'>
				<div class='msgPopSelect'>
					<select name="type" id="msgPopSelect-type" onchange="getValue()">
		                <option value="">选择消息类型</option>
		                <option value="text">文本回复</option>
		                <option value="news">微信图文回复</option>
		                <option value="localnews">本地图文回复</option>
		                <option value="image">图片回复</option>		               
			        </select>  
				</div>
				<div class='msgPopList'>
	    <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
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
			<shiro:hasPermission name="wxkeyword:wxKeyword:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/wxkeyword/wxKeyword/?accountid=${accountid}'"/>
		</div>
	</form:form>
</body>
</html>