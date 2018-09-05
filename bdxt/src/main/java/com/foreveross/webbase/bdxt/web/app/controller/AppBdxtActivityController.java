package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtActivityService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.ActivityQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserApplyReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserQuoteReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "ActivityController",description = "活动模块")
@RestController
@RequestMapping("/api")
public class AppBdxtActivityController {

    @Resource
    private BdxtActivityService bdxtActivityService;


     @RequestMapping(value = "/queryActivityInfo",method = RequestMethod.POST)
     @ApiOperation(value = "活动列表", notes = "活动列表")
     public CommonResponse queryActivityInfo(@RequestBody ActivityQueryReq activityQueryReq) {
       return bdxtActivityService.queryActivityInfoByCondition(activityQueryReq);
    }

    @RequestMapping(value = "/queryActivityDetail/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "活动详情", notes = "活动详情")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse detail(@PathVariable("id") String id,@LoginUser BdxtUser bdxtUser) {
        return bdxtActivityService.detail(id,bdxtUser);
    }

    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    @ApiOperation(value = "活动报名", notes = "活动报名")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse apply(@RequestBody UserQuoteReq userQuoteReq,
                                @LoginUser BdxtUser bdxtUser) {
        return bdxtActivityService.apply(userQuoteReq,bdxtUser);
    }

    @RequestMapping(value = "/queryApplyInfo",method = RequestMethod.POST)
    @ApiOperation(value = "我的报名记录", notes = "我的报名记录")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryApplyInfo(@RequestBody UserApplyReq userApplyReq,
                                         @LoginUser BdxtUser bdxtUser) {
        return bdxtActivityService.queryApplyInfo(userApplyReq,bdxtUser);
    }
}
