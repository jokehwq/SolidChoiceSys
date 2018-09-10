package com.foreveross.webbase.solidchoice.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.solidchoice.entity.Topic;

public interface TopicService extends ICrudService<Topic> {
    public String findTopicCount(String userId);
}
