package cn.tjhyyt.user.mapper;

import cn.tjhyyt.user.entity.ParentMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
public interface ParentMenuMapper extends BaseMapper<ParentMenu> {
    List<ParentMenu> selectMenusByPName(String permissionName);
}
