package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.entity.BdxtUserQuote;
import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BdxtUserQuoteDao extends CrudDao<BdxtUserQuote> {
    //活动报名
    int apply(Map<String, Object> userQuoteMap);
    //查询用户是否已申请该工作
    int queryTotalById(Map<String, Object> userQuoteMap);

    /**
     * 根据不同条件查询该活动下报名用户信息
     */
    List<BdxtUserQuote> queryUserQuoteInfo(BdxtUser bdxtUser);
}