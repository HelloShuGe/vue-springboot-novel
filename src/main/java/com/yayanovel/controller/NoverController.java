package com.yayanovel.controller;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.controller.viewVO.CatogaryVO;
import com.yayanovel.controller.viewVO.ContentVO;
import com.yayanovel.controller.viewVO.HotNovelLen;
import com.yayanovel.controller.viewVO.SearchNovelVO;
import com.yayanovel.entity.Bookshelf;
import com.yayanovel.entity.Novel;
import com.yayanovel.entity.UserInfo;
import com.yayanovel.service.NovelService;
import com.yayanovel.util.ResponseVO;
import com.yayanovel.util.UuidUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * 小说控制层
 */
@RestController
public class NoverController {
    private static Logger logger = LoggerFactory.getLogger(NoverController.class);
    @Autowired
    private NovelService novelService;
    @ApiOperation(value = "插入小说类别", notes="插入小说类别")
    @RequestMapping(value="/insertNovelCategory", method = RequestMethod.POST)
    public ResponseVO insertNovelCategory(@RequestBody JSONObject jsonObject){
        List<String> list = (List)jsonObject.get("data");
        logger.info(list.size() + "");
        for (String s : list) {
            novelService.insertNovleCategory(s);
        }
        return ResponseVO.response(null, "插入成功",200);
    }
    /**
     * 插入小说
     * @return
     */
    @ApiOperation(value = "插入小说", notes="插入小说")
    @RequestMapping(value="/insertNovel",method = RequestMethod.POST)
    public ResponseVO insertNovel(){
        return novelService.insertNovel(null);
    }
    /**
     * 热门小说推荐
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "热门小说推荐", notes="热门小说推荐")
    @RequestMapping(value="api/getHotNovel",method = RequestMethod.POST)
    public ResponseVO hotNovel(@RequestBody HotNovelLen hotNovelLen){
        String len = hotNovelLen.getHotNovelLen();
        if (StringUtils.isEmpty(len)){
            logger.info("输入的推荐个数为空");
            //传入参数为-1则是全量查询
            List result = novelService.getHotNovel(-1);
            return ResponseVO.response(result,"Get popular novel success",200);
        }
        int cnt = Integer.parseInt(len);
        List<UserInfo> list = novelService.getHotNovel(cnt);
        logger.info("获取热门小说成功");
        return ResponseVO.response(list,"Get popular novel success", 200);
    }
    /**
     * 搜索小说,按照小说的名字模糊查询
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "搜索小说", notes="搜索小说")
    @RequestMapping(value="api/searchNovel",method = RequestMethod.POST)
    public ResponseVO searchNovel(@RequestBody SearchNovelVO searchNovelVO){
        String searchWord = searchNovelVO.getSearchWord();
        logger.info("查询的小说名字为" + searchWord);
        if (StringUtils.isEmpty(searchWord)){
            logger.info("输入搜索为空");
            return ResponseVO.response(null,"Input search character cannot be empty",400);
        }
        List<Novel> result = novelService.searchNovel(searchWord);
        return ResponseVO.response(result,"Search successful",200);
    }

    /**
     * 搜索小说类别
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "查询小说类别", notes="查询小说类别")
    @RequestMapping(value="api/searchNovelCatogary",method = RequestMethod.POST)
    public ResponseVO gethNovelCatogary(@RequestBody SearchNovelVO searchNovelVO){
        List result = novelService.getNovelCatogary();
        return ResponseVO.response(result,"Search successful",200);
    }

    /**
     * 搜索小说,全量搜索
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "搜索小说", notes="搜索小说")
    @RequestMapping(value="api/searchNovelAll",method = RequestMethod.POST)
    public ResponseVO searchNovelAll(){
        List<Novel> result = novelService.getNovelAll();
        logger.info(result.get(0).getIsEnd());
        return ResponseVO.response(result,"Search successful",200);
    }
    /**
     * 获取小说内容
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "获取小说内容", notes="获取小说内容")
    @RequestMapping(value="api/getContent",method = RequestMethod.POST)
    public ResponseVO getContent(@RequestBody ContentVO contentVO){
        String novelName = contentVO.getNovelName();
        String chapterName = contentVO.getChapterName();
        List<String> result = novelService.getContent(novelName,chapterName);
        return ResponseVO.response(result,"Search successful",200);
    }
    /**
     * 搜索连载的小说
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "获取小说内容", notes="获取小说内容")
    @RequestMapping(value="api/getOngoingNovel",method = RequestMethod.POST)
    public ResponseVO getOngoingNovel(){
        logger.info("搜素连载小说");
        List<Novel> result = novelService.getOngoingNovel();
        return ResponseVO.response(result,"Search successful",200);
    }
    /**
     * 搜索完结的小说
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "获取小说内容", notes="获取小说内容")
    @RequestMapping(value="api/getCompletedNovel",method = RequestMethod.POST)
    public ResponseVO getCompletedNovel(){
        logger.info("搜素完结小说");
        List<Novel> result = novelService.getCompletedNovel();
        return ResponseVO.response(result,"Search successful",200);
    }
    /**
     * 根据类别搜索小说
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "获取小说内容", notes="获取小说内容")
    @RequestMapping(value="api/getCatogaryNovel",method = RequestMethod.POST)
    public ResponseVO getCatogaryNovel(@RequestBody CatogaryVO catogaryVO){
        String catogary = catogaryVO.getCatogary();
        if(StringUtils.isEmpty(catogary)){
            logger.info("位空");
        }
        logger.info("小说类别:" + catogary);
        List<Novel> result = novelService.getCatogaryNovel(catogary);
        return ResponseVO.response(result,"Search successful",200);
    }
    /**
     * 测试代码，将小说第一章的前200个字符
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "获取小说内容", notes="获取小说内容")
    @RequestMapping(value="api/insertIntroduction",method = RequestMethod.POST)
    public ResponseVO insertIntroduction(){
        novelService.insertIntrodection();
        return null;
    }

    /**
     * 根据用户邮箱查询书架
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "查询用户收藏", notes="查询用户收藏")
    @RequestMapping(value="api/getlibarynovel",method = RequestMethod.POST)
    public ResponseVO getLibaryNovel(@RequestBody Bookshelf bookshelf){
        String userEmail = bookshelf.getUserEmail();
        logger.info("查询用户收藏小说" + userEmail);
        List novelList = novelService.getLibaryNovel(userEmail);
        if(novelList == null || novelList.size() == 0){
            return ResponseVO.response(null,"The query failed",400);
        }else{
            return ResponseVO.response(novelList,"Query successful",200);
        }
    }
}
