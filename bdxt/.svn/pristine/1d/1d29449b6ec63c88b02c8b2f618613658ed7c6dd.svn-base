
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtDict;
import com.foreveross.webbase.bdxt.web.app.entity.request.DictInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;

import java.util.List;

public interface BdxtDictService extends ICrudService<BdxtDict> {
    //查询父级字典信息
    List<BdxtDict> queryParentInfo();
    //获取字典列表
    CommonResponse queryDictInfo(DictInfoReq dictInfoReq);
}