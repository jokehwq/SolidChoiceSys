/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.bdxt.web.app.entity.request.RegisterUserReq;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户登录信息Entity
 * @author tanjinhua
 * @version 2018-01-29
 */
public class BdxtUser extends DataEntity<BdxtUser> {
	
	private static final long serialVersionUID = 1L;

	private String openId;		// 微信openid
	private String phone;		// 用户名
	private String password;		// 密码
	private String nickname;		// 昵称
	private Integer gender;		// 性别(1-男 2-女)
	private String city;		// 城市
	private String job;		// 工作
	private String headUrl;		// 用户头像
	private String wechat;		// 微信号
	private Integer wechatType;		// 微信号查看权限(1-所有商家可见 2-专业商家可见 3-不公开)
	private Integer status;		// 状态 1-启用 2-停用
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private Date lastLoginTime;//最后登录时间
	private BigDecimal integral;//积分
	private BigDecimal capital;//
    //添加字段
	private String realName; //真实姓名
	private Integer userType;//用户类型(1-普通会员 2-人才库会员)
	private Integer height;//身高

	private Integer heightleft;//身高左区间
	private Integer heightright;//身高右区间
    private Integer weightleft;//体重左区间
	private Integer weightright;//体重右区间
	private Integer bustleft;// 胸围左区间
	private Integer bustright;//胸围右区间
	private Integer waistleft;//腰围左区间
	private Integer waistright;//腰围右区间
	private Integer hiplineleft;//臀围左区间
	private Integer hiplineright;//臀围右区间
    private Integer userSignType;//用户身份类型 1-艺人 2-商家
    private BigDecimal inCapital;//入账金额
	private BigDecimal outCapital;//出账金额
	private String workImageUrl;//个人代表作URL
	private List<String> workImageUrlList;//多个个人代表作
	private Integer weight;//体重
	private Integer bust;//胸围
	private Integer waist;//腰围
	private Integer hipline;//臀围
	private Integer shoeSize;//鞋码
	private String eyeColor;//眼睛颜色
	private String hairColor;//头发颜色
	private String experience;//工作经验
	private String activityId;//活动id
	private Integer quoteStatus;//用户类型(1-普通会员 2-人才库会员)
	public BdxtUser() {
		super();
	}

	public BdxtUser(String id){
		super(id);
	}

	@Length(min=0, max=50, message="openid长度必须介于 0 和 50 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Length(min=0, max=18, message="用户名长度必须介于 0 和 18 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=50, message="密码长度必须介于 0 和 50 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=50, message="昵称长度必须介于 0 和 50 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=50, message="城市长度必须介于 0 和 50 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=50, message="工作长度必须介于 0 和 50 之间")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	@Length(min=0, max=255, message="用户头像长度必须介于 0 和 255 之间")
	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	
	@Length(min=0, max=255, message="微信号长度必须介于 0 和 255 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	@NotNull(message="微信号查看权限(1-所有商家可见 2-专业商家可见 3-不公开)不能为空")
	public Integer getWechatType() {
		return wechatType;
	}

	public void setWechatType(Integer wechatType) {
		this.wechatType = wechatType;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHeightleft() {
		return heightleft;
	}

	public void setHeightleft(Integer heightleft) {
		this.heightleft = heightleft;
	}

	public Integer getHeightright() {
		return heightright;
	}

	public void setHeightright(Integer heightright) {
		this.heightright = heightright;
	}

	public Integer getWeightleft() {
		return weightleft;
	}

	public void setWeightleft(Integer weightleft) {
		this.weightleft = weightleft;
	}

	public Integer getWeightright() {
		return weightright;
	}

	public void setWeightright(Integer weightright) {
		this.weightright = weightright;
	}

	public Integer getBustleft() {
		return bustleft;
	}

	public void setBustleft(Integer bustleft) {
		this.bustleft = bustleft;
	}

	public Integer getBustright() {
		return bustright;
	}

	public void setBustright(Integer bustright) {
		this.bustright = bustright;
	}

	public Integer getWaistleft() {
		return waistleft;
	}

	public void setWaistleft(Integer waistleft) {
		this.waistleft = waistleft;
	}

	public Integer getWaistright() {
		return waistright;
	}

	public void setWaistright(Integer waistright) {
		this.waistright = waistright;
	}

	public Integer getHiplineleft() {
		return hiplineleft;
	}

	public void setHiplineleft(Integer hiplineleft) {
		this.hiplineleft = hiplineleft;
	}

	public Integer getHiplineright() {
		return hiplineright;
	}

	public void setHiplineright(Integer hiplineright) {
		this.hiplineright = hiplineright;
	}

	public Integer getUserSignType() {
		return userSignType;
	}

	public void setUserSignType(Integer userSignType) {
		this.userSignType = userSignType;
	}

	public BigDecimal getInCapital() {
		return inCapital;
	}

	public void setInCapital(BigDecimal inCapital) {
		this.inCapital = inCapital;
	}

	public BigDecimal getOutCapital() {
		return outCapital;
	}

	public void setOutCapital(BigDecimal outCapital) {
		this.outCapital = outCapital;
	}


	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getBust() {
		return bust;
	}

	public void setBust(Integer bust) {
		this.bust = bust;
	}

	public Integer getWaist() {
		return waist;
	}

	public void setWaist(Integer waist) {
		this.waist = waist;
	}

	public Integer getHipline() {
		return hipline;
	}

	public void setHipline(Integer hipline) {
		this.hipline = hipline;
	}

	public Integer getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(Integer shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {

		this.activityId = activityId;
	}

	public String getWorkImageUrl() {
		return workImageUrl;
	}

	public void setWorkImageUrl(String workImageUrl) {
		this.workImageUrl = workImageUrl;
	}

	public List<String> getWorkImageUrlList() {
		return workImageUrlList;
	}

	public void setWorkImageUrlList(List<String> workImageUrlList) {
		this.workImageUrlList = workImageUrlList;
	}

	public Integer getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(Integer quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
}