package com.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_film-type")
public class FilmType {
    private Long id;
    private int filmId;
    private int typeId;

}
