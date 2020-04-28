1.构建配置中心时，若要eureka，则要把配置中心注册到eureka上去，所以配置中心是注册中心的一个客户端
    pom文件中要引用
        配置中心server：spring-cloud-config-server
        注册中心客户端：spring-cloud-starter-netflix-eureka-client