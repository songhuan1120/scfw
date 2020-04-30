package cn.tjhyyt.common.model.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/***
*   权限表
*/
@Data
public class Permission implements Serializable {
    //权限ID
    private Long permissionId;
    //权限名称
    private String permissionName;
    //权限描述
    private String des;
    //创建此用户的账号
    private String createAccount;
    //创建时间
    private Date createTime;
    //get set 方法
    public void setPermissionId (Long  permissionId){
        this.permissionId=permissionId;
    }
    public  Long getPermissionId(){
        return this.permissionId;
    }
    public void setPermissionName (String  permissionName){
        this.permissionName=permissionName;
    }
    public  String getPermissionName(){
        return this.permissionName;
    }
    public void setDes (String  des){
        this.des=des;
    }
    public  String getDes(){
        return this.des;
    }
    public void setCreateAccount (String  createAccount){
        this.createAccount=createAccount;
    }
    public  String getCreateAccount(){
        return this.createAccount;
    }
    public void setCreateTime (Date  createTime){
        this.createTime=createTime;
    }
    public  Date getCreateTime(){
        return this.createTime;
    }
}
