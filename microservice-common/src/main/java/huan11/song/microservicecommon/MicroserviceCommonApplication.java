package huan11.song.microservicecommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCommonApplication.class, args);
    }

}
