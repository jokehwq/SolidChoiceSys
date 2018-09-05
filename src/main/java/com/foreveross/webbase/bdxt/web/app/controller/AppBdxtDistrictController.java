package com.foreveross.webbase.bdxt.web.app.controller;
import com.foreveross.webbase.bdxt.service.BdxtDistrictService;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * Created by wangkun on 2017/7/19.
 */
@Api(value = "districtController",description = "区域模块")
@RestController
@RequestMapping("/api")
public class AppBdxtDistrictController {

    @Resource
    private BdxtDistrictService bdxtDistrictService;


    @ApiOperation(value = "获取区域列表信息", notes = "获取区域列表信息")
    @RequestMapping(value = "/queryDistrictInfo",method = RequestMethod.GET)
    public CommonResponse queryDistrictInfo(){
       return bdxtDistrictService.queryDistrictInfo();
    }


      

}
