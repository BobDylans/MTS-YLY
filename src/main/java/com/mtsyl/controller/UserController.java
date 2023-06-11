package com.mtsyl.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.service.UserService;
import com.mtsyl.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 20179
 */
@RestController
@Slf4j
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    private static final String appid ="wx4d0bf0137e54de77";
    private static final String secret="fb20f9420989effbd6ffe3ad6c768f2d";


    @PostMapping("/login")
    public Result login(@RequestBody User user){

        User res = userService.findByUserNameAndPassWord(user.getNickName(), user.getPassword());

        return Result.ok(res);
    }


    @PostMapping("/test")
    public void test(@RequestParam("code") String code,
                     @RequestParam("phone") String phone,
                     @RequestParam("nickName")String nickName){
        System.out.println(code+phone+nickName);
    }

    @PostMapping("/wx/login")
    public Result user_login(@RequestParam(value = "js_code") String code,
                             @RequestParam(value="nickName",required = false) String nickName) {

        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        String codeUpper = code.toUpperCase();
        JSONObject SessionKeyOpenId = WeChatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");

        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        QueryWrapper<User> lqw = new QueryWrapper<>();
        lqw.eq("openid", openid);
        User user = userService.getOne(lqw);
        if (user == null) {
            // 用户信息入库

            user = new User();
            user.setOpenid(openid);
            user.setNickName(nickName);
            userService.save(user);
        }
        return Result.ok(user);
    }



}
