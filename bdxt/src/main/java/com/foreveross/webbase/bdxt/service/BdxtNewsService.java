/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.web.app.entity.request.NewsInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtNews;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 资讯信息Service
 * @author tanjinhua
 * @version 2018-01-29
 */
public interface BdxtNewsService extends ICrudService<BdxtNews> {

    /**
     * 新增/修改资讯
     * @param news
     * @param file
     * @return
     */
    public boolean saveOrUpdateNews(BdxtNews news, MultipartFile file) throws Exception;

    /**
     * 删除资讯
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delectNews(String id) throws Exception;


    //---------------------------前端页面接口--------------------------------------------------

    /**
     * 获取资讯列表---带分页
     * @param newsInfoReq
     * @return
     */
    public CommonResponse queryNewsInfo(NewsInfoReq newsInfoReq);

    /**
     * 获取我的资讯列表---带分页
     * @param newsInfoReq
     * @return
     */
    public CommonResponse queryNewsInfoByUser(NewsInfoReq newsInfoReq);


}