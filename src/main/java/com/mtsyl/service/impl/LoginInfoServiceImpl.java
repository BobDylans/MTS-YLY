package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.entity.LoginInfo;
import com.mtsyl.mapper.LoginInfoMapper;
import com.mtsyl.service.LoginInfoService;
import org.springframework.stereotype.Service;

@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService {
}
