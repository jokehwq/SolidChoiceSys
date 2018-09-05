package com.foreveross.webbase.weixin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxUserRelGroup;

/**
 * 微信用户分组DAO接口
 * @ClassName: WxUserRelGroupDao
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月12日 上午11:38:39
 */
@MyBatisDao
public interface WxUserRelGroupDao extends CrudDao<WxUserRelGroup> {
	
	/**
	 * 根据groupid获取用户id
	 * @param groupidgroupid
	 * @return
	 */
	public List<String> getUserid(String groupid);
	
	/**
	 * 根据用户ID,userid查询用户
	 * @param userMap
	 * @return
	 */
	public WxUserRelGroup getUser(Map<String, Object> userMap);
	
	/**
	 * 根据用userid, groupid删除分组用户记录
	 * @param userid, groupid
	 * @return
	 */
	public void delGroupUser(@Param("userid")String userid,@Param("roomid")String roomid);

	/**
	 * 根据用户获取该用户的分组
	 * @param id
	 * @return
	 */
	public List<WxUserRelGroup> getByUserid(String userid);
	

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
	public List<WxUserRelGroup> findUserList(WxUserRelGroup wxUserRelGroup);
	
}