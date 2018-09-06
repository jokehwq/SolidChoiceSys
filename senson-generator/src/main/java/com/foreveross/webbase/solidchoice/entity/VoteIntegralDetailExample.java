package com.foreveross.webbase.solidchoice.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteIntegralDetailExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public VoteIntegralDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
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
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
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

        public Criteria andTopicIdIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicIdEqualTo(Long value) {
            addCriterion("topic_id =", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotEqualTo(Long value) {
            addCriterion("topic_id <>", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThan(Long value) {
            addCriterion("topic_id >", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThanOrEqualTo(Long value) {
            addCriterion("topic_id >=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThan(Long value) {
            addCriterion("topic_id <", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThanOrEqualTo(Long value) {
            addCriterion("topic_id <=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIn(List<Long> values) {
            addCriterion("topic_id in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotIn(List<Long> values) {
            addCriterion("topic_id not in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdBetween(Long value1, Long value2) {
            addCriterion("topic_id between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotBetween(Long value1, Long value2) {
            addCriterion("topic_id not between", value1, value2, "topicId");
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

        public Criteria andVoteTypeIsNull() {
            addCriterion("vote_type is null");
            return (Criteria) this;
        }

        public Criteria andVoteTypeIsNotNull() {
            addCriterion("vote_type is not null");
            return (Criteria) this;
        }

        public Criteria andVoteTypeEqualTo(Integer value) {
            addCriterion("vote_type =", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotEqualTo(Integer value) {
            addCriterion("vote_type <>", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeGreaterThan(Integer value) {
            addCriterion("vote_type >", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote_type >=", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeLessThan(Integer value) {
            addCriterion("vote_type <", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeLessThanOrEqualTo(Integer value) {
            addCriterion("vote_type <=", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeIn(List<Integer> values) {
            addCriterion("vote_type in", values, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotIn(List<Integer> values) {
            addCriterion("vote_type not in", values, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeBetween(Integer value1, Integer value2) {
            addCriterion("vote_type between", value1, value2, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("vote_type not between", value1, value2, "voteType");
            return (Criteria) this;
        }

        public Criteria andIsLikeIsNull() {
            addCriterion("is_like is null");
            return (Criteria) this;
        }

        public Criteria andIsLikeIsNotNull() {
            addCriterion("is_like is not null");
            return (Criteria) this;
        }

        public Criteria andIsLikeEqualTo(Integer value) {
            addCriterion("is_like =", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotEqualTo(Integer value) {
            addCriterion("is_like <>", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeGreaterThan(Integer value) {
            addCriterion("is_like >", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_like >=", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeLessThan(Integer value) {
            addCriterion("is_like <", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeLessThanOrEqualTo(Integer value) {
            addCriterion("is_like <=", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeIn(List<Integer> values) {
            addCriterion("is_like in", values, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotIn(List<Integer> values) {
            addCriterion("is_like not in", values, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeBetween(Integer value1, Integer value2) {
            addCriterion("is_like between", value1, value2, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_like not between", value1, value2, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsAttentionIsNull() {
            addCriterion("is_attention is null");
            return (Criteria) this;
        }

        public Criteria andIsAttentionIsNotNull() {
            addCriterion("is_attention is not null");
            return (Criteria) this;
        }

        public Criteria andIsAttentionEqualTo(Integer value) {
            addCriterion("is_attention =", value, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionNotEqualTo(Integer value) {
            addCriterion("is_attention <>", value, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionGreaterThan(Integer value) {
            addCriterion("is_attention >", value, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_attention >=", value, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionLessThan(Integer value) {
            addCriterion("is_attention <", value, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionLessThanOrEqualTo(Integer value) {
            addCriterion("is_attention <=", value, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionIn(List<Integer> values) {
            addCriterion("is_attention in", values, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionNotIn(List<Integer> values) {
            addCriterion("is_attention not in", values, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionBetween(Integer value1, Integer value2) {
            addCriterion("is_attention between", value1, value2, "isAttention");
            return (Criteria) this;
        }

        public Criteria andIsAttentionNotBetween(Integer value1, Integer value2) {
            addCriterion("is_attention not between", value1, value2, "isAttention");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeIsNull() {
            addCriterion("intergral_type is null");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeIsNotNull() {
            addCriterion("intergral_type is not null");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeEqualTo(Integer value) {
            addCriterion("intergral_type =", value, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeNotEqualTo(Integer value) {
            addCriterion("intergral_type <>", value, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeGreaterThan(Integer value) {
            addCriterion("intergral_type >", value, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("intergral_type >=", value, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeLessThan(Integer value) {
            addCriterion("intergral_type <", value, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeLessThanOrEqualTo(Integer value) {
            addCriterion("intergral_type <=", value, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeIn(List<Integer> values) {
            addCriterion("intergral_type in", values, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeNotIn(List<Integer> values) {
            addCriterion("intergral_type not in", values, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeBetween(Integer value1, Integer value2) {
            addCriterion("intergral_type between", value1, value2, "intergralType");
            return (Criteria) this;
        }

        public Criteria andIntergralTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("intergral_type not between", value1, value2, "intergralType");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated do_not_delete_during_merge Thu Sep 06 10:11:00 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table a_vote_integral_detail
     *
     * @mbggenerated Thu Sep 06 10:11:00 CST 2018
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