package cn.tjhyyt.user.service;

import cn.tjhyyt.common.model.dao.User;
import java.util.List;

public interface UserService {
    List<User> getUserListByMap(String userName);

    User getPermissionById(Long userId);
}
