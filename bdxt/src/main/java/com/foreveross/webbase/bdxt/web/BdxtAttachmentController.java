package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtAttachment;
import com.foreveross.webbase.bdxt.service.BdxtAttachmentService;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户附件信息Controller
 *
 * @author wangkun
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtAttachment")
public class BdxtAttachmentController extends BaseController {

    @Autowired
    private BdxtAttachmentService bdxtAttachmentService;

    @RequiresPermissions("bdxt:bdxtAttachment:view")
    @RequestMapping(value = {"list", ""})
    public String list(BdxtAttachment bdxtAttachment) {
        Page<BdxtAttachment> page = bdxtAttachmentService.findPage(new Page<BdxtAttachment>(request(), response()), bdxtAttachment);
        attr("page", page);
        return "bdxt/bdxtAttachmentList";
    }

    @RequiresPermissions("bdxt:bdxtAttachment:view")
    @RequestMapping("form")
    public String form(String id) {
        if (StringUtils.isNotEmpty(id)) {
            BdxtAttachment bdxtAttachment = bdxtAttachmentService.get(id);
            attr("bdxtAttachment", bdxtAttachment);
        } else {
            attr("bdxtAttachment", new BdxtAttachment());
        }
        return "bdxt/bdxtAttachmentForm";
    }

    @RequestMapping("update")
    @ResponseBody
    public String update(BdxtAttachment bdxtAttachment) {
       int count= bdxtAttachmentService.updateAttachmentInfo(bdxtAttachment);
       if(count>0){
          return "更新用户作品成功";
       }
        return "更新用户作品失败";
    }


    @RequestMapping("delete")
    @ResponseBody
    public String delete(@RequestParam String id) {
        bdxtAttachmentService.delete(id);
        return "删除用户作品成功";
    }

}