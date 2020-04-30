package cn.tjhyyt.common.model.dao;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/***
*   角色表
*/
@Data
public class Role implements Serializable {
    //角色ID
    private Long roleId;
    //角色名字
    private String roleName;
    //创建此用户的账号
    private String createAccount;
    //创建时间
    private Date createTime;
    //角色描述
    private String des;

    private Set<Permission> permissionSet;
}
