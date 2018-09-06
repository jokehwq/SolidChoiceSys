package com.foreveross.webbase.solidchoice.dao;

import com.foreveross.webbase.solidchoice.entity.SysUser;
import com.foreveross.webbase.solidchoice.entity.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int countByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int insertSelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    List<SysUser> selectByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_sys_user
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    int updateByPrimaryKey(SysUser record);
}