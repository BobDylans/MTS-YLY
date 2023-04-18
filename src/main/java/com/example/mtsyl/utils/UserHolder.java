package com.example.mtsyl.utils;

import com.example.mtsyl.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author 20179
 */
@Component
public class UserHolder {
    private static final ThreadLocal<User> USER_THREAD_LOCAL;

    static {
        USER_THREAD_LOCAL = new ThreadLocal<>();
    }

    public static void put(User user)  {
        System.out.println(Thread.currentThread().getId());
        USER_THREAD_LOCAL.set(user);
    }

    public static User getCurrentUser() {
        System.out.println(Thread.currentThread().getId());
        return USER_THREAD_LOCAL.get();
    }

    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }
}
