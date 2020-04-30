package cn.tjhyyt.common.model.dao;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/***
*   用户表
*/
@Data
public class User implements Serializable {
    //用户ID
    private Long userId;
    //登录用户名
    private String userName;
    //账号
    private String userAccount;
    //用户密码
    private String password;
    //创建此用户的账号
    private String createAccount;
    //创建时间
    private Date createTime;
    //是否有效 1:有效 0无效
    private Integer isValid;

    private Set<Role> roleSet;
}