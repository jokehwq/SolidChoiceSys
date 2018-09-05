package com.foreveross.webbase.weixin.sdk.core;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.foreveross.webbase.weixin.sdk.common.EventType;
import com.foreveross.webbase.weixin.sdk.common.MessageType;
import com.foreveross.webbase.weixin.sdk.exception.WechatRunTimeException;
import com.foreveross.webbase.weixin.sdk.repo.com.qq.weixin.mp.aes.AesException;
import com.foreveross.webbase.weixin.sdk.repo.com.qq.weixin.mp.aes.SHA1;
import com.foreveross.webbase.weixin.sdk.repo.com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.foreveross.webbase.weixin.sdk.util.StreamTool;
import com.foreveross.webbase.weixin.sdk.vo.MPAccount;
import com.foreveross.webbase.weixin.sdk.vo.event.BasicEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.CustomServiceEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.LocationEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.MenuEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.ScanCodeEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.ScanEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.SendLocationInfoEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.SendPhotosEvent;
import com.foreveross.webbase.weixin.sdk.vo.message.BasicMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.CustomerServiceMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.ImageMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.LinkMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.LocationMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.MusicMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.NewsMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.VideoMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.VoiceMsg;
import com.foreveross.webbase.weixin.sdk.vo.push.SentAllJobEvent;
import com.foreveross.webbase.weixin.sdk.vo.push.SentTmlJobEvent;

/**
 * 微信消息内核 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class WechatKernel {

	private static final Log log = Logs.get();
	private SAXParserFactory factory = SAXParserFactory.newInstance(); // XML解析准备
	private SAXParser xmlParser;
	private MessageHandler msgHandler = new MessageHandler();
	private WechatHandler handler; 	// 消息处理器
	private Map<String, String[]> params; 	// Request参数
	private MPAccount mpAct; 	// 公众号信息

	public WechatKernel() {
		try {
			xmlParser = factory.newSAXParser();
		} catch (Exception e) {
			throw Lang.wrapThrow(new WechatRunTimeException(
					"初始化SAXParserFactory出现异常", e));
		}
	}

	public WechatKernel(MPAccount mpAct, WechatHandler handler,
			Map<String, String[]> params) {
		this();
		this.mpAct = mpAct;
		this.handler = handler;
		this.params = params;
	}

	/**
	 * 设置微信服务器请求参数
	 * @param params  请求参数
	 */
	public void setParams(Map<String, String[]> params) {
		this.params = params;
		if (log.isDebugEnabled()) {
			Set<Entry<String, String[]>> es = params.entrySet();
			log.debug("微信服务器请求参数列表.");
			for (Entry<String, String[]> e : es) {
				log.debugf("%s-%s", e.getKey(), e.getValue()[0]);
			}
		}
	}

	/**
	 * 设置公众号信息
	 * @param mpAct 公众号信息
	 */
	public void setMpAct(MPAccount mpAct) {
		this.mpAct = mpAct;
	}

	/**
	 * 设置微信消息处理器
	 * @param handler 消息处理器
	 */
	public void setWechatHandler(WechatHandler handler) {
		this.handler = handler;
	}

	/**
	 * 获取参数值
	 * @param param 参数名
	 * @return 参数值
	 */
	protected String get(String param) {
		String[] vals = params.get(param);
		return vals == null ? null : vals[0];
	}

	/**
	 * 微信服务器校验
	 * @return 微信服务器随机字符串
	 */
	public String check() {
		String sign = get("signature");
		String ts = get("timestamp");
		String nonce = get("nonce");
		if (sign == null || sign.length() > 128 || ts == null
				|| ts.length() > 128 || nonce == null || nonce.length() > 128) {
			log.warnf("接入微信服务器认证的加密参数为空或是长度大于128.");
			return "error";
		}

		try {
			String validsign = SHA1.calculate(mpAct.getToken(), ts, nonce);
			if (log.isDebugEnabled()) {
				log.debugf("接入微信服务器认证: %b. 加密字符串: %s",
						Lang.equals(validsign, sign), validsign);
			}

			if (sign.equals(validsign)) {
				return get("echostr");
			}

			return "error";
		} catch (AesException e) {
			throw Lang.wrapThrow(new WechatRunTimeException("校验服务器认证出现异常", e));
		}
	}

	/**
	 * 与微信服务器交互处理过程
	 * @param is 微信服务器推送消息
	 * @return 响应消息
	 */
	// TODO 是否考虑添加重复消息过滤功能
	public String handle(InputStream is, String id) {
		String encrypt = get("encrypt_type");
		WXBizMsgCrypt pc = null;
		BasicMsg msg = null;
		String respmsg = "success";
		
		// 密文模式
		if (encrypt != null && "aes".equals(encrypt)
				&& mpAct.getAESKey() != null) {
			try {
				pc = new WXBizMsgCrypt(mpAct.getToken(), mpAct.getAESKey(),
						mpAct.getAppId());
				String ts = get("timestamp");
				String nonce = get("nonce");
				String msgsign = get("msg_signature");
				String decmsg = pc.decryptMsg(msgsign, ts, nonce, is);
				xmlParser.parse(StreamTool.toStream(decmsg), msgHandler);
				msg = handleMsg(id);
				respmsg = pc.encryptMsg(responseXML(msg), ts, nonce);
			} catch (Exception e) {
				throw Lang
						.wrapThrow(new WechatRunTimeException("使用密文模式出现异常", e));
			}
		}
		
		// 明文模式
		else {
			try {
				xmlParser.parse(is, msgHandler);
			} catch (Exception e) {
				throw Lang.wrapThrow(new WechatRunTimeException(
						"明文模式下解析消息出现异常", e));
			}
			msg = handleMsg(id);
			respmsg = responseXML(msg);
		}

		return respmsg;
	}

	/**
	 * 微信消息处理
	 * @return 回复消息
	 */
	protected BasicMsg handleMsg(String id) {
		String msgtype = msgHandler.getValues().get("msgType");
		if ("event".equals(msgtype)) {
			return handleEventMsg(id);
		} else {
			return handleNormalMsg(id);
		}
	}

	/**
	 * 处理普通消息
	 * @param id  公众号id
	 * @return 回复消息
	 */
	protected BasicMsg handleNormalMsg(String id) {
		BasicMsg msg = null;
		MessageType mt;
		try {
			mt = MessageType.valueOf(msgHandler.getValues().get("msgType"));
		} catch (Exception e) {
			log.error("处理微信普通消息时发现新的消息类型,请查阅官方更新文档.");
			mt = MessageType.def;
		}
		switch (mt) {
		case text:
			TextMsg tm = new TextMsg(msgHandler.getValues());
			msg = handler.text(tm, id);
			break;
		case image:
			ImageMsg im = new ImageMsg(msgHandler.getValues());
			msg = handler.image(im, id);
			break;
		case voice:
			VoiceMsg vom = new VoiceMsg(msgHandler.getValues());
			msg = handler.voice(vom, id);
			break;
		case video:
			VideoMsg vim = new VideoMsg(msgHandler.getValues());
			msg = handler.video(vim, id);
			break;
		case shortvideo:
			VideoMsg shortvim = new VideoMsg(msgHandler.getValues());
			msg = handler.shortVideo(shortvim, id);
			break;
		case location:
			LocationMsg locm = new LocationMsg(msgHandler.getValues());
			msg = handler.location(locm, id);
			break;
		case link:
			LinkMsg lm = new LinkMsg(msgHandler.getValues());
			msg = handler.link(lm, id);
			break;
		default:
			BasicMsg bm = new BasicMsg(msgHandler.getValues());
			msg = handler.defMsg(bm);
			break;
		}
		return msg;
	}

	/**
	 * 处理事件消息
	 * @return 回复消息
	 */
	protected BasicMsg handleEventMsg(String id) {
		BasicMsg msg = null;
		EventType et;
		try {
			et = EventType.valueOf(msgHandler.getValues().get("event"));
		} catch (Exception e) {
			log.error("处理微信事件消息时发现新的事件类型,请查阅官方更新文档.");
			et = EventType.def;
		}
		switch (et) {
		case subscribe:
			BasicEvent sube = new BasicEvent(msgHandler.getValues());
			msg = handler.eSub(sube, id);
			break;
		case unsubscribe:
			BasicEvent unsube = new BasicEvent(msgHandler.getValues());
			handler.eUnSub(unsube, id);
			break;
		case SCAN:
			ScanEvent se = new ScanEvent(msgHandler.getValues());
			msg = handler.eScan(se, id);
			break;
		case LOCATION:
			LocationEvent le = new LocationEvent(msgHandler.getValues());
			handler.eLocation(le, id);
			break;
		case CLICK:
			MenuEvent cme = new MenuEvent(msgHandler.getValues());
			msg = handler.eClick(cme, id);
			break;
		case VIEW:
			MenuEvent vme = new MenuEvent(msgHandler.getValues());
			handler.eView(vme, id);
			break;
		case scancode_push:
			ScanCodeEvent sce = new ScanCodeEvent(msgHandler.getValues());
			msg = handler.eScanCodePush(sce, id);
			break;
		case scancode_waitmsg:
			ScanCodeEvent scemsg = new ScanCodeEvent(msgHandler.getValues());
			msg = handler.eScanCodeWait(scemsg, id);
			break;
		case pic_sysphoto:
			SendPhotosEvent spesys = new SendPhotosEvent(msgHandler.getValues());
			msg = handler.ePicSysPhoto(spesys, id);
			break;
		case pic_photo_or_album:
			SendPhotosEvent spealb = new SendPhotosEvent(msgHandler.getValues());
			msg = handler.ePicPhotoOrAlbum(spealb, id);
			break;
		case pic_weixin:
			SendPhotosEvent spewx = new SendPhotosEvent(msgHandler.getValues());
			msg = handler.ePicWeixin(spewx, id);
			break;
		case location_select:
			SendLocationInfoEvent lse = new SendLocationInfoEvent(
					msgHandler.getValues());
			msg = handler.eLocationSelect(lse, id);
			break;
			
		// TODO 暂不清楚微信的推送
		/*
		 * case media_id: case view_limited: BasicEvent mvbe = new
		 * BasicEvent(msgHandler.getValues()); msg = handler.defEvent(mvbe);
		 * break;
		 */
			
		case TEMPLATESENDJOBFINISH:
			SentTmlJobEvent stje = new SentTmlJobEvent(msgHandler.getValues());
			handler.eSentTmplJobFinish(stje, id);
			break;
		case MASSSENDJOBFINISH:
			SentAllJobEvent saje = new SentAllJobEvent(msgHandler.getValues());
			handler.eSentAllJobFinish(saje, id);
			break;
		case kf_create_session:
			CustomServiceEvent sce_create = new CustomServiceEvent(
					msgHandler.getValues());
			handler.eCreateKfSession(sce_create, id);
			break;
		case kf_close_session:
			CustomServiceEvent sce_close = new CustomServiceEvent(
					msgHandler.getValues());
			handler.eCloseKfSession(sce_close, id);
			break;
		case kf_switch_session:
			CustomServiceEvent sce_switch = new CustomServiceEvent(
					msgHandler.getValues());
			handler.eSwitchKfSession(sce_switch, id);
			break;
		default:
			BasicEvent be = new BasicEvent(msgHandler.getValues());
			msg = handler.defEvent(be);
			break;
		}
		return msg;
	}

	/**
	 * 输出回复消息
	 * @param msg 回复消息数据
	 * @return XML消息
	 */
	protected String responseXML(BasicMsg msg) {
		String respmsg = "success";
		if (msg == null || Strings.isBlank(msg.getMsgType())) {
			return respmsg;
		}
		
		// 交换 fromUser 和 toUser
		String fromUser = msg.getFromUserName();
		String toUser = msg.getToUserName();
		msg.setFromUserName(toUser);
		msg.setToUserName(fromUser);
		MessageType mt = MessageType.valueOf(msg.getMsgType());
		switch (mt) {
		case text:
			respmsg = XmlMsgBuilder.create().text((TextMsg) msg).build();
			break;
		case image:
			respmsg = XmlMsgBuilder.create().image((ImageMsg) msg).build();
			break;
		case voice:
			respmsg = XmlMsgBuilder.create().voice((VoiceMsg) msg).build();
			break;
		case music:
			respmsg = XmlMsgBuilder.create().music((MusicMsg) msg).build();
			break;
		case video:
			respmsg = XmlMsgBuilder.create().video((VideoMsg) msg).build();
			break;
		case news:
			respmsg = XmlMsgBuilder.create().news((NewsMsg) msg).build();
			break;
		case transfer_customer_service:
			respmsg = XmlMsgBuilder.create()
					.transferCustomerService((CustomerServiceMsg) msg).build();
			break;
		default:
			break;
		}
		return respmsg;
	}

}
