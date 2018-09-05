package com.foreveross.webbase.bdxt.web.app.controller;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtUserCardService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserCardReq;
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
import javax.validation.Valid;
/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "UserCardController",description = "用户打卡模块")
@RestController
@RequestMapping("/api")
public class AppBdxtUserCardController {

    @Autowired
    private BdxtUserCardService bdxtUserCardService;

    @ApiOperation(value = "用户打卡", notes = "用户打卡")
    @RequestMapping(value = "/userCard",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse userCard(@RequestBody @Valid UserCardReq userCardReq,
                                   @LoginUser BdxtUser bdxtUser ){
        return bdxtUserCardService.userCard(userCardReq,bdxtUser);
    }

    @ApiOperation(value = "用户打卡列表", notes = "用户打卡列表 必填参数：pageNo、pageSize")
    @RequestMapping(value = "/queryUserCardInfo",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryUserCardInfo(@RequestBody UserCardReq userCardReq,@LoginUser BdxtUser bdxtUser ){
        return bdxtUserCardService.queryUserCardInfo(userCardReq,bdxtUser);
    }

}
