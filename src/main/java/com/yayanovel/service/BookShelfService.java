package com.yayanovel.service;

import com.yayanovel.entity.Bookshelf;
import com.yayanovel.mapper.BookshelfMapper;
import com.yayanovel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookShelfService {
    @Autowired
    private BookshelfMapper bookshelfMapper;
    public int addNovel(Bookshelf bookshelf){
        String bookShelfUid = UuidUtil.getUUID();
        bookshelf.setBookshelfUid(bookShelfUid);
        return bookshelfMapper.insert(bookshelf);
    }
}
