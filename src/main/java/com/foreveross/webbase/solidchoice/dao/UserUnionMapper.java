package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.solidchoice.entity.UserUnion;

@MyBatisDao
public interface UserUnionMapper extends CrudDao<UserUnion> {

    public String findFollowerNum(String folloewer);
    public String findFollowingNum(String folloewing);
}