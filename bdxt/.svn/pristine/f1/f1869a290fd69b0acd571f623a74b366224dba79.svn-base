package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtActivity;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.entity.BdxtUserCard;
import com.foreveross.webbase.bdxt.entity.BdxtUserQuote;
import com.foreveross.webbase.bdxt.web.app.entity.request.ActivityQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserApplyReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserQuoteReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BdxtActivityService extends ICrudService<BdxtActivity> {
    //保存活动信息
    int saveActivityInfo(BdxtActivity bdxtActivity, MultipartFile file);
    //更新活动信息
    int UpdateActivityInfo(BdxtActivity bdxtActivity);
    //根据不同条件查询活动信息
    CommonResponse queryActivityInfoByCondition(ActivityQueryReq activityQueryReq);

    /**
     * 查询活动详情
     * @param id 活动id
     * @return
     */
    CommonResponse detail(String id,BdxtUser bdxtUser);
    //活动报名
    CommonResponse apply(UserQuoteReq userQuoteReq, BdxtUser bdxtUser);
    //查看用户活动报名列表
    CommonResponse queryApplyInfo(UserApplyReq userApplyReq,BdxtUser bdxtUser);
    //查询该活动下报名用户信息
    List<BdxtUserQuote> queryActivityUserQuoteInfo(BdxtUser bdxtUser);
    //查询该活动下打卡信息
    List<BdxtUserCard> queryUserCardInfo(BdxtUser bdxtUser);
}