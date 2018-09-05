package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtComment;
import com.foreveross.webbase.bdxt.entity.BdxtNews;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtCommentService;
import com.foreveross.webbase.bdxt.service.BdxtNewsService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.CommentInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.utils.Constants;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.system.sys.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "CommentController",description = "资讯评论")
@RestController
@RequestMapping("/api")
public class AppBdxtCommentController {

    @Resource
    private BdxtCommentService bdxtCommentService;
    @Resource
    private BdxtNewsService bdxtNewsService;

    @RequestMapping(value = "/queryCommentInfo",method = RequestMethod.POST)
    @ApiOperation(value = "评论列表,分页参数:pageSize：每页显示数量，pageNo:页码", notes = "评论列表")
    public CommonResponse queryCommentInfo(@RequestBody CommentInfoReq commentInfoReq) {
        return bdxtCommentService.queryCommentInfo(commentInfoReq);
    }

    @ApiOperation(value = "添加评论,第一次评论paraentId 传null", notes = "添加评论")
    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    @ApiImplicitParam(name = Constants.TOKENKEY, required = false, dataType = "string", paramType = "header")
    @Login
    public CommonResponse addComment(@RequestBody BdxtComment bdxtComment, @LoginUser BdxtUser bdxtUser){
        //参数设置----这边存放ID，需要实时更新用户的头像和昵称
        User user = new User();
        user.setName(bdxtUser.getId());
        bdxtComment.setCreateBy(user);
        //判断paraent
        if(StringUtils.isEmpty(bdxtComment.getParaentId())){
            bdxtComment.setParaentId(null);
        }
        bdxtCommentService.save(bdxtComment);
        //增加评论数
        BdxtNews bdxtNews = bdxtNewsService.get(bdxtComment.getNewsId());
        bdxtNews.setNewsCommentNum(bdxtNews.getNewsCommentNum()+1);
        bdxtNewsService.save(bdxtNews);

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
    }

    @RequestMapping(value = "/delCommentInfo",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除评论", notes = "删除评论")
    public CommonResponse delCommentInfo(@RequestParam(value = "id", required = true)
                                                     String  id) {
        BdxtComment bdxtComment = bdxtCommentService.get(id);
        if(bdxtComment == null)
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_NO_SUCCESS.getMsg(),
                    ConstantsEnum.NON_EXIST_VALUE.getCode(), "").build();

        bdxtCommentService.delete(id);
        //删除一条评论数
        BdxtNews bdxtNews = bdxtNewsService.get(bdxtComment.getNewsId());
        bdxtNews.setNewsCommentNum(bdxtNews.getNewsCommentNum()+1);
        bdxtNewsService.save(bdxtNews);

        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), null).build();
    }
}
