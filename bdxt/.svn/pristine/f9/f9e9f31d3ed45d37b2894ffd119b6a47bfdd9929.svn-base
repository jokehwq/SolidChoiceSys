package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtDistrictDao;
import com.foreveross.webbase.bdxt.entity.BdxtDistrict;
import com.foreveross.webbase.bdxt.service.BdxtDistrictService;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.Constants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BdxtDistrictServiceImpl extends CrudService<BdxtDistrictDao, BdxtDistrict> implements BdxtDistrictService {

    @Autowired
    private BdxtDistrictDao bdxtDistrictDao;
    /**
     * 获取区域列表信息
     * @return
     */
    @Override
    public CommonResponse queryDistrictInfo() {
        List<BdxtDistrict> districtList= bdxtDistrictDao.queryDistrictListByCondition(Constants.DISTRICT_CODE,
                Constants.DISTRICT_CODE_NAME);
        if (CollectionUtils.isNotEmpty(districtList)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), districtList).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
    }
}