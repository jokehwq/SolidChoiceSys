package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface BdxtAttachmentDao extends CrudDao<BdxtAttachment> {

    int addAttachInfo(Map<String, Object> map);
    /**
     * 查询用户作品信息
     * @param userId 用户id
     * @param attachType 附件类型 1-图片 2-视频
     */

    List<BdxtAttachment> queryUserAttachInfo(@Param("bdxtUserId")String userId,
                                             @Param("attachType") Integer attachType);
    //查询精彩瞬间列表
    BdxtAttachment queryMomentInfo();
     //查询最佳前5作品列表
    List<BdxtAttachment> queryTopAttInfo();
    //查询最佳作品
    List<BdxtAttachment> queryBestAttInfo();
}