package cn.tjhyyt.user.security.spring.config;

import cn.tjhyyt.user.anotation.SpringSecurityEnv;
import cn.tjhyyt.user.security.spring.domain.*;
import cn.tjhyyt.user.service.ParentMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@SpringSecurityEnv
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ParentMenuService parentMenuService;

    private String tokenHeader = "authorize";
    private String head = "token";

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
//        http.authorizeRequests()
//        //不需要身份认证
//                .antMatchers("/", "/home","/toLogin","/**/customer/**").permitAll()
//                .antMatchers("/js/**", "/css/**", "/images/**", "/fronts/**", "/doc/**", "/toLogin").permitAll()
//                .antMatchers("/user/**").hasAnyRole("USER")
//                .antMatchers("/login").permitAll()
//                //.hasIpAddress()//读取配置权限配置
//                .antMatchers("/**").access("hasRole('ADMIN')")
//                .anyRequest().authenticated()
//                //自定义登录界面
//                .and().formLogin().loginPage("/toLogin").loginProcessingUrl("/login").failureUrl("/toLogin?error").permitAll()
//                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .and().exceptionHandling().accessDeniedPage("/toLogin?deny")
//                .and().httpBasic()
//                .and().sessionManagement().invalidSessionUrl("/toLogin")
//                .and().csrf().disable();
        http.csrf().disable()
//                .cors()
//                .and()
//                .formLogin()
//                    .loginPage("/login.html")
//                .and()
                .exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                    .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                    .antMatchers("/").permitAll()
                    .antMatchers("/hi/authorize4").hasAuthority("1")
                    .antMatchers("/login1").permitAll()
//                    .antMatchers("/toLogin").permitAll()
                .anyRequest()
                .authenticated()
                .and()
//                .addFilterBefore(new MyExceptionHandleFilter(), LogoutFilter.class)
                .addFilter(new MyLoginFilter(authenticationManager(),head,tokenHeader,parentMenuService));
                //.addFilter(new MyAuthenticationFilter(authenticationManager(),tokenHeader,head,new MyUserDetailsService()))
                //.addFilterAfter(new MyLogoutFilter(new MyLogoutSuccessHandler(),new MyLogoutHandler(tokenHeader,head)),MyAuthenticationFilter.class);
    }
}
