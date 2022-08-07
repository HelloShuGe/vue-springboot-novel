package com.yayanovel.controller;

import com.alibaba.fastjson.JSONObject;
import com.yayanovel.annocation.PassToken;
import com.yayanovel.controller.viewVO.LoginVO;
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
     * @param loginVO
     * @param response
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "登录", notes="登录")
    @RequestMapping(value="api/login", method = RequestMethod.POST)
    public ResponseVO login(@RequestBody LoginVO loginVO, HttpServletResponse response){
        UserInfo user = userService.selectByEmail(loginVO.getUsername());
        if (user == null){
            logger.info("登录失败，用户未注册");
            return ResponseVO.response(null,"The login failed and the user was not registered!", 400);
        }
        if (!user.getUserPassword().equals(loginVO.getPassword())) {
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
                data.put("username", user.getUserEmail());
                logger.info("登录成功");
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                return ResponseVO.response(data,"The login was successful", 200);
            }
        }
    }
    /**
     * 注册接口，首先传入用户email和用户密码，存入数据库，
     * 给邮箱发送验证码
     * @param userInfo 包括userEmail和password
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "注册", notes="注册")
    @RequestMapping(value="api/register",method = RequestMethod.POST)
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
            return ResponseVO.response(null,"The password is not in the correct format,Greater than or equal to 8 digits, must contain numbers and letters!", 400);
        }
        return userService.register(userInfo);
    }
    /**
     * 注册接口，首先传入用户email和用户密码，存入数据库，
     * 给邮箱发送验证码
     * @param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "获取验证码", notes="获取验证码")
    @RequestMapping(value="api/getcode",method = RequestMethod.POST)
    public ResponseVO getCode(@RequestBody UserInfo userInfo){
        //参数校验
        String userEmail = (String)userInfo.getUserEmail();
        if (!ValidUtil.isValidEmail(userEmail)){
            logger.info("邮箱格式不正确");
            return ResponseVO.response(null,"The mailbox is not in the correct format!", 400);
        }
        String userPassword = (String)userInfo.getUserPassword();
        if (!ValidUtil.isValidPassword(userPassword)){
            logger.info("密码格式不正确");
            return ResponseVO.response(null,"The password is not in the correct format,Greater than or equal to 8 digits, must contain numbers and letters!", 400);
        }
        return userService.getCode(userInfo);
    }


    /**
     * 修改密码
     * @param passwordChangeVO
     * @return
     */
    @PassToken
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
