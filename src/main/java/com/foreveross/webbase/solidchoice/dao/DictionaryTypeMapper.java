package com.foreveross.webbase.solidchoice.dao;

import com.senson.entity.DictionaryType;
import com.senson.entity.DictionaryTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DictionaryTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_dictionary_type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int countByExample(DictionaryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_dictionary_type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int deleteByExample(DictionaryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_dictionary_type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insert(DictionaryType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_dictionary_type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int insertSelective(DictionaryType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_dictionary_type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    List<DictionaryType> selectByExample(DictionaryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_dictionary_type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") DictionaryType record, @Param("example") DictionaryTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_dictionary_type
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    int updateByExample(@Param("record") DictionaryType record, @Param("example") DictionaryTypeExample example);
}