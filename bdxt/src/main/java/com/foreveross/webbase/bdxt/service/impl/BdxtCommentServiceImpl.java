/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.entity.request.CommentInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.bdxt.entity.BdxtComment;
import com.foreveross.webbase.bdxt.service.BdxtCommentService;
import com.foreveross.webbase.bdxt.dao.BdxtCommentDao;

import javax.annotation.Resource;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

/**
 * 资讯评论Service
 * @author tanjinhua
 * @version 2018-02-18
 */
@Service
public class BdxtCommentServiceImpl extends CrudService<BdxtCommentDao, BdxtComment> implements BdxtCommentService {
    @Resource
    private BdxtCommentDao bdxtCommentDao;
    @Override
    public CommonResponse queryCommentInfo(CommentInfoReq commentInfoReq) {
        if (commentInfoReq.getPageNo() == null || commentInfoReq.getPageSize() == null)
            return null;

        PageHelper.startPage(commentInfoReq.getPageNo(), commentInfoReq.getPageSize());
        //创建根节点列表
        List<BdxtComment> rootList = bdxtCommentDao.getRoot(commentInfoReq.getNewsId());
        if(CollectionUtils.isEmpty(rootList))
            return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), new ArrayList<>()).build();

        //找出子节点
        List<BdxtComment> childList = bdxtCommentDao.getChild(commentInfoReq.getNewsId());
        if(CollectionUtils.isNotEmpty(rootList)){
            for(BdxtComment bc : rootList){
                bc.setList(getChild(bc.getId(),childList));
            }
        }
        PageInfo<BdxtComment> pageInfo = new PageInfo<>(rootList);
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
    }

    private List<BdxtComment> getChild(String id, List<BdxtComment> list) {
        // 子菜单
        List<BdxtComment> childList = new ArrayList<>();
        for (BdxtComment bc : list) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtils.isNotBlank(bc.getParaentId())) {
                if (bc.getParaentId().equals(id)) {
                    childList.add(bc);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (BdxtComment child : childList) {// 没有url子菜单还有子菜单
                // 递归
            child.setList(getChild(child.getId(), list));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}