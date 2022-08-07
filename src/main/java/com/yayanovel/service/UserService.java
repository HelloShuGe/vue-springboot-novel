package com.yayanovel.service;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.entity.UserInfo;
import com.yayanovel.mapper.UserInfoMapper;
import com.yayanovel.util.DateTimeUtil;
import com.yayanovel.util.ResponseVO;
import com.yayanovel.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.ResourceBundle;


/**
 * 用户信息的service
 */
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private MailService mailService;
    @Value("${spring.mail.message.subject}")
    private String subject;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 根据用户email查询用户信息
     * @param userEmail
     * @return
     */
    public UserInfo selectByEmail(String userEmail){
        return userInfoMapper.selectByEmail(userEmail);
    }

    /**
     * 根据用户userUid查询用户信息
     * @param userUid
     * @return
     */
    public UserInfo selectByUid(String userUid){
        return userInfoMapper.selectByUserUid(userUid);
    }

    /**
     * 插入用户信息
     * @param userInfo
     * @return
     */
    public void insertUser(UserInfo userInfo, String code){
        //用户uuid
        String userUid = UuidUtil.getUUID();
        userInfo.setUserUid(userUid);
        //创建时间
        String createTime = DateTimeUtil.getTime();
        userInfo.setCreateTime(createTime);
        userInfo.setActiveCode(code);
        //激活码创建时间
        String activeTime = DateTimeUtil.getTime();
        userInfo.setActiveTime(activeTime);
        //是否激活
        String isActive = "0";
        userInfo.setIsActive(isActive);
        //更新时间
        String updateTime = DateTimeUtil.getTime();
        userInfo.setUpdateTime(updateTime);
        //设置随机昵称
        userInfo.setUserName(createName());
        userInfoMapper.insert(userInfo);
    }
    public void updateActive(UserInfo userInfo, String code){
        //创建时间
        String createTime = DateTimeUtil.getTime();
        userInfo.setCreateTime(createTime);
        //激活码
        userInfo.setActiveCode(code);
        //激活码创建时间
        String activeTime = DateTimeUtil.getTime();
        userInfo.setActiveTime(activeTime);
        //是否激活
        String isActive = "0";
        userInfo.setIsActive(isActive);
        //更新时间
        String updateTime = DateTimeUtil.getTime();
        userInfo.setUpdateTime(updateTime);
        userInfoMapper.updateAvtiveState(userInfo);
    }
    /**
     * 注册服务层
     * @param userInfo
     * @return
     */
//    public ResponseVO register(UserInfo userInfo){
//        String userEmail = userInfo.getUserEmail();
//        UserInfo user = userInfoMapper.selectByEmail(userEmail);
//        String uuid = null;
//        if (user == null){
//            logger.info("用户没有注册");
//            uuid = insertUser(userInfo);
//        } else{
//            if ("1".equals(user.getIsActive())){
//                logger.info("用户已经注册");
//                return ResponseVO.response(null, "The user is already registered!", 400);
//            } else{
//                logger.info("用户已经注册，请前往邮箱进行激活");
//                return ResponseVO.response(null,"The user is already registered,please log in to the mailbox activation!",400);
//            }
//        }
//        String context = "<h1>此邮件为官方激活邮件！请点击下面链接完成激活操作！</h1> <a href=\"http://120.25.210.42:8080/api/user/active?code="+uuid+"\">激活请点击:"+uuid+"</a> ";
//        mailService.sendSimplerMail(userEmail,subject,context);
//        logger.info("邮件已经发送，请登录邮箱进行激活");
//        return ResponseVO.response(null,"The message has been sent, please log in to the mailbox activation!",200);
//    }

    /**
     * 激活
     * @param
     * @return
     */
    public ResponseVO register(UserInfo userInfo){
        UserInfo userInfo1 = userInfoMapper.selectByEmail(userInfo.getUserEmail());
        if(userInfo1 == null){
            logger.info("用户还没有获取验证码");
            return ResponseVO.response(null,"The verification code is not valid", 400);
        }
        if(!userInfo.getActiveCode().equals(userInfo1.getActiveCode())){
            logger.info("验证码错误");
            return ResponseVO.response(null,"The verification code is incorrect", 400);
        }
        String nowTime = DateTimeUtil.getTime();
        String activeTime = userInfo1.getActiveTime();
        Long nowLong = Long.parseLong(nowTime);
        Long activeLong = Long.parseLong(activeTime);
        //创建时间
        String createTime = DateTimeUtil.getTime();
        userInfo.setCreateTime(createTime);
        if (nowLong - activeLong > 1){
            logger.info("验证码超时");
            return ResponseVO.response(null, "The verification code timed out", 400);
        }
        userInfoMapper.updateActive(userInfo.getUserEmail());
        logger.info("激活成功");
        return ResponseVO.response(null,"Registration successful", 200);
    }

    /**
     * 随机生成昵称（10位）
     * @return
     */
    public String createName(){
        String chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 10; i++){
            sb.append(chars.charAt(random.nextInt(52)));
        }
        return sb.toString();
    }

    /**
     * 修改密码
     * @param userEmail
     * @param originalPassword
     * @param newPassword
     * @return
     */
    public ResponseVO changePassword(String userEmail, String originalPassword, String newPassword){
        UserInfo userInfo = userInfoMapper.selectByEmail(userEmail);
        if (userInfo == null){
            logger.info("用户账号不正确");
            return ResponseVO.response(null, "User account does not exist",400);
        }
        String password = userInfo.getUserPassword();
        if (!StringUtils.pathEquals(password, originalPassword)){
            logger.info("用户输入密码不正确");
            return ResponseVO.response(null, "The user entered the password incorrectly", 400);
        }
        int res = userInfoMapper.updatePassword(newPassword, userEmail);
        if(res == 1){
            logger.info("密码修改成功");
            return ResponseVO.response(null, "Password reset complete", 200);
        } else{
            logger.info("密码修改失败");
            return ResponseVO.response(null, "Password modification failed", 400);
        }
    }

    /**
     * 发送验证码
     * @return
     */
    public ResponseVO getCode(UserInfo userInfo){
        String code = genCode();
        String context = "The verification code is:" + code;
        UserInfo userInfo1 = userInfoMapper.selectByEmail(userInfo.getUserEmail());
        if(userInfo1 == null){
            insertUser(userInfo, code);
        }else{
            updateActive(userInfo,code);
        }
        try{
            mailService.sendSimplerMail(userInfo.getUserEmail(),subject,context);
            logger.info("邮件已经发送，请登录邮箱进行激活");
            return ResponseVO.response(null,"The message has been sent, please Log in to your email address to get a verification code",200);
        } catch (Exception e){
            logger.info("邮件发送失败");
            return ResponseVO.response(null,"The email failed to send, please check if the mailbox is correct",400);
        }
    }

    /**
     * 生成4位验证码
     * @return
     */
    public static String genCode(){
        StringBuilder codeStr = new StringBuilder();
        for(int i = 0; i < 4; i++){
            int tem = (int)(Math.random() * 10);
            codeStr.append(tem+"");
        }
        return codeStr.toString();
    }
}
