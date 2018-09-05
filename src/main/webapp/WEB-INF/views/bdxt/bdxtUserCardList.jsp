<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户打卡信息管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		*{margin:0;padding:0;}
		.theme-popover-mask{z-index:1;position:absolute;left:0;top:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);-moz-opacity:0.5;display:none;}
		.theme-popover{z-index:9;position:absolute;top:50%;left:50%;width:660px;height:360px;margin:-180px 0 0 -330px;border-radius:5px;border:solid 2px #e4e4e4;background-color:#fff;display:none;box-shadow:0 0 10px #666;background:#fff;}
		.theme-poptit{border-bottom:1px solid #ddd;padding:12px;position:relative;height:24px;}
		.theme-poptit .close{float:right;color:#999;padding:5px;margin:-2px -5px -5px;font:bold 14px/14px simsun;text-shadow:0 1px 0 #ddd}
		.theme-poptit .close:hover{color:#444;}
		.theme-popbod{width:600px;height:300px;}
		#allmap{width:660px;height:300px;}
		.BMap_bubble_content{margin-top: -1px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/getscript?v=2.0.2&ak=184ZsPnpGiYQC0AlPr9Fd7tV9drpWVTc&services=&t=20131216074619"></script>
	<script type="text/javascript">
		function page(n, s) {
			if (n) $("#pageNo").val(n);
			if (s) $("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		};

		function sign(longitude,latitude) {
			var map, marker, infoWindow1;
			$('.theme-popover-mask').show();
			$('.theme-popover-mask').height($(document).height());
			$('.theme-popover').show();
			if(navigator.geolocation) {
				// 百度地图API功能
				var map = new BMap.Map("allmap");
				var point = new BMap.Point(longitude, latitude);
				var gc = new BMap.Geocoder();  //初始化，Geocoder类
				gc.getLocation(point, function (rs) {   //getLocation函数用来解析地址信息，分别返回省市区街等
					var addComp = rs.addressComponents;
					province = addComp.province;//获取省份
					city = addComp.city;//获取城市
					district = addComp.district;//区
					street = addComp.street;//街
					var marker = new BMap.Marker(point); //地图事件类
					var opts = {
						width: 25,     // 信息窗口宽度
						height: 120,     // 信息窗口高度
						title: "我所在的地点:<hr />"  // 信息窗口标题 ，这里声明下，可以在自己输出的信息里面嵌入html标签的
					}
					var infoWindow = new BMap.InfoWindow("省份:" + province + ";" + "城市:"
					+ city + ";<br /><br />" + "县/区:" + district + ";" + "街道:" + street + ".", opts);
					// 创建信息窗口对象，把信息在初始化 地图信息窗口类的同时写进去
					marker.enableDragging(); //启用拖拽事件
					marker.addEventListener("dragend", function (e) {
						gc.getLocation(point, function (rs) {
							//由于在getLocation函数返回信息之前，首先执行它下面的代码的，所以要把重新拖动后的代码放到它里面
							var addComp = rs.addressComponents;
							province = addComp.province;//获取省份
							city = addComp.city;//获取城市
							district = addComp.district;//区
							street = addComp.street;//街
							opts = {
								width: 25,     // 信息窗口宽度
								height: 160,     // 信息窗口高度
								title: "现在的位置:<hr />"  // 信息窗口标题
							}
							point = new BMap.Point(e.point.lng, e.point.lat); //标记新坐标（拖拽以后的坐标）
							marker = new BMap.Marker(point);  //事件类

							infoWindow = new BMap.InfoWindow("省份:" + province + ";" + "城市:"
							+ city + ";<br /><br />" + "县/区:" + district + ";" + "街道:" + street + ".<br />" +
							"经度：" + e.point.lng + "<br />纬度：" + e.point.lat, opts);

							map.openInfoWindow(infoWindow, point);
							//这条函数openInfoWindow是输出信息函数，传入信息类和点坐标
						})
					})

					map.addControl(new BMap.NavigationControl()); //左上角控件
					map.enableScrollWheelZoom(); //滚动放大
					map.enableKeyboard(); //键盘放大
					map.centerAndZoom(point, 13); //绘制地图
					map.addOverlay(marker); //标记地图
					map.openInfoWindow(infoWindow, map.getCenter());      // 打开信息窗口
				});
			}
		}

		$(document).ready(function () {

			$('.theme-poptit .close').click(function(){
				$('.theme-popover-mask').hide();
				$('.theme-popover').hide();
			})
			$('.theme-popover-mask').click(function(){
				$('.theme-poptit .close').click()
			});

			$("#btnExport").click(function () {
				top.$.jBox.confirm("确认要导出打卡记录吗？", "系统提示", function (v, h, f) {
					if (v == "ok") {
						$("#searchForm").attr("action", "${ctx}/bdxt/bdxtUserCard/exportExcel");
						$("#searchForm").submit();
					}
				}, {buttonsFocus: 1});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
		});
		var html = "<div style='padding:20px;'>审核状态：<input type='radio' id='clockState' name='clockState' value='2'>审核通过</input>&nbsp;&nbsp;" +
				"<input type='radio' id='clockState' name='clockState' value='3'>审核不通过</input>" +
				"<br/><br/>" +
				"输入备注：<input type='text' id='remark' name='remark' /><br/>" +
				"</div>";
		function check(id) {
			$.jBox.open(html, "审核意见", 600, 300, {
				buttons: {"确定": "ok", "关闭": true},submit: function (v, h, f) {
					var clockState='undefined';
					var obj = document.getElementsByName("clockState");
					for(var i = 0; i < obj.length; i ++) {
						if(obj[i].checked){
							clockState =obj[i].value;
							break;
						}
					}
					if (clockState == 'undefined') {
						$.jBox.tip("请输入审核状态", 'error', { focusId: "clockState" }); // 关闭设置 clockState 为焦点
						return false;
					}
					if (f.remark == '') {
						$.jBox.tip("请输入审核意见", 'error', { focusId: "remark" }); // 关闭设置 remark 为焦点
						return false;
					}
					if (v == "ok") {
						var remark=$("#remark").val();
						$.ajax({
							type:"POST",
							url:"${ctx}/bdxt/bdxtUserCard/update",
							data: {"id":id,"clockState":clockState,"remark":remark},
							success:function(data){
								alert(data);
								//刷新当前页
								location.reload();
							}
						});
					}
				}
			});
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<shiro:hasPermission name="bdxt:bdxtActivity:view"><li><a href="${ctx}/bdxt/bdxtActivity/detail?id=${activityId}">活动详情</a></li></shiro:hasPermission>
		<shiro:hasPermission name="bdxt:bdxtActivity:view"><li><a href="${ctx}/bdxt/bdxtUserQuote/list?bdxtActivityId=${activityId}">报名记录</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/bdxt/bdxtUserCard/list?bdxtActivityId=${activityId}">打卡记录</a></li>
		<shiro:hasPermission name="bdxt:bdxtActivityOrder:view"><li><a href="${ctx}/bdxt/bdxtActivityOrder/applyList?activityId=${activityId}">支付信息</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bdxtUserCard" action="${ctx}/bdxt/bdxtUserCard/" method="post" class="breadcrumb form-search">
		<input type="hidden" name="bdxtActivityId" value="${activityId}"/>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label>
				<form:input path="realName" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入用户名称"/>
			</li>
			<li><label>联系方式：</label>
				<form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium" placeholder="请输入联系方式"/>
			</li>
			<li><label>打卡日期：</label>
				<input name="clockTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bdxtUserCard.clockTime}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>打卡状态</label>
				<form:select path="clockState" class="input-medium">
					<form:option value="" disabled="true" selected="selected">请选择</form:option>
					<form:option value="1">待审核</form:option>
					<form:option value="2">审核通过</form:option>
					<form:option value="3">审核不通过</form:option>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			</br>
			<li><label>签到时间：</label>
				<input name="signTimeLeft" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bdxtUserCard.signTimeLeft}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>&nbsp;&nbsp;&nbsp;&nbsp;
				~ &nbsp;
				<input name="signTimeRight" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bdxtUserCard.signTimeRight}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>签退时间：</label>
				<input name="signOutTimeLeft" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bdxtUserCard.signOutTimeLeft}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>&nbsp;&nbsp;&nbsp;&nbsp;
				~ &nbsp;
				<input name="signOutTimeRight" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${bdxtUserCard.signOutTimeRight}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>图片</th>
				<th>姓名</th>
				<th>联系方式</th>
				<th>打卡日期</th>
				<th>打卡城市</th>
				<th>打卡状态</th>
				<th>签到时间</th>
				<th>签退时间</th>
				<shiro:hasPermission name="bdxt:bdxtUserCard:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bdxtUserCard" varStatus="status">
			<tr>
				<td>
						${status.count}
				</td>
				<td>

				</td>
				<td>
						${bdxtUserCard.realName}
				</td>
				<td>
						${bdxtUserCard.phone}
				</td>
				<td>
				    ${fn:split(bdxtUserCard.clockStartTime," ")[0]}
				</td>
				<td>
						${bdxtUserCard.clockCity}
				</td>
				<td>
					<c:choose>
						<c:when test="${bdxtUserCard.clockState ==1}">
							待审核
						</c:when>
						<c:when test="${bdxtUserCard.clockState ==2}">
							审核通过
						</c:when>
						<c:when test="${bdxtUserCard.clockState ==3}">
							审核不通过
						</c:when>
					</c:choose>
				</td>
				<td>
				     ${fn:split(bdxtUserCard.clockStartTime," ")[1]}
				</td>
				<td>
				    <c:if test="${bdxtUserCard.clockEndTime!=''}">
				        ${fn:split(bdxtUserCard.clockEndTime," ")[1]}
				    </c:if>
				</td>
				<shiro:hasPermission name="bdxt:bdxtUserCard:edit"><td>
					<a href="#" onclick="return check('${bdxtUserCard.id}');">审核</a>
					<a class="theme-login" href="#" onclick="return sign('${bdxtUserCard.signlongitude}','${bdxtUserCard.signlatitude}');">签到位置</a>
					<a class="theme-login" href="#" onclick="return sign('${bdxtUserCard.signoutlongitude}','${bdxtUserCard.signoutlatitude}');">签退位置</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

	<div class="theme-popover-mask"></div>
	<div class="theme-popover">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a>
			<h3>位置</h3>
		</div>
		<div class="theme-popbod">
			<div id="allmap"></div>
		</div>
	</div>
</body>
</html>