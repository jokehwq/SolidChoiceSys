package com.foreveross.webbase.solidchoice.entity;

import java.util.Date;

public class Rule {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_rule.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_rule.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_rule.update_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Long updateUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_rule.update_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_rule.create_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Long createUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_rule.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_rule.role_content
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String roleContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_rule.id
     *
     * @return the value of a_rule.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_rule.id
     *
     * @param id the value for a_rule.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_rule.type
     *
     * @return the value of a_rule.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_rule.type
     *
     * @param type the value for a_rule.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_rule.update_uid
     *
     * @return the value of a_rule.update_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Long getUpdateUid() {
        return updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_rule.update_uid
     *
     * @param updateUid the value for a_rule.update_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setUpdateUid(Long updateUid) {
        this.updateUid = updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_rule.update_time
     *
     * @return the value of a_rule.update_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_rule.update_time
     *
     * @param updateTime the value for a_rule.update_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_rule.create_uid
     *
     * @return the value of a_rule.create_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Long getCreateUid() {
        return createUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_rule.create_uid
     *
     * @param createUid the value for a_rule.create_uid
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_rule.create_time
     *
     * @return the value of a_rule.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_rule.create_time
     *
     * @param createTime the value for a_rule.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_rule.role_content
     *
     * @return the value of a_rule.role_content
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getRoleContent() {
        return roleContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_rule.role_content
     *
     * @param roleContent the value for a_rule.role_content
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setRoleContent(String roleContent) {
        this.roleContent = roleContent == null ? null : roleContent.trim();
    }
}