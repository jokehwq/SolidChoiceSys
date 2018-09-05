/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.service.impl;

import com.foreveross.webbase.bdxt.web.app.entity.request.NewsInfoReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.FileUtils;
import com.foreveross.webbase.common.utils.OssUtils;
import com.foreveross.webbase.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.bdxt.entity.BdxtNews;
import com.foreveross.webbase.bdxt.service.BdxtNewsService;
import com.foreveross.webbase.bdxt.dao.BdxtNewsDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 资讯信息Service
 * @author tanjinhua
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly=false)
public class BdxtNewsServiceImpl extends CrudService<BdxtNewsDao, BdxtNews> implements BdxtNewsService {

    private static String  ueditor_path = Global.getConfig("ueditor.uploaddir");

    @Override
    public boolean saveOrUpdateNews(BdxtNews news, MultipartFile file) throws Exception {
        //上传图片到oss服务器,重命名图片名
        String url = "";
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();
            //获取后缀
            String prefix = filename.substring(filename.lastIndexOf(".")+1);
            url = OssUtils.uploadFile(file.getInputStream(),UUID.randomUUID().toString()+"."+prefix);
        }else if(StringUtils.isNotBlank(news.getId())){
            BdxtNews bdxtNews_temp = get(news.getId());
            url = bdxtNews_temp.getNewsImageUrl();
            news.setNewsContentUrl(bdxtNews_temp.getNewsContentUrl());
            news.setBdxtUserId(bdxtNews_temp.getBdxtUserId());
        }
        news.setNewsImageUrl(url);
        //获取富文本图片，上传到oss
        //--------------------------------------------------------------------------
        String result_url = "";
        String img_url = "";
        String html = StringEscapeUtils.unescapeHtml4(news.getNewsContent());
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
                InputStream stream=null;
                stream=new FileInputStream(oss_file);
                String name = oss_file.getName();
                //获取后缀
                String prefixName = name.substring(name.lastIndexOf(".")+1);
                String oss_url = OssUtils.uploadFile(stream,UUID.randomUUID().toString()+"."+prefixName);

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
        news.setNewsContent(result_url);
        if(StringUtils.isNotBlank(img_url)){
            news.setNewsContentUrl(img_url);
        }
        //删除本地服务器下面的图片
        //this.getClass().getClassLoader().getResource("").getPath();
        FileUtils.deleteDirectory(ueditor_path+"/ueditor/jsp/upload");
        if(StringUtils.isNotBlank(news.getId())){
            //更新------删除老服务器下的图片。上传新图片，防止oss存储过大
            BdxtNews old_news = get(news.getId());
            List<String> del = new ArrayList<>();
            if(StringUtils.isNoneBlank(old_news.getNewsImageUrl())){
                del.add(old_news.getNewsImageUrl());
            }
            if(StringUtils.isNoneBlank(old_news.getNewsContentUrl())){
                String[] urls = old_news.getNewsContentUrl().split(";");
                del.addAll( Arrays.asList(urls));
            }
            if(del.size() >0){
                for( String str : del){
                    if(str.length() >1){
                        OssUtils.deleteFile(str);
                    }
                }
            }
        }
        save(news);
        return true;
    }

    @Override
    public boolean delectNews(String id) throws Exception {
        BdxtNews bdxtNews = get(id);
        List<String> del = new ArrayList<>();
        if(bdxtNews.getNewsImageUrl() != null){
            del.add(bdxtNews.getNewsImageUrl());
        }
        if(bdxtNews.getNewsContentUrl() != null){
            String[] urls = bdxtNews.getNewsContentUrl().split(";");
            del.addAll( Arrays.asList(urls));
        }
        if(del.size() > 0){
            for( String str : del){
                if(!StringUtils.isBlank(str)){
                    OssUtils.deleteFile(str);
                }
            }
        }
        delete(id);
        return true;
    }

    @Override
    public CommonResponse queryNewsInfo(NewsInfoReq newsInfoReq) {
        if (newsInfoReq.getPageNo() == null || newsInfoReq.getPageSize() == null)
            return null;

        PageHelper.startPage(newsInfoReq.getPageNo(), newsInfoReq.getPageSize());
        BdxtNews news = new BdxtNews();
        //只显示已发布的。
        news.setStatus(0);
        if(newsInfoReq.getType() != null && newsInfoReq.getType().length() > 1){
            news.setNewsType(newsInfoReq.getType());
        }
        List<BdxtNews> list = findList(news);

        if (CollectionUtils.isNotEmpty(list)) {
            PageInfo<BdxtNews> pageInfo = new PageInfo<>(list);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(),pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), new ArrayList<>()).build();
    }

    @Override
    public CommonResponse queryNewsInfoByUser(NewsInfoReq newsInfoReq) {
        if (newsInfoReq.getPageNo() == null || newsInfoReq.getPageSize() == null)
            return null;

        PageHelper.startPage(newsInfoReq.getPageNo(), newsInfoReq.getPageSize());
        BdxtNews news = new BdxtNews();
        news.setBdxtUserId(newsInfoReq.getBdxtUserId());
        news.setStatus(newsInfoReq.getStatus());
        if(newsInfoReq.getType() != null && newsInfoReq.getType().length() > 1){
            news.setNewsType(newsInfoReq.getType());
        }
        List<BdxtNews> list = findList(news);
        if (CollectionUtils.isNotEmpty(list)) {
            PageInfo<BdxtNews> pageInfo = new PageInfo<>(list);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), pageInfo).build();
        }
        return new CommonResponse.Builder(true, ConstantsEnum.NON_EXIST_VALUE.getMsg(),
                ConstantsEnum.STATUS_SUCCESS.getCode(), new ArrayList<>()).build();
    }

}