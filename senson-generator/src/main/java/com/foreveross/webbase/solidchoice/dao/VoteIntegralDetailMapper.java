package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.solidchoice.entity.VoteIntegralDetail;
import com.foreveross.webbase.solidchoice.entity.VoteIntegralDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteIntegralDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int countByExample(VoteIntegralDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByExample(VoteIntegralDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insert(VoteIntegralDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insertSelective(VoteIntegralDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    List<VoteIntegralDetail> selectByExample(VoteIntegralDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    VoteIntegralDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExampleSelective(@Param("record") VoteIntegralDetail record, @Param("example") VoteIntegralDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExample(@Param("record") VoteIntegralDetail record, @Param("example") VoteIntegralDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKeySelective(VoteIntegralDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKey(VoteIntegralDetail record);
}