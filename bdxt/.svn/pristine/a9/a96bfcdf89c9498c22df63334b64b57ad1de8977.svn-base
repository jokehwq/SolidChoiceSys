package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtTypeTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface BdxtTypeTagDao extends CrudDao<BdxtTypeTag> {
    //保存用户类型下标签信息
    int saveBdxtTypeTags(List<BdxtTypeTag> bdxtTypeTagList);
    //更新用户类型下标签信息
    int updateBdxtTypeTag(List<BdxtTypeTag> typeTagList);
    //更新用户类型下的标签状态
    int updateTypeTagStatusById(@Param("userId")String userId);
    //查询该类型下标签是否为推荐标签
    int queryBdxtTypeTagTotal(@Param("dictId") String dictId,@Param("tagId")String tagId);

    /**
     * 查询该类型下是否存在该标签
     * @param bxdtType 类型id
     * @param tagName 标签名称
     * @return
     */
    int queryTotalByName(@Param("bxdtType")String bxdtType,@Param("tagName") String tagName);
}