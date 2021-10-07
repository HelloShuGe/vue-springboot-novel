package com.yayanovel.service;

import com.alibaba.druid.filter.AutoLoad;
import com.yayanovel.entity.Chapter;
import com.yayanovel.entity.Novel;
import com.yayanovel.mapper.ChapterMapper;
import com.yayanovel.mapper.NovelMapper;
import com.yayanovel.util.ResponseVO;
import com.yayanovel.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * 章节的服务层
 */
@Service
public class ChapterService {
    private  static Logger logger = LoggerFactory.getLogger(ChapterService.class);
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private NovelMapper novelMapper;
    @Value("${novel.novelBasePath}")
    private String novelBasePath;
    /**
     * 插入章节
     * @param novelName
     * @return
     */
    public ResponseVO insertChapter(String novelName){
        File file = null;
        try{
            file = ResourceUtils.getFile("classpath:novel");
        } catch (Exception e){
            logger.info("导入文件加异常",e);
            return ResponseVO.response(null,"文件加载异常", 400);
        }
        File[] list = file.listFiles();
        if (list == null || list.length == 0){
            logger.info("小说数目为0");
            return ResponseVO.response(null, "小说数目为0", 200);
        }
        //插入每一篇小说
        for (File file1 : list) {
            insertFun(file1);
        }
        return ResponseVO.response(null,"插入成功",200);
    }

    /**
     * 插入每一篇小说的章节
     * @param file
     * @return
     */
    public int insertFun(File file){
        File[] list = file.listFiles();
        String novelName = file.getName();
        novelName = novelName.trim();
        logger.info("小说名字" + novelName);
        int len = list.length;
        if (len == 0){
            logger.info("小说" + file.getName() + "章节数为0");
            return 0;
        }
        String[] uuidList = new String[len];
        //设置章节的uuid
        for(int i = 0; i < len; i++){
            uuidList[i] = UuidUtil.getUUID();
        }
        //根据小说名字获取uuid
        Novel novel = novelMapper.selectUuidByName(novelName);
        String novelUuid = novel.getNovelUid();
        for(int i = 0; i < len; i++){
            Chapter chapter = new Chapter();
            chapter.setNovelUid(novelUuid);
            chapter.setChapterUid(uuidList[i]);
            chapter.setChapterName(list[i].getName());
            if (i == 0){
                chapter.setPreUid(uuidList[i]);
            } else{
                chapter.setPreUid(uuidList[i - 1]);
            }
            if (i == len - 1){
                chapter.setBackUid(uuidList[i]);
            } else{
                chapter.setBackUid(uuidList[i + 1]);
            }
            chapter.setChapterNum(i + 1L);
            String path = novelBasePath + "/" + novelName + "/" + list[i].getName();
            chapter.setChapterAddr(path);
            chapter.setNovelName(novelName);
            chapterMapper.insert(chapter);
        }
        return 0;
    }
}
