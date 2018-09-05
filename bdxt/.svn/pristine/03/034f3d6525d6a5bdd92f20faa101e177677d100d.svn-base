package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtActivityOrderDao;
import com.foreveross.webbase.bdxt.dao.BdxtUserDao;
import com.foreveross.webbase.bdxt.dao.BdxtUserFundRecordDao;
import com.foreveross.webbase.bdxt.dao.BdxtUserQuoteDao;
import com.foreveross.webbase.bdxt.entity.BdxtActivityOrder;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.entity.BdxtUserFundRecord;
import com.foreveross.webbase.bdxt.entity.BdxtUserQuote;
import com.foreveross.webbase.bdxt.service.BdxtActivityOrderService;
import com.foreveross.webbase.bdxt.web.app.entity.request.ActivityOrderReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.CommonUtils;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 活动订单信息Service
 * @author wangkun
 * @version 2018-02-03
 */
@Service
public class BdxtActivityOrderServiceImpl extends CrudService<BdxtActivityOrderDao, BdxtActivityOrder> implements BdxtActivityOrderService {


    @Autowired
    private BdxtActivityOrderDao bdxtActivityOrderDao;

    @Autowired
    private BdxtUserQuoteDao bdxtUserQuoteDao;
    @Autowired
    private BdxtUserDao bdxtUserDao;
    @Autowired
    private BdxtUserFundRecordDao bdxtUserFundRecordDao;

    //插入订单
    @Override
    @Transactional(readOnly = false)
    public void saveActivityOrderInfo(BdxtActivityOrder bdxtActivityOrder) {
        User user = UserUtils.getUser();
        bdxtActivityOrder.setOrderNumber(CommonUtils.getOrderNumByUUId());
        bdxtActivityOrder.setCreateBy(user);
        bdxtActivityOrder.setUpdateBy(user);
        bdxtActivityOrderDao.insert(bdxtActivityOrder);

        BdxtUserQuote bdxtUserQuote=new BdxtUserQuote();
        bdxtUserQuote.setId(bdxtActivityOrder.getUserQuoteId());
        bdxtUserQuote.setQuoteStatus(3);

        bdxtUserQuote.setUpdateBy(user);
        bdxtUserQuoteDao.update(bdxtUserQuote);
    }

    /**
     * 查询用户订单信息
     * @param orderReq 订单传入参数
     * @param bdxtUser 用户传入对象
     * @return
     */
    @Override
    public CommonResponse queryOrderInfo(ActivityOrderReq orderReq, BdxtUser bdxtUser) {
        if (orderReq.getPageNo() != null && orderReq.getPageSize() != null) {
            PageHelper.startPage(orderReq.getPageNo(), orderReq.getPageSize());
        }
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("userId",bdxtUser.getId());
        map.put("orderStatus",orderReq.getOrderStatus());
        List<BdxtActivityOrder> activityOrderList=bdxtActivityOrderDao.queryActivityOrderInfo(map);
        if (CollectionUtils.isNotEmpty(activityOrderList)) {
            PageInfo<BdxtActivityOrder> pageInfo = new PageInfo<>(activityOrderList);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }

    @Override
    public Page<BdxtActivityOrder> findApplyPageList(Page<BdxtActivityOrder> page, BdxtActivityOrder bdxtActivityOrder) {
             bdxtActivityOrder.setPage(page);
             page.setList(bdxtActivityOrderDao.findApplyPageList(bdxtActivityOrder));
            return page;

    }

    //更新活动订单信息
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public int updateBdxtActivityOrderInfo(BdxtActivityOrder bdxtActivityOrder) {
        BdxtActivityOrder bdxtActivityOrder_update = get(bdxtActivityOrder.getId());
        //step1  判断该活动状态为待支付
        if(bdxtActivityOrder_update.getActivityStatus()!=6){
            return 2;
        }
        //更新订单支付信息---已支付
        bdxtActivityOrder_update.setOrderStatus(3);
        bdxtActivityOrder_update.setOrderTime(new Date());
        bdxtActivityOrder_update.setRemark(bdxtActivityOrder.getRemark());
        User user = UserUtils.getUser();
        bdxtActivityOrder_update.setUpdateBy(user);
        bdxtActivityOrder_update.setOrderAmount(bdxtActivityOrder.getOrderAmount());
        bdxtActivityOrderDao.update(bdxtActivityOrder_update);
        //更新用户金额信息
        BdxtUser bdxtUser = bdxtUserDao.get(bdxtActivityOrder_update.getBdxtUserId());
        BigDecimal capital = bdxtUser.getCapital();
        BigDecimal add_capital = new BigDecimal(bdxtActivityOrder.getOrderAmount());
        BigDecimal capital_res = capital.add(add_capital);
        bdxtUserDao.updateCapital(capital_res,bdxtUser.getId());
        //插入流水
        BdxtUserFundRecord bdxtUserFundRecord = new BdxtUserFundRecord();
        bdxtUserFundRecord.setId(UUID.randomUUID().toString());
        bdxtUserFundRecord.setBxdtUserId(bdxtUser.getId());
        bdxtUserFundRecord.setCapital(add_capital);
        bdxtUserFundRecord.setStatus(1);
        bdxtUserFundRecord.setCapitalType(1);
        bdxtUserFundRecord.setCreateBy(user);
        bdxtUserFundRecordDao.insert(bdxtUserFundRecord);
        return 1;
    }
}