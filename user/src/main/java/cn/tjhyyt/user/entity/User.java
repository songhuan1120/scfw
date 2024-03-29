package cn.tjhyyt.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author songhuan
 * @since 2021-09-07
 */
@Data
//@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
public class User implements Serializable {
    public User() {

    }
    public User(User user) {
        this();
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userNo = user.getUserNo();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String userNo;

    private String password;

    @TableField(exist = false)
    private List<Role> roles;


}
