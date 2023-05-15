package com.mtsyl.service.impl;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mtsyl.entity.LoginInfo;
import com.mtsyl.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

import static com.mtsyl.utils.RedisConstants.WX_SESSION_ID;

@Component
public class WxService {
    @Autowired
    LoginInfoService loginInfoService;

    public String wxDecrypt(String encryptedData, String sessionId, String vi) throws Exception {
        // 开始解密

        //1.先从数据库中取出我们需要的res,并解密成openId
        LambdaQueryWrapper<LoginInfo> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(LoginInfo::getKey,WX_SESSION_ID+sessionId);
        String json = loginInfoService.getOne(wrapper).getValue();
        JSONObject jsonObject = JSON.parseObject(json);
        String sessionKey = (String) jsonObject.get("session_key");
        byte[] encData = cn.hutool.core.codec.Base64.decode(encryptedData);
        byte[] iv = cn.hutool.core.codec.Base64.decode(vi);
        byte[] key = Base64.decode(sessionKey);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return new String(cipher.doFinal(encData), "UTF-8");
    }





    //生成随机用户名，数字和字母组成,
    public String getStringRandom(int length) {

        StringBuilder val = new StringBuilder();
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }
}