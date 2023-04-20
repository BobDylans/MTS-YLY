package com.mtsyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.mapper.UserMapper;

public interface UserService extends IService<User> {
    Result Login(User loginParam);

    Result LoginByPassword(User loginParam);

    Result sendMsg(String phone);

    Result register(User loginParam);

    Result forgetPassword(User loginParam);
}
