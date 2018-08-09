package quick.pager.pay.mapper.user;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.user.Role;

import java.util.List;

public interface RoleMapper extends IMapper<Long, Role> {

    /**
     * 根据用户Id查询该用户的所有角色
     *
     * @param userId 用户Id
     */
    Role selectRoleByUserId(@Param("userId") Long userId);

    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     */
    List<Role> selectRoles(@Param("roleName") String roleName);
}