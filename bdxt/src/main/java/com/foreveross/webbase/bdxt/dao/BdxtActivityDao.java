package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.bdxt.entity.BdxtActivity;
import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BdxtActivityDao extends CrudDao<BdxtActivity> {
    //app 根据不同条件筛选活动列表信息
    List<BdxtActivity> queryActivityInfoByCondition(Map<String,Object> map);
    //app 查看活动详情
    BdxtActivity detail(@Param("id")String id,@Param("userId") String userId);
    //查看我的活动报名记录
    List<BdxtActivity> queryApplyInfo(@Param("userId")String userId,
                                      @Param("quoteStatus") Integer quoteStatus);
    //job 批量更新过期活动
    void updateActivityInfo(@Param("ids")List<String> ids);
    //job 查询活动列表
    List<BdxtActivity> queryActivityList();
}