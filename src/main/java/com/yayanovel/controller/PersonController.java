package com.yayanovel.controller;

import com.yayanovel.controller.viewVO.BookshelfVO;
import com.yayanovel.entity.Bookmark;
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

    /**
     * 收藏章节
     * @param collection
     * @return
     */
    @ApiOperation(value = "收藏章节", notes="收藏章节")
    @RequestMapping(value="/addChapter", method = RequestMethod.POST)
    public ResponseVO addChapter(@RequestBody Collection collection){
        if (collection == null){
            logger.info("待收藏的章节为空");
            return ResponseVO.response(null,"The chapter to be saved is empty",404);
        }
        return personService.addChapter(collection);
    }
    /**
     * 根据用户uid展示收藏
     * @param bookshelfVO
     * @return
     */
    @ApiOperation(value = "个人图书馆展示", notes="个人图书馆展示")
    @RequestMapping(value="/getCollection", method = RequestMethod.POST)
    public ResponseVO getCollection(@RequestBody BookshelfVO bookshelfVO){
        String userUid = bookshelfVO.getUserUid();
        if (StringUtils.isEmpty(userUid)){
            logger.info("输入的用户uid为空");
            return ResponseVO.response(null,"The user uid entered is empty",404);
        }
        return personService.getCollection(userUid);
    }

    /**
     * 添加书签
     * @param bookmark
     * @return
     */
    @ApiOperation(value = "添加书签", notes="添加书签")
    @RequestMapping(value="/addBookmark", method = RequestMethod.POST)
    public ResponseVO addBookmark(@RequestBody Bookmark bookmark){
        if (bookmark == null){
            logger.info("待添加的书签为空");
            return ResponseVO.response(null,"The chapter to be saved is empty",404);
        }
        return personService.addBookmark(bookmark);
    }
    /**
     * 根据用户uid展示书签
     * @param bookshelfVO
     * @return
     */
    @ApiOperation(value = "书签展示", notes="书签展示")
    @RequestMapping(value="/getBookmark", method = RequestMethod.POST)
    public ResponseVO getBookmark(@RequestBody BookshelfVO bookshelfVO){
        String userUid = bookshelfVO.getUserUid();
        if (StringUtils.isEmpty(userUid)){
            logger.info("输入的用户uid为空");
            return ResponseVO.response(null,"The user uid entered is empty",404);
        }
        return personService.getBookmark(userUid);
    }
}
