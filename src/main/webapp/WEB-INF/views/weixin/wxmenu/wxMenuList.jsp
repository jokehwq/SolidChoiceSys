<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
	var topMenuCount=0 ;
	var secondMenuIds = new Array();
	var topMenuIds = new Array();
	var contrastTopMenuIds = new Array()
		$(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});

			for(var i=0;i<data.length;i++){
				if(data[i].parentId == '0'){
					topMenuIds.push(data[i].id);
					topMenuCount++;
				}else{
					contrastTopMenuIds.push(data[i].parentId)
					secondMenuIds.push(data[i].id);
				}
			}
			if(topMenuCount>=3){
				$('#addMenu').hide();
			}
			
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">	
		<li class="active"><a href="${ctx}/wxmenu/wxMenu/?accountId=${accountId}">菜单列表</a></li>
		<shiro:hasPermission name="wxmenu:wxMenu:edit"><li id="addMenu"><a href="${ctx}/wxmenu/wxMenu/form?accountId=${accountId}">菜单添加</a></li></shiro:hasPermission>
	</ul>
 	<form:form id="searchForm" modelAttribute="wxMenu"  class="breadcrumb form-search"> 
		<ul class="ul-form">	
			<li class="btns"><a href="${ctx}/wxmenu/wxMenu/push?accountid=${accountId}" onclick="return confirmx('确认发布菜单吗？', this.href)" ><input id="btnSubmit" class="btn btn-primary" type="button" value="发布菜单"/></a></li>
			<li class="clearfix"></li>
		 </ul> 
	 </form:form> 
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>菜单名称</th>
				<shiro:hasPermission name="wxmenu:wxMenu:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	注意:一级菜单只能添加3个，二级菜单只能添加5个
	
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/wxmenu/wxMenu/form?id={{row.id}}&&accountId={{row.accountid}}&&parentId={{row.parentId}}">
				{{row.name}}
			</a></td>
			<shiro:hasPermission name="wxmenu:wxMenu:edit"><td>
   				<a href="${ctx}/wxmenu/wxMenu/form?id={{row.id}}&&accountId={{row.accountid}}&&parentId={{row.parentId}}">修改</a>
				<a href="${ctx}/wxmenu/wxMenu/delete?id={{row.id}}&&accountId={{row.accountid}}" onclick="return confirmx('确认要删除该菜单及所有子菜单吗？', this.href)">删除</a>
				<a href="${ctx}/wxmenu/wxMenu/form?parentId={{row.id}}&&accountId={{row.accountid}}" class="ahide">添加下级菜单</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
	<script>
	   $(function(){   
		   for(i=0;i<secondMenuIds.length;i++){
			    rowId = secondMenuIds[i];
			   $('#'+rowId+" .ahide").hide();
			   				
		   }
 
       for(i=0;i<topMenuIds.length;i++){
    	   count = 0;
          for(j=0;j<contrastTopMenuIds.length;j++){
        	  if(topMenuIds[i]==contrastTopMenuIds[j]){
        		  count++;
        		  
        	  }
          }
          if(count>=5){
			  $('#'+topMenuIds[i]+" .ahide").hide();
		  }
       }
 
	   })
	</script>
</body>
</html>