package com.foreveross.webbase.weixin.service;

import java.util.List;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;


/**
 * @ClassName: WxNewsMsgService
 * @Description: 图文回复业务层接口
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午3:53:09
 */
public interface WxNewsMsgService extends ICrudService<WxMsg> {

	/**
	 * 保存微信图文消息
	 * @param materials
	 * @param wxmsg
	 * @param oldmediaid
	 */
	void saveNewsMsg(List<WxMaterialNews> materials, WxMsg wxmsg);
	
    /**
     * 保存本地图文消息
     * @param materials
     * @param wxmsg
     */
    void saveLocalNewsMsg(List<WxMaterialNews>materials,WxMsg wxmsg);
    
    /**
     * 分页查询图文消息 填充素材
     * @param page
     * @param wxmsg
     * @return
     */
    Page<WxMsg>findPageMsg(Page<WxMsg> page,WxMsg wxmsg);
    
	/**
	 * 分页查询本地图文
	 * @param page
	 * @param wxmsg
	 * @return
	 */
	public Page<WxMsg>findLocalPageMsg(Page<WxMsg> page,WxMsg wxmsg);
		
	/**
	 * 删除图文消息
	 * @param wxmsg
	 */
	public void deleteNewsMsg(WxMsg wxmsg);
	
}