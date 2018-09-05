package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtTag;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.TagReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserTagReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;

import java.util.List;
public interface BdxtTagService extends ICrudService<BdxtTag> {

    /**
     * 获取类型下推荐标签列表
     * @param dictId 类型id
     * @return
     */
    CommonResponse queryTagInfo(String dictId);
    //获取用户标签信息
    CommonResponse queryUserTagInfo(BdxtUser bdxtUser);

    //添加用户标签信息
    CommonResponse addUserTags(List<UserTagReq> userTagReqs, BdxtUser bdxtUser);
    //批量更新用户标签信息
    CommonResponse updateUserTags(List<UserTagReq> userTagReqs, BdxtUser bdxtUser);
    //添加类型下标签信息
    CommonResponse addTags(TagReq tagReq,BdxtUser bdxtUser);
}