package com.foreveross.webbase.bdxt.dao;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralRecord;
import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BdxtIntegralRecordDao extends CrudDao<BdxtIntegralRecord> {
    //app 查询用户积分信息
    Map<String,Object> queryIntegralInfo(@Param("userId") String userId);
    //app 查询用户积分记录
    List<BdxtIntegralRecord> queryIntegralRecordInfo(Map<String, Object> map);
}