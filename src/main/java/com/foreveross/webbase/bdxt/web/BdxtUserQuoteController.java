package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtUserQuote;
import com.foreveross.webbase.bdxt.service.BdxtUserQuoteService;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.ExcelUtil;
import com.foreveross.webbase.common.web.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 用户报价信息Controller
 *
 * @author wangkun
 * @version 2018-02-21
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtUserQuote")
public class BdxtUserQuoteController extends BaseController {

    @Autowired
    private BdxtUserQuoteService bdxtUserQuoteService;


    @RequiresPermissions("bdxt:bdxtUserQuote:view")
    @RequestMapping(value = {"list", ""})
    public String list(BdxtUserQuote bdxtUserQuote) {
        Page<BdxtUserQuote> page = bdxtUserQuoteService.findPage(new Page<BdxtUserQuote>(request(), response()), bdxtUserQuote);
        attr("page", page);
        attr("activityId",bdxtUserQuote.getBdxtActivityId());
        return "bdxt/bdxtUserQuoteList";
    }


    @RequiresPermissions("bdxt:bdxtUserQuote:edit")
    @RequestMapping("save")
    public String save(BdxtUserQuote bdxtUserQuote, RedirectAttributes redirectAttributes) {
        if (!beanValidator(redirectAttributes, bdxtUserQuote)) {
            return null;
        }
        bdxtUserQuoteService.save(bdxtUserQuote);
        addMessage(redirectAttributes, "保存用户报价信息成功");
        return "redirect:" + Global.getAdminPath() + "/bdxt/bdxtUserQuote/?repage";
    }

    @RequestMapping("update")
    @ResponseBody
    public String update(BdxtUserQuote bdxtUserQuote) {
        int count = bdxtUserQuoteService.updateBdxtUserQuoteByCondition(bdxtUserQuote);
        if (count > 0) {
            return "更新成功";
        }
        return "更新失败";
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public void updateStatus(BdxtUserQuote bdxtUserQuote) {
        bdxtUserQuoteService.updateStatus(bdxtUserQuote);
    }


    /**
     * create by wangkun 2016/12/22
     * 导出excel 报名记录
     * @return
     */
    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response,BdxtUserQuote bdxtUserQuote) throws IOException {
        String fileName = "报名记录信息.xls";// 导出Excel表格
        String[] title={"姓名","联系方式","报价","性别","状态","常住地点","身高","申请时间","申请内容"};
        List<Map<String,Object>> mapList=new LinkedList<>();
        List<Object[]> obj = new ArrayList<Object[]>();
        Page<BdxtUserQuote> page=bdxtUserQuoteService.findPage(new Page<BdxtUserQuote>(), bdxtUserQuote);
        if(CollectionUtils.isNotEmpty(page.getList())) {
            for (BdxtUserQuote userQuote : page.getList()) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("realname", userQuote.getRealname());
                map.put("phone", userQuote.getPhone());
                map.put("quotePrice", userQuote.getQuotePrice());
                map.put("gender", userQuote.getGender() != null ? userQuote.getGender() == 1 ? "男" : "女" : "");
                String quoteStatus = "";
                switch (userQuote.getQuoteStatus()) {
                    case 1:
                        quoteStatus = "待审核";
                        break;
                    case 2:
                        quoteStatus = "已议价";
                        break;
                    case 3:
                        quoteStatus = "已通过";
                        break;
                    default:
                        quoteStatus = "未通过";
                }
                map.put("quoteStatus", quoteStatus);
                map.put("city", userQuote.getCity());
                map.put("height", userQuote.getHeight());
                map.put("applyTime", DateUtils.formatDateTime(userQuote.getCreateTime()));
                map.put("applyContent", userQuote.getApplyContent());
                mapList.add(map);
            }
            for (Map<String, Object> map : mapList) {
                Collection values = map.values();
                List list = new ArrayList(values);
                obj.add(list.toArray());
            }
        }
        ExcelUtil.exportWithHeadExcel(fileName, title,obj,response);
    }
}