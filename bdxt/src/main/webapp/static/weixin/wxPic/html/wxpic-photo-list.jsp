<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图文管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/weixin/wxPic/app.css" rel="stylesheet" />
	
</head>
<body>
<div class="modal-header">
    <h4 class="modal-title">图片 <i class="icon-remove pull-right gray" ng-click="close()"></i></h4>
</div>
<div class="wx-photo">
    <ul>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li><li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li><li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li><li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li><li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    <li>
      <img alt="图片" src="${ctxStatic}/weixin/wxPic/img_holder.png">
    </li>
    </ul>
</div>
 <footer class="panel-footer text-right">
            <paging
                    class="small"
                    page="1"
                    page-size="10"
                    total="36"
                    adjacent="{{adjacent}}"
                    dots="{{dots}}"
                    scroll-top="true"
                    hide-if-empty="true"
                    ul-class="pagination pagination-sm"
                    active-class="active"
                    disabled-class="disabled"
                    show-prev-next="true"
                    paging-action="doRefreshImage({page:page})">
            </paging>
        </footer> 
</body>
</html>