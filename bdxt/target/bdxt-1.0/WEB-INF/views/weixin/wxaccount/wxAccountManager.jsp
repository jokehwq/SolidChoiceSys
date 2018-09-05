<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>${wxAccount.name}功能管理中心</title>  
    <link rel="stylesheet" href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css">
    <link rel="stylesheet" href="${ctxStatic}/weixin/account/admin.css">	
    <link rel="stylesheet" href="${ctxStatic}/weixin/account/pintuer.css"> 
    
   
     <script src="${ctxStatic}/jquery/jquery-1.8.3.js"></script> 
      <script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js"></script>             
</head>
<body>
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${ctxStatic}/images/weixin.png" class="radius-circle rotate-hover" height="50" alt="" />${wxAccount.name}-功能管理中心</h1>
  </div>
 <!--  <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a> </div> -->
<div class="head-l">
<!-- <a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a> --> </div> 
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
   <h2><a href="${ctx}/wxmenu/wxMenu/list?accountId=${wxAccount.id}" target="right"><span class="icon-terminal"></span>自定义菜单</a></h2>
   <h2 class="h2"><span class="icon-reply"></span>回复管理</h2>
      <ul>
         <li><a href="${ctx}/reply/wxSubscribeMsg/form?accountid=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>关注回复</a></li>
         <li><a href="${ctx}/wx_second_subscribe/wxSecondSubscribeMsg/secondForm?accountid=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>默认回复</a></li> 
         <li><a href="${ctx}/wxkeyword/wxKeyword/list?accountid=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>关键字回复</a></li>    
      </ul>
  <h2 class="h2"><span class="icon-pencil-square-o"></span>消息管理</h2>
  <ul>
    <li><a href="${ctx}/weixin/wxMsg/list?accountid=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>文本消息</a></li>
    <%--<li><a href="${ctx}/wxnewsmsg/wxNewsMsg/list?accountid=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>图文管理(微信)</a></li>
    <li><a href="${ctx}/wxnewsmsg/wxNewsMsg/locallist?accountid=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>图文管理(本地)</a></li>
    <li><a href="${ctx}/weixin/wxImageMsg/list?accountid=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>图片消息</a></li>--%>
  </ul>
  <h2 class="h2"><span class="icon-user"></span>用户管理</h2>
  <ul>
  <li><a href="${ctx}/weixin/wxUser/grouplist?accountId=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>用户列表</a></li> 
  <%--<li><a href="javascript:void(0)" target="right"><span class="icon-caret-right"></span>用户群发</a></li>  --%>
  </ul>
     <h2 class="h2"><span class="icon-files-o"></span>素材管理</h2>
  <ul>
    <li><a href="${ctx}/weixin/wxMaterialNews/list?accountId=${wxAccount.id}" target="right"><span class="icon-caret-right"></span>图片列表</a></li>        
  </ul>   
</div>

<ul class="bread">
 <!--  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li> -->
  <!-- <li><a href="##" id="a_leader_txt">网站信息</a></li> -->
  <!-- <li><b>当前语言：</b><span style="color:red;">中文</php></span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li> -->
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="" name="right" width="100%" height="100%"></iframe>
</div>
</body>
<script type="text/javascript">
$(function(){
	  $(".leftnav .h2").click(function(){
		  $(this).next().slideToggle(200);	
		  $(this).toggleClass("on"); 
	  })
	  $(".leftnav ul li a").click(function(){
		    $("#a_leader_txt").text($(this).text());
	  		$(".leftnav ul li a").removeClass("on");
			$(this).addClass("on");
	  })
	});

</script>
</html>