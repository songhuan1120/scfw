package huan11.song.microservicemovie.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
public class TestConfiguration {

//    public static class BazServiceList extends ConfigurationBasedServerList {
//
//        public BazServiceList(IClientConfig config) {
//            super.initWithNiwsConfig(config);
//        }
//
//    }

}

//@Configuration
//class DefaultRibbonConfig {
//
//    @Bean
//    public IRule ribbonRule() {
//        return new BestAvailableRule();
//    }
//
//    @Bean
//    public IPing ribbonPing() {
//        return new PingUrl();
//    }
//
//    @Bean
//    public ServerList<Server> ribbonServerList(IClientConfig config) {
//        return new TestConfiguration.BazServiceList(config);
//    }
//
//    @Bean
//    public ServerListSubsetFilter serverListFilter() {
//        ServerListSubsetFilter filter = new ServerListSubsetFilter();
//        return filter;
//    }
//
//}