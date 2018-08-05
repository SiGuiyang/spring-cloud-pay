package quick.pager.pay.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quick.pager.pay.admin.service.PermissionService;
import quick.pager.pay.admin.service.RoleService;
import quick.pager.pay.Constants;
import quick.pager.pay.dto.admin.PermissionDto;
import quick.pager.pay.dto.admin.RoleDto;
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

    @ApiOperation("用户列表")
    @PostMapping("/permission/user/list")
    public Response userList(PermissionRequest request) {

        PermissionDto dto  = new PermissionDto();
        dto.setOperation(Constants.Operation.list);
        return permissionService.doService(dto);
    }

    @ApiOperation("查看用户信息")
    @PostMapping("/permission/user/info")
    public Response userInfo(PermissionRequest request) {
        PermissionDto dto = new PermissionDto();
        return permissionService.doService(dto);
    }

    @ApiOperation("添加用户")
    @PostMapping("/permission/user/add")
    public Response addUser(UserRequest request) {
        PermissionDto dto = new PermissionDto();
        return permissionService.doService(dto);
    }


    @ApiOperation("获取角色列表")
    @PostMapping("/permission/roles")
    public Response roles(PermissionRequest request){
        PermissionDto dto = new PermissionDto();
        return permissionService.doService(dto);
    }

    @ApiOperation("角色列表")
    @PostMapping("/permission/role/list")
    public Response roleList(PermissionRequest request) {

        RoleDto dto = new RoleDto();

        return roleService.doService(dto);
    }

    @ApiOperation("角色信息")
    @PostMapping("/permission/role/info")
    public Response roleInfo(PermissionRequest request) {
        RoleDto dto = new RoleDto();

        return roleService.doService(dto);

    }

    @ApiOperation("添加|修改角色")
    @PostMapping("/permission/role/add")
    public Response addRole(PermissionRequest request) {
        RoleDto dto = new RoleDto();

        return roleService.doService(dto);
    }

    @PostMapping("/permission/menu/list")
    @ApiOperation("菜单列表")
    public Response resourceList(PermissionRequest request) {
        PermissionDto dto = new PermissionDto();
        return permissionService.doService(dto);
    }

    @ApiOperation("赋予权限")
    @PostMapping("/permission")
    public Response permission(PermissionRequest request) {
        PermissionDto dto = new PermissionDto();
        return permissionService.doService(dto);
    }

    @ApiOperation("获取全部角色代码信息")
    @PostMapping("/getRoleCode")
    public Response getRoleCode(PermissionRequest request) {
        PermissionDto dto = new PermissionDto();
        return permissionService.doService(dto);
    }
}
