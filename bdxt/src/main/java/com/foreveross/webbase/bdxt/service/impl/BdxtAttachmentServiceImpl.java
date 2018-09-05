
package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtAttachmentDao;
import com.foreveross.webbase.bdxt.entity.BdxtAttachment;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtAttachmentService;
import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.AttachInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.AttachQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.ChkUtil;
import com.foreveross.webbase.common.utils.CommonUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class BdxtAttachmentServiceImpl extends CrudService<BdxtAttachmentDao, BdxtAttachment>
        implements BdxtAttachmentService {

    @Autowired
    private BdxtAttachmentDao bdxtAttachmentDao;

    /**
     * 发布作品
     * @param attachInfoReq 附件传入对象
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public CommonResponse addAttachInfo(AttachInfoReq attachInfoReq, BdxtUser bdxtUser) {
        logger.info("发布作品输入参数：{}", attachInfoReq);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        Map<String, Object> map = CommonUtils.getKeyAndValue(attachInfoReq);
        map.put("bdxtUserId",bdxtUser.getId());
        map.put("attachUrl", CommonUtils.arrayString(attachInfoReq.getAttachUrl()));
        map.put("bdxtTag",CommonUtils.arrayString(attachInfoReq.getTagIds()));
        map.put("createBy", bdxtUser.getRealName());
        map.put("updateBy", bdxtUser.getRealName());
        int count=bdxtAttachmentDao.addAttachInfo(map);
        if (count > 0) {
            costTime = System.currentTimeMillis() - startTime;
            logger.info("发布作品结束.[耗时:{}毫秒],返回添加标签结果:{}", costTime, count);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
    }
    //查询作品列表
    @Override
    public CommonResponse queryAttachInfo(AttachQueryReq attachQueryReq,
                                          BdxtUser bdxtUser) {
        if (attachQueryReq.getPageNo() != null && attachQueryReq.getPageSize() != null) {
            PageHelper.startPage(attachQueryReq.getPageNo(), attachQueryReq.getPageSize());
        }
        //BdxtAttachment bdxtAttachment=new BdxtAttachment(bdxtUser.getId(),attachQueryReq.getAttachType());
        List<BdxtAttachment> attachmentList=bdxtAttachmentDao.queryUserAttachInfo(bdxtUser.getId(),
                attachQueryReq.getAttachType());
        if(CollectionUtils.isNotEmpty(attachmentList)){
            for(BdxtAttachment attachments:attachmentList){
                    //将字符串切割处理数组返回
                    String[] tagNames=attachments.getTagNames().split(",");
                    attachments.setTagName(tagNames);

            }
            PageInfo<BdxtAttachment> pageInfo = new PageInfo<>(attachmentList);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }


    //更新用户作品信息
    @Override
    @Transactional(readOnly = false)
    public int updateAttachmentInfo(BdxtAttachment bdxtAttachment) {
        //step1 查询用户作品信息
        BdxtAttachment attachment=bdxtAttachmentDao.get(bdxtAttachment.getId());
        if(attachment!=null){
            //处理用户作品url
            if(StringUtils.isNotBlank(bdxtAttachment.getAttachUrl())){
                String[] attachUrls = attachment.getAttachUrl().split(",");
                List<String> attachList= Arrays.asList(attachUrls);
                CopyOnWriteArrayList<String> arrList = new CopyOnWriteArrayList(attachList);
                for (String s : arrList) {
                    if (s.equals(bdxtAttachment.getAttachUrl())) {
                        arrList.remove(s);
                    }
                }
                //step2 未匹配的图片地址重新赋值
                String attachUrl=CommonUtils.ListToString(arrList);
                attachment.setAttachUrl(attachUrl);
            }
            //处理用户标签
            if(StringUtils.isNotBlank(bdxtAttachment.getBdxtTag())){
                String[] tags = attachment.getBdxtTag().split(",");
                List<String> tagList= Arrays.asList(tags);
                CopyOnWriteArrayList<String> arrList = new CopyOnWriteArrayList(tagList);
                for (String s : arrList) {
                    if (s.equals(bdxtAttachment.getBdxtTag())) {
                        arrList.remove(s);
                    }
                }
                //step2 未匹配的标签id重新赋值
                String tag=CommonUtils.ListToString(arrList);
                attachment.setBdxtTag(tag);
            }
            int count=bdxtAttachmentDao.update(attachment);
            if (count>0){
                return 1;
            }
        }
        return 0;
    }

    //查询精彩瞬间或最新作品
    @Override
    public CommonResponse queryMomentInfo() {
        BdxtAttachment lastAttach=bdxtAttachmentDao.queryMomentInfo();
        if(ChkUtil.isNotEmpty(lastAttach)){
            String[] attachs=lastAttach.getAttachUrl().split(",");
            //将图片字符串切割处理数组返回
            lastAttach.setAttachUrls(attachs);
            //将标签字符串切割处理数组返回
            String[] tagNames=lastAttach.getTagNames().split(",");
            lastAttach.setTagName(tagNames);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), lastAttach).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }


    //查询最佳前5作品列表
    @Override
    public CommonResponse queryTopAttInfo() {
        List<BdxtAttachment> bestAttList=bdxtAttachmentDao.queryTopAttInfo();
        if(CollectionUtils.isNotEmpty(bestAttList)){
            for(BdxtAttachment att:bestAttList){
                String url=att.getAttachUrl().split(",")[0].toString();
                att.setAttachUrl(url);
            }
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), bestAttList).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }


    @Override
    public CommonResponse queryBestAttInfo(PageInfoReq pageReq) {
        if (pageReq.getPageNo() != null && pageReq.getPageSize() != null) {
            PageHelper.startPage(pageReq.getPageNo(), pageReq.getPageSize());
        }
        List<BdxtAttachment> attachmentList=bdxtAttachmentDao.queryUserAttachInfo(null,
                null);
        if(CollectionUtils.isNotEmpty(attachmentList)){
            for(BdxtAttachment attachments:attachmentList){
                //将字符串切割处理数组返回
                String[] tagNames=attachments.getTagNames().split(",");
                attachments.setTagName(tagNames);
            }
            PageInfo<BdxtAttachment> pageInfo = new PageInfo<>(attachmentList);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }
}