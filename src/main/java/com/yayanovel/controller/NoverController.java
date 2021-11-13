package com.yayanovel.controller;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.controller.viewVO.SearchNovelVO;
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
@RequestMapping("/novel")
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
     * @param len
     * @return
     */
    @ApiOperation(value = "热门小说推荐", notes="热门小说推荐")
    @RequestMapping(value="/getHotNovel",method = RequestMethod.POST)
    public ResponseVO hotNovel(@Param("len") String len){
        if (StringUtils.isEmpty(len)){
            logger.info("输入的推荐个数为空");
            return ResponseVO.response(null,"Recommended number cannot be empty",400);
        }
        int cnt = Integer.parseInt(len);
        List<UserInfo> list = novelService.getHotNovel(cnt);
        logger.info("获取热门小说成功");
        return ResponseVO.response(list,"Get popular novel success", 200);
    }
    /**
     * 搜索小说
     * @param
     * @return
     */
    @ApiOperation(value = "搜索小说", notes="搜索小说")
    @RequestMapping(value="/searchNovel",method = RequestMethod.POST)
    public ResponseVO searchNovel(@RequestBody SearchNovelVO searchNovelVO){
        String searchWord = searchNovelVO.getSearchWord();
        if (StringUtils.isEmpty(searchWord)){
            logger.info("输入搜索为空");
            return ResponseVO.response(null,"Input search character cannot be empty",400);
        }
        List<Novel> result = novelService.searchNovel(searchWord);
        return ResponseVO.response(result,"Search successful",200);
    }

}
