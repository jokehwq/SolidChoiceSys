package com.foreveross.webbase.solidchoice.entity;

import com.foreveross.webbase.common.persistence.DataEntity;

import java.util.Date;

public class IntegralRecord  extends DataEntity<IntegralRecord> {

    private String id;

    private String userId;

    private String integralId;

    private String operationType;

    private String typeId;

    private String note;

    private Integer capital;

    private Integer integralType;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntegralId() {
        return integralId;
    }

    public void setIntegralId(String integralId) {
        this.integralId = integralId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_integral_record.note
     *
     * @param note the value for a_integral_record.note
     *
     * @mbggenerated Fri Sep 07 16:35:43 CST 2018
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_integral_record.capital
     *
     * @return the value of a_integral_record.capital
     *
     * @mbggenerated Fri Sep 07 16:35:43 CST 2018
     */
    public Integer getCapital() {
        return capital;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_integral_record.capital
     *
     * @param capital the value for a_integral_record.capital
     *
     * @mbggenerated Fri Sep 07 16:35:43 CST 2018
     */
    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_integral_record.integral_type
     *
     * @return the value of a_integral_record.integral_type
     *
     * @mbggenerated Fri Sep 07 16:35:43 CST 2018
     */
    public Integer getIntegralType() {
        return integralType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_integral_record.integral_type
     *
     * @param integralType the value for a_integral_record.integral_type
     *
     * @mbggenerated Fri Sep 07 16:35:43 CST 2018
     */
    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column a_integral_record.create_time
     *
     * @return the value of a_integral_record.create_time
     *
     * @mbggenerated Fri Sep 07 16:35:43 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column a_integral_record.create_time
     *
     * @param createTime the value for a_integral_record.create_time
     *
     * @mbggenerated Fri Sep 07 16:35:43 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}