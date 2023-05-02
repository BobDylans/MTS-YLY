package com.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

/**
 * @author 20179
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_user")
public class User {

    @TableId(value = "id")
    private Long id;

    private String nickName;

    private String phone;

    private String password;
    private String openId;


}
