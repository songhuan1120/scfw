package cn.tjhyyt.user.mapper;

import cn.tjhyyt.common.model.dao.Role;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {

	public Role getRoleById(Long id)throws Exception;

	public List<Role>	getRoleListByMap(Map<String, Object> param)throws Exception;

	public Integer getRoleCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertRole(Role role)throws Exception;

	public Integer updateRole(Role role)throws Exception;

	public Integer deleteRoleById(Long id)throws Exception;

	public Integer batchDeleteRole(Map<String, List<String>> params);

}
