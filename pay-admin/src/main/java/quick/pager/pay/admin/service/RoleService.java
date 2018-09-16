package quick.pager.pay.admin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.admin.RoleDTO;
import quick.pager.pay.mapper.user.RoleMapper;
import quick.pager.pay.model.common.SystemConfig;
import quick.pager.pay.model.user.Role;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.List;

@Service
@Slf4j
public class RoleService implements IService {
    
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisService redisService;


    @Override
    public Response doService(BaseDTO dto) {

        RoleDTO roleDTO = (RoleDTO) dto;
        Constants.Operation operation = roleDTO.getOperation();
        
        Response response = null;
        switch (operation){
            case list:
                response = queryRoleList(roleDTO);
                break;
            case select:
                response = roleInfo(roleDTO.getId());
                break;
            case update:
                response = modifyRole(roleDTO);
                break;
            case cache:
                response = getRoleCodes();
                break;

                
        }
        return response;
    }

    /**
     * 角色列表
     */
    private Response queryRoleList(RoleDTO dto) {
        Integer pageNum = dto.getPageNum();
        Integer pageSize = dto.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.selectRoles(dto.getUsername());
        Response<List<Role>> resp = new Response<>();

        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        resp.setData(rolePageInfo.getList());
        resp.setTotal(rolePageInfo.getTotal());

        return resp;
    }

    /**
     * 角色信息
     */
    private Response roleInfo(Long id) {

        Role role = roleMapper.selectByPrimaryKey(id);

        if (ObjectUtils.isEmpty(role)) {
            return new Response(ResponseStatus.UNKNOWN_ROLE.code, ResponseStatus.UNKNOWN_ROLE.msg);
        }
        Response<Role> response = new Response<>();
        response.setData(role);
        return response;
    }

    /**
     * 修改角色
     */
    private Response modifyRole(RoleDTO dto) {

        Role role = roleMapper.selectByPrimaryKey(dto.getId());
        // 添加
        if (ObjectUtils.isEmpty(role)) {
            Role insertRole = new Role();
            insertRole.setRemark(dto.getRemark());
            insertRole.setRoleCode(dto.getRoleCode());
            insertRole.setRoleName(dto.getRoleName());
            insertRole.setServerStatus(dto.getServerStatus());
            roleMapper.insertSelective(insertRole);
        } else { //修改
            Role updateRole = new Role();
            updateRole.setId(role.getId());
            updateRole.setRemark(dto.getRemark());
            updateRole.setRoleCode(dto.getRoleCode());
            updateRole.setRoleName(dto.getRoleName());
            updateRole.setServerStatus(dto.getServerStatus());
            roleMapper.updateByPrimaryKeySelective(updateRole);
        }
        return new Response();
    }

    private Response getRoleCodes() {
        List<SystemConfig> list = redisService.get(Constants.Keys.ADMIN_ROLE_CODE);
        Response<List<SystemConfig>> response = new Response<>();
        response.setData(list);

        return response;
    }
}
