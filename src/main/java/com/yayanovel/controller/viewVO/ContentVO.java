package com.yayanovel.controller.viewVO;

import org.springframework.stereotype.Component;

@Component
public class ContentVO {
    /**
     * 小说名称
     */
    private String novelName;
    /**
     * 章节名称
     */
    private String chapterName;

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
}
