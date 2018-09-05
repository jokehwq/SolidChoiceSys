package com.foreveross.webbase.weixin.sdk.core;

import java.util.Arrays;

import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.entity.WxSubscribeMsg;
import com.foreveross.webbase.weixin.sdk.vo.event.BasicEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.CustomServiceEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.LocationEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.MenuEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.ScanCodeEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.ScanEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.SendLocationInfoEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.SendPhotosEvent;
import com.foreveross.webbase.weixin.sdk.vo.message.Article;
import com.foreveross.webbase.weixin.sdk.vo.message.BasicMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.ImageMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.LinkMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.LocationMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.NewsMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.VideoMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.VoiceMsg;
import com.foreveross.webbase.weixin.sdk.vo.push.SentAllJobEvent;
import com.foreveross.webbase.weixin.sdk.vo.push.SentTmlJobEvent;
import com.foreveross.webbase.weixin.service.WxKeywordService;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxMsgService;
import com.foreveross.webbase.weixin.service.WxSubscribeMsgService;
import com.foreveross.webbase.weixin.service.WxUserService;

/**
 * 微信消息,事件处理器(实际生产中需要重写)
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
@Component
public class WechatDefHandler implements WechatHandler {

	@Autowired
	WxMsgService wxMsgService;
	
	@Autowired
	WxKeywordService wxkeyWordService;
	
	@Autowired
	WxUserService wxUserService;
	
	@Autowired
	WxMaterialNewsService wxMaterialNewsService;
	
	@Autowired
	private WxSubscribeMsgService wxSubscribeMsgService;

	
	@Override
	public BasicMsg defMsg(BasicMsg msg) {
		TextMsg text_msg = new TextMsg(msg);
		text_msg.setContent(msg.getMsgType());
		return text_msg;
	}

	@Override
	public BasicMsg defEvent(BasicEvent event) {
		TextMsg text_msg = new TextMsg(event);
		text_msg.setContent(Strings.join("\n", event.getEvent(),
				event.getEventKey()));
		return text_msg;
	}

	@Override
	public BasicMsg text(TextMsg msg, String id) {
		BasicMsg reMsg = wxkeyWordService.getTextOrTuwenMsg(msg, id);
		return reMsg;
	}

	@Override
	public BasicMsg image(ImageMsg msg, String id) {
		return msg;
	}

	@Override
	public BasicMsg voice(VoiceMsg msg, String id) {
		TextMsg text_msg = new TextMsg(msg);
		text_msg.setContent(Strings.join("\n", msg.getMediaId(),
				msg.getFormat(), msg.getRecognition()));
		return text_msg;
	}

	@Override
	public BasicMsg video(VideoMsg msg, String id) {
		TextMsg text_msg = new TextMsg(msg);
		text_msg.setContent(Strings.join("\n", msg.getMsgType(),
				msg.getMediaId(), msg.getThumbMediaId()));
		return text_msg;
	}

	@Override
	public BasicMsg shortVideo(VideoMsg msg, String id) {
		TextMsg text_msg = new TextMsg(msg);
		text_msg.setContent(Strings.join("\n", msg.getMsgType(),
				msg.getMediaId(), msg.getThumbMediaId()));
		return text_msg;
	}

	@Override
	public BasicMsg location(LocationMsg msg, String id) {
		TextMsg text_msg = new TextMsg(msg);
		text_msg.setContent(Strings.join("\n", msg.getX(), msg.getY(),
				String.valueOf(msg.getScale()), msg.getLabel()));
		return text_msg;
	}

	@Override
	public BasicMsg link(LinkMsg msg, String id) {
		NewsMsg news_msg = new NewsMsg(msg);
		Article art = new Article();
		art.setTitle(msg.getTitle());
		art.setDescription(msg.getDescription());
		art.setPicUrl("http://j2ee.u.qiniudn.com/mpsdk4j-logo.png-aliassmall");
		art.setUrl(msg.getUrl());
		news_msg.setArticles(Arrays.asList(art));
		return news_msg;
	}

	@Override
	public BasicMsg eClick(MenuEvent event, String id) {
		TextMsg text_msg = new TextMsg(event);
		text_msg.setContent(event.getEventKey());
		BasicMsg reMsg = wxkeyWordService.getTextOrTuwenMsg(text_msg, id);
		return reMsg;
	}

	@Override
	public void eView(MenuEvent event, String id) {
	
	}

	@Override
	public BasicMsg eSub(BasicEvent event, String id) {
		TextMsg text_msg = new TextMsg(event);
		this.wxUserService.SaveWxUser(text_msg.getFromUserName(), id);
		WxSubscribeMsg wxSubscribeMsg=wxSubscribeMsgService.getAccount(id);
		if(wxSubscribeMsg != null){
		String msgid=wxSubscribeMsg.getContent();
			WxMsg wxmsg=wxMsgService.get(msgid);
			if(wxmsg.getMsgtype().equals("text")){
				text_msg.setContent(wxmsg.getContent());
				return text_msg;
			}else{
				WxMaterialNews  material = new WxMaterialNews();
				material.setAccountId(id);
				material.setMsgId(wxmsg.getId());
				NewsMsg  newsMsg = new NewsMsg();
				newsMsg.setFromUserName(event.getFromUserName());
				newsMsg.setToUserName(event.getToUserName());
				newsMsg = wxMaterialNewsService.convertNewsMsg(material, newsMsg,wxmsg.getMsgtype());
				return  newsMsg;
			}
		}else{
			text_msg.setContent("欢迎关注");;
		}
		return text_msg;
	}

	@Override
	public void eUnSub(BasicEvent event, String id) {
		TextMsg text_msg = new TextMsg(event);
		this.wxUserService.updateUser(text_msg.getFromUserName(),id);
	}

	@Override
	public BasicMsg eScan(ScanEvent event, String id) {
		TextMsg text_msg = new TextMsg(event);
		text_msg.setContent(event.getEventKey() + event.getTicket());
		return text_msg;
	}

	@Override
	public void eLocation(LocationEvent event, String id) {
	}

	@Override
	public BasicMsg eScanCodePush(ScanCodeEvent event, String id) {
		TextMsg text_msg = new TextMsg(event);
		text_msg.setContent(Strings.join("\n", event.getEventKey(),
				event.getScanType(), event.getScanResult()));
		return text_msg;
	}

	@Override
	public BasicMsg eScanCodeWait(ScanCodeEvent event, String id) {
		return this.eScanCodePush(event, id);
	}

	@Override
	public BasicMsg ePicSysPhoto(SendPhotosEvent event, String id) {
		TextMsg text_msg = new TextMsg(event);
		text_msg.setContent(Strings.join(
				"\n",
				event.getEventKey(),
				String.valueOf(event.getSendPicsInfo().getCount()),
				String.valueOf(event.getSendPicsInfo().getPicList()),
				String.valueOf(event.getSendPicsInfo().getPicList().get(0)
						.getPicMd5Sum())));
		return text_msg;
	}

	@Override
	public BasicMsg ePicPhotoOrAlbum(SendPhotosEvent event, String id) {
		return this.ePicSysPhoto(event, id);
	}

	@Override
	public BasicMsg ePicWeixin(SendPhotosEvent event, String id) {
		return this.ePicSysPhoto(event, id);
	}

	@Override
	public BasicMsg eLocationSelect(SendLocationInfoEvent event, String id) {
		TextMsg text_msg = new TextMsg(event);
		text_msg.setContent(Strings.join("\n", event.getLocationX(),
				event.getLocationY(), event.getLabel(),
				String.valueOf(event.getScale()), event.getPoiname()));
		return text_msg;
	}

	@Override
	public void eSentTmplJobFinish(SentTmlJobEvent event, String id) {
	}

	@Override
	public void eSentAllJobFinish(SentAllJobEvent event, String id) {
	}

	@Override
	public void eCreateKfSession(CustomServiceEvent event, String id) {
	}

	@Override
	public void eCloseKfSession(CustomServiceEvent event, String id) {
	}

	@Override
	public void eSwitchKfSession(CustomServiceEvent event, String id) {
	}
}
