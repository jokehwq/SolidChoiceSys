<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户详情管理</title>
    <meta name="decorator" content="default"/>
    <style>
        .info-detail {
            padding: 20px 20px 20px 40px;
        }

        .works-detail {
            display: none;
            padding: 20px;
        }

        .videos-detail {
            display: none;
            padding: 20px;
        }

        .user-detail-info {

        }

        .user-detail-ul {
            height: 40px;
        }

        .user-detail-li {
            float: left;
            height: 40px;
            line-height: 40px;
            width: 100px;
            text-align: center;
            list-style-type: none;
            cursor: pointer;
        }

        .user-detail-li-active {
            border-bottom: 4px solid #2fa4e7;
            color: #2fa4e7;
            font-weight: bold;
        }

        .headimg, .headinfo {
            display: inline-block;
        }

        .headimg {
            float: left;
            height: 70px;
            width: 70px;
            border-radius: 100%;
            background-color: #e7e7e7;
            padding: 5px;
            line-height: 70px;
            text-align: center;
        }

        .headimg .img {
            width: auto;
            height: 90%;
        }

        .info-item {
            border-bottom: 4px solid #e7e7e7;
        }

        .info-title {
            height: 80px;
        }

        .headinfo {
            padding: 20px;
            margin-left: 5px;
        }

        .clearance {
            margin: 0 5px;
        }

        .info-row-item {
            padding: 16px 0 6px 0;
        }

        .info-row-span1 {
            width: 120px;
            margin-left: 0px;
            color: #999;
            font-size: 15px;
        }

        .info-row-span {
            width: 150px;
            margin-left: 0px;
        }

        .magnum-img {
            display: inline-block;
            width: 100px;
            height: 120px;
            margin-right: 20px;
            line-height: 120px;
            text-align: center;
        }

        .magnum-img .img {
            width: auto;
            height: auto;
        }

        .magnum-info {
            padding: 20px 0;
        }

        .info-row-item-title {
            padding: 15px 0 10px 0;
            font-size: 16px;
            font-weight: bold;
        }

        .box-img-title {
            display: inline-block;
        }

        .box-img-icon {
            display: inline-block;
            width: 20px;
            height: auto;
            margin: 0 10px;
            padding: 10px;
            cursor: pointer;
        }

        .works-item {
            display: inline-block;
            width: 110px;
            height: 140px;
            margin-right: 20px;
            line-height: 140px;
            text-align: center;
        }

        .works-item img {
            height: auto;
            width: auto;
        }

        .works-label {
            padding: 10px 0;
        }

        .works-label-item {
            display: inline-block;
            padding: 4px 30px;
            background: #45aeea;
            text-align: center;
            margin-right: 16px;
            font-size: 12px;
            color: #fff;
            border-radius: 15px;
            min-width: 40px;
        }

        .box-img-btn {
            display: inline-block;
            width: 100px;
            text-align: center;
            background: #45aeea;
            padding: 3px 0;
            border-radius: 7px;
            color: #fff;
            cursor: pointer;
        }

        .box-img-btn1 {
            display: inline-block;
            width: 100px;
            text-align: center;
            background: #fff;
            padding: 3px 0;
            border-radius: 7px;
            color: #45aeea;
            cursor: pointer;
            border: 1px solid;
        }

        .delete-img1 {
            background-image: url(${ctxStatic}/images/delete2.png);
            background-repeat: no-repeat;
            background-size: 20px;
            background-position: 80px 120px;
            cursor: pointer;
        }

        .delete-img2 {
            background-image: url(${ctxStatic}/images/delete2.png);
            background-repeat: no-repeat;
            background-size: 15px;
            background-position: 66px 6px;
            cursor: pointer;
        }

        .btn-cancel, .btn-save {
            display: none;
        }

        .btn-cancel {
            margin-left: 10px;
        }

        .box-flex {
            display: flex;
            justify-content: flex-start;
            align-items: center;
        }
    </style>
    <script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var _this = this;
            $('.user-detail-li').click(function (e) {
                var liDetail = $('.user-detail-li');
                var name = $(e.currentTarget).attr('name')
                for (var i = 0; i < liDetail.length; i++) {
                    $(liDetail[i]).removeClass('user-detail-li-active');
                }
                $('.info-detail').hide();
                $('.works-detail').hide();
                $('.videos-detail').hide()
                $(e.currentTarget).addClass('user-detail-li-active');
                $('.' + name).show();
            })
            $(".img").click(function () {
                var _this = $(this);//将当前的img元素作为_this传入函数
                imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
            });
            $(".openImg").click(function () {
                var _this = $(this);//将当前的img元素作为_this传入函数
                imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
            });
            $('.btn-cancel').click(function (e) {
                hideDeleteImg(e)
            })
            $('.btn-save').click(function (e) {
                hideDeleteImg(e)

            })
            $('.btn-delete').click(function (e) {
                var id = $(e.currentTarget).data('id') //自己还有写获取id
                layer.open({
                    type: 0,
                    title: '删除该作品?',
                    closeBtn: 2,
                    btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        $.ajax({
                            type: "GET",
                            url: "${ctx}/bdxt/bdxtAttachment/delete",
                            data: {"id": id},
                            success: function (data) {
                                alert(data);
                                //刷新当前页
                                location.reload();
                            }
                        });
                    }
                    , btn2: function (index, layero) {

                    },
                    shadeClose: true,
                    skin: '',
                });
            })
            $('.btn-update').click(function (e) {
                var id = $(e.currentTarget).data('id');
                var nodeList = $($(e.currentTarget).parents('.works-detail-item')[0]).find('.works-item');
                var labelList = $($(e.currentTarget).parents('.works-detail-item')[0]).find('.works-label-item');
                for (var i = 0; i < nodeList.length; i++) {
                    $(nodeList[i]).addClass('delete-img1');
                }
                for (var i = 0; i < labelList.length; i++) {
                    $(labelList[i]).addClass('delete-img2');
                }
                $(e.currentTarget).siblings('.btn-cancel').show()
                $(e.currentTarget).siblings('.btn-save').show()
                bindDeleteEvent(id);
            })
        });

        //绑定删除
        function bindDeleteEvent(id) {
            $('.delete-img1').click(function (e) {
                e.stopPropagation();
                var attachUrl;
                var img = $(e.currentTarget).find('img').attr('src');
                if (img != null) {
                    attachUrl = img;
                }
                var video = $(e.currentTarget).find('video').attr('src');
                if (video != null) {
                    attachUrl = video;
                }
                layer.open({
                    type: 0,
                    title: '删除?',
                    closeBtn: 2,
                    btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        //更新接口
                        $.ajax({
                            type: "POST",
                            url: "${ctx}/bdxt/bdxtAttachment/update",
                            data: {"id": id, "attachUrl": attachUrl},
                            success: function (data) {
                                alert(data);
                                //刷新当前页
                                location.reload();
                            }
                        });
                    }
                    , btn2: function (index, layero) {

                    },
                    shadeClose: true,
                    skin: '',
                });
            })
            $('.delete-img2').click(function (e) {
                e.stopPropagation();
                var tagId = $(e.currentTarget).data('tag');
                alert(tagId)
                layer.open({
                    type: 0,
                    title: '删除该标签?',
                    closeBtn: 2,
                    btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        //更新接口
                        $.ajax({
                            type: "POST",
                            url: "${ctx}/bdxt/bdxtAttachment/update",
                            data: {"id": id, "bdxtTag": tagId},
                            success: function (data) {
                                alert(data);
                                //刷新当前页
                                location.reload();
                            }
                        });
                    }
                    , btn2: function (index, layero) {

                    },
                    shadeClose: true,
                    skin: '',
                });

            })
        }

        //作品保存 取消 时候把X去掉
        function hideDeleteImg(e) {
            var nodeList = $($(e.currentTarget).parents('.works-detail-item')[0]).find('.works-item');
            var labelList = $($(e.currentTarget).parents('.works-detail-item')[0]).find('.works-label-item');
            for (var i = 0; i < nodeList.length; i++) {
                $(nodeList[i]).removeClass('delete-img1');
            }
            for (var i = 0; i < labelList.length; i++) {
                $(labelList[i]).removeClass('delete-img2');
            }
            $(e.currentTarget).hide();
            $(e.currentTarget).siblings('.btn-save').hide()
            $(e.currentTarget).siblings('.btn-cancel').hide()
        }

        function imgShow(outerdiv, innerdiv, bigimg, _this) {
            var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
            $(bigimg).attr("src", src);//设置#bigimg元素的src属性

            /*获取当前点击图片的真实大小，并显示弹出层及大图*/
            $("<img/>").attr("src", src).load(function () {
                var windowW = $(window).width();//获取当前窗口宽度
                var windowH = $(window).height();//获取当前窗口高度
                var realWidth = this.width;//获取图片真实宽度
                var realHeight = this.height;//获取图片真实高度
                var imgWidth, imgHeight;
                var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

                if (realHeight > windowH * scale) {//判断图片高度
                    imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
                    imgWidth = imgHeight / realHeight * realWidth;//等比例缩放宽度
                    if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
                        imgWidth = windowW * scale;//再对宽度进行缩放
                    }
                } else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
                    imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放
                    imgHeight = imgWidth / realWidth * realHeight;//等比例缩放高度
                } else {//如果图片真实高度和宽度都符合要求，高宽不变
                    imgWidth = realWidth;
                    imgHeight = realHeight;
                }
                $(bigimg).css("width", imgWidth);//以最终的宽度对图片缩放

                var w = (windowW - imgWidth) / 2;//计算图片与窗口左边距
                var h = (windowH - imgHeight) / 2;//计算图片与窗口上边距
                $(innerdiv).css({"top": h, "left": w});//设置#innerdiv的top和left属性
                $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
            });

            $(outerdiv).click(function () {//再次点击淡出消失弹出层
                $(this).fadeOut("fast");
            });
        }
    </script>
</head>
<body>
<%--<ul class="nav nav-tabs">--%>
<%--<li><a href="${ctx}/bdxt/bdxtUser/">用户列表</a></li>--%>
<%--<li class="active">用户详情</li>--%>
<%--</ul>--%>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/solidChoice/vipUser/">会员列表</a></li>
    <li class="active"><a href="${ctx}/solidChoice/vipUser/detail?id=${vipUser.id}">用户信息</a></li>
</ul>
<br/>

<div id="outerdiv"
     style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
    <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:5px solid #fff;" src=""/>
    </div>
</div>
<div class="user-detail">
    <div class="control-group user-detail-info">
        <ul class="user-detail-ul">
            <li class="user-detail-li user-detail-li-active" name="info-detail">基本信息</li>
            <%-- <li class="user-detail-li" name="works-detail">作品</li>
             <li class="user-detail-li" name="videos-detail">视频</li>--%>
        </ul>
    </div>
    <div class="info-detail">
        <div>
            <div class="control-group info-title">
                <div class="headimg">
                    <c:if test="${vipUser.avatarPath == null}">
                        <img class="img"
                             src="http://app-testzone1.oss-cn-shenzhen.aliyuncs.com/0edd954a-3042-4208-86cd-51e5b6fb6d94.png"
                             alt="">
                    </c:if>
                    <c:if test="${vipUser.avatarPath != null}">
                        <img class="img" src=${vipUser.avatarPath} alt="">
                    </c:if>
                </div>
                <div class="headinfo">
                    <div class="clearance" style="font-weight: bold;font-size: 15px">${vipUser.nickName}</div>
                    <div style="margin-top: 10px">
                        <span class="clearance">${vipUser.area}</span> |
                        <span class="clearance">
                            <c:if test="${vipUser.gender == 1}">
                                男
                            </c:if>
					       <c:if test="${vipUser.gender == 2}">
                               女
                           </c:if>
                        </span> |
                        </div>
                </div>
            </div>
        </div>
        <div class="info-item">
            <div class="control-group">
                <div class="info-row-item">
                    <label class="control-label">
                        <span class="info-row-span1">用户姓名</span><span class="info-row-span">${vipUser.name}</span>
                    </label>
                    <label class="control-label">
                        <span class="info-row-span1">所在城市</span><span class="info-row-span">${vipUser.area}</span>
                    </label>
                </div>
            </div>
            <div class="control-group">
                <div class="info-row-item">
                    <label class="control-label">
                        <span class="info-row-span1">职业</span>
                        <span class="info-row-span">${vipUser.jobTitle}</span>
                    </label>
                    <label class="control-label">
                        <span class="info-row-span1">微信号</span>
                        <span class="info-row-span">${vipUser.openId}</span>
                    </label>
                </div>
            </div>
            <div class="control-group">
                <div class="info-row-item">
                    <label class="control-label">
                        <span class="info-row-span1">联系电话</span>
                        <span class="info-row-span">${vipUser.phoneModel}</span>
                    </label>
                </div>
            </div>
            <div class="control-group">
                <div class="info-row-item">
                    <label class="control-label">
                        <span class="info-row-span1">关注</span>
                        <span class="info-row-span">${followerNum}</span>
                    </label>
                    <label class="control-label">
                        <span class="info-row-span1">粉丝</span>
                        <span class="info-row-span">${followingNum}</span>
                    </label>
                </div>
            </div>
            <div class="control-group">
                <div class="info-row-item">
                    <label class="control-label">
                        <span class="info-row-span1">发起的投票</span>
                        <span class="info-row-span">${topicCount}</span>
                    </label>
                    <label class="control-label">
                        <span class="info-row-span1">参与的投票</span>
                        <span class="info-row-span">${initiatingTopic}</span>
                    </label>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>