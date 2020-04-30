package cn.tjhyyt.user.service.impl;

import cn.tjhyyt.common.model.dao.User;
import cn.tjhyyt.user.mapper.UserMapper;
import cn.tjhyyt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserListByMap(String userAccount){
        Map<String, Object> param=new HashMap<>();
        param.put("userAccount",userAccount);
        return userMapper.getUserListByMap(param);
    }


    @Override
    public User getPermissionById(Long id) {
        return userMapper.getPermissionById(id);
    }

}
