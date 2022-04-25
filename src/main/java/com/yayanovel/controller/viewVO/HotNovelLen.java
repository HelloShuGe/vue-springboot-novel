package com.yayanovel.controller.viewVO;

import org.springframework.stereotype.Component;

@Component
public class HotNovelLen {
    //热门小说推荐个数
    private String HotNovelLen;

    public String getHotNovelLen() {
        return HotNovelLen;
    }

    public void setHotNovelLen(String hotNovelLen) {
        HotNovelLen = hotNovelLen;
    }
}
