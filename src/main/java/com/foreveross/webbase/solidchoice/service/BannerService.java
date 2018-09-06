package com.foreveross.webbase.solidchoice.service;

import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.solidchoice.entity.Banner;

import java.util.List;

/**
 * 活动订单信息Service
 * @author wangkun
 * @version 2018-02-03
 */
public interface BannerService {

    //查询父级字典信息
    List<Banner> selectBannerList();
    //获取字典列表
    CommonResponse queryDictInfo(Banner banner);

}