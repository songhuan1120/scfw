package cn.tjhyyt.user.common.realm;

import cn.tjhyyt.common.model.dao.Permission;
import cn.tjhyyt.common.model.dao.Role;
import cn.tjhyyt.common.model.dao.User;
import cn.tjhyyt.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    // 授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        List<String> permissionList = new ArrayList<>();
//        List<String> roleNameList = new ArrayList<>();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        userService.getPermissionById()
//        info.addStringPermissions(permissionList);
//        info.addRoles(roleNameList);
//        return info;
//    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
//        User userPermission = userService.getPermissionById(user.getUserId());
        User userPermission = new User();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet = userPermission.getRoleSet();
        if (!CollectionUtils.isEmpty(roleSet)) {
            for(Role role : roleSet) {
                roleNameList.add(role.getRoleName());
                Set<Permission> permissionSet = role.getPermissionSet();
                if (!CollectionUtils.isEmpty(permissionSet)) {
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getPermissionName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }


    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user=null;
        String passWord="";
        try{
//            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//            String username = usernamePasswordToken.getUsername();
//            List<User> userListByMap = userService.getUserListByMap(username);
//            user=new User();
//            if(!CollectionUtils.isEmpty(userListByMap)){
//                user = userListByMap.get(0);
//                passWord=user.getPassword();
//            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return new SimpleAuthenticationInfo(user, passWord, this.getClass().getName());
    }
}
