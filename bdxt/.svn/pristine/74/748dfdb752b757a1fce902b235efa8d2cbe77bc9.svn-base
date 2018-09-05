package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.*;
import com.foreveross.webbase.bdxt.entity.*;
import com.foreveross.webbase.bdxt.service.BdxtUserDetailService;
import com.foreveross.webbase.bdxt.service.BdxtUserFundLogService;
import com.foreveross.webbase.bdxt.service.BdxtUserFundRecordService;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.entity.request.*;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.CommonUtils;
import com.foreveross.webbase.common.utils.Constants;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户明细信息接入
 *
 * @author wangkun
 * @version 2018-02-11
 */
@Service
public class BdxtUserDetailServiceImpl extends CrudService<BdxtUserDetailDao, BdxtUserDetail> implements BdxtUserDetailService {


    @Autowired
    private BdxtUserDetailDao bdxtUserDetailDao;
    @Autowired
    private BdxtUserDao bdxtUserDao;
    @Autowired
    private BdxtBargainReservateDao bdxtBargainReservateDao;
    @Autowired
    private BdxtQuoteRacketDao bdxtQuoteRacketDao;
    @Autowired
    private BdxtQuoteBuyerDao bdxtQuoteBuyerDao;

    @Autowired
    private BdxtUserFundLogService bdxtUserFundLogService;
    @Autowired
    private BdxtUserFundRecordService bdxtUserFundRecordService;
    //app 更新用户明细
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public CommonResponse updateUserDetail(UserInfoReq userInfoReq, BdxtUser bdxtUser) {
        logger.info("更新用户明细输入参数：{}", userInfoReq);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        Map<String, Object> userDetailMap = CommonUtils.getKeyAndValue(userInfoReq);
        userDetailMap.put("bdxtUserId", bdxtUser.getId());
        userDetailMap.put("userType", Constants.USER_TYPE_TALENT_MEMBER);
        userDetailMap.put("userWorkImageUrl",CommonUtils.arrayString(userInfoReq.getUserWorkImageUrl()));
        int count = bdxtUserDetailDao.updateUserDetail(userDetailMap);
        if (count > 0) {
            //step2 新增微信号与性别 保存到登录表
            bdxtUserDao.updateUserInfo(userDetailMap);
            costTime = System.currentTimeMillis() - startTime;
            logger.info("更新用户明细结束.[耗时:{}毫秒],返回更新用户明细结果:{}", costTime, count);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //token中获取用户信息
    @Override
    public CommonResponse queryUserDetail(BdxtUser bdxtUser) {
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), bdxtUser).build();
    }

    /**
     * app 添加议价预约
     * @param userBarginReserReq 议价报价传入对象
     * @param bdxtUser
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public CommonResponse addBarginReser(UserBarginReserReq userBarginReserReq, BdxtUser bdxtUser) {
        Map<String, Object> map = CommonUtils.getKeyAndValue(userBarginReserReq);
        map.put("bdxtUserId", bdxtUser.getId());
        map.put("createBy", bdxtUser.getRealName());
        map.put("updateBy", bdxtUser.getRealName());
        //最多5条
        int sum = bdxtBargainReservateDao.queryTotalById(bdxtUser.getId());
        if (sum > 5) {
            return new CommonResponse.Builder(true, ConstantsEnum.USER_ERROR_BARGIN_RESER_TOTAL.getMsg(),
                    ConstantsEnum.USER_ERROR_BARGIN_RESER_TOTAL.getCode(), "").build();
        }
        int count = bdxtBargainReservateDao.addBarginReserInfo(map);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //我的议价报价列表信息
    @Override
    public CommonResponse queryUserBarginReserInfo(BdxtUser bdxtUser) {
        List<BdxtBargainReservate> bargainReservateList = bdxtBargainReservateDao.queryUserBarginReserInfo(bdxtUser.getId());
        if (CollectionUtils.isNotEmpty(bargainReservateList)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), bargainReservateList).build();
        }

        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }

    /**
     * 删除我的议价报价信息
     *
     * @param id 议价预约id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public CommonResponse delUserBarginReserInfo(String id) {
        int count = bdxtBargainReservateDao.updateUserBarginStatus(id);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //更新我的议价报价信息
    @Override
    @Transactional(readOnly = false)
    public CommonResponse updateUserBarginReserInfo(UserBarginReserReq userBarginReserReq) {
        Map<String, Object> map = CommonUtils.getKeyAndValue(userBarginReserReq);
        int count = bdxtBargainReservateDao.updateUserBarginReserInfo(map);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //添加网拍报价
    @Override
    @Transactional(readOnly = false)
    public CommonResponse addQuoteRacket(UserQuoteRacketReq userBarginReserReq, BdxtUser bdxtUser) {
        Map<String, Object> map = CommonUtils.getKeyAndValue(userBarginReserReq);
        map.put("bdxtUserId", bdxtUser.getId());
        map.put("createBy", bdxtUser.getRealName());
        map.put("updateBy", bdxtUser.getRealName());
        //最多3条
        int sum = bdxtQuoteRacketDao.queryTotalById(bdxtUser.getId());
        if (sum > 3) {
            return new CommonResponse.Builder(true, ConstantsEnum.USER_ERROR_QUOTE_RACKET_TOTAL.getMsg(),
                    ConstantsEnum.USER_ERROR_QUOTE_RACKET_TOTAL.getCode(), "").build();
        }
        int count = bdxtQuoteRacketDao.addQuoteRacket(map);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //我的网拍报价列表
    @Override
    public CommonResponse queryUserQuoteRacketInfo(BdxtUser bdxtUser) {
        List<BdxtQuoteRacket> quoteRacketList = bdxtQuoteRacketDao.queryUserQuoteRacketInfo(bdxtUser.getId());
        if (CollectionUtils.isNotEmpty(quoteRacketList)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), quoteRacketList).build();
        }

        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }

    /**
     * app 删除我的网拍报价信息
     *
     * @param id 网拍报价id
     */
    @Override
    @Transactional(readOnly = false)
    public CommonResponse delUserQuoteRacketInfo(String id) {
        int count = bdxtQuoteRacketDao.updateQuoteRacketStatus(id);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //app 更新我的网拍报价信息
    @Override
    @Transactional(readOnly = false)
    public CommonResponse updateUserQuoteRacketInfo(UserQuoteRacketReq userQuoteRacketReq) {
        Map<String, Object> map = CommonUtils.getKeyAndValue(userQuoteRacketReq);
        int count = bdxtQuoteRacketDao.updateUserQuoteRacketInfo(map);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //app 添加买家秀报价
    @Override
    @Transactional(readOnly = false)
    public CommonResponse addQuoteBuyer(UserQuoteBuyerReq userQuoteBuyerReq, BdxtUser bdxtUser) {
        Map<String, Object> map = CommonUtils.getKeyAndValue(userQuoteBuyerReq);
        map.put("bdxtUserId", bdxtUser.getId());
        map.put("createBy", bdxtUser.getRealName());
        map.put("updateBy", bdxtUser.getRealName());
        int count = bdxtQuoteBuyerDao.addQuoteBuyer(map);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

   //获取用户报价信息
    @Override
    public CommonResponse queryUserQuoteInfo(BdxtUser bdxtUser) {
        Map<String, Object> mapList = new ConcurrentHashMap<>();
        //step1 查询我的议价报价
        List<BdxtBargainReservate> bargainReservateList = bdxtBargainReservateDao.queryUserBarginReserInfo(bdxtUser.getId());
        //step2 查询我的网拍报价
        List<BdxtQuoteRacket> quoteRacketList = bdxtQuoteRacketDao.queryUserQuoteRacketInfo(bdxtUser.getId());
        //step3 查询我的买家秀报价
        BdxtQuoteBuyer bdxtQuoteBuyer=bdxtQuoteBuyerDao.queryUserQuoteBuyerInfo(bdxtUser.getId());
        mapList.put("bargainReservate",bargainReservateList);
        mapList.put("quoteRacket",quoteRacketList);
        mapList.put("bdxtQuoteBuyer",bdxtQuoteBuyer);
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), mapList).build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public CommonResponse userFund(BdxtUser bdxtUser,UserFundReq userFundReq) {
        //验证密码
        String hex_pwd = new Sha256Hash(userFundReq.getPwd()).toHex();
        if(!bdxtUser.getPassword().equals(hex_pwd))
            return new CommonResponse.Builder(true, "密码不正确",
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        //扣减余额
        BdxtUser bdxtUser_update = bdxtUserDao.get(bdxtUser.getId());
        BigDecimal capital_ = bdxtUser_update.getCapital();
        BigDecimal subtract = capital_.subtract(userFundReq.getCapital());
        //判断是否足够
        if(subtract.intValue()<0)
            return new CommonResponse.Builder(true, "余额不足",
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        bdxtUserDao.updateCapital(subtract,bdxtUser_update.getId());
        //插入流水
        User user = UserUtils.getUser();
        BdxtUserFundRecord bdxtUserFundRecord = new BdxtUserFundRecord();
        bdxtUserFundRecord.setBxdtUserId(bdxtUser_update.getId());
        bdxtUserFundRecord.setCapital(userFundReq.getCapital());
        bdxtUserFundRecord.setBankCardNo(userFundReq.getBankCardNo());
        bdxtUserFundRecord.setHouseHolderName(userFundReq.getHouseHolderName());
        bdxtUserFundRecord.setStatus(1);
        bdxtUserFundRecord.setCapitalType(2);
        bdxtUserFundRecord.setCreateBy(user);
        bdxtUserFundRecordService.save(bdxtUserFundRecord);
        //插入提现记录
        BdxtUserFundLog bdxtUserFundLog = new BdxtUserFundLog();
        bdxtUserFundLog.setCapital(userFundReq.getCapital());
        bdxtUserFundLog.setBankCardNo(userFundReq.getBankCardNo());
        bdxtUserFundLog.setUserName(userFundReq.getHouseHolderName());
        bdxtUserFundLog.setPhone(bdxtUser_update.getPhone());
        bdxtUserFundLog.setCreateBy(user);
        bdxtUserFundLogService.save(bdxtUserFundLog);

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }
}