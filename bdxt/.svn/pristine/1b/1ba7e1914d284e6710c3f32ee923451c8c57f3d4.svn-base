package com.foreveross.webbase.weixin.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.JsonMessage;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.entity.WxUserGroup;
import com.foreveross.webbase.weixin.sdk.api.GroupsAPI;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.service.WxUserGroupService;

/**
 * 微信用户分组Controller
* @ClassName: WxUserGroupController
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月06日 上午11:33:38
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/wxUserGroup")
public class WxUserGroupController extends BaseController {

	@Autowired
	private WxUserGroupService wxUserGroupService;

	/**
	 * 微信用户分组列表
	 * @param wxUserGroup
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping(value = { "list", "" })
	public String list(WxUserGroup wxUserGroup) {
		Page<WxUserGroup> page = wxUserGroupService.findPage(
				new Page<WxUserGroup>(request(), response()), wxUserGroup);
		attr("page", page);
		attr("accountid", wxUserGroup.getAccountid());
		return "weixin/wxusergroup/wxUserGroupList";
	}

	/**
	 * 微信用户分组修改添加表单页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:edit")
	@RequestMapping("form")
	public String form(String id, String accountid) {
		if (StringUtils.isNotEmpty(id)) {
			WxUserGroup wxUserGroup = wxUserGroupService.get(id);
			attr("wxUserGroup", wxUserGroup);
		} else {
			attr("wxUserGroup", new WxUserGroup());
		}
		attr("accountid", accountid);
		return "weixin/wxusergroup/wxUserGroupForm";
	}
	
	/**
	 * 保存微信用户分组
	 * @param wxUserGroup
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:save")
	@RequestMapping("save")
	public String save(WxUserGroup wxUserGroup, String accountid,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, wxUserGroup)) {
			return form(wxUserGroup.getId(), accountid);
		}
		wxUserGroup.setAccountid(accountid);
		GroupsAPI groupsAPI = WechatUtil.getWechatAPI(wxUserGroup
				.getAccountid());
		if (StringUtils.isNotEmpty(wxUserGroup.getId())) {
			boolean groupValue = groupsAPI.renGroups(wxUserGroup.getGroupId(),
					wxUserGroup.getGroupName());
			if (groupValue) {
				wxUserGroupService.update(wxUserGroup);
				addMessage(redirectAttributes, "修改微信用户分组成功");
			}
		} else {
			List<WxUserGroup> list = wxUserGroupService.getGroups(accountid);
			if(list.size() < 100){
				int groupId = groupsAPI.createGroup(wxUserGroup.getGroupName());
				if (groupId != 0) {
					wxUserGroup.setGroupId(groupId);
					wxUserGroupService.save(wxUserGroup);
					addMessage(redirectAttributes, "保存微信用户分组成功");
				}else{
					addMessage(redirectAttributes, "每个公众最多创建100个分组");
				}
			}
			
		}
		return "redirect:" + Global.getAdminPath()
				+ "/weixin/wxUser/?repage&&accountId=" + accountid;
	}
	
	/**
	 * 修改分组
	 * @param wxUserGroup
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:edit")
	@ResponseBody
	@RequestMapping("update")
	public JsonMessage update(String id, String groupName, String accountid,
			RedirectAttributes redirectAttributes){
		wxUserGroupService.update(id, groupName, accountid);
		JsonMessage resultMessage = result(1,"修改成功");
		return resultMessage;	
	}

	/**
	 * 删除微信用户分组
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:edit")
	@ResponseBody
	@RequestMapping("delete")
	public JsonMessage delete(String groupid, String accountid, RedirectAttributes redirectAttributes) {
		WxUserGroup wxUserGroup = wxUserGroupService.getGroup(groupid, accountid);
		GroupsAPI groupsAPI = WechatUtil.getWechatAPI(wxUserGroup.getAccountid());
		boolean groupValue = groupsAPI.delGroup(wxUserGroup.getGroupId());
		JsonMessage resultMessage = null;
		if (groupValue) {
			wxUserGroupService.delete(wxUserGroup.getId());
			resultMessage = result(1,"删除用户成功");
		}else{
		    resultMessage = result(2,"删除用户失败");
		}
		return resultMessage;
	}
		
	/**
	 * 分组选择成员列表
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping("userdialog")
	public String userDialog(String accountid, String groupid) {
		List<WxUser> userlist = wxUserGroupService.getUsers(accountid);
		attr("userlist", userlist);
		attr("accountId", accountid);
		attr("groupId", groupid);
		return "weixin/wxusergroup/wxUserGroupDialog";
	}

	/**
	 * 查看分组成员列表
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping("selectGroupUser")
	public String selectGroupUser(Page<WxUser> page,String accountid, String groupid, String id) {
		Page<WxUser> groupUsers = wxUserGroupService.getWxUsers(page, groupid, null);
		attr("groupUsers", groupUsers);
		attr("accountid", accountid);
		attr("groupid", groupid);
		attr("id", id);
		return "weixin/wxusergroup/wxUserGroupDialog";
	}

	/**
	 * 添加单个成员到分组
	 * @param id
	 * @param accountId
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping("move")
	public @ResponseBody JsonMessage move(@RequestParam("ids[]") String ids,@RequestParam("groupid") String groupid,
			@RequestParam("accountid") String accountid,RedirectAttributes redirectAttributes) {
		String msg =  wxUserGroupService.moveUser(ids, groupid, accountid);
		return result(200, msg);
	}
	
	/**
	 * 批量添加成员到分组
	 * @param id
	 * @param accountId
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping("moveUser")
	public @ResponseBody JsonMessage moveUser(@RequestParam("ids[]") String ids,@RequestParam("groupid") String groupid,
			@RequestParam("accountid") String accountid,RedirectAttributes redirectAttributes) {
		String msg = wxUserGroupService.moveUsers(ids, groupid, accountid);
		return result(200, msg);
	}
	
	/**
	 * 获取微信用户分组
	 * @param wxUserGroup
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:view")
	@RequestMapping(value = { "selectGroupList"})
	public @ResponseBody List<WxUserGroup> selectGroupList(String accountid) {
		List<WxUserGroup> groupList = wxUserGroupService.getGroups(accountid);
		attr("accountid", accountid);
		return groupList;
	}
	
}