package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtNews;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtNewsService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.NewsInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.utils.Constants;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.system.sys.entity.Dict;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(value = "NewsController",description = "资讯模块")
@RestController
@RequestMapping("/api")
public class AppBdxtNewsController extends BaseController{
    @Resource
    private BdxtNewsService bdxtNewsService;
    @Resource
    private DictService dictService;

    @ApiOperation(value = "获取首页资讯列表(已发布的),分页参数:pageSize：每页显示数量，pageNo:页码,type 查全部传null", notes = "获取资讯列表")
    @RequestMapping(value = "/queryNewsInfo",method = RequestMethod.POST)
    public CommonResponse queryNewsInfo(@RequestBody NewsInfoReq newsInfoReq){
        return bdxtNewsService.queryNewsInfo(newsInfoReq);
    }

    @ApiOperation(value = "获取我的资讯列表,分页参数:pageSize：每页显示数量，pageNo:页码,type 查全部传null", notes = "获取我的资讯列表")
    @RequestMapping(value = "/queryNewsInfoByUser",method = RequestMethod.POST)
    public CommonResponse queryNewsInfoByUser(@RequestBody NewsInfoReq newsInfoReq){
        return bdxtNewsService.queryNewsInfoByUser(newsInfoReq);
    }

    @ApiOperation(value = "资讯分类列表,不存在分页，直接获取所有资讯分类数据", notes = "资讯分类列表")
    @RequestMapping(value = "/queryNewsDictInfo",method = RequestMethod.POST)
    public CommonResponse queryNewsDictInfo(){
        Dict dict = new Dict();
        dict.setType("bdxt_news");
        List<Dict> list = dictService.findList(dict);
        if(list == null || list.size() == 0)
            return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), new ArrayList<>()).build();

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), list).build();
    }

    @RequestMapping(value = "/queryNewsDetail/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "资讯详情，传入资讯ID", notes = "资讯详情")
    public CommonResponse detail(@PathVariable("id") String id) {
        BdxtNews bdxtNews = bdxtNewsService.get(id);
        String newsType = bdxtNews.getNewsType();
        String label = dictService.get(newsType).getLabel();
        bdxtNews.setTypeName(label);

        if(bdxtNews == null)
            return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
        bdxtNews.setNewsReadsNum(bdxtNews.getNewsReadsNum()+1);
        bdxtNewsService.save(bdxtNews);

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), bdxtNews).build();
    }

    @ApiOperation(value = "添加资讯-投稿", notes = "添加资讯-投稿")
    @RequestMapping(value = "/addNews",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addNews(@RequestBody BdxtNews bdxtNews,@LoginUser BdxtUser bdxtUser){
        //设置参数
        User user = new User();
        user.setName(bdxtUser.getNickname());
        bdxtNews.setCreateBy(user);
        bdxtNews.setBdxtUserId(bdxtUser.getId());
        bdxtNews.setStatus(1);
        bdxtNews.setNewsCommentNum((long)0);
        bdxtNews.setNewsReadsNum((long)0);
        bdxtNews.setNewsPublishType(2);
        bdxtNewsService.save(bdxtNews);

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
    }

}
