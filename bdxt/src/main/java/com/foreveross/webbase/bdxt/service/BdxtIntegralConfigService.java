/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralConfig;

/**
 * 积分配置管理Service
 * @author wangkun
 * @version 2018-04-22
 */
public interface BdxtIntegralConfigService extends ICrudService<BdxtIntegralConfig> {
     //根据不同操作类型获取积分配置
    BdxtIntegralConfig getConfigInfo(Integer operateTypeNews);
}