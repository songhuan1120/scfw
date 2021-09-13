package cn.tjhyyt.user.security.spring.domain;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyLogoutFilter extends LogoutFilter {
    private MyLogoutSuccessHandler logoutSuccessHandler;
    private MyLogoutHandler logoutHandler;
//    private String filterProcessesUrl;



    public MyLogoutFilter(MyLogoutSuccessHandler logoutSuccessHandler, MyLogoutHandler logoutHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.logoutHandler = logoutHandler;
    }

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        super.doFilterInternal(request, response, chain);
    }
}
