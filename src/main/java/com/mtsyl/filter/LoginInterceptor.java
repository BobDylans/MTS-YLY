package com.mtsyl.filter;

import com.mtsyl.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor() {
    }

    @Override
    //这时只要获取token,从redis中取出user并进行验证.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //1.判断是否需要拦截
        if(UserHolder.getUser()==null){
            response.setStatus(401);
            return false;
        }
        //2.由用户就放行
        return true;
    }


}
