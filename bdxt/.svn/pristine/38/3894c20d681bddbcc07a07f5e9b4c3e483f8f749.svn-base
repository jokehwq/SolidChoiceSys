package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtActivityOrderService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.ActivityOrderReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "ActivityOrderController",description = "订单模块")
@RestController
@RequestMapping("/api")
public class AppBdxtActivityOrderController {

    @Resource
    private BdxtActivityOrderService bdxtActivityOrderService;


    @RequestMapping(value = "/queryOrderInfo",method = RequestMethod.POST)
    @ApiOperation(value = "我的订单信息", notes = "我的订单信息")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryOrderInfo(@RequestBody ActivityOrderReq orderReq,
                                         @LoginUser BdxtUser bdxtUser) {
        return bdxtActivityOrderService.queryOrderInfo(orderReq,
                bdxtUser);
    }
}
