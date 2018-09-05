
/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.weixin.entity.WxKeyword;
import com.foreveross.webbase.weixin.entity.WxKeywordRelMsg;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.BasicMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.ImageMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.NewsMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;
import com.foreveross.webbase.weixin.service.WxKeywordRelMsgService;
import com.foreveross.webbase.weixin.service.WxKeywordService;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxMsgService;
import com.foreveross.webbase.weixin.dao.WxKeywordDao;

/**
 * 关键字管理ServiceImpl
 * @ClassName: WxKeywordServiceImpl
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午10:58:53
 */
@Service
public class WxKeywordServiceImpl extends CrudService<WxKeywordDao, WxKeyword> implements WxKeywordService {

	@Autowired
	private WxKeywordRelMsgService wxKeywordRelMsgService;

	@Autowired
	WxMsgService wxMsgService;

	@Autowired
	private WxKeywordDao wxKeywordDao;
	
	@Autowired
	WxMaterialNewsService wxMaterialNewsService;


	@Transactional(readOnly = false)
	@Override
	public String saveKeyword(WxKeyword wxKeyword) {
		Date date = new Date();
		if(StringUtils.isNotEmpty(wxKeyword.getKeyword())){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("accountid", wxKeyword.getAccountid());
			paramMap.put("keyword", wxKeyword.getKeyword());
			if(StringUtils.isNotEmpty(wxKeyword.getId())){
				paramMap.put("id", wxKeyword.getId());
			}
			List<WxKeyword> keywordlist = wxKeywordDao.findKeyword(paramMap);
			if(keywordlist != null && keywordlist.size() > 0){
				return "关键字已存在！";
			}
			if(StringUtils.isNotEmpty(wxKeyword.getId())){
				wxKeyword.setModifyTime(date);
			}else{
				wxKeyword.setCreateTime(date);
			    wxKeyword.setModifyTime(date);
			    }
			this.save(wxKeyword);
			return "关键字保存成功！";
		}
		return "请输入关键字！";
	}

	@Override
	public String getTextMsg(String msg,String accountid) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("accountid", accountid);
		paramMap.put("keyword", msg);
		List<WxKeyword> wxKeyword = wxKeywordDao.findKeyword(paramMap);
		WxKeywordRelMsg wxKeywordRelMsg=new WxKeywordRelMsg();
		wxKeywordRelMsg=wxKeywordRelMsgService.findWxKeywordRelMsgBykeyId(wxKeyword.get(0).getId());
		WxMsg wxMsg = wxMsgService.get(wxKeywordRelMsg.getMsgId());
		String textMsg = wxMsg.getContent();
		return textMsg;
	}

	public List<WxKeyword> selectKeywords(WxKeyword keyword){
		return this.dao.selectKeyAndMsgId(keyword);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteKeyword(String id) {
		WxKeywordRelMsg wxKeywordRelMsg = wxKeywordRelMsgService.findWxKeywordRelMsgBykeyId(id);
		if(wxKeywordRelMsg!=null){
			wxKeywordRelMsgService.delete(wxKeywordRelMsg.getId());
		}
		this.delete(id);
	}

	@Override
	public BasicMsg getTextOrTuwenMsg(TextMsg msg, String id) {
		WxKeyword keyword = new WxKeyword();
		keyword.setAccountid(id);
		BasicMsg replyMsg = null;
		List<WxKeyword> keywords = this.selectKeywords(keyword);
		for (WxKeyword wxkeyword : keywords) {
      		if (wxkeyword.getKeyword().trim().equals(msg.getContent().trim())) {
      			List<WxMsg> wxmsgs = wxkeyword.getMsgs();
      			//暂时只处理一个关键字对应一条消息
      			if(wxmsgs.size()==1){
				 WxMsg wxmsg = wxMsgService.get(wxmsgs.get(0));
				  String msgType = wxmsg.getMsgtype();
				   if(msgType.equals("text")){
					   String text=msg.getContent();
				    	String textMsg=this.getTextMsg(text,id);
				    	msg.setContent(textMsg);	
				    	replyMsg= msg;
				   }else if(msgType.equals("news")||msgType.equals("localnews")){
					 WxMaterialNews  material = new WxMaterialNews();
					 material.setAccountId(id);
					 material.setMsgId(wxmsg.getId());
					 NewsMsg  newsMsg = new NewsMsg();
					 newsMsg.setFromUserName(msg.getFromUserName());
					 newsMsg.setToUserName(msg.getToUserName());
					 newsMsg = wxMaterialNewsService.convertNewsMsg(material, newsMsg,msgType);
					 replyMsg =  newsMsg;
			   }else if(msgType.equals("image")){
					String mediaId = wxmsg.getMediaId();
					WxMaterialNews wxMaterialNews = new WxMaterialNews();
					wxMaterialNews.setAccountId(wxmsg.getAccountid());
					wxMaterialNews.setMediaId(mediaId);
					wxMaterialNews = wxMaterialNewsService.findImageByMediaId(wxMaterialNews);
					 ImageMsg  imageMsg = new ImageMsg();
					 imageMsg.setFromUserName(msg.getFromUserName());
					 imageMsg.setToUserName(msg.getToUserName());
					 imageMsg.setPicUrl(wxMaterialNews.getUrl());
					 imageMsg.setMediaId(wxMaterialNews.getMediaId());
					 replyMsg =  imageMsg;
			   }
				   
      	      }
		   }
		}
		if(replyMsg== null){
			//默认回复
			WxKeywordRelMsg wxKeywordRelMsg = wxKeywordRelMsgService.findWxKeywordRelMsgBykeyId(id);
				if(null != wxKeywordRelMsg && wxKeywordRelMsg.getId()!=null){
					WxMsg wxmsg=wxMsgService.get(wxKeywordRelMsg.getMsgId());
					if(wxmsg.getMsgtype().equals("text")){
						msg.setContent(wxmsg.getContent());
						replyMsg = msg;
					}else{
						WxMaterialNews  material = new WxMaterialNews();
						 material.setAccountId(id);
							 material.setMsgId(wxmsg.getId());
							 NewsMsg  newsMsg = new NewsMsg();
							 newsMsg.setFromUserName(msg.getFromUserName());
							 newsMsg.setToUserName(msg.getToUserName());
							 newsMsg = wxMaterialNewsService.convertNewsMsg(material, newsMsg,wxmsg.getMsgtype());
							 replyMsg =  newsMsg;
					}
				};
		}
		return replyMsg;
	}

}