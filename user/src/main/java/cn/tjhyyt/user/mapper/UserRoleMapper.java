package cn.tjhyyt.user.mapper;

import cn.tjhyyt.common.model.dao.UserRole;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRoleMapper {

	public UserRole getUserRoleById(Long id)throws Exception;

	public List<UserRole>	getUserRoleListByMap(Map<String, Object> param)throws Exception;

	public Integer getUserRoleCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertUserRole(UserRole userRole)throws Exception;

	public Integer updateUserRole(UserRole userRole)throws Exception;

	public Integer deleteUserRoleById(Long id)throws Exception;

	public Integer batchDeleteUserRole(Map<String, List<String>> params);

}
