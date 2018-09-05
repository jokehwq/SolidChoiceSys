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
	</style>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/weixin/wxUserGroup/">微信用户分组列表</a></li>
		<shiro:hasPermission name="weixin:wxUserGroup:edit"><li><a href="${ctx}/weixin/wxUserGroup/form?accountid=${accountid}">微信用户分组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wxUserGroup" action="${ctx}/weixin/wxUserGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分组名字：</label>
				<form:input path="groupName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分组名字</th>
				<shiro:hasPermission name="weixin:wxUserGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxUserGroup">
			<input type="hidden" id="accountid" name="accountid" value="${accountid}"/>
			<input type="hidden" id="groupid" name="groupid" value="${wxUserGroup.id}"/>
			
			<tr>
				<td><a href="${ctx}/weixin/wxUserGroup/form?id=${wxUserGroup.id}&accountid=${accountid}">  
				</a></td>
				
				<shiro:hasPermission name="weixin:wxUserGroup:edit"><td>
    				<a href="${ctx}/weixin/wxUserGroup/form?id=${wxUserGroup.id}&accountid=${accountid}">修改</a>
					<a href="${ctx}/weixin/wxUserGroup/delete?id=${wxUserGroup.id}&accountid=${accountid}" onclick="return confirmx('确认要删除该微信用户分组吗？', this.href)">删除</a>
					<a href="javascript:void(0);" onclick="showUserDialog('${wxUserGroup.id}');">添加成员</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<div id="userpop" class='userPop'>
		
	</div>
	
	<script type="text/javascript">
		function showUserDialog(groupid) {
			var accountid= $("#accountid").val();
			$.get('${ctx}/weixin/wxUserGroup/userdialog?accountid='+accountid+'&groupid='+groupid,function(text){
				$('#userpop').html(text).show();
			});
		}
	</script>
</body>
</html>