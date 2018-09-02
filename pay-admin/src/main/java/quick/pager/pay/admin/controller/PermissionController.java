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
import quick.pager.pay.dto.admin.MenuDto;
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
    @Autowired
    private MenuService menuService;

    @ApiOperation("用户列表")
    @PostMapping("/permission/user/list")
    public Response userList(PermissionRequest request) {

        PermissionDto dto  = new PermissionDto();
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
        PermissionDto dto = new PermissionDto();
        dto.setId(request.getId());
        dto.setOperation(Constants.Operation.select);
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
        dto.setPageSize(request.getPageSize());
        dto.setPageNum(request.getPageNum());
        dto.setUsername(request.getUsername());
        dto.setOperation(Constants.Operation.list);
        return roleService.doService(dto);
    }

    @ApiOperation("角色信息")
    @PostMapping("/permission/role/info")
    public Response roleInfo(PermissionRequest request) {
        RoleDto dto = new RoleDto();
        dto.setId(request.getId());
        dto.setOperation(Constants.Operation.select);
        return roleService.doService(dto);

    }

    @ApiOperation("添加|修改角色")
    @PostMapping("/permission/role/modify")
    public Response modify(PermissionRequest request) {
        RoleDto dto = new RoleDto();

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
        MenuDto dto = new MenuDto();
        dto.setId(request.getId());
        dto.setOperation(Constants.Operation.list);
        return menuService.doService(dto);
    }

    @ApiOperation("赋予权限")
    @PostMapping("/permission")
    public Response permission(PermissionRequest request) {
        PermissionDto dto = new PermissionDto();
        dto.setId(request.getId());
        dto.setResIds(request.getResIds());
        dto.setOperation(Constants.Operation.permissions);
        return permissionService.doService(dto);
    }

    @ApiOperation("获取全部角色代码信息")
    @PostMapping("/getRoleCode")
    public Response getRoleCode() {
        RoleDto dto = new RoleDto();
        dto.setOperation(Constants.Operation.cache);
        return roleService.doService(dto);
    }
}
