package com.mtsyl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WXAuth {
 private String encryptedData;
 private String iv;
 private String sessionId;

}
