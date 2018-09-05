<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">
<head>
	<title>图文管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/weixin/wxPic/app.css" rel="stylesheet" />
	<script src="${ctxStatic}/weixin/wxPic/wxPicForm.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ckeditor/ckeditor.js" type="text/javascript"></script>
</head>
<body>
<div ng-app="myApp" ng-controller="WxImageAddCtrl">
    <ul class="nav nav-tabs">
		<shiro:hasPermission name="weixin:wxMaterialNews:view"><li><a href="${ctx}/wxnewsmsg/wxNewsMsg/list?accountid=${accountId}" style="color: #157ab5;">图文管理列表</a></li></shiro:hasPermission>
		<li class="active"><a href="${ctx}/wxnewsmsg/wxNewsMsg/form/?accountid=${accountId}">图文{{id?'编辑':'新增'}}</a></li>
	</ul>
    <div class="wrapper-md">
        <form role="form" class="panel-body m-t row form-validation" name="myForm" onbeforeunload >
        <div class="col span4">

                <div class="cover_appmsg_item" ng-repeat="item in material track by $index" ng-class="{'js_appmsg_item':$index>0}">
                    <h4 class="appmsg_title">
                        <a>{{item.title?item.title:'标题'}}</a>
                    </h4>

                    <div class="appmsg_thumb_wrp">
                        <img ng-if="item.thumbMediaUrl" class="js_appmsg_thumb appmsg_thumb"
                             ui-img-lazy="{{item.thumbMediaUrl}}?imageView2/1/w/200/h/200" ng-src="{{item.thumbMediaUrl}}">

                        <i class="appmsg_thumb default">
                            {{$index==0?'封面图片':'缩略图'}}
                        </i>
                    </div>

                    <div class="appmsg_edit_mask">
                        <a class="icon18_common edit_gray js_edit" ng-click="selectMaterial($index)"
                           href="javascript:;">
                            <i class="icon-pencil"></i>
                        </a>
                        <a ng-click="upMove($index)" class="icon18_common edit_gray js_up" ng-show="$index>0"
                           href="javascript:;"><i class="icon-arrow-up"></i></a>
                           
                         <a ng-click="downMove($index)" class="icon18_common edit_gray js_down" ng-show="$index < material.length-1"
                           href="javascript:;"><i class="icon-arrow-down"></i></a>
                           
                        <a ng-click="removeMaterial($index)" class="icon18_common edit_gray js_del" ng-show="$index>0"
                           href="javascript:;"><i class="icon-trash"></i></a>

                    </div>
                </div>

                <a class="create_access_primary add_prize" ng-click="addMaterial()" ng-hide="id">
                    <i class="icon-plus"></i>
                </a>

                <div class="form-actions form-oprate m-b m-t" style="clear:both;text-align:center;padding-left:0px;">
                    <button ng-click="wxpicSave()" class="btn btn-primary m-r" ng-disabled="saving">
                        {{saving?'保存中...':'保存'}}
                    </button>
                    <a href="${ctx}/wxnewsmsg/wxNewsMsg/list?accountid=${accountId}" class="btn">取消</a>
                </div>
            </div>
            
            <div class="col clear" style="padding-left: 22px;" ng-style="{'padding-top':currentIndex==0?0:(currentIndex-1)*98 + 160}">
                <div class="span8 padder-v prize-list ">
                    <i class="arrow arrow_in"></i>

                    <div class="form-group">
                        <label class=" w-sm v-top control-label m-r-sm c666"> 标题</label>
                        <div class="clear">
                            <input type="text" required="" class="form-control inline wxtext" ng-model="currentMate.title"
                                   placeholder="请填写标题" name="title" ui-validate="'$value'">

                              <span ng-show='myForm.title.$error.validator' class="error-tip"><i
                                      class="icon-info"></i> 请填写标题</span>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class=" w-sm v-top control-label m-r-sm c666">
                            作者
                            <small>（选填）</small>
                        </label>

                        <div class="clear">
                            <input type="text" class="form-control wxtext" ng-model="currentMate.author" placeholder="请填写作者">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label m-r-sm c666">封面

                            <small class="help-block inline" ng-if="currentIndex==0">建议大小（大图片建议尺寸：900像素 * 500像素, 1MB以内）</small>
                            <small class="help-block inline" ng-if="currentIndex>0">建议大小（小图片建议尺寸：200像素 * 200像素, 1MB以内）</small>
                        </label>

                        <div class="clear">
                            <img class="thumb_img" ng-show="currentMate.thumbMediaUrl"
                                 ng-src="{{currentMate.thumbMediaUrl}}"
                                 style="width:100px;"/>

                            <img style="cursor: pointer;" ng-click="openPhotoModal(options)" ng-hide="currentMate.thumbMediaUrl" src="${ctxStatic}/weixin/wxPic/img_holder.png"  alt=""/>

                            <input type="hidden" required="" class="form-control inline"
                                    ng-model="currentMate.thumbMediaUrl"
                                    name="thumbMediaUrl" ui-validate="'$value'">

                              <span ng-show='myForm.thumbMediaUrl.$error.validator' class="error-tip"><i
                                      class="icon-info"></i> 请选择封面</span>

                            <div class="m-t-sm">

                                <div class="m-b-sm">
                                  <!--  <label class="btn btn-sm btn-default m-r-sm ">
                                        <input type="file" nv-file-select="" uploader="uploader" name="file"
                                               class="webuploader-element-invisible" accept="image/*">
                                        选择本地图片
                                    </label>-->

                                    <button type="button" class="btn btn-sm btn-primary"
                                            ng-click="openPhotoModal(options)">从素材库选择
                                    </button>
                                </div>

                                <div class="" ng-repeat="item in uploader.queue">
                                    <div class="progress progress-sm m-b-none m-t-xs" ng-hide="item.isSuccess">
                                        <div class="progress-bar bg-info" role="progressbar"
                                             ng-style="{ 'width': item.progress + '%' }"></div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>

                <div class="form-group">
                        
                        <div class="clear">
                            <label class="i-checks radio-inline">
                                <input type="checkbox" name="showCoverPic" value=""
                                       ng-model="currentMate.showCoverPic_modal"
                                       ng-checked="currentMate.showCoverPic==1"
                                       ng-click="currentMate.showCoverPic=(currentMate.showCoverPic_modal?1:0)"/>
                                <i></i>
                            </label>
                            <label class=" w-sm v-top control-label m-r-sm c666">封面图片显示在正文中</label>
                        </div>
                    </div> 

                     <div class="form-group">
                        <label class="control-label m-r-sm c666">
                            摘要
                            <small>（选填，该摘要只在发送图文消息为单条时显示）</small>
                        </label>

                        <div class="clear">
                            <input type="text" class="form-control wxtext" ng-model="currentMate.digest">
                        </div>
                    </div> 


                    <div class="form-group contentOrigin">
                        <label class=" w-sm v-top control-label m-r-sm c666">正文内容</label>

                        <div class="clear">
                            <div  id="editor1" name="editor1" ></div>
                            <!-- <small class="help-block">最多20000个字符</small> -->
                        </div>
                    </div>

                    <div class="form-group">
                        <label class=" w-sm v-top control-label m-r-sm c666">原文链接（选填）</label>

                        <div class="clear">
                            <input type="text" class="form-control wxtext" ng-model="currentMate.contentSourceUrl"/>
                            <small class="help-block"></small>
                        </div>
                    </div> 

                </div>

            </div>
        </form>
      </div>
<!--       遮罩层 -->
     <div class='msgPop'>
			<div class='msgPopContent'>
				<div class="modal-header">
    <h4 class="modal-title">图片 <i class="icon-remove pull-right gray" ng-click="close()"></i></h4>
</div>
<div class="wx-photo">
   <div class="imgNull" ng-show="vm.imgListData.total==0">
                图库为空,请先到素材库上传!
   </div>
   <ul ng-show="vm.imgListData.total>0">
   <li ng-repeat='imgitem in vm.imgListData.images' data-id="{{imgitem.mediaId}}" ng-click="slectImg($index)">
       <img alt="图片" src="{{imgitem.thumbMediaUrl}}"> 
    </li>
   </ul>
</div>
<footer class="panel-footer text-right" ng-show="vm.imgListData.total>0">
    <paging
            class="small"
            page="vm.imgListData.pageNo"
            page-size="10"
            total="vm.imgListData.total"
            adjacent="{{adjacent}}"
            dots="{{dots}}"
            scroll-top="true"
            hide-if-empty="true"
            ul-class="pagination pagination-sm"
            active-class="active"
            disabled-class="disabled"
            show-prev-next="true"
            paging-action="refreshList({page:page})">
    </paging>
<button class="btn btn-primary pic-submit" ng-click="picSubmit()">确定</button>
</footer>
		
			</div>
		</div>
		<!-- end -->

</div>
</body>
</html>