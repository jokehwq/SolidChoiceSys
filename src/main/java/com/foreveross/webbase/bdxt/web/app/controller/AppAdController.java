package com.foreveross.webbase.bdxt.web.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.bdxt.entity.BdxtAd;
import com.foreveross.webbase.bdxt.entity.BdxtAdLog;
import com.foreveross.webbase.bdxt.entity.BdxtAdMaterial;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtAdLogService;
import com.foreveross.webbase.bdxt.service.BdxtAdMaterialService;
import com.foreveross.webbase.bdxt.service.BdxtAdService;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.AdInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.utils.Constants;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@Api(value = "adController",description = "广告模块")
@RestController
@RequestMapping("/api")
public class AppAdController {

    @Resource
    private BdxtAdService bdxtAdService;
    @Resource
    private BdxtAdLogService bdxtAdLogService;
    @Resource
    private BdxtAdMaterialService bdxtAdMaterialService;
    @Resource
    private DictService dictService;
    @Resource
    private BdxtUserService bdxtUserService;


    @RequestMapping(value = "/queryAdInfo",method = RequestMethod.POST)
    @ApiOperation(value = "查询需要展示的广告", notes = "查询需要展示的广告")
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse queryAdInfo(@LoginUser BdxtUser bdxtUser) {
        //查询登陆用户属于哪个城市
        String city = bdxtUser.getCity();
        BdxtAd ba = new BdxtAd();
        //已上架
        ba.setStatus(1);
        List<BdxtAd> list = bdxtAdService.findList(ba);

        if(CollectionUtils.isEmpty(list))
            return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();

        List<BdxtAd> _list = new ArrayList<>();
        //筛选
        for(BdxtAd _t : list){
            //对比城市
            if(_t.getCity().equals(city) ||_t.getCity().equals("中国")){
                _list.add(_t);
            }
        }

        List<BdxtAd> lbt = new ArrayList<>();
        List<BdxtAd> qt = new ArrayList<>();
        for(BdxtAd t : _list){
            if("首页轮播图".equals(t.getLocationname())){
                lbt.add(t);
                //在广告/素材展示数+1
                t.setShowcount(t.getShowcount()+1);
                bdxtAdService.save(t);
                BdxtAdMaterial bdxtAdMaterial = bdxtAdMaterialService.get(t.getMaterial());
                bdxtAdMaterial.setShowcount(bdxtAdMaterial.getShowcount()+1);
                bdxtAdMaterialService.save(bdxtAdMaterial);
            }else{
                qt.add(t);
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("首页轮播图",lbt);
        BdxtAd bdxtAd = null;
        if(CollectionUtils.isNotEmpty(qt)){
            //筛选
                int temp = (int) (Math.random() * qt.size());
                bdxtAd = qt.get(temp);
                //查询出类型

                String typename = dictService.get(bdxtAd.getType()).getLabel();
                bdxtAd.setTypename(typename);

                //在广告/素材展示数+1
                bdxtAd.setShowcount(bdxtAd.getShowcount()+1);
                bdxtAdService.save(bdxtAd);
                BdxtAdMaterial bdxtAdMaterial = bdxtAdMaterialService.get(bdxtAd.getMaterial());
                bdxtAdMaterial.setShowcount(bdxtAdMaterial.getShowcount()+1);
                bdxtAdMaterialService.save(bdxtAdMaterial);
                map.put(bdxtAd.getLocationname(),bdxtAd);
        }else{
            map.put("non","");
        }
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), map).build();
    }
    @RequestMapping(value = "/addClickcount",method = RequestMethod.POST)
    @ApiOperation(value = "用户点击广告，广告数+1(传入广告ID，如果用户登录，传入用户ID)", notes = "用户点击广告，广告数+1")
    public CommonResponse addClickcount(@RequestBody AdInfoReq adInfoReq) {
        //在广告/素材点击数+1
        BdxtAd bdxtAd = bdxtAdService.get(adInfoReq.getAdId());
        bdxtAd.setClickcount(bdxtAd.getClickcount()+1);
        bdxtAdService.save(bdxtAd);
        BdxtAdMaterial bdxtAdMaterial = bdxtAdMaterialService.get(bdxtAd.getMaterial());
        bdxtAdMaterial.setClickcount(bdxtAdMaterial.getClickcount()+1);
        bdxtAdMaterialService.save(bdxtAdMaterial);
        //插入一条记录
        if(StringUtils.isNotBlank(adInfoReq.getUserId())){
            BdxtUser bdxtUser = bdxtUserService.get(adInfoReq.getUserId());
            if(bdxtUser ==null)
                return new CommonResponse.Builder(true, "用户不存在",
                        ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();

            BdxtAdLog bg = new BdxtAdLog();
            bg.setAdid(bdxtAd.getId());
            bg.setBdxtUserId(bdxtUser.getId());
            //设置查询当天
            String current_date = DateUtils.getDate();
            String start_time = current_date+" 00:00:00";
            String end_time = current_date+" 23:59:59";
            bg.setBeginCreateTime(DateUtils.parseDate(start_time));
            bg.setEndCreateTime(DateUtils.parseDate(end_time));
            List<BdxtAdLog> list = bdxtAdLogService.findList(bg);

            BdxtAdLog add_bg = new BdxtAdLog();
            add_bg.setAdid(bdxtAd.getId());
            add_bg.setTitle(bdxtAdMaterial.getTitle());
            add_bg.setType(bdxtAdMaterial.getType());
            add_bg.setClickname(bdxtUser.getPhone());
            add_bg.setBdxtUserId(bdxtUser.getId());
            User user = new User();
            user.setName("系统管理员");
            add_bg.setCreateBy(user);
            if(CollectionUtils.isEmpty(list)){
                //如果该用户下没有记录就直接插入一条有效的
                add_bg.setStatus("1");
            }else {
                add_bg.setStatus("2");
            }
            bdxtAdLogService.save(add_bg);
        }
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }



}
