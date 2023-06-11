package com.mtsyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtsyl.common.Result;
import com.mtsyl.entity.Film;
import com.mtsyl.mapper.FilmMapper;

public interface FilmService extends IService<Film> {
    Result getByType(Integer typeId);

}
