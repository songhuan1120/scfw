package huan11.song.microservicecommon.client.user;

import huan11.song.microservicecommon.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("micro-user")
//@FeignClient(name = "aaa",url = "http://micro-user")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user/find/{id}")
    User findUserById(@PathVariable Integer id);
}
