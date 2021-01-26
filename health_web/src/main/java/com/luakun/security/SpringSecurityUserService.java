package com.luakun.security;


import com.luakun.pojo.Permission;
import com.luakun.pojo.Role;
import com.luakun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/25  20:15
 * Description: SpringSecurityUserService
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        com.luakun.pojo.User user = userService.findByUserName(username);
            //远程调用用户服务，根据用户名查询用户信息
        if(user == null){
            return null;
        }
        /**
         * 这个GrantedAuthority是存储这用户的所有权限
         */
        List<GrantedAuthority> list = new ArrayList<>();
        /**
         *获取当前所有的角色
         */
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        UserDetails userDetails = new User(username,user.getPassword(),list);


        /**
         * User()
         * 1：指定用户名
         * 2：指定密码（SpringSecurity会自动对密码进行校验）
         * 3：传递授予的角色和权限
         */



        return userDetails;
    }
}
