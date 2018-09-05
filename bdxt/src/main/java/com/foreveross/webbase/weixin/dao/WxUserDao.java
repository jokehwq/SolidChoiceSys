/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import java.util.List;
import java.util.Map;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserGroup;

/**
 * 用户管理DAO接口
 * @author lukaicheng
 * @version 2016-12-22
 */
@MyBatisDao
public interface WxUserDao extends CrudDao<WxUser> {
	
	/**
	 * 根据openid获取单条数据
	 * @param openid
	 * @return
	 */
	public WxUser getUser(String openid);
	
	/**
	 * 修改取消关注成员的状态
	 * @param wxUser
	 */
	public void updateUser(WxUser wxUser);
	
	/**
	 * 获取所有成员
	 * @return
	 */
	public List<WxUser> getUsers(String accountId);
 
	/**
	 * 根据分组id获取成员列表
	 * @param group
	 * @return
	 */
	public List<WxUser>selectUserByGroupId(WxUser user);
	
	/**
	 * 查询列表数据
	 * @param wxUser
	 * @return
	 */
	public List<WxUser> userList(Map<String,Object> map);
	
	
    /**
     * 根据openid 获取 用户角色列表
     * @param openid
     * @return
     */
    public List<WxUserGroup> getUserWithGroupids(String openid);
    
    /**
     * 根据openid 获取 单元id
     * @param openid
     * @return
     */
    public String getUserWithUnitid(String openid);
    
    /**
     * 更新用户认证状态
     * @param wxUser
     * @return
     */
    public void updateAuthentication(WxUser wxUser);
    
    /**
     * 关联用户和单元
     * @param wxUser
     */
    public void insertUserRoom(Map<String,Object> params);
	
    /**
     * 取消用户和单元的关联
     */
    public void deleteUserRoom(Map<String, String> par);

    /**
     * 根据大楼id获取该大楼的所有用户
     * @param unitid
     * @return
     */
	public List<WxUser> findUserListByUnitid(String unitid);
	
	
    /**
     * 关联用户和组
     * @param wxUser
     */
    public void insertUserGroup(Map<String,String> params);
	
    /**
     * 取消用户和组的关联
     */
    public void deleteUserGroup(String id);
    
    /**
     * 根据openid删除用户
     * @param openid
     */
    public void deUser(String openid);
    
    /**
	 * 根据phone 获取用户
	 * @return
	 */
	public WxUser getUserByPhone(String phone); 
    
}