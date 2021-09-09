package cn.tjhyyt.user.controller;

import cn.tjhyyt.common.model.dao.User;
import cn.tjhyyt.user.common.utli.JwtUtil;
import cn.tjhyyt.user.entity.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(produces = "application/json; charset=utf-8")
public class LoginController {
    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public String loginUser(@RequestBody Login login) {
        System.out.println("/login");
        System.out.println("/login");
        return "login";
//        UsernamePasswordToken token = new UsernamePasswordToken(userAccount, password);
//        //User test = userMapper.test(1l);
//        //User userPermission = userService.getPermissionById(1l);
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            subject.login(token);
//            User user = (User) subject.getPrincipal();
//            String tokenBack = JwtUtil.generateToken(user.getUserId()+"");
//            //User user = (User) subject.getPrincipal();
//            //session.setAttribute("user", user);
//            Map<String,String> result=new HashMap<>();
//            result.put("token",tokenBack);
//            return result.toString();
//        } catch (Exception e) {
//            return "登录失败，请重新登录";
//        }
    }
}
