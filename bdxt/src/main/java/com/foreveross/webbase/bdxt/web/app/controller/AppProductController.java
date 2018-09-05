package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtProduct;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtProductService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.ProInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanjinhua on 2018-4-26.
 */
@Api(value = "productController",description = "商城模块")
@RestController
@RequestMapping("/api")
public class AppProductController {
    @Resource
    private BdxtProductService bdxtProductService;

    @RequestMapping(value = "/queryProMapInfo",method = RequestMethod.POST)
    @ApiOperation(value = "商品轮播图", notes = "商品轮播图")
    public CommonResponse queryProMapInfo() {

        BdxtProduct bp = new BdxtProduct();
        //上架并且设为轮播的
        bp.setStatus(1);
        bp.setCarousel(1);
        List<BdxtProduct> list = bdxtProductService.findList(bp);
        if(CollectionUtils.isEmpty(list))
            return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), new ArrayList<>()).build();

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), list).build();
    }

    @RequestMapping(value = "/queryProInfo",method = RequestMethod.POST)
    @ApiOperation(value = "商品列表", notes = "商品列表")
    public CommonResponse queryProInfo(@RequestBody ProInfoReq proInfoReq) {
       return bdxtProductService.queryProInfo(proInfoReq);
    }

    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ApiOperation(value = "商品详情", notes = "商品详情")
    public CommonResponse detail(@RequestBody ProInfoReq proInfoReq) {

        BdxtProduct bdxtProduct = bdxtProductService.get(proInfoReq.getProId());
        if(bdxtProduct == null)
            return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), new ArrayList<>()).build();

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), bdxtProduct).build();
    }

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    @ApiOperation(value = "下单", notes = "下单")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addOrder(@RequestBody ProInfoReq proInfoReq, @LoginUser BdxtUser bdxtUser) {

        proInfoReq.setPeople(bdxtUser.getPhone());
        return bdxtProductService.addOrder(proInfoReq,bdxtUser);
    }

}
