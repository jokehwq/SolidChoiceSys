<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信用户分组管理</title>
	<meta name="decorator" content="default"/>
	
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
		.userPopBtn{
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
		.userPopBtnl{
			margin-left:50px;
			background:#546a79;
		}
		.userPop{
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
		.userPopContent{
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
		.userPopBtns{
		    position: absolute;
		    left: 0px;
		    right: 0px;
		    bottom: 20px;
		    margin: 0 auto;
		    text-align: center;
		}
		.userPopBtn button{
			margin:0px;
		}
		.userPopSelect{
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
    	.ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
	</style>
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>	
</head>
<body>

   	<div id="content" class="row-fluid">
		<div id="left" class="accordion-group">
			<div class="accordion-heading">
					<a class="accordion-toggle" href="${ctx}/weixin/wxUser/grouplist?accountId=${accountId}" target="right"><span class="icon-caret-right"></span>全部用户</a> 
		    </div>
			<div id="ztree" class="ztree"></div>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="officeContent" src="${ctx}/weixin/wxUser/selectGroupUser?accountId=${accountId}" width="100%" height="91%" frameborder="0"></iframe>
		</div>
	</div>
	
	<div id="userpop" class='userPop'>
		
	</div>
		<script type="text/javascript">
		var accountid = "${accountId}";
		 $.ajaxSetup({ 
		    error: function (x, e) { 
		        alert(e); 
		    } 
		});	
		var setting = {
				view: {
					addHoverDom: addHoverDom,
					removeHoverDom: removeHoverDom,
					selectedMulti: false
				},
				  edit: {
					enable: true,
					removeTitle:'删除',
					renameTitle:'修改'
				}, 
				data : {
					simpleData : {
						enable : true,
						idKey : "id",
						pIdKey : "pId",
						rootPId : '0'
					}
				},
				callback : {
					onClick : function(event, treeId, treeNode) {
						var id = treeNode.id == '0' ? '' : treeNode.id;
						$('#officeContent').attr("src",
								"${ctx}/weixin/wxUser/selectGroupUser?accountId=${accountId}&&groupid="
										+ id );
					},
					 beforeRemove: function(treeId, treeNode) {
						return true;
					}, 
					onRemove:function(e, treeId, treeNode) {
					var submit = function (v, h, f) {
						if (v == true){
						groupid = treeNode.groupId;
						accountid = accountid;
					 	$.ajax({  
	                          url:basePath+'/a/weixin/wxUserGroup/delete',
	                          dataType : "json",
	                          traditional: true,
	                          data:{'groupid':groupid,"accountid":accountid},                          
	                          type:"post",
	                          success:function(){
	                        	  top.$.jBox.tip("删除分组成功","success",{persistent:true,opacity:0});
	                        	  refreshTree();
	                          }
	                       })
						 }else{
							   top.$.jBox.tip("已取消", 'info');
							   refreshTree();   
						 }
		                    return true;
					   }
					top.$.jBox.confirm("确定删除分组？", "提示", submit, { buttons: { '删除': true, '取消': false } });
	                top.$('.jbox-body .jbox-icon').css('top','55px');
					},
					beforeRename:function(treeId, treeNode) {
						return true;
					},
					onRename:function(e, treeId, treeNode ) {
						var submit = function (v, h, f) {
		                    if (v == true){
		                	id = treeNode.id;
		                    accountid = accountid;
							groupName = treeNode.name;
						 	$.ajax({  
		                          url:basePath+'/a/weixin/wxUserGroup/update',
		                          dataType : "json",
		                          traditional: true,
		                          data:{'id':id, "accountid":accountid,"groupName":groupName },                          
		                          type:"post",
		                          success:function(data){
		                        	  top.$.jBox.tip("修改成功","success",{persistent:true,opacity:0});
		                        	  refreshTree(); 
		                          }
		                     })        
		                    }else{
		                    	top.$.jBox.tip("已取消", 'info');
		                        refreshTree();
		                    }
		  
		                    return true;
		                };
		                top.$.jBox.confirm("确定是否修改分组？", "提示", submit, { buttons: { '修改': true, '取消': false } });
		                top.$('.jbox-body .jbox-icon').css('top','55px');
				}
		}
	}
		var localObj = window.location;
		var contextPath = localObj.pathname.split("/")[1];
		var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

			var accountid = "${accountId}";
			function refreshTree() {
				$.getJSON(
						"${ctx}/weixin/wxUserGroup/selectGroupList?accountid="
								+ accountid, function(data) {
							groupstr = JSON.stringify(data);
							newstr = groupstr.replace(/groupName/g, "name");
							console.log(newstr);
							data = JSON.parse(newstr);
							$.fn.zTree.init($("#ztree"), setting, data)
									.expandAll(true);
						});
			}
			refreshTree();

			var leftWidth = 180; // 左侧窗口大小
			var htmlObj = $("html"), mainObj = $("#main");
			var frameObj = $("#left, #openClose, #right, #right iframe");
			function wSize() {
				var strs = getWindowSize().toString().split(",");
				htmlObj.css({
					"overflow-x" : "hidden",
					"overflow-y" : "hidden"
				});
				mainObj.css("width", "auto");
				frameObj.height(strs[0] - 5);
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left")
						.width());
				$("#right").width(
						$("#content").width() - leftWidth
								- $("#openClose").width() - 5);
				$(".ztree").width(leftWidth - 10)
						.height(frameObj.height() - 46);
			}

			
			function addHoverDom(treeId, treeNode) {
				 var sObj = $("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) {
					return;
				}
				
				var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
					+ "' title='添加成员'></span>";
				sObj.after(addStr);
				
				var btn = $("#addBtn_"+treeNode.tId);
				btn.bind("click", function(){
					showUserDialog(treeNode.groupId) 
					return false;
				});
			};
			function removeHoverDom(treeId, treeNode) {
				$("#addBtn_"+treeNode.tId).unbind().remove();
			};
			
			function showUserDialog(groupid) {
				$.get('${ctx}/weixin/wxUserGroup/userdialog?accountid='+accountid+'&groupid='+groupid,function(text){
					$('#userpop').html(text).show();
				});
			}
			
		</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>