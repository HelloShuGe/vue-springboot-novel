package com.yayanovel.service;

import com.yayanovel.entity.Bookshelf;
import com.yayanovel.entity.Collection;
import com.yayanovel.mapper.BookshelfMapper;
import com.yayanovel.mapper.CollectionMapper;
import com.yayanovel.util.ResponseVO;
import com.yayanovel.util.UuidUtil;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 个人信息展示服务层，包括个人图书馆，收藏夹
 */
@Service
public class PersonService {
    private static Logger logger = LoggerFactory.getLogger(PersonService.class);
    @Autowired
    private BookshelfMapper bookshelfMapper;
    @Autowired
    private CollectionMapper collectionMapper;

    /**
     * 添加图书到个人图书馆
     * @param bookshelf
     * @return
     */
    public ResponseVO addBook(Bookshelf bookshelf){
        String uuid = UuidUtil.getUUID();
        bookshelf.setBookshelfUid(uuid);
        int result = bookshelfMapper.insert(bookshelf);
        if(result != 1){
            logger.info("插入失败");
            return ResponseVO.response(null,"The insertion failed",500);
        }
        logger.info("添加成功");
        return ResponseVO.response(null,"The addition was successful",200);
    }

    /**
     * 展示个人图书馆
     * @param userUid
     * @return
     */
    public ResponseVO getBookshelf(String userUid){
        List<Bookshelf> list = bookshelfMapper.getBookshlefByUseruid(userUid);
        logger.info("查询成功");
        return ResponseVO.response(list,"The query was successful",200);
    }

    /**
     * 添加收藏
     * @param collection
     * @return
     */
    public ResponseVO addChapter(Collection collection){
        String uuid = UuidUtil.getUUID();
        collection.setCollectionUid(uuid);
        int result = collectionMapper.insert(collection);
        if(result != 1){
            logger.info("插入失败");
            return ResponseVO.response(null,"The insertion failed",500);
        }
        return ResponseVO.response(null,"The addition was successful",200);
    }
}
