/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.bdxt.entity.BdxtAdMaterial;
import org.springframework.web.multipart.MultipartFile;

/**
 * 广告素材Service
 * @author tjh
 * @version 2018-04-21
 */
public interface BdxtAdMaterialService extends ICrudService<BdxtAdMaterial> {

    public boolean saveORupdate(BdxtAdMaterial bdxtAdMaterial,MultipartFile[] files) throws Exception;
}