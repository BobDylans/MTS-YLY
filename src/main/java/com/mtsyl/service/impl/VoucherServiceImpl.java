package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.entity.Voucher;
import com.mtsyl.mapper.VoucherMapper;
import com.mtsyl.service.VoucherOrderService;
import com.mtsyl.service.VoucherService;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher> implements VoucherService {
}
