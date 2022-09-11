package com.yayanovel.mapper;

import com.yayanovel.entity.Chapter;
import com.yayanovel.entity.ChapterExample;
import java.util.List;

import com.yayanovel.entity.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ChapterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    long countByExample(ChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int deleteByExample(ChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int deleteByPrimaryKey(Long chapterId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int insert(Chapter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int insertSelective(Chapter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    List<Chapter> selectByExample(ChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    Chapter selectByPrimaryKey(Long chapterId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int updateByExampleSelective(@Param("record") Chapter record, @Param("example") ChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int updateByExample(@Param("record") Chapter record, @Param("example") ChapterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int updateByPrimaryKeySelective(Chapter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table chapter
     *
     * @mbg.generated Sun Aug 07 21:47:38 CST 2022
     */
    int updateByPrimaryKey(Chapter record);

    /**
     * 根据小说名字获取章节
     * @param novelName
     * @return
     */
    String getChapter(String novelName);

    /**
     * 根据小说uid查询章节
     * @param novelUid
     * @return
     */
    List<Chapter> getChapterByNovelUid(String novelUid);

    /**
     * 根据小说uid和页数查询小说
     * @param novelUid
     * @param packageNum
     * @return
     */
    List<Chapter> getChapterByNovelPackage(String novelUid, int packageNum);

    /**
     * 根据章节uid查询章节
     * @param chapterUid
     * @return
     */
    Chapter getChapterByChapterUid(String chapterUid);
}