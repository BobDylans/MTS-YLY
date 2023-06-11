package com.mtsyl.controller;

import com.mtsyl.common.Result;
import com.mtsyl.entity.Voucher;
import com.mtsyl.service.VoucherService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    VoucherService voucherService;
    @PostMapping
    public Result addVoucher(@RequestBody Voucher voucher) {
        voucherService.save(voucher);
        return Result.ok(voucher.getId());
    }
    @GetMapping("/list/{filmId}")
    public Result queryVoucherOfShop(@PathVariable("filmId") Long filmId) {
        return voucherService.selectVouchersByFilmId(filmId);
    }
}
