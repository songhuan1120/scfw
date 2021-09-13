package cn.tjhyyt.user.security.spring.domain;

import cn.tjhyyt.user.common.utli.JwtUtil;
import cn.tjhyyt.user.exception.NoneTokenException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyAuthenticationFilter extends BasicAuthenticationFilter {
    private String tokenHeader;
    private String header;
    private UserDetailsService userDetailsService;

    public MyAuthenticationFilter(AuthenticationManager authenticationManager, String tokenHeader, String header, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.tokenHeader = tokenHeader;
        this.header = header;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isEmpty(token)) {
            log.warn("请登录访问");
            throw new NoneTokenException("无token异常");
        }
        if (!token.startsWith(header)) {
            log.warn("token信息不合法");
            throw new NoneTokenException("token信息不合法");
        }
        Claims claims = JwtUtil.parseToken(token);
        if (JwtUtil.isTokenExpired(claims.getExpiration())) {
            log.warn("当前token信息已过期,请重新登录");
            throw new NoneTokenException("当前token信息已过期,请重新登录");
        }
        String userName = claims.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        log.info("用户：{}，正在访问：{}", userName, request.getRequestURI());
        logger.info("authenticated user " + userName + ", setting security context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
        super.doFilterInternal(request, response, chain);
    }

}
