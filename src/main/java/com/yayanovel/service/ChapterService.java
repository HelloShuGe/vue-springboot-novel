package com.yayanovel.service;

import com.alibaba.druid.filter.AutoLoad;
import com.yayanovel.entity.Chapter;
import com.yayanovel.entity.Novel;
import com.yayanovel.entity.NovelContent;
import com.yayanovel.mapper.ChapterMapper;
import com.yayanovel.mapper.NovelContentMapper;
import com.yayanovel.mapper.NovelMapper;
import com.yayanovel.util.ResponseVO;
import com.yayanovel.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    private NovelContentMapper novelContentMapper;
    @Value("${novel.novelBasePath}")
    private String novelBasePath;
    /**
     * 插入章节
     * @param novelName
     * @return
     */
    public ResponseVO insertChapter(String novelName) throws IOException {
        File file = null;
        try{
            file = ResourceUtils.getFile("D:\\项目\\novel");
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
     * 根据章节名字获取章节号
     * @param fileName
     * @return
     */
    public int getChapterNum(String fileName){
        StringBuffer st = new StringBuffer();
        int i = 0;
        while(i < fileName.length() && (fileName.charAt(i) < '0' || fileName.charAt(i) > '9')){
            i++;
        }
        if(i >= fileName.length()){
            return 100;
        }
        while(i < fileName.length() && fileName.charAt(i) >= '0' && fileName.charAt(i) <= '9'){
            st.append(fileName.charAt(i));
            i++;
        }
        if(st.length() >= 6){
            return Integer.parseInt(st.toString().substring(0,6).trim());
        } else if(st.length() == 0) {
            return 100;
        } else{
            return Integer.parseInt(st.toString().trim());
        }
    }

    /**
     * 获取章节内容
     * @param file
     * @return
     * @throws IOException
     */
    public String getNovelContent(File file) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        fis.close();
        return sb.toString();
    }
    /**
     * 插入每一篇小说的章节
     * @param file
     * @return
     */
    public int insertFun(File file) throws IOException {
        File[] list = file.listFiles();
        Arrays.sort(list, new Comparator<File>() {
            public int compare(File file1, File file2){
                String fileName1 = file1.getName();
                String fileName2 = file2.getName();
                return  getChapterNum(fileName2) - getChapterNum(fileName1);
            }
        });
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
            chapter.setChapterName(list[i].getName().replace(".txt",""));
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
            logger.info("小说内容{}", list[i].toString());
            chapterMapper.insert(chapter);
//            NovelContent novelContent = new NovelContent();
//            novelContent.setChapterName(chapter.getChapterName());
//            novelContent.setNovelName(chapter.getNovelName());
//            novelContent.setNovelUid(chapter.getNovelUid());
//            novelContent.setChapterUid(chapter.getChapterUid());
//            novelContent.setNovelContentUid(UuidUtil.getUUID());
//            novelContent.setNovelContnet(getNovelContent(list[i]));
//            novelContentMapper.insert(novelContent);
        }
        return 0;
    }

    /**
     * 根据小说id查询章节
     * @param novelUid
     * @return
     */
    public List<Chapter> getChapterByNovelUid(String novelUid){
        return chapterMapper.getChapterByNovelUid(novelUid);
    }

    /**
     * 根据小说id查询章节，分页版
     * @param novelUid
     * @param packageNum
     * @return
     */
    public List<Chapter> getChapterByNovelUidPackage(String novelUid, Integer packageNum){
        return chapterMapper.getChapterByNovelPackage(novelUid, packageNum);
    }
    /**
     * 根据chapterUid获取前一个章节内容
     * @param chapterUid
     * @return
     */
    public Chapter getPreChapter(String chapterUid){
        Chapter chapter = chapterMapper.getChapterByChapterUid(chapterUid);
        logger.info("前一个章节的udi:{}",chapter.getPreUid());
        Chapter prChapter = chapterMapper.getChapterByChapterUid(chapter.getPreUid());
        return prChapter;
    }
    /**
     * 根据chapterUid获取章节内容
     * @param chapterUid
     * @return
     */
    public Chapter getNextChapter(String chapterUid){
        Chapter chapter = chapterMapper.getChapterByChapterUid(chapterUid);
        logger.info("前一个章节的udi:{}",chapter.getBackUid());
        Chapter prChapter = chapterMapper.getChapterByChapterUid(chapter.getBackUid());
        return prChapter;
    }
}
