<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script type="text/javascript">
function save(){
	var accountId = $("#accountid").val();
	var groupid = $("#groupId").val();
	var idarr=$('input[name="ids"]:checked').map(function(){
		return $(this).val();
	}).get(); 
	$.post("${ctx}/weixin/wxUserGroup/moveUser",
			{'ids[]':idarr,'accountid':accountId,'groupid':groupid},
			function(data){
				$('#userpop').hide();
	        	top.$.jBox.tip(data.msg,"success",{persistent:true,opacity:0});
	        });

}
</script>
<div class='userPopContent'>
	<div class='userPopList'>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
				<th height="5" width="20">头像</th>
				<th>昵称</th>
				<th>性别</th>
				<th>城市</th>
				<th>关注状态</th>
				<th>选择</th>
			</tr>
		</thead>
			<tbody id='userPopListContent'>
				<c:forEach var="user" items="${userlist}">
			<tr>
				<td>
					<img alt="" src="${user.headimgurl}">	 
				</td>
				<td>
					${user.nickname}
				</td>
				<td>
					<c:if test="${user.sex==0}">未知</c:if>
					<c:if test="${user.sex==1}">男</c:if>
					<c:if test="${user.sex==2}">女</c:if>
				</td>
				<td>
					${user.city}
				</td>
				<td>
					${user.subscribe==1?"已关注":"未关注"}
				</td>
				<td>
					<input type="checkbox" name="ids" value="${user.openid}"/>
					<input type="hidden" id="accountid" name="accountid" value="${user.accountId}"/>
					<input type="hidden" id="groupId" name="groupid" value="${groupId}"/>
				</td>
			</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<div class="pagination"></div>
	</div>
	<div class='userPopBtns'>
		<button class='userPopBtn' id='' type="button"onclick="save()">保存</button>
		<button class='userPopBtn userPopBtnl' type="button"
			onclick="$('#userpop').hide();">取消</button>
	</div>
</div>