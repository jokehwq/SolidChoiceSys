package com.foreveross.webbase.solidchoice.service.impl;


import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.solidchoice.dao.UserUnionMapper;
import com.foreveross.webbase.solidchoice.entity.UserUnion;
import com.foreveross.webbase.solidchoice.service.UserUnionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly=false)
public class UserUnionServiceImpl extends CrudService<UserUnionMapper, UserUnion> implements UserUnionService {

    @Resource
    private UserUnionMapper userUnionMapper;

    @Override
    public String findFollowerNum(String folloewer) {
        return userUnionMapper.findFollowerNum(folloewer);
    }

    @Override
    public String findFollowingNum(String folloewing) {
        return userUnionMapper.findFollowingNum(folloewing);
    }
}
