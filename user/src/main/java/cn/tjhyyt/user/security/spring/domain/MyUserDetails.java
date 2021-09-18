package cn.tjhyyt.user.security.spring.domain;

import cn.tjhyyt.user.entity.Permission;
import cn.tjhyyt.user.entity.Role;
import cn.tjhyyt.user.entity.User;
import cn.tjhyyt.user.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class MyUserDetails extends User implements UserDetails {
    public MyUserDetails(User user) {
        super(user);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();
        List<Role> roles = super.getRoles();
        for (Role role : roles) {
            List<Permission> permissionList = role.getPermissions();
            if (!CollectionUtils.isEmpty(permissionList)) {
                for (Permission permission : permissionList) {
                    if (!permissions.contains(permission)) {
                        permissions.add(permission);
                    }
                }
            }
        }
        if (CollectionUtils.isEmpty(permissions))  {
            log.info("用户："+getUserName()+"没有任何权限");
        } else {
            for (Permission permission:permissions) {
                authorities.add(new SimpleGrantedAuthority("role_"+permission.getId()+""));
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
