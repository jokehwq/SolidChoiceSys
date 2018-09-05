package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtTagDao;
import com.foreveross.webbase.bdxt.dao.BdxtTypeTagDao;
import com.foreveross.webbase.bdxt.entity.BdxtTag;
import com.foreveross.webbase.bdxt.entity.BdxtTypeTag;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtTagService;
import com.foreveross.webbase.bdxt.web.app.entity.request.TagReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserTagReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.ChkUtil;
import com.foreveross.webbase.common.utils.CommonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BdxtTagServiceImpl extends CrudService<BdxtTagDao, BdxtTag> implements BdxtTagService {

    private static  Map<String,Object> map=new LinkedHashMap<>();
    static {
        map.put("cf891eeccde44f7cb4a3cc6c969d0a9d","个人风格");
        map.put("9550cbb3c8404c0ca9bec34639e1b3f2","外貌标签");
        map.put("9550cbb3c8404c0ca9bec34639e1b3f1","体型标签");
        map.put("9550cbb3c8404c0ca9bec34639e1b3f3","魅力标签");

    }
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BdxtTagDao bdxtTagDao;
    @Autowired
    private BdxtTypeTagDao bdxtTypeTagDao;

    //app 获取用户标签列表
    @Override
    public CommonResponse queryUserTagInfo(BdxtUser bdxtUser) {
        Map<String,Object> paramMap=new ConcurrentHashMap<>();
        paramMap.put("userId",bdxtUser.getId());
        List<BdxtTag> tagList = bdxtTagDao.queryUserTagListById(paramMap);
        String[] str=new String[4];
        //查询用户类型是否缺少
        for(int i=0;i<tagList.size();i++) {
            str[i]=tagList.get(i).getDictId();
        }
        List<Object> list = CommonUtils.compare(str,map.keySet().toArray());
        if(ChkUtil.isNotEmpty(list)) {
            for (Object s : list) {
                BdxtTag newDict = new BdxtTag();//添加用户少的类型
                newDict.setDictId(s.toString());
                newDict.setDictName(map.get(s).toString());
                newDict.setTagId("");
                newDict.setTagName("");
                tagList.add(newDict);
            }
        }
         return getTagList(tagList);
    }

    /**
     * 获取类型下推荐标签列表
     * @param dictId 类型id
     * @return
     */
    @Override
    public CommonResponse queryTagInfo(String dictId) {
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("dictId",dictId);
        List<BdxtTag> tagList = bdxtTagDao.queryUserTagListById(map);
        return getTagList(tagList);
    }

    /**
     * app 添加用户标签信息
     * @param userTagReqs 标签类型传入对象
     * @param bdxtUser    用户传入对象
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public CommonResponse addUserTags(List<UserTagReq> userTagReqs, BdxtUser bdxtUser) {
        logger.info("添加用户标签信息输入参数：{}", userTagReqs);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        int addCount=0;
        int updateCount=0;
        List<BdxtTypeTag> bdxtTypeTagAddList = new LinkedList<>();
        List<BdxtTypeTag> bdxtTypeTagUpdateList = new LinkedList<>();
        for (UserTagReq userTags : userTagReqs) {
            String[] tagIds = userTags.getTagIds();
            if(tagIds.length>0){
                for (String tagId : tagIds) {
                    BdxtTypeTag bdxtTypeTag = new BdxtTypeTag(bdxtUser.getId(),
                            userTags.getBxdtTypeId(), tagId, bdxtUser.getRealName(), bdxtUser.getRealName());
                    //step1 查询该类型下标签是否为推荐标签
                    int exist= bdxtTypeTagDao.queryBdxtTypeTagTotal(userTags.getBxdtTypeId(),tagId);
                    if(exist>0){
                        bdxtTypeTagUpdateList.add(bdxtTypeTag);
                    }else {
                        bdxtTypeTagAddList.add(bdxtTypeTag);
                    }
                }
            }else {
                BdxtTypeTag bdxtTypeTag = new BdxtTypeTag(bdxtUser.getId(),
                        userTags.getBxdtTypeId(), "", bdxtUser.getRealName(), bdxtUser.getRealName());
                bdxtTypeTagAddList.add(bdxtTypeTag);
            }
        }
        if(ChkUtil.isNotEmpty(bdxtTypeTagAddList)) {
            //批量添加用户标签信息
            addCount = bdxtTypeTagDao.saveBdxtTypeTags(bdxtTypeTagAddList);
        }
        if(ChkUtil.isNotEmpty(bdxtTypeTagUpdateList)){
            //批量更新用户标签信息
            updateCount=bdxtTypeTagDao.updateBdxtTypeTag(bdxtTypeTagUpdateList);
        }
        costTime = System.currentTimeMillis() - startTime;
        logger.info("添加用户标签结束.[耗时:{}毫秒],返回添加用户标签结果:{},返回更新用户标签结果:{}", costTime, addCount,updateCount);
        if (addCount > 0 || updateCount>0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }

    /**
     * 更新标签信息
     * @param userTagReqs 标签类型传入对象
     * @param bdxtUser    传入用户对象
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public CommonResponse updateUserTags(List<UserTagReq> userTagReqs, BdxtUser bdxtUser) {
        logger.info("更新用户标签信息输入参数：{}", userTagReqs);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        List<BdxtTypeTag> typeTagList = new LinkedList<>();
        //step1 更新用户类型下的标签
        int count = bdxtTypeTagDao.updateTypeTagStatusById(bdxtUser.getId());
        if (count > 0) {
            for (UserTagReq userTags : userTagReqs) {
                String[] tagIds = userTags.getTagIds();
                if(tagIds.length>0){
                    for (String tagId : tagIds) {
                        BdxtTypeTag bdxtTypeTag = new BdxtTypeTag(bdxtUser.getId(),
                                userTags.getBxdtTypeId(), tagId, bdxtUser.getRealName(), bdxtUser.getRealName());
                        typeTagList.add(bdxtTypeTag);
                    }
                }else {
                    BdxtTypeTag bdxtTypeTag = new BdxtTypeTag(bdxtUser.getId(),
                            userTags.getBxdtTypeId(), "", bdxtUser.getRealName(), bdxtUser.getRealName());
                    typeTagList.add(bdxtTypeTag);
                }
            }
            //step2 批量添加用户新标签信息
            bdxtTypeTagDao.saveBdxtTypeTags(typeTagList);
            costTime = System.currentTimeMillis() - startTime;
            logger.info("更新用户标签结束.[耗时:{}毫秒],返回更新用户标签结果:{}", costTime, count);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
    }

    //添加类型下标签信息
    @Override
    @Transactional(readOnly = false)
    public CommonResponse addTags(TagReq tagReq, BdxtUser bdxtUser) {
        logger.info("添加标签信息输入参数：{}", tagReq);
        long startTime = System.currentTimeMillis();
        long costTime = 0;
        //step1 查询该标签名是否存在
        String tagId = bdxtTagDao.queryTagTotal(tagReq.getTagName());
        if (ChkUtil.isNotEmpty(tagId)) {
            return new CommonResponse.Builder(true, ConstantsEnum.TAG_ERROR_DUPLICATED_NAME.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), tagId).build();
        }
        //step2 添加标签
        BdxtTag tag = new BdxtTag(tagReq.getTagName(),
                bdxtUser.getRealName(), bdxtUser.getRealName());
        int count = bdxtTagDao.saveTagInfo(tag);
        if (count > 0) {
            costTime = System.currentTimeMillis() - startTime;
            logger.info("添加标签结束.[耗时:{}毫秒],返回添加标签结果:{}", costTime, count);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), tag.getId()).build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), null).build();
    }


    //封装公共标签列表信息
    private CommonResponse getTagList(List<BdxtTag> tagList) {
        if (CollectionUtils.isNotEmpty(tagList)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), tagList).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }
}