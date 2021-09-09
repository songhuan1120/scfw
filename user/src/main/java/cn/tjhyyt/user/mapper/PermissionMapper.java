package cn.tjhyyt.user.mapper;

import cn.tjhyyt.user.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select * from permission where permission_name = #{name}")
    Permission findPermissionByPermissionName(String name);

    List<Permission> selectPermissionsByRoleId(Integer roleId);
}
