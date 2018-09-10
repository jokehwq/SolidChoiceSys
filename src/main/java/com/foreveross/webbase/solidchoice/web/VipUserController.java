package com.foreveross.webbase.solidchoice.web;

import com.foreveross.webbase.bdxt.entity.*;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.solidchoice.entity.IntegralRecord;
import com.foreveross.webbase.solidchoice.entity.UserUnion;
import com.foreveross.webbase.solidchoice.entity.VipUser;
import com.foreveross.webbase.solidchoice.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/solidChoice/vipUser")
public class VipUserController extends BaseController {
    @Autowired
    private VipUserService userService;
    @Autowired
    private IntegralRecordService integralRecordService;
    @Autowired
    private UserUnionService userUnionService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserCollectionService userCollectionService;

    @RequiresPermissions("solidChoice:vipUser:view")
    @RequestMapping(value = {"list", ""})
    public String list(VipUser user) {
        Page<VipUser> page = userService.findPage(new Page<VipUser>(request(), response()), user);
        attr("page", page);
        return "solidchoice/vipUserList";
    }

    @RequiresPermissions("solidChoice:vipUser:edit")
    @RequestMapping("updateUserStatus")
    public String updateUserStatus(@RequestParam String id,@RequestParam Integer userStatus, RedirectAttributes redirectAttributes) {
        VipUser vipUser = new VipUser();
        vipUser.setId(id);
        vipUser.setUserStatus(userStatus);
        userService.save(vipUser);
        addMessage(redirectAttributes, "修改用户状态信息成功");
        return "redirect:" + Global.getAdminPath() + "/solidChoice/vipUser/?repage";
    }

    @RequiresPermissions("solidChoice:vipUser:view")
    @RequestMapping("queryIntegralRecord")
    public String form(String id, IntegralRecord integralRecord) {
        integralRecord.setUserId(id);
        Page<IntegralRecord> page = integralRecordService.findPage(new Page<IntegralRecord>(request(), response()), integralRecord);
        attr("page", page);
        return "solidchoice/vipUserIntegralRecodeList";
    }

    @RequiresPermissions("solidChoice:vipUser:view")
    @RequestMapping("detail")
    public String detail(String id) {
        VipUser vipUser = userService.get(id);
        attr("vipUser",vipUser);

        //关注的人数量
        String followerNum = userUnionService.findFollowerNum(id);
        attr("followerNum",followerNum);
        //粉丝数量
        String followingNum = userUnionService.findFollowingNum(id);
        attr("followingNum",followingNum);
        //发起的投票
        String topicCount = topicService.findTopicCount(id);
        attr("topicCount",topicCount);
        //参与的投票
        String initiatingTopic = userCollectionService.findInitiatingTopicCount(id);
        attr("initiatingTopic",initiatingTopic);

        return "solidchoice/vipUserDetail";
    }
}
