package com.luakun.dao;

import com.luakun.pojo.Role;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/25  20:52
 * Description: RoleDao
 */
public interface RoleDao {

    Set<Role> findRolesByUserId(Integer userId);


}