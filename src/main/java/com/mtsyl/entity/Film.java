package com.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_film")
public class Film {

    private int id;

    private String synopsis;

    private String name;

    private String director;

    private String actor;

    private int tickets;

    private String images;

    private int type;

    private String start;

    private Integer duration;

}
