package cn.tjhyyt.user.security.spring.impl;

import cn.tjhyyt.user.anotation.SpringSecurityEnv;
import cn.tjhyyt.user.entity.Login;
import cn.tjhyyt.user.security.service.LoginService;
import org.springframework.stereotype.Service;

@Service
@SpringSecurityEnv
public class LoginServiceImpl implements LoginService {
    @Override
    public Login login(Login login) {
        return null;
    }
}
