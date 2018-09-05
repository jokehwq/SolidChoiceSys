package com.foreveross.webbase.solidchoice.dao;

import com.senson.entity.TopicDetails;
import com.senson.entity.TopicDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicDetailsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(TopicDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(TopicDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(TopicDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(TopicDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<TopicDetails> selectByExample(TopicDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    TopicDetails selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") TopicDetails record, @Param("example") TopicDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") TopicDetails record, @Param("example") TopicDetailsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(TopicDetails record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_details
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKey(TopicDetails record);
}