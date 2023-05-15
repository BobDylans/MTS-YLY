package com.mtsyl.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.common.Result;
import com.mtsyl.entity.LoginInfo;
import com.mtsyl.entity.User;
import com.mtsyl.entity.WXAuth;
import com.mtsyl.mapper.UserMapper;
import com.mtsyl.service.LoginInfoService;
import com.mtsyl.service.UserService;
import com.mtsyl.utils.MD5Util;
import com.mtsyl.utils.SMSUtils;
import com.mtsyl.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.mtsyl.utils.RedisConstants.*;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    LoginInfoService loginInfoService;
    @Autowired
    WxService wxService;


    private static final String appid ="wx4d0bf0137e54de77";
    private static final String secret="fb20f9420989effbd6ffe3ad6c768f2d";


    @Override
    public Result getSessionId(String code) {
        /**
         * 1.拼接url,微信登陆凭证校验接口
         * 2.发起一个http的调用,获取微信的返回结果
         * 3.生成一个sessionId,返会给前端,作为当前需要登陆的用户的标识
         * 4.生成一个sessionId 用户再次微信登陆的时候,我们可以标识是谁点击微信登录
          */
        String url="https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", code);
        String res = HttpUtil.get(replaceUrl);
        String uuid= UUID.randomUUID().toString();

        //相当于把请求后的res放到了数据库中
        loginInfoService.save(new LoginInfo(WX_SESSION_ID+uuid,res));
        HashMap<String, String> map = new HashMap<>();
        //这里可以直接返回一个res
        map.put("sessionId",uuid);
        return Result.ok(map);
    }

    @Override
    public Result authLogin(WXAuth wxAuth) {
        //1.通过wxauth中的值,要对其进行解密
        //2.解密完成后,会获取微信用户信息没其中包含openId,性别,昵称,头像等信息
        //3.openId 是唯一的,需要去user表中查询openId是否存在;如果存在,登陆成功
        //4.不存在,就注册一个新用户
        //5.使用jwt技术,生成一个token,提供给前端token令牌,用户在下次访问时可以携带同肯来访问
        try {
            String json = wxService.wxDecrypt(wxAuth.getEncryptedData(), wxAuth.getSessionId(), wxAuth.getIv());
            //讲解密后的json转换成user
            User userInfo = JSON.parseObject(json, User.class);

            //从中获取到openId,并根据openId查询数据库
            String openId = userInfo.getOpenId();
            LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(User::getOpenId,openId);
            User user = this.getOne(wrapper);
            //如果未查到,就根据前端传过来的数据注册一个新的
            if (user==null){
                //注册
                this.save(userInfo);
                //返回前端信息
                return Result.ok(userInfo);
            }else {
                //登陆成功,就返回一个openId当作key,下次可以通过这个key来获取用户信息
                return Result.ok(user.getOpenId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.fail("失败");
    }







}
/**
    public String check(HttpServletRequest request){
        System.out.println("get方法");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (WechatUtils.checkSignature(signature, timestamp, nonce)) {
            System.out.println("检验通过");
            return echostr;
        }
        System.out.println("检验不通过");
        return "校验不通过";
    }
 public class WechatUtils {
 /*
 * 规则描述
 *1. 将token、timestamp、nonce三个参数进行字典序排序
 *2. 将三个参数字符串拼接成一个字符串进行sha1加密
 *3. 获得加密后的字符串可与signature对比，标识该请求来源于微信
 */
/**
public static boolean checkSignature(String signature, String timestamp, String nonce) {
    String token = "prince";
    ArrayList<String> list = new ArrayList();
    list.add(nonce);
    list.add(timestamp);
    list.add(token);
    Collections.sort(list);
    StringBuilder sb= new StringBuilder();
    list.forEach(sb::append);
    String result = sb.toString();
    String sha1 = DigestUtils.sha1Hex(result);
    System.out.println(String.format("sha1:%s,signature:%s",sha1,signature));
    return signature.equals(sha1);
}
}



**/