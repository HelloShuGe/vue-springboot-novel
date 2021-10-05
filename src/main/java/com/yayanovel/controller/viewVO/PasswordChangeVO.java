package com.yayanovel.controller.viewVO;

import org.springframework.stereotype.Component;

/**
 * 密码修改VO
 */
@Component
public class PasswordChangeVO {
    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 原始密码
     */
    private String originalPassword;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 确认密码
     */
    private String quitPassword;

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getQuitPassword() {
        return quitPassword;
    }

    public void setQuitPassword(String quitPassword) {
        this.quitPassword = quitPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    @Override
    public String toString() {
        return "PasswordChangeVO{" +
                "userEmail='" + userEmail + '\'' +
                ", originalPassword='" + originalPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", quitPassword='" + quitPassword + '\'' +
                '}';
    }
}
