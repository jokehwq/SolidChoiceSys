
package com.foreveross.webbase.bdxt.service;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.AttachInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.AttachQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtAttachment;

public interface BdxtAttachmentService extends ICrudService<BdxtAttachment> {

    /**
     * 发布作品
     * @param attachInfoReq 附件传入对象
     * @return
     */
    CommonResponse addAttachInfo(AttachInfoReq attachInfoReq,BdxtUser bdxtUser);
    //查询作品列表
    CommonResponse queryAttachInfo(AttachQueryReq attachQueryReq,BdxtUser bdxtUser);

    int updateAttachmentInfo(BdxtAttachment bdxtAttachment);
    //查询精彩瞬间列表
    CommonResponse queryMomentInfo();
    //查询最佳前5作品列表
    CommonResponse queryTopAttInfo();
    //查询最佳作品与视频列表
    CommonResponse queryBestAttInfo(PageInfoReq pageReq);
}