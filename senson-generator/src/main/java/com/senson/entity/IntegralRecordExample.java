package com.senson.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegralRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public IntegralRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOperationTypeIsNull() {
            addCriterion("operation_type is null");
            return (Criteria) this;
        }

        public Criteria andOperationTypeIsNotNull() {
            addCriterion("operation_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperationTypeEqualTo(Integer value) {
            addCriterion("operation_type =", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeNotEqualTo(Integer value) {
            addCriterion("operation_type <>", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeGreaterThan(Integer value) {
            addCriterion("operation_type >", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operation_type >=", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeLessThan(Integer value) {
            addCriterion("operation_type <", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("operation_type <=", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeIn(List<Integer> values) {
            addCriterion("operation_type in", values, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeNotIn(List<Integer> values) {
            addCriterion("operation_type not in", values, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeBetween(Integer value1, Integer value2) {
            addCriterion("operation_type between", value1, value2, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operation_type not between", value1, value2, "operationType");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Long value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Long value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Long value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Long value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Long> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Long> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Long value1, Long value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(Integer value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(Integer value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(Integer value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(Integer value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(Integer value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(Integer value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<Integer> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<Integer> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(Integer value1, Integer value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(Integer value1, Integer value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNull() {
            addCriterion("capital is null");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNotNull() {
            addCriterion("capital is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalEqualTo(Integer value) {
            addCriterion("capital =", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotEqualTo(Integer value) {
            addCriterion("capital <>", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThan(Integer value) {
            addCriterion("capital >", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThanOrEqualTo(Integer value) {
            addCriterion("capital >=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThan(Integer value) {
            addCriterion("capital <", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThanOrEqualTo(Integer value) {
            addCriterion("capital <=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalIn(List<Integer> values) {
            addCriterion("capital in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotIn(List<Integer> values) {
            addCriterion("capital not in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalBetween(Integer value1, Integer value2) {
            addCriterion("capital between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotBetween(Integer value1, Integer value2) {
            addCriterion("capital not between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeIsNull() {
            addCriterion("integral_type is null");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeIsNotNull() {
            addCriterion("integral_type is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeEqualTo(Integer value) {
            addCriterion("integral_type =", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeNotEqualTo(Integer value) {
            addCriterion("integral_type <>", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeGreaterThan(Integer value) {
            addCriterion("integral_type >", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_type >=", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeLessThan(Integer value) {
            addCriterion("integral_type <", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeLessThanOrEqualTo(Integer value) {
            addCriterion("integral_type <=", value, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeIn(List<Integer> values) {
            addCriterion("integral_type in", values, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeNotIn(List<Integer> values) {
            addCriterion("integral_type not in", values, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeBetween(Integer value1, Integer value2) {
            addCriterion("integral_type between", value1, value2, "integralType");
            return (Criteria) this;
        }

        public Criteria andIntegralTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("integral_type not between", value1, value2, "integralType");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table a_integral_record
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 05 18:05:14 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table a_integral_record
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}