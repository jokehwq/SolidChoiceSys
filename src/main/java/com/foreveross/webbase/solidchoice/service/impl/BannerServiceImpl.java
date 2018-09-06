package com.foreveross.webbase.solidchoice.service.impl;

import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.solidchoice.dao.BannerMapper;
import com.foreveross.webbase.solidchoice.entity.Banner;
import com.foreveross.webbase.solidchoice.service.BannerService;

import javax.annotation.Resource;
import java.util.List;

public class BannerServiceImpl implements BannerService {

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

}
