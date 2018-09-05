package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.LoginUserReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.RegisterUserReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;

import java.math.BigDecimal;

public interface BdxtUserService extends ICrudService<BdxtUser> {
    //app用户注册
    CommonResponse saveUserInfo(RegisterUserReq registerUserReq);
    //app 忘记密码
    CommonResponse updatePassword(RegisterUserReq registerUserReq);
    //app 完善个人信息
    CommonResponse updateUserInfo(RegisterUserReq registerUserReq);
    //app 判断用户是否存在
    CommonResponse updatePasswordOne(String phone);
    //app 用户登录
    CommonResponse login(LoginUserReq loginUserReq);

    /**
     * 查询该手机是否已注册
     * @param phone 手机号
     * @return
     */
    int queryTotalByName(String phone);

    /**
     * 退出登录
     * @param bdxtUser
     * @return
     */
    int outLogin(BdxtUser bdxtUser);

    /**
     * 通过openid获取用户信息
     * @param openid
     * @return
     */
    BdxtUser getByopenid(String openid);

    int updateCapital(BigDecimal capital,String id);
    //重置密码
    CommonResponse initPwd(BdxtUser bdxtUser,RegisterUserReq updatePwdReq);
}