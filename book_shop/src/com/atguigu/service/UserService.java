package com.atguigu.service;

import com.atguigu.pojo.User;


public interface UserService {


    public void registUser(User user);


    public User loginUser(User user);


    public boolean existsUsername(String username);
}
