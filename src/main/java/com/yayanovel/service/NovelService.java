package com.yayanovel.service;

import com.yayanovel.entity.Novel;
import com.yayanovel.entity.NovelCategory;
import com.yayanovel.entity.UserInfo;
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

import java.io.File;
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

    /**
     * 根据小说阅读量获取热门推荐小说
     * @param cnt 推荐个数
     * @return
     */
    public List<UserInfo> getHotNovel(int cnt){
        List<UserInfo>  list = novelMapper.selectHotNovel();
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
     * 搜索小说
     * @param searchWord
     * @return
     */
    public List<Novel> searchNovel(String searchWord){
        return novelMapper.searchNovel(searchWord);
    }
}
