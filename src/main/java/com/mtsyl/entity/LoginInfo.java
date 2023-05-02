package com.mtsyl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@TableName("logininfo")
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private String key;
    private String value;

}
