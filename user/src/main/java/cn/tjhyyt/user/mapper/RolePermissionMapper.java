package cn.tjhyyt.user.mapper;

import cn.tjhyyt.common.model.dao.RolePermission;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface RolePermissionMapper {

	public RolePermission getRolePermissionById(Long id)throws Exception;

	public List<RolePermission>	getRolePermissionListByMap(Map<String, Object> param)throws Exception;

	public Integer getRolePermissionCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertRolePermission(RolePermission rolePermission)throws Exception;

	public Integer updateRolePermission(RolePermission rolePermission)throws Exception;

	public Integer deleteRolePermissionById(Long id)throws Exception;

	public Integer batchDeleteRolePermission(Map<String, List<String>> params);

}
