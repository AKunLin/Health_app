package com.luakun.controller;
import com.luakun.constant.MessageConstant;
import com.luakun.entity.Result;
import com.luakun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/25  22:46
 * Description: UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //获取当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername() throws Exception {
        try {
            org.springframework.security.core.userdetails.User user =
                    (org.springframework.security.core.userdetails.User)
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}