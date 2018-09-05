package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtDistrict;

public interface BdxtDistrictService extends ICrudService<BdxtDistrict> {
    //app 获取区域列表信息
    CommonResponse queryDistrictInfo();
}