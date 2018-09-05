
package com.foreveross.webbase.bdxt.entity;

import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 附件信息
 * create by wangkun
 * Date 2018/1/29
 */
public class BdxtAttachment  extends DataEntity<BdxtAttachment> {

	private static final long serialVersionUID = 1L;
	private String id;
	private String bdxtUserId;		// 用户账号id
	private String bdxtTag;		// 标签类型ids(bxdt_tag表关联id)
	private String attachName;		// 附件名称
	private Integer attachType;		// 附件类型 1-图片 2-视频
	private String attachUrl;		// 附件url
	private Integer attachStatus;		// 状态 1-启用 2-停用
	//add req filed
	private String[] attachUrls; //附件Url
	private String[] tagIds;//作品标签ids
	//add resp filed
	private String tagNames;//标签名称集合
	private String[] tagName;//标签名称数组
	private String tagId;//标签id集合

	private List<String> attachUrlImgList;//作品url集合
	private List<String> attachUrlVideoList;//视频url集合
	private List<BdxtTag> tagImgList; //作品标签名称
	private List<BdxtTag> tagVideoList;//视频标签名称

	private Integer gender;//性别
	private String headUrl;//头像
	private String nickName;//昵称
	//private List<String> tagNamesList;//标签名称集合
	//private List<String> tagIdsList;//标签id集合
	//查询附件列表构造方法
	public BdxtAttachment(String bdxtUserId,Integer attachType){
		this.bdxtUserId=bdxtUserId;
		this.attachType=attachType;
	}
	public BdxtAttachment(){}

	@Length(min=0, max=50, message="用户账号id长度必须介于 0 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}

	@Length(min=0, max=50, message="标签类型ids(bxdt_tag表关联id)长度必须介于 0 和 50 之间")
	public String getBdxtTag() {
		return bdxtTag;
	}

	public void setBdxtTag(String bdxtTag) {
		this.bdxtTag = bdxtTag;
	}

	@Length(min=0, max=255, message="附件名称长度必须介于 0 和 255 之间")
	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public Integer getAttachType() {
		return attachType;
	}

	public void setAttachType(Integer attachType) {
		this.attachType = attachType;
	}

	@Length(min=0, max=2048, message="附件url长度必须介于 0 和 2048 之间")
	public String getAttachUrl() {
		return attachUrl;
	}

	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}

	public Integer getAttachStatus() {
		return attachStatus;
	}

	public void setAttachStatus(Integer attachStatus) {
		this.attachStatus = attachStatus;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

	public String[] getTagName() {
		return tagName;
	}

	public void setTagName(String[] tagName) {
		this.tagName = tagName;
	}

	public String[] getAttachUrls() {
		return attachUrls;
	}

	public void setAttachUrls(String[] attachUrls) {
		this.attachUrls = attachUrls;
	}

	public String[] getTagIds() {
		return tagIds;
	}

	public void setTagIds(String[] tagIds) {
		this.tagIds = tagIds;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}


	public List<String> getAttachUrlImgList() {
		return attachUrlImgList;
	}

	public void setAttachUrlImgList(List<String> attachUrlImgList) {
		this.attachUrlImgList = attachUrlImgList;
	}

	public List<String> getAttachUrlVideoList() {
		return attachUrlVideoList;
	}

	public void setAttachUrlVideoList(List<String> attachUrlVideoList) {
		this.attachUrlVideoList = attachUrlVideoList;
	}

	public List<BdxtTag> getTagImgList() {
		return tagImgList;
	}

	public void setTagImgList(List<BdxtTag> tagImgList) {
		this.tagImgList = tagImgList;
	}

	public List<BdxtTag> getTagVideoList() {
		return tagVideoList;
	}

	public void setTagVideoList(List<BdxtTag> tagVideoList) {
		this.tagVideoList = tagVideoList;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}