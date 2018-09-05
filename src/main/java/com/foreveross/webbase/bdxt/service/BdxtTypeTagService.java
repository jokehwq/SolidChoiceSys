package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtTypeTag;

public interface BdxtTypeTagService extends ICrudService<BdxtTypeTag> {
    //判断该类型下是否已存在该标签
    int queryTotalByName(BdxtTypeTag bdxtTypeTag);
    //保存推荐标签信息
    int saveBdxtTypeTagInfo(BdxtTypeTag bdxtTypeTag);
}