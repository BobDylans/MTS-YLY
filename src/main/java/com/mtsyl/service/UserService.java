package com.mtsyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.entity.WXAuth;
import com.mtsyl.mapper.UserMapper;

public interface UserService extends IService<User> {


    Result getSessionId(String code);

    Result authLogin(WXAuth wxAuth);
}
