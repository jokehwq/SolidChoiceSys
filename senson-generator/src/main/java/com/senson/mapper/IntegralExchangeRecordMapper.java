package com.senson.mapper;

import com.senson.entity.IntegralExchangeRecord;
import com.senson.entity.IntegralExchangeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralExchangeRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(IntegralExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(IntegralExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(IntegralExchangeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(IntegralExchangeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<IntegralExchangeRecord> selectByExample(IntegralExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    IntegralExchangeRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") IntegralExchangeRecord record, @Param("example") IntegralExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") IntegralExchangeRecord record, @Param("example") IntegralExchangeRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(IntegralExchangeRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_exchange_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKey(IntegralExchangeRecord record);
}