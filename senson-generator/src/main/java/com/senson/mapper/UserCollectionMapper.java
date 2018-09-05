package com.senson.mapper;

import com.senson.entity.UserCollection;
import com.senson.entity.UserCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCollectionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(UserCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(UserCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(UserCollection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(UserCollection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<UserCollection> selectByExample(UserCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    UserCollection selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") UserCollection record, @Param("example") UserCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") UserCollection record, @Param("example") UserCollectionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(UserCollection record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_user_collection
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByPrimaryKey(UserCollection record);
}