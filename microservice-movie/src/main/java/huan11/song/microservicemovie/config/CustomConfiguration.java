package huan11.song.microservicemovie.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import feign.Logger;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

//@Configuration
public class CustomConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
