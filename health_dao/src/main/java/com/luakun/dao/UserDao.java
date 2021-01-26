package com.luakun.dao;

import com.luakun.pojo.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/25  20:50
 * Description: UserDao
 */
public interface UserDao {
    User findByName(String username);
}
