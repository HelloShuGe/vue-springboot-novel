package com.yayanovel.controller.viewVO;

import org.springframework.stereotype.Component;

@Component
public class HotNovelLen {
    //热门小说推荐个数
    private String hotNovelLen;

    public String getHotNovelLen() {
        return hotNovelLen;
    }

    public void setHotNovelLen(String hotNovelLen) {
        this.hotNovelLen = hotNovelLen;
    }
}
