package com.foreveross.webbase.solidchoice.entity;

import java.util.Date;

public class SysLog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.title
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.user_agent
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String userAgent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.request_uri
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String requestUri;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.method
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String method;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.remote_addr
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String remoteAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.user_role
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String userRole;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.create_name
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String createName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_sys_log.create_date
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Date createDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.id
     *
     * @return the value of a_sys_log.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.id
     *
     * @param id the value for a_sys_log.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.type
     *
     * @return the value of a_sys_log.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.type
     *
     * @param type the value for a_sys_log.type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.title
     *
     * @return the value of a_sys_log.title
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.title
     *
     * @param title the value for a_sys_log.title
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.user_agent
     *
     * @return the value of a_sys_log.user_agent
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.user_agent
     *
     * @param userAgent the value for a_sys_log.user_agent
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.request_uri
     *
     * @return the value of a_sys_log.request_uri
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.request_uri
     *
     * @param requestUri the value for a_sys_log.request_uri
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.method
     *
     * @return the value of a_sys_log.method
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.method
     *
     * @param method the value for a_sys_log.method
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.remote_addr
     *
     * @return the value of a_sys_log.remote_addr
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.remote_addr
     *
     * @param remoteAddr the value for a_sys_log.remote_addr
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.user_role
     *
     * @return the value of a_sys_log.user_role
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.user_role
     *
     * @param userRole the value for a_sys_log.user_role
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.create_name
     *
     * @return the value of a_sys_log.create_name
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.create_name
     *
     * @param createName the value for a_sys_log.create_name
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_sys_log.create_date
     *
     * @return the value of a_sys_log.create_date
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_sys_log.create_date
     *
     * @param createDate the value for a_sys_log.create_date
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}