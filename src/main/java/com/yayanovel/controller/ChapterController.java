package com.yayanovel.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yayanovel.entity.Chapter;
import com.yayanovel.service.ChapterService;
import com.yayanovel.util.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 章节控制层
 */
@RestController
@RequestMapping("/api")
public class ChapterController {
    private Logger logger = LoggerFactory.getLogger(ChapterController.class);
    @Autowired
    private ChapterService chapterService;
    /**
     * 插入章节
     * @return
     */
    @ApiOperation(value = "插入章节", notes="章节")
    @RequestMapping(value="/insertChapter",method = RequestMethod.POST)
    public ResponseVO insertChpater(@RequestBody JSONObject jsonObject) throws IOException {
        String novelName = jsonObject.getString("novelName");
        return chapterService.insertChapter(novelName);
    }

    /**
     * 根据小说uid获取章节
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "根据小说uid获取章节", notes="章节")
    @RequestMapping(value="/getChapterByNovelUid",method = RequestMethod.POST)
    public ResponseVO getChapterByNovelUid(@RequestBody JSONObject jsonObject){
        String novelUid = jsonObject.getString("novelUid");
        String packageNumStr = jsonObject.getString("packageNum");
        logger.info("页数{}", packageNumStr);
        if(StringUtils.isEmpty(packageNumStr)){
            List chapterList = chapterService.getChapterByNovelUid(novelUid);
            return ResponseVO.response(chapterList,"Search successful",200);
        }else{
            Integer packageNum = Integer.parseInt(packageNumStr);
            packageNum *= 50;
            List chapterList = chapterService.getChapterByNovelUidPackage(novelUid, packageNum);
            return ResponseVO.response(chapterList,"Search successful",200);
        }
    }
    /**
     * 根据章节uid获取前一个章节信息
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "根据章节uid获取前一个章节信息", notes="根据章节uid获取前一个章节信息")
    @RequestMapping(value="/getNextChapter",method = RequestMethod.POST)
    public ResponseVO getPreChapter(@RequestBody JSONObject jsonObject){
        String chapterUid = jsonObject.getString("chapterUid");
        logger.info("章节Uid:{}", chapterUid);
        Chapter chapter = chapterService.getPreChapter(chapterUid);
        return  ResponseVO.response(chapter,"Search successful",200);
    }
    /**
     * 根据章节uid获取后一个章节信息
     * @param jsonObject
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "根据章节uid获取后一个章节信息", notes="根据章节uid获取前一个章节信息")
    @RequestMapping(value="/getPreChapter",method = RequestMethod.POST)
    public ResponseVO getNextChapter(@RequestBody JSONObject jsonObject){
        String chapterUid = jsonObject.getString("chapterUid");
        logger.info("章节Uid:{}", chapterUid);
        Chapter chapter = chapterService.getNextChapter(chapterUid);
        return  ResponseVO.response(chapter,"Search successful",200);
    }

}
