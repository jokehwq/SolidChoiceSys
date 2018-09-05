package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtActivityDao;
import com.foreveross.webbase.bdxt.dao.BdxtActivityOrderDao;
import com.foreveross.webbase.bdxt.dao.BdxtUserQuoteDao;
import com.foreveross.webbase.bdxt.entity.*;
import com.foreveross.webbase.bdxt.service.BdxtActivityService;
import com.foreveross.webbase.bdxt.web.app.entity.request.ActivityQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserApplyReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserQuoteReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.*;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 活动信息
 *
 * @author wangkun
 * @version 2018-02-01
 */
@Service
public class BdxtActivityServiceImpl extends CrudService<BdxtActivityDao, BdxtActivity> implements BdxtActivityService {

    private static Logger logger = Logger.getLogger(BdxtActivityServiceImpl.class);

    @Autowired
    private BdxtActivityDao bdxtActivityDao;

    @Autowired
    private BdxtUserQuoteDao bdxtUserQuoteDao;

    @Autowired
    private BdxtActivityOrderDao bdxtActivityOrderDao;

    //保存活动信息
    @Override
    @Transactional(readOnly = false)
    public int saveActivityInfo(BdxtActivity bdxtActivity, MultipartFile file) {
        logger.info("保存活动信息开始");
        try {
            if (StringUtils.isNoneBlank(bdxtActivity.getId())) {
                //step1 已暂停活动状态变成进行中
                bdxtActivity.setActivityStatus(2);
            }
            if (!file.isEmpty()) {
                //step2 删除之前的服务器图片
                BdxtActivity oldActivity = get(bdxtActivity.getId());
                if (StringUtils.isNoneBlank(oldActivity.getReferenceSampleUrl())) {
                    OssUtils.deleteFile(oldActivity.getReferenceSampleUrl());
                }
                //step3 上传新图片到oss服务器
                String filename = file.getOriginalFilename();
                //获取后缀
                String prefix = filename.substring(filename.lastIndexOf(".") + 1);
                String url = OssUtils.uploadFile(file.getInputStream(), UUID.randomUUID() + "." + prefix);
                bdxtActivity.setReferenceSampleUrl(url);
            }
            save(bdxtActivity);
            return 1;
        } catch (Exception ex) {
            logger.info("保存活动信息异常{}" + ex.getMessage());
        }
        return 0;
    }
    //更新活动信息

    @Override
    @Transactional(readOnly = false)
    public int UpdateActivityInfo(BdxtActivity bdxtActivity) {
        try {
            //step1 更新活动状态
            save(bdxtActivity);
            //notice 活动状态为待支付时 更新活动订单为待支付
            if(bdxtActivity.getActivityStatus()==6){
                User user = UserUtils.getUser();
                BdxtActivityOrder order=new BdxtActivityOrder(bdxtActivity.getId(),
                        2);
                order.setUpdateBy(user);
                bdxtActivityOrderDao.updateOrder(order);
            }
            return 1;
        } catch (Exception ex) {
            logger.info("更新活动信息异常{}" + ex.getMessage());
        }
        return 0;
    }

    /**
     * 根据不同条件查询活动列表信息
     *
     * @param activityQueryReq 活动传入对象
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public CommonResponse queryActivityInfoByCondition(ActivityQueryReq activityQueryReq) {
        if (activityQueryReq.getPageNo() != null && activityQueryReq.getPageSize() != null) {
            PageHelper.startPage(activityQueryReq.getPageNo(), activityQueryReq.getPageSize());
        }
        Map<String, Object> map = CommonUtils.getKeyAndValue(activityQueryReq);
        if (ChkUtil.isNotEmpty(activityQueryReq.getPublishTime())) {
            map = getPublishTime(activityQueryReq.getPublishTime(), map);
        }
        List<BdxtActivity> activityList = bdxtActivityDao.queryActivityInfoByCondition(map);
        if (CollectionUtils.isNotEmpty(activityList)) {
            PageInfo<BdxtActivity> pageInfo = new PageInfo<>(activityList);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }

    /**
     * 查询活动详情
     *
     * @param id 活动id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public CommonResponse detail(String id, BdxtUser bdxtUser) {
        BdxtActivity activity = bdxtActivityDao.detail(id, bdxtUser.getId());
        if (ChkUtil.isNotEmpty(activity)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), activity).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
    }

    //活动报名
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public CommonResponse apply(UserQuoteReq userQuoteReq, BdxtUser bdxtUser) {
        Map<String, Object> userQuoteMap = CommonUtils.getKeyAndValue(userQuoteReq);
        userQuoteMap.put("bdxtUserId", bdxtUser.getId());
        userQuoteMap.put("quoteStatus", Constants.QUOTE_STATUS_AUDIT);
        userQuoteMap.put("createBy", bdxtUser.getRealName());
        userQuoteMap.put("updateBy", bdxtUser.getRealName());
        //step1 判断用户是否已申请该工作
        int sum = bdxtUserQuoteDao.queryTotalById(userQuoteMap);
        if (sum > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.ACTIVITY_ERROR_USER_QUOTE_TOTAL.getMsg(),
                    ConstantsEnum.ACTIVITY_ERROR_USER_QUOTE_TOTAL.getCode(), "").build();
        }
        //step2 判断该活动是否已招募满
        userQuoteMap.put("bdxtUserId", "");
        int quoteActivityNum = bdxtUserQuoteDao.queryTotalById(userQuoteMap); //该活动已报名人数
        BdxtActivity activity = bdxtActivityDao.detail(userQuoteReq.getBdxtActivityId(), ""); //该活动招募人数
        if ((quoteActivityNum + 1) == activity.getRecruitNum()) {
            //step3 更新活动状态为进行中
            activity.setActivityStatus(Constants.ACTIVITY_STATUS_CONDUCT);
            bdxtActivityDao.update(activity);
        }
        //
        userQuoteMap.put("bdxtUserId", bdxtUser.getId());
        int count = bdxtUserQuoteDao.apply(userQuoteMap);
        if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    //查看我的活动报名列表
    @Override
    public CommonResponse queryApplyInfo(UserApplyReq userApplyReq, BdxtUser bdxtUser) {
        if (userApplyReq.getPageNo() != null && userApplyReq.getPageSize() != null) {
            PageHelper.startPage(userApplyReq.getPageNo(), userApplyReq.getPageSize());
        }
        List<BdxtActivity> bdxtUserQuoteList = bdxtActivityDao.queryApplyInfo(bdxtUser.getId(),
                userApplyReq.getQuoteStatus());
        if (CollectionUtils.isNotEmpty(bdxtUserQuoteList)) {
            PageInfo<BdxtActivity> pageInfo = new PageInfo<>(bdxtUserQuoteList);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }

    //查询该活动下报名用户信息
    @Override
    public List<BdxtUserQuote> queryActivityUserQuoteInfo(BdxtUser bdxtUser) {
        return bdxtUserQuoteDao.queryUserQuoteInfo(bdxtUser);
    }
   //查询该活动下打卡信息
    @Override
    public List<BdxtUserCard> queryUserCardInfo(BdxtUser bdxtUser) {
        return null;
    }


    /**
     * 封装发布时间
     *
     * @param publishTime 1-不限 2-一个月内 3-三个月内 4-一年内
     * @return
     */
    private Map<String, Object> getPublishTime(Integer publishTime, Map<String, Object> map) {
        try {
            switch (publishTime) {
                case 1:
                    map.put("publishTime","");
                    break;
                case 2:
                    map.put("publishTime", DateUtils.getLastTime(2, 1, "yyyy-MM-dd"));
                    break;
                case 3:
                    map.put("publishTime", DateUtils.getLastTime(2, 3, "yyyy-MM-dd"));
                    break;
                default:
                    map.put("publishTime", DateUtils.getLastTime(1, 1, "yyyy-MM-dd"));
            }
        } catch (Exception ex) {
            logger.info("封装发布时间出错：{}" + ex.getMessage());
        }
        return map;
    }
}