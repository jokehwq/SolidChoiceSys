package com.senson.mapper;

import com.senson.entity.UserSignRecord;
import com.senson.entity.UserSignRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSignRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(UserSignRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(UserSignRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(UserSignRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(UserSignRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<UserSignRecord> selectByExample(UserSignRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    UserSignRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") UserSignRecord record, @Param("example") UserSignRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") UserSignRecord record, @Param("example") UserSignRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(UserSignRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_sign_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKey(UserSignRecord record);
}