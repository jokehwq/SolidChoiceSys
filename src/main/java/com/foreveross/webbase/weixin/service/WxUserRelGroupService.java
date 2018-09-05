package com.foreveross.webbase.weixin.service;

import java.util.List;
import java.util.Map;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserRelGroup;

/**
 * 微信用户分组Service
 * @ClassName: WxUserRelGroupService
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月07日 上午11:37:14
 */
public interface WxUserRelGroupService extends ICrudService<WxUserRelGroup> {
	
	/**
	 * 保存成员到分组
	 * @param wUR
	 */
	public void add(WxUserRelGroup wUR);
	
	/**
	 * 根据分组id,userid查询用户
	 */
	public WxUserRelGroup getUser(Map<String, Object> userMap);
	
	/**
	 * 修改分组用户
	 * @param WU
	 */
	public void update(WxUserRelGroup WU);
	
	/**
	 * 根据用户组id获取所有用户的id列表
	 * @param groupid
	 * @return
	 */
	public List<String> findUserListByGroupid(String groupid);
	
	/**
	 * 根据用userid删除分组用户记录
	 * @param userid
	 * @return
	 */
	public void deGroupUser(String id);
	
	/**
	 * 获取该用户的分组
	 * @param id
	 * @return
	 */
	public Page<WxUser> findUserList(Page<WxUser>page, WxUserRelGroup wxUserRelGroup);
	
}