package com.example.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author 20179
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName
public class User {

    @TableId(value = "id")
    private Long id;

    private String username;

    private String phone;

    private String password;

    private Timestamp createTime;

    private Timestamp updateTime;

}
