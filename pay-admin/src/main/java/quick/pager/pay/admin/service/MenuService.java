package quick.pager.pay.admin.service;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.dto.admin.MenuDto;
import quick.pager.pay.mapper.user.ResourcesMapper;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;
import quick.pager.pay.vo.MenuVO;
import quick.pager.pay.vo.ResourceVO;

import java.util.Arrays;
import java.util.List;

/**
 * 菜单服务
 */
@Service
@Slf4j
public class MenuService implements IService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public Response doService(BaseDto dto) {
        MenuDto menuDto = (MenuDto) dto;

        Constants.Operation operation = menuDto.getOperation();

        Response response = null;

        switch (operation){
            case list:
                response = queryMenuList(menuDto.getId());

        }

        return response;
    }


    /**
     * 查询菜单列表
     */
    private Response queryMenuList(long id) {
        // 查询所有的资源
        List<ResourceVO> vos = resourcesMapper.selectAllResource(0L, null);
        List<MenuVO> permissionEvents = Lists.newArrayList();
        permissionEvents = fetchRouter(permissionEvents, vos);
        List<Long> roleIds = resourcesMapper.selectResourceByRoleId(id);
        Response<List<MenuVO>> response = new Response<>();
        PageInfo<ResourceVO> pageInfo = new PageInfo<>(vos);
        response.setTotal(pageInfo.getTotal());
        response.setData(permissionEvents);
//        response.setData(roleIds);
        return response;
    }

    /**
     * 递归迭代路由
     *
     * @param result 返回路由的结果
     * @param vos    菜单列表
     */
    private List<MenuVO> fetchRouter(List<MenuVO> result, List<ResourceVO> vos) {
        vos.forEach(vo -> {
            MenuVO router = new MenuVO();
            router.setId(vo.getId());
            router.setName(vo.getTitle());
            router.setLabel(vo.getTitle());
            router.setPath(vo.getPath());
            router.setComponent(vo.getComponent());
            router.setHidden(vo.isNoCache());
            router.setPermission(Arrays.asList(vo.getPermission().split("\\,")));
            List<MenuVO> list = fetchRouter(Lists.newArrayList(), resourcesMapper.selectAllResource(vo.getId(), null));
            if (!CollectionUtils.isEmpty(list)) {
                // 迭代children元素
                router.setChildren(list);
            }
            result.add(router);
        });
        return result;
    }
}
