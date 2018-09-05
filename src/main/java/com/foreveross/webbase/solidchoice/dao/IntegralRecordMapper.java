package com.foreveross.webbase.solidchoice.dao;

import com.senson.entity.IntegralRecord;
import com.senson.entity.IntegralRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(IntegralRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(IntegralRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(IntegralRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(IntegralRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<IntegralRecord> selectByExample(IntegralRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    IntegralRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") IntegralRecord record, @Param("example") IntegralRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") IntegralRecord record, @Param("example") IntegralRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(IntegralRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKey(IntegralRecord record);
}