package cn.tjhyyt.user.service.impl;

import cn.tjhyyt.user.entity.ParentMenu;
import cn.tjhyyt.user.mapper.ParentMenuMapper;
import cn.tjhyyt.user.service.ParentMenuService;
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
public class ParentMenuServiceImpl extends ServiceImpl<ParentMenuMapper, ParentMenu> implements ParentMenuService {
    @Override
    public List<ParentMenu> selectMenusByPName(String permissionName) {
        return this.baseMapper.selectMenusByPName(permissionName);
    }
}
