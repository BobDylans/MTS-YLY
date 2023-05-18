package com.mtsyl.entity.po;

import com.mtsyl.entity.User;
import lombok.Data;

@Data
public class WxUserInfo extends User {
    private String code;
}
