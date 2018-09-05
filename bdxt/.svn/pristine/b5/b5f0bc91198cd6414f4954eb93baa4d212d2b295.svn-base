package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.bdxt.entity.BdxtQuoteBuyer;
import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@MyBatisDao
public interface BdxtQuoteBuyerDao extends CrudDao<BdxtQuoteBuyer> {
    //添加买家秀报价信息
    int addQuoteBuyer(Map<String, Object> map);
    //查询我的买家秀报价信息
    BdxtQuoteBuyer queryUserQuoteBuyerInfo(@Param("userId")String userId);
}