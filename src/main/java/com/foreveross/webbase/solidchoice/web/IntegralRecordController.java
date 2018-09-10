package com.foreveross.webbase.solidchoice.web;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.solidchoice.entity.IntegralRecord;
import com.foreveross.webbase.solidchoice.service.IntegralRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${adminPath}/solidChoice/integralRecord")
public class IntegralRecordController extends BaseController {
    @Autowired
    private IntegralRecordService integralRecordService;

    @RequiresPermissions("solidChoice:vipUser:view")
    @RequestMapping(value = {"list", ""})
    public String list(IntegralRecord integralRecord) {
        Page<IntegralRecord> page = integralRecordService.findPage(new Page<IntegralRecord>(request(), response()), integralRecord);
        attr("page", page);
        return "solidchoice/integralRecord";
    }


}
