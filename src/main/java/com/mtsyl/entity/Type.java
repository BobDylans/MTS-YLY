package com.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_film_type")
public class Type {
    private Long id;
    @TableField("type_id")
    private int typeId;
    private  String name;
}
