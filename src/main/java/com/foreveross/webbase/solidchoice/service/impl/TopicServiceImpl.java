package com.foreveross.webbase.solidchoice.service.impl;


import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.solidchoice.dao.TopicMapper;
import com.foreveross.webbase.solidchoice.entity.Topic;
import com.foreveross.webbase.solidchoice.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly=false)
public class TopicServiceImpl extends CrudService<TopicMapper, Topic> implements TopicService {

    @Resource
    private TopicMapper topicMapper;

    @Override
    public String findTopicCount(String userId) {
        return topicMapper.findTopicCount(userId);
    }
}
