package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtDictDao;
import com.foreveross.webbase.bdxt.dao.BdxtTagDao;
import com.foreveross.webbase.bdxt.entity.BdxtDict;
import com.foreveross.webbase.bdxt.service.BdxtDictService;
import com.foreveross.webbase.bdxt.web.app.entity.request.DictInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.service.CrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典信息接入
 * @author wangkun
 * @version 2018-02-05
 */
@Service
public class BdxtDictServiceImpl extends CrudService<BdxtDictDao, BdxtDict> implements BdxtDictService {

    @Resource
    private BdxtDictDao bdxtDictDao;

    @Resource
    private BdxtTagDao bdxtTagDao;

    //查询父级字典信息
    @Override
    public List<BdxtDict> queryParentInfo() {
        return bdxtDictDao.queryParentInfo();
    }
    //获取字典列表
    @Override
    public CommonResponse queryDictInfo(DictInfoReq dictInfoReq) {
        List<BdxtDict> dictList= bdxtDictDao.queryDictListByCondition(dictInfoReq.getCode(),dictInfoReq.getName());
        if (CollectionUtils.isNotEmpty(dictList)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), dictList).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }



}