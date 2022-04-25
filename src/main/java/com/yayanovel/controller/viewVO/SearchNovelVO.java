package com.yayanovel.controller.viewVO;

import org.springframework.stereotype.Component;

@Component
public class SearchNovelVO {
    private String searchWord;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String toString() {
        return "serarchNovelVO{" +
                "searchWord='" + searchWord + '\'' +
                '}';
    }
}
