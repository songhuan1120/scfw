package huan11.song.microserviceuser.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import huan11.song.microservicecommon.model.User;
import huan11.song.microservicecommon.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

//    @Autowired
//    private EurekaClient discoveryClient;

//    @GetMapping("test")
//    public String serviceUrl() {
//        InstanceInfo instance = discoveryClient.getNextServerFromEureka("micro-user", false);
//        return instance.getHomePageUrl();
//    }

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable Integer id) {
        User user = new User();
        if (id == 1) {
            user.setId(1);
            user.setName("name1");
            user.setNickname("nickname1");
        } else {
            user.setId(2);
            user.setName("name2");
            user.setNickname("nickname2");
        }
        System.out.println("获取User："+ JsonUtil.parseJsonStr(user));
        return user;
    }

    @GetMapping("find/admin/{id}")
    public User findAdminUser(@PathVariable Integer id) {
        User user = new User();
        if (id == 1) {
            user.setId(1);
            user.setName("name1");
            user.setNickname("nickname1");
        } else {
            user.setId(2);
            user.setName("name2");
            user.setNickname("nickname2");
        }
        System.out.println("获取User："+ JsonUtil.parseJsonStr(user));
        return user;
    }

    @RequestMapping("/login")
    public String login() {
        return "请登录";
    }


}
