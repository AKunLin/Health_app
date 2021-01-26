package com.luakun.dao;

import com.luakun.pojo.Permission;


import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/25  20:52
 * Description: PermissionDao
 */

public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
