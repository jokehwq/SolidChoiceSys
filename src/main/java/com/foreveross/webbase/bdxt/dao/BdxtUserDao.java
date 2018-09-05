package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

@MyBatisDao
public interface BdxtUserDao extends CrudDao<BdxtUser> {
    /**
     * app注册判断用户是否已存在
     * @param phone 手机号码
     * @return
     */
    int queryTotalByName(@Param("phone")String phone,@Param("loginOldPwd") String loginOldPwd);
    //app保存用户信息
    int saveUserInfo(Map<String, Object> userMap);
    //app更新用户密码
    int updateUserPwd(Map<String, Object> pwdMap);
    //app 完善个人信息
    int updateUserInfo(Map<String, Object> userInfoMap);
    //app 登录
    BdxtUser queryUserInfoByMap(Map<String, Object> map);
    //通过OpenID获取用户信息
    BdxtUser getByopenid(@Param("openId")String openId);

    int loginOut(@Param("openId")String openId,@Param("id")String id);

    int updateCapital(@Param("capital")BigDecimal capital, @Param("id")String id);
}