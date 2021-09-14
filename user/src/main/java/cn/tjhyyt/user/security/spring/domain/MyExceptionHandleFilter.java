package cn.tjhyyt.user.security.spring.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class MyExceptionHandleFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().write(e.getMessage());
        }
    }
}
