package com.yayanovel.service;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.entity.UserInfo;
import com.yayanovel.entity.UserInfoExample;
import com.yayanovel.mapper.UserInfoMapper;
import com.yayanovel.util.DateTimeUtil;
import com.yayanovel.util.UuidUtil;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.util.JAXBSource;
import java.util.Random;


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
    public String insertUser(UserInfo userInfo){
        //用户uuid
        String userUid = UuidUtil.getUUID();
        userInfo.setUserUid(userUid);
        //创建时间
        String createTime = DateTimeUtil.getTime();
        userInfo.setCreateTime(createTime);
        //激活码
        String activeCode = UuidUtil.getUUID();
        userInfo.setActiveCode(activeCode);
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
        return activeCode;
    }
    public String updateActive(UserInfo userInfo){
        //创建时间
        String createTime = DateTimeUtil.getTime();
        userInfo.setCreateTime(createTime);
        //激活码
        String activeCode = UuidUtil.getUUID();
        userInfo.setActiveCode(activeCode);
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
        return activeCode;
    }
    /**
     * 注册服务层
     * @param userInfo
     * @return
     */
    public JSONObject register(UserInfo userInfo){
        JSONObject jsonObject = new JSONObject();
        String userEmail = userInfo.getUserEmail();
        UserInfo user = userInfoMapper.selectByEmail(userEmail);
        String uuid = null;
        if (user == null){
            logger.info("用户没有注册");
            uuid = insertUser(userInfo);
        } else{
            if ("1".equals(user.getIsActive())){
                jsonObject.put("code","400");
                logger.info("用户已经注册");
                jsonObject.put("message","The user is already registered!");
                return jsonObject;
            } else{
                logger.info("用户没有注册");
                uuid = updateActive(userInfo);
            }
        }
        String context = "<h1>此邮件为官方激活邮件！请点击下面链接完成激活操作！</h1> <a href=\"http://localhost:8080/active?code="+uuid+"\">激活请点击:"+uuid+"</a> ";
        mailService.sendSimplerMail(userEmail,subject,context);
        jsonObject.put("code","200");
        logger.info("邮件已经发送，请登录邮箱进行激活");
        jsonObject.put("message","The message has been sent, please log in to the mailbox activation!");
        return jsonObject;
    }

    /**
     * 激活
     * @param code
     * @return
     */
    public JSONObject active(String code){
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo = userInfoMapper.selectByActiveCode(code);
        if(userInfo == null){
            jsonObject.put("code","400");
            logger.info("无效验证码");
            jsonObject.put("message","The verification code is not valid");
            return jsonObject;
        }
        String nowTime = DateTimeUtil.getTime();
        String activeTime = userInfo.getActiveTime();
        Long nowLong = Long.parseLong(nowTime);
        Long activeLong = Long.parseLong(activeTime);
        if (nowLong - activeLong > 1){
            jsonObject.put("code","400");
            logger.info("验证码超时");
            jsonObject.put("message","The verification code timed out");
            return jsonObject;
        }
        userInfoMapper.updateActive(code);
        jsonObject.put("code","200");
        logger.info("激活成功");
        jsonObject.put("message","The activation was successful");
        return jsonObject;
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

}
