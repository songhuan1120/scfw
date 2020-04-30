package cn.tjhyyt.common.model.dao;
import lombok.Data;

import java.io.Serializable;

/***
*   权限资源表
*/
@Data
public class PermissionResource implements Serializable {
    //权限ID
    private Long permissionId;
    //资源ID
    private Long resourceId;
    //get set 方法
    public void setPermissionId (Long  permissionId){
        this.permissionId=permissionId;
    }
    public  Long getPermissionId(){
        return this.permissionId;
    }
    public void setResourceId (Long  resourceId){
        this.resourceId=resourceId;
    }
    public  Long getResourceId(){
        return this.resourceId;
    }
}
