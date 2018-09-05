package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtTagDao;
import com.foreveross.webbase.bdxt.dao.BdxtTypeTagDao;
import com.foreveross.webbase.bdxt.entity.BdxtTag;
import com.foreveross.webbase.bdxt.entity.BdxtTypeTag;
import com.foreveross.webbase.bdxt.service.BdxtTypeTagService;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * 用户标签信息Service
 * @author wangkun
 * @version 2018-02-12
 */
@Service
public class BdxtTypeTagServiceImpl extends CrudService<BdxtTypeTagDao, BdxtTypeTag> implements BdxtTypeTagService {

    @Autowired
    private BdxtTypeTagDao bdxtTypeTagDao;
    @Autowired
    private BdxtTagDao bdxtTagDao;
    //查询该类型下是否存在推荐标签
    @Override
    public int queryTotalByName(BdxtTypeTag bdxtTypeTag) {
        return bdxtTypeTagDao.queryTotalByName(bdxtTypeTag.getBxdtType(),bdxtTypeTag.getTagName());
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public int saveBdxtTypeTagInfo(BdxtTypeTag bdxtTypeTag) {
        List<BdxtTypeTag> bdxtTypeTagList=new LinkedList<>();
        User user = UserUtils.getUser();
        BdxtTag tag = new BdxtTag(bdxtTypeTag.getTagName(),
                user.getName(), user.getName());
        int count=bdxtTagDao.saveTagInfo(tag);
        if(count>0){
            bdxtTypeTag.setCreateUserBy(user.getName());
            bdxtTypeTag.setUpdateUserBy(user.getName());
            bdxtTypeTag.setBxdtTag(tag.getId());
            bdxtTypeTagList.add(bdxtTypeTag);
            bdxtTypeTagDao.saveBdxtTypeTags(bdxtTypeTagList);
            return 1;
        }
        return 0;
    }
}