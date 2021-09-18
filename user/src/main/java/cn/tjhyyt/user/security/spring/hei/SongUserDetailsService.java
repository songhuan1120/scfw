package cn.tjhyyt.user.security.spring.hei;

import cn.tjhyyt.user.entity.Permission;
import cn.tjhyyt.user.entity.Role;
import cn.tjhyyt.user.entity.User;
import cn.tjhyyt.user.security.spring.domain.MyUserDetails;
import cn.tjhyyt.user.service.PermissionService;
import cn.tjhyyt.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Slf4j
public class SongUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_name",username);
        User user = userService.selectUserAndRoleByName(username);
        for (Role role : user.getRoles()) {
            List<Permission> permissionList = permissionService.selectPermissionsByRoleId(role.getId());
            role.setPermissions(permissionList);
        }

        //
//        if (user == null) {
//            log.error("用户不存在");
//            throw new UsernameNotFoundException("用户不存在");
////            throw new WrongUsernameException(AuthErrorEnum.LOGIN_NAME_ERROR.getMessage());
//        }
//
//        List<Role> roles = user.getRoles();
        UserDetails userDetails = new MyUserDetails(user);
        return userDetails;
    }
}
