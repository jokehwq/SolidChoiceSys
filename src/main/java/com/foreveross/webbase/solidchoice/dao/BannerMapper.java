package com.foreveross.webbase.solidchoice.dao;


import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.solidchoice.entity.Banner;

import java.util.List;

@MyBatisDao
public interface BannerMapper extends CrudDao<Banner> {
    //查询banner列表
    List<Banner> selectBannerList();
    //根据banner简介搜索banner内容
/*    List<Banner>  queryDictListByCondition(@Param("id")Integer id,@Param("text") String text);*/
}