package com.gasq.bdp.logn.model;

import java.util.ArrayList;
import java.util.List;

public class FSkuclassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FSkuclassExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
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

        public Criteria andContentNameIsNull() {
            addCriterion("content_name is null");
            return (Criteria) this;
        }

        public Criteria andContentNameIsNotNull() {
            addCriterion("content_name is not null");
            return (Criteria) this;
        }

        public Criteria andContentNameEqualTo(String value) {
            addCriterion("content_name =", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotEqualTo(String value) {
            addCriterion("content_name <>", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameGreaterThan(String value) {
            addCriterion("content_name >", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameGreaterThanOrEqualTo(String value) {
            addCriterion("content_name >=", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameLessThan(String value) {
            addCriterion("content_name <", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameLessThanOrEqualTo(String value) {
            addCriterion("content_name <=", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameLike(String value) {
            addCriterion("content_name like", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotLike(String value) {
            addCriterion("content_name not like", value, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameIn(List<String> values) {
            addCriterion("content_name in", values, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotIn(List<String> values) {
            addCriterion("content_name not in", values, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameBetween(String value1, String value2) {
            addCriterion("content_name between", value1, value2, "contentName");
            return (Criteria) this;
        }

        public Criteria andContentNameNotBetween(String value1, String value2) {
            addCriterion("content_name not between", value1, value2, "contentName");
            return (Criteria) this;
        }

        public Criteria andEshopIdIsNull() {
            addCriterion("eshop_id is null");
            return (Criteria) this;
        }

        public Criteria andEshopIdIsNotNull() {
            addCriterion("eshop_id is not null");
            return (Criteria) this;
        }

        public Criteria andEshopIdEqualTo(String value) {
            addCriterion("eshop_id =", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdNotEqualTo(String value) {
            addCriterion("eshop_id <>", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdGreaterThan(String value) {
            addCriterion("eshop_id >", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdGreaterThanOrEqualTo(String value) {
            addCriterion("eshop_id >=", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdLessThan(String value) {
            addCriterion("eshop_id <", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdLessThanOrEqualTo(String value) {
            addCriterion("eshop_id <=", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdLike(String value) {
            addCriterion("eshop_id like", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdNotLike(String value) {
            addCriterion("eshop_id not like", value, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdIn(List<String> values) {
            addCriterion("eshop_id in", values, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdNotIn(List<String> values) {
            addCriterion("eshop_id not in", values, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdBetween(String value1, String value2) {
            addCriterion("eshop_id between", value1, value2, "eshopId");
            return (Criteria) this;
        }

        public Criteria andEshopIdNotBetween(String value1, String value2) {
            addCriterion("eshop_id not between", value1, value2, "eshopId");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameIsNull() {
            addCriterion("tag_level1_name is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameIsNotNull() {
            addCriterion("tag_level1_name is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameEqualTo(String value) {
            addCriterion("tag_level1_name =", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameNotEqualTo(String value) {
            addCriterion("tag_level1_name <>", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameGreaterThan(String value) {
            addCriterion("tag_level1_name >", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level1_name >=", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameLessThan(String value) {
            addCriterion("tag_level1_name <", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameLessThanOrEqualTo(String value) {
            addCriterion("tag_level1_name <=", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameLike(String value) {
            addCriterion("tag_level1_name like", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameNotLike(String value) {
            addCriterion("tag_level1_name not like", value, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameIn(List<String> values) {
            addCriterion("tag_level1_name in", values, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameNotIn(List<String> values) {
            addCriterion("tag_level1_name not in", values, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameBetween(String value1, String value2) {
            addCriterion("tag_level1_name between", value1, value2, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1NameNotBetween(String value1, String value2) {
            addCriterion("tag_level1_name not between", value1, value2, "tagLevel1Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdIsNull() {
            addCriterion("tag_level1_id is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdIsNotNull() {
            addCriterion("tag_level1_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdEqualTo(String value) {
            addCriterion("tag_level1_id =", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdNotEqualTo(String value) {
            addCriterion("tag_level1_id <>", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdGreaterThan(String value) {
            addCriterion("tag_level1_id >", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level1_id >=", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdLessThan(String value) {
            addCriterion("tag_level1_id <", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdLessThanOrEqualTo(String value) {
            addCriterion("tag_level1_id <=", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdLike(String value) {
            addCriterion("tag_level1_id like", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdNotLike(String value) {
            addCriterion("tag_level1_id not like", value, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdIn(List<String> values) {
            addCriterion("tag_level1_id in", values, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdNotIn(List<String> values) {
            addCriterion("tag_level1_id not in", values, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdBetween(String value1, String value2) {
            addCriterion("tag_level1_id between", value1, value2, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel1IdNotBetween(String value1, String value2) {
            addCriterion("tag_level1_id not between", value1, value2, "tagLevel1Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameIsNull() {
            addCriterion("tag_level2_name is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameIsNotNull() {
            addCriterion("tag_level2_name is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameEqualTo(String value) {
            addCriterion("tag_level2_name =", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameNotEqualTo(String value) {
            addCriterion("tag_level2_name <>", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameGreaterThan(String value) {
            addCriterion("tag_level2_name >", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level2_name >=", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameLessThan(String value) {
            addCriterion("tag_level2_name <", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameLessThanOrEqualTo(String value) {
            addCriterion("tag_level2_name <=", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameLike(String value) {
            addCriterion("tag_level2_name like", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameNotLike(String value) {
            addCriterion("tag_level2_name not like", value, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameIn(List<String> values) {
            addCriterion("tag_level2_name in", values, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameNotIn(List<String> values) {
            addCriterion("tag_level2_name not in", values, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameBetween(String value1, String value2) {
            addCriterion("tag_level2_name between", value1, value2, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2NameNotBetween(String value1, String value2) {
            addCriterion("tag_level2_name not between", value1, value2, "tagLevel2Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdIsNull() {
            addCriterion("tag_level2_id is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdIsNotNull() {
            addCriterion("tag_level2_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdEqualTo(String value) {
            addCriterion("tag_level2_id =", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdNotEqualTo(String value) {
            addCriterion("tag_level2_id <>", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdGreaterThan(String value) {
            addCriterion("tag_level2_id >", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level2_id >=", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdLessThan(String value) {
            addCriterion("tag_level2_id <", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdLessThanOrEqualTo(String value) {
            addCriterion("tag_level2_id <=", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdLike(String value) {
            addCriterion("tag_level2_id like", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdNotLike(String value) {
            addCriterion("tag_level2_id not like", value, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdIn(List<String> values) {
            addCriterion("tag_level2_id in", values, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdNotIn(List<String> values) {
            addCriterion("tag_level2_id not in", values, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdBetween(String value1, String value2) {
            addCriterion("tag_level2_id between", value1, value2, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel2IdNotBetween(String value1, String value2) {
            addCriterion("tag_level2_id not between", value1, value2, "tagLevel2Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameIsNull() {
            addCriterion("tag_level3_name is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameIsNotNull() {
            addCriterion("tag_level3_name is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameEqualTo(String value) {
            addCriterion("tag_level3_name =", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameNotEqualTo(String value) {
            addCriterion("tag_level3_name <>", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameGreaterThan(String value) {
            addCriterion("tag_level3_name >", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level3_name >=", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameLessThan(String value) {
            addCriterion("tag_level3_name <", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameLessThanOrEqualTo(String value) {
            addCriterion("tag_level3_name <=", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameLike(String value) {
            addCriterion("tag_level3_name like", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameNotLike(String value) {
            addCriterion("tag_level3_name not like", value, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameIn(List<String> values) {
            addCriterion("tag_level3_name in", values, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameNotIn(List<String> values) {
            addCriterion("tag_level3_name not in", values, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameBetween(String value1, String value2) {
            addCriterion("tag_level3_name between", value1, value2, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3NameNotBetween(String value1, String value2) {
            addCriterion("tag_level3_name not between", value1, value2, "tagLevel3Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdIsNull() {
            addCriterion("tag_level3_id is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdIsNotNull() {
            addCriterion("tag_level3_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdEqualTo(String value) {
            addCriterion("tag_level3_id =", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdNotEqualTo(String value) {
            addCriterion("tag_level3_id <>", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdGreaterThan(String value) {
            addCriterion("tag_level3_id >", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level3_id >=", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdLessThan(String value) {
            addCriterion("tag_level3_id <", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdLessThanOrEqualTo(String value) {
            addCriterion("tag_level3_id <=", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdLike(String value) {
            addCriterion("tag_level3_id like", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdNotLike(String value) {
            addCriterion("tag_level3_id not like", value, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdIn(List<String> values) {
            addCriterion("tag_level3_id in", values, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdNotIn(List<String> values) {
            addCriterion("tag_level3_id not in", values, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdBetween(String value1, String value2) {
            addCriterion("tag_level3_id between", value1, value2, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel3IdNotBetween(String value1, String value2) {
            addCriterion("tag_level3_id not between", value1, value2, "tagLevel3Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameIsNull() {
            addCriterion("tag_level4_name is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameIsNotNull() {
            addCriterion("tag_level4_name is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameEqualTo(String value) {
            addCriterion("tag_level4_name =", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameNotEqualTo(String value) {
            addCriterion("tag_level4_name <>", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameGreaterThan(String value) {
            addCriterion("tag_level4_name >", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level4_name >=", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameLessThan(String value) {
            addCriterion("tag_level4_name <", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameLessThanOrEqualTo(String value) {
            addCriterion("tag_level4_name <=", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameLike(String value) {
            addCriterion("tag_level4_name like", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameNotLike(String value) {
            addCriterion("tag_level4_name not like", value, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameIn(List<String> values) {
            addCriterion("tag_level4_name in", values, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameNotIn(List<String> values) {
            addCriterion("tag_level4_name not in", values, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameBetween(String value1, String value2) {
            addCriterion("tag_level4_name between", value1, value2, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4NameNotBetween(String value1, String value2) {
            addCriterion("tag_level4_name not between", value1, value2, "tagLevel4Name");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdIsNull() {
            addCriterion("tag_level4_id is null");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdIsNotNull() {
            addCriterion("tag_level4_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdEqualTo(String value) {
            addCriterion("tag_level4_id =", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdNotEqualTo(String value) {
            addCriterion("tag_level4_id <>", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdGreaterThan(String value) {
            addCriterion("tag_level4_id >", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdGreaterThanOrEqualTo(String value) {
            addCriterion("tag_level4_id >=", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdLessThan(String value) {
            addCriterion("tag_level4_id <", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdLessThanOrEqualTo(String value) {
            addCriterion("tag_level4_id <=", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdLike(String value) {
            addCriterion("tag_level4_id like", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdNotLike(String value) {
            addCriterion("tag_level4_id not like", value, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdIn(List<String> values) {
            addCriterion("tag_level4_id in", values, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdNotIn(List<String> values) {
            addCriterion("tag_level4_id not in", values, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdBetween(String value1, String value2) {
            addCriterion("tag_level4_id between", value1, value2, "tagLevel4Id");
            return (Criteria) this;
        }

        public Criteria andTagLevel4IdNotBetween(String value1, String value2) {
            addCriterion("tag_level4_id not between", value1, value2, "tagLevel4Id");
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