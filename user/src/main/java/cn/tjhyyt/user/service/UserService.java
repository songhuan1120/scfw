package cn.tjhyyt.user.service;

import cn.tjhyyt.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
public interface UserService extends IService<User> {
    User selectUserByUsername(String username);

    User selectUserAndRoleByName(String username);
}
