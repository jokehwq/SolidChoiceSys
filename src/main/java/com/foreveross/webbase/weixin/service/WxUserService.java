/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service;

import java.util.List;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserGroup;
import com.foreveross.webbase.weixin.sdk.api.UserAPI;

/**
 * 用户管理Service
 * @ClassName: WxUserService
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月29日 下午7:49:57
 */
public interface WxUserService extends ICrudService<WxUser> {

	/**
	 * 添加关注成员
	 * @param userName
	 * @param id
	 */
	public void SaveWxUser(String userName,String id);
	
	/**
	 * 修改取消关注成员的状态
	 * @param userName
	 * @param id
	 */
	public void updateUser(String userName,String id);
	
	/**
	 * 根据分组Id 查询分页用户
	 * @param page
	 * @param user
	 * @return
	 */
	Page<WxUser> findPageUserByGroupId(Page<WxUser>page,WxUser user);
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<WxUser> userPage(Page<WxUser> page, WxUser wxUser); 
	
	/**
	 * 根据openid 获取用户
	 * @return
	 */
	public WxUser getUserByOpenid(String openid); 
	
	/**
	 * 根据phone 获取用户
	 * @return
	 */
	public WxUser getUserByPhone(String phone); 
	
	/**
	 * 根据openid 获取用户角色列表
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
	 * 查询分页数据
	 * @param page 分页对象
	 * @param wxUser
	 * @return
	 */
	public Page<WxUser> findPage(Page<WxUser> page, WxUser wxUser); 
	
    /**
     * 删除用户
     * @param wxUser
     * @return
     */
    public void delUser(WxUser wxUser);
    
    /**
     * 根据大楼id获取所有该大楼的用户
     * @param unitid
     * @return
     */
	public List<WxUser> findUserListByUnitid(String unitid);
	
	/**
	 * 批量获取微信用户
	 */
	public void batchWxUser(String nextOpenId, String id);
    
	/**
	 * 根据openid在微信端获取用户
	 */
	public void obtainUser(WxUser user, String openId,String accountId,UserAPI userAPI );
	
	/**
	 * 获取所有成员
	 * @param accountId
	 * @return
	 */
	public Page<WxUser> getGroupUsers(Page<WxUser> page, String accountid);
}