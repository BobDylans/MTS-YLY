package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.entity.WXAuth;
import com.mtsyl.mapper.UserMapper;
import com.mtsyl.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Resource
    UserMapper userMapper;



    @Override
    public User findByUserNameAndPassWord(String nickName, String password) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("nick_name",nickName).eq("password",password);
        User user = getOne(wrapper);

        return user;
    }
}

