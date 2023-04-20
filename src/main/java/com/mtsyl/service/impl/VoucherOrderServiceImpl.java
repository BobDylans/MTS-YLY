package com.mtsyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtsyl.entity.Voucher;
import com.mtsyl.entity.VoucherOrder;
import com.mtsyl.mapper.VoucherOrderMapper;
import com.mtsyl.service.VoucherOrderService;
import com.mtsyl.service.VoucherService;
import org.springframework.stereotype.Service;

@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements VoucherOrderService {
}
