/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxUserDao;
import com.foreveross.webbase.weixin.dao.WxUserGroupDao;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserGroup;
import com.foreveross.webbase.weixin.sdk.api.UserAPI;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.sdk.vo.api.FollowList;
import com.foreveross.webbase.weixin.sdk.vo.api.Follower;
import com.foreveross.webbase.weixin.service.WxUserService;

/**
 * 用户管理Service
 * @ClassName: WxUserServiceImpl
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月29日 下午7:50:07
 */
@Service
public class WxUserServiceImpl extends CrudService<WxUserDao, WxUser> implements WxUserService {

	private static final String[][] String = null;
	@Autowired
	WxUserDao wxUserDao;
	@Autowired
	WxUserGroupDao userGroupDao;
	@Value("${environmen}")
	private String environmen;
	
	
	@Override
	@Transactional(readOnly = false)
	public void SaveWxUser(String userName, String id) {
		UserAPI userAPI = WechatUtil.getWechatAPI(id);
		String openid = userName;
		Follower follower = userAPI.getFollower(openid, "zh_CN");
		WxUser wxuser = dao.getUser(follower.getOpenid());
		//String newName = StringToUnicode(follower.getNickname());
		if(wxuser != null){
			BeanUtils.copyProperties(follower, wxuser);
			//wxuser.setNickname(newName);
			wxuser.setAccountId(id);
			dao.updateUser(wxuser);
		}else{
			WxUser wx = new WxUser();	
			BeanUtils.copyProperties(follower, wx);
			//wx.setNickname(newName);
			wx.setAccountId(id);
			save(wx);
		}
		
	}

	@Override
	@Transactional(readOnly = false)
	public void updateUser(String userName, String id) {
		UserAPI userAPI = WechatUtil.getWechatAPI(id);
		String openid = userName;
		Follower follower = userAPI.getFollower(openid, "zh_CN");
		WxUser wxUser = wxUserDao.getUser(follower.getOpenid());
		if (wxUser != null) {
			// 把成员的关注状态设为未关注，0为未关注。
			wxUser.setSubscribe(0);
			wxUserDao.updateUser(wxUser);
		}
	}

	public Page<WxUser> findPageUserByGroupId(Page<WxUser>page,WxUser user){
		user.setPage(page);
		List<WxUser> users = wxUserDao.selectUserByGroupId(user);
		page.setList(users);
		return page;
	}

	public Page<WxUser> userPage(Page<WxUser> page, WxUser wxUser) {
		wxUser.setPage(page);
//		Map<String ,Object> map = new HashMap<String ,Object>();
//		map.put("wxUser", wxUser);
//		map.put("accountId", wxUser.getAccountId());
//		List <WxUser> users = dao.userList(map);
		List <WxUser> users = dao.findList(wxUser);
//		List <WxUser> userlist  = new ArrayList<WxUser>();
		/*for(WxUser user : users){
			user.setNickname(unicodeToString(user.getNickname()));
			userlist.add(user);
		}*/
		page.setList(users);
		
		int a=6;
		System.out.println(a);
		System.out.println(a++);
		System.out.println(a);
		
		return page;
	}

	@Override
	public List<WxUserGroup> getUserWithGroupids(String openid) {
		return dao.getUserWithGroupids(openid);
	}

	@Override
	public String getUserWithUnitid(String openid) {	      
		return dao.getUserWithUnitid(openid);
	}

	@Override
	public WxUser getUserByOpenid(String openid) {
		return dao.getUser(openid);
	}

	public Page<WxUser> findPage(Page<WxUser> page, WxUser wxUser) {
		wxUser.setPage(page);
		List <WxUser> users = dao.findList(wxUser);
	/*	List <WxUser> userlist = new ArrayList<WxUser>();
		for(WxUser user : users){
			user.setNickname(unicodeToString(user.getNickname()));
			userlist.add(user);
		}*/
		page.setList(users);
		return page;
	}


	@Override
	@Transactional(readOnly = false)
	public void delUser(WxUser wxUser) {
		dao.update(wxUser);
	}


	@Override
	public List<WxUser> findUserListByUnitid(String unitid) {
		List<WxUser> list = dao.findUserListByUnitid(unitid);
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public void batchWxUser(String nextOpenId ,String id) {
		UserAPI userAPI = WechatUtil.getWechatAPI(id);
		FollowList followList = userAPI.getFollowerList(nextOpenId);
		for(String openId :followList.getOpenIds()){
			WxUser user = dao.getUser(openId);
			obtainUser(user, openId, id, userAPI );
		}
	}
	
	/*public WxUser get(String id){
		WxUser wxUser = dao.get(id);
		wxUser.setNickname(unicodeToString(wxUser.getNickname()));
		return wxUser;
	}
	
	*//** 
	 * 字符串转unicode 
	 */ 
	public static  String StringToUnicode(String string) {  
	    StringBuffer unicode = new StringBuffer();  
	    for (int i = 0; i < string.length(); i++) {  
	        // 取出每一个字符  
	        char c = string.charAt(i);  
	        // 转换为unicode  
	        unicode.append("\\u" + Integer.toHexString(c));  
	    }  
	    return unicode.toString();  
	}  
	
	/** 
	 * unicode 转字符串 
	 *//*  
	public static  String unicodeToString(String unicode) {  
	    StringBuffer string = new StringBuffer();  
	    String[] hex = unicode.split("\\\\u");  
	    for (int i = 1; i < hex.length; i++) {  
	        // 转换出每一个代码点  
	        int data = Integer.parseInt(hex[i], 16);  
	        // 追加成string  
	        string.append((char) data);  
	    }  
	    return string.toString();  
	}
	*/
	
	
	/**
	 * 根据openid在微信端获取用户
	 */
	@Override
	@Transactional(readOnly = false)
	public void obtainUser(WxUser user, String openId,String accountId,UserAPI userAPI ){
		if(user!=null){
			Follower follower = userAPI.getFollower(openId,"zh_CN");
			BeanUtils.copyProperties(follower, user);
			user.setNickname(StringToUnicode(follower.getNickname()));
			user.setAccountId(accountId);
			dao.updateUser(user);
		}else{
			WxUser newUser = new WxUser();
			Follower follower = userAPI.getFollower(openId,"zh_CN");
			BeanUtils.copyProperties(follower, newUser);
			//newUser.setNickname(StringToUnicode(follower.getNickname()));
			newUser.setAccountId(accountId);
			save(newUser);
		}
	}

	@Override
	public Page<WxUser> getGroupUsers(Page<WxUser> page, String accountid) {
		WxUser wxUser = new WxUser();
		wxUser.setPage(page);
		//List<WxUser> userlist = new ArrayList();
		List<WxUser> users = wxUserDao.getUsers(accountid);
		/*for(WxUser user : users){
			user.setNickname(unicodeToString(user.getNickname()));
			userlist.add(user);
		}*/
		page.setList(users);
		return page;
	}

	@Override
	public WxUser getUserByPhone(java.lang.String phone) {
		return dao.getUserByPhone(phone);
	}
}