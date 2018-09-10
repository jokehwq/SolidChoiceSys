package com.foreveross.webbase.solidchoice.service.impl;


import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.solidchoice.dao.IntegralRecordMapper;
import com.foreveross.webbase.solidchoice.entity.IntegralRecord;
import com.foreveross.webbase.solidchoice.service.IntegralRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly=false)
public class IntegralRecordServiceImpl extends CrudService<IntegralRecordMapper, IntegralRecord> implements IntegralRecordService {

    @Resource
    private IntegralRecordMapper integralRecordMapper;
}
