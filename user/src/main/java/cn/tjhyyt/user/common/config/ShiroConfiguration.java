package cn.tjhyyt.user.common.config;

import cn.tjhyyt.common.model.dao.Permission;
import cn.tjhyyt.common.model.dao.Resource;
import cn.tjhyyt.user.common.match.CredentialMatcher;
import cn.tjhyyt.user.common.realm.AuthRealm;
import cn.tjhyyt.user.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
public class ShiroConfiguration {
    @Autowired
    ResourceService resourceService;

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {

        //开始默认的权限配置
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        bean.setSuccessUrl("/index");
        bean.setLoginUrl("/login/user");
        // 设置无权限时跳转的 url;
        bean.setUnauthorizedUrl("/unauthorized/无权限");
        //拦截器，进行配置
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index", "authc");
        // 配置不会被拦截的链接 顺序判断
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser","anon");
        filterChainDefinitionMap.put("/admin","authc");
        filterChainDefinitionMap.put("/unauthorized/**", "anon");
        try{
            //加载数据库中的资源，匹配对应的权限
            //加载资源及对应的权限
            List<Resource> resourceListByMap = resourceService.getResourceAndPermission();
            for(Resource resource:resourceListByMap){
                //作为拦截的k
                String path=resource.getPath();
                //作为拦截器的value
                Set<Permission> permissionSet =resource.getPermissionSet();
                //String[] roles=new String[permissionSet.size()];
                //int i=0;
                StringBuffer perms=new StringBuffer();
                perms.append("perms"+"[");
                for(Permission permission:permissionSet){
                    perms.append(permission.getPermissionName());
                    perms.append(",");
                }
                String permsStr=perms.substring(0,perms.length()-1);
                permsStr=permsStr+"]";
                //路径对应的权限进行配置
                filterChainDefinitionMap.put(path, permsStr);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
