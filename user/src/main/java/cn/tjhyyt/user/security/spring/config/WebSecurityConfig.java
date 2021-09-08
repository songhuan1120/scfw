package cn.tjhyyt.user.security.spring.config;

import cn.tjhyyt.user.security.spring.domain.MyAuthenticationProvider;
import cn.tjhyyt.user.security.spring.domain.MyLoginFilter;
import cn.tjhyyt.user.security.spring.domain.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.head}")
    private String head;

    @Value("${jwt.expired}")
    private boolean expired;

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.permitUris}")
    private String permitUris;

    @Bean
    public UserDetailsService myUserDetailsService(){
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("super.configure(auth)");
        auth.authenticationProvider(new MyAuthenticationProvider(myUserDetailsService(),passwordEncoder()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.out.println("super.configure(web)");
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("super.configure(http)");
        http.csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new MyLoginFilter(authenticationManager(),head,tokenHeader));
    }
}
