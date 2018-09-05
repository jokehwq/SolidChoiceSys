package com.foreveross.webbase.weixin.sdk.core;

import com.foreveross.webbase.weixin.sdk.vo.event.BasicEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.CustomServiceEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.LocationEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.MenuEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.ScanCodeEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.ScanEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.SendLocationInfoEvent;
import com.foreveross.webbase.weixin.sdk.vo.event.SendPhotosEvent;
import com.foreveross.webbase.weixin.sdk.vo.message.BasicMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.ImageMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.LinkMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.LocationMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.VideoMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.VoiceMsg;
import com.foreveross.webbase.weixin.sdk.vo.push.SentAllJobEvent;
import com.foreveross.webbase.weixin.sdk.vo.push.SentTmlJobEvent;

/**
 * 微信消息处理集合
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public interface WechatHandler {

    /**
     * 新消息类型
     * 
     * @param msg
     *            新消息
     * @return 回复消息
     */
    BasicMsg defMsg(BasicMsg msg);

    /**
     * 新事件类型
     * 
     * @param event
     *            新类型
     * @return 回复消息
     */
    BasicMsg defEvent(BasicEvent event);

    /**
     * 处理文本消息
     *
     * @param msg
     *            文本消息
     * @return 回复消息
     */
    BasicMsg text(TextMsg msg,String id);

    /**
     * 处理图像消息
     *
     * @param msg
     *            图像消息
     * @return 回复消息
     */
    BasicMsg image(ImageMsg msg,String id);

    /**
     * 处理音频消息
     *
     * @param msg
     *            音频消息
     * @return 回复消息
     */
    BasicMsg voice(VoiceMsg msg,String id);

    /**
     * 处理视频消息
     *
     * @param msg
     *            视频消息
     * @return 回复消息
     */
    BasicMsg video(VideoMsg msg,String id);

    /**
     * 处理短视频消息
     *
     * @param msg
     *            短视频消息
     * @return 回复消息
     */
    BasicMsg shortVideo(VideoMsg msg,String id);

    /**
     * 处理地理位置消息
     * 
     * @param msg
     *            地理位置
     * @return 回复消息
     */
    BasicMsg location(LocationMsg msg,String id);

    /**
     * 处理链接消息
     *
     * @param msg
     *            链接消息
     * @return 回复消息
     */
    BasicMsg link(LinkMsg msg,String id);

    /**
     * 处理菜单点击事件消息
     *
     * @param event
     *            菜单事件
     * @return 回复消息
     */
    BasicMsg eClick(MenuEvent event,String id);

    /**
     * 处理菜单视图事件消息
     *
     * @param event
     *            菜单事件
     */
    void eView(MenuEvent event,String id);

    /**
     * 处理订阅事件消息
     *
     * @param event
     *            事件消息
     * @return 回复消息
     */
    BasicMsg eSub(BasicEvent event,String id);

    /**
     * 处理退订事件消息
     *
     * @param event
     *            事件消息
     */
    void eUnSub(BasicEvent event,String id);

    /**
     * 处理扫描事件消息
     *
     * @param event
     *            事件消息
     * @return 回复消息
     */
    BasicMsg eScan(ScanEvent event,String id);

    /**
     * 处理自动上传地理事件消息
     *
     * @param event
     *            地理事件事件
     */
    void eLocation(LocationEvent event,String id);

    /**
     * 处理二维码扫描事件消息
     *
     * @param event
     *            扫码事件
     * @return 回复消息
     */
    BasicMsg eScanCodePush(ScanCodeEvent event,String id);

    /**
     * 扫码推事件且弹出“消息接收中”提示框
     *
     * @param event
     *            扫码事件
     * @return 回复消息
     */
    BasicMsg eScanCodeWait(ScanCodeEvent event,String id);

    /**
     * 处理弹出系统拍照发图的事件推送
     *
     * @param event
     *            发图事件
     * @return 回复消息
     */
    BasicMsg ePicSysPhoto(SendPhotosEvent event,String id);

    /**
     * 处理弹出拍照或者相册发图的事件推送
     *
     * @param event
     *            发图事件
     * @return 回复消息
     */
    BasicMsg ePicPhotoOrAlbum(SendPhotosEvent event,String id);

    /**
     * 处理弹出微信相册发图器的事件推送
     *
     * @param event
     *            发图事件
     * @return 回复消息
     */
    BasicMsg ePicWeixin(SendPhotosEvent event,String id);

    /**
     * 处理弹出地理位置选择器的事件推送消息
     *
     * @param event
     *            地理位置选取事件
     * @return 回复消息
     */
    BasicMsg eLocationSelect(SendLocationInfoEvent event,String id);

    /**
     * 处理模板发送事件消息
     *
     * @param event
     *            发送模板消息结果事件
     */
    void eSentTmplJobFinish(SentTmlJobEvent event,String id);

    /**
     * 处理群发消息事件消息
     *
     * @param event
     *            群发消息结果事件
     */
    void eSentAllJobFinish(SentAllJobEvent event,String id);

    /**
     * 处理创建多客服事件消息
     *
     * @param event
     *  多客服消息事件
     */
    void eCreateKfSession(CustomServiceEvent event,String id);

    /**
     * 处理关闭多客服事件消息
     * @param event 多客服消息事件
     */
    void eCloseKfSession(CustomServiceEvent event,String id);

    /**
     * 处理转接多客服事件消息
     * @param event 多客服消息事件
     */
    void eSwitchKfSession(CustomServiceEvent event,String id);

}
