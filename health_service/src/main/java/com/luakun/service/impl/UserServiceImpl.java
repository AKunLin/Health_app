package com.luakun.service.impl;

import com.luakun.dao.UserDao;
import com.luakun.pojo.User;
import com.luakun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/25  20:23
 * Description: UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByUserName(String username) {
        User user = userDao.findByName(username);
        return user;
    }
}
