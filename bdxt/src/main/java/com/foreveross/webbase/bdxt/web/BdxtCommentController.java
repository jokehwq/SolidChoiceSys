/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtComment;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtCommentService;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 资讯信息Controller
 * @author tanjinhua
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtComment")
public class BdxtCommentController extends BaseController {

	@Autowired
	private BdxtCommentService bdxtCommentService;

	@RequestMapping(value = {"list", ""})
	public String list(BdxtComment bdxtComment) {
		Page<BdxtComment> bdxtCommentPage = new Page<>(request(), response());
		bdxtCommentPage.setPageSize(10);
		bdxtCommentPage.setOrderBy("create_time desc");
		Page<BdxtComment> page = bdxtCommentService.findPage(bdxtCommentPage, bdxtComment);
		attr("page", page);
		attr("newsId", bdxtComment.getNewsId());
		return "bdxt/bdxtNewsLyaer";
	}

	@RequestMapping("delete")
	@ResponseBody
	public void delete(@RequestParam String id) {
		try {
			bdxtCommentService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}