package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWorkforcemanagementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWorkforcemanagementExample() {
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

        public Criteria andCompanyidIsNull() {
            addCriterion("companyid is null");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNotNull() {
            addCriterion("companyid is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyidEqualTo(Integer value) {
            addCriterion("companyid =", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotEqualTo(Integer value) {
            addCriterion("companyid <>", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThan(Integer value) {
            addCriterion("companyid >", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("companyid >=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThan(Integer value) {
            addCriterion("companyid <", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThanOrEqualTo(Integer value) {
            addCriterion("companyid <=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIn(List<Integer> values) {
            addCriterion("companyid in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotIn(List<Integer> values) {
            addCriterion("companyid not in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidBetween(Integer value1, Integer value2) {
            addCriterion("companyid between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotBetween(Integer value1, Integer value2) {
            addCriterion("companyid not between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCycleIsNull() {
            addCriterion("cycle is null");
            return (Criteria) this;
        }

        public Criteria andCycleIsNotNull() {
            addCriterion("cycle is not null");
            return (Criteria) this;
        }

        public Criteria andCycleEqualTo(String value) {
            addCriterion("cycle =", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotEqualTo(String value) {
            addCriterion("cycle <>", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThan(String value) {
            addCriterion("cycle >", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleGreaterThanOrEqualTo(String value) {
            addCriterion("cycle >=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThan(String value) {
            addCriterion("cycle <", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLessThanOrEqualTo(String value) {
            addCriterion("cycle <=", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleLike(String value) {
            addCriterion("cycle like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotLike(String value) {
            addCriterion("cycle not like", value, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleIn(List<String> values) {
            addCriterion("cycle in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotIn(List<String> values) {
            addCriterion("cycle not in", values, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleBetween(String value1, String value2) {
            addCriterion("cycle between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andCycleNotBetween(String value1, String value2) {
            addCriterion("cycle not between", value1, value2, "cycle");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andDay1IsNull() {
            addCriterion("day1 is null");
            return (Criteria) this;
        }

        public Criteria andDay1IsNotNull() {
            addCriterion("day1 is not null");
            return (Criteria) this;
        }

        public Criteria andDay1EqualTo(String value) {
            addCriterion("day1 =", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1NotEqualTo(String value) {
            addCriterion("day1 <>", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1GreaterThan(String value) {
            addCriterion("day1 >", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1GreaterThanOrEqualTo(String value) {
            addCriterion("day1 >=", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1LessThan(String value) {
            addCriterion("day1 <", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1LessThanOrEqualTo(String value) {
            addCriterion("day1 <=", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1Like(String value) {
            addCriterion("day1 like", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1NotLike(String value) {
            addCriterion("day1 not like", value, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1In(List<String> values) {
            addCriterion("day1 in", values, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1NotIn(List<String> values) {
            addCriterion("day1 not in", values, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1Between(String value1, String value2) {
            addCriterion("day1 between", value1, value2, "day1");
            return (Criteria) this;
        }

        public Criteria andDay1NotBetween(String value1, String value2) {
            addCriterion("day1 not between", value1, value2, "day1");
            return (Criteria) this;
        }

        public Criteria andDay2IsNull() {
            addCriterion("day2 is null");
            return (Criteria) this;
        }

        public Criteria andDay2IsNotNull() {
            addCriterion("day2 is not null");
            return (Criteria) this;
        }

        public Criteria andDay2EqualTo(String value) {
            addCriterion("day2 =", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2NotEqualTo(String value) {
            addCriterion("day2 <>", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2GreaterThan(String value) {
            addCriterion("day2 >", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2GreaterThanOrEqualTo(String value) {
            addCriterion("day2 >=", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2LessThan(String value) {
            addCriterion("day2 <", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2LessThanOrEqualTo(String value) {
            addCriterion("day2 <=", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2Like(String value) {
            addCriterion("day2 like", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2NotLike(String value) {
            addCriterion("day2 not like", value, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2In(List<String> values) {
            addCriterion("day2 in", values, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2NotIn(List<String> values) {
            addCriterion("day2 not in", values, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2Between(String value1, String value2) {
            addCriterion("day2 between", value1, value2, "day2");
            return (Criteria) this;
        }

        public Criteria andDay2NotBetween(String value1, String value2) {
            addCriterion("day2 not between", value1, value2, "day2");
            return (Criteria) this;
        }

        public Criteria andDay3IsNull() {
            addCriterion("day3 is null");
            return (Criteria) this;
        }

        public Criteria andDay3IsNotNull() {
            addCriterion("day3 is not null");
            return (Criteria) this;
        }

        public Criteria andDay3EqualTo(String value) {
            addCriterion("day3 =", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3NotEqualTo(String value) {
            addCriterion("day3 <>", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3GreaterThan(String value) {
            addCriterion("day3 >", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3GreaterThanOrEqualTo(String value) {
            addCriterion("day3 >=", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3LessThan(String value) {
            addCriterion("day3 <", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3LessThanOrEqualTo(String value) {
            addCriterion("day3 <=", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3Like(String value) {
            addCriterion("day3 like", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3NotLike(String value) {
            addCriterion("day3 not like", value, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3In(List<String> values) {
            addCriterion("day3 in", values, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3NotIn(List<String> values) {
            addCriterion("day3 not in", values, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3Between(String value1, String value2) {
            addCriterion("day3 between", value1, value2, "day3");
            return (Criteria) this;
        }

        public Criteria andDay3NotBetween(String value1, String value2) {
            addCriterion("day3 not between", value1, value2, "day3");
            return (Criteria) this;
        }

        public Criteria andDay4IsNull() {
            addCriterion("day4 is null");
            return (Criteria) this;
        }

        public Criteria andDay4IsNotNull() {
            addCriterion("day4 is not null");
            return (Criteria) this;
        }

        public Criteria andDay4EqualTo(String value) {
            addCriterion("day4 =", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4NotEqualTo(String value) {
            addCriterion("day4 <>", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4GreaterThan(String value) {
            addCriterion("day4 >", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4GreaterThanOrEqualTo(String value) {
            addCriterion("day4 >=", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4LessThan(String value) {
            addCriterion("day4 <", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4LessThanOrEqualTo(String value) {
            addCriterion("day4 <=", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4Like(String value) {
            addCriterion("day4 like", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4NotLike(String value) {
            addCriterion("day4 not like", value, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4In(List<String> values) {
            addCriterion("day4 in", values, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4NotIn(List<String> values) {
            addCriterion("day4 not in", values, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4Between(String value1, String value2) {
            addCriterion("day4 between", value1, value2, "day4");
            return (Criteria) this;
        }

        public Criteria andDay4NotBetween(String value1, String value2) {
            addCriterion("day4 not between", value1, value2, "day4");
            return (Criteria) this;
        }

        public Criteria andDay5IsNull() {
            addCriterion("day5 is null");
            return (Criteria) this;
        }

        public Criteria andDay5IsNotNull() {
            addCriterion("day5 is not null");
            return (Criteria) this;
        }

        public Criteria andDay5EqualTo(String value) {
            addCriterion("day5 =", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5NotEqualTo(String value) {
            addCriterion("day5 <>", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5GreaterThan(String value) {
            addCriterion("day5 >", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5GreaterThanOrEqualTo(String value) {
            addCriterion("day5 >=", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5LessThan(String value) {
            addCriterion("day5 <", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5LessThanOrEqualTo(String value) {
            addCriterion("day5 <=", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5Like(String value) {
            addCriterion("day5 like", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5NotLike(String value) {
            addCriterion("day5 not like", value, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5In(List<String> values) {
            addCriterion("day5 in", values, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5NotIn(List<String> values) {
            addCriterion("day5 not in", values, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5Between(String value1, String value2) {
            addCriterion("day5 between", value1, value2, "day5");
            return (Criteria) this;
        }

        public Criteria andDay5NotBetween(String value1, String value2) {
            addCriterion("day5 not between", value1, value2, "day5");
            return (Criteria) this;
        }

        public Criteria andDay6IsNull() {
            addCriterion("day6 is null");
            return (Criteria) this;
        }

        public Criteria andDay6IsNotNull() {
            addCriterion("day6 is not null");
            return (Criteria) this;
        }

        public Criteria andDay6EqualTo(String value) {
            addCriterion("day6 =", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6NotEqualTo(String value) {
            addCriterion("day6 <>", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6GreaterThan(String value) {
            addCriterion("day6 >", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6GreaterThanOrEqualTo(String value) {
            addCriterion("day6 >=", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6LessThan(String value) {
            addCriterion("day6 <", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6LessThanOrEqualTo(String value) {
            addCriterion("day6 <=", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6Like(String value) {
            addCriterion("day6 like", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6NotLike(String value) {
            addCriterion("day6 not like", value, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6In(List<String> values) {
            addCriterion("day6 in", values, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6NotIn(List<String> values) {
            addCriterion("day6 not in", values, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6Between(String value1, String value2) {
            addCriterion("day6 between", value1, value2, "day6");
            return (Criteria) this;
        }

        public Criteria andDay6NotBetween(String value1, String value2) {
            addCriterion("day6 not between", value1, value2, "day6");
            return (Criteria) this;
        }

        public Criteria andDay7IsNull() {
            addCriterion("day7 is null");
            return (Criteria) this;
        }

        public Criteria andDay7IsNotNull() {
            addCriterion("day7 is not null");
            return (Criteria) this;
        }

        public Criteria andDay7EqualTo(String value) {
            addCriterion("day7 =", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7NotEqualTo(String value) {
            addCriterion("day7 <>", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7GreaterThan(String value) {
            addCriterion("day7 >", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7GreaterThanOrEqualTo(String value) {
            addCriterion("day7 >=", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7LessThan(String value) {
            addCriterion("day7 <", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7LessThanOrEqualTo(String value) {
            addCriterion("day7 <=", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7Like(String value) {
            addCriterion("day7 like", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7NotLike(String value) {
            addCriterion("day7 not like", value, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7In(List<String> values) {
            addCriterion("day7 in", values, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7NotIn(List<String> values) {
            addCriterion("day7 not in", values, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7Between(String value1, String value2) {
            addCriterion("day7 between", value1, value2, "day7");
            return (Criteria) this;
        }

        public Criteria andDay7NotBetween(String value1, String value2) {
            addCriterion("day7 not between", value1, value2, "day7");
            return (Criteria) this;
        }

        public Criteria andDay8IsNull() {
            addCriterion("day8 is null");
            return (Criteria) this;
        }

        public Criteria andDay8IsNotNull() {
            addCriterion("day8 is not null");
            return (Criteria) this;
        }

        public Criteria andDay8EqualTo(String value) {
            addCriterion("day8 =", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8NotEqualTo(String value) {
            addCriterion("day8 <>", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8GreaterThan(String value) {
            addCriterion("day8 >", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8GreaterThanOrEqualTo(String value) {
            addCriterion("day8 >=", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8LessThan(String value) {
            addCriterion("day8 <", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8LessThanOrEqualTo(String value) {
            addCriterion("day8 <=", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8Like(String value) {
            addCriterion("day8 like", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8NotLike(String value) {
            addCriterion("day8 not like", value, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8In(List<String> values) {
            addCriterion("day8 in", values, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8NotIn(List<String> values) {
            addCriterion("day8 not in", values, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8Between(String value1, String value2) {
            addCriterion("day8 between", value1, value2, "day8");
            return (Criteria) this;
        }

        public Criteria andDay8NotBetween(String value1, String value2) {
            addCriterion("day8 not between", value1, value2, "day8");
            return (Criteria) this;
        }

        public Criteria andDay9IsNull() {
            addCriterion("day9 is null");
            return (Criteria) this;
        }

        public Criteria andDay9IsNotNull() {
            addCriterion("day9 is not null");
            return (Criteria) this;
        }

        public Criteria andDay9EqualTo(String value) {
            addCriterion("day9 =", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9NotEqualTo(String value) {
            addCriterion("day9 <>", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9GreaterThan(String value) {
            addCriterion("day9 >", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9GreaterThanOrEqualTo(String value) {
            addCriterion("day9 >=", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9LessThan(String value) {
            addCriterion("day9 <", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9LessThanOrEqualTo(String value) {
            addCriterion("day9 <=", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9Like(String value) {
            addCriterion("day9 like", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9NotLike(String value) {
            addCriterion("day9 not like", value, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9In(List<String> values) {
            addCriterion("day9 in", values, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9NotIn(List<String> values) {
            addCriterion("day9 not in", values, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9Between(String value1, String value2) {
            addCriterion("day9 between", value1, value2, "day9");
            return (Criteria) this;
        }

        public Criteria andDay9NotBetween(String value1, String value2) {
            addCriterion("day9 not between", value1, value2, "day9");
            return (Criteria) this;
        }

        public Criteria andDay10IsNull() {
            addCriterion("day10 is null");
            return (Criteria) this;
        }

        public Criteria andDay10IsNotNull() {
            addCriterion("day10 is not null");
            return (Criteria) this;
        }

        public Criteria andDay10EqualTo(String value) {
            addCriterion("day10 =", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10NotEqualTo(String value) {
            addCriterion("day10 <>", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10GreaterThan(String value) {
            addCriterion("day10 >", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10GreaterThanOrEqualTo(String value) {
            addCriterion("day10 >=", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10LessThan(String value) {
            addCriterion("day10 <", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10LessThanOrEqualTo(String value) {
            addCriterion("day10 <=", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10Like(String value) {
            addCriterion("day10 like", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10NotLike(String value) {
            addCriterion("day10 not like", value, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10In(List<String> values) {
            addCriterion("day10 in", values, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10NotIn(List<String> values) {
            addCriterion("day10 not in", values, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10Between(String value1, String value2) {
            addCriterion("day10 between", value1, value2, "day10");
            return (Criteria) this;
        }

        public Criteria andDay10NotBetween(String value1, String value2) {
            addCriterion("day10 not between", value1, value2, "day10");
            return (Criteria) this;
        }

        public Criteria andDay11IsNull() {
            addCriterion("day11 is null");
            return (Criteria) this;
        }

        public Criteria andDay11IsNotNull() {
            addCriterion("day11 is not null");
            return (Criteria) this;
        }

        public Criteria andDay11EqualTo(String value) {
            addCriterion("day11 =", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11NotEqualTo(String value) {
            addCriterion("day11 <>", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11GreaterThan(String value) {
            addCriterion("day11 >", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11GreaterThanOrEqualTo(String value) {
            addCriterion("day11 >=", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11LessThan(String value) {
            addCriterion("day11 <", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11LessThanOrEqualTo(String value) {
            addCriterion("day11 <=", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11Like(String value) {
            addCriterion("day11 like", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11NotLike(String value) {
            addCriterion("day11 not like", value, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11In(List<String> values) {
            addCriterion("day11 in", values, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11NotIn(List<String> values) {
            addCriterion("day11 not in", values, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11Between(String value1, String value2) {
            addCriterion("day11 between", value1, value2, "day11");
            return (Criteria) this;
        }

        public Criteria andDay11NotBetween(String value1, String value2) {
            addCriterion("day11 not between", value1, value2, "day11");
            return (Criteria) this;
        }

        public Criteria andDay12IsNull() {
            addCriterion("day12 is null");
            return (Criteria) this;
        }

        public Criteria andDay12IsNotNull() {
            addCriterion("day12 is not null");
            return (Criteria) this;
        }

        public Criteria andDay12EqualTo(String value) {
            addCriterion("day12 =", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12NotEqualTo(String value) {
            addCriterion("day12 <>", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12GreaterThan(String value) {
            addCriterion("day12 >", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12GreaterThanOrEqualTo(String value) {
            addCriterion("day12 >=", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12LessThan(String value) {
            addCriterion("day12 <", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12LessThanOrEqualTo(String value) {
            addCriterion("day12 <=", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12Like(String value) {
            addCriterion("day12 like", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12NotLike(String value) {
            addCriterion("day12 not like", value, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12In(List<String> values) {
            addCriterion("day12 in", values, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12NotIn(List<String> values) {
            addCriterion("day12 not in", values, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12Between(String value1, String value2) {
            addCriterion("day12 between", value1, value2, "day12");
            return (Criteria) this;
        }

        public Criteria andDay12NotBetween(String value1, String value2) {
            addCriterion("day12 not between", value1, value2, "day12");
            return (Criteria) this;
        }

        public Criteria andDay13IsNull() {
            addCriterion("day13 is null");
            return (Criteria) this;
        }

        public Criteria andDay13IsNotNull() {
            addCriterion("day13 is not null");
            return (Criteria) this;
        }

        public Criteria andDay13EqualTo(String value) {
            addCriterion("day13 =", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13NotEqualTo(String value) {
            addCriterion("day13 <>", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13GreaterThan(String value) {
            addCriterion("day13 >", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13GreaterThanOrEqualTo(String value) {
            addCriterion("day13 >=", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13LessThan(String value) {
            addCriterion("day13 <", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13LessThanOrEqualTo(String value) {
            addCriterion("day13 <=", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13Like(String value) {
            addCriterion("day13 like", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13NotLike(String value) {
            addCriterion("day13 not like", value, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13In(List<String> values) {
            addCriterion("day13 in", values, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13NotIn(List<String> values) {
            addCriterion("day13 not in", values, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13Between(String value1, String value2) {
            addCriterion("day13 between", value1, value2, "day13");
            return (Criteria) this;
        }

        public Criteria andDay13NotBetween(String value1, String value2) {
            addCriterion("day13 not between", value1, value2, "day13");
            return (Criteria) this;
        }

        public Criteria andDay14IsNull() {
            addCriterion("day14 is null");
            return (Criteria) this;
        }

        public Criteria andDay14IsNotNull() {
            addCriterion("day14 is not null");
            return (Criteria) this;
        }

        public Criteria andDay14EqualTo(String value) {
            addCriterion("day14 =", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14NotEqualTo(String value) {
            addCriterion("day14 <>", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14GreaterThan(String value) {
            addCriterion("day14 >", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14GreaterThanOrEqualTo(String value) {
            addCriterion("day14 >=", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14LessThan(String value) {
            addCriterion("day14 <", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14LessThanOrEqualTo(String value) {
            addCriterion("day14 <=", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14Like(String value) {
            addCriterion("day14 like", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14NotLike(String value) {
            addCriterion("day14 not like", value, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14In(List<String> values) {
            addCriterion("day14 in", values, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14NotIn(List<String> values) {
            addCriterion("day14 not in", values, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14Between(String value1, String value2) {
            addCriterion("day14 between", value1, value2, "day14");
            return (Criteria) this;
        }

        public Criteria andDay14NotBetween(String value1, String value2) {
            addCriterion("day14 not between", value1, value2, "day14");
            return (Criteria) this;
        }

        public Criteria andDay15IsNull() {
            addCriterion("day15 is null");
            return (Criteria) this;
        }

        public Criteria andDay15IsNotNull() {
            addCriterion("day15 is not null");
            return (Criteria) this;
        }

        public Criteria andDay15EqualTo(String value) {
            addCriterion("day15 =", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15NotEqualTo(String value) {
            addCriterion("day15 <>", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15GreaterThan(String value) {
            addCriterion("day15 >", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15GreaterThanOrEqualTo(String value) {
            addCriterion("day15 >=", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15LessThan(String value) {
            addCriterion("day15 <", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15LessThanOrEqualTo(String value) {
            addCriterion("day15 <=", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15Like(String value) {
            addCriterion("day15 like", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15NotLike(String value) {
            addCriterion("day15 not like", value, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15In(List<String> values) {
            addCriterion("day15 in", values, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15NotIn(List<String> values) {
            addCriterion("day15 not in", values, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15Between(String value1, String value2) {
            addCriterion("day15 between", value1, value2, "day15");
            return (Criteria) this;
        }

        public Criteria andDay15NotBetween(String value1, String value2) {
            addCriterion("day15 not between", value1, value2, "day15");
            return (Criteria) this;
        }

        public Criteria andDay16IsNull() {
            addCriterion("day16 is null");
            return (Criteria) this;
        }

        public Criteria andDay16IsNotNull() {
            addCriterion("day16 is not null");
            return (Criteria) this;
        }

        public Criteria andDay16EqualTo(String value) {
            addCriterion("day16 =", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16NotEqualTo(String value) {
            addCriterion("day16 <>", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16GreaterThan(String value) {
            addCriterion("day16 >", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16GreaterThanOrEqualTo(String value) {
            addCriterion("day16 >=", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16LessThan(String value) {
            addCriterion("day16 <", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16LessThanOrEqualTo(String value) {
            addCriterion("day16 <=", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16Like(String value) {
            addCriterion("day16 like", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16NotLike(String value) {
            addCriterion("day16 not like", value, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16In(List<String> values) {
            addCriterion("day16 in", values, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16NotIn(List<String> values) {
            addCriterion("day16 not in", values, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16Between(String value1, String value2) {
            addCriterion("day16 between", value1, value2, "day16");
            return (Criteria) this;
        }

        public Criteria andDay16NotBetween(String value1, String value2) {
            addCriterion("day16 not between", value1, value2, "day16");
            return (Criteria) this;
        }

        public Criteria andDay17IsNull() {
            addCriterion("day17 is null");
            return (Criteria) this;
        }

        public Criteria andDay17IsNotNull() {
            addCriterion("day17 is not null");
            return (Criteria) this;
        }

        public Criteria andDay17EqualTo(String value) {
            addCriterion("day17 =", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17NotEqualTo(String value) {
            addCriterion("day17 <>", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17GreaterThan(String value) {
            addCriterion("day17 >", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17GreaterThanOrEqualTo(String value) {
            addCriterion("day17 >=", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17LessThan(String value) {
            addCriterion("day17 <", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17LessThanOrEqualTo(String value) {
            addCriterion("day17 <=", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17Like(String value) {
            addCriterion("day17 like", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17NotLike(String value) {
            addCriterion("day17 not like", value, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17In(List<String> values) {
            addCriterion("day17 in", values, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17NotIn(List<String> values) {
            addCriterion("day17 not in", values, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17Between(String value1, String value2) {
            addCriterion("day17 between", value1, value2, "day17");
            return (Criteria) this;
        }

        public Criteria andDay17NotBetween(String value1, String value2) {
            addCriterion("day17 not between", value1, value2, "day17");
            return (Criteria) this;
        }

        public Criteria andDay18IsNull() {
            addCriterion("day18 is null");
            return (Criteria) this;
        }

        public Criteria andDay18IsNotNull() {
            addCriterion("day18 is not null");
            return (Criteria) this;
        }

        public Criteria andDay18EqualTo(String value) {
            addCriterion("day18 =", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18NotEqualTo(String value) {
            addCriterion("day18 <>", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18GreaterThan(String value) {
            addCriterion("day18 >", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18GreaterThanOrEqualTo(String value) {
            addCriterion("day18 >=", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18LessThan(String value) {
            addCriterion("day18 <", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18LessThanOrEqualTo(String value) {
            addCriterion("day18 <=", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18Like(String value) {
            addCriterion("day18 like", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18NotLike(String value) {
            addCriterion("day18 not like", value, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18In(List<String> values) {
            addCriterion("day18 in", values, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18NotIn(List<String> values) {
            addCriterion("day18 not in", values, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18Between(String value1, String value2) {
            addCriterion("day18 between", value1, value2, "day18");
            return (Criteria) this;
        }

        public Criteria andDay18NotBetween(String value1, String value2) {
            addCriterion("day18 not between", value1, value2, "day18");
            return (Criteria) this;
        }

        public Criteria andDay19IsNull() {
            addCriterion("day19 is null");
            return (Criteria) this;
        }

        public Criteria andDay19IsNotNull() {
            addCriterion("day19 is not null");
            return (Criteria) this;
        }

        public Criteria andDay19EqualTo(String value) {
            addCriterion("day19 =", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19NotEqualTo(String value) {
            addCriterion("day19 <>", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19GreaterThan(String value) {
            addCriterion("day19 >", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19GreaterThanOrEqualTo(String value) {
            addCriterion("day19 >=", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19LessThan(String value) {
            addCriterion("day19 <", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19LessThanOrEqualTo(String value) {
            addCriterion("day19 <=", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19Like(String value) {
            addCriterion("day19 like", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19NotLike(String value) {
            addCriterion("day19 not like", value, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19In(List<String> values) {
            addCriterion("day19 in", values, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19NotIn(List<String> values) {
            addCriterion("day19 not in", values, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19Between(String value1, String value2) {
            addCriterion("day19 between", value1, value2, "day19");
            return (Criteria) this;
        }

        public Criteria andDay19NotBetween(String value1, String value2) {
            addCriterion("day19 not between", value1, value2, "day19");
            return (Criteria) this;
        }

        public Criteria andDay20IsNull() {
            addCriterion("day20 is null");
            return (Criteria) this;
        }

        public Criteria andDay20IsNotNull() {
            addCriterion("day20 is not null");
            return (Criteria) this;
        }

        public Criteria andDay20EqualTo(String value) {
            addCriterion("day20 =", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20NotEqualTo(String value) {
            addCriterion("day20 <>", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20GreaterThan(String value) {
            addCriterion("day20 >", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20GreaterThanOrEqualTo(String value) {
            addCriterion("day20 >=", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20LessThan(String value) {
            addCriterion("day20 <", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20LessThanOrEqualTo(String value) {
            addCriterion("day20 <=", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20Like(String value) {
            addCriterion("day20 like", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20NotLike(String value) {
            addCriterion("day20 not like", value, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20In(List<String> values) {
            addCriterion("day20 in", values, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20NotIn(List<String> values) {
            addCriterion("day20 not in", values, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20Between(String value1, String value2) {
            addCriterion("day20 between", value1, value2, "day20");
            return (Criteria) this;
        }

        public Criteria andDay20NotBetween(String value1, String value2) {
            addCriterion("day20 not between", value1, value2, "day20");
            return (Criteria) this;
        }

        public Criteria andDay21IsNull() {
            addCriterion("day21 is null");
            return (Criteria) this;
        }

        public Criteria andDay21IsNotNull() {
            addCriterion("day21 is not null");
            return (Criteria) this;
        }

        public Criteria andDay21EqualTo(String value) {
            addCriterion("day21 =", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21NotEqualTo(String value) {
            addCriterion("day21 <>", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21GreaterThan(String value) {
            addCriterion("day21 >", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21GreaterThanOrEqualTo(String value) {
            addCriterion("day21 >=", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21LessThan(String value) {
            addCriterion("day21 <", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21LessThanOrEqualTo(String value) {
            addCriterion("day21 <=", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21Like(String value) {
            addCriterion("day21 like", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21NotLike(String value) {
            addCriterion("day21 not like", value, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21In(List<String> values) {
            addCriterion("day21 in", values, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21NotIn(List<String> values) {
            addCriterion("day21 not in", values, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21Between(String value1, String value2) {
            addCriterion("day21 between", value1, value2, "day21");
            return (Criteria) this;
        }

        public Criteria andDay21NotBetween(String value1, String value2) {
            addCriterion("day21 not between", value1, value2, "day21");
            return (Criteria) this;
        }

        public Criteria andDay22IsNull() {
            addCriterion("day22 is null");
            return (Criteria) this;
        }

        public Criteria andDay22IsNotNull() {
            addCriterion("day22 is not null");
            return (Criteria) this;
        }

        public Criteria andDay22EqualTo(String value) {
            addCriterion("day22 =", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22NotEqualTo(String value) {
            addCriterion("day22 <>", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22GreaterThan(String value) {
            addCriterion("day22 >", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22GreaterThanOrEqualTo(String value) {
            addCriterion("day22 >=", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22LessThan(String value) {
            addCriterion("day22 <", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22LessThanOrEqualTo(String value) {
            addCriterion("day22 <=", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22Like(String value) {
            addCriterion("day22 like", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22NotLike(String value) {
            addCriterion("day22 not like", value, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22In(List<String> values) {
            addCriterion("day22 in", values, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22NotIn(List<String> values) {
            addCriterion("day22 not in", values, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22Between(String value1, String value2) {
            addCriterion("day22 between", value1, value2, "day22");
            return (Criteria) this;
        }

        public Criteria andDay22NotBetween(String value1, String value2) {
            addCriterion("day22 not between", value1, value2, "day22");
            return (Criteria) this;
        }

        public Criteria andDay23IsNull() {
            addCriterion("day23 is null");
            return (Criteria) this;
        }

        public Criteria andDay23IsNotNull() {
            addCriterion("day23 is not null");
            return (Criteria) this;
        }

        public Criteria andDay23EqualTo(String value) {
            addCriterion("day23 =", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23NotEqualTo(String value) {
            addCriterion("day23 <>", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23GreaterThan(String value) {
            addCriterion("day23 >", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23GreaterThanOrEqualTo(String value) {
            addCriterion("day23 >=", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23LessThan(String value) {
            addCriterion("day23 <", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23LessThanOrEqualTo(String value) {
            addCriterion("day23 <=", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23Like(String value) {
            addCriterion("day23 like", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23NotLike(String value) {
            addCriterion("day23 not like", value, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23In(List<String> values) {
            addCriterion("day23 in", values, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23NotIn(List<String> values) {
            addCriterion("day23 not in", values, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23Between(String value1, String value2) {
            addCriterion("day23 between", value1, value2, "day23");
            return (Criteria) this;
        }

        public Criteria andDay23NotBetween(String value1, String value2) {
            addCriterion("day23 not between", value1, value2, "day23");
            return (Criteria) this;
        }

        public Criteria andDay24IsNull() {
            addCriterion("day24 is null");
            return (Criteria) this;
        }

        public Criteria andDay24IsNotNull() {
            addCriterion("day24 is not null");
            return (Criteria) this;
        }

        public Criteria andDay24EqualTo(String value) {
            addCriterion("day24 =", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24NotEqualTo(String value) {
            addCriterion("day24 <>", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24GreaterThan(String value) {
            addCriterion("day24 >", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24GreaterThanOrEqualTo(String value) {
            addCriterion("day24 >=", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24LessThan(String value) {
            addCriterion("day24 <", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24LessThanOrEqualTo(String value) {
            addCriterion("day24 <=", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24Like(String value) {
            addCriterion("day24 like", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24NotLike(String value) {
            addCriterion("day24 not like", value, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24In(List<String> values) {
            addCriterion("day24 in", values, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24NotIn(List<String> values) {
            addCriterion("day24 not in", values, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24Between(String value1, String value2) {
            addCriterion("day24 between", value1, value2, "day24");
            return (Criteria) this;
        }

        public Criteria andDay24NotBetween(String value1, String value2) {
            addCriterion("day24 not between", value1, value2, "day24");
            return (Criteria) this;
        }

        public Criteria andDay25IsNull() {
            addCriterion("day25 is null");
            return (Criteria) this;
        }

        public Criteria andDay25IsNotNull() {
            addCriterion("day25 is not null");
            return (Criteria) this;
        }

        public Criteria andDay25EqualTo(String value) {
            addCriterion("day25 =", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25NotEqualTo(String value) {
            addCriterion("day25 <>", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25GreaterThan(String value) {
            addCriterion("day25 >", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25GreaterThanOrEqualTo(String value) {
            addCriterion("day25 >=", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25LessThan(String value) {
            addCriterion("day25 <", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25LessThanOrEqualTo(String value) {
            addCriterion("day25 <=", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25Like(String value) {
            addCriterion("day25 like", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25NotLike(String value) {
            addCriterion("day25 not like", value, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25In(List<String> values) {
            addCriterion("day25 in", values, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25NotIn(List<String> values) {
            addCriterion("day25 not in", values, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25Between(String value1, String value2) {
            addCriterion("day25 between", value1, value2, "day25");
            return (Criteria) this;
        }

        public Criteria andDay25NotBetween(String value1, String value2) {
            addCriterion("day25 not between", value1, value2, "day25");
            return (Criteria) this;
        }

        public Criteria andDay26IsNull() {
            addCriterion("day26 is null");
            return (Criteria) this;
        }

        public Criteria andDay26IsNotNull() {
            addCriterion("day26 is not null");
            return (Criteria) this;
        }

        public Criteria andDay26EqualTo(String value) {
            addCriterion("day26 =", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26NotEqualTo(String value) {
            addCriterion("day26 <>", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26GreaterThan(String value) {
            addCriterion("day26 >", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26GreaterThanOrEqualTo(String value) {
            addCriterion("day26 >=", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26LessThan(String value) {
            addCriterion("day26 <", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26LessThanOrEqualTo(String value) {
            addCriterion("day26 <=", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26Like(String value) {
            addCriterion("day26 like", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26NotLike(String value) {
            addCriterion("day26 not like", value, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26In(List<String> values) {
            addCriterion("day26 in", values, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26NotIn(List<String> values) {
            addCriterion("day26 not in", values, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26Between(String value1, String value2) {
            addCriterion("day26 between", value1, value2, "day26");
            return (Criteria) this;
        }

        public Criteria andDay26NotBetween(String value1, String value2) {
            addCriterion("day26 not between", value1, value2, "day26");
            return (Criteria) this;
        }

        public Criteria andDay27IsNull() {
            addCriterion("day27 is null");
            return (Criteria) this;
        }

        public Criteria andDay27IsNotNull() {
            addCriterion("day27 is not null");
            return (Criteria) this;
        }

        public Criteria andDay27EqualTo(String value) {
            addCriterion("day27 =", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27NotEqualTo(String value) {
            addCriterion("day27 <>", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27GreaterThan(String value) {
            addCriterion("day27 >", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27GreaterThanOrEqualTo(String value) {
            addCriterion("day27 >=", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27LessThan(String value) {
            addCriterion("day27 <", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27LessThanOrEqualTo(String value) {
            addCriterion("day27 <=", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27Like(String value) {
            addCriterion("day27 like", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27NotLike(String value) {
            addCriterion("day27 not like", value, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27In(List<String> values) {
            addCriterion("day27 in", values, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27NotIn(List<String> values) {
            addCriterion("day27 not in", values, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27Between(String value1, String value2) {
            addCriterion("day27 between", value1, value2, "day27");
            return (Criteria) this;
        }

        public Criteria andDay27NotBetween(String value1, String value2) {
            addCriterion("day27 not between", value1, value2, "day27");
            return (Criteria) this;
        }

        public Criteria andDay28IsNull() {
            addCriterion("day28 is null");
            return (Criteria) this;
        }

        public Criteria andDay28IsNotNull() {
            addCriterion("day28 is not null");
            return (Criteria) this;
        }

        public Criteria andDay28EqualTo(String value) {
            addCriterion("day28 =", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28NotEqualTo(String value) {
            addCriterion("day28 <>", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28GreaterThan(String value) {
            addCriterion("day28 >", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28GreaterThanOrEqualTo(String value) {
            addCriterion("day28 >=", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28LessThan(String value) {
            addCriterion("day28 <", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28LessThanOrEqualTo(String value) {
            addCriterion("day28 <=", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28Like(String value) {
            addCriterion("day28 like", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28NotLike(String value) {
            addCriterion("day28 not like", value, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28In(List<String> values) {
            addCriterion("day28 in", values, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28NotIn(List<String> values) {
            addCriterion("day28 not in", values, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28Between(String value1, String value2) {
            addCriterion("day28 between", value1, value2, "day28");
            return (Criteria) this;
        }

        public Criteria andDay28NotBetween(String value1, String value2) {
            addCriterion("day28 not between", value1, value2, "day28");
            return (Criteria) this;
        }

        public Criteria andDay29IsNull() {
            addCriterion("day29 is null");
            return (Criteria) this;
        }

        public Criteria andDay29IsNotNull() {
            addCriterion("day29 is not null");
            return (Criteria) this;
        }

        public Criteria andDay29EqualTo(String value) {
            addCriterion("day29 =", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29NotEqualTo(String value) {
            addCriterion("day29 <>", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29GreaterThan(String value) {
            addCriterion("day29 >", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29GreaterThanOrEqualTo(String value) {
            addCriterion("day29 >=", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29LessThan(String value) {
            addCriterion("day29 <", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29LessThanOrEqualTo(String value) {
            addCriterion("day29 <=", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29Like(String value) {
            addCriterion("day29 like", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29NotLike(String value) {
            addCriterion("day29 not like", value, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29In(List<String> values) {
            addCriterion("day29 in", values, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29NotIn(List<String> values) {
            addCriterion("day29 not in", values, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29Between(String value1, String value2) {
            addCriterion("day29 between", value1, value2, "day29");
            return (Criteria) this;
        }

        public Criteria andDay29NotBetween(String value1, String value2) {
            addCriterion("day29 not between", value1, value2, "day29");
            return (Criteria) this;
        }

        public Criteria andDay30IsNull() {
            addCriterion("day30 is null");
            return (Criteria) this;
        }

        public Criteria andDay30IsNotNull() {
            addCriterion("day30 is not null");
            return (Criteria) this;
        }

        public Criteria andDay30EqualTo(String value) {
            addCriterion("day30 =", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30NotEqualTo(String value) {
            addCriterion("day30 <>", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30GreaterThan(String value) {
            addCriterion("day30 >", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30GreaterThanOrEqualTo(String value) {
            addCriterion("day30 >=", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30LessThan(String value) {
            addCriterion("day30 <", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30LessThanOrEqualTo(String value) {
            addCriterion("day30 <=", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30Like(String value) {
            addCriterion("day30 like", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30NotLike(String value) {
            addCriterion("day30 not like", value, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30In(List<String> values) {
            addCriterion("day30 in", values, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30NotIn(List<String> values) {
            addCriterion("day30 not in", values, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30Between(String value1, String value2) {
            addCriterion("day30 between", value1, value2, "day30");
            return (Criteria) this;
        }

        public Criteria andDay30NotBetween(String value1, String value2) {
            addCriterion("day30 not between", value1, value2, "day30");
            return (Criteria) this;
        }

        public Criteria andDay31IsNull() {
            addCriterion("day31 is null");
            return (Criteria) this;
        }

        public Criteria andDay31IsNotNull() {
            addCriterion("day31 is not null");
            return (Criteria) this;
        }

        public Criteria andDay31EqualTo(String value) {
            addCriterion("day31 =", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31NotEqualTo(String value) {
            addCriterion("day31 <>", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31GreaterThan(String value) {
            addCriterion("day31 >", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31GreaterThanOrEqualTo(String value) {
            addCriterion("day31 >=", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31LessThan(String value) {
            addCriterion("day31 <", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31LessThanOrEqualTo(String value) {
            addCriterion("day31 <=", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31Like(String value) {
            addCriterion("day31 like", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31NotLike(String value) {
            addCriterion("day31 not like", value, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31In(List<String> values) {
            addCriterion("day31 in", values, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31NotIn(List<String> values) {
            addCriterion("day31 not in", values, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31Between(String value1, String value2) {
            addCriterion("day31 between", value1, value2, "day31");
            return (Criteria) this;
        }

        public Criteria andDay31NotBetween(String value1, String value2) {
            addCriterion("day31 not between", value1, value2, "day31");
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

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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