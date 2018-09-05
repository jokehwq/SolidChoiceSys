package com.foreveross.webbase.weixin.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxNewsMsgDao;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxNewsMsgService;


/**
 * @ClassName: WxNewsMsgServiceImpl
 * @Description: 图文回复业务层实现类
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午3:56:51
 */
@Service
public class WxNewsMsgServiceImpl extends CrudService<WxNewsMsgDao, WxMsg> implements
		WxNewsMsgService {
	
	@Autowired
	WxMaterialNewsService materialNewsService;
	
	public Page<WxMsg>findPageMsg(Page<WxMsg> page,WxMsg wxmsg){		
		wxmsg.setPage(page);
		wxmsg.setMsgtype("news");
		 List<WxMsg> wxMsgs = dao.findList(wxmsg);
		 WxMaterialNews  material = new WxMaterialNews();
		 material.setAccountId(wxmsg.getAccountid());
		 material.setMaterialType("news");
		 for(WxMsg msg:wxMsgs){
			 material.setMsgId(msg.getId());
			 List<WxMaterialNews> materials = this.materialNewsService.getMateriaslByMsgId(material);
			 for(WxMaterialNews news:materials){
			  material.setMediaId(news.getThumbMediaId());
			  news.setThumbMediaUrl(this.materialNewsService.getlocalImageUrl(material));
	        }
			msg.setMaterials(materials);
		 }		 
		page.setList(wxMsgs);
		return page;
	}
	
	@Transactional(readOnly=false)
	public void saveNewsMsg(List<WxMaterialNews>materials,WxMsg wxmsg){
		   String oldmediaid = wxmsg.getMediaId();
			save(wxmsg);
			materialNewsService.saveNewsMaterial(materials,wxmsg,oldmediaid);
			//更新mediaId
			save(wxmsg);
	}
	
	@Transactional(readOnly=false)	
	public void saveLocalNewsMsg(List<WxMaterialNews>materials,WxMsg wxmsg){
			
			if(StringUtils.isEmpty(wxmsg.getId())){
				save(wxmsg);
				materialNewsService.saveLocalNewsMaterial(materials,wxmsg);
			}else{
				save(wxmsg);
				materialNewsService.updateLocalNewsMaterial(materials,wxmsg);
			}
			
	}
	
	public Page<WxMsg>findLocalPageMsg(Page<WxMsg> page,WxMsg wxmsg){		
		wxmsg.setPage(page);
		wxmsg.setMsgtype("localnews");
		 List<WxMsg> wxMsgs = dao.findList(wxmsg);
		 WxMaterialNews  material = new WxMaterialNews();
		 material.setAccountId(wxmsg.getAccountid());		
		 for(WxMsg msg:wxMsgs){
			 material.setMsgId(msg.getId());
			 List<WxMaterialNews> materials = this.materialNewsService.getLocalMateriaslByMsgId(material);
			msg.setMaterials(materials);
		 }		 
		page.setList(wxMsgs);
		return page;
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteNewsMsg(WxMsg wxmsg) {
		WxMaterialNews material = new WxMaterialNews(); 
		material.setMsgId(wxmsg.getId());
		material.setAccountId(wxmsg.getAccountid());
		List<WxMaterialNews> materials = null;
		if(wxmsg.getMsgtype().equals("news")){	
			materials = materialNewsService.getMateriaslByMsgId(material);
		for(WxMaterialNews news:materials){
			materialNewsService.delMaterial(news);
		}			
		}else if(wxmsg.getMsgtype().equals("localnews")){
			materials = materialNewsService.getLocalMateriaslByMsgId(material);			
			materialNewsService.deleteLocalNewsMaterial(materials);
		}
		 delete(wxmsg);
	}
	
}