package com.mtsyl.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtsyl.common.Result;
import com.mtsyl.entity.Film;
import com.mtsyl.entity.FilmType;
import com.mtsyl.entity.Type;
import com.mtsyl.service.FilmService;
import com.mtsyl.service.FilmTypeService;
import com.mtsyl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    FilmService filmService;
    @Autowired
    TypeService typeService;
    @Autowired
    FilmTypeService filmTypeService;

    @GetMapping("/get{id}")
    public Result getFilm(@PathVariable Long id){
        Film film = filmService.getById(id);

        return Result.ok(film);
    }
    @GetMapping("/getAll/{pageId}/{limit}")
    public Result getAllFilm(@PathVariable int page,@PathVariable int limit){
        //分页查询
        Page<Film> page1 = filmService.page(new Page<>(page, limit));
        return Result.ok(page1);
    }
    @PostMapping("/add")
    public Result addFilm(@RequestBody Film film){
        filmService.save(film);
        return Result.ok();
    }

    @GetMapping("/film/delete/{id}")
    public Result deleteFilm(@PathVariable Long id){
        filmService.removeById(id);
        return Result.ok();
    }


    @GetMapping("/getByType/{typeId}")
    public Result getByType(@PathVariable Integer typeId){
        LambdaQueryWrapper<Film> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Film::getTypeId,typeId);
        List<Film> filmList = filmService.list(wrapper);
        return Result.ok(filmList);
    }



}
