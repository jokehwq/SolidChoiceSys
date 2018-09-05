package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.bdxt.entity.BdxtTag;
import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 标签信息DAO接口
 * @author wangkun
 * @version 2018-01-30
 */
@MyBatisDao
public interface BdxtTagDao extends CrudDao<BdxtTag> {
    //查询用户标签列表信息
    List<BdxtTag> queryUserTagListById(Map<String,Object> map);
   //查询推荐标签列表
    List<BdxtTag> queryTagInfo();
    //查询该标签是否存在
    String queryTagTotal(@Param("tagName") String tagName);
    //保存标签信息
    int saveTagInfo(BdxtTag tag);
    //批量更新标签信息
    int updateTagInfoById(List<BdxtTag> tagList);

}