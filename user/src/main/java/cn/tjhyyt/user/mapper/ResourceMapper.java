package cn.tjhyyt.user.mapper;

import cn.tjhyyt.common.model.dao.Resource;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface ResourceMapper {

	public Resource getResourceById(Long id)throws Exception;

	public List<Resource>getResourceListByMap(Map<String, Object> param);

	public List<Resource>getResourceAndPermission();

	public Integer getResourceCountByMap(Map<String, Object> param)throws Exception;

	public Integer insertResource(Resource resource)throws Exception;

	public Integer updateResource(Resource resource)throws Exception;

	public Integer deleteResourceById(Long id)throws Exception;

	public Integer batchDeleteResource(Map<String, List<String>> params);

}
