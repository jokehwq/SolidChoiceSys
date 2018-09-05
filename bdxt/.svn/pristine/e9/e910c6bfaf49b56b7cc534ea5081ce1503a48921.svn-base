package com.foreveross.webbase.weixin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxUserDao;
import com.foreveross.webbase.weixin.dao.WxUserGroupDao;
import com.foreveross.webbase.weixin.dao.WxUserRelGroupDao;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserGroup;
import com.foreveross.webbase.weixin.entity.WxUserRelGroup;
import com.foreveross.webbase.weixin.sdk.api.GroupsAPI;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.service.WxUserGroupService;
import com.foreveross.webbase.weixin.service.WxUserRelGroupService;
import com.foreveross.webbase.weixin.service.WxUserService;

/**
 * 微信用户分组Service
 * 
 * @ClassName: WxUserGroupServiceImpl
 * @Description:
 * @author lukaicheng
 * @email lukaicheng@foreveross.com
 * @date 2016年12月06日 上午11:34:54
 */
@Service
public class WxUserGroupServiceImpl<D> extends CrudService<WxUserGroupDao, WxUserGroup> implements WxUserGroupService {

	@Autowired
	protected WxUserGroupDao wxUserGroupDao;
	@Autowired
	protected WxUserDao wxUserDao;
	@Autowired
	protected WxUserService wxUserService;
	@Autowired
	private WxUserRelGroupDao wxUserRelGroupDao;
	@Autowired
	private WxUserRelGroupService wxUserRelGroupService;

	@Override
	@Transactional(readOnly = false)
	public void update(WxUserGroup wxUserGroup) {
		wxUserGroupDao.update(wxUserGroup);
	}

	@Override
	public List<WxUser> getUsers(String accountid) {
		//List<WxUser> userlist = new ArrayList();
		List<WxUser> users = wxUserDao.getUsers(accountid);
	/*	for(WxUser user:users){
			user.setNickname(WxUserServiceImpl.unicodeToString(user.getNickname()));
			userlist.add(user);
		}*/
		return users;
	}

	@Override
	public Page<WxUser> getWxUsers(Page<WxUser> page, String groupid, String accountid) {
		WxUser wxUser = new WxUser();
		wxUser.setPage(page);
		List<WxUser> groupUsers = new ArrayList<WxUser>();
		if (StringUtils.isEmpty(groupid)) {
			groupUsers = getUsers(accountid);
		} else {
			List<String> userIds = wxUserRelGroupDao.getUserid(groupid);
			for (String str : userIds) {
				WxUser wx = wxUserService.get(str);
				groupUsers.add(wx);
			}
		}
		page.setList(groupUsers);
		return page;

	}

	@Override
	public WxUser getWxUser(String ids) {
		WxUser user = wxUserDao.getUser(ids);
		return user;
	}

	@Override
	@Transactional(readOnly = false)
	public String moveUser(String ids, String groupid, String accountid) {
		WxUserGroup wxUserGroup = get(groupid);
		WxUserRelGroup wx = wxUserRelGroupService.get(groupid);
		if (wx == null) {
			GroupsAPI groupsAPI = WechatUtil.getWechatAPI(accountid);
			boolean groupValue = groupsAPI.move2Group(ids, wxUserGroup.getGroupId());
			if (groupValue) {
				WxUserRelGroup wUR = new WxUserRelGroup();
				WxUser wxUser = getWxUser(ids);
				wUR.setGroupid(wxUserGroup.getId());
				wUR.setUserid(wxUser.getId());
				wxUserRelGroupService.add(wUR);
			}
		} else {
			return "添加的成员以存在";
		}
		return "添加成员成功";
	}

	@Override
	@Transactional(readOnly = false)
	public String moveUsers(String ids, String groupid, String accountid) {
		Map<String, Object> map = new HashMap<>();
		map.put("groupid", groupid);
		map.put("accountid", accountid);
		WxUserGroup wxUserGroup = wxUserGroupDao.getGroup(map);
		String[] idss = ids.split(",");
		List<String> idsList = new ArrayList<String>();
		for (String str : idss) {
			idsList.add(str);
		}
		if (idsList.size() <= 50) {
			GroupsAPI groupsAPI = WechatUtil.getWechatAPI(accountid);
			boolean groupValue = groupsAPI.batchMove2Group(idsList, wxUserGroup.getGroupId());
			if (groupValue) {
				Map<String, Object> userMap = new HashMap<>();
				for (String s : idsList) {
					WxUser wxUser = getWxUser(s);
					userMap.put("groupid", wxUserGroup.getId());
					userMap.put("userid", wxUser.getId());
					WxUserRelGroup wx = wxUserRelGroupService.getUser(userMap);
					if (wx == null) {
						WxUserRelGroup wxUserRelGroups = new WxUserRelGroup();
						wxUserRelGroups.setGroupid(wxUserGroup.getId());
						wxUserRelGroups.setUserid(wxUser.getId());
						wxUserRelGroupService.save(wxUserRelGroups);
					} else {
						continue;
					}
				}
			}
		} else {
			return "一次只能保存50个成员";
		}
		return "添加成员成功";
	}

	@Override
	public List<WxUserGroup> getGroups(String accountid) {
		List<WxUserGroup> Groups = wxUserGroupDao.getGroups(accountid);
		return Groups;
	}

	@Override
	public WxUserGroup getGroup(String groupid, String accountid) {
		Integer groupId = Integer.valueOf(groupid);
		Map<String, Object> map = new HashMap();
		map.put("groupid", groupId);
		map.put("accountid", accountid);
		WxUserGroup wxUserGroup = wxUserGroupDao.getGroup(map);
		return wxUserGroup;
	}

	@Override
	@Transactional(readOnly = false)
	public String update(String id, String groupName, String accountid) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("accountid", accountid);
		WxUserGroup wxUserGroup = wxUserGroupDao.getUserGroup(map);
		GroupsAPI groupsAPI = WechatUtil.getWechatAPI(wxUserGroup.getAccountid());
		if (StringUtils.isNotEmpty(wxUserGroup.getId())) {
			boolean groupValue = groupsAPI.renGroups(wxUserGroup.getGroupId(), groupName);
			if (groupValue) {
				wxUserGroup.setGroupName(groupName);
				update(wxUserGroup);
			}
		}
		return "修改成功";
	}

	@Override
	public List<WxUserGroup> getUserGroups(WxUserGroup wxUserGroup) {
		List<WxUserGroup> group=dao.getUserGroups(wxUserGroup);
		return group;
	}

	@Override
	public WxUserGroup getUG(String groupName) {
		return dao.getUG(groupName);
	}

}