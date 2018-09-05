package com.foreveross.webbase.solidchoice.entity;

import java.util.Date;

public class TopicForward {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_topic_forward.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_topic_forward.user_id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_topic_forward.topic_id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Integer topicId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_topic_forward.content
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_topic_forward.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_topic_forward.id
     *
     * @return the value of a_topic_forward.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_topic_forward.id
     *
     * @param id the value for a_topic_forward.id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_topic_forward.user_id
     *
     * @return the value of a_topic_forward.user_id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_topic_forward.user_id
     *
     * @param userId the value for a_topic_forward.user_id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_topic_forward.topic_id
     *
     * @return the value of a_topic_forward.topic_id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_topic_forward.topic_id
     *
     * @param topicId the value for a_topic_forward.topic_id
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_topic_forward.content
     *
     * @return the value of a_topic_forward.content
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_topic_forward.content
     *
     * @param content the value for a_topic_forward.content
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_topic_forward.create_time
     *
     * @return the value of a_topic_forward.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_topic_forward.create_time
     *
     * @param createTime the value for a_topic_forward.create_time
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}