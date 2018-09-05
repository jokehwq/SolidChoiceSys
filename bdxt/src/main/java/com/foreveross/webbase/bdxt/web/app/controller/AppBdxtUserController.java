package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtUserDetailService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.*;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import com.foreveross.webbase.common.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "UserController",description = "用户模块")
@RestController
@RequestMapping("/api")
public class AppBdxtUserController {

    @Resource
    private BdxtUserDetailService bdxtUserDetailService;

    @ApiOperation(value = "更新用户明细", notes = "更新用户明细")
    @RequestMapping(value = "/updateUserDetail",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse updateUserDetail(@RequestBody @Valid UserInfoReq userInfoReq,@LoginUser BdxtUser bdxtUser){

       return bdxtUserDetailService.updateUserDetail(userInfoReq,bdxtUser);
    }

    @ApiOperation(value = "获取用户明细", notes = "获取用户明细")
    @RequestMapping(value = "/queryUserDetail",method = RequestMethod.GET)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryUserDetail(@LoginUser BdxtUser bdxtUser){
        return bdxtUserDetailService.queryUserDetail(bdxtUser);
    }

    @ApiOperation(value = "获取用户报价信息", notes = "获取用户报价信息")
    @RequestMapping(value = "/queryUserQuoteInfo",method = RequestMethod.GET)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryUserQuoteInfo(@LoginUser BdxtUser bdxtUser){

        return bdxtUserDetailService.queryUserQuoteInfo(bdxtUser);
    }

    @ApiOperation(value = "添加议价预约", notes = "添加议价预约")
    @RequestMapping(value = "/addBarginReser",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addBarginReser(@RequestBody @Validated({AddGroup.class}) UserBarginReserReq userBarginReserReq,
                                         @LoginUser BdxtUser bdxtUser){

        return bdxtUserDetailService.addBarginReser(userBarginReserReq,bdxtUser);
    }

    @ApiOperation(value = "我的议价预约列表", notes = "我的议价预约列表")
    @RequestMapping(value = "/queryUserBarginReserInfo",method = RequestMethod.GET)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryUserBarginReserInfo(@LoginUser BdxtUser bdxtUser){

        return bdxtUserDetailService.queryUserBarginReserInfo(bdxtUser);
    }

    @ApiOperation(value = "删除议价预约", notes = "删除议价预约")
    @RequestMapping(value = "/delUserBarginReserInfo",method = RequestMethod.DELETE)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse delUserBarginReserInfo(@RequestParam(value = "id", required = true)
                                                     String  id){

        return bdxtUserDetailService.delUserBarginReserInfo(id);
    }

    @ApiOperation(value = "更新议价预约", notes = "更新议价预约")
    @RequestMapping(value = "/updateUserBarginReserInfo",method = RequestMethod.PUT)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse updateUserBarginReserInfo(@RequestBody @Validated({EditGroup.class})
                                                        UserBarginReserReq userBarginReserReq){
        return bdxtUserDetailService.updateUserBarginReserInfo(userBarginReserReq);
    }

    @ApiOperation(value = "添加网拍报价", notes = "添加网拍报价")
    @RequestMapping(value = "/addQuoteRacket",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addQuoteRacket(@RequestBody @Validated({AddGroup.class}) UserQuoteRacketReq userQuoteRacketReq,
                                         @LoginUser BdxtUser bdxtUser){

        return bdxtUserDetailService.addQuoteRacket(userQuoteRacketReq, bdxtUser);
    }
    @ApiOperation(value = "我的网拍报价列表", notes = "我的网拍报价列表")
    @RequestMapping(value = "/queryUserQuoteRacketInfo",method = RequestMethod.GET)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryUserQuoteRacketInfo(@LoginUser BdxtUser bdxtUser){

        return bdxtUserDetailService.queryUserQuoteRacketInfo(bdxtUser);
    }
    @ApiOperation(value = "删除网拍报价", notes = "删除网拍报价")
    @RequestMapping(value = "/delUserQuoteRacketInfo",method = RequestMethod.DELETE)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse delUserQuoteRacketInfo(@RequestParam(value = "id", required = true)
                                                 String  id){

        return bdxtUserDetailService.delUserQuoteRacketInfo(id);
    }

    @ApiOperation(value = "更新网拍报价", notes = "更新网拍报价")
    @RequestMapping(value = "/updateUserQuoteRacketInfo",method = RequestMethod.PUT)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse updateUserQuoteRacketInfo(@RequestBody @Validated({EditGroup.class})
                                                        UserQuoteRacketReq userQuoteRacketReq){
        return bdxtUserDetailService.updateUserQuoteRacketInfo(userQuoteRacketReq);
    }

    @ApiOperation(value = "添加买家秀报价", notes = "添加买家秀报价")
    @RequestMapping(value = "/addQuoteBuyer",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addQuoteBuyer(@RequestBody @Validated({AddGroup.class}) UserQuoteBuyerReq userQuoteBuyerReq,
                                         @LoginUser BdxtUser bdxtUser){

        return bdxtUserDetailService.addQuoteBuyer(userQuoteBuyerReq, bdxtUser);
    }

    @ApiOperation(value = "提现", notes = "提现")
    @RequestMapping(value = "/userFund",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse userFund(@LoginUser BdxtUser bdxtUser,@RequestBody UserFundReq userFundReq){

        return bdxtUserDetailService.userFund(bdxtUser,userFundReq);
    }
}
