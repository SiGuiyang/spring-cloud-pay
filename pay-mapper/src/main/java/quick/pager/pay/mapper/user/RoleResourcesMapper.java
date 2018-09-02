package quick.pager.pay.mapper.user;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.user.RoleResources;

import java.util.List;

public interface RoleResourcesMapper extends IMapper<Long, RoleResources> {

    /**
     * 根据roleId查询该角色所有的资源
     *
     * @param roleId 角色Id
     */
    List<Long> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据roleId和resId选择当前角色是否有权限
     *
     * @param roleId 角色Id
     * @param resId  资源Id
     */
    RoleResources selectByRoleIdAndResId(@Param("roleId") Long roleId, @Param("resId") Long resId);

    /**
     * 更新角色资源权限
     *
     * @param roleId   角色Id
     * @param resId    资源Id
     * @param newResId 需要更新的资源Id
     */
    int updateRoleResourceByRoleIdAndResId(@Param("roleId") Long roleId, @Param("resId") Long resId, @Param("newResId") Long newResId);

    /**
     * 批量删除
     */
    void deleteByRoleId(@Param("roleId") Long id);
}