package com.senson.mapper;

import com.senson.entity.Integral;
import com.senson.entity.IntegralExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(IntegralExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(IntegralExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(Integral record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(Integral record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<Integral> selectByExample(IntegralExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    Integral selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") Integral record, @Param("example") IntegralExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") Integral record, @Param("example") IntegralExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(Integral record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKey(Integral record);
}