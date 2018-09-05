package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.ActivityOrderReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtActivityOrder;

/**
 * 活动订单信息Service
 * @author wangkun
 * @version 2018-02-03
 */
public interface BdxtActivityOrderService extends ICrudService<BdxtActivityOrder> {

    void saveActivityOrderInfo(BdxtActivityOrder bdxtActivityOrder);

    /**
     * 查询用户订单信息
     * @param orderReq 订单传入参数
     * @param bdxtUser 用户传入对象
     * @return
     */
    CommonResponse queryOrderInfo(ActivityOrderReq orderReq, BdxtUser bdxtUser);
    //查询活动支付列表
    Page<BdxtActivityOrder> findApplyPageList(Page<BdxtActivityOrder> bdxtActivityOrderPage, BdxtActivityOrder bdxtActivityOrder);
    //更新活动订单信息
    int updateBdxtActivityOrderInfo(BdxtActivityOrder bdxtActivityOrder);
}