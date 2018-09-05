/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.entity.request.ProInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtProduct;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品管理Service
 * @author tjh
 * @version 2018-04-22
 */
public interface BdxtProductService extends ICrudService<BdxtProduct> {

    public boolean saveOrupdate(BdxtProduct bdxtProduct,MultipartFile[] files) throws Exception;

    public CommonResponse queryProInfo(ProInfoReq proInfoReq);

    public CommonResponse addOrder(ProInfoReq proInfoReq,BdxtUser bdxtUser);
}