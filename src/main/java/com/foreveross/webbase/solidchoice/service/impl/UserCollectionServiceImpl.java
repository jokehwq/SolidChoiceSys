package com.foreveross.webbase.solidchoice.service.impl;


import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.solidchoice.dao.UserCollectionMapper;
import com.foreveross.webbase.solidchoice.entity.UserCollection;
import com.foreveross.webbase.solidchoice.service.UserCollectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly=false)
public class UserCollectionServiceImpl extends CrudService<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Resource
    private UserCollectionMapper userCollectionMapper;

    @Override
    public String findInitiatingTopicCount(String userId) {
        return userCollectionMapper.findInitiatingTopicCount(userId);
    }
}
