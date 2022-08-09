package com.yayanovel.controller;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.entity.Bookshelf;
import com.yayanovel.service.BookShelfService;
import com.yayanovel.util.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 书架控制类
 */
@RestController
@RequestMapping("/api/libary")
public class BookShelfController {
    private Logger logger = LoggerFactory.getLogger(BookShelfController.class);
    @Autowired
    private BookShelfService bookShelfService;
    /**
     * 添加书
     * @param bookshelf
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "添加小说", notes="添加小说")
    @RequestMapping(value="/addnovel",method = RequestMethod.POST)
    public ResponseVO addNovel(@RequestBody Bookshelf bookshelf){
        logger.info("小说uid"+bookshelf.getNovelUid());
        logger.info("用户邮箱：" + bookshelf.getUserEmail());
        if(bookShelfService.addNovel(bookshelf) != 1){
            return ResponseVO.response(null,"Failed to add book",400);
        }else{
            return ResponseVO.response(null,"The book was added successfully",200);
        }
    }
}
