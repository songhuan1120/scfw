package cn.tjhyyt.user.mapper;

import cn.tjhyyt.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select id,user_name,user_no,password,del_flag from user where user_name=#{username} and del_flag='0' limit 1")
    User selectUserByUserName(String username);

    User selectUserAndRoleByName(String username);
}
