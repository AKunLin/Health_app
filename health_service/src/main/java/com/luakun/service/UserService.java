package com.luakun.service;

import com.luakun.pojo.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/25  20:19
 * Description: UserService
 */
public interface UserService {
    User findByUserName(String username);
}
