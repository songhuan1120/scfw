package huan11.song.microservicemovie.controller;

import huan11.song.microservicemovie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("movie")
@RestController
public class MovieController {

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/find/{id}")
    public User findUserById(@PathVariable Integer id) {
//        ServiceInstance instance = loadBalancerClient.choose("micro-user");
        User user = restTemplate.getForObject("http://micro-user/user/find/{id}",User.class,id);
        return user;
    }
}
