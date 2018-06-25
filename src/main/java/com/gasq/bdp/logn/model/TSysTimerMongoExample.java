package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSysTimerMongoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSysTimerMongoExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andHostPortsIsNull() {
            addCriterion("host_ports is null");
            return (Criteria) this;
        }

        public Criteria andHostPortsIsNotNull() {
            addCriterion("host_ports is not null");
            return (Criteria) this;
        }

        public Criteria andHostPortsEqualTo(String value) {
            addCriterion("host_ports =", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsNotEqualTo(String value) {
            addCriterion("host_ports <>", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsGreaterThan(String value) {
            addCriterion("host_ports >", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsGreaterThanOrEqualTo(String value) {
            addCriterion("host_ports >=", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsLessThan(String value) {
            addCriterion("host_ports <", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsLessThanOrEqualTo(String value) {
            addCriterion("host_ports <=", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsLike(String value) {
            addCriterion("host_ports like", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsNotLike(String value) {
            addCriterion("host_ports not like", value, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsIn(List<String> values) {
            addCriterion("host_ports in", values, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsNotIn(List<String> values) {
            addCriterion("host_ports not in", values, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsBetween(String value1, String value2) {
            addCriterion("host_ports between", value1, value2, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andHostPortsNotBetween(String value1, String value2) {
            addCriterion("host_ports not between", value1, value2, "hostPorts");
            return (Criteria) this;
        }

        public Criteria andMaxConnectIsNull() {
            addCriterion("max_connect is null");
            return (Criteria) this;
        }

        public Criteria andMaxConnectIsNotNull() {
            addCriterion("max_connect is not null");
            return (Criteria) this;
        }

        public Criteria andMaxConnectEqualTo(Integer value) {
            addCriterion("max_connect =", value, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectNotEqualTo(Integer value) {
            addCriterion("max_connect <>", value, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectGreaterThan(Integer value) {
            addCriterion("max_connect >", value, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_connect >=", value, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectLessThan(Integer value) {
            addCriterion("max_connect <", value, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectLessThanOrEqualTo(Integer value) {
            addCriterion("max_connect <=", value, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectIn(List<Integer> values) {
            addCriterion("max_connect in", values, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectNotIn(List<Integer> values) {
            addCriterion("max_connect not in", values, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectBetween(Integer value1, Integer value2) {
            addCriterion("max_connect between", value1, value2, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxConnectNotBetween(Integer value1, Integer value2) {
            addCriterion("max_connect not between", value1, value2, "maxConnect");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadIsNull() {
            addCriterion("max_wait_thread is null");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadIsNotNull() {
            addCriterion("max_wait_thread is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadEqualTo(Integer value) {
            addCriterion("max_wait_thread =", value, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadNotEqualTo(Integer value) {
            addCriterion("max_wait_thread <>", value, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadGreaterThan(Integer value) {
            addCriterion("max_wait_thread >", value, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_wait_thread >=", value, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadLessThan(Integer value) {
            addCriterion("max_wait_thread <", value, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadLessThanOrEqualTo(Integer value) {
            addCriterion("max_wait_thread <=", value, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadIn(List<Integer> values) {
            addCriterion("max_wait_thread in", values, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadNotIn(List<Integer> values) {
            addCriterion("max_wait_thread not in", values, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadBetween(Integer value1, Integer value2) {
            addCriterion("max_wait_thread between", value1, value2, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxWaitThreadNotBetween(Integer value1, Integer value2) {
            addCriterion("max_wait_thread not between", value1, value2, "maxWaitThread");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutIsNull() {
            addCriterion("max_time_out is null");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutIsNotNull() {
            addCriterion("max_time_out is not null");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutEqualTo(Integer value) {
            addCriterion("max_time_out =", value, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutNotEqualTo(Integer value) {
            addCriterion("max_time_out <>", value, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutGreaterThan(Integer value) {
            addCriterion("max_time_out >", value, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_time_out >=", value, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutLessThan(Integer value) {
            addCriterion("max_time_out <", value, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutLessThanOrEqualTo(Integer value) {
            addCriterion("max_time_out <=", value, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutIn(List<Integer> values) {
            addCriterion("max_time_out in", values, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutNotIn(List<Integer> values) {
            addCriterion("max_time_out not in", values, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutBetween(Integer value1, Integer value2) {
            addCriterion("max_time_out between", value1, value2, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxTimeOutNotBetween(Integer value1, Integer value2) {
            addCriterion("max_time_out not between", value1, value2, "maxTimeOut");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeIsNull() {
            addCriterion("max_wait_time is null");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeIsNotNull() {
            addCriterion("max_wait_time is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeEqualTo(Integer value) {
            addCriterion("max_wait_time =", value, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeNotEqualTo(Integer value) {
            addCriterion("max_wait_time <>", value, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeGreaterThan(Integer value) {
            addCriterion("max_wait_time >", value, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_wait_time >=", value, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeLessThan(Integer value) {
            addCriterion("max_wait_time <", value, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeLessThanOrEqualTo(Integer value) {
            addCriterion("max_wait_time <=", value, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeIn(List<Integer> values) {
            addCriterion("max_wait_time in", values, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeNotIn(List<Integer> values) {
            addCriterion("max_wait_time not in", values, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeBetween(Integer value1, Integer value2) {
            addCriterion("max_wait_time between", value1, value2, "maxWaitTime");
            return (Criteria) this;
        }

        public Criteria andMaxWaitTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("max_wait_time not between", value1, value2, "maxWaitTime");
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

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseIsNull() {
            addCriterion("mg_database is null");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseIsNotNull() {
            addCriterion("mg_database is not null");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseEqualTo(String value) {
            addCriterion("mg_database =", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseNotEqualTo(String value) {
            addCriterion("mg_database <>", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseGreaterThan(String value) {
            addCriterion("mg_database >", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseGreaterThanOrEqualTo(String value) {
            addCriterion("mg_database >=", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseLessThan(String value) {
            addCriterion("mg_database <", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseLessThanOrEqualTo(String value) {
            addCriterion("mg_database <=", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseLike(String value) {
            addCriterion("mg_database like", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseNotLike(String value) {
            addCriterion("mg_database not like", value, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseIn(List<String> values) {
            addCriterion("mg_database in", values, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseNotIn(List<String> values) {
            addCriterion("mg_database not in", values, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseBetween(String value1, String value2) {
            addCriterion("mg_database between", value1, value2, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgDatabaseNotBetween(String value1, String value2) {
            addCriterion("mg_database not between", value1, value2, "mgDatabase");
            return (Criteria) this;
        }

        public Criteria andMgCollectionIsNull() {
            addCriterion("mg_collection is null");
            return (Criteria) this;
        }

        public Criteria andMgCollectionIsNotNull() {
            addCriterion("mg_collection is not null");
            return (Criteria) this;
        }

        public Criteria andMgCollectionEqualTo(String value) {
            addCriterion("mg_collection =", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionNotEqualTo(String value) {
            addCriterion("mg_collection <>", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionGreaterThan(String value) {
            addCriterion("mg_collection >", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionGreaterThanOrEqualTo(String value) {
            addCriterion("mg_collection >=", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionLessThan(String value) {
            addCriterion("mg_collection <", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionLessThanOrEqualTo(String value) {
            addCriterion("mg_collection <=", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionLike(String value) {
            addCriterion("mg_collection like", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionNotLike(String value) {
            addCriterion("mg_collection not like", value, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionIn(List<String> values) {
            addCriterion("mg_collection in", values, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionNotIn(List<String> values) {
            addCriterion("mg_collection not in", values, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionBetween(String value1, String value2) {
            addCriterion("mg_collection between", value1, value2, "mgCollection");
            return (Criteria) this;
        }

        public Criteria andMgCollectionNotBetween(String value1, String value2) {
            addCriterion("mg_collection not between", value1, value2, "mgCollection");
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

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andGroupnameIsNull() {
            addCriterion("groupName is null");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNotNull() {
            addCriterion("groupName is not null");
            return (Criteria) this;
        }

        public Criteria andGroupnameEqualTo(String value) {
            addCriterion("groupName =", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotEqualTo(String value) {
            addCriterion("groupName <>", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThan(String value) {
            addCriterion("groupName >", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("groupName >=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThan(String value) {
            addCriterion("groupName <", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThanOrEqualTo(String value) {
            addCriterion("groupName <=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLike(String value) {
            addCriterion("groupName like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotLike(String value) {
            addCriterion("groupName not like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameIn(List<String> values) {
            addCriterion("groupName in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotIn(List<String> values) {
            addCriterion("groupName not in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameBetween(String value1, String value2) {
            addCriterion("groupName between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotBetween(String value1, String value2) {
            addCriterion("groupName not between", value1, value2, "groupname");
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