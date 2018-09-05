package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtDictService;
import com.foreveross.webbase.bdxt.service.BdxtTagService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.DictInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.TagReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserTagReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import com.foreveross.webbase.common.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "DictController",description = "字典与标签模块")
@RestController
@RequestMapping("/api")
public class AppBdxtDictController {

    @Resource
    private BdxtDictService bdxtDictService;

    @Resource
    private BdxtTagService bdxtTagService;


    @ApiOperation(value = "获取类型列表", notes = "获取类型列表")
    @RequestMapping(value = "/queryDictInfo",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryDictInfo(@RequestBody DictInfoReq dictInfoReq){
        return bdxtDictService.queryDictInfo(dictInfoReq);
    }


    @ApiOperation(value = "获取推荐标签列表", notes = "获取推荐标签列表")
    @RequestMapping(value = "/queryTagInfo/{dictId}",method = RequestMethod.GET)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryTagInfo(
            @ApiParam(name = "dictId", value = "类型id", required = true) @PathVariable String dictId){
        return bdxtTagService.queryTagInfo(dictId);
    }


    @ApiOperation(value = "获取用户标签列表", notes = "获取用户标签列表")
    @RequestMapping(value = "/queryUserTagInfo",method = RequestMethod.GET)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryUserTagInfo(@LoginUser BdxtUser bdxtUser){
        return bdxtTagService.queryUserTagInfo(bdxtUser);
    }

    @ApiOperation(value = "添加类型下标签", notes = "添加类型下标签")
    @RequestMapping(value = "/addTags",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addTags(@RequestBody  @Validated({AddGroup.class}) TagReq tagReq,
                                  @LoginUser BdxtUser bdxtUser) {
        return bdxtTagService.addTags(tagReq,bdxtUser);
    }
    @ApiOperation(value = "添加用户标签", notes = "添加用户标签")
    @RequestMapping(value = "/addUserTags",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addUserTags(@RequestBody  @Validated({AddGroup.class}) List<UserTagReq> userTagReqs,
                                      @LoginUser BdxtUser bdxtUser){
        return bdxtTagService.addUserTags(userTagReqs, bdxtUser);
    }
    @ApiOperation(value = "批量删除用户标签", notes = "批量删除用户标签")
    @RequestMapping(value = "/updateUserTags",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse updateUserTags(@RequestBody @Validated({EditGroup.class}) List<UserTagReq> userTagReqs,
                                         @LoginUser BdxtUser bdxtUser){
        return bdxtTagService.updateUserTags(userTagReqs, bdxtUser);
    }
}
