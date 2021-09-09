package cn.tjhyyt.user.entity;

import lombok.Data;

@Data
public class Login {
    private String username;
    private String password;
    private String token;
}
