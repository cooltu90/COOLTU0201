package com.codingtu.cooltu.dm;

import com.codingtu.cooltu.bean.User;
import com.codingtu.cooltu.processor.annotation.data.Data;

public class UserDataConfig {

    @Data
    public User user(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    @Data
    public User user() {
        return new User();
    }

    @Data
    public User user(String name, int age) {
        User user = new User();
        user.name = name;
        user.age = age;
        return user;
    }

}
