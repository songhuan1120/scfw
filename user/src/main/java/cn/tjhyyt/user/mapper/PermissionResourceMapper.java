package cn.tjhyyt.user.mapper;


import cn.tjhyyt.common.model.dao.PermissionResource;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface PermissionResourceMapper {

	public PermissionResource getPermissionResourceById(Long id)throws Exception;

	public List<PermissionResource>	getPermissionResourceListByMap(Map<String, Object> param)throws Exception;

	public Integer getPermissionResourceCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertPermissionResource(PermissionResource permissionResource)throws Exception;

	public Integer updatePermissionResource(PermissionResource permissionResource)throws Exception;

	public Integer deletePermissionResourceById(Long id)throws Exception;

	public Integer batchDeletePermissionResource(Map<String, List<String>> params);

}
