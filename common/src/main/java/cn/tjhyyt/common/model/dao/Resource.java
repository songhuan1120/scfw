package cn.tjhyyt.common.model.dao;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/***
*   资源表
*/
@Data
public class Resource implements Serializable {
    //资源ID
    private Long resourceId;
    //资源名称
    private String resourceName;
    //资源描述
    private String des;
    //资源路径
    private String path;

    private Set<Permission> permissionSet;
}
