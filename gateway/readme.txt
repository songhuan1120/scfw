1.构建微服务时，若要eureka的配置中心，则要引用配置中心客户端和eureka客户端
    pom文件中要引用
        注册中心客户端：spring-cloud-starter-netflix-eureka-client
        配置中心客户端：spring-cloud-starter-config
        网关服务zuul：spring-cloud-starter-netflix-zuul

    appliction类上需要添加注解：@EnableEurekaClient @EnableZuulProxy
