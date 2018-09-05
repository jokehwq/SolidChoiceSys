package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtActivityOrder;

import java.util.List;
import java.util.Map;

/**
 * 活动订单信息DAO接口
 * @author wangkun
 * @version 2018-02-03
 */
@MyBatisDao
public interface BdxtActivityOrderDao extends CrudDao<BdxtActivityOrder> {

    //app查询用户活动订单列表
    List<BdxtActivityOrder> queryActivityOrderInfo(Map<String, Object> map);
    //查询活动下支付列表
    List<BdxtActivityOrder> findApplyPageList(BdxtActivityOrder bdxtActivityOrder);

    //活动进行中 结束活动更新活动订单为待支付
    void updateOrder(BdxtActivityOrder order);
}