package cn.tjhyyt.user.service.impl;

import cn.tjhyyt.user.entity.User;
import cn.tjhyyt.user.mapper.UserMapper;
import cn.tjhyyt.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User selectUserByUsername(String username) {
        return this.baseMapper.selectUserByUserName(username);
    }

    @Override
    public User selectUserAndRoleByName(String username) {
        return this.baseMapper.selectUserAndRoleByName(username);
    }
}
