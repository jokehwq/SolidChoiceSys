package com.senson.entity;

import java.util.Date;

public class Banner {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.text
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String text;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.img_url
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String imgUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.jump_url
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String jumpUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.position
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String position;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.is_del
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Integer isDel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.update_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.update_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Long updateUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_banner.create_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Long createUid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.id
     *
     * @return the value of a_banner.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.id
     *
     * @param id the value for a_banner.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.text
     *
     * @return the value of a_banner.text
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getText() {
        return text;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.text
     *
     * @param text the value for a_banner.text
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.img_url
     *
     * @return the value of a_banner.img_url
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.img_url
     *
     * @param imgUrl the value for a_banner.img_url
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.jump_url
     *
     * @return the value of a_banner.jump_url
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getJumpUrl() {
        return jumpUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.jump_url
     *
     * @param jumpUrl the value for a_banner.jump_url
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl == null ? null : jumpUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.type
     *
     * @return the value of a_banner.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.type
     *
     * @param type the value for a_banner.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.position
     *
     * @return the value of a_banner.position
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.position
     *
     * @param position the value for a_banner.position
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.is_del
     *
     * @return the value of a_banner.is_del
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.is_del
     *
     * @param isDel the value for a_banner.is_del
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.update_time
     *
     * @return the value of a_banner.update_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.update_time
     *
     * @param updateTime the value for a_banner.update_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.update_uid
     *
     * @return the value of a_banner.update_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Long getUpdateUid() {
        return updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.update_uid
     *
     * @param updateUid the value for a_banner.update_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setUpdateUid(Long updateUid) {
        this.updateUid = updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.create_time
     *
     * @return the value of a_banner.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.create_time
     *
     * @param createTime the value for a_banner.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_banner.create_uid
     *
     * @return the value of a_banner.create_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Long getCreateUid() {
        return createUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_banner.create_uid
     *
     * @param createUid the value for a_banner.create_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

}