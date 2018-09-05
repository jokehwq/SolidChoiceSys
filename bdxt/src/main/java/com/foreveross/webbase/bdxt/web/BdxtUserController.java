package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.dao.BdxtAttachmentDao;
import com.foreveross.webbase.bdxt.dao.BdxtBargainReservateDao;
import com.foreveross.webbase.bdxt.dao.BdxtQuoteBuyerDao;
import com.foreveross.webbase.bdxt.dao.BdxtQuoteRacketDao;
import com.foreveross.webbase.bdxt.entity.*;
import com.foreveross.webbase.bdxt.service.BdxtIntegralRecordService;
import com.foreveross.webbase.bdxt.service.BdxtTagService;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.IntegralQueryReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.Constants;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.ExcelUtil;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户登录信息Controller
 *
 * @author tanjinhua
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtUser")
public class BdxtUserController extends BaseController {

    @Autowired
    private BdxtUserService bdxtUserService;
    @Autowired
    private BdxtTagService bdxtTagService;

    @Autowired
    private BdxtBargainReservateDao bdxtBargainReservateDao;
    @Autowired
    private BdxtQuoteRacketDao bdxtQuoteRacketDao;
    @Autowired
    private BdxtQuoteBuyerDao bdxtQuoteBuyerDao;
    @Autowired
    private BdxtAttachmentDao bdxtAttachmentDao;
    @Autowired
    private BdxtIntegralRecordService bdxtIntegralRecordService;

    @RequiresPermissions("bdxt:bdxtUser:view")
    @RequestMapping(value = {"list", ""})
    public String list(BdxtUser bdxtUser) {
        Page<BdxtUser> page = bdxtUserService.findPage(new Page<BdxtUser>(request(), response()), bdxtUser);
        attr("page", page);
        return "bdxt/bdxtUserList";
    }

    @RequiresPermissions("bdxt:bdxtUser:view")
    @RequestMapping("detail")
    public String form(String id) {
        if (StringUtils.isNotEmpty(id)) {
            //step1 获取用户基本信息
            BdxtUser bdxtUser = bdxtUserService.get(id);
            //处理个人代表作多个转换
            if (StringUtils.isNotBlank(bdxtUser.getWorkImageUrl())) {
                String[] workImageUrls = bdxtUser.getWorkImageUrl().split(",");
                if (workImageUrls.length > 0) {
                    bdxtUser.setWorkImageUrlList(Arrays.asList(workImageUrls));
                }
            }
            BigDecimal restCapital = bdxtUser.getInCapital().
                    subtract(bdxtUser.getOutCapital()).
                    setScale(2, BigDecimal.ROUND_HALF_UP);//用户余额 保留2位小数
            bdxtUser.setInCapital(restCapital);
            attr("bdxtUser", bdxtUser);
            //step2.. 查询用户报价信息
            //step1 查询我的议价报价
            List<BdxtBargainReservate> bargainReservateList = bdxtBargainReservateDao.queryUserBarginReserInfo(id);
            attr("bargainReservateList", bargainReservateList);
            //step2 查询我的网拍报价
            List<BdxtQuoteRacket> quoteRacketList = bdxtQuoteRacketDao.queryUserQuoteRacketInfo(id);
            attr("quoteRacketList", quoteRacketList);
            //step3 查询我的买家秀报价
            BdxtQuoteBuyer bdxtQuoteBuyer = bdxtQuoteBuyerDao.queryUserQuoteBuyerInfo(id);
            attr("bdxtQuoteBuyer", bdxtQuoteBuyer);
            //step3 查询用户标签信息
            CommonResponse response = bdxtTagService.queryUserTagInfo(bdxtUser);
            attr("tagList", response!=null?response.getData():"");
            //stpe4 查询用户作品信息
            List<BdxtAttachment> attImgList = bdxtAttachmentDao.queryUserAttachInfo(id, 1);
            //处理用户作品url多个转换
            if (CollectionUtils.isNotEmpty(attImgList)) {
                for (BdxtAttachment imgList : attImgList) {
                    if(StringUtils.isNotBlank(imgList.getAttachUrl())){
                        String[] imgUrls = imgList.getAttachUrl().split(",");
                        List<String> imgUrlList=Arrays.asList(imgUrls);
                        imgList.setAttachUrlImgList(imgUrlList);
                    }
                   if(StringUtils.isNotBlank(imgList.getTagNames())){
                       List<BdxtTag> tagImgList=new LinkedList<>();
                       String[] tagNames = imgList.getTagNames().split(",");
                       for (int i = 0; i < tagNames.length; i++) {
                           BdxtTag tag=new BdxtTag();
                           tag.setTagName(tagNames[i]);
                           tag.setTagId(StringUtils.split(imgList.getTagId(),",")[i]);
                           tagImgList.add(tag);
                       }
                       imgList.setTagImgList(tagImgList);
                   }
                }
            }
            attr("attImgList", attImgList);
            //step5 查询用户视频信息
            List<BdxtAttachment> attVideoList = bdxtAttachmentDao.queryUserAttachInfo(id, 2);
            //处理用户视频url多个转换
            if (CollectionUtils.isNotEmpty(attVideoList)) {
                for (BdxtAttachment videoList : attVideoList) {
                    if(StringUtils.isNotBlank(videoList.getAttachUrl())) {
                        String[] videoUrls = videoList.getAttachUrl().split(",");
                        videoList.setAttachUrlVideoList(Arrays.asList(videoUrls));
                    }
                    if(StringUtils.isNotBlank(videoList.getTagNames())){
                        List<BdxtTag> tagVideoList=new LinkedList<>();
                        String[] tagNames = videoList.getTagNames().split(",");
                        for (int i = 0; i < tagNames.length; i++) {
                            BdxtTag tag=new BdxtTag();
                            tag.setTagName(tagNames[i]);
                            tag.setTagId(StringUtils.split(videoList.getTagId(),",")[i]);
                            tagVideoList.add(tag);
                        }
                        videoList.setTagVideoList(tagVideoList);
                    }
                }
            }
            attr("attVideoList", attVideoList);
        } else {
            attr("bdxtUser", new BdxtUser());
        }
        return "bdxt/bdxtUserDetail";
    }

    @RequiresPermissions("bdxt:bdxtUser:edit")
    @RequestMapping("save")
    public String save(BdxtUser bdxtUser, RedirectAttributes redirectAttributes) {
        if (!beanValidator(redirectAttributes, bdxtUser)) {
            return form(bdxtUser.getId());
        }
        bdxtUserService.save(bdxtUser);
        addMessage(redirectAttributes, "保存用户登录信息成功");
        return "redirect:" + Global.getAdminPath() + "/bdxt/bdxtUser/?repage";
    }

    @RequiresPermissions("bdxt:bdxtUser:edit")
    @RequestMapping("delete")
    public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
        bdxtUserService.delete(id);
        addMessage(redirectAttributes, "删除用户登录信息成功");
        return "redirect:" + Global.getAdminPath() + "/bdxt/bdxtUser/?repage";
    }


    /**
     * create by wangkun 2018/04/28
     * 导出excel 用户列表
     * @return
     */
    @RequestMapping(value = "/exportExcel")
    public void exportExcel(HttpServletResponse response, BdxtUser bdxtUser) throws IOException {
        String fileName = "用户列表信息.xls";// 导出Excel表格
        String[] title={"昵称","用户名","联系方式","类型","性别","城市","当前积分","职业","身高","胸围","腰围","臀围"};
        List<Map<String,Object>> mapList=new LinkedList<>();
        List<Object[]> obj = new ArrayList<Object[]>();
        Page<BdxtUser> page=bdxtUserService.findPage(new Page<BdxtUser>(), bdxtUser);
        if(CollectionUtils.isNotEmpty(page.getList())) {
            for (BdxtUser user : page.getList()) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("nickname", user.getNickname());
                map.put("realname", user.getRealName());
                map.put("phone", user.getPhone());
                map.put("userType", user.getUserType() != null ? user.getUserType() == 1 ? "普通会员" : "人才库会员" : "");
                map.put("gender", user.getGender() != null ? user.getGender() == 1 ? "男" : "女" : "");
                map.put("city",user.getCity());
                map.put("integral",user.getIntegral());
                map.put("job",user.getJob());
                map.put("height",user.getHeight());
                map.put("bust",user.getBust());
                map.put("waist",user.getWaist());
                map.put("hipline",user.getHipline());
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

    @RequiresPermissions("bdxt:bdxtUser:view")
    @RequestMapping(value = "/queryIntegralRecord")
    public String queryIntegralRecord(BdxtIntegralRecord record) {
        Page<BdxtIntegralRecord> page = bdxtIntegralRecordService.findPage(new Page<BdxtIntegralRecord>(request(), response()), record);
        attr("page", page);
        attr("userId",record.getBdxtUserId());
        return "bdxt/bdxtIntegralRecodeList";
    }


}