//package com.mtsyl.filter;
//
//import cn.hutool.core.bean.BeanUtil;
//import com.mtsyl.entity.User;
//import com.mtsyl.utils.UserHolder;
//import com.mtsyl.utils.RedisConstants;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//
//public class RefreshTokenInterceptor implements HandlerInterceptor {
//
//    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
//        this.stringRedisTemplate = stringRedisTemplate;
//    }
//
//    private StringRedisTemplate stringRedisTemplate;
//
//    public RefreshTokenInterceptor() {
//    }
//
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //从前端得header中获取token
//        String token = request.getHeader("authorization");
//        //拼接token
//        String key= RedisConstants.LOGIN_USER_KEY+token;
//        //下面得检验都要放行,只是不满足的不添加用户信息到线程中,让他们在之后被拦截即可
//        if(token==null){
//            return true;
//        }
//        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
//
//        if (userMap.isEmpty()){
//            return true;
//        }
//        User userDTO = BeanUtil.fillBeanWithMap(userMap, new User(), false);
//        //取出后就可以存到当前线程中,可以在之后被放行
//        UserHolder.saveUser(userDTO);
//        stringRedisTemplate.expire(key, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
//        return true;
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        UserHolder.removeUser();
//    }
//
//}
