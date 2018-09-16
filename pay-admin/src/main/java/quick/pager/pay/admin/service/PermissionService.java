package quick.pager.pay.admin.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.dto.admin.PermissionDTO;
import quick.pager.pay.mapper.user.RoleMapper;
import quick.pager.pay.mapper.user.RoleResourcesMapper;
import quick.pager.pay.mapper.user.UserMapper;
import quick.pager.pay.model.user.Role;
import quick.pager.pay.model.user.RoleResources;
import quick.pager.pay.model.user.User;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;
import quick.pager.pay.vo.admin.UserVO;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PermissionService implements IService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourcesMapper roleResourcesMapper;

    @Override
    public Response doService(BaseDTO dto) {
        PermissionDTO permissionDTO = (PermissionDTO) dto;
        Constants.Operation operation = permissionDTO.getOperation();
        Response response = null;
        switch (operation) {
            case list:
                response = queryUserList(permissionDTO);
                break;
            case select:
                response = userInfo(permissionDTO.getId());
                break;
            case permissions:
                response = permission(permissionDTO);
                break;

        }
        return response;
    }


    /**
     * 权限用户列表
     */
    private Response queryUserList(PermissionDTO dto) {
        log.info("查询用户列表，查询参数 username = {}，beginTime = {}，endTime = {} ", dto.getUsername(), dto.getBeginTime(), dto.getEndTime());

        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());

        Date start = null;
        Date end = null;
        if (0L != dto.getBeginTime()) {
            start = DateUtil.date(dto.getBeginTime());
        }
        if (0L != dto.getEndTime()) {
            end = DateUtil.date(dto.getEndTime());
        }
        List<UserVO> userList = userMapper.selectUser(dto.getUsername(), start, end);

        PageInfo<UserVO> page = new PageInfo<>(userList);
        Response<List<UserVO>> resp = new Response<>();
        resp.setData(page.getList());
        resp.setTotal(page.getTotal());

        return resp;
    }

    /**
     * 查看用户信息
     */
    private Response userInfo(Long id) {

        User user = userMapper.selectByPrimaryKey(id);
        UserVO userVO = userMapper.selectByUser(id);
        // 防止添加时未设置角色导致userVO对象的值为null
        if (ObjectUtils.isEmpty(userVO)) {
            userVO = new UserVO();
            userVO.setAge(user.getAge());
            userVO.setBirthday(user.getBirthday());
            userVO.setCreateTime(user.getCreateTime());
            userVO.setGender(user.getGender());
            userVO.setMobile(user.getMobile());
            userVO.setPassword(user.getPassword());
            userVO.setUsername(user.getUsername());
            userVO.setToken(user.getToken());
            userVO.setServerStatus(user.getServerStatus());
            userVO.setUserId(user.getId());
        }
        Response<UserVO> response = new Response<>();
        response.setData(userVO);
        return response;
    }


    /**
     * 赋予权限
     */
    public Response permission(PermissionDTO dto) {

        Role role = roleMapper.selectByPrimaryKey(dto.getId());

        if (ObjectUtils.isEmpty(role)) {
            log.warn("不存在此角色，角色Id = {}", dto.getId());
            return new Response(ResponseStatus.ROLE_NOT_EXISTS.code, ResponseStatus.ROLE_NOT_EXISTS.msg);
        }
        // 删除当前角色所有的的权限资源菜单
        roleResourcesMapper.deleteByRoleId(dto.getId());
        // 向t_role_resources 添加数据，赋予权限
        dto.getResIds().forEach(res -> {
            if (!StringUtils.isEmpty(res)) {
                // 查不到则说明是新增权限
                RoleResources insertRoleResources = new RoleResources();
                insertRoleResources.setResId(Long.parseLong(res));
                insertRoleResources.setRoleId(dto.getId());
                roleResourcesMapper.insertSelective(insertRoleResources);

            }
        });

        return new Response();
    }



}
