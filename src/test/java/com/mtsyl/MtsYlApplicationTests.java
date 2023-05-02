package com.mtsyl;

import com.mtsyl.service.FilmService;
import com.mtsyl.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MtsYlApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    FilmService filmService;

    @Test
    void contextLoads() {
        System.out.println(userService.list());
    }



}
