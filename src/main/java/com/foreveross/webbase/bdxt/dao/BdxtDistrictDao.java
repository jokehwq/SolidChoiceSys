package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface BdxtDistrictDao extends CrudDao<BdxtDistrict> {

    //根据code与name查询区域列表
    List<BdxtDistrict> queryDistrictListByCondition(@Param("code")String code,@Param("name") String name);
}