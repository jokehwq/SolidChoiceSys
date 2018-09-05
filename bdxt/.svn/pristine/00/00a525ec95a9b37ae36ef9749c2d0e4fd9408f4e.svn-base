
package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.bdxt.entity.BdxtBargainReservate;
import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BdxtBargainReservateDao extends CrudDao<BdxtBargainReservate> {
    //添加议价报价信息
    int addBarginReserInfo(Map<String, Object> map);

    /**
     * 查询我的议价报价列表信息
     * @param userId 用户id
     * @return
     */
    List<BdxtBargainReservate> queryUserBarginReserInfo(@Param("userId")String userId);
    //删除我的议价报价信息
    int updateUserBarginStatus(@Param("id")String id);
    //更新我的议价报价信息
    int updateUserBarginReserInfo( Map<String,Object> map);
    //查询我的议价报价条数
    int queryTotalById(@Param("userId")String userId);
}