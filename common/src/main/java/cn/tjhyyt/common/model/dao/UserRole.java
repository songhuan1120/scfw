package cn.tjhyyt.common.model.dao;
import lombok.Data;
import java.io.Serializable;

/***
*   用户角色表
*/
@Data
public class UserRole implements Serializable {
    //用户ID
    private Long userId;
    //角色ID
    private Long roleId;
    //get set 方法
    public void setUserId (Long  userId){
        this.userId=userId;
    }
    public  Long getUserId(){
        return this.userId;
    }
    public void setRoleId (Long  roleId){
        this.roleId=roleId;
    }
    public  Long getRoleId(){
        return this.roleId;
    }
}
