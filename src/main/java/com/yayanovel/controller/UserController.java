package com.yayanovel.controller;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.entity.UserInfo;
import com.yayanovel.service.TokenService;
import com.yayanovel.service.UserService;
import com.yayanovel.util.ValidUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录的控制层
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @ApiOperation(value = "登录", notes="登录")
    @RequestMapping("/login")
    public Object login(@RequestBody UserInfo userInfo, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        UserInfo user = userService.selectByEmail(userInfo.getUserEmail());
        if (user == null){
            jsonObject.put("code","400");
            logger.info("登录失败，用户未注册");
            jsonObject.put("message", "The login failed and the user was not registered!");
            return jsonObject;
        }
        if (!user.getUserPassword().equals(userInfo.getUserPassword())) {
            jsonObject.put("code","400");
            logger.info("登录失败，用户名或者密码错误");
            jsonObject.put("message", "Failed to log on, username or password error!");
            return jsonObject;
        } else {
            if(!"1".equals(user.getIsActive())){
                jsonObject.put("code","400");
                logger.info("账号未激活，请登录邮箱进行激活");
                jsonObject.put("message", "Account is not activated, please log in to the mailbox to activate!");
                return jsonObject;
            } else{
                String token = tokenService.getToken(user);
                jsonObject.put("token", token);
                jsonObject.put("code","200");
                logger.info("登录成功");
                jsonObject.put("message","The login was successful");
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                return jsonObject;
            }
        }
    }
    //@UserLoginToken
    @ApiOperation(value = "获取信息", notes = "获取信息")
    @RequestMapping(value = "/getMessage" ,method = RequestMethod.GET)
    public String getMessage() {
        // 取出token中带的用户id 进行操作
        return "您已通过验证";
    }

    /**
     * 注册接口，首先传入用户email和用户密码，存入数据库，然后给用户发送邮件
     * 用户点击连接后，再激活用户
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "注册", notes="注册")
    @RequestMapping("/register")
    public JSONObject register(@RequestBody UserInfo userInfo){
        JSONObject jsonObject= new JSONObject();
        //参数校验
        String userEmail = (String)userInfo.getUserEmail();
        if (!ValidUtil.isValidEmail(userEmail)){
            jsonObject.put("code","400");
            logger.info("邮箱格式不正确");
            jsonObject.put("message","The mailbox is not in the correct format!");
            return jsonObject;
        }
        String userPassword = (String)userInfo.getUserPassword();
        if (!ValidUtil.isValidPassword(userPassword)){
            jsonObject.put("code","400");
            logger.info("密码格式不正确");
            jsonObject.put("message","The password is not in the correct format!");
            return jsonObject;
        }
        return userService.register(userInfo);
    }

    /**
     * 邮箱激活
     * @param code
     * @return
     */
    @ApiOperation(value = "激活", notes="激活")
    @RequestMapping("/active")
    public JSONObject active(@RequestParam("code") String code){
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(code)){
            jsonObject.put("code","400");
            logger.info("用户输入验证码为空");
            jsonObject.put("message","The user input verification code is empty");
            return jsonObject;
        } else{
            return userService.active(code);
        }
    }

}
