package com.amoxu.entity;

import java.util.ArrayList;
import java.util.List;

public class PermissionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public PermissionExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andFriendIsNull() {
            addCriterion("friend is null");
            return (Criteria) this;
        }

        public Criteria andFriendIsNotNull() {
            addCriterion("friend is not null");
            return (Criteria) this;
        }

        public Criteria andFriendEqualTo(Integer value) {
            addCriterion("friend =", value, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendNotEqualTo(Integer value) {
            addCriterion("friend <>", value, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendGreaterThan(Integer value) {
            addCriterion("friend >", value, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendGreaterThanOrEqualTo(Integer value) {
            addCriterion("friend >=", value, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendLessThan(Integer value) {
            addCriterion("friend <", value, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendLessThanOrEqualTo(Integer value) {
            addCriterion("friend <=", value, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendIn(List<Integer> values) {
            addCriterion("friend in", values, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendNotIn(List<Integer> values) {
            addCriterion("friend not in", values, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendBetween(Integer value1, Integer value2) {
            addCriterion("friend between", value1, value2, "friend");
            return (Criteria) this;
        }

        public Criteria andFriendNotBetween(Integer value1, Integer value2) {
            addCriterion("friend not between", value1, value2, "friend");
            return (Criteria) this;
        }

        public Criteria andEventsIsNull() {
            addCriterion("events is null");
            return (Criteria) this;
        }

        public Criteria andEventsIsNotNull() {
            addCriterion("events is not null");
            return (Criteria) this;
        }

        public Criteria andEventsEqualTo(Integer value) {
            addCriterion("events =", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotEqualTo(Integer value) {
            addCriterion("events <>", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsGreaterThan(Integer value) {
            addCriterion("events >", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsGreaterThanOrEqualTo(Integer value) {
            addCriterion("events >=", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLessThan(Integer value) {
            addCriterion("events <", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsLessThanOrEqualTo(Integer value) {
            addCriterion("events <=", value, "events");
            return (Criteria) this;
        }

        public Criteria andEventsIn(List<Integer> values) {
            addCriterion("events in", values, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotIn(List<Integer> values) {
            addCriterion("events not in", values, "events");
            return (Criteria) this;
        }

        public Criteria andEventsBetween(Integer value1, Integer value2) {
            addCriterion("events between", value1, value2, "events");
            return (Criteria) this;
        }

        public Criteria andEventsNotBetween(Integer value1, Integer value2) {
            addCriterion("events not between", value1, value2, "events");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("message is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("message is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(Integer value) {
            addCriterion("message =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(Integer value) {
            addCriterion("message <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(Integer value) {
            addCriterion("message >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(Integer value) {
            addCriterion("message >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(Integer value) {
            addCriterion("message <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(Integer value) {
            addCriterion("message <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<Integer> values) {
            addCriterion("message in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<Integer> values) {
            addCriterion("message not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(Integer value1, Integer value2) {
            addCriterion("message between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(Integer value1, Integer value2) {
            addCriterion("message not between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andNearbyIsNull() {
            addCriterion("nearby is null");
            return (Criteria) this;
        }

        public Criteria andNearbyIsNotNull() {
            addCriterion("nearby is not null");
            return (Criteria) this;
        }

        public Criteria andNearbyEqualTo(Integer value) {
            addCriterion("nearby =", value, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyNotEqualTo(Integer value) {
            addCriterion("nearby <>", value, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyGreaterThan(Integer value) {
            addCriterion("nearby >", value, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyGreaterThanOrEqualTo(Integer value) {
            addCriterion("nearby >=", value, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyLessThan(Integer value) {
            addCriterion("nearby <", value, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyLessThanOrEqualTo(Integer value) {
            addCriterion("nearby <=", value, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyIn(List<Integer> values) {
            addCriterion("nearby in", values, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyNotIn(List<Integer> values) {
            addCriterion("nearby not in", values, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyBetween(Integer value1, Integer value2) {
            addCriterion("nearby between", value1, value2, "nearby");
            return (Criteria) this;
        }

        public Criteria andNearbyNotBetween(Integer value1, Integer value2) {
            addCriterion("nearby not between", value1, value2, "nearby");
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