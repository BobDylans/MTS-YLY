package com.mtsyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtsyl.common.Result;
import com.mtsyl.entity.Voucher;
import org.springframework.stereotype.Service;


public interface VoucherService extends IService<Voucher> {


    Result selectVouchersByFilmId(Long filmId);

}
