/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.web.app.entity.request.CommentInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtComment;

/**
 * 资讯评论Service
 * @author tanjinhua
 * @version 2018-02-18
 */
public interface BdxtCommentService extends ICrudService<BdxtComment> {

    /**
     * 查询评论列表
     * @param commentInfoReq
     * @return
     */
    public CommonResponse queryCommentInfo(CommentInfoReq commentInfoReq);
}