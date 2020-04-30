package cn.tjhyyt.user.controller;



import cn.tjhyyt.common.model.dao.User;
import cn.tjhyyt.user.common.utli.JwtUtil;
import cn.tjhyyt.user.mapper.UserMapper;
import cn.tjhyyt.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(produces = "application/json; charset=utf-8")
public class TestController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "请登录";
    }
    /**
     * 登录
     * @return
     */
    @RequestMapping("/login/user")
    public String loginUser(@RequestParam("userAccount") String userAccount,
                            @RequestParam("password") String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(userAccount, password);
        //User test = userMapper.test(1l);
        //User userPermission = userService.getPermissionById(1l);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            String tokenBack = jwtUtil.generateToken(user.getUserId());
            //User user = (User) subject.getPrincipal();
            //session.setAttribute("user", user);
            Map<String,String> result=new HashMap<>();
            result.put("token",tokenBack);
            return result.toString();
        } catch (Exception e) {
            return "登录失败，请重新登录";
        }
    }

    @RequestMapping("/index")
    public String index() {

        return "进入首页";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {

        return "进入增加";
    }



    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "退出";
    }

    @RequestMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin success";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit() {
        return "edit success";
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public String unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return "非法登录";
    }
}
