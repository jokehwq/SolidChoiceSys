/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.common.utils.OssUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.bdxt.entity.BdxtAdMaterial;
import com.foreveross.webbase.bdxt.service.BdxtAdMaterialService;
import com.foreveross.webbase.bdxt.dao.BdxtAdMaterialDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 广告素材Service
 * @author tjh
 * @version 2018-04-21
 */
@Service
@Transactional(readOnly=false)
public class BdxtAdMaterialServiceImpl extends CrudService<BdxtAdMaterialDao, BdxtAdMaterial> implements BdxtAdMaterialService {

    @Override
    public boolean saveORupdate(BdxtAdMaterial bdxtAdMaterial, MultipartFile[] files)  throws Exception{

        //上传图片
        if(StringUtils.isNotBlank(files[0].getOriginalFilename())){
            //判断修改文件时，文件是否上传过。
            if(bdxtAdMaterial.getImg().length() >10){
                //删除文件
                String img = bdxtAdMaterial.getImg();
                JSONArray jsonObject = JSONObject.parseArray(img);
                for(Object obj : jsonObject){
                    OssUtils.deleteFile(obj.toString());
                }
            }
            //上传文件
            JSONArray img_arr = new JSONArray();
            for(MultipartFile mf : files){
                try {
                    //获取后缀
                    String filename = mf.getOriginalFilename();
                    String prefix = filename.substring(filename.lastIndexOf(".")+1);
                    String url = OssUtils.uploadFile(mf.getInputStream(), UUID.randomUUID().toString()+"."+prefix);
                    img_arr.add(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bdxtAdMaterial.setImg(img_arr.toJSONString());

        }
        save(bdxtAdMaterial);
        return true;
    }
}