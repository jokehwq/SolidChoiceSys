package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.solidchoice.entity.UserCollection;

@MyBatisDao
public interface UserCollectionMapper extends CrudDao<UserCollection> {

    public String findInitiatingTopicCount(String userId);
}