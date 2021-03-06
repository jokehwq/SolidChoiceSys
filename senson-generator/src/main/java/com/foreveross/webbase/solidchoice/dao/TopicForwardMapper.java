package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.solidchoice.entity.TopicForward;
import com.foreveross.webbase.solidchoice.entity.TopicForwardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicForwardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int countByExample(TopicForwardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByExample(TopicForwardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insert(TopicForward record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insertSelective(TopicForward record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    List<TopicForward> selectByExample(TopicForwardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    TopicForward selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExampleSelective(@Param("record") TopicForward record, @Param("example") TopicForwardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExample(@Param("record") TopicForward record, @Param("example") TopicForwardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKeySelective(TopicForward record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_forward
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKey(TopicForward record);
}