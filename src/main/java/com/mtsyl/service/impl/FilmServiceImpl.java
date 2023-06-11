package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.common.Result;
import com.mtsyl.entity.Film;
import com.mtsyl.mapper.FilmMapper;
import com.mtsyl.service.FilmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {

    @Resource
    FilmMapper filmMapper;
    @Override
    public Result getByType(Integer typeId) {
        QueryWrapper<Film> wrapper=new QueryWrapper();
        wrapper.eq("type",typeId);
        List<Film> films = filmMapper.selectList(wrapper);

        return Result.ok(films);

    }
}
