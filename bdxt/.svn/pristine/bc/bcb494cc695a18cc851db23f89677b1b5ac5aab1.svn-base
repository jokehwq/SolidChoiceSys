
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.entity.BdxtUserCard;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserCardReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;

/**
 * 用户打卡信息Service
 * @author wangkun
 * @version 2018-03-22
 */
public interface BdxtUserCardService extends ICrudService<BdxtUserCard> {

    //app 用户打卡
    CommonResponse userCard(UserCardReq userCardReq, BdxtUser bdxtUser);
    //app 查询用户打卡列表
    CommonResponse queryUserCardInfo(UserCardReq userCardReq,BdxtUser bdxtUser);
    //更新用户打卡信息
    int updateBdxtUserCardInfo(BdxtUserCard bdxtUserCard);
}