package com.yayanovel.entity;

public class NovelCategory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column novel_category.CATEGORY_ID
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    private Long categoryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column novel_category.CATEGORY_UID
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    private String categoryUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column novel_category.CATEGORY_NAME
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    private String categoryName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column novel_category.TEXT1
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    private String text1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column novel_category.TEXT2
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    private String text2;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column novel_category.CATEGORY_ID
     *
     * @return the value of novel_category.CATEGORY_ID
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column novel_category.CATEGORY_ID
     *
     * @param categoryId the value for novel_category.CATEGORY_ID
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column novel_category.CATEGORY_UID
     *
     * @return the value of novel_category.CATEGORY_UID
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public String getCategoryUid() {
        return categoryUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column novel_category.CATEGORY_UID
     *
     * @param categoryUid the value for novel_category.CATEGORY_UID
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public void setCategoryUid(String categoryUid) {
        this.categoryUid = categoryUid == null ? null : categoryUid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column novel_category.CATEGORY_NAME
     *
     * @return the value of novel_category.CATEGORY_NAME
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column novel_category.CATEGORY_NAME
     *
     * @param categoryName the value for novel_category.CATEGORY_NAME
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column novel_category.TEXT1
     *
     * @return the value of novel_category.TEXT1
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public String getText1() {
        return text1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column novel_category.TEXT1
     *
     * @param text1 the value for novel_category.TEXT1
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public void setText1(String text1) {
        this.text1 = text1 == null ? null : text1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column novel_category.TEXT2
     *
     * @return the value of novel_category.TEXT2
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public String getText2() {
        return text2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column novel_category.TEXT2
     *
     * @param text2 the value for novel_category.TEXT2
     *
     * @mbg.generated Sun Aug 07 21:49:03 CST 2022
     */
    public void setText2(String text2) {
        this.text2 = text2 == null ? null : text2.trim();
    }
}