package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSysTimerJobSqlInsertUpdateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSysTimerJobSqlInsertUpdateExample() {
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

        public Criteria andDbCodeIsNull() {
            addCriterion("db_code is null");
            return (Criteria) this;
        }

        public Criteria andDbCodeIsNotNull() {
            addCriterion("db_code is not null");
            return (Criteria) this;
        }

        public Criteria andDbCodeEqualTo(String value) {
            addCriterion("db_code =", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotEqualTo(String value) {
            addCriterion("db_code <>", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeGreaterThan(String value) {
            addCriterion("db_code >", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeGreaterThanOrEqualTo(String value) {
            addCriterion("db_code >=", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeLessThan(String value) {
            addCriterion("db_code <", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeLessThanOrEqualTo(String value) {
            addCriterion("db_code <=", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeLike(String value) {
            addCriterion("db_code like", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotLike(String value) {
            addCriterion("db_code not like", value, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeIn(List<String> values) {
            addCriterion("db_code in", values, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotIn(List<String> values) {
            addCriterion("db_code not in", values, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeBetween(String value1, String value2) {
            addCriterion("db_code between", value1, value2, "dbCode");
            return (Criteria) this;
        }

        public Criteria andDbCodeNotBetween(String value1, String value2) {
            addCriterion("db_code not between", value1, value2, "dbCode");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andIsbatchIsNull() {
            addCriterion("isbatch is null");
            return (Criteria) this;
        }

        public Criteria andIsbatchIsNotNull() {
            addCriterion("isbatch is not null");
            return (Criteria) this;
        }

        public Criteria andIsbatchEqualTo(Integer value) {
            addCriterion("isbatch =", value, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchNotEqualTo(Integer value) {
            addCriterion("isbatch <>", value, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchGreaterThan(Integer value) {
            addCriterion("isbatch >", value, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchGreaterThanOrEqualTo(Integer value) {
            addCriterion("isbatch >=", value, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchLessThan(Integer value) {
            addCriterion("isbatch <", value, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchLessThanOrEqualTo(Integer value) {
            addCriterion("isbatch <=", value, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchIn(List<Integer> values) {
            addCriterion("isbatch in", values, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchNotIn(List<Integer> values) {
            addCriterion("isbatch not in", values, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchBetween(Integer value1, Integer value2) {
            addCriterion("isbatch between", value1, value2, "isbatch");
            return (Criteria) this;
        }

        public Criteria andIsbatchNotBetween(Integer value1, Integer value2) {
            addCriterion("isbatch not between", value1, value2, "isbatch");
            return (Criteria) this;
        }

        public Criteria andBatchsizeIsNull() {
            addCriterion("batchSize is null");
            return (Criteria) this;
        }

        public Criteria andBatchsizeIsNotNull() {
            addCriterion("batchSize is not null");
            return (Criteria) this;
        }

        public Criteria andBatchsizeEqualTo(Integer value) {
            addCriterion("batchSize =", value, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeNotEqualTo(Integer value) {
            addCriterion("batchSize <>", value, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeGreaterThan(Integer value) {
            addCriterion("batchSize >", value, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("batchSize >=", value, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeLessThan(Integer value) {
            addCriterion("batchSize <", value, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeLessThanOrEqualTo(Integer value) {
            addCriterion("batchSize <=", value, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeIn(List<Integer> values) {
            addCriterion("batchSize in", values, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeNotIn(List<Integer> values) {
            addCriterion("batchSize not in", values, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeBetween(Integer value1, Integer value2) {
            addCriterion("batchSize between", value1, value2, "batchsize");
            return (Criteria) this;
        }

        public Criteria andBatchsizeNotBetween(Integer value1, Integer value2) {
            addCriterion("batchSize not between", value1, value2, "batchsize");
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

        public Criteria andUpdateColumnsIsNull() {
            addCriterion("update_columns is null");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsIsNotNull() {
            addCriterion("update_columns is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsEqualTo(Integer value) {
            addCriterion("update_columns =", value, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsNotEqualTo(Integer value) {
            addCriterion("update_columns <>", value, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsGreaterThan(Integer value) {
            addCriterion("update_columns >", value, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_columns >=", value, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsLessThan(Integer value) {
            addCriterion("update_columns <", value, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsLessThanOrEqualTo(Integer value) {
            addCriterion("update_columns <=", value, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsIn(List<Integer> values) {
            addCriterion("update_columns in", values, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsNotIn(List<Integer> values) {
            addCriterion("update_columns not in", values, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsBetween(Integer value1, Integer value2) {
            addCriterion("update_columns between", value1, value2, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateColumnsNotBetween(Integer value1, Integer value2) {
            addCriterion("update_columns not between", value1, value2, "updateColumns");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysIsNull() {
            addCriterion("update_keys is null");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysIsNotNull() {
            addCriterion("update_keys is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysEqualTo(Integer value) {
            addCriterion("update_keys =", value, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysNotEqualTo(Integer value) {
            addCriterion("update_keys <>", value, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysGreaterThan(Integer value) {
            addCriterion("update_keys >", value, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_keys >=", value, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysLessThan(Integer value) {
            addCriterion("update_keys <", value, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysLessThanOrEqualTo(Integer value) {
            addCriterion("update_keys <=", value, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysIn(List<Integer> values) {
            addCriterion("update_keys in", values, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysNotIn(List<Integer> values) {
            addCriterion("update_keys not in", values, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysBetween(Integer value1, Integer value2) {
            addCriterion("update_keys between", value1, value2, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andUpdateKeysNotBetween(Integer value1, Integer value2) {
            addCriterion("update_keys not between", value1, value2, "updateKeys");
            return (Criteria) this;
        }

        public Criteria andInputParamsIsNull() {
            addCriterion("input_params is null");
            return (Criteria) this;
        }

        public Criteria andInputParamsIsNotNull() {
            addCriterion("input_params is not null");
            return (Criteria) this;
        }

        public Criteria andInputParamsEqualTo(Integer value) {
            addCriterion("input_params =", value, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsNotEqualTo(Integer value) {
            addCriterion("input_params <>", value, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsGreaterThan(Integer value) {
            addCriterion("input_params >", value, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsGreaterThanOrEqualTo(Integer value) {
            addCriterion("input_params >=", value, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsLessThan(Integer value) {
            addCriterion("input_params <", value, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsLessThanOrEqualTo(Integer value) {
            addCriterion("input_params <=", value, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsIn(List<Integer> values) {
            addCriterion("input_params in", values, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsNotIn(List<Integer> values) {
            addCriterion("input_params not in", values, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsBetween(Integer value1, Integer value2) {
            addCriterion("input_params between", value1, value2, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputParamsNotBetween(Integer value1, Integer value2) {
            addCriterion("input_params not between", value1, value2, "inputParams");
            return (Criteria) this;
        }

        public Criteria andInputTypeIsNull() {
            addCriterion("input_type is null");
            return (Criteria) this;
        }

        public Criteria andInputTypeIsNotNull() {
            addCriterion("input_type is not null");
            return (Criteria) this;
        }

        public Criteria andInputTypeEqualTo(Integer value) {
            addCriterion("input_type =", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotEqualTo(Integer value) {
            addCriterion("input_type <>", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThan(Integer value) {
            addCriterion("input_type >", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("input_type >=", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThan(Integer value) {
            addCriterion("input_type <", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeLessThanOrEqualTo(Integer value) {
            addCriterion("input_type <=", value, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeIn(List<Integer> values) {
            addCriterion("input_type in", values, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotIn(List<Integer> values) {
            addCriterion("input_type not in", values, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeBetween(Integer value1, Integer value2) {
            addCriterion("input_type between", value1, value2, "inputType");
            return (Criteria) this;
        }

        public Criteria andInputTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("input_type not between", value1, value2, "inputType");
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