package cn.tjhyyt.user.security.spring.hei;

import cn.tjhyyt.user.common.utli.JwtUtil;
import cn.tjhyyt.user.security.spring.domain.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SongLoginSuccessHandler implements AuthenticationSuccessHandler {
    private SongUserDetailsService userDetailsService;

    public SongLoginSuccessHandler(SongUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
        String token = JwtUtil.generateToken(String.valueOf(myUserDetails.getId()));
        response.setHeader("Authorization", token);
    }
}
