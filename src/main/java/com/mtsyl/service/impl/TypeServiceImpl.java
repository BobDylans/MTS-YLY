package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.entity.Type;
import com.mtsyl.mapper.TypeMapper;
import com.mtsyl.service.TypeService;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper,Type> implements TypeService {
}
