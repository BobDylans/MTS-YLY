package com.mtsyl.controller;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

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
    public Result user_login(@RequestParam("code") String code,
                             @RequestParam("phone") String phone,
                             @RequestParam("nickName")String nickName) {
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口code
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        String jscode2sessionUrl = requestUrl
                + "?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + code
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
        wrapper.eq(User::getOpenid, openid);
        User user = userService.getOne(wrapper);
        if (user == null) {
            // 用户信息入库
            user = new User();
            user.setOpenid(openid);
            user.setNickName(nickName);
            user.setPhone(phone);
            userService.save(user);
        }else{
            user.setOpenid(openid);
            user.setNickName(nickName);
            user.setPhone(phone);
            userService.updateById(user);
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("openId",openid);
        log.info(openid);
        return Result.ok(resultMap);
    }

    @PostMapping("/test")
    public void test(@RequestParam("code") String code,
                     @RequestParam("phone") String phone,
                     @RequestParam("nickName")String nickName){
        System.out.println(code+phone+nickName);
    }


}
