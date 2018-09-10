package com.foreveross.webbase.solidchoice.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.solidchoice.entity.UserUnion;

public interface UserUnionService extends ICrudService<UserUnion> {
    public String findFollowerNum(String folloewer);
    public String findFollowingNum(String folloewing);
}
