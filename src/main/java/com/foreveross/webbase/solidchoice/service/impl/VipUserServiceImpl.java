package com.foreveross.webbase.solidchoice.service.impl;


import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.solidchoice.dao.VipUserMapper;
import com.foreveross.webbase.solidchoice.entity.VipUser;
import com.foreveross.webbase.solidchoice.service.VipUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(readOnly=false)
public class VipUserServiceImpl extends CrudService<VipUserMapper, VipUser> implements VipUserService {

    @Resource
    private VipUserMapper userMapper;
}
