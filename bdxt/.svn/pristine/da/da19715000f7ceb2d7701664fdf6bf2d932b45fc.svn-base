
package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtUserQuoteDao;
import com.foreveross.webbase.bdxt.entity.BdxtUserQuote;
import com.foreveross.webbase.bdxt.service.BdxtUserQuoteService;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BdxtUserQuoteServiceImpl extends CrudService<BdxtUserQuoteDao, BdxtUserQuote> implements BdxtUserQuoteService {

    @Autowired
    private BdxtUserQuoteDao bdxtUserQuoteDao;

    //更新用户报价信息
    @Override
    @Transactional(readOnly = false)
    public int updateBdxtUserQuoteByCondition(BdxtUserQuote bdxtUserQuote) {
        //step1 查询之前用户报价信息
        BdxtUserQuote userQuote = bdxtUserQuoteDao.get(bdxtUserQuote.getId());
        //step2 重新拼接报价信息
        StringBuffer sb = new StringBuffer(userQuote.getQuotePrice());
        sb.append(",");
        sb.append(bdxtUserQuote.getQuotePrice());
        userQuote.setQuotePrice(sb.toString());
        userQuote.setRemark(bdxtUserQuote.getRemark());
        User user = UserUtils.getUser();
        userQuote.setUpdateBy(user);
        return bdxtUserQuoteDao.update(userQuote);
    }

    //更新用户报价状态
    @Override
    @Transactional(readOnly = false)
    public int updateStatus(BdxtUserQuote bdxtUserQuote) {
        User user = UserUtils.getUser();
        bdxtUserQuote.setUpdateBy(user);
        return bdxtUserQuoteDao.update(bdxtUserQuote);
    }
}