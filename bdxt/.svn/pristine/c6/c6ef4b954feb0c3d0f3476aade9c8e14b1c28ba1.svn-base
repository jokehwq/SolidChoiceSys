/**
 * @author senhui.li
 */
package com.foreveross.webbase.weixin.sdk.api;

import com.foreveross.webbase.weixin.sdk.vo.api.Template;
import com.foreveross.webbase.weixin.sdk.vo.message.BasicMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.GroupMessage;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;

/**
 * 微信高级消息接口
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public interface MessageAPI {
    
    /**
     * 发模板消息地址
     */
    String send_template = "/message/template/send?access_token=";
    
    /**
     * 群发消息
     */
    String send_all = "/message/mass/sendall?access_token=";
    
    /**
     * 发送客服客服消息
     */
    String send_custom = "/message/custom/send?access_token=";

    /**
     * 发送模板消息
     * @param openId
     *            接收用户Id
     * @param tmlId
     *            模板Id
     * @param topColor
     *            顶部颜色
     * @param url
     *            跳转链接
     * @param tmls
     *            模板数据
     * @return 消息Id
     */
    long sendTemplateMsg(String openId, String tmlId, String topColor, String url, Template... tmls);
    
    /**
     * 发送群发消息
     * @param groupMessage
     * @return
     */
    public String sendAllMessage(GroupMessage groupMessage);
    
    /**
     * 发送文本客服消息
     * @param openId 接收用户的id
     * @param msg 发送的文本消息
     * @return
     */
    public String sendTextCustomMsg(String openId, TextMsg msg);
  
}
