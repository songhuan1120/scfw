package cn.tjhyyt.user.security.spring.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Slf4j
public class MyAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder passwordEncoder;
    public MyAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (userDetails == null){
            log.warn("User not found with userName:{}",userName);
//            throw new AuthenticationException("e");
        }
        if (!password.equals(userDetails.getPassword())){
            log.warn("Wrong password");
//            throw new WrongPasswordException(AuthErrorEnum.LOGIN_PASSWORD_ERROR.getMessage());
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails,password,authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
