/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.bdxt.dao.BdxtProductOrderDao;
import com.foreveross.webbase.bdxt.entity.BdxtProductOrder;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtProductOrderService;
import com.foreveross.webbase.bdxt.web.app.entity.request.ProInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.FileUtils;
import com.foreveross.webbase.common.utils.OssUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.bdxt.entity.BdxtProduct;
import com.foreveross.webbase.bdxt.service.BdxtProductService;
import com.foreveross.webbase.bdxt.dao.BdxtProductDao;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 商品管理Service
 * @author tjh
 * @version 2018-04-22
 */
@Service
@Transactional(readOnly=false)
public class BdxtProductServiceImpl extends CrudService<BdxtProductDao, BdxtProduct> implements BdxtProductService {

    @Resource
    private BdxtProductOrderService bdxtProductOrderService;

    private static String  ueditor_path = Global.getConfig("ueditor.uploaddir");
    @Override
    public boolean saveOrupdate(BdxtProduct bdxtProduct, MultipartFile[] files) throws Exception{
        //上传图片
        if(StringUtils.isNotBlank(files[0].getName())){
            //判断修改文件时，文件是否上传过。
            if(StringUtils.isNotBlank(bdxtProduct.getImg())){
                //删除文件
                String img = bdxtProduct.getImg();
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
                bdxtProduct.setImg(img_arr.toJSONString());

        }

        //获取富文本图片，上传到oss
        //--------------------------------------------------------------------------
        String result_url = "";
        String img_url = "";
        String html = StringEscapeUtils.unescapeHtml4(bdxtProduct.getContent());
        System.out.println(html);
        String[] split_1 = html.split("src=");
        String res ="";
        for(int i =0;i<=split_1.length-1;i++){
            String[] split_2 = split_1[i].split(" ");  //
            if(split_2.length >1 && split_2[0].contains("/ueditor/jsp/upload/")){
                //获取第一个数据额
                String str_2 = split_2[0];
                //判断是否带单引号----查找目录
                String file_ = "";
                if(str_2.substring(0,1).equals("'")){
                    file_ = str_2.substring(1,str_2.length()-1);
                }
                if(str_2.substring(0,1).equals("\"")){
                    file_ = str_2.substring(1,str_2.length()-1);
                }
                //上传服务器
                File oss_file = new File(ueditor_path+file_);
                InputStream stream=new FileInputStream(oss_file);

                String name = oss_file.getName();
                //获取后缀
                String prefixName = name.substring(name.lastIndexOf(".")+1);
                String oss_url = OssUtils.uploadFile(stream, UUID.randomUUID().toString()+"."+prefixName);

                //判断是否带单引号
                if(str_2.substring(0,1).equals("'")){
                    oss_url = "'"+oss_url+"'";
                }
                if(str_2.substring(0,1).equals("\"")){
                    oss_url = "\""+oss_url+"\"";
                }
                split_1[i] = split_1[i].replace(str_2,oss_url);//字符串替换
                img_url = img_url+oss_url+",";
            }
            if(i == split_1.length-1){
                res = res+split_1[i];
            }else{
                res = res+split_1[i] + "src=";
            }
        }
        result_url = StringEscapeUtils.escapeHtml4(res);
        //---------------------------------------------------------------------------
        bdxtProduct.setContent(result_url);
        if(StringUtils.isNotBlank(img_url)){
            bdxtProduct.setContentimg(img_url);
        }

        //删除本地服务器下面的图片
        //this.getClass().getClassLoader().getResource("").getPath();
        FileUtils.deleteDirectory(ueditor_path+"/ueditor/jsp/upload");
        if(StringUtils.isNotBlank(bdxtProduct.getId())){
            //更新------删除老服务器下的图片。上传新图片，防止oss存储过大
            BdxtProduct old_pro = get(bdxtProduct.getId());

            if(StringUtils.isNoneBlank(old_pro.getContentimg())){
                String[] urls = old_pro.getContentimg().split(";");
                for( String str : urls){
                    if(str.length() >1){
                        try {
                            OssUtils.deleteFile(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        save(bdxtProduct);
        return true;
    }

    @Override
    public CommonResponse queryProInfo(ProInfoReq proInfoReq) {
        if (proInfoReq.getPageNo() == null || proInfoReq.getPageSize() == null)
            return null;

        PageHelper.startPage(proInfoReq.getPageNo(), proInfoReq.getPageSize());
        BdxtProduct bdxtProduct = new BdxtProduct();
        if(StringUtils.isNotBlank(proInfoReq.getName())){
            bdxtProduct.setName(proInfoReq.getName());
        }
        List<BdxtProduct> list = findList(bdxtProduct);
        if (CollectionUtils.isNotEmpty(list)) {
            PageInfo<BdxtProduct> pageInfo = new PageInfo<>(list);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), new ArrayList<>()).build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class)
    public CommonResponse addOrder(ProInfoReq proInfoReq,BdxtUser bdxtUser) {
        logger.info("下单开始：{}", proInfoReq);
        //判断积分
        int integral = bdxtUser.getIntegral().intValue();
        if(integral< proInfoReq.getIntegral())
            return new CommonResponse.Builder(true, "积分不足！",
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();

        //查询库存
        BdxtProduct bdxtProduct = get(proInfoReq.getProId());
        if(bdxtProduct.getCount()<proInfoReq.getCount())
            return new CommonResponse.Builder(true, "库存不足！",
                    ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
        //生成订单信息，并且扣减库存
        String temp = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
        BdxtProductOrder bdxtProductOrder = new BdxtProductOrder();
        bdxtProductOrder.setName(bdxtProduct.getName());
        bdxtProductOrder.setCode(bdxtProduct.getCode());
        bdxtProductOrder.setType(bdxtProduct.getType());
        bdxtProductOrder.setPrice(bdxtProduct.getPrice());
        bdxtProductOrder.setPeople(proInfoReq.getPeople());
        bdxtProductOrder.setCount(proInfoReq.getCount());
        bdxtProductOrder.setStatus(1);//待发货
        bdxtProductOrder.setRecipients(proInfoReq.getRecipients());
        bdxtProductOrder.setPhone(proInfoReq.getPhone());
        bdxtProductOrder.setAddr(proInfoReq.getAddr());
        bdxtProductOrder.setOrdercode("D"+temp);
        //更新订单以及库存
        bdxtProductOrderService.save(bdxtProductOrder);
        bdxtProduct.setCount(bdxtProduct.getCount()-proInfoReq.getCount());
        save(bdxtProduct);
        return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), "").build();
    }
}