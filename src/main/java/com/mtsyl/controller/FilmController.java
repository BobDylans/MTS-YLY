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

    //根据id查询
    @GetMapping("/get/{id}")
    public Result getFilm(@PathVariable Long id){
        Film film = filmService.getById(id);

        return Result.ok(film);
    }
    //分页查询全部

    @GetMapping("/getAll/{pageId}/{limit}")
    public Result getAllFilm(@PathVariable int pageId,@PathVariable int limit){
        //分页查询
        Page<Film> page =  filmService.page(new Page<>(pageId, limit));
        return Result.ok(page);
    }
    //新增电影
    @PostMapping("/add")
    public Result addFilm(@RequestBody Film film){
        filmService.save(film);
        return Result.ok();
    }


    //删除电影
    @GetMapping("/film/delete/{id}")
    public Result deleteFilm(@PathVariable Long id){
        filmService.removeById(id);
        return Result.ok();
    }

    //根据电影类型进行查询
    @GetMapping("/getByType/{typeId}")
    public Result getByType(@PathVariable Integer typeId){
        return filmService.getByType(typeId);
    }
    @GetMapping("/getAll")
    public Result getAll(){
        List<Film> films = filmService.list();
        return Result.ok(films);
    }



}
