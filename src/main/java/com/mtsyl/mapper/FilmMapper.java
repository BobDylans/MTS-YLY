package com.mtsyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtsyl.entity.Film;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilmMapper extends BaseMapper<Film> {
}
