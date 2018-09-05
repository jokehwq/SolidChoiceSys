package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.bdxt.entity.BdxtUserCard;
import com.foreveross.webbase.bdxt.entity.BdxtUserCardTime;
import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BdxtUserCardDao extends CrudDao<BdxtUserCard> {
    //添加用户打卡信息
    int addUserCardInfoByMap(Map<String, Object> userCardMap);
    //查询用户打卡列表
    List<BdxtUserCard> queryUserCardInfoById(@Param("userId")String userId);
    //查询用户打卡详情
    String queryCardDetailByMap(Map<String, Object> userCardMap);
    //更新用户打卡信息
    int updateUserCardInfoByMap(Map<String, Object> userCardMap);
    //添加用户打卡时间信息
    int addUserCardTimeByMap(Map<String, Object> userCardMap);
    //查询用户打卡时间信息
    List<BdxtUserCardTime> queryCardTimeInfoById(@Param("bdxtUserCardId")String bdxtUserCardId);
}