package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.entity.Film;
import com.mtsyl.mapper.FilmMapper;
import com.mtsyl.service.FilmService;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {
}
