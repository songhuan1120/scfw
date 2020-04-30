package cn.tjhyyt.user.mapper;

import cn.tjhyyt.common.model.dao.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

	public User getUserById(Long id)throws Exception;

	public User getPermissionById(Long userId);

	public User test(Long userId);

	public List<User>getUserListByMap(Map<String, Object> param);

	public Integer getUserCountByMap(Map<String, Object> param);

	public Integer insertUser(User user)throws Exception;

	public Integer updateUser(User user)throws Exception;

	public Integer deleteUserById(Long id)throws Exception;

	public Integer batchDeleteUser(Map<String, List<String>> params);

}
