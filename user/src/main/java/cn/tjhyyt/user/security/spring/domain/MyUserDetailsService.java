package cn.tjhyyt.user.security.spring.domain;

import cn.tjhyyt.user.entity.Role;
import cn.tjhyyt.user.entity.User;
import cn.tjhyyt.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            log.error("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
//            throw new WrongUsernameException(AuthErrorEnum.LOGIN_NAME_ERROR.getMessage());
        }

        List<Role> roles = user.getRoles();
        UserDetails userDetails = new MyUserDetails(user);
        return userDetails;
    }
}
