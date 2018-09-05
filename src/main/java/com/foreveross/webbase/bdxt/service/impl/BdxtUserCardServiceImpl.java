package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.dao.BdxtUserCardDao;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.entity.BdxtUserCard;
import com.foreveross.webbase.bdxt.entity.BdxtUserCardTime;
import com.foreveross.webbase.bdxt.service.BdxtUserCardService;
import com.foreveross.webbase.bdxt.web.app.entity.request.UserCardReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.CommonUtils;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户打卡信息Service
 * @author wangkun
 * @version 2018-03-22
 */
@Service
public class BdxtUserCardServiceImpl extends CrudService<BdxtUserCardDao, BdxtUserCard> implements BdxtUserCardService {


    @Resource
    private BdxtUserCardDao bdxtUserCardDao;

    /**
     * 用户打卡
     * @param userCardReq 打卡传入对象
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public CommonResponse userCard(UserCardReq userCardReq,
                                   BdxtUser bdxtUser) {
        Map<String, Object> userCardMap = CommonUtils.getKeyAndValue(userCardReq);
        userCardMap.put("bdxtUserId", bdxtUser.getId());
        userCardMap.put("bdxtActivityId", userCardReq.getBdxtActivityId());
        userCardMap.put("createBy", bdxtUser.getRealName());
        userCardMap.put("updateBy", bdxtUser.getRealName());
        userCardMap.put("day",DateUtils.getDateByHour());
        userCardMap.put("nextDay",DateUtils.getDateByFiveHours());
        //step1 查询是否存在用户打卡记录
        String  userCardId=bdxtUserCardDao.queryCardDetailByMap(userCardMap);
        if(StringUtils.isNotBlank(userCardId)){
            //step2 更新用户打卡记录信息
            bdxtUserCardDao.updateUserCardInfoByMap(userCardMap);
            userCardMap.put("userCardId",userCardId);
        }else {
            //step2 添加用户打卡记录信息
            bdxtUserCardDao.addUserCardInfoByMap(userCardMap);
            userCardMap.put("userCardId",userCardMap.get("id"));
        }
        //step3 添加用户打卡时间记录信息
        userCardMap.put("clockTime", DateUtils.getDateTime(userCardReq.getClockTime()));//打卡日期
        userCardMap.put("clockHourTime",DateUtils.getHourTime(userCardReq.getClockTime()));//打卡时分秒
         int count=bdxtUserCardDao.addUserCardTimeByMap(userCardMap);
         if (count > 0) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        }
        return new CommonResponse.Builder(false, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
    }


    /**
     * 用户打卡列表信息
     * @param bdxtUser    传入用户对象
     * @return
     */
    @Override
    public CommonResponse queryUserCardInfo(UserCardReq userCardReq,BdxtUser bdxtUser) {
        if (userCardReq.getPageNo() != null && userCardReq.getPageSize() != null) {
            PageHelper.startPage(userCardReq.getPageNo(), userCardReq.getPageSize());
        }
        List<Map<String,Object>> mapList=new LinkedList<>();
        //step1 查询用户打卡列表
        List<BdxtUserCard> userCardList = bdxtUserCardDao.queryUserCardInfoById(bdxtUser.getId());
        if (CollectionUtils.isNotEmpty(userCardList)) {
            for(BdxtUserCard userCard:userCardList){
                Map<String,Object> map=new ConcurrentHashMap<>();
                //step2 获取打卡时间记录
                List<BdxtUserCardTime> cardTimeList=bdxtUserCardDao.queryCardTimeInfoById(userCard.getId());
                map.put("userCard",userCard);
                map.put("userCardTime",cardTimeList);
                mapList.add(map);
            }
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(),mapList).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }


    //更新用户打卡信息
    @Override
    @Transactional(readOnly = false)
    public int updateBdxtUserCardInfo(BdxtUserCard bdxtUserCard) {
        User user = UserUtils.getUser();
        bdxtUserCard.setUpdateBy(user);
        return bdxtUserCardDao.update(bdxtUserCard);
    }
}