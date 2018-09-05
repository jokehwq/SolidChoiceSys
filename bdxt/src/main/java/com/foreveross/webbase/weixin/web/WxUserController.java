/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserRelGroup;
import com.foreveross.webbase.weixin.service.WxUserRelGroupService;
import com.foreveross.webbase.weixin.service.WxUserService;

/**
 * 用户管理Controller
 * @author lukaicheng
 * @version 2016-12-22
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/wxUser")
public class WxUserController extends BaseController {
	
	@Autowired
	private WxUserService wxUserService;
	@Autowired
	private WxUserRelGroupService wxUserRelGroupService;
		
	/**
	 * 获取关注成员列表
	 * @param wxUser
	 * @return
	 */
	@RequiresPermissions("weixin:wxUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxUser wxUser) {
		Page<WxUser> page = wxUserService.userPage(new Page<WxUser>(request(), response()), wxUser); 
		attr("page", page);
		attr("accountId",wxUser.getAccountId());
		return "weixin/wxuser/wxUserList";
	}
	
	/**
	 * 获取分组成员列表
	 * @param wxUser
	 * @return
	 */
	@RequiresPermissions("weixin:wxUser:view")
	@RequestMapping(value = "grouplist")
	public String grouplist(WxUser wxUser) {
		attr("accountId",wxUser.getAccountId());
		return "weixin/wxuser/wxGroupUserList";
	}
	
	/**
	 * 查看分组成员列表
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping("selectGroupUser")
	public String selectGroupUser(WxUser user, String id ,WxUserRelGroup wxUserRelGroup){
		
		if(StringUtils.isEmpty(wxUserRelGroup.getGroupid())){
			Page<WxUser> page = wxUserService.userPage(new Page<WxUser>(request(), response()), user); 
			attr("page",page);
		}else{
			Page<WxUser>  page = wxUserRelGroupService.findUserList(new Page<WxUser>(request(), response()),wxUserRelGroup);
			attr("page",page);
		}
		attr("accountId", user.getAccountId());
		attr("groupid", wxUserRelGroup.getGroupid());
		return "weixin/wxuser/wxUserList";
	}
	
	/**
	 * 查看用户详细信息页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping("selectUser")
	public String selectUser(String id) {
		if(StringUtils.isNotEmpty(id)) {
			WxUser wxUser = wxUserService.get(id);
			attr("wxUser", wxUser);
		} 
		return "weixin/wxuser/wxUserDetails";
	}

}