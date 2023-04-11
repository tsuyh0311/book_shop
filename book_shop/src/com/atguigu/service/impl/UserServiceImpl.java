package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;


public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.SaveUser(user);
    }

    @Override
    public User loginUser(User user) {

        return userDao.queryByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryByUsername(username)==null){
            return false;//表示用户名可用
        }
        return true;//表示用户名已存在
    }
}
