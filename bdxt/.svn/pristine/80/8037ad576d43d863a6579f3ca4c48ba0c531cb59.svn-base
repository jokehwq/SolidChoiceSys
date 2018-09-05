package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtAttachmentService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.AttachInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.AttachQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.utils.Constants;
import com.foreveross.webbase.common.utils.OssUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Created by tanjinhua on 2018-2-3.
 */
@Api(value = "FileControlle",description = "附件模块")
@RestController
@RequestMapping(value = "/api")
public class AppBdxtFileController {


     @Autowired
     private BdxtAttachmentService bdxtAttachmentService;

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @RequestMapping(value = "/upload", consumes = "multipart/form-data", method = RequestMethod.POST)
    public CommonResponse upload(@RequestParam("file") MultipartFile file) {
        String url = "";
        try {
            //文件名去重
            String originalFilename = file.getOriginalFilename();
            String prefix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            url = OssUtils.uploadFile(file.getInputStream(), UUID.randomUUID().toString()+"."+prefix);
        } catch (Exception e) {
            return new CommonResponse.Builder(false, ConstantsEnum.UPLOAD_IMG_ERROR.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), url).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.UPDATE_INFO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), url).build();
    }


    @ApiOperation(value = "附件删除(文件)", notes = "附件删除(文件)")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public CommonResponse delete(
            @ApiParam(name="path", value="附件路径",required = true)
            @RequestParam(value = "path")String path) {
        try {
            OssUtils.deleteFile(path);
        } catch (Exception e) {
            return new CommonResponse.Builder(false, ConstantsEnum.UPLOAD_IMG_ERROR.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.UPDATE_INFO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
    }


    @RequestMapping(value = "/addAttachInfo",method = RequestMethod.POST)
    @ApiOperation(value = "发布作品", notes = "发布作品")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addAttachInfo(@RequestBody AttachInfoReq attachInfoReq,
                                        @LoginUser BdxtUser bdxtUser) {
        return bdxtAttachmentService.addAttachInfo(attachInfoReq,bdxtUser);
    }

    @RequestMapping(value = "/queryAttachInfo",method = RequestMethod.POST)
    @ApiOperation(value = "作品列表", notes = "作品列表")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryAttachInfo(@RequestBody AttachQueryReq attachQueryReq,
                                          @LoginUser BdxtUser bdxtUser) {
        return bdxtAttachmentService.queryAttachInfo(attachQueryReq,bdxtUser);
    }

    @RequestMapping(value = "/queryMomentInfo",method = RequestMethod.POST)
    @ApiOperation(value = "最新作品或精彩瞬间", notes = "最新作品或精彩瞬间")
    public CommonResponse queryMomentInfo() {
        return bdxtAttachmentService.queryMomentInfo();
    }

    @RequestMapping(value = "/queryTopAttInfo",method = RequestMethod.POST)
    @ApiOperation(value = "最佳前5作品列表", notes = "最佳作品列表")
    public CommonResponse queryTopAttInfo() {
        return bdxtAttachmentService.queryTopAttInfo();
    }

    @RequestMapping(value = "/queryBestAttInfo",method = RequestMethod.POST)
    @ApiOperation(value = "最佳作品与视频列表", notes = "最佳作品与视频列表")
    public CommonResponse queryBestAttInfo(@RequestBody PageInfoReq pageReq) {
        return bdxtAttachmentService.queryBestAttInfo(pageReq);
    }
}
