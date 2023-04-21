package com.mtsyl.controller;


import com.mtsyl.common.Result;
import com.mtsyl.entity.User;
import com.mtsyl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 20179
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;


    //用户的增删改查操作
    @PostMapping("/code")
    public Result loginByPhone(

            @RequestBody User loginParam
    ) {
        return userService.Login(loginParam);
    }


    @PostMapping("/password")
    public Result loginByPassword(

            @RequestBody
                    User loginParam) {
        return userService.LoginByPassword(loginParam);
    }


    @GetMapping("/sendMsg")
    public Result sendMsg(

                    String phone) {
        return userService.sendMsg(phone);

    }


    @PostMapping("/register")
    public Result register(

            @RequestBody User loginParam
    ) {
        return userService.register(loginParam);

    }

    @PostMapping("/forget/password")
    public Result forgetPassword(
            @RequestBody
                    User loginParam
    ) {
        return userService.forgetPassword(loginParam);
    }

}
