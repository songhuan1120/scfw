package cn.tjhyyt.user.security.spring.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("当前用户没有访问该资源的权限：{}",accessDeniedException.getMessage());
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("访问被拒-没有权限");
    }
}
