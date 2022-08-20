package com.yayanovel.service;

import com.yayanovel.controller.viewVO.NovelCatogaryVO;
import com.yayanovel.entity.*;
import com.yayanovel.mapper.ChapterMapper;
import com.yayanovel.mapper.NovelCategoryMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 小说服务层
 */
@Service
public class NovelService {
    private static Logger logger = LoggerFactory.getLogger(NovelService.class);
    @Autowired
    private NovelMapper novelMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private NovelCategoryMapper novelCategoryMapper;
    @Value("${novel.novelBasePath}")
    private String novelBasePath;
    @Value("${env}")
    private String env;
    /**
     * 插入小说简介
     */
    public void insertIntrodection(){
        //获取所有小说目录
        List<Novel> novelList = novelMapper.searchNovelAll();
        for(int i = 0; i < novelList.size(); i++){
            String novelName = novelList.get(i).getNovelName();
            String chapterName = chapterMapper.getChapter(novelName);
            List<String> contentList = getContent(novelName, chapterName);
            StringBuilder st = new StringBuilder();
            for(int j = 0; j < contentList.size(); j++){
                st.append(contentList.get(j));
            }
            String tem = st.toString().trim();
            if(st.length() > 200){
                String introducetion = tem.substring(0,200);
                novelMapper.insertIntroduction(introducetion, novelName);
            }else{
                novelMapper.insertIntroduction(tem, novelName);
            }

        }
    }

    /**
     * 根据小说阅读量获取热门推荐小说
     * @param cnt 推荐个数,如果cnt为-1，则是全量查询
     * @return
     */
    public List<UserInfo> getHotNovel(int cnt){
        List<UserInfo>  list = novelMapper.selectHotNovel();
        if(cnt == -1){
            return list;
        }
        int size = list.size();
        if (cnt >= size){
            return list;
        } else{
            return list.subList(0,cnt);
        }
    }

    /**
     * 插入小说
     * @param novel
     * @return
     */
    public ResponseVO insertNovel(Novel novel){
        if (novel != null){
            novelMapper.insert(novel);
            return ResponseVO.response(null,"插入成功",200);
        }
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
        for (File file1 : list) {
            logger.info(file1.getName());
            novel = new Novel();
            String uuid = UuidUtil.getUUID();
            novel.setNovelUid(uuid);
            novel.setNovelName(file1.getName());
            novel.setNovelAddr(novelBasePath + "/" + file1.getName());
            Random random = new Random();
            //阅读量范围，500-1000
            novel.setReadNum(random.nextInt(500) + 500L);
            //搜藏量，300-500
            novel.setCollectionNum(random.nextInt(300) + 200L);
            List<NovelCategory> novelCategoryList = novelCategoryMapper.selectByExample(null);
            novel.setCategory(novelCategoryList.get(random.nextInt(30)).getCategoryName());
            novel.setImgAddr("/img");
            novel.setIsEnd("Y");
            novel.setKeywords("hello");
            novelMapper.insert(novel);
        }
        return ResponseVO.response(null,"插入成功",200);
    }

    /**
     * 插入小说类别
     * @param novelName
     * @return
     */
    public int insertNovleCategory(String novelName){
        NovelCategory novelCategory = new NovelCategory();
        String uuid = UuidUtil.getUUID();
        novelCategory.setCategoryUid(uuid);
        novelCategory.setCategoryName(novelName);
        return novelCategoryMapper.insert(novelCategory);
    }

    /**
     * 搜索小说,通过小说名字模糊查询
     * @param searchWord
     * @return
     */
    public List<Novel> searchNovel(String searchWord){
        return novelMapper.searchNovel(searchWord);
    }

    /**
     * 查询小说类别
     * @return
     */
    public List<NovelCatogaryVO> getNovelCatogary(){
        List<NovelCategory> novelCategoryList = novelCategoryMapper.selectCatogary();
        List<NovelCatogaryVO> list = new ArrayList<>();
        for(int i = 0; i < novelCategoryList.size(); i++){
            NovelCatogaryVO novelCatogaryVO = new NovelCatogaryVO();
            novelCatogaryVO.setName(novelCategoryList.get(i).getCategoryName());
            list.add(novelCatogaryVO);
        }
        return list;
    }
    /**
     * 全量查询小说
     * @return
     */
    public List<Novel> getNovelAll(){
        //查询所有小说
        return novelMapper.searchNovelAll();
    }

    public List<String> getContent(String novelName, String chapterName){
        logger.info("当前环境为{}", env);
        String path = null;
        if("test".equals(env)){
            path = novelBasePath + "\\\\" + novelName + "\\\\" + chapterName + ".txt";
        }else{
            path = novelBasePath + "/" + novelName + "/" + chapterName + ".txt";
        }
        logger.info("小说路径{}", path);
        File file = null;
        try{
            file = ResourceUtils.getFile(path);
            return getNovelContent(file);
        } catch (Exception e){
            logger.info("导入文件加异常",e);
            return null;
        }
    }

    /**
     * 获取小说内容
     * @param file
     * @return
     * @throws IOException
     */
    public List<String> getNovelContent(File file) throws IOException {
        List<String> contentList = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            contentList.add(line);
        }
        fis.close();
        return contentList;
    }

    /**
     * 查询连载小说
     * @return
     */
    public List<Novel> getOngoingNovel(){
        return novelMapper.searchOngoingNovel();
    }
    /**
     * 查询完结小说
     * @return
     */
    public List<Novel> getCompletedNovel(){
        return novelMapper.searchCompletedNovel();
    }

    /**
     * 根据类别搜索小说
     * @param catogary
     * @return
     */
    public List<Novel> getCatogaryNovel(String catogary){
        return novelMapper.searchCatogaryNovel(catogary);
    }

    /**
     * 根据用户邮箱查询收藏小说
     * @param userEmail
     * @return
     */
    public List<Novel> getLibaryNovel(String userEmail){
        List novelList = novelMapper.getLibaryNovel(userEmail);
        return novelList;
    }
}
