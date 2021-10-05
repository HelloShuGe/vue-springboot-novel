package com.yayanovel.controller;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.controller.viewVO.PasswordChangeVO;
import com.yayanovel.entity.UserInfo;
import com.yayanovel.service.TokenService;
import com.yayanovel.service.UserService;
import com.yayanovel.util.ResponseVO;
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

    /**
     * 登录接口
     * @param userInfo
     * @param response
     * @return
     */
    @ApiOperation(value = "登录", notes="登录")
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseVO login(@RequestBody UserInfo userInfo, HttpServletResponse response){
        UserInfo user = userService.selectByEmail(userInfo.getUserEmail());
        if (user == null){
            logger.info("登录失败，用户未注册");
            return ResponseVO.response(null,"The login failed and the user was not registered!", 400);
        }
        if (!user.getUserPassword().equals(userInfo.getUserPassword())) {
            logger.info("登录失败，用户名或者密码错误");
            return ResponseVO.response(null, "Failed to log on, username or password error!", 400);
        } else {
            if(!"1".equals(user.getIsActive())){
                logger.info("账号未激活，请登录邮箱进行激活");
                return ResponseVO.response(null,"Account is not activated, please log in to the mailbox to activate!",400);
            } else{
                String token = tokenService.getToken(user);
                JSONObject data = new JSONObject();
                data.put("token", token);
                logger.info("登录成功");
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                return ResponseVO.response(data,"The login was successful", 200);
            }
        }
    }
    /**
     * 注册接口，首先传入用户email和用户密码，存入数据库，然后给用户发送邮件
     * 用户点击连接后，再激活用户
     * @param userInfo 包括userEmail和password
     * @return
     */
    @ApiOperation(value = "注册", notes="注册")
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResponseVO register(@RequestBody UserInfo userInfo){
        //参数校验
        String userEmail = (String)userInfo.getUserEmail();
        if (!ValidUtil.isValidEmail(userEmail)){
            logger.info("邮箱格式不正确");
            return ResponseVO.response(null,"The mailbox is not in the correct format!", 400);
        }
        String userPassword = (String)userInfo.getUserPassword();
        if (!ValidUtil.isValidPassword(userPassword)){
            logger.info("密码格式不正确");
            return ResponseVO.response(null,"The password is not in the correct format!", 400);
        }
        return userService.register(userInfo);
    }

    /**
     * 邮箱激活
     * @param code 激活码
     * @return
     */
    @ApiOperation(value = "账号激活", notes="账号激活")
    @RequestMapping(value="/active",method = RequestMethod.POST)
    public ResponseVO active(@RequestParam("code") String code){
        if (StringUtils.isEmpty(code)){
            logger.info("用户输入验证码为空");
            return ResponseVO.response(null,"The user input verification code is empty", 400);
        } else{
            return userService.active(code);
        }
    }

    /**
     * 修改密码
     * @param passwordChangeVO
     * @return
     */
    @ApiOperation(value = "修改密码", notes="修改密码")
    @RequestMapping(value="/passwordChange",method = RequestMethod.POST)
    public ResponseVO passwordChange(@RequestBody PasswordChangeVO passwordChangeVO){
        String originalPassword = passwordChangeVO.getOriginalPassword();
        String newPassword = passwordChangeVO.getNewPassword();
        String quitPassword = passwordChangeVO.getQuitPassword();
        String email = passwordChangeVO.getUserEmail();
        if (!StringUtils.pathEquals(newPassword, quitPassword)){
            return ResponseVO.response(null,"The two passwords are not the same",400);
        }
        if (!ValidUtil.isValidPassword(newPassword)){
            return ResponseVO.response(null,"The new password format is incorrect", 400);
        }
        return userService.changePassword(email,originalPassword,newPassword);
    }
}
