/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service;

import java.util.List;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxKeyword;
import com.foreveross.webbase.weixin.sdk.vo.message.BasicMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;

/**
 * 关键字管理Service
 * @ClassName: WxKeywordService
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午10:57:44
 */
public interface WxKeywordService extends ICrudService<WxKeyword> {
	
	/**
	 * 保存关键字
	 * @param wxKeyword
	 * @return
	 */
	public String saveKeyword(WxKeyword wxKeyword);
	
	/**
	 * 获取关键字
	 * @param keyword
	 * @return
	 */
	public List<WxKeyword> selectKeywords(WxKeyword keyword);

	/**
	 * 通过关键字获取文本消息
	 * @param keyword
	 * @return
	 */
	public String getTextMsg(String msg,String accountid);

	/**删除关键字
	 * @param id
	 */
	public void deleteKeyword(String id);
	
	/**
	 * 通过关键字获取文本消息或者图文消息
	 * @return
	 */
	public BasicMsg getTextOrTuwenMsg(TextMsg msg, String id);
	
}