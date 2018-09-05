package com.senson.entity;

import java.util.ArrayList;
import java.util.List;

public class IntegralConfigExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public IntegralConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
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
     * This method corresponds to the database table a_integral_config
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
     * This method corresponds to the database table a_integral_config
     *
     * @mbggenerated Wed Sep 05 18:05:14 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_integral_config
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
     * This class corresponds to the database table a_integral_config
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

        public Criteria andInviteFriendIsNull() {
            addCriterion("invite_friend is null");
            return (Criteria) this;
        }

        public Criteria andInviteFriendIsNotNull() {
            addCriterion("invite_friend is not null");
            return (Criteria) this;
        }

        public Criteria andInviteFriendEqualTo(Long value) {
            addCriterion("invite_friend =", value, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendNotEqualTo(Long value) {
            addCriterion("invite_friend <>", value, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendGreaterThan(Long value) {
            addCriterion("invite_friend >", value, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendGreaterThanOrEqualTo(Long value) {
            addCriterion("invite_friend >=", value, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendLessThan(Long value) {
            addCriterion("invite_friend <", value, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendLessThanOrEqualTo(Long value) {
            addCriterion("invite_friend <=", value, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendIn(List<Long> values) {
            addCriterion("invite_friend in", values, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendNotIn(List<Long> values) {
            addCriterion("invite_friend not in", values, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendBetween(Long value1, Long value2) {
            addCriterion("invite_friend between", value1, value2, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andInviteFriendNotBetween(Long value1, Long value2) {
            addCriterion("invite_friend not between", value1, value2, "inviteFriend");
            return (Criteria) this;
        }

        public Criteria andVoteIsNull() {
            addCriterion("vote is null");
            return (Criteria) this;
        }

        public Criteria andVoteIsNotNull() {
            addCriterion("vote is not null");
            return (Criteria) this;
        }

        public Criteria andVoteEqualTo(Long value) {
            addCriterion("vote =", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotEqualTo(Long value) {
            addCriterion("vote <>", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThan(Long value) {
            addCriterion("vote >", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThanOrEqualTo(Long value) {
            addCriterion("vote >=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThan(Long value) {
            addCriterion("vote <", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThanOrEqualTo(Long value) {
            addCriterion("vote <=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteIn(List<Long> values) {
            addCriterion("vote in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotIn(List<Long> values) {
            addCriterion("vote not in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteBetween(Long value1, Long value2) {
            addCriterion("vote between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotBetween(Long value1, Long value2) {
            addCriterion("vote not between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andForwardIsNull() {
            addCriterion("forward is null");
            return (Criteria) this;
        }

        public Criteria andForwardIsNotNull() {
            addCriterion("forward is not null");
            return (Criteria) this;
        }

        public Criteria andForwardEqualTo(Long value) {
            addCriterion("forward =", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardNotEqualTo(Long value) {
            addCriterion("forward <>", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardGreaterThan(Long value) {
            addCriterion("forward >", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardGreaterThanOrEqualTo(Long value) {
            addCriterion("forward >=", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardLessThan(Long value) {
            addCriterion("forward <", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardLessThanOrEqualTo(Long value) {
            addCriterion("forward <=", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardIn(List<Long> values) {
            addCriterion("forward in", values, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardNotIn(List<Long> values) {
            addCriterion("forward not in", values, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardBetween(Long value1, Long value2) {
            addCriterion("forward between", value1, value2, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardNotBetween(Long value1, Long value2) {
            addCriterion("forward not between", value1, value2, "forward");
            return (Criteria) this;
        }

        public Criteria andSignInIsNull() {
            addCriterion("sign_in is null");
            return (Criteria) this;
        }

        public Criteria andSignInIsNotNull() {
            addCriterion("sign_in is not null");
            return (Criteria) this;
        }

        public Criteria andSignInEqualTo(Long value) {
            addCriterion("sign_in =", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInNotEqualTo(Long value) {
            addCriterion("sign_in <>", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInGreaterThan(Long value) {
            addCriterion("sign_in >", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInGreaterThanOrEqualTo(Long value) {
            addCriterion("sign_in >=", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInLessThan(Long value) {
            addCriterion("sign_in <", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInLessThanOrEqualTo(Long value) {
            addCriterion("sign_in <=", value, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInIn(List<Long> values) {
            addCriterion("sign_in in", values, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInNotIn(List<Long> values) {
            addCriterion("sign_in not in", values, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInBetween(Long value1, Long value2) {
            addCriterion("sign_in between", value1, value2, "signIn");
            return (Criteria) this;
        }

        public Criteria andSignInNotBetween(Long value1, Long value2) {
            addCriterion("sign_in not between", value1, value2, "signIn");
            return (Criteria) this;
        }

        public Criteria andShareIsNull() {
            addCriterion("share is null");
            return (Criteria) this;
        }

        public Criteria andShareIsNotNull() {
            addCriterion("share is not null");
            return (Criteria) this;
        }

        public Criteria andShareEqualTo(Long value) {
            addCriterion("share =", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareNotEqualTo(Long value) {
            addCriterion("share <>", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareGreaterThan(Long value) {
            addCriterion("share >", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareGreaterThanOrEqualTo(Long value) {
            addCriterion("share >=", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareLessThan(Long value) {
            addCriterion("share <", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareLessThanOrEqualTo(Long value) {
            addCriterion("share <=", value, "share");
            return (Criteria) this;
        }

        public Criteria andShareIn(List<Long> values) {
            addCriterion("share in", values, "share");
            return (Criteria) this;
        }

        public Criteria andShareNotIn(List<Long> values) {
            addCriterion("share not in", values, "share");
            return (Criteria) this;
        }

        public Criteria andShareBetween(Long value1, Long value2) {
            addCriterion("share between", value1, value2, "share");
            return (Criteria) this;
        }

        public Criteria andShareNotBetween(Long value1, Long value2) {
            addCriterion("share not between", value1, value2, "share");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table a_integral_config
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
     * This class corresponds to the database table a_integral_config
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