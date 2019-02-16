package com.foreveross.webbase.solidchoice.web;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.solidchoice.entity.Topic;
import com.foreveross.webbase.solidchoice.entity.TopicComment;
import com.foreveross.webbase.solidchoice.entity.VipUser;
import com.foreveross.webbase.solidchoice.entity.Voting;
import com.foreveross.webbase.solidchoice.service.VipUserService;
import com.foreveross.webbase.solidchoice.service.VotingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HIAPAD on 2018/9/10.
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/voting")
public class VotingController extends BaseController{

    @Autowired
    private VotingService votingService;
    
    @Autowired
    private VipUserService userService;

    @RequiresPermissions("sys:voting:view")
    @RequestMapping(value = {"list", ""})
    public String List(Voting voting){

        Page<Voting> page = votingService.findPage(new Page<Voting>(request(), response()), voting);
        attr("page", page);
        return "solidchoice/votingList";
    }
//    @RequiresPermissions("sys:voting:view")
//    @RequestMapping("form")
//    public String form(String id) {
//        //step1 查询parentid为空的父级字典信息
//        List<Voting> votingList = votingService.selectVotingList();
//        attr("votingList",votingList);
//        if(StringUtils.isNotEmpty(id)) {
//            Voting voting=votingService.selectVotingById(id);
//            attr("voting", voting);
//        } else {
//            attr("voting", new Voting());
//        }
//        return "solidchoice/votingForm";
//    }
    
    @RequiresPermissions("sys:voting:view")
    @RequestMapping("detail")
    public String detail(String id) {
    	Voting selectVotingById = votingService.selectVotingById(id);
    	 VipUser vipUser = new VipUser();
    	if(StringUtils.isNotBlank(selectVotingById.getTopic().getUserId())){
    		 vipUser = userService.get(selectVotingById.getTopic().getUserId());
    	}
    	java.util.List<TopicComment> comment = selectVotingById.getComment();
    	java.util.List<TopicComment> comment2 = new ArrayList();
    	for (TopicComment topicComment : comment) {
    		if(topicComment!=null){
    			if(StringUtils.isNoneBlank(topicComment.getComUid())){
        			topicComment.setNikeName(userService.get(topicComment.getComUid()).getNickName());
        		}
        		
        		comment2.add(topicComment);
    		}
    		
		}
    	
        attr("vipUser",vipUser);
        attr("topic",selectVotingById.getTopic());
        attr("comment",comment2);
        attr("voting",selectVotingById);
        
        return "solidchoice/vateDetail";
    }
    
    @RequiresPermissions("sys:voting:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
    	boolean delVotingById = votingService.delVotingById(id);
    	if(delVotingById){
    		addMessage(redirectAttributes, "删除投票信息成功！");
    	}else{
    		addMessage(redirectAttributes, "删除投票信息失败！");
    	}
		
		return "redirect:"+Global.getAdminPath()+"/sys/voting/?repage";
	}

    @RequiresPermissions("sys:voting:edit")
   	@RequestMapping("checkStatus")
   	public String checkStatus(Topic topic,RedirectAttributes redirectAttributes) {
       	boolean checkPass = votingService.checkStatus(topic);
       	if(checkPass){
       		addMessage(redirectAttributes, "审批成功！");
       	}else{
       		addMessage(redirectAttributes, "审批失败，请重新进行审批！");
       	}
   		
   		return "redirect:"+Global.getAdminPath()+"/sys/voting/?repage";
   	}
    
//    @RequiresPermissions("sys:banner:edit")
//    @RequestMapping("save")
//    public String save(Voting voting, Model model, RedirectAttributes redirectAttributes, MultipartFile file) {
////        if (!beanValidator(model, banner)){
////            return form(banner.getId());
////        }
//
//
//        boolean res = true;
//        try {
//            res = votingService.saveOrUpdateNews(voting, file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(res){
//            addMessage(redirectAttributes, "保存资讯信息成功");
//        }else{
//            addMessage(redirectAttributes, "保存资讯信息失败");
//        }
//
//
//
////        bannerService.save(voting);
//        addMessage(model, "保存字典信息成功");
//        return "redirect:"+ Global.getAdminPath()+"/sys/banner/?repage";
//    }

}
