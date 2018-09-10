package com.foreveross.webbase.solidchoice.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.solidchoice.entity.UserCollection;

public interface UserCollectionService extends ICrudService<UserCollection> {

    public String findInitiatingTopicCount(String userId);
}
