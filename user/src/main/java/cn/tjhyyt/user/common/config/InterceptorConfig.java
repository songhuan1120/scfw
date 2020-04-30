package cn.tjhyyt.user.common.config;


import cn.tjhyyt.user.common.filter.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport
{
    /**
     * 加载静态资源
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        System.out.println("加载静态资源");
//        registry.addResourceHandler("/testStatic/**").addResourceLocations("classpath:/testStatic/");
//    }

    @Autowired
    private JwtInterceptor jwtInterceptor;
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器要声明拦截器对象和要拦截的请求
        //特别注释，如果权限拦截器对于此路径是有权限的，这个地方会失效
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/login/user")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/change")
                .excludePathPatterns("/admin");
    }

}