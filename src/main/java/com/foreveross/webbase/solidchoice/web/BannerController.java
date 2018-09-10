package com.foreveross.webbase.solidchoice.web;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.solidchoice.entity.Banner;
import com.foreveross.webbase.solidchoice.service.BannerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "${adminPath}/sys/banner")
public class BannerController extends BaseController {

    @Autowired
    private BannerService bannerService;

    @RequiresPermissions("sys:banner:view")
    @RequestMapping(value = {"list", ""})
    public String list(Banner banner) {
        Page<Banner> page = bannerService.findPage(new Page<Banner>(request(), response()), banner);
        attr("page", page);
        return "solidchoice/bannerList";
    }

    @RequiresPermissions("sys:banner:view")
    @RequestMapping("form")
    public String form(String id) {
        //step1 查询parentid为空的父级字典信息
        List<Banner> bannerList = bannerService.selectBannerList();
        attr("bannerList",bannerList);
        if(StringUtils.isNotEmpty(id)) {
            Banner banner=bannerService.get(id);
            attr("banner", banner);
        } else {
            attr("banner", new Banner());
        }
        return "solidchoice/bannerForm";
    }

    @RequiresPermissions("sys:banner:edit")
    @RequestMapping("save")
    public String save(Banner banner, Model model, RedirectAttributes redirectAttributes, MultipartFile file) {
//        if (!beanValidator(model, banner)){
//            return form(banner.getId());
//        }


        boolean res = true;
        try {
            res = bannerService.saveOrUpdateNews(banner, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(res){
            addMessage(redirectAttributes, "保存资讯信息成功");
        }else{
            addMessage(redirectAttributes, "保存资讯信息失败");
        }



//        bannerService.save(banner);
        addMessage(model, "保存字典信息成功");
        return "redirect:"+ Global.getAdminPath()+"/sys/banner/?repage";
    }

    @RequiresPermissions("sys:banner:edit")
    @RequestMapping("delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
        bannerService.delete(id);
        addMessage(redirectAttributes, "删除字典信息成功");
        return "redirect:"+ Global.getAdminPath()+"/sys/banner/?repage";
    }
}
