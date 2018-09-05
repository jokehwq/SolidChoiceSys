package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtQuoteRacket;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BdxtQuoteRacketDao extends CrudDao<BdxtQuoteRacket> {
    //app 添加网拍报价信息
    int addQuoteRacket(Map<String, Object> map);

    /**
     * 我的网拍报价列表信息
     * @param userId 用户id
     * @return
     */
    List<BdxtQuoteRacket> queryUserQuoteRacketInfo(@Param("userId")String userId);
    //删除我的网拍报价信息
    int updateQuoteRacketStatus(String id);
    //更新我的网拍报价信息
    int updateUserQuoteRacketInfo(Map<String, Object> map);
    //查询我的网拍报价条数
    int queryTotalById(@Param("userId")String userId);
}