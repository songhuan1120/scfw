package cn.tjhyyt.common.model.dao;
import lombok.Data;
import java.io.Serializable;

/***
*   角色权限表
*/
@Data
public class RolePermission implements Serializable {
    //角色ID
    private Long roleId;
    //权限ID
    private Long permissionId;
    //get set 方法
    public void setRoleId (Long  roleId){
        this.roleId=roleId;
    }
    public  Long getRoleId(){
        return this.roleId;
    }
    public void setPermissionId (Long  permissionId){
        this.permissionId=permissionId;
    }
    public  Long getPermissionId(){
        return this.permissionId;
    }
}
