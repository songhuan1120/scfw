package cn.tjhyyt.user.security.spring.config;

import cn.tjhyyt.user.anotation.SpringSecurityEnv;
import cn.tjhyyt.user.security.spring.domain.*;
import cn.tjhyyt.user.service.ParentMenuService;
import cn.tjhyyt.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@SpringSecurityEnv
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ParentMenuService parentMenuService;

    private String tokenHeader = "authorize";
    private String head = "token";

//    @Value("${jwt.tokenHeader}")
//    private String tokenHeader;
//
//    @Value("${jwt.head}")
//    private String head;
//
//    @Value("${jwt.expired}")
//    private boolean expired;
//
//    @Value("${jwt.expiration}")
//    private int expiration;
//
//    @Value("${jwt.permitUris}")
//    private String permitUris;

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
                .exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/toLogin").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/toLogin")
                    .and()
                .addFilterBefore(new MyExceptionHandleFilter(), LogoutFilter.class)
                .addFilter(new MyLoginFilter(authenticationManager(),head,tokenHeader,parentMenuService));
                //.addFilter(new MyAuthenticationFilter(authenticationManager(),tokenHeader,head,new MyUserDetailsService()))
                //.addFilterAfter(new MyLogoutFilter(new MyLogoutSuccessHandler(),new MyLogoutHandler(tokenHeader,head)),MyAuthenticationFilter.class);
    }
}
