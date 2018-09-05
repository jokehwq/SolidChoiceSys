package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtUserDetail;

import java.util.Map;


@MyBatisDao
public interface BdxtUserDetailDao extends CrudDao<BdxtUserDetail> {
    //保存用户明细信息
    int saveUserDetail(Map<String, Object> userDetailMap);
    //更新用户明细信息
    int updateUserDetail(Map<String, Object> userDetailMap);
}