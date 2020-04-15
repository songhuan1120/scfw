package huan11.song.microservicemovie.controller;

import huan11.song.microservicemovie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("movie")
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/find/{id}")
    public User findUserById(@PathVariable Integer id) {
        User user = restTemplate.getForObject("http://localhost:8000/user/find/{id}",User.class,id);
        return user;
    }
}
