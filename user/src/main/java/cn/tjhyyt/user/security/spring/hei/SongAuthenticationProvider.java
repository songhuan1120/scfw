package cn.tjhyyt.user.security.spring.hei;

import cn.tjhyyt.user.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class SongAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    public SongAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        authentication.getPrincipal()
//        UserDetails user = userService.getUserLoginInfo(username);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, jwt, user.getAuthorities());
//        return token;
        System.out.println("SongAuthenticationProvider"+"authenticate");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
