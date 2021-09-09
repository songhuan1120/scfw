package cn.tjhyyt.user.security.spring.domain;

import cn.tjhyyt.user.common.utli.JwtUtil;
import cn.tjhyyt.user.entity.ParentMenu;
import cn.tjhyyt.user.service.ParentMenuService;
import cn.tjhyyt.user.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private String head;
    private String tokenHeader;
    private ParentMenuService parentMenuService;

    public MyLoginFilter(AuthenticationManager authenticationManager,String head,String tokenHeader,ParentMenuService parentMenuService) {
        this.authenticationManager = authenticationManager;
        this.head = head;
        this.tokenHeader = tokenHeader;
        this.parentMenuService = parentMenuService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //从request中获取username和password，并封装成user
//        String body =  new GetPostRequestContentUtil().getRequestBody(request);
//        User user = (User) ObjectMapperUtil.readValue(body,User.class);
//        if (user == null){
//            log.error("解析出错");
//            return null;
//        }
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String userName = obtainUsername(request);
        String password = obtainPassword(request);
        log.info("用户(登录名)：{} 正在进行登录验证。。。密码：{}",userName,password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,password);
        //提交给自定义的provider组件进行身份验证和授权
        Authentication authentication = authenticationManager.authenticate(token);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //设置安全上下文。在当前的线程中，任何一处都可以通过SecurityContextHolder来获取当前用户认证成功的Authentication对象
        SecurityContextHolder.getContext().setAuthentication(authResult);
        MyUserDetails userDetails = (MyUserDetails) authResult.getPrincipal();
        //使用JWT快速生成token
        String token = JwtUtil.generateToken(userDetails.getId()+"");
        //根据当前用户的权限可以获取当前用户可以查看的父菜单以及子菜单。(这里在UserDetailsService中由于级联查询，该用户下的所有信息已经查出)
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<ParentMenu> parentMenus = new ArrayList<>();
        for (GrantedAuthority authority : authorities){
            String permissionId = authority.getAuthority();
            List<ParentMenu> parentMenuList = parentMenuService.selectMenusByPName(permissionId);
            for (ParentMenu parentMenu : parentMenuList){
                if (!parentMenus.contains(parentMenu)){
                    parentMenus.add(parentMenu);
                }
            }
        }
        //返回在response header 中返回token，并且返回用户可以查看的菜单数据
        response.setHeader(tokenHeader,head+token);
        response.setCharacterEncoding("utf-8");
//        response.getWriter().write(ObjectMapperUtil.writeAsString(parentMenus));
    }
}
