
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.*;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtUserDetail;

public interface BdxtUserDetailService extends ICrudService<BdxtUserDetail> {
    //app 更新用户明细
    CommonResponse updateUserDetail(UserInfoReq userInfoReq,BdxtUser bdxtUser);
    //app 获取用户明细
    CommonResponse queryUserDetail(BdxtUser bdxtUser);
    //app 添加议价预约
    CommonResponse addBarginReser(UserBarginReserReq userBarginReserReq, BdxtUser bdxtUser);
    //app 我的议价预约列表
    CommonResponse queryUserBarginReserInfo(BdxtUser bdxtUser);

    /**
     * app 删除我的议价预约信息
     * @param id 议价预约id
     * @return
     */
    CommonResponse delUserBarginReserInfo(String  id);
    //app 更新我的议价预约信息
    CommonResponse updateUserBarginReserInfo(UserBarginReserReq userBarginReserReq);
    //app 添加网拍报价
    CommonResponse addQuoteRacket(UserQuoteRacketReq userBarginReserReq, BdxtUser bdxtUser);
    //app 我的网拍报价列表
    CommonResponse queryUserQuoteRacketInfo(BdxtUser bdxtUser);

    /**
     * app 删除我的网拍报价信息
     * @param id 网拍报价id
     */

    CommonResponse delUserQuoteRacketInfo(String id);
    //app 更新我的网拍报价信息
    CommonResponse updateUserQuoteRacketInfo(UserQuoteRacketReq userQuoteRacketReq);
    //app 添加买家秀报价
    CommonResponse addQuoteBuyer(UserQuoteBuyerReq userQuoteBuyerReq, BdxtUser bdxtUser);
    //app 获取用户报价信息
    CommonResponse queryUserQuoteInfo(BdxtUser bdxtUser);

    /**
     * 用户提现
     */
    CommonResponse userFund(BdxtUser bdxtUser,UserFundReq userFundReq);
}