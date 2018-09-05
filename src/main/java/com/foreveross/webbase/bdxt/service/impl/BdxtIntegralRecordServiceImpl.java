package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtIntegralRecordDao;
import com.foreveross.webbase.bdxt.entity.BdxtActivityOrder;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralRecord;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtIntegralRecordService;
import com.foreveross.webbase.bdxt.web.app.entity.request.IntegralQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 积分信息service
 * @author wangkun
 * @version 2018-02-03
 */
@Service
public class BdxtIntegralRecordServiceImpl extends CrudService<BdxtIntegralRecordDao, BdxtIntegralRecord>
        implements BdxtIntegralRecordService {


    @Autowired
    private BdxtIntegralRecordDao bdxtIntegralRecordDao;


    /**
     * 查询用户积分信息
     * @param bdxtUser 用户对象
     * @return
     */
    @Override
    public CommonResponse queryIntegralInfo(BdxtUser bdxtUser) {
        Map<String, Object> recordMap = bdxtIntegralRecordDao.queryIntegralInfo(bdxtUser.getId());
        BigDecimal restCapital= CommonUtils.sub(recordMap.get("inCapital").toString(),
                recordMap.get("outCapital").toString());
        recordMap.put("surPlusCapital",restCapital);
        recordMap.put("inCapital",recordMap.get("inCapital").toString());
        recordMap.put("outCapital",recordMap.get("outCapital").toString());
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), recordMap).build();
    }

    /**
     * 查询用户积分记录
     * @param integralQueryReq 积分查询传入对象
     * @param bdxtUser 用户对象
     * @return
     */
    @Override
    public CommonResponse queryIntegralRecord(IntegralQueryReq integralQueryReq,
                                              BdxtUser bdxtUser) {
        if (integralQueryReq.getPageNo() != null && integralQueryReq.getPageSize() != null) {
            PageHelper.startPage(integralQueryReq.getPageNo(), integralQueryReq.getPageSize());
        }
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("userId",bdxtUser.getId());
        map.put("integralType",integralQueryReq.getIntegralType());
        List<BdxtIntegralRecord> integralRecordList=bdxtIntegralRecordDao.queryIntegralRecordInfo(map);
        if (CollectionUtils.isNotEmpty(integralRecordList)) {
            PageInfo<BdxtIntegralRecord> pageInfo = new PageInfo<>(integralRecordList);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }
}