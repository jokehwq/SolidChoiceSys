package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.solidchoice.entity.IntegralLevel;
import com.foreveross.webbase.solidchoice.entity.IntegralLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralLevelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int countByExample(IntegralLevelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByExample(IntegralLevelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insert(IntegralLevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insertSelective(IntegralLevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    List<IntegralLevel> selectByExample(IntegralLevelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    IntegralLevel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExampleSelective(@Param("record") IntegralLevel record, @Param("example") IntegralLevelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExample(@Param("record") IntegralLevel record, @Param("example") IntegralLevelExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKeySelective(IntegralLevel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_level
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKey(IntegralLevel record);
}