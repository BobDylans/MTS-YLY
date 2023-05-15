package com.mtsyl.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.entity.WXAuth;
import com.mtsyl.service.UserService;
import com.mtsyl.utils.WechatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 20179
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


//    //获取会话信息
//    @GetMapping
//    public Result getSessionId(String code){
//        return userService.getSessionId(code);
//    }
//    @PostMapping("/authLogin")
//    public Result authLogin(@RequestBody WXAuth wxAuth){
//        Result result=userService.authLogin(wxAuth);
//        log.info("{}",result);
//        return result;
//
//    }



    @PostMapping("/wx/login")
    public Result user_login(@RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "rawData", required = false) String rawData,
                            @RequestParam(value = "signature", required = false) String signature) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return Result.fail("检验签名失败");
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User::getOpenId, openid);
        User user = userService.getOne(wrapper);
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            user = new User();
            user.setOpenId(openid);
            user.setNickName(nickName);
            userService.save(user);
        }
        return Result.ok(user);
    }


}
