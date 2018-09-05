/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralConfig;
import com.foreveross.webbase.bdxt.service.BdxtIntegralConfigService;
import com.foreveross.webbase.bdxt.dao.BdxtIntegralConfigDao;

/**
 * 积分配置管理Service
 * @author wangkun
 * @version 2018-04-22
 */
@Service
public class BdxtIntegralConfigServiceImpl extends CrudService<BdxtIntegralConfigDao, BdxtIntegralConfig> implements BdxtIntegralConfigService {

    @Autowired
    private BdxtIntegralConfigDao bdxtIntegralConfigDao;

    @Override
    public BdxtIntegralConfig getConfigInfo(Integer operateTypeNews) {
        return bdxtIntegralConfigDao.getConfigInfo(operateTypeNews);
    }
}