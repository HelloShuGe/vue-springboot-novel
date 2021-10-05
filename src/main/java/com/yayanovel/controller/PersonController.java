package com.yayanovel.controller;

import com.yayanovel.controller.viewVO.BookshelfVO;
import com.yayanovel.entity.Bookshelf;
import com.yayanovel.entity.Collection;
import com.yayanovel.service.PersonService;
import com.yayanovel.util.ResponseVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户个人信息控制类，
 * 包括个人图书馆，收藏夹等
 */
@RestController
public class PersonController {
    private static Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private PersonService personService;

    /**
     * 将图书添加到个人图书馆
     * @param bookshelf
     * @return
     */
    @ApiOperation(value = "添加图书到图书馆", notes="添加图书到图书馆")
    @RequestMapping(value="/addBook", method = RequestMethod.POST)
    public ResponseVO addBook(@RequestBody Bookshelf bookshelf){
        if (bookshelf == null){
            logger.info("输入的图书为空");
            return ResponseVO.response(null,"Enter that the book is empty",404);
        }
        if (StringUtils.isEmpty(bookshelf.getNovelUid()) || StringUtils.isEmpty(bookshelf.getNovelName())){
            logger.info("输入的图书为空");
            return ResponseVO.response(null,"Enter that the book is empty",404);
        }
        return personService.addBook(bookshelf);
    }

    /**
     * 根据用户uid展示用户图书馆
     * @param bookshelfVO
     * @return
     */
    @ApiOperation(value = "个人图书馆展示", notes="个人图书馆展示")
    @RequestMapping(value="/getBookshelf", method = RequestMethod.POST)
    public ResponseVO getBookshelf(@RequestBody BookshelfVO bookshelfVO){
        String userUid = bookshelfVO.getUserUid();
        if (StringUtils.isEmpty(userUid)){
            logger.info("输入的用户uid为空");
            return ResponseVO.response(null,"The user uid entered is empty",404);
        }
        return personService.getBookshelf(userUid);
    }
    @ApiOperation(value = "收藏章节", notes="收藏章节")
    @RequestMapping(value="/addChapter", method = RequestMethod.POST)
    public ResponseVO addChapter(@RequestBody Collection collection){
        if (collection == null){
            logger.info("待收藏的章节为空");
            return ResponseVO.response(null,"The chapter to be saved is empty",404);
        }
        return personService.addChapter(collection);
    }
}
