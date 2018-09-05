package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtUserDao;
import com.foreveross.webbase.bdxt.dao.BdxtUserDetailDao;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.entity.request.LoginUserReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.RegisterUserReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.config.ConstantsRedis;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.*;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户信息接入
 *
 * @author wangkun
 * @version 2018-01-29
 */
@Service
public class BdxtUserServiceImpl extends CrudService<BdxtUserDao, BdxtUser> implements BdxtUserService {





    @Autowired
    private BdxtUserDao bdxtUserDao;
    @Autowired
    private BdxtUserDetailDao bdxtUserDetailDao;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * app用户注册
     *
     * @param registerUserReq 注册传入对象
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public CommonResponse saveUserInfo(RegisterUserReq registerUserReq) {
        logger.info("用户注册输入参数：{}", registerUserReq);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        //step1 判断手机号是否已注册
        int total = bdxtUserDao.queryTotalByName(registerUserReq.getPhone(),null);
        if (total > 0) {
            return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_DUPLICATED_ACCOUNT.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        }
        //step...
        //用户信息校验
        CommonResponse resp = checkUserInfo(registerUserReq);
        if (!resp.isSuccess()) {
            return resp;
        }
        //step3 保存用户信息
        Map<String, Object> userMap = CommonUtils.getKeyAndValue(registerUserReq);
        userMap.put("password", new Sha256Hash(registerUserReq.getPassword()).toHex());
        int count = bdxtUserDao.saveUserInfo(userMap);
        if (count > 0) {
            userMap.put("userType", Constants.USER_TYPE_ORDINARY_MEMBER);
            bdxtUserDetailDao.saveUserDetail(userMap);
            costTime = System.currentTimeMillis() - startTime;
            logger.info("用户注册结束.[耗时:{}毫秒],返回用户注册结果:{}", costTime, count);
            //获取token,返回
            LoginUserReq  req = new LoginUserReq();
            req.setPhone(registerUserReq.getPhone());
            req.setPassword(registerUserReq.getPassword());
            CommonResponse login = login(req);
            Map<String, Object> req_map  = new HashMap<>();
            req_map.put("token",login.getData());
            req_map.put("userid",userMap.get("bdxtUserId").toString());
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), req_map).build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
    }

    //忘记密码
    @Override
    @Transactional(readOnly = false)
    public CommonResponse updatePassword(RegisterUserReq registerUserReq) {
        logger.info("忘记密码输入参数：{}", registerUserReq);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        //step1 判断手机号是否已注册
        int total = bdxtUserDao.queryTotalByName(registerUserReq.getPhone(),null);
        if (total == 0) {
            return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_NO_DUPLICATED_ACCOUNT.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
        }
        //step...
        //用户信息校验
        CommonResponse resp = checkUserInfo(registerUserReq);
        if (!resp.isSuccess()) {
            return resp;
        }
        Map<String, Object> pwdMap = CommonUtils.getKeyAndValue(registerUserReq);
        pwdMap.put("password", new Sha256Hash(registerUserReq.getPassword()).toHex());
        int count = bdxtUserDao.updateUserPwd(pwdMap);
        costTime = System.currentTimeMillis() - startTime;
        logger.info("忘记密码结束.[耗时:{}毫秒]{},返回忘记密码结果:{}", costTime, count);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    /**
     * 完善个人信息
     *
     * @param registerUserReq
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public CommonResponse updateUserInfo(RegisterUserReq registerUserReq) {
        Map<String, Object> userInfoMap = CommonUtils.getKeyAndValue(registerUserReq);
        int count = bdxtUserDao.updateUserInfo(userInfoMap);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    /**
     * 判断用户是否存在
     * @param phone 手机号
     * @return
     */
    @Override
    public CommonResponse updatePasswordOne(String phone) {
        //step1 判断手机号是否已注册
        int total = bdxtUserDao.queryTotalByName(phone,null);
        if (total == 0) {
            return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_NO_DUPLICATED_ACCOUNT.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.USER_ERROR_DUPLICATED_ACCOUNT.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }

    /**
     * 用户登录
     * @param loginUserReq 登录传入对象
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public CommonResponse login(LoginUserReq loginUserReq) {
        logger.info("用户登录输入参数：{}", loginUserReq);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        //notice 这块1.0版先简单登录
        Map<String, Object> map = CommonUtils.getKeyAndValue(loginUserReq);
        BdxtUser user = bdxtUserDao.queryUserInfoByMap(map);
        //判断账号是否存在
        if (user == null) {
            throw new UnknownAccountException();
        }
        //判断密码是否正确
        String hashPassword = new Sha256Hash(loginUserReq.getPassword()).toHex();
        if (!hashPassword.equals(user.getPassword())) {
            //微信登录判断
            if(!loginUserReq.getPassword().equals(user.getPassword())){
                throw new IncorrectCredentialsException();
            }
        }
        //step3 查询
        //step2 更新用户登录时间
        Map<String,Object> userMap=CommonUtils.getKeyAndValue(user);
        userMap.put("lastLoginTime",1);
        userMap.put("bdxtUserId",user.getId());
        //更新openId
        if(StringUtils.isNotBlank(loginUserReq.getOpenid())){
            userMap.put("openId",loginUserReq.getOpenid());
        }
        int count=bdxtUserDao.updateUserInfo(userMap);
        if(count>0){
            String token = jwtUtils.generateToken(user.getId());
            costTime = System.currentTimeMillis() - startTime;
            logger.info("用户登录结束.[耗时:{}毫秒],返回用户登录结果:{}", costTime, count);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), token).build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    /**
     * 查询该手机是否已注册
     * @param phone 手机号
     * @return
     */
    @Override
    public int queryTotalByName(String phone) {
        return bdxtUserDao.queryTotalByName(phone,null);
    }

    @Override
    @Transactional(readOnly = false)
    public int outLogin(BdxtUser bdxtUser) {
        return bdxtUserDao.loginOut(null,bdxtUser.getId());
    }

    @Override
    public BdxtUser getByopenid(String openid) {

        return bdxtUserDao.getByopenid(openid);
    }

    @Override
    public int updateCapital(BigDecimal capital, String id) {
        return bdxtUserDao.updateCapital(capital,id);
    }

    //重置密码
    @Override
    @Transactional(readOnly = false)
    public CommonResponse initPwd(BdxtUser bdxtUser,RegisterUserReq updatePwdReq) {
        logger.info("重置密码输入参数：{}", updatePwdReq);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        //step1 判断用户旧密码是否正确
        int total = bdxtUserDao.queryTotalByName(bdxtUser.getPhone(),
                new Sha256Hash(updatePwdReq.getLoginOldPwd()).toHex());
        if (total == 0) {
            return new CommonResponse.Builder(false, ConstantsEnum.LOGIN_ERROR_INCORRECTCREDENTIAL.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
        }
        //step2 判断2次密码是否一致
        if (!updatePwdReq.getLoginNewPwd().equals(updatePwdReq.getLoginConfirmNewPwd())) {
            return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_INCORRECT_DIFFER.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
        }
        Map<String, Object> pwdMap =new ConcurrentHashMap<>();
        pwdMap.put("phone",bdxtUser.getPhone());
        pwdMap.put("password", new Sha256Hash(updatePwdReq.getLoginNewPwd()).toHex());
        int count = bdxtUserDao.updateUserPwd(pwdMap);
        costTime = System.currentTimeMillis() - startTime;
        logger.info("重置密码结束.[耗时:{}毫秒]{},返回重置密码结果:{}", costTime, count);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }


    //用户信息相关判断封装
    private CommonResponse checkUserInfo(RegisterUserReq userReq) {
        //step2 判断该手机号是否已获取验证码
        String redisSmsCode = RedisUtil.get(ConstantsRedis.REDIS_KEY_SMS_PREFIX.getMsg() + userReq.getPhone());
        if (ChkUtil.isEmpty(redisSmsCode)) {
            return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_SMS_CODE.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
        }
        //step3 验证码是否一致
        if (!redisSmsCode.equals(userReq.getSmsCode())) {
            return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_NO_DUPLICATED_SMS_CODE.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
        }
        //step4 验证密码是否一致
        if (!userReq.getPassword().equals(userReq.getConfirmPassword())) {
            return new CommonResponse.Builder(false, ConstantsEnum.USER_ERROR_INCORRECT_DIFFER.getMsg(),
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
    }
}