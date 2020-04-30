package cn.tjhyyt.user.common.config;

import cn.tjhyyt.common.model.dao.Permission;
import cn.tjhyyt.common.model.dao.Resource;
import cn.tjhyyt.user.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ShiroConfigurationUpdate {
    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    ResourceService resourceService;

    /**
     * 动态更新新的权限
     * @param
     */
    private synchronized void updatePermission() {
        AbstractShiroFilter shiroFilter = null;
        try {
            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            // 获取过滤管理器
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager filterManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            //清空拦截管理器中的存储
            filterManager.getFilterChains().clear();
            /*
            清空拦截工厂中的存储,如果不清空这里,还会把之前的带进去
            ps:如果仅仅是更新的话,可以根据这里的 map 遍历数据修改,重新整理好权限再一起添加
             */
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            // 相当于新建的 map, 因为已经清空了
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            Map<String, String> filterMap=new HashMap<>();
            //获取新的配置
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
                filterMap.put(path, permsStr);
            }
            //把修改后的 map 放进去
            chains.putAll(filterMap);
            //这个相当于是全量添加
            for (Map.Entry<String, String> entry : filterMap.entrySet()) {
                //要拦截的地址
                String url = entry.getKey().trim().replace(" ", "");
                //地址持有的权限
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                //生成拦截
                filterManager.createChain(url, chainDefinition);
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }


}
