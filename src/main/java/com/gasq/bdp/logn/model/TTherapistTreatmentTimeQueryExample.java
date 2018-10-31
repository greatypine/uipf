package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTherapistTreatmentTimeQueryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TTherapistTreatmentTimeQueryExample() {
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

        public Criteria andTime1IsNull() {
            addCriterion("time1 is null");
            return (Criteria) this;
        }

        public Criteria andTime1IsNotNull() {
            addCriterion("time1 is not null");
            return (Criteria) this;
        }

        public Criteria andTime1EqualTo(String value) {
            addCriterion("time1 =", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotEqualTo(String value) {
            addCriterion("time1 <>", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1GreaterThan(String value) {
            addCriterion("time1 >", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1GreaterThanOrEqualTo(String value) {
            addCriterion("time1 >=", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1LessThan(String value) {
            addCriterion("time1 <", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1LessThanOrEqualTo(String value) {
            addCriterion("time1 <=", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1Like(String value) {
            addCriterion("time1 like", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotLike(String value) {
            addCriterion("time1 not like", value, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1In(List<String> values) {
            addCriterion("time1 in", values, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotIn(List<String> values) {
            addCriterion("time1 not in", values, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1Between(String value1, String value2) {
            addCriterion("time1 between", value1, value2, "time1");
            return (Criteria) this;
        }

        public Criteria andTime1NotBetween(String value1, String value2) {
            addCriterion("time1 not between", value1, value2, "time1");
            return (Criteria) this;
        }

        public Criteria andTime2IsNull() {
            addCriterion("time2 is null");
            return (Criteria) this;
        }

        public Criteria andTime2IsNotNull() {
            addCriterion("time2 is not null");
            return (Criteria) this;
        }

        public Criteria andTime2EqualTo(String value) {
            addCriterion("time2 =", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2NotEqualTo(String value) {
            addCriterion("time2 <>", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2GreaterThan(String value) {
            addCriterion("time2 >", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2GreaterThanOrEqualTo(String value) {
            addCriterion("time2 >=", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2LessThan(String value) {
            addCriterion("time2 <", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2LessThanOrEqualTo(String value) {
            addCriterion("time2 <=", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2Like(String value) {
            addCriterion("time2 like", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2NotLike(String value) {
            addCriterion("time2 not like", value, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2In(List<String> values) {
            addCriterion("time2 in", values, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2NotIn(List<String> values) {
            addCriterion("time2 not in", values, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2Between(String value1, String value2) {
            addCriterion("time2 between", value1, value2, "time2");
            return (Criteria) this;
        }

        public Criteria andTime2NotBetween(String value1, String value2) {
            addCriterion("time2 not between", value1, value2, "time2");
            return (Criteria) this;
        }

        public Criteria andTime3IsNull() {
            addCriterion("time3 is null");
            return (Criteria) this;
        }

        public Criteria andTime3IsNotNull() {
            addCriterion("time3 is not null");
            return (Criteria) this;
        }

        public Criteria andTime3EqualTo(String value) {
            addCriterion("time3 =", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3NotEqualTo(String value) {
            addCriterion("time3 <>", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3GreaterThan(String value) {
            addCriterion("time3 >", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3GreaterThanOrEqualTo(String value) {
            addCriterion("time3 >=", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3LessThan(String value) {
            addCriterion("time3 <", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3LessThanOrEqualTo(String value) {
            addCriterion("time3 <=", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3Like(String value) {
            addCriterion("time3 like", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3NotLike(String value) {
            addCriterion("time3 not like", value, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3In(List<String> values) {
            addCriterion("time3 in", values, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3NotIn(List<String> values) {
            addCriterion("time3 not in", values, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3Between(String value1, String value2) {
            addCriterion("time3 between", value1, value2, "time3");
            return (Criteria) this;
        }

        public Criteria andTime3NotBetween(String value1, String value2) {
            addCriterion("time3 not between", value1, value2, "time3");
            return (Criteria) this;
        }

        public Criteria andTime4IsNull() {
            addCriterion("time4 is null");
            return (Criteria) this;
        }

        public Criteria andTime4IsNotNull() {
            addCriterion("time4 is not null");
            return (Criteria) this;
        }

        public Criteria andTime4EqualTo(String value) {
            addCriterion("time4 =", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4NotEqualTo(String value) {
            addCriterion("time4 <>", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4GreaterThan(String value) {
            addCriterion("time4 >", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4GreaterThanOrEqualTo(String value) {
            addCriterion("time4 >=", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4LessThan(String value) {
            addCriterion("time4 <", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4LessThanOrEqualTo(String value) {
            addCriterion("time4 <=", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4Like(String value) {
            addCriterion("time4 like", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4NotLike(String value) {
            addCriterion("time4 not like", value, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4In(List<String> values) {
            addCriterion("time4 in", values, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4NotIn(List<String> values) {
            addCriterion("time4 not in", values, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4Between(String value1, String value2) {
            addCriterion("time4 between", value1, value2, "time4");
            return (Criteria) this;
        }

        public Criteria andTime4NotBetween(String value1, String value2) {
            addCriterion("time4 not between", value1, value2, "time4");
            return (Criteria) this;
        }

        public Criteria andTime5IsNull() {
            addCriterion("time5 is null");
            return (Criteria) this;
        }

        public Criteria andTime5IsNotNull() {
            addCriterion("time5 is not null");
            return (Criteria) this;
        }

        public Criteria andTime5EqualTo(String value) {
            addCriterion("time5 =", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5NotEqualTo(String value) {
            addCriterion("time5 <>", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5GreaterThan(String value) {
            addCriterion("time5 >", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5GreaterThanOrEqualTo(String value) {
            addCriterion("time5 >=", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5LessThan(String value) {
            addCriterion("time5 <", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5LessThanOrEqualTo(String value) {
            addCriterion("time5 <=", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5Like(String value) {
            addCriterion("time5 like", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5NotLike(String value) {
            addCriterion("time5 not like", value, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5In(List<String> values) {
            addCriterion("time5 in", values, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5NotIn(List<String> values) {
            addCriterion("time5 not in", values, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5Between(String value1, String value2) {
            addCriterion("time5 between", value1, value2, "time5");
            return (Criteria) this;
        }

        public Criteria andTime5NotBetween(String value1, String value2) {
            addCriterion("time5 not between", value1, value2, "time5");
            return (Criteria) this;
        }

        public Criteria andTime6IsNull() {
            addCriterion("time6 is null");
            return (Criteria) this;
        }

        public Criteria andTime6IsNotNull() {
            addCriterion("time6 is not null");
            return (Criteria) this;
        }

        public Criteria andTime6EqualTo(String value) {
            addCriterion("time6 =", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6NotEqualTo(String value) {
            addCriterion("time6 <>", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6GreaterThan(String value) {
            addCriterion("time6 >", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6GreaterThanOrEqualTo(String value) {
            addCriterion("time6 >=", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6LessThan(String value) {
            addCriterion("time6 <", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6LessThanOrEqualTo(String value) {
            addCriterion("time6 <=", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6Like(String value) {
            addCriterion("time6 like", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6NotLike(String value) {
            addCriterion("time6 not like", value, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6In(List<String> values) {
            addCriterion("time6 in", values, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6NotIn(List<String> values) {
            addCriterion("time6 not in", values, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6Between(String value1, String value2) {
            addCriterion("time6 between", value1, value2, "time6");
            return (Criteria) this;
        }

        public Criteria andTime6NotBetween(String value1, String value2) {
            addCriterion("time6 not between", value1, value2, "time6");
            return (Criteria) this;
        }

        public Criteria andTime7IsNull() {
            addCriterion("time7 is null");
            return (Criteria) this;
        }

        public Criteria andTime7IsNotNull() {
            addCriterion("time7 is not null");
            return (Criteria) this;
        }

        public Criteria andTime7EqualTo(String value) {
            addCriterion("time7 =", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7NotEqualTo(String value) {
            addCriterion("time7 <>", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7GreaterThan(String value) {
            addCriterion("time7 >", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7GreaterThanOrEqualTo(String value) {
            addCriterion("time7 >=", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7LessThan(String value) {
            addCriterion("time7 <", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7LessThanOrEqualTo(String value) {
            addCriterion("time7 <=", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7Like(String value) {
            addCriterion("time7 like", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7NotLike(String value) {
            addCriterion("time7 not like", value, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7In(List<String> values) {
            addCriterion("time7 in", values, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7NotIn(List<String> values) {
            addCriterion("time7 not in", values, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7Between(String value1, String value2) {
            addCriterion("time7 between", value1, value2, "time7");
            return (Criteria) this;
        }

        public Criteria andTime7NotBetween(String value1, String value2) {
            addCriterion("time7 not between", value1, value2, "time7");
            return (Criteria) this;
        }

        public Criteria andTime8IsNull() {
            addCriterion("time8 is null");
            return (Criteria) this;
        }

        public Criteria andTime8IsNotNull() {
            addCriterion("time8 is not null");
            return (Criteria) this;
        }

        public Criteria andTime8EqualTo(String value) {
            addCriterion("time8 =", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8NotEqualTo(String value) {
            addCriterion("time8 <>", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8GreaterThan(String value) {
            addCriterion("time8 >", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8GreaterThanOrEqualTo(String value) {
            addCriterion("time8 >=", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8LessThan(String value) {
            addCriterion("time8 <", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8LessThanOrEqualTo(String value) {
            addCriterion("time8 <=", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8Like(String value) {
            addCriterion("time8 like", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8NotLike(String value) {
            addCriterion("time8 not like", value, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8In(List<String> values) {
            addCriterion("time8 in", values, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8NotIn(List<String> values) {
            addCriterion("time8 not in", values, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8Between(String value1, String value2) {
            addCriterion("time8 between", value1, value2, "time8");
            return (Criteria) this;
        }

        public Criteria andTime8NotBetween(String value1, String value2) {
            addCriterion("time8 not between", value1, value2, "time8");
            return (Criteria) this;
        }

        public Criteria andTime9IsNull() {
            addCriterion("time9 is null");
            return (Criteria) this;
        }

        public Criteria andTime9IsNotNull() {
            addCriterion("time9 is not null");
            return (Criteria) this;
        }

        public Criteria andTime9EqualTo(String value) {
            addCriterion("time9 =", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9NotEqualTo(String value) {
            addCriterion("time9 <>", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9GreaterThan(String value) {
            addCriterion("time9 >", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9GreaterThanOrEqualTo(String value) {
            addCriterion("time9 >=", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9LessThan(String value) {
            addCriterion("time9 <", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9LessThanOrEqualTo(String value) {
            addCriterion("time9 <=", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9Like(String value) {
            addCriterion("time9 like", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9NotLike(String value) {
            addCriterion("time9 not like", value, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9In(List<String> values) {
            addCriterion("time9 in", values, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9NotIn(List<String> values) {
            addCriterion("time9 not in", values, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9Between(String value1, String value2) {
            addCriterion("time9 between", value1, value2, "time9");
            return (Criteria) this;
        }

        public Criteria andTime9NotBetween(String value1, String value2) {
            addCriterion("time9 not between", value1, value2, "time9");
            return (Criteria) this;
        }

        public Criteria andTime10IsNull() {
            addCriterion("time10 is null");
            return (Criteria) this;
        }

        public Criteria andTime10IsNotNull() {
            addCriterion("time10 is not null");
            return (Criteria) this;
        }

        public Criteria andTime10EqualTo(String value) {
            addCriterion("time10 =", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10NotEqualTo(String value) {
            addCriterion("time10 <>", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10GreaterThan(String value) {
            addCriterion("time10 >", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10GreaterThanOrEqualTo(String value) {
            addCriterion("time10 >=", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10LessThan(String value) {
            addCriterion("time10 <", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10LessThanOrEqualTo(String value) {
            addCriterion("time10 <=", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10Like(String value) {
            addCriterion("time10 like", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10NotLike(String value) {
            addCriterion("time10 not like", value, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10In(List<String> values) {
            addCriterion("time10 in", values, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10NotIn(List<String> values) {
            addCriterion("time10 not in", values, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10Between(String value1, String value2) {
            addCriterion("time10 between", value1, value2, "time10");
            return (Criteria) this;
        }

        public Criteria andTime10NotBetween(String value1, String value2) {
            addCriterion("time10 not between", value1, value2, "time10");
            return (Criteria) this;
        }

        public Criteria andTime11IsNull() {
            addCriterion("time11 is null");
            return (Criteria) this;
        }

        public Criteria andTime11IsNotNull() {
            addCriterion("time11 is not null");
            return (Criteria) this;
        }

        public Criteria andTime11EqualTo(String value) {
            addCriterion("time11 =", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11NotEqualTo(String value) {
            addCriterion("time11 <>", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11GreaterThan(String value) {
            addCriterion("time11 >", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11GreaterThanOrEqualTo(String value) {
            addCriterion("time11 >=", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11LessThan(String value) {
            addCriterion("time11 <", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11LessThanOrEqualTo(String value) {
            addCriterion("time11 <=", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11Like(String value) {
            addCriterion("time11 like", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11NotLike(String value) {
            addCriterion("time11 not like", value, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11In(List<String> values) {
            addCriterion("time11 in", values, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11NotIn(List<String> values) {
            addCriterion("time11 not in", values, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11Between(String value1, String value2) {
            addCriterion("time11 between", value1, value2, "time11");
            return (Criteria) this;
        }

        public Criteria andTime11NotBetween(String value1, String value2) {
            addCriterion("time11 not between", value1, value2, "time11");
            return (Criteria) this;
        }

        public Criteria andTime12IsNull() {
            addCriterion("time12 is null");
            return (Criteria) this;
        }

        public Criteria andTime12IsNotNull() {
            addCriterion("time12 is not null");
            return (Criteria) this;
        }

        public Criteria andTime12EqualTo(String value) {
            addCriterion("time12 =", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12NotEqualTo(String value) {
            addCriterion("time12 <>", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12GreaterThan(String value) {
            addCriterion("time12 >", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12GreaterThanOrEqualTo(String value) {
            addCriterion("time12 >=", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12LessThan(String value) {
            addCriterion("time12 <", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12LessThanOrEqualTo(String value) {
            addCriterion("time12 <=", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12Like(String value) {
            addCriterion("time12 like", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12NotLike(String value) {
            addCriterion("time12 not like", value, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12In(List<String> values) {
            addCriterion("time12 in", values, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12NotIn(List<String> values) {
            addCriterion("time12 not in", values, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12Between(String value1, String value2) {
            addCriterion("time12 between", value1, value2, "time12");
            return (Criteria) this;
        }

        public Criteria andTime12NotBetween(String value1, String value2) {
            addCriterion("time12 not between", value1, value2, "time12");
            return (Criteria) this;
        }

        public Criteria andTime13IsNull() {
            addCriterion("time13 is null");
            return (Criteria) this;
        }

        public Criteria andTime13IsNotNull() {
            addCriterion("time13 is not null");
            return (Criteria) this;
        }

        public Criteria andTime13EqualTo(String value) {
            addCriterion("time13 =", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13NotEqualTo(String value) {
            addCriterion("time13 <>", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13GreaterThan(String value) {
            addCriterion("time13 >", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13GreaterThanOrEqualTo(String value) {
            addCriterion("time13 >=", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13LessThan(String value) {
            addCriterion("time13 <", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13LessThanOrEqualTo(String value) {
            addCriterion("time13 <=", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13Like(String value) {
            addCriterion("time13 like", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13NotLike(String value) {
            addCriterion("time13 not like", value, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13In(List<String> values) {
            addCriterion("time13 in", values, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13NotIn(List<String> values) {
            addCriterion("time13 not in", values, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13Between(String value1, String value2) {
            addCriterion("time13 between", value1, value2, "time13");
            return (Criteria) this;
        }

        public Criteria andTime13NotBetween(String value1, String value2) {
            addCriterion("time13 not between", value1, value2, "time13");
            return (Criteria) this;
        }

        public Criteria andTime14IsNull() {
            addCriterion("time14 is null");
            return (Criteria) this;
        }

        public Criteria andTime14IsNotNull() {
            addCriterion("time14 is not null");
            return (Criteria) this;
        }

        public Criteria andTime14EqualTo(String value) {
            addCriterion("time14 =", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14NotEqualTo(String value) {
            addCriterion("time14 <>", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14GreaterThan(String value) {
            addCriterion("time14 >", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14GreaterThanOrEqualTo(String value) {
            addCriterion("time14 >=", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14LessThan(String value) {
            addCriterion("time14 <", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14LessThanOrEqualTo(String value) {
            addCriterion("time14 <=", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14Like(String value) {
            addCriterion("time14 like", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14NotLike(String value) {
            addCriterion("time14 not like", value, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14In(List<String> values) {
            addCriterion("time14 in", values, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14NotIn(List<String> values) {
            addCriterion("time14 not in", values, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14Between(String value1, String value2) {
            addCriterion("time14 between", value1, value2, "time14");
            return (Criteria) this;
        }

        public Criteria andTime14NotBetween(String value1, String value2) {
            addCriterion("time14 not between", value1, value2, "time14");
            return (Criteria) this;
        }

        public Criteria andTime15IsNull() {
            addCriterion("time15 is null");
            return (Criteria) this;
        }

        public Criteria andTime15IsNotNull() {
            addCriterion("time15 is not null");
            return (Criteria) this;
        }

        public Criteria andTime15EqualTo(String value) {
            addCriterion("time15 =", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15NotEqualTo(String value) {
            addCriterion("time15 <>", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15GreaterThan(String value) {
            addCriterion("time15 >", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15GreaterThanOrEqualTo(String value) {
            addCriterion("time15 >=", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15LessThan(String value) {
            addCriterion("time15 <", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15LessThanOrEqualTo(String value) {
            addCriterion("time15 <=", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15Like(String value) {
            addCriterion("time15 like", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15NotLike(String value) {
            addCriterion("time15 not like", value, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15In(List<String> values) {
            addCriterion("time15 in", values, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15NotIn(List<String> values) {
            addCriterion("time15 not in", values, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15Between(String value1, String value2) {
            addCriterion("time15 between", value1, value2, "time15");
            return (Criteria) this;
        }

        public Criteria andTime15NotBetween(String value1, String value2) {
            addCriterion("time15 not between", value1, value2, "time15");
            return (Criteria) this;
        }

        public Criteria andTime16IsNull() {
            addCriterion("time16 is null");
            return (Criteria) this;
        }

        public Criteria andTime16IsNotNull() {
            addCriterion("time16 is not null");
            return (Criteria) this;
        }

        public Criteria andTime16EqualTo(String value) {
            addCriterion("time16 =", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16NotEqualTo(String value) {
            addCriterion("time16 <>", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16GreaterThan(String value) {
            addCriterion("time16 >", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16GreaterThanOrEqualTo(String value) {
            addCriterion("time16 >=", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16LessThan(String value) {
            addCriterion("time16 <", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16LessThanOrEqualTo(String value) {
            addCriterion("time16 <=", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16Like(String value) {
            addCriterion("time16 like", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16NotLike(String value) {
            addCriterion("time16 not like", value, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16In(List<String> values) {
            addCriterion("time16 in", values, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16NotIn(List<String> values) {
            addCriterion("time16 not in", values, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16Between(String value1, String value2) {
            addCriterion("time16 between", value1, value2, "time16");
            return (Criteria) this;
        }

        public Criteria andTime16NotBetween(String value1, String value2) {
            addCriterion("time16 not between", value1, value2, "time16");
            return (Criteria) this;
        }

        public Criteria andTime17IsNull() {
            addCriterion("time17 is null");
            return (Criteria) this;
        }

        public Criteria andTime17IsNotNull() {
            addCriterion("time17 is not null");
            return (Criteria) this;
        }

        public Criteria andTime17EqualTo(String value) {
            addCriterion("time17 =", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17NotEqualTo(String value) {
            addCriterion("time17 <>", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17GreaterThan(String value) {
            addCriterion("time17 >", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17GreaterThanOrEqualTo(String value) {
            addCriterion("time17 >=", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17LessThan(String value) {
            addCriterion("time17 <", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17LessThanOrEqualTo(String value) {
            addCriterion("time17 <=", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17Like(String value) {
            addCriterion("time17 like", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17NotLike(String value) {
            addCriterion("time17 not like", value, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17In(List<String> values) {
            addCriterion("time17 in", values, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17NotIn(List<String> values) {
            addCriterion("time17 not in", values, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17Between(String value1, String value2) {
            addCriterion("time17 between", value1, value2, "time17");
            return (Criteria) this;
        }

        public Criteria andTime17NotBetween(String value1, String value2) {
            addCriterion("time17 not between", value1, value2, "time17");
            return (Criteria) this;
        }

        public Criteria andTime18IsNull() {
            addCriterion("time18 is null");
            return (Criteria) this;
        }

        public Criteria andTime18IsNotNull() {
            addCriterion("time18 is not null");
            return (Criteria) this;
        }

        public Criteria andTime18EqualTo(String value) {
            addCriterion("time18 =", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18NotEqualTo(String value) {
            addCriterion("time18 <>", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18GreaterThan(String value) {
            addCriterion("time18 >", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18GreaterThanOrEqualTo(String value) {
            addCriterion("time18 >=", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18LessThan(String value) {
            addCriterion("time18 <", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18LessThanOrEqualTo(String value) {
            addCriterion("time18 <=", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18Like(String value) {
            addCriterion("time18 like", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18NotLike(String value) {
            addCriterion("time18 not like", value, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18In(List<String> values) {
            addCriterion("time18 in", values, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18NotIn(List<String> values) {
            addCriterion("time18 not in", values, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18Between(String value1, String value2) {
            addCriterion("time18 between", value1, value2, "time18");
            return (Criteria) this;
        }

        public Criteria andTime18NotBetween(String value1, String value2) {
            addCriterion("time18 not between", value1, value2, "time18");
            return (Criteria) this;
        }

        public Criteria andTime19IsNull() {
            addCriterion("time19 is null");
            return (Criteria) this;
        }

        public Criteria andTime19IsNotNull() {
            addCriterion("time19 is not null");
            return (Criteria) this;
        }

        public Criteria andTime19EqualTo(String value) {
            addCriterion("time19 =", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19NotEqualTo(String value) {
            addCriterion("time19 <>", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19GreaterThan(String value) {
            addCriterion("time19 >", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19GreaterThanOrEqualTo(String value) {
            addCriterion("time19 >=", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19LessThan(String value) {
            addCriterion("time19 <", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19LessThanOrEqualTo(String value) {
            addCriterion("time19 <=", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19Like(String value) {
            addCriterion("time19 like", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19NotLike(String value) {
            addCriterion("time19 not like", value, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19In(List<String> values) {
            addCriterion("time19 in", values, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19NotIn(List<String> values) {
            addCriterion("time19 not in", values, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19Between(String value1, String value2) {
            addCriterion("time19 between", value1, value2, "time19");
            return (Criteria) this;
        }

        public Criteria andTime19NotBetween(String value1, String value2) {
            addCriterion("time19 not between", value1, value2, "time19");
            return (Criteria) this;
        }

        public Criteria andTime20IsNull() {
            addCriterion("time20 is null");
            return (Criteria) this;
        }

        public Criteria andTime20IsNotNull() {
            addCriterion("time20 is not null");
            return (Criteria) this;
        }

        public Criteria andTime20EqualTo(String value) {
            addCriterion("time20 =", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20NotEqualTo(String value) {
            addCriterion("time20 <>", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20GreaterThan(String value) {
            addCriterion("time20 >", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20GreaterThanOrEqualTo(String value) {
            addCriterion("time20 >=", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20LessThan(String value) {
            addCriterion("time20 <", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20LessThanOrEqualTo(String value) {
            addCriterion("time20 <=", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20Like(String value) {
            addCriterion("time20 like", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20NotLike(String value) {
            addCriterion("time20 not like", value, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20In(List<String> values) {
            addCriterion("time20 in", values, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20NotIn(List<String> values) {
            addCriterion("time20 not in", values, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20Between(String value1, String value2) {
            addCriterion("time20 between", value1, value2, "time20");
            return (Criteria) this;
        }

        public Criteria andTime20NotBetween(String value1, String value2) {
            addCriterion("time20 not between", value1, value2, "time20");
            return (Criteria) this;
        }

        public Criteria andTime21IsNull() {
            addCriterion("time21 is null");
            return (Criteria) this;
        }

        public Criteria andTime21IsNotNull() {
            addCriterion("time21 is not null");
            return (Criteria) this;
        }

        public Criteria andTime21EqualTo(String value) {
            addCriterion("time21 =", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21NotEqualTo(String value) {
            addCriterion("time21 <>", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21GreaterThan(String value) {
            addCriterion("time21 >", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21GreaterThanOrEqualTo(String value) {
            addCriterion("time21 >=", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21LessThan(String value) {
            addCriterion("time21 <", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21LessThanOrEqualTo(String value) {
            addCriterion("time21 <=", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21Like(String value) {
            addCriterion("time21 like", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21NotLike(String value) {
            addCriterion("time21 not like", value, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21In(List<String> values) {
            addCriterion("time21 in", values, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21NotIn(List<String> values) {
            addCriterion("time21 not in", values, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21Between(String value1, String value2) {
            addCriterion("time21 between", value1, value2, "time21");
            return (Criteria) this;
        }

        public Criteria andTime21NotBetween(String value1, String value2) {
            addCriterion("time21 not between", value1, value2, "time21");
            return (Criteria) this;
        }

        public Criteria andTime22IsNull() {
            addCriterion("time22 is null");
            return (Criteria) this;
        }

        public Criteria andTime22IsNotNull() {
            addCriterion("time22 is not null");
            return (Criteria) this;
        }

        public Criteria andTime22EqualTo(String value) {
            addCriterion("time22 =", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22NotEqualTo(String value) {
            addCriterion("time22 <>", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22GreaterThan(String value) {
            addCriterion("time22 >", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22GreaterThanOrEqualTo(String value) {
            addCriterion("time22 >=", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22LessThan(String value) {
            addCriterion("time22 <", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22LessThanOrEqualTo(String value) {
            addCriterion("time22 <=", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22Like(String value) {
            addCriterion("time22 like", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22NotLike(String value) {
            addCriterion("time22 not like", value, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22In(List<String> values) {
            addCriterion("time22 in", values, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22NotIn(List<String> values) {
            addCriterion("time22 not in", values, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22Between(String value1, String value2) {
            addCriterion("time22 between", value1, value2, "time22");
            return (Criteria) this;
        }

        public Criteria andTime22NotBetween(String value1, String value2) {
            addCriterion("time22 not between", value1, value2, "time22");
            return (Criteria) this;
        }

        public Criteria andTime23IsNull() {
            addCriterion("time23 is null");
            return (Criteria) this;
        }

        public Criteria andTime23IsNotNull() {
            addCriterion("time23 is not null");
            return (Criteria) this;
        }

        public Criteria andTime23EqualTo(String value) {
            addCriterion("time23 =", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23NotEqualTo(String value) {
            addCriterion("time23 <>", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23GreaterThan(String value) {
            addCriterion("time23 >", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23GreaterThanOrEqualTo(String value) {
            addCriterion("time23 >=", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23LessThan(String value) {
            addCriterion("time23 <", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23LessThanOrEqualTo(String value) {
            addCriterion("time23 <=", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23Like(String value) {
            addCriterion("time23 like", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23NotLike(String value) {
            addCriterion("time23 not like", value, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23In(List<String> values) {
            addCriterion("time23 in", values, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23NotIn(List<String> values) {
            addCriterion("time23 not in", values, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23Between(String value1, String value2) {
            addCriterion("time23 between", value1, value2, "time23");
            return (Criteria) this;
        }

        public Criteria andTime23NotBetween(String value1, String value2) {
            addCriterion("time23 not between", value1, value2, "time23");
            return (Criteria) this;
        }

        public Criteria andTime24IsNull() {
            addCriterion("time24 is null");
            return (Criteria) this;
        }

        public Criteria andTime24IsNotNull() {
            addCriterion("time24 is not null");
            return (Criteria) this;
        }

        public Criteria andTime24EqualTo(String value) {
            addCriterion("time24 =", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24NotEqualTo(String value) {
            addCriterion("time24 <>", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24GreaterThan(String value) {
            addCriterion("time24 >", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24GreaterThanOrEqualTo(String value) {
            addCriterion("time24 >=", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24LessThan(String value) {
            addCriterion("time24 <", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24LessThanOrEqualTo(String value) {
            addCriterion("time24 <=", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24Like(String value) {
            addCriterion("time24 like", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24NotLike(String value) {
            addCriterion("time24 not like", value, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24In(List<String> values) {
            addCriterion("time24 in", values, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24NotIn(List<String> values) {
            addCriterion("time24 not in", values, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24Between(String value1, String value2) {
            addCriterion("time24 between", value1, value2, "time24");
            return (Criteria) this;
        }

        public Criteria andTime24NotBetween(String value1, String value2) {
            addCriterion("time24 not between", value1, value2, "time24");
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