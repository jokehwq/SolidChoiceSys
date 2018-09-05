package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtIntegralRecord;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.IntegralQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.ICrudService;

/**
 * create by wangkun
 * Date 2018/4/22
 */
public interface BdxtIntegralRecordService extends ICrudService<BdxtIntegralRecord> {
    /**
     *app 查询用户积分信息
     * @param bdxtUser 用户对象
     * @return
     */
    CommonResponse queryIntegralInfo(BdxtUser bdxtUser);

    /**
     * app 查询用户积分记录
     * @param integralQueryReq 积分查询传入对象
     * @param bdxtUser 用户对象
     * @return
     */
    CommonResponse queryIntegralRecord(IntegralQueryReq integralQueryReq,
                                       BdxtUser bdxtUser);
    //
    void save(BdxtIntegralRecord record);
}
