package com.mtsyl.utils;

import cn.hutool.crypto.digest.MD5;
import com.mtsyl.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author 20179
 */
@Component
public class MD5Util {

    public static String getMd5Encode(String data) {
        MD5 md5 = MD5.create();
        return md5.digestHex16(data);
    }

    public static String createUserToken(User user) {
        UserHolder.saveUser(user);
        return getMd5Encode(user.getOpenid() + System.currentTimeMillis());
    }
}
