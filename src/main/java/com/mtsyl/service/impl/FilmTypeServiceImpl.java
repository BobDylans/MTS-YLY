package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.entity.FilmType;
import com.mtsyl.mapper.FileTypeMapper;
import com.mtsyl.service.FilmTypeService;
import org.springframework.stereotype.Service;

@Service
public class FilmTypeServiceImpl extends ServiceImpl<FileTypeMapper,FilmType> implements FilmTypeService {
}
