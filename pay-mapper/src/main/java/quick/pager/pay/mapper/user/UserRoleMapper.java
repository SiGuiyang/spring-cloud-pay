package quick.pager.pay.mapper.user;


import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.user.UserRole;

public interface UserRoleMapper extends IMapper<Long, UserRole> {

    /**
     * 根据用户Id查询当前角色
     *
     * @param userId 用户Id
     */
    UserRole selectByUserId(@Param("userId") Long userId);

}