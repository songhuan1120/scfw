package cn.tjhyyt.user.mapper;
import cn.tjhyyt.common.model.dao.Permission;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface PermissionMapper {

	public Permission getPermissionById(Long id)throws Exception;

	public List<Permission>	getPermissionListByMap(Map<String, Object> param)throws Exception;

	public Integer getPermissionCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertPermission(Permission permission)throws Exception;

	public Integer updatePermission(Permission permission)throws Exception;

	public Integer deletePermissionById(Long id)throws Exception;

	public Integer batchDeletePermission(Map<String, List<String>> params);

}
