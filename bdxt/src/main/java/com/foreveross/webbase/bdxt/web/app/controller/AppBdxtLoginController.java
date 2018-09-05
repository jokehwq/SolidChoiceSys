package com.foreveross.webbase.bdxt.web.app.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.LoginUserReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.RegisterUserReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import com.foreveross.webbase.common.beanvalidator.RegisterUserGroup;
import com.foreveross.webbase.common.beanvalidator.SmsGroup;
import com.foreveross.webbase.common.config.ConstantsRedis;
import com.foreveross.webbase.common.utils.Constants;
import com.foreveross.webbase.common.utils.SendSmsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "LoginController", description = "用户登录模块")
@RestController
@RequestMapping("/api")
public class AppBdxtLoginController {

    @Resource
    private BdxtUserService bdxtUserService;


    @ApiOperation(value = "登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResponse login(@RequestBody @Valid LoginUserReq loginUserReq) {
        return bdxtUserService.login(loginUserReq);
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/outlogin", method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse outlogin(@LoginUser BdxtUser bdxtUser) {
        int res = bdxtUserService.outLogin(bdxtUser);
        if(res != 0){
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }else {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        }
    }

    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @RequestMapping(value = "/sendTel/{phone}/{isRegister}", method = RequestMethod.GET)
    public CommonResponse sendUserCode(@PathVariable("phone") String phone,
                                       @ApiParam(name = "isRegister", value = "是否注册用，注册传true，其他传false", required = true)
                                       @PathVariable("isRegister") Boolean isRegister) {
        SendSmsResponse smsRes = new SendSmsResponse();
        try {
            int total = bdxtUserService.queryTotalByName(phone);
            if (isRegister) {
                //step1..判断手机号是否已注册
                if (total > 0) {
                    return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_DUPLICATED_ACCOUNT.getMsg(),
                            ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
                }
            } else {
                if (total==0) {
                    return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_NO_DUPLICATED_ACCOUNT.getMsg(),
                            ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
                }
            }
            smsRes = SendSmsUtils.sendSms(ConstantsRedis.REDIS_KEY_SMS_PREFIX.getMsg() + phone, phone);
            if (smsRes.getCode() != null
                    && smsRes.getCode().equals("OK")) {
                return new CommonResponse.Builder(true, ConstantsEnum.USER_SUCCESS_SMS_CODE.getMsg(),
                        ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.USER_NO_SUCCESS_SMS_CODE.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), smsRes.getMessage()).build();
    }


    @ApiOperation(value = "注册", notes = "用户注册")
    @RequestMapping(value = "/registerOne", method = RequestMethod.POST)
    public CommonResponse registerOne(@RequestBody @Validated({SmsGroup.class})
                                      RegisterUserReq registerUserReq) {
        return bdxtUserService.saveUserInfo(registerUserReq);
    }

    @ApiOperation(value = "完善个人信息", notes = "完善个人信息")
    @RequestMapping(value = "/registerTwo", method = RequestMethod.POST)
    public CommonResponse registerTwo(@RequestBody @Validated({RegisterUserGroup.class})
                                      RegisterUserReq registerUserReq) {
        return bdxtUserService.updateUserInfo(registerUserReq);
    }

    /*@ApiOperation(value = "验证用户是否存在", notes = "验证用户是否存在")
    @RequestMapping(value = "/updatePasswordOne/{phone}",method = RequestMethod.GET)
    public CommonResponse updatePasswordOne(@PathVariable("phone")String phone){
        return bdxtUserService.updatePasswordOne(phone);
    }*/

    @ApiOperation(value = "忘记密码", notes = "忘记密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResponse updatePassword(@RequestBody @Validated({SmsGroup.class})
                                         RegisterUserReq updatePwdReq) {
        return bdxtUserService.updatePassword(updatePwdReq);
    }

    @ApiOperation(value = "重置密码",notes = "重置密码")
    @RequestMapping(value = "/initPwd",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse initPwd(@LoginUser BdxtUser bdxtUser,
                                  @RequestBody @Validated({EditGroup.class}) RegisterUserReq  updatePwdReq){
        return  bdxtUserService.initPwd(bdxtUser,updatePwdReq);
    }
}
