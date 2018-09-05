package com.foreveross.webbase.weixin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxUserRelGroupDao;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserRelGroup;
import com.foreveross.webbase.weixin.service.WxUserRelGroupService;
import com.foreveross.webbase.weixin.service.WxUserService;

/**
 * 微信用户分组Service
 * @ClassName: WxUserRelGroupServiceImpl
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月12日 上午11:37:39
 */
@Service
public class WxUserRelGroupServiceImpl extends CrudService<WxUserRelGroupDao, WxUserRelGroup> implements WxUserRelGroupService {
 
	@Autowired
	private WxUserService wxUserService;
	
	@Override
	@Transactional(readOnly = false)
	public void add(WxUserRelGroup wUR) {
		dao.insert(wUR);
	}

	@Override
	public WxUserRelGroup getUser(Map<String, Object> userMap){
		WxUserRelGroup wxUserRelGroup = dao.getUser(userMap);
		return wxUserRelGroup;
	}

	@Override
	@Transactional(readOnly = false)
	public void update(WxUserRelGroup WU) {
		dao.update(WU);
	}
	
	@Override
	public List<String> findUserListByGroupid(String groupid) {
		List<String> list = dao.getUserid(groupid);
		return list;
	}
	
	@Override
	public void deGroupUser(String userid) {
		dao.deGroupUser(userid);
		
	}

	@Override
	public Page<WxUser> findUserList(Page<WxUser> page, WxUserRelGroup wxUserRelGroup) {
		List<WxUser> wxUsers = new ArrayList();
		WxUser wxUser = new WxUser();
		wxUser.setPage(page);
		List<WxUserRelGroup> wxGroup =dao.findUserList(wxUserRelGroup);
		for(WxUserRelGroup group : wxGroup){
			WxUser  user =wxUserService.get(group.getUserid());
			wxUsers.add(user);
		}
		page.setList(wxUsers);
		return page;
	}
	
}
