package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TLtnCustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TLtnCustomerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenIsNull() {
            addCriterion("chu_fu_zhen is null");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenIsNotNull() {
            addCriterion("chu_fu_zhen is not null");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenEqualTo(Integer value) {
            addCriterion("chu_fu_zhen =", value, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenNotEqualTo(Integer value) {
            addCriterion("chu_fu_zhen <>", value, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenGreaterThan(Integer value) {
            addCriterion("chu_fu_zhen >", value, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenGreaterThanOrEqualTo(Integer value) {
            addCriterion("chu_fu_zhen >=", value, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenLessThan(Integer value) {
            addCriterion("chu_fu_zhen <", value, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenLessThanOrEqualTo(Integer value) {
            addCriterion("chu_fu_zhen <=", value, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenIn(List<Integer> values) {
            addCriterion("chu_fu_zhen in", values, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenNotIn(List<Integer> values) {
            addCriterion("chu_fu_zhen not in", values, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenBetween(Integer value1, Integer value2) {
            addCriterion("chu_fu_zhen between", value1, value2, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andChuFuZhenNotBetween(Integer value1, Integer value2) {
            addCriterion("chu_fu_zhen not between", value1, value2, "chuFuZhen");
            return (Criteria) this;
        }

        public Criteria andCustomernameIsNull() {
            addCriterion("customername is null");
            return (Criteria) this;
        }

        public Criteria andCustomernameIsNotNull() {
            addCriterion("customername is not null");
            return (Criteria) this;
        }

        public Criteria andCustomernameEqualTo(String value) {
            addCriterion("customername =", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameNotEqualTo(String value) {
            addCriterion("customername <>", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameGreaterThan(String value) {
            addCriterion("customername >", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameGreaterThanOrEqualTo(String value) {
            addCriterion("customername >=", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameLessThan(String value) {
            addCriterion("customername <", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameLessThanOrEqualTo(String value) {
            addCriterion("customername <=", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameLike(String value) {
            addCriterion("customername like", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameNotLike(String value) {
            addCriterion("customername not like", value, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameIn(List<String> values) {
            addCriterion("customername in", values, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameNotIn(List<String> values) {
            addCriterion("customername not in", values, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameBetween(String value1, String value2) {
            addCriterion("customername between", value1, value2, "customername");
            return (Criteria) this;
        }

        public Criteria andCustomernameNotBetween(String value1, String value2) {
            addCriterion("customername not between", value1, value2, "customername");
            return (Criteria) this;
        }

        public Criteria andPhonenumbIsNull() {
            addCriterion("phonenumb is null");
            return (Criteria) this;
        }

        public Criteria andPhonenumbIsNotNull() {
            addCriterion("phonenumb is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenumbEqualTo(String value) {
            addCriterion("phonenumb =", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbNotEqualTo(String value) {
            addCriterion("phonenumb <>", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbGreaterThan(String value) {
            addCriterion("phonenumb >", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbGreaterThanOrEqualTo(String value) {
            addCriterion("phonenumb >=", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbLessThan(String value) {
            addCriterion("phonenumb <", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbLessThanOrEqualTo(String value) {
            addCriterion("phonenumb <=", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbLike(String value) {
            addCriterion("phonenumb like", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbNotLike(String value) {
            addCriterion("phonenumb not like", value, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbIn(List<String> values) {
            addCriterion("phonenumb in", values, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbNotIn(List<String> values) {
            addCriterion("phonenumb not in", values, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbBetween(String value1, String value2) {
            addCriterion("phonenumb between", value1, value2, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andPhonenumbNotBetween(String value1, String value2) {
            addCriterion("phonenumb not between", value1, value2, "phonenumb");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNull() {
            addCriterion("card_id is null");
            return (Criteria) this;
        }

        public Criteria andCardIdIsNotNull() {
            addCriterion("card_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardIdEqualTo(String value) {
            addCriterion("card_id =", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotEqualTo(String value) {
            addCriterion("card_id <>", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThan(String value) {
            addCriterion("card_id >", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("card_id >=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThan(String value) {
            addCriterion("card_id <", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLessThanOrEqualTo(String value) {
            addCriterion("card_id <=", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdLike(String value) {
            addCriterion("card_id like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotLike(String value) {
            addCriterion("card_id not like", value, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdIn(List<String> values) {
            addCriterion("card_id in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotIn(List<String> values) {
            addCriterion("card_id not in", values, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdBetween(String value1, String value2) {
            addCriterion("card_id between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCardIdNotBetween(String value1, String value2) {
            addCriterion("card_id not between", value1, value2, "cardId");
            return (Criteria) this;
        }

        public Criteria andCounsolerIsNull() {
            addCriterion("counsoler is null");
            return (Criteria) this;
        }

        public Criteria andCounsolerIsNotNull() {
            addCriterion("counsoler is not null");
            return (Criteria) this;
        }

        public Criteria andCounsolerEqualTo(Integer value) {
            addCriterion("counsoler =", value, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerNotEqualTo(Integer value) {
            addCriterion("counsoler <>", value, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerGreaterThan(Integer value) {
            addCriterion("counsoler >", value, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerGreaterThanOrEqualTo(Integer value) {
            addCriterion("counsoler >=", value, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerLessThan(Integer value) {
            addCriterion("counsoler <", value, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerLessThanOrEqualTo(Integer value) {
            addCriterion("counsoler <=", value, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerIn(List<Integer> values) {
            addCriterion("counsoler in", values, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerNotIn(List<Integer> values) {
            addCriterion("counsoler not in", values, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerBetween(Integer value1, Integer value2) {
            addCriterion("counsoler between", value1, value2, "counsoler");
            return (Criteria) this;
        }

        public Criteria andCounsolerNotBetween(Integer value1, Integer value2) {
            addCriterion("counsoler not between", value1, value2, "counsoler");
            return (Criteria) this;
        }

        public Criteria andTherapeutistIsNull() {
            addCriterion("therapeutist is null");
            return (Criteria) this;
        }

        public Criteria andTherapeutistIsNotNull() {
            addCriterion("therapeutist is not null");
            return (Criteria) this;
        }

        public Criteria andTherapeutistEqualTo(Integer value) {
            addCriterion("therapeutist =", value, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistNotEqualTo(Integer value) {
            addCriterion("therapeutist <>", value, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistGreaterThan(Integer value) {
            addCriterion("therapeutist >", value, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistGreaterThanOrEqualTo(Integer value) {
            addCriterion("therapeutist >=", value, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistLessThan(Integer value) {
            addCriterion("therapeutist <", value, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistLessThanOrEqualTo(Integer value) {
            addCriterion("therapeutist <=", value, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistIn(List<Integer> values) {
            addCriterion("therapeutist in", values, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistNotIn(List<Integer> values) {
            addCriterion("therapeutist not in", values, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistBetween(Integer value1, Integer value2) {
            addCriterion("therapeutist between", value1, value2, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andTherapeutistNotBetween(Integer value1, Integer value2) {
            addCriterion("therapeutist not between", value1, value2, "therapeutist");
            return (Criteria) this;
        }

        public Criteria andRemindtimeIsNull() {
            addCriterion("remindTime is null");
            return (Criteria) this;
        }

        public Criteria andRemindtimeIsNotNull() {
            addCriterion("remindTime is not null");
            return (Criteria) this;
        }

        public Criteria andRemindtimeEqualTo(Date value) {
            addCriterionForJDBCDate("remindTime =", value, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("remindTime <>", value, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("remindTime >", value, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("remindTime >=", value, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeLessThan(Date value) {
            addCriterionForJDBCDate("remindTime <", value, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("remindTime <=", value, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeIn(List<Date> values) {
            addCriterionForJDBCDate("remindTime in", values, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("remindTime not in", values, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("remindTime between", value1, value2, "remindtime");
            return (Criteria) this;
        }

        public Criteria andRemindtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("remindTime not between", value1, value2, "remindtime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdIsNull() {
            addCriterion("subscribe_id is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdIsNotNull() {
            addCriterion("subscribe_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdEqualTo(Integer value) {
            addCriterion("subscribe_id =", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdNotEqualTo(Integer value) {
            addCriterion("subscribe_id <>", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdGreaterThan(Integer value) {
            addCriterion("subscribe_id >", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("subscribe_id >=", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdLessThan(Integer value) {
            addCriterion("subscribe_id <", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdLessThanOrEqualTo(Integer value) {
            addCriterion("subscribe_id <=", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdIn(List<Integer> values) {
            addCriterion("subscribe_id in", values, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdNotIn(List<Integer> values) {
            addCriterion("subscribe_id not in", values, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdBetween(Integer value1, Integer value2) {
            addCriterion("subscribe_id between", value1, value2, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("subscribe_id not between", value1, value2, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNull() {
            addCriterion("createUser is null");
            return (Criteria) this;
        }

        public Criteria andCreateuserIsNotNull() {
            addCriterion("createUser is not null");
            return (Criteria) this;
        }

        public Criteria andCreateuserEqualTo(String value) {
            addCriterion("createUser =", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotEqualTo(String value) {
            addCriterion("createUser <>", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThan(String value) {
            addCriterion("createUser >", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserGreaterThanOrEqualTo(String value) {
            addCriterion("createUser >=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThan(String value) {
            addCriterion("createUser <", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLessThanOrEqualTo(String value) {
            addCriterion("createUser <=", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserLike(String value) {
            addCriterion("createUser like", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotLike(String value) {
            addCriterion("createUser not like", value, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserIn(List<String> values) {
            addCriterion("createUser in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotIn(List<String> values) {
            addCriterion("createUser not in", values, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserBetween(String value1, String value2) {
            addCriterion("createUser between", value1, value2, "createuser");
            return (Criteria) this;
        }

        public Criteria andCreateuserNotBetween(String value1, String value2) {
            addCriterion("createUser not between", value1, value2, "createuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIsNull() {
            addCriterion("updateUser is null");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIsNotNull() {
            addCriterion("updateUser is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateuserEqualTo(String value) {
            addCriterion("updateUser =", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotEqualTo(String value) {
            addCriterion("updateUser <>", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserGreaterThan(String value) {
            addCriterion("updateUser >", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserGreaterThanOrEqualTo(String value) {
            addCriterion("updateUser >=", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserLessThan(String value) {
            addCriterion("updateUser <", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserLessThanOrEqualTo(String value) {
            addCriterion("updateUser <=", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserLike(String value) {
            addCriterion("updateUser like", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotLike(String value) {
            addCriterion("updateUser not like", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIn(List<String> values) {
            addCriterion("updateUser in", values, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotIn(List<String> values) {
            addCriterion("updateUser not in", values, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserBetween(String value1, String value2) {
            addCriterion("updateUser between", value1, value2, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotBetween(String value1, String value2) {
            addCriterion("updateUser not between", value1, value2, "updateuser");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andImporttimeIsNull() {
            addCriterion("importTime is null");
            return (Criteria) this;
        }

        public Criteria andImporttimeIsNotNull() {
            addCriterion("importTime is not null");
            return (Criteria) this;
        }

        public Criteria andImporttimeEqualTo(Date value) {
            addCriterion("importTime =", value, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeNotEqualTo(Date value) {
            addCriterion("importTime <>", value, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeGreaterThan(Date value) {
            addCriterion("importTime >", value, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("importTime >=", value, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeLessThan(Date value) {
            addCriterion("importTime <", value, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeLessThanOrEqualTo(Date value) {
            addCriterion("importTime <=", value, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeIn(List<Date> values) {
            addCriterion("importTime in", values, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeNotIn(List<Date> values) {
            addCriterion("importTime not in", values, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeBetween(Date value1, Date value2) {
            addCriterion("importTime between", value1, value2, "importtime");
            return (Criteria) this;
        }

        public Criteria andImporttimeNotBetween(Date value1, Date value2) {
            addCriterion("importTime not between", value1, value2, "importtime");
            return (Criteria) this;
        }

        public Criteria andImportuserIsNull() {
            addCriterion("importUser is null");
            return (Criteria) this;
        }

        public Criteria andImportuserIsNotNull() {
            addCriterion("importUser is not null");
            return (Criteria) this;
        }

        public Criteria andImportuserEqualTo(String value) {
            addCriterion("importUser =", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserNotEqualTo(String value) {
            addCriterion("importUser <>", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserGreaterThan(String value) {
            addCriterion("importUser >", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserGreaterThanOrEqualTo(String value) {
            addCriterion("importUser >=", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserLessThan(String value) {
            addCriterion("importUser <", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserLessThanOrEqualTo(String value) {
            addCriterion("importUser <=", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserLike(String value) {
            addCriterion("importUser like", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserNotLike(String value) {
            addCriterion("importUser not like", value, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserIn(List<String> values) {
            addCriterion("importUser in", values, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserNotIn(List<String> values) {
            addCriterion("importUser not in", values, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserBetween(String value1, String value2) {
            addCriterion("importUser between", value1, value2, "importuser");
            return (Criteria) this;
        }

        public Criteria andImportuserNotBetween(String value1, String value2) {
            addCriterion("importUser not between", value1, value2, "importuser");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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