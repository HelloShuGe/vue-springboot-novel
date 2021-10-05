package com.yayanovel.controller.viewVO;

import org.springframework.stereotype.Component;

@Component
public class BookshelfVO {
    /**
     * 用户uid
     */
    private String userUid;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    @Override
    public String toString() {
        return "BookshelfVO{" +
                "userUid='" + userUid + '\'' +
                '}';
    }
}
