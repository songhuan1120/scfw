package cn.tjhyyt.user.common.filter;

import cn.tjhyyt.user.common.utli.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
public class JwtInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //无论如何都放行。具体能不能操作还是在具体的操作中去判断。
        //拦截器只是负责把头请求头中包含token的令牌进行一个解析验证。
        String header = request.getHeader("token");
        if(header!=null && !"".equals(header)){
            try{
                Claims claims = JwtUtil.parseToken(header);
            } catch (Exception e){
                throw new RuntimeException("令牌不正确！");
            }
        }else{
            throw new RuntimeException("非法登录！");
        }
        return true;
    }
}
