package com.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_voucher_order")
public class VoucherOrder {
    private Long id;

    @TableField("user_id")
    private int userId;

    @TableField("voucher_id")
    private int voucherId;

    private int status;

    private String startTime;

    private String endTime;

    private String seat;
    private Integer filmId;

}
