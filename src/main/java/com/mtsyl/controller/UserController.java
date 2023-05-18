package com.mtsyl.controller;


import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.entity.WXAuth;
import com.mtsyl.entity.po.WxUserInfo;
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
    private static final String appid ="wx4d0bf0137e54de77";
    private static final String secret="fb20f9420989effbd6ffe3ad6c768f2d";


    @PostMapping("/wx/login")
    public Result user_login(@RequestBody WxUserInfo wxUserInfo) {
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        String jscode2sessionUrl = requestUrl
                + "?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + wxUserInfo.getCode()
                + "&grant_type=authorization_code";
        HttpRequest result = HttpRequest.post(jscode2sessionUrl);
        String resultJSON = result.toString();
        log.info(resultJSON);
        JSONObject jsonObject = JSONObject.parseObject(resultJSON);

        // 3.接收微信接口服务 获取返回的参数
        String openid = jsonObject.get("openid").toString();
        log.info(openid);
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper();
        wrapper.eq(User::getOpenId, openid);
        User user = userService.getOne(wrapper);
        if (user == null) {
            // 用户信息入库
            user = new User();
            user.setOpenId(openid);
            user.setNickName(wxUserInfo.getNickName());
            user.setPhone(wxUserInfo.getPhone());
            userService.save(user);
        }else{
            user.setOpenId(openid);
            user.setNickName(wxUserInfo.getNickName());
            user.setPhone(wxUserInfo.getPhone());
            userService.updateById(user);
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("openId",openid);
        return Result.ok(resultMap);
    }


}
