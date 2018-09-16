package quick.pager.pay.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.admin.service.MenuService;
import quick.pager.pay.admin.service.PermissionService;
import quick.pager.pay.admin.service.RoleService;
import quick.pager.pay.Constants;
import quick.pager.pay.dto.admin.MenuDTO;
import quick.pager.pay.dto.admin.PermissionDTO;
import quick.pager.pay.dto.admin.RoleDTO;
import quick.pager.pay.request.admin.PermissionRequest;
import quick.pager.pay.request.admin.UserRequest;
import quick.pager.pay.response.Response;

/**
 *
 */
@RestController
@RequestMapping(Constants.ADMIN_MODULE)
@Api(description = "用户权限管理模块")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @ApiOperation("用户列表")
    @PostMapping("/permission/user/list")
    public Response userList(PermissionRequest request) {

        PermissionDTO dto  = new PermissionDTO();
        dto.setPageNum(request.getPageNum());
        dto.setPageSize(request.getPageSize());
        dto.setBeginTime(request.getBeginTime());
        dto.setEndTime(request.getEndTime());

        dto.setOperation(Constants.Operation.list);
        return permissionService.doService(dto);
    }

    @ApiOperation("查看用户信息")
    @PostMapping("/permission/user/info")
    public Response userInfo(PermissionRequest request) {
        PermissionDTO dto = new PermissionDTO();
        dto.setId(request.getId());
        dto.setOperation(Constants.Operation.select);
        return permissionService.doService(dto);
    }

    @ApiOperation("添加用户")
    @PostMapping("/permission/user/add")
    public Response addUser(UserRequest request) {
        PermissionDTO dto = new PermissionDTO();
        return permissionService.doService(dto);
    }


    @ApiOperation("获取角色列表")
    @PostMapping("/permission/roles")
    public Response roles(PermissionRequest request){
        PermissionDTO dto = new PermissionDTO();

        return permissionService.doService(dto);
    }

    @ApiOperation("角色列表")
    @PostMapping("/permission/role/list")
    public Response roleList(PermissionRequest request) {

        RoleDTO dto = new RoleDTO();
        dto.setPageSize(request.getPageSize());
        dto.setPageNum(request.getPageNum());
        dto.setUsername(request.getUsername());
        dto.setOperation(Constants.Operation.list);
        return roleService.doService(dto);
    }

    @ApiOperation("角色信息")
    @PostMapping("/permission/role/info")
    public Response roleInfo(PermissionRequest request) {
        RoleDTO dto = new RoleDTO();
        dto.setId(request.getId());
        dto.setOperation(Constants.Operation.select);
        return roleService.doService(dto);

    }

    @ApiOperation("添加|修改角色")
    @PostMapping("/permission/role/modify")
    public Response modify(PermissionRequest request) {
        RoleDTO dto = new RoleDTO();

        dto.setId(request.getId());
        dto.setRoleName(request.getRoleName());
        dto.setRemark(request.getRemark());
        dto.setRoleCode(request.getRoleCode());
        dto.setRoleName(request.getRoleName());
        dto.setServerStatus(request.getServerStatus());
        dto.setOperation(Constants.Operation.update);
        return roleService.doService(dto);
    }

    @PostMapping("/permission/menu/list")
    @ApiOperation("菜单列表")
    public Response resourceList(PermissionRequest request) {
        MenuDTO dto = new MenuDTO();
        dto.setId(request.getId());
        dto.setOperation(Constants.Operation.list);
        return menuService.doService(dto);
    }

    @ApiOperation("赋予权限")
    @PostMapping("/permission")
    public Response permission(PermissionRequest request) {
        PermissionDTO dto = new PermissionDTO();
        dto.setId(request.getId());
        dto.setResIds(request.getResIds());
        dto.setOperation(Constants.Operation.permissions);
        return permissionService.doService(dto);
    }

    @ApiOperation("获取全部角色代码信息")
    @PostMapping("/getRoleCode")
    public Response getRoleCode() {
        RoleDTO dto = new RoleDTO();
        dto.setOperation(Constants.Operation.cache);
        return roleService.doService(dto);
    }
}
