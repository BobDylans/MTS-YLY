package com.mtsyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtsyl.entity.VoucherOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.Base64;

@Mapper
public interface VoucherOrderMapper extends BaseMapper<VoucherOrder> {
}
