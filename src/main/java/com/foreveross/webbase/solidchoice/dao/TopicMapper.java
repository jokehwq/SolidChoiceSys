package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.solidchoice.entity.Topic;

@MyBatisDao
public interface TopicMapper extends CrudDao<Topic> {

    public String findTopicCount(String userId);
}