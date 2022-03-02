package com.yayanovel.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yayanovel.service.ChapterService;
import com.yayanovel.util.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 章节控制层
 */
@RestController
@RequestMapping("/api/chapter")
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
    public ResponseVO insertChpater(@RequestBody JSONObject jsonObject){
        String novelName = jsonObject.getString("novelName");
        return chapterService.insertChapter(novelName);
    }
}
