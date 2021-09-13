package cn.tjhyyt.user.security.spring.domain;

import cn.tjhyyt.user.common.utli.JwtUtil;
import cn.tjhyyt.user.exception.NoneTokenException;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class MyLogoutHandler implements LogoutHandler {
    private String tokenHeader;
    private String header;

    public MyLogoutHandler(String tokenHeader, String header) {
        this.tokenHeader = tokenHeader;
        this.header = header;
    }

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("执行登出操作...");
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            log.warn("请先登录");
            throw new NoneTokenException("无token异常");
        }
        if (!token.startsWith(header)) {
            log.warn("token信息不合法");
            throw new NoneTokenException("token信息不合法");
        }
        Claims claims = JwtUtil.parseToken(token);
        if (claims == null){
            request.setAttribute("userName",null);
        }else {
            String userName = claims.getSubject();
            request.setAttribute("userName",userName);
        }
    }
}
