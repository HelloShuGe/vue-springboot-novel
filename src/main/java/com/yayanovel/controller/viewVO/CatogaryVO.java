package com.yayanovel.controller.viewVO;

import org.springframework.stereotype.Component;

@Component
public class CatogaryVO {
    /**
     * 小说类别
     */
    private String catogary;

    public String getCatogary() {
        return catogary;
    }

    public void setCatogary(String catogary) {
        this.catogary = catogary;
    }
}
