package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.common.Result;
import com.mtsyl.entity.Voucher;
import com.mtsyl.mapper.VoucherMapper;
import com.mtsyl.service.VoucherOrderService;
import com.mtsyl.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements VoucherService {

    @Autowired
    VoucherMapper voucherMapper;

    @Override
    public Result selectVouchersByFilmId(Long filmId) {
        QueryWrapper<Voucher> wrapper=new QueryWrapper();
        wrapper.eq("film_id",filmId);
        List<Voucher> vouchers = voucherMapper.selectList(wrapper);
        return Result.ok(vouchers);

    }
}
