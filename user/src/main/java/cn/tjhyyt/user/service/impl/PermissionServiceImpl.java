package cn.tjhyyt.user.service.impl;

import cn.tjhyyt.user.entity.ParentMenu;
import cn.tjhyyt.user.entity.Permission;
import cn.tjhyyt.user.mapper.PermissionMapper;
import cn.tjhyyt.user.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public Permission selectParentMenusByPName(String pName) {
        return this.baseMapper.findPermissionByPermissionName(pName);
    }

    @Override
    public List<Permission> selectPermissionsByRoleId(Integer roleId) {
        return this.baseMapper.selectPermissionsByRoleId(roleId);
    }
}
