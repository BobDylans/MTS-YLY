package com.mtsyl.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtsyl.common.Result;
import com.mtsyl.entity.Film;
import com.mtsyl.entity.Voucher;
import com.mtsyl.entity.VoucherOrder;
import com.mtsyl.service.FilmService;
import com.mtsyl.service.VoucherOrderService;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    VoucherOrderService voucherOrderService;

    @Autowired
    FilmService filmService;




    //分页查询所有订单
    @GetMapping("/getOrders/{page}/{limit}")
    public Result getOrders(@PathVariable int page,@PathVariable int limit){
        //分页查询
        Page<VoucherOrder> page1 = voucherOrderService.page(new Page<>(page, limit));
        return Result.ok(page1);
    }

    //新增订单
    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody VoucherOrder voucherOrder){

        //获取对应的电影信息,并将订单的开始时间设置的和Film一样
        LambdaQueryWrapper<Film> wrapper=new LambdaQueryWrapper();
        wrapper.eq(Film::getId,voucherOrder.getFilmId());
        Film film = filmService.getOne(wrapper);
        voucherOrder.setEndTime(film.getStartTime());
        //并将电影票减一
        boolean success = filmService.update()
                .setSql("tickets = tickets-1")
                .eq("id", voucherOrder.getFilmId())
                .gt("tickets", 0)//乐观锁方案,在修改前要先判断库存量是否大于0;如果大于0才进行修改.
                .update();
        voucherOrderService.save(voucherOrder);
        return Result.ok();

    }




}
