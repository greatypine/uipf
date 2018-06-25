package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSysTimerJobconfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSysTimerJobconfigExample() {
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

        public Criteria andWorkflowIdIsNull() {
            addCriterion("workflow_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdIsNotNull() {
            addCriterion("workflow_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdEqualTo(Integer value) {
            addCriterion("workflow_id =", value, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdNotEqualTo(Integer value) {
            addCriterion("workflow_id <>", value, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdGreaterThan(Integer value) {
            addCriterion("workflow_id >", value, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("workflow_id >=", value, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdLessThan(Integer value) {
            addCriterion("workflow_id <", value, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdLessThanOrEqualTo(Integer value) {
            addCriterion("workflow_id <=", value, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdIn(List<Integer> values) {
            addCriterion("workflow_id in", values, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdNotIn(List<Integer> values) {
            addCriterion("workflow_id not in", values, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdBetween(Integer value1, Integer value2) {
            addCriterion("workflow_id between", value1, value2, "workflowId");
            return (Criteria) this;
        }

        public Criteria andWorkflowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("workflow_id not between", value1, value2, "workflowId");
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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

        public Criteria andModelIsNull() {
            addCriterion("model is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("model is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(Integer value) {
            addCriterion("model =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(Integer value) {
            addCriterion("model <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(Integer value) {
            addCriterion("model >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(Integer value) {
            addCriterion("model >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(Integer value) {
            addCriterion("model <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(Integer value) {
            addCriterion("model <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<Integer> values) {
            addCriterion("model in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<Integer> values) {
            addCriterion("model not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(Integer value1, Integer value2) {
            addCriterion("model between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(Integer value1, Integer value2) {
            addCriterion("model not between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andNodeTypeIsNull() {
            addCriterion("node_type is null");
            return (Criteria) this;
        }

        public Criteria andNodeTypeIsNotNull() {
            addCriterion("node_type is not null");
            return (Criteria) this;
        }

        public Criteria andNodeTypeEqualTo(Integer value) {
            addCriterion("node_type =", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeNotEqualTo(Integer value) {
            addCriterion("node_type <>", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeGreaterThan(Integer value) {
            addCriterion("node_type >", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("node_type >=", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeLessThan(Integer value) {
            addCriterion("node_type <", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("node_type <=", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeIn(List<Integer> values) {
            addCriterion("node_type in", values, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeNotIn(List<Integer> values) {
            addCriterion("node_type not in", values, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeBetween(Integer value1, Integer value2) {
            addCriterion("node_type between", value1, value2, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("node_type not between", value1, value2, "nodeType");
            return (Criteria) this;
        }

        public Criteria andExcuteIdIsNull() {
            addCriterion("excute_id is null");
            return (Criteria) this;
        }

        public Criteria andExcuteIdIsNotNull() {
            addCriterion("excute_id is not null");
            return (Criteria) this;
        }

        public Criteria andExcuteIdEqualTo(Integer value) {
            addCriterion("excute_id =", value, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdNotEqualTo(Integer value) {
            addCriterion("excute_id <>", value, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdGreaterThan(Integer value) {
            addCriterion("excute_id >", value, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("excute_id >=", value, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdLessThan(Integer value) {
            addCriterion("excute_id <", value, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdLessThanOrEqualTo(Integer value) {
            addCriterion("excute_id <=", value, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdIn(List<Integer> values) {
            addCriterion("excute_id in", values, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdNotIn(List<Integer> values) {
            addCriterion("excute_id not in", values, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdBetween(Integer value1, Integer value2) {
            addCriterion("excute_id between", value1, value2, "excuteId");
            return (Criteria) this;
        }

        public Criteria andExcuteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("excute_id not between", value1, value2, "excuteId");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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

        public Criteria andXIsNull() {
            addCriterion("x is null");
            return (Criteria) this;
        }

        public Criteria andXIsNotNull() {
            addCriterion("x is not null");
            return (Criteria) this;
        }

        public Criteria andXEqualTo(Integer value) {
            addCriterion("x =", value, "x");
            return (Criteria) this;
        }

        public Criteria andXNotEqualTo(Integer value) {
            addCriterion("x <>", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThan(Integer value) {
            addCriterion("x >", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThanOrEqualTo(Integer value) {
            addCriterion("x >=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThan(Integer value) {
            addCriterion("x <", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThanOrEqualTo(Integer value) {
            addCriterion("x <=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXIn(List<Integer> values) {
            addCriterion("x in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXNotIn(List<Integer> values) {
            addCriterion("x not in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXBetween(Integer value1, Integer value2) {
            addCriterion("x between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andXNotBetween(Integer value1, Integer value2) {
            addCriterion("x not between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andYIsNull() {
            addCriterion("y is null");
            return (Criteria) this;
        }

        public Criteria andYIsNotNull() {
            addCriterion("y is not null");
            return (Criteria) this;
        }

        public Criteria andYEqualTo(Integer value) {
            addCriterion("y =", value, "y");
            return (Criteria) this;
        }

        public Criteria andYNotEqualTo(Integer value) {
            addCriterion("y <>", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThan(Integer value) {
            addCriterion("y >", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThanOrEqualTo(Integer value) {
            addCriterion("y >=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThan(Integer value) {
            addCriterion("y <", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThanOrEqualTo(Integer value) {
            addCriterion("y <=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYIn(List<Integer> values) {
            addCriterion("y in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYNotIn(List<Integer> values) {
            addCriterion("y not in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYBetween(Integer value1, Integer value2) {
            addCriterion("y between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andYNotBetween(Integer value1, Integer value2) {
            addCriterion("y not between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andWidthIsNull() {
            addCriterion("width is null");
            return (Criteria) this;
        }

        public Criteria andWidthIsNotNull() {
            addCriterion("width is not null");
            return (Criteria) this;
        }

        public Criteria andWidthEqualTo(Integer value) {
            addCriterion("width =", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotEqualTo(Integer value) {
            addCriterion("width <>", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThan(Integer value) {
            addCriterion("width >", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("width >=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThan(Integer value) {
            addCriterion("width <", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthLessThanOrEqualTo(Integer value) {
            addCriterion("width <=", value, "width");
            return (Criteria) this;
        }

        public Criteria andWidthIn(List<Integer> values) {
            addCriterion("width in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotIn(List<Integer> values) {
            addCriterion("width not in", values, "width");
            return (Criteria) this;
        }

        public Criteria andWidthBetween(Integer value1, Integer value2) {
            addCriterion("width between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("width not between", value1, value2, "width");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(Integer value) {
            addCriterion("height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(Integer value) {
            addCriterion("height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(Integer value) {
            addCriterion("height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(Integer value) {
            addCriterion("height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(Integer value) {
            addCriterion("height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<Integer> values) {
            addCriterion("height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<Integer> values) {
            addCriterion("height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(Integer value1, Integer value2) {
            addCriterion("height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(Integer value1, Integer value2) {
            addCriterion("height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andTextIsNull() {
            addCriterion("text is null");
            return (Criteria) this;
        }

        public Criteria andTextIsNotNull() {
            addCriterion("text is not null");
            return (Criteria) this;
        }

        public Criteria andTextEqualTo(String value) {
            addCriterion("text =", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotEqualTo(String value) {
            addCriterion("text <>", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThan(String value) {
            addCriterion("text >", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThanOrEqualTo(String value) {
            addCriterion("text >=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThan(String value) {
            addCriterion("text <", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThanOrEqualTo(String value) {
            addCriterion("text <=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLike(String value) {
            addCriterion("text like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotLike(String value) {
            addCriterion("text not like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextIn(List<String> values) {
            addCriterion("text in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotIn(List<String> values) {
            addCriterion("text not in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextBetween(String value1, String value2) {
            addCriterion("text between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotBetween(String value1, String value2) {
            addCriterion("text not between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andProps1IsNull() {
            addCriterion("props1 is null");
            return (Criteria) this;
        }

        public Criteria andProps1IsNotNull() {
            addCriterion("props1 is not null");
            return (Criteria) this;
        }

        public Criteria andProps1EqualTo(String value) {
            addCriterion("props1 =", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1NotEqualTo(String value) {
            addCriterion("props1 <>", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1GreaterThan(String value) {
            addCriterion("props1 >", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1GreaterThanOrEqualTo(String value) {
            addCriterion("props1 >=", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1LessThan(String value) {
            addCriterion("props1 <", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1LessThanOrEqualTo(String value) {
            addCriterion("props1 <=", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1Like(String value) {
            addCriterion("props1 like", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1NotLike(String value) {
            addCriterion("props1 not like", value, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1In(List<String> values) {
            addCriterion("props1 in", values, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1NotIn(List<String> values) {
            addCriterion("props1 not in", values, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1Between(String value1, String value2) {
            addCriterion("props1 between", value1, value2, "props1");
            return (Criteria) this;
        }

        public Criteria andProps1NotBetween(String value1, String value2) {
            addCriterion("props1 not between", value1, value2, "props1");
            return (Criteria) this;
        }

        public Criteria andProps2IsNull() {
            addCriterion("props2 is null");
            return (Criteria) this;
        }

        public Criteria andProps2IsNotNull() {
            addCriterion("props2 is not null");
            return (Criteria) this;
        }

        public Criteria andProps2EqualTo(String value) {
            addCriterion("props2 =", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2NotEqualTo(String value) {
            addCriterion("props2 <>", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2GreaterThan(String value) {
            addCriterion("props2 >", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2GreaterThanOrEqualTo(String value) {
            addCriterion("props2 >=", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2LessThan(String value) {
            addCriterion("props2 <", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2LessThanOrEqualTo(String value) {
            addCriterion("props2 <=", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2Like(String value) {
            addCriterion("props2 like", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2NotLike(String value) {
            addCriterion("props2 not like", value, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2In(List<String> values) {
            addCriterion("props2 in", values, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2NotIn(List<String> values) {
            addCriterion("props2 not in", values, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2Between(String value1, String value2) {
            addCriterion("props2 between", value1, value2, "props2");
            return (Criteria) this;
        }

        public Criteria andProps2NotBetween(String value1, String value2) {
            addCriterion("props2 not between", value1, value2, "props2");
            return (Criteria) this;
        }

        public Criteria andProps3IsNull() {
            addCriterion("props3 is null");
            return (Criteria) this;
        }

        public Criteria andProps3IsNotNull() {
            addCriterion("props3 is not null");
            return (Criteria) this;
        }

        public Criteria andProps3EqualTo(String value) {
            addCriterion("props3 =", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3NotEqualTo(String value) {
            addCriterion("props3 <>", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3GreaterThan(String value) {
            addCriterion("props3 >", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3GreaterThanOrEqualTo(String value) {
            addCriterion("props3 >=", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3LessThan(String value) {
            addCriterion("props3 <", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3LessThanOrEqualTo(String value) {
            addCriterion("props3 <=", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3Like(String value) {
            addCriterion("props3 like", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3NotLike(String value) {
            addCriterion("props3 not like", value, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3In(List<String> values) {
            addCriterion("props3 in", values, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3NotIn(List<String> values) {
            addCriterion("props3 not in", values, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3Between(String value1, String value2) {
            addCriterion("props3 between", value1, value2, "props3");
            return (Criteria) this;
        }

        public Criteria andProps3NotBetween(String value1, String value2) {
            addCriterion("props3 not between", value1, value2, "props3");
            return (Criteria) this;
        }

        public Criteria andStartIsNull() {
            addCriterion("start is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("start is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(String value) {
            addCriterion("start =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(String value) {
            addCriterion("start <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(String value) {
            addCriterion("start >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(String value) {
            addCriterion("start >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(String value) {
            addCriterion("start <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(String value) {
            addCriterion("start <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLike(String value) {
            addCriterion("start like", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotLike(String value) {
            addCriterion("start not like", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<String> values) {
            addCriterion("start in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<String> values) {
            addCriterion("start not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(String value1, String value2) {
            addCriterion("start between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(String value1, String value2) {
            addCriterion("start not between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andEndIsNull() {
            addCriterion("end is null");
            return (Criteria) this;
        }

        public Criteria andEndIsNotNull() {
            addCriterion("end is not null");
            return (Criteria) this;
        }

        public Criteria andEndEqualTo(String value) {
            addCriterion("end =", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotEqualTo(String value) {
            addCriterion("end <>", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThan(String value) {
            addCriterion("end >", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndGreaterThanOrEqualTo(String value) {
            addCriterion("end >=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThan(String value) {
            addCriterion("end <", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLessThanOrEqualTo(String value) {
            addCriterion("end <=", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndLike(String value) {
            addCriterion("end like", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotLike(String value) {
            addCriterion("end not like", value, "end");
            return (Criteria) this;
        }

        public Criteria andEndIn(List<String> values) {
            addCriterion("end in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotIn(List<String> values) {
            addCriterion("end not in", values, "end");
            return (Criteria) this;
        }

        public Criteria andEndBetween(String value1, String value2) {
            addCriterion("end between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andEndNotBetween(String value1, String value2) {
            addCriterion("end not between", value1, value2, "end");
            return (Criteria) this;
        }

        public Criteria andPathDotsIsNull() {
            addCriterion("path_dots is null");
            return (Criteria) this;
        }

        public Criteria andPathDotsIsNotNull() {
            addCriterion("path_dots is not null");
            return (Criteria) this;
        }

        public Criteria andPathDotsEqualTo(String value) {
            addCriterion("path_dots =", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsNotEqualTo(String value) {
            addCriterion("path_dots <>", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsGreaterThan(String value) {
            addCriterion("path_dots >", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsGreaterThanOrEqualTo(String value) {
            addCriterion("path_dots >=", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsLessThan(String value) {
            addCriterion("path_dots <", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsLessThanOrEqualTo(String value) {
            addCriterion("path_dots <=", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsLike(String value) {
            addCriterion("path_dots like", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsNotLike(String value) {
            addCriterion("path_dots not like", value, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsIn(List<String> values) {
            addCriterion("path_dots in", values, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsNotIn(List<String> values) {
            addCriterion("path_dots not in", values, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsBetween(String value1, String value2) {
            addCriterion("path_dots between", value1, value2, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathDotsNotBetween(String value1, String value2) {
            addCriterion("path_dots not between", value1, value2, "pathDots");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextIsNull() {
            addCriterion("path_props_text is null");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextIsNotNull() {
            addCriterion("path_props_text is not null");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextEqualTo(String value) {
            addCriterion("path_props_text =", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextNotEqualTo(String value) {
            addCriterion("path_props_text <>", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextGreaterThan(String value) {
            addCriterion("path_props_text >", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextGreaterThanOrEqualTo(String value) {
            addCriterion("path_props_text >=", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextLessThan(String value) {
            addCriterion("path_props_text <", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextLessThanOrEqualTo(String value) {
            addCriterion("path_props_text <=", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextLike(String value) {
            addCriterion("path_props_text like", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextNotLike(String value) {
            addCriterion("path_props_text not like", value, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextIn(List<String> values) {
            addCriterion("path_props_text in", values, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextNotIn(List<String> values) {
            addCriterion("path_props_text not in", values, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextBetween(String value1, String value2) {
            addCriterion("path_props_text between", value1, value2, "pathPropsText");
            return (Criteria) this;
        }

        public Criteria andPathPropsTextNotBetween(String value1, String value2) {
            addCriterion("path_props_text not between", value1, value2, "pathPropsText");
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