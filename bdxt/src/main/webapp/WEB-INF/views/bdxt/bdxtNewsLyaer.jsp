<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评论管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/common/backStyle.css">
</head>
<body>
	<form:form id="searchForm1" modelAttribute="bdxtComment" action="${ctx}/bdxt/bdxtComment" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="ul-form btnul" style="margin-left: -15px;">
		    <label>评论内容：</label><form:input path="commentContent" htmlEscape="false" maxlength="64" class="input-medium"/>
			<label>评论人：</label><form:input path="nickName" htmlEscape="false" maxlength="64" class="input-medium"/>
			<span class="btns"><i class="inputIcon icon-search"></i><input id="btnSubmit" class="btnInput btn btn-primary" type="submit" value="查询"/></span>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<div id="codearea" style="width:100%;height:305px;overflow:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>评论人</th>
				<th>头像</th>
				<th>评论内容</th>
				<th>评论时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtComment" varStatus="status">
			<tr>
			<%-- <td><input name="check" type="radio" value="${status.count}"/></td>--%>
			    <td>
					${bdxtComment.nickName}
				</td>
				<td>
					<img src="${bdxtComment.headUrl}" style="width: 25px;height: auto">
				</td>
				<td>
					${bdxtComment.commentContent}
				</td>
				<td>
					<fmt:formatDate value="${bdxtComment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<a  onclick="javascript:deleteComment('${bdxtComment.id}')">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	</div>
	<div class="pagination">${page}</div>
	<div class="btnlist btnlistbtn">
	     <div class="btnsCen">
		<%--<span class="btns"><i class="inputIcon icon-search"></i><input id="transmit" class="btnInput btn btn-primary" type="button" value="确 定" />&nbsp;</span>--%>
		<span class="btns btnNull"><i class="inputIcon icon-reply" style="color:#222;"></i><input id="closeIframe" class="btnInput btn" type="button" value="关 闭" /></span>
	    </div> 
	</div>           
	<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
		function deleteComment(id) {

            $.jBox.confirm('确认删除?','系统提示',function(v,h,f){
                if(v=='ok'){
                    $.ajax({
                        type:"GET",
                        url:"${ctx}/bdxt/bdxtComment/delete",
                        data: {"id":id},
                        success:function(data){
                            //刷新当前页
                            showTip('删除成功');
                            location.reload();
                        },
						error:function (data) {
                            showTip('删除失败');
                            location.reload();
                        }
                    });
                }
            },{buttonsFocus:1, closed:function(){
                if (typeof closed == 'function') {
                    closed();
                }
            }});
        }
		function page(n,s){
		if(n) $("#pageNo").val(n);
		if(s) $("#pageSize").val(s);
		$("#searchForm1").attr("action","${ctx}/bdxt/bdxtComment");
		$("#searchForm1").submit();
		return false;
		}
		$(function() {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			//关闭iframe
			$('#closeIframe').click(function(){
			    parent.layer.close(index);
			});
		});

	</script>
</body>
</html>