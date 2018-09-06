package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.solidchoice.entity.CommentReply;
import com.foreveross.webbase.solidchoice.entity.CommentReplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentReplyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int countByExample(CommentReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByExample(CommentReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insert(CommentReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insertSelective(CommentReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    List<CommentReply> selectByExample(CommentReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    CommentReply selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExampleSelective(@Param("record") CommentReply record, @Param("example") CommentReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExample(@Param("record") CommentReply record, @Param("example") CommentReplyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKeySelective(CommentReply record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_comment_reply
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKey(CommentReply record);
}