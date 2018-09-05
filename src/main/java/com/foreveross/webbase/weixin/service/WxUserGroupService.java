package com.foreveross.webbase.weixin.service;

import java.util.List;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserGroup;

/**
 * 微信用户分组Service
 * @ClassName: WxUserGroupService
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月06日 下午2:16:09
 */
public interface WxUserGroupService extends ICrudService<WxUserGroup> {
	
	/**
	 * 修改分组
	 * @param wxUserGroup
	 */
	public void update(WxUserGroup wxUserGroup);
	
	/**
	 * 获取所有成员
	 * @param accountId
	 * @return
	 */
	public List<WxUser> getUsers(String accountid);
	
	/**
	 * 根据openId获取成员
	 * @param ids 用户的openId		
	 */
	public WxUser getWxUser(String ids);
	
	/**
	 * 根据groupid获取成员
	 * @param groupid 分组id
	 */
	public Page<WxUser> getWxUsers(Page<WxUser> page, String groupid, String accountid);
	
	/**
	 * 根据accountid查询分组
	 * @param accountid
	 * @return
	 */
	public List<WxUserGroup> getGroups(String accountid);
	
	/**
	 * 保存单个成员到分组
	 * @param id
	 * @param accountId
	 * @param ids 
	 * @param redirectAttributes
	 * @return
	 */
	public String moveUser(String ids, String groupid,  String accountid);
	
	/**
	 * 批量保存成员到分组
	 * @param id
	 * @param accountId
	 * @param ids 
	 * @param redirectAttributes
	 * @return
	 */
	public String moveUsers(String ids, String groupid,  String accountid);
	
	/**
	 * 根据 groupid，accountid查询分组
	 * @param groupid
	 * @param accountid
	 */
	public WxUserGroup getGroup( String groupid, String accountid);
	
	/**
	 * 修改分组
	 * @param wxUserGroup
	 * @param accountid
	 * @return
	 */
	public String update(String id, String name, String accountid);
	
	/**
	 * 获取用户分组列表
	 * @return
	 */
	public List<WxUserGroup> getUserGroups(WxUserGroup wxUserGroup);
	
	/**
	 * 根据分组名称查询分组
	 * @param groupid
	 * @param accountid
	 */
	public WxUserGroup getUG(String groupName);

}