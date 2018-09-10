package com.foreveross.webbase.solidchoice.service.impl;

import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.OssUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.solidchoice.dao.BannerMapper;
import com.foreveross.webbase.solidchoice.entity.Banner;
import com.foreveross.webbase.solidchoice.service.BannerService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly=false)
public class BannerServiceImpl extends CrudService<BannerMapper, Banner> implements BannerService {

    @Resource
    private BannerMapper bannerDao;

    @Override
    public List<Banner> selectBannerList() {
        return bannerDao.selectBannerList();
    }

    @Override
    public CommonResponse queryDictInfo(Banner banner) {
        /*List<Banner> dictList= bannerDao.queryDictListByCondition(banner.getId(),banner.getText());
        if (CollectionUtils.isNotEmpty(dictList)) {
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), dictList).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();*/
        return  null;
    }

    @Override
    public boolean saveOrUpdateNews(Banner banner, MultipartFile file) throws Exception {
        //上传图片到oss服务器,重命名图片名
        String url = "";
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();
            //获取后缀
            String prefix = filename.substring(filename.lastIndexOf(".")+1);
            url = OssUtils.uploadFile(file.getInputStream(), UUID.randomUUID().toString()+"."+prefix);
        }else if(StringUtils.isNotBlank(banner.getId())){
            Banner bdxtNews_temp = get(banner.getId());
            url = bdxtNews_temp.getImgUrl();
//            banner.setNewsContentUrl(bdxtNews_temp.getNewsContentUrl());
//            banner.setBdxtUserId(bdxtNews_temp.getBdxtUserId());
        }
        banner.setImgUrl(url);
        //获取富文本图片，上传到oss
        //--------------------------------------------------------------------------
        String result_url = "";
        String img_url = "";


        String res ="";

        result_url = StringEscapeUtils.escapeHtml4(res);
        //---------------------------------------------------------------------------

        //删除本地服务器下面的图片
        //this.getClass().getClassLoader().getResource("").getPath();
        if(StringUtils.isNotBlank(banner.getId())){
            //更新------删除老服务器下的图片。上传新图片，防止oss存储过大
            Banner old_news = get(banner.getId());
            List<String> del = new ArrayList<>();
            if(StringUtils.isNoneBlank(old_news.getImgUrl())){
                del.add(old_news.getImgUrl());
            }
            if(del.size() >0){
                for( String str : del){
                    if(str.length() >1){
                        OssUtils.deleteFile(str);
                    }
                }
            }
        }
        save(banner);
        return true;
    }

}
