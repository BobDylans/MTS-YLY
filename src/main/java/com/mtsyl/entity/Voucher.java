package com.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_voucher")
public class Voucher {
    private Long id;
    @TableField("film_id")
    private int filmId;
    private String title;
    private String rules;
    @TableField("pay_value")
    private int payValue;
    @TableField("actual_value")
    private int actualValue;
}
