package com.atguigu.dao;

import com.atguigu.pojo.User;


public interface UserDao {


    public User queryByUsername(String username);


    public User queryByUsernameAndPassword(String username,String password);

    public int SaveUser(User user);
}
