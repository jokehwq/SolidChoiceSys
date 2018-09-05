package com.foreveross.webbase.bdxt.web.app.controller;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtIntegralRecordService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.IntegralQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "IntegralRecordController",description = "积分模块")
@RestController
@RequestMapping("/api")
public class AppBdxtIntegralRecordController {

    @Autowired
    private BdxtIntegralRecordService bdxtIntegralRecordService;

    @RequestMapping(value = "/queryIntegral",method = RequestMethod.POST)
    @ApiOperation(value = "查询用户积分信息", notes = "查询用户积分信息")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryIntegralInfo(@LoginUser BdxtUser bdxtUser) {
        return bdxtIntegralRecordService.queryIntegralInfo(bdxtUser);
    }


    @RequestMapping(value = "/queryIntegralRecord",method = RequestMethod.POST)
    @ApiOperation(value = "查询用户积分记录", notes = "查询用户积分记录")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryIntegralRecord( @RequestBody IntegralQueryReq integralQueryReq,
                                               @LoginUser BdxtUser bdxtUser) {
        return bdxtIntegralRecordService.queryIntegralRecord(integralQueryReq, bdxtUser);
    }
}
