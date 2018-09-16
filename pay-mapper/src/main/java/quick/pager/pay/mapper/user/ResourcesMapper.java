package quick.pager.pay.mapper.user;


import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.user.Resources;
import quick.pager.pay.vo.admin.ResourceVO;

import java.util.List;


public interface ResourcesMapper extends IMapper<Long, Resources> {

    /**
     * 查询所有的可用菜单
     *
     * @param parentId     父级菜单
     * @param resourceType 资源类型, 0: 查询路由菜单 1: 查询按钮菜单
     */
    List<ResourceVO> selectAllResource(@Param("parentId") Long parentId, @Param("resourceType") Integer resourceType);

    /**
     * 查询菜单列表
     *
     * @param userId       用户Id
     * @param parentId     父级菜单Id
     * @param resourceType 资源类型, 0: 查询路由菜单 1: 查询按钮菜单
     */
    List<ResourceVO> selectResourceByUserId(@Param("userId") Long userId, @Param("parentId") Long parentId, @Param("resourceType") Integer resourceType);

    /**
     * 根据角色Id查询该角色所拥有的资源
     *
     * @param roleId 角色Id
     */
    List<Long> selectResourceByRoleId(@Param("roleId") Long roleId);
}