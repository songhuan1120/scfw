package cn.tjhyyt.user.security.spring.hei;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

public class SongLoginConfigurer <T extends SongLoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {
    private SongUsernamePasswordAuthenticationFilter authFilter;

    public SongLoginConfigurer() {
        this.authFilter = new SongUsernamePasswordAuthenticationFilter();
    }

    @Override
    public void configure(B http) throws Exception {
        //设置Filter使用的AuthenticationManager,这里取公共的即可
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //设置失败的Handler
        authFilter.setAuthenticationFailureHandler(new SongLoginFailureHandler());
        //不将认证后的context放入session
        authFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

        SongUsernamePasswordAuthenticationFilter filter = postProcess(authFilter);
        //指定Filter的位置
        http.addFilterAfter(filter, LogoutFilter.class);
    }
    //设置成功的Handler，这个handler定义成Bean，所以从外面set进来
    public SongLoginConfigurer<T,B> loginSuccessHandler(AuthenticationSuccessHandler authSuccessHandler){
        authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        return this;
    }
}
