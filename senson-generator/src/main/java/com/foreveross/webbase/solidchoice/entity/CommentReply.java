package com.foreveross.webbase.solidchoice.entity;

import java.util.Date;

public class CommentReply {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_comment_reply.id
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_comment_reply.comment_id
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    private Long commentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_comment_reply.reply_uid
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    private Long replyUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_comment_reply.reply_content
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    private String replyContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_comment_reply.img_url
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    private String imgUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column a_comment_reply.create_time
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_comment_reply.id
     *
     * @return the value of a_comment_reply.id
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_comment_reply.id
     *
     * @param id the value for a_comment_reply.id
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_comment_reply.comment_id
     *
     * @return the value of a_comment_reply.comment_id
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_comment_reply.comment_id
     *
     * @param commentId the value for a_comment_reply.comment_id
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_comment_reply.reply_uid
     *
     * @return the value of a_comment_reply.reply_uid
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public Long getReplyUid() {
        return replyUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_comment_reply.reply_uid
     *
     * @param replyUid the value for a_comment_reply.reply_uid
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setReplyUid(Long replyUid) {
        this.replyUid = replyUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_comment_reply.reply_content
     *
     * @return the value of a_comment_reply.reply_content
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_comment_reply.reply_content
     *
     * @param replyContent the value for a_comment_reply.reply_content
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_comment_reply.img_url
     *
     * @return the value of a_comment_reply.img_url
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_comment_reply.img_url
     *
     * @param imgUrl the value for a_comment_reply.img_url
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_comment_reply.create_time
     *
     * @return the value of a_comment_reply.create_time
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_comment_reply.create_time
     *
     * @param createTime the value for a_comment_reply.create_time
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}