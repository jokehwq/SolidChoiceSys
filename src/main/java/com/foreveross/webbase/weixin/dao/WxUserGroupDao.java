package com.foreveross.webbase.weixin.dao;

import java.util.List;
import java.util.Map;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxUserGroup;

/**
 * 微信用户分组DAO接口
 * @ClassName: WxUserGroupDao
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月12日 上午11:36:40
 */
@MyBatisDao
public interface WxUserGroupDao extends CrudDao<WxUserGroup> {

	/**
	 * 根据accountid查询分组
	 * @param accountid
	 * @return
	 */
	public List<WxUserGroup> getGroups(String accountid);
	
	/**
	 * 根据 groupid，accountid查询分组
	 * @param groupid
	 * @param accountid
	 */
	public WxUserGroup getGroup(Map<String,Object> map);
	
	/**
	 * 根据ID,accountid获取分组
	 * @param map
	 * @return
	 */
	public WxUserGroup getUserGroup(Map<String, Object> map);
	
	/**
	 * 获取用户分组
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