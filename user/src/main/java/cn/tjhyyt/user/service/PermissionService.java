package cn.tjhyyt.user.service;

import cn.tjhyyt.user.entity.ParentMenu;
import cn.tjhyyt.user.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
public interface PermissionService extends IService<Permission> {
    Permission selectParentMenusByPName(String pName);

    List<Permission> selectPermissionsByRoleId(Integer roleId);
}
