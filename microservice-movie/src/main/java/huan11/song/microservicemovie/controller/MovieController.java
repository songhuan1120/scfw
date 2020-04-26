package huan11.song.microservicemovie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import huan11.song.microservicecommon.client.user.UserClient;
import huan11.song.microservicecommon.model.User;
import huan11.song.microservicecommon.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("movie")
@RestController
//@RibbonClient(name="micro-user",configuration = CustomConfiguration.class)
//@RibbonClient(name="micro-user")
public class MovieController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
//
//    @Autowired
////    @LoadBalanced
//    private RestTemplate restTemplate;

//    @Autowired
//    private UserClient userClient;
//
//    @GetMapping("/user/find/{id}")
//    @HystrixCommand(fallbackMethod = "defaultStores")
//    public User findRandomUserById(@PathVariable Integer id) {
////        ServiceInstance instance = loadBalancerClient.choose("micro-user");
////        User user = restTemplate.getForObject("http://micro-user/user/find/{id}",User.class,id);
//        User user = userClient.findUserById(id);
//        System.out.println("movie获取到的user："+ JsonUtil.parseJsonStr(user));
//        return user;
//    }
//
//    public User defaultStores(@PathVariable Integer id) {
//        User defaultUser = new User();
//        defaultUser.setId(0);
//        defaultUser.setName("默认用户");
//        defaultUser.setNickname("默认nick");
//        return defaultUser;
//    }

//    @GetMapping("/user/find/{id}/default")
//    public User findDefaultUserById(@PathVariable Integer id) {
////        ServiceInstance instance = loadBalancerClient.choose("micro-user");
//        User user = restTemplate.getForObject("http://micro-user/user/find/{id}",User.class,id);
//        return user;
//    }

    @GetMapping("printRibbon")
    public void printRibbon() {
        ServiceInstance instance = loadBalancerClient.choose("micro-user");
        String host = instance.getHost();
        int port = instance.getPort();
        String instanceId = instance.getInstanceId();
        String serviceId = instance.getServiceId();
        URI uri = instance.getUri();
        URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));

        System.out.println("host：" + host);
        System.out.println("port：" + port);
        System.out.println("instanceId：" + instanceId);
        System.out.println("serviceId：" + serviceId);
        System.out.println("uri：" + uri);
        System.out.println("storesUri：" + storesUri);
    }
}
