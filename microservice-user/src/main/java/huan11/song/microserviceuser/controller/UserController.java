package huan11.song.microserviceuser.controller;

import huan11.song.microserviceuser.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

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
        return user;
    }
}
