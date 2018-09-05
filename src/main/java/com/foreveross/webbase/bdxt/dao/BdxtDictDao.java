/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典信息DAO接口
 * @author wangkun
 * @version 2018-02-05
 */
@MyBatisDao
public interface BdxtDictDao extends CrudDao<BdxtDict> {
    //查询父级字典信息
    List<BdxtDict> queryParentInfo();
    /**
     * 根据code与name查询活动类型列表
     * @param code 编码
     * @param name 编码名称
     * @return
     */
    List<BdxtDict> queryDictListByCondition(@Param("code")String code,@Param("name") String name);
}