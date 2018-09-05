<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>活动详情管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/bdxt/bdxtActivity/detail?id=${bdxtActivity.id}">活动详情</a></li>
    <shiro:hasPermission name="bdxt:bdxtUserQuote:view"><li><a href="${ctx}/bdxt/bdxtUserQuote/list?bdxtActivityId=${bdxtActivity.id}">报名记录</a></li></shiro:hasPermission>
    <shiro:hasPermission name="bdxt:bdxtUserCard:view"><li><a href="${ctx}/bdxt/bdxtUserCard/list?bdxtActivityId=${bdxtActivity.id}">打卡记录</a></li></shiro:hasPermission>
    <shiro:hasPermission name="bdxt:bdxtActivityOrder:view"><li><a href="${ctx}/bdxt/bdxtActivityOrder/applyList?activityId=${bdxtActivity.id}">支付信息</a></li></shiro:hasPermission>
</ul>

<form:form class="form-horizontal">
    <div class="control-group">
        <label class="control-label">活动主题：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.activityName}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">活动类型：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.dictName}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">活动状态：</label>

        <div class="controls">
            <label class="lbl">
                <c:choose>
                    <c:when test="${bdxtActivity.activityStatus ==1}">
                        招募中
                    </c:when>
                    <c:when test="${bdxtActivity.activityStatus ==2}">
                        进行中
                    </c:when>
                    <c:when test="${bdxtActivity.activityStatus ==3}">
                        已结束
                    </c:when>
                    <c:otherwise>
                        暂停
                    </c:otherwise>
                </c:choose>
            </label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">活动时间：</label>

        <div class="controls">
            <label class="lbl">
                <fmt:formatDate value="${bdxtActivity.activityStartTime}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;&nbsp;
                ~ &nbsp;&nbsp;&nbsp;
                <fmt:formatDate value="${bdxtActivity.activityEndTime}" pattern="yyyy-MM-dd"/>
            </label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作地址：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.workAddress}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">招募人数：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.recruitNum}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">人均预算：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.perBugget}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别要求：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.genderReq==1?'男':'女'}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">身高要求：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.heightReqLeft} ~ ${bdxtActivity.heightReqRight}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">是否报销差旅费：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.isReimburseTravel==1?'是':'否'}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">招募内容：</label>

        <div class="controls">
            <label class="lbl">${bdxtActivity.recruitRemark}</label>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">参考样例图片地址：</label>

        <div class="controls">
            <label class="lbl">
                <c:choose>
                    <c:when test="${bdxtActivity.id == '' || bdxtActivity.id == null}">
                        <c:if test="${bdxtActivity.referenceSampleUrl == null}">
                            <img style="margin-left: 20px;width: 70px;" id="imghead" border="0"
                                 src="${ctxStatic}/images/photo_icon.png" width="300px" disabled="disabled"/>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <img style="margin-left: 20px;width: 70px;" id="imghead" border="0"
                             src="${bdxtActivity.referenceSampleUrl}">
                    </c:otherwise>
                </c:choose>
            </label>
        </div>
    </div>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回"
               onclick="window.location.href='${ctx}/bdxt/bdxtActivity'"/>
    </div>






</form:form>
</body>
</html>