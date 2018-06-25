package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSysTimerJobElasticsearchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSysTimerJobElasticsearchExample() {
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

        public Criteria andServerIpIsNull() {
            addCriterion("server_ip is null");
            return (Criteria) this;
        }

        public Criteria andServerIpIsNotNull() {
            addCriterion("server_ip is not null");
            return (Criteria) this;
        }

        public Criteria andServerIpEqualTo(String value) {
            addCriterion("server_ip =", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotEqualTo(String value) {
            addCriterion("server_ip <>", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpGreaterThan(String value) {
            addCriterion("server_ip >", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpGreaterThanOrEqualTo(String value) {
            addCriterion("server_ip >=", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLessThan(String value) {
            addCriterion("server_ip <", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLessThanOrEqualTo(String value) {
            addCriterion("server_ip <=", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLike(String value) {
            addCriterion("server_ip like", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotLike(String value) {
            addCriterion("server_ip not like", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpIn(List<String> values) {
            addCriterion("server_ip in", values, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotIn(List<String> values) {
            addCriterion("server_ip not in", values, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpBetween(String value1, String value2) {
            addCriterion("server_ip between", value1, value2, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotBetween(String value1, String value2) {
            addCriterion("server_ip not between", value1, value2, "serverIp");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(Integer value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(Integer value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(Integer value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(Integer value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(Integer value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<Integer> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<Integer> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(Integer value1, Integer value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(Integer value1, Integer value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andClusterNameIsNull() {
            addCriterion("cluster_name is null");
            return (Criteria) this;
        }

        public Criteria andClusterNameIsNotNull() {
            addCriterion("cluster_name is not null");
            return (Criteria) this;
        }

        public Criteria andClusterNameEqualTo(String value) {
            addCriterion("cluster_name =", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotEqualTo(String value) {
            addCriterion("cluster_name <>", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameGreaterThan(String value) {
            addCriterion("cluster_name >", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameGreaterThanOrEqualTo(String value) {
            addCriterion("cluster_name >=", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameLessThan(String value) {
            addCriterion("cluster_name <", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameLessThanOrEqualTo(String value) {
            addCriterion("cluster_name <=", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameLike(String value) {
            addCriterion("cluster_name like", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotLike(String value) {
            addCriterion("cluster_name not like", value, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameIn(List<String> values) {
            addCriterion("cluster_name in", values, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotIn(List<String> values) {
            addCriterion("cluster_name not in", values, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameBetween(String value1, String value2) {
            addCriterion("cluster_name between", value1, value2, "clusterName");
            return (Criteria) this;
        }

        public Criteria andClusterNameNotBetween(String value1, String value2) {
            addCriterion("cluster_name not between", value1, value2, "clusterName");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteIsNull() {
            addCriterion("scoroll_minute is null");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteIsNotNull() {
            addCriterion("scoroll_minute is not null");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteEqualTo(Integer value) {
            addCriterion("scoroll_minute =", value, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteNotEqualTo(Integer value) {
            addCriterion("scoroll_minute <>", value, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteGreaterThan(Integer value) {
            addCriterion("scoroll_minute >", value, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteGreaterThanOrEqualTo(Integer value) {
            addCriterion("scoroll_minute >=", value, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteLessThan(Integer value) {
            addCriterion("scoroll_minute <", value, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteLessThanOrEqualTo(Integer value) {
            addCriterion("scoroll_minute <=", value, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteIn(List<Integer> values) {
            addCriterion("scoroll_minute in", values, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteNotIn(List<Integer> values) {
            addCriterion("scoroll_minute not in", values, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteBetween(Integer value1, Integer value2) {
            addCriterion("scoroll_minute between", value1, value2, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andScorollMinuteNotBetween(Integer value1, Integer value2) {
            addCriterion("scoroll_minute not between", value1, value2, "scorollMinute");
            return (Criteria) this;
        }

        public Criteria andQueryIndexIsNull() {
            addCriterion("query_index is null");
            return (Criteria) this;
        }

        public Criteria andQueryIndexIsNotNull() {
            addCriterion("query_index is not null");
            return (Criteria) this;
        }

        public Criteria andQueryIndexEqualTo(String value) {
            addCriterion("query_index =", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexNotEqualTo(String value) {
            addCriterion("query_index <>", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexGreaterThan(String value) {
            addCriterion("query_index >", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexGreaterThanOrEqualTo(String value) {
            addCriterion("query_index >=", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexLessThan(String value) {
            addCriterion("query_index <", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexLessThanOrEqualTo(String value) {
            addCriterion("query_index <=", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexLike(String value) {
            addCriterion("query_index like", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexNotLike(String value) {
            addCriterion("query_index not like", value, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexIn(List<String> values) {
            addCriterion("query_index in", values, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexNotIn(List<String> values) {
            addCriterion("query_index not in", values, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexBetween(String value1, String value2) {
            addCriterion("query_index between", value1, value2, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryIndexNotBetween(String value1, String value2) {
            addCriterion("query_index not between", value1, value2, "queryIndex");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIsNull() {
            addCriterion("query_type is null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIsNotNull() {
            addCriterion("query_type is not null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeEqualTo(String value) {
            addCriterion("query_type =", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotEqualTo(String value) {
            addCriterion("query_type <>", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeGreaterThan(String value) {
            addCriterion("query_type >", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("query_type >=", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLessThan(String value) {
            addCriterion("query_type <", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLessThanOrEqualTo(String value) {
            addCriterion("query_type <=", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLike(String value) {
            addCriterion("query_type like", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotLike(String value) {
            addCriterion("query_type not like", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIn(List<String> values) {
            addCriterion("query_type in", values, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotIn(List<String> values) {
            addCriterion("query_type not in", values, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeBetween(String value1, String value2) {
            addCriterion("query_type between", value1, value2, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotBetween(String value1, String value2) {
            addCriterion("query_type not between", value1, value2, "queryType");
            return (Criteria) this;
        }

        public Criteria andOutputfileIsNull() {
            addCriterion("outputFile is null");
            return (Criteria) this;
        }

        public Criteria andOutputfileIsNotNull() {
            addCriterion("outputFile is not null");
            return (Criteria) this;
        }

        public Criteria andOutputfileEqualTo(String value) {
            addCriterion("outputFile =", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileNotEqualTo(String value) {
            addCriterion("outputFile <>", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileGreaterThan(String value) {
            addCriterion("outputFile >", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileGreaterThanOrEqualTo(String value) {
            addCriterion("outputFile >=", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileLessThan(String value) {
            addCriterion("outputFile <", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileLessThanOrEqualTo(String value) {
            addCriterion("outputFile <=", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileLike(String value) {
            addCriterion("outputFile like", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileNotLike(String value) {
            addCriterion("outputFile not like", value, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileIn(List<String> values) {
            addCriterion("outputFile in", values, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileNotIn(List<String> values) {
            addCriterion("outputFile not in", values, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileBetween(String value1, String value2) {
            addCriterion("outputFile between", value1, value2, "outputfile");
            return (Criteria) this;
        }

        public Criteria andOutputfileNotBetween(String value1, String value2) {
            addCriterion("outputFile not between", value1, value2, "outputfile");
            return (Criteria) this;
        }

        public Criteria andDiffDayIsNull() {
            addCriterion("diff_day is null");
            return (Criteria) this;
        }

        public Criteria andDiffDayIsNotNull() {
            addCriterion("diff_day is not null");
            return (Criteria) this;
        }

        public Criteria andDiffDayEqualTo(Integer value) {
            addCriterion("diff_day =", value, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayNotEqualTo(Integer value) {
            addCriterion("diff_day <>", value, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayGreaterThan(Integer value) {
            addCriterion("diff_day >", value, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("diff_day >=", value, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayLessThan(Integer value) {
            addCriterion("diff_day <", value, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayLessThanOrEqualTo(Integer value) {
            addCriterion("diff_day <=", value, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayIn(List<Integer> values) {
            addCriterion("diff_day in", values, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayNotIn(List<Integer> values) {
            addCriterion("diff_day not in", values, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayBetween(Integer value1, Integer value2) {
            addCriterion("diff_day between", value1, value2, "diffDay");
            return (Criteria) this;
        }

        public Criteria andDiffDayNotBetween(Integer value1, Integer value2) {
            addCriterion("diff_day not between", value1, value2, "diffDay");
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