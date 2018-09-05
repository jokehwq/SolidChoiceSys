package com.senson.mapper;

import com.senson.entity.TopicTop;
import com.senson.entity.TopicTopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicTopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(TopicTopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(TopicTopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(TopicTop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(TopicTop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<TopicTop> selectByExample(TopicTopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    TopicTop selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") TopicTop record, @Param("example") TopicTopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") TopicTop record, @Param("example") TopicTopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(TopicTop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_topic_top
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKey(TopicTop record);
}