package com.yayanovel.entity;

import java.util.ArrayList;
import java.util.List;

public class NovelExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public NovelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
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
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
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

        public Criteria andNovelIdIsNull() {
            addCriterion("NOVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andNovelIdIsNotNull() {
            addCriterion("NOVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNovelIdEqualTo(Long value) {
            addCriterion("NOVEL_ID =", value, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdNotEqualTo(Long value) {
            addCriterion("NOVEL_ID <>", value, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdGreaterThan(Long value) {
            addCriterion("NOVEL_ID >", value, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("NOVEL_ID >=", value, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdLessThan(Long value) {
            addCriterion("NOVEL_ID <", value, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdLessThanOrEqualTo(Long value) {
            addCriterion("NOVEL_ID <=", value, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdIn(List<Long> values) {
            addCriterion("NOVEL_ID in", values, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdNotIn(List<Long> values) {
            addCriterion("NOVEL_ID not in", values, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdBetween(Long value1, Long value2) {
            addCriterion("NOVEL_ID between", value1, value2, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelIdNotBetween(Long value1, Long value2) {
            addCriterion("NOVEL_ID not between", value1, value2, "novelId");
            return (Criteria) this;
        }

        public Criteria andNovelUidIsNull() {
            addCriterion("NOVEL_UID is null");
            return (Criteria) this;
        }

        public Criteria andNovelUidIsNotNull() {
            addCriterion("NOVEL_UID is not null");
            return (Criteria) this;
        }

        public Criteria andNovelUidEqualTo(String value) {
            addCriterion("NOVEL_UID =", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidNotEqualTo(String value) {
            addCriterion("NOVEL_UID <>", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidGreaterThan(String value) {
            addCriterion("NOVEL_UID >", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidGreaterThanOrEqualTo(String value) {
            addCriterion("NOVEL_UID >=", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidLessThan(String value) {
            addCriterion("NOVEL_UID <", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidLessThanOrEqualTo(String value) {
            addCriterion("NOVEL_UID <=", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidLike(String value) {
            addCriterion("NOVEL_UID like", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidNotLike(String value) {
            addCriterion("NOVEL_UID not like", value, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidIn(List<String> values) {
            addCriterion("NOVEL_UID in", values, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidNotIn(List<String> values) {
            addCriterion("NOVEL_UID not in", values, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidBetween(String value1, String value2) {
            addCriterion("NOVEL_UID between", value1, value2, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelUidNotBetween(String value1, String value2) {
            addCriterion("NOVEL_UID not between", value1, value2, "novelUid");
            return (Criteria) this;
        }

        public Criteria andNovelNameIsNull() {
            addCriterion("NOVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNovelNameIsNotNull() {
            addCriterion("NOVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNovelNameEqualTo(String value) {
            addCriterion("NOVEL_NAME =", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameNotEqualTo(String value) {
            addCriterion("NOVEL_NAME <>", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameGreaterThan(String value) {
            addCriterion("NOVEL_NAME >", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameGreaterThanOrEqualTo(String value) {
            addCriterion("NOVEL_NAME >=", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameLessThan(String value) {
            addCriterion("NOVEL_NAME <", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameLessThanOrEqualTo(String value) {
            addCriterion("NOVEL_NAME <=", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameLike(String value) {
            addCriterion("NOVEL_NAME like", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameNotLike(String value) {
            addCriterion("NOVEL_NAME not like", value, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameIn(List<String> values) {
            addCriterion("NOVEL_NAME in", values, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameNotIn(List<String> values) {
            addCriterion("NOVEL_NAME not in", values, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameBetween(String value1, String value2) {
            addCriterion("NOVEL_NAME between", value1, value2, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelNameNotBetween(String value1, String value2) {
            addCriterion("NOVEL_NAME not between", value1, value2, "novelName");
            return (Criteria) this;
        }

        public Criteria andNovelAddrIsNull() {
            addCriterion("NOVEL_ADDR is null");
            return (Criteria) this;
        }

        public Criteria andNovelAddrIsNotNull() {
            addCriterion("NOVEL_ADDR is not null");
            return (Criteria) this;
        }

        public Criteria andNovelAddrEqualTo(String value) {
            addCriterion("NOVEL_ADDR =", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrNotEqualTo(String value) {
            addCriterion("NOVEL_ADDR <>", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrGreaterThan(String value) {
            addCriterion("NOVEL_ADDR >", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrGreaterThanOrEqualTo(String value) {
            addCriterion("NOVEL_ADDR >=", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrLessThan(String value) {
            addCriterion("NOVEL_ADDR <", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrLessThanOrEqualTo(String value) {
            addCriterion("NOVEL_ADDR <=", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrLike(String value) {
            addCriterion("NOVEL_ADDR like", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrNotLike(String value) {
            addCriterion("NOVEL_ADDR not like", value, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrIn(List<String> values) {
            addCriterion("NOVEL_ADDR in", values, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrNotIn(List<String> values) {
            addCriterion("NOVEL_ADDR not in", values, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrBetween(String value1, String value2) {
            addCriterion("NOVEL_ADDR between", value1, value2, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andNovelAddrNotBetween(String value1, String value2) {
            addCriterion("NOVEL_ADDR not between", value1, value2, "novelAddr");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNull() {
            addCriterion("READ_NUM is null");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNotNull() {
            addCriterion("READ_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andReadNumEqualTo(Long value) {
            addCriterion("READ_NUM =", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotEqualTo(Long value) {
            addCriterion("READ_NUM <>", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThan(Long value) {
            addCriterion("READ_NUM >", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThanOrEqualTo(Long value) {
            addCriterion("READ_NUM >=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThan(Long value) {
            addCriterion("READ_NUM <", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThanOrEqualTo(Long value) {
            addCriterion("READ_NUM <=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumIn(List<Long> values) {
            addCriterion("READ_NUM in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotIn(List<Long> values) {
            addCriterion("READ_NUM not in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumBetween(Long value1, Long value2) {
            addCriterion("READ_NUM between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotBetween(Long value1, Long value2) {
            addCriterion("READ_NUM not between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNull() {
            addCriterion("COLLECTION_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNotNull() {
            addCriterion("COLLECTION_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumEqualTo(Long value) {
            addCriterion("COLLECTION_NUM =", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotEqualTo(Long value) {
            addCriterion("COLLECTION_NUM <>", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThan(Long value) {
            addCriterion("COLLECTION_NUM >", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThanOrEqualTo(Long value) {
            addCriterion("COLLECTION_NUM >=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThan(Long value) {
            addCriterion("COLLECTION_NUM <", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThanOrEqualTo(Long value) {
            addCriterion("COLLECTION_NUM <=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIn(List<Long> values) {
            addCriterion("COLLECTION_NUM in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotIn(List<Long> values) {
            addCriterion("COLLECTION_NUM not in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumBetween(Long value1, Long value2) {
            addCriterion("COLLECTION_NUM between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotBetween(Long value1, Long value2) {
            addCriterion("COLLECTION_NUM not between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("CATEGORY =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("CATEGORY <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("CATEGORY >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("CATEGORY <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("CATEGORY like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("CATEGORY not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("CATEGORY in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("CATEGORY not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("CATEGORY between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("CATEGORY not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andImgAddrIsNull() {
            addCriterion("IMG_ADDR is null");
            return (Criteria) this;
        }

        public Criteria andImgAddrIsNotNull() {
            addCriterion("IMG_ADDR is not null");
            return (Criteria) this;
        }

        public Criteria andImgAddrEqualTo(String value) {
            addCriterion("IMG_ADDR =", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrNotEqualTo(String value) {
            addCriterion("IMG_ADDR <>", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrGreaterThan(String value) {
            addCriterion("IMG_ADDR >", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrGreaterThanOrEqualTo(String value) {
            addCriterion("IMG_ADDR >=", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrLessThan(String value) {
            addCriterion("IMG_ADDR <", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrLessThanOrEqualTo(String value) {
            addCriterion("IMG_ADDR <=", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrLike(String value) {
            addCriterion("IMG_ADDR like", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrNotLike(String value) {
            addCriterion("IMG_ADDR not like", value, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrIn(List<String> values) {
            addCriterion("IMG_ADDR in", values, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrNotIn(List<String> values) {
            addCriterion("IMG_ADDR not in", values, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrBetween(String value1, String value2) {
            addCriterion("IMG_ADDR between", value1, value2, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andImgAddrNotBetween(String value1, String value2) {
            addCriterion("IMG_ADDR not between", value1, value2, "imgAddr");
            return (Criteria) this;
        }

        public Criteria andIsEndIsNull() {
            addCriterion("IS_END is null");
            return (Criteria) this;
        }

        public Criteria andIsEndIsNotNull() {
            addCriterion("IS_END is not null");
            return (Criteria) this;
        }

        public Criteria andIsEndEqualTo(String value) {
            addCriterion("IS_END =", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotEqualTo(String value) {
            addCriterion("IS_END <>", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndGreaterThan(String value) {
            addCriterion("IS_END >", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndGreaterThanOrEqualTo(String value) {
            addCriterion("IS_END >=", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndLessThan(String value) {
            addCriterion("IS_END <", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndLessThanOrEqualTo(String value) {
            addCriterion("IS_END <=", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndLike(String value) {
            addCriterion("IS_END like", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotLike(String value) {
            addCriterion("IS_END not like", value, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndIn(List<String> values) {
            addCriterion("IS_END in", values, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotIn(List<String> values) {
            addCriterion("IS_END not in", values, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndBetween(String value1, String value2) {
            addCriterion("IS_END between", value1, value2, "isEnd");
            return (Criteria) this;
        }

        public Criteria andIsEndNotBetween(String value1, String value2) {
            addCriterion("IS_END not between", value1, value2, "isEnd");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("KEYWORDS is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("KEYWORDS is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("KEYWORDS =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("KEYWORDS <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("KEYWORDS >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("KEYWORDS >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("KEYWORDS <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("KEYWORDS <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("KEYWORDS like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("KEYWORDS not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("KEYWORDS in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("KEYWORDS not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("KEYWORDS between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("KEYWORDS not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andText1IsNull() {
            addCriterion("TEXT1 is null");
            return (Criteria) this;
        }

        public Criteria andText1IsNotNull() {
            addCriterion("TEXT1 is not null");
            return (Criteria) this;
        }

        public Criteria andText1EqualTo(String value) {
            addCriterion("TEXT1 =", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1NotEqualTo(String value) {
            addCriterion("TEXT1 <>", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1GreaterThan(String value) {
            addCriterion("TEXT1 >", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1GreaterThanOrEqualTo(String value) {
            addCriterion("TEXT1 >=", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1LessThan(String value) {
            addCriterion("TEXT1 <", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1LessThanOrEqualTo(String value) {
            addCriterion("TEXT1 <=", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1Like(String value) {
            addCriterion("TEXT1 like", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1NotLike(String value) {
            addCriterion("TEXT1 not like", value, "text1");
            return (Criteria) this;
        }

        public Criteria andText1In(List<String> values) {
            addCriterion("TEXT1 in", values, "text1");
            return (Criteria) this;
        }

        public Criteria andText1NotIn(List<String> values) {
            addCriterion("TEXT1 not in", values, "text1");
            return (Criteria) this;
        }

        public Criteria andText1Between(String value1, String value2) {
            addCriterion("TEXT1 between", value1, value2, "text1");
            return (Criteria) this;
        }

        public Criteria andText1NotBetween(String value1, String value2) {
            addCriterion("TEXT1 not between", value1, value2, "text1");
            return (Criteria) this;
        }

        public Criteria andText2IsNull() {
            addCriterion("TEXT2 is null");
            return (Criteria) this;
        }

        public Criteria andText2IsNotNull() {
            addCriterion("TEXT2 is not null");
            return (Criteria) this;
        }

        public Criteria andText2EqualTo(String value) {
            addCriterion("TEXT2 =", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotEqualTo(String value) {
            addCriterion("TEXT2 <>", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2GreaterThan(String value) {
            addCriterion("TEXT2 >", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2GreaterThanOrEqualTo(String value) {
            addCriterion("TEXT2 >=", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2LessThan(String value) {
            addCriterion("TEXT2 <", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2LessThanOrEqualTo(String value) {
            addCriterion("TEXT2 <=", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2Like(String value) {
            addCriterion("TEXT2 like", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotLike(String value) {
            addCriterion("TEXT2 not like", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2In(List<String> values) {
            addCriterion("TEXT2 in", values, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotIn(List<String> values) {
            addCriterion("TEXT2 not in", values, "text2");
            return (Criteria) this;
        }

        public Criteria andText2Between(String value1, String value2) {
            addCriterion("TEXT2 between", value1, value2, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotBetween(String value1, String value2) {
            addCriterion("TEXT2 not between", value1, value2, "text2");
            return (Criteria) this;
        }

        public Criteria andChapterNumIsNull() {
            addCriterion("CHAPTER_NUM is null");
            return (Criteria) this;
        }

        public Criteria andChapterNumIsNotNull() {
            addCriterion("CHAPTER_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andChapterNumEqualTo(Long value) {
            addCriterion("CHAPTER_NUM =", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumNotEqualTo(Long value) {
            addCriterion("CHAPTER_NUM <>", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumGreaterThan(Long value) {
            addCriterion("CHAPTER_NUM >", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumGreaterThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_NUM >=", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumLessThan(Long value) {
            addCriterion("CHAPTER_NUM <", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumLessThanOrEqualTo(Long value) {
            addCriterion("CHAPTER_NUM <=", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumIn(List<Long> values) {
            addCriterion("CHAPTER_NUM in", values, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumNotIn(List<Long> values) {
            addCriterion("CHAPTER_NUM not in", values, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_NUM between", value1, value2, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumNotBetween(Long value1, Long value2) {
            addCriterion("CHAPTER_NUM not between", value1, value2, "chapterNum");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table novel
     *
     * @mbg.generated do_not_delete_during_merge Sun Aug 07 21:47:23 CST 2022
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table novel
     *
     * @mbg.generated Sun Aug 07 21:47:23 CST 2022
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