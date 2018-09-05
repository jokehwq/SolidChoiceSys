/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtComment;
import org.apache.ibatis.annotations.Param;

import javax.xml.stream.events.Comment;
import java.util.List;

/**
 * 资讯评论DAO接口
 * @author tanjinhua
 * @version 2018-02-18
 */
@MyBatisDao
public interface BdxtCommentDao extends CrudDao<BdxtComment> {

    public List<BdxtComment> getRoot(@Param("newsId") String newsId);

    public List<BdxtComment> getChild(@Param("newsId") String newsId);

}