package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.common.auth.PmsConstant;
import cn.dhbin.isme.common.auth.RoleType;
import cn.dhbin.isme.common.exception.BadRequestException;
import cn.dhbin.isme.common.response.Page;
import cn.dhbin.isme.pms.domain.dto.PermissionDto;
import cn.dhbin.isme.pms.domain.dto.RolePageDto;
import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.domain.entity.RolePermission;
import cn.dhbin.isme.pms.domain.entity.UserRole;
import cn.dhbin.isme.pms.domain.request.AddRolePermissionsRequest;
import cn.dhbin.isme.pms.domain.request.AddRoleUsersRequest;
import cn.dhbin.isme.pms.domain.request.CreateRoleRequest;
import cn.dhbin.isme.pms.domain.request.RemoveRoleUsersRequest;
import cn.dhbin.isme.pms.domain.request.RolePageRequest;
import cn.dhbin.isme.pms.domain.request.UpdateRoleRequest;
import cn.dhbin.isme.pms.mapper.RoleMapper;
import cn.dhbin.isme.pms.service.PermissionService;
import cn.dhbin.isme.pms.service.RolePermissionService;
import cn.dhbin.isme.pms.service.RoleService;
import cn.dhbin.isme.pms.service.UserRoleService;
import cn.dhbin.isme.pms.util.PermissionUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleServiceImpl
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final PermissionService permissionService;

    private final RolePermissionService rolePermissionService;

    private final UserRoleService userRoleService;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return getBaseMapper().findRolesByUserId(userId);
    }

    @Override
    public List<Tree<Long>> findRolePermissionsTree(String roleCode) {
        Role role = this.findByCode(roleCode);
        if (role == null) {
            throw new BadRequestException("当前角色不存在或者已删除");
        }
        List<Permission> permissions =
            PmsConstant.ROLE_ADMIN.equals(roleCode) ? permissionService.list()
                : permissionService.findByRoleId(role.getId());

        return PermissionUtil.toTreeNode(permissions, null);
    }

    @Override
    public Role findByCode(String roleCode) {
        return lambdaQuery().eq(Role::getCode, roleCode).one();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(CreateRoleRequest request) {
        boolean exists = lambdaQuery().eq(Role::getCode, request.getCode())
            .or()
            .eq(Role::getName, request.getName())
            .exists();

        if (exists) {
            throw new BadRequestException("角色已存在（角色名和角色编码不能重复）");
        }

        Role role = request.convert(Role.class);
        save(role);
        List<RolePermission> permissionList = request.getPermissionIds().stream()
            .map(permId -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermissionId(permId);
                return rolePermission;
            }).toList();
        this.rolePermissionService.saveBatch(permissionList);
    }

    @Override
    public Page<RolePageDto> queryPage(RolePageRequest request) {
        IPage<Role> qp = request.toPage();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(request.getName()), Role::getName, request.getName())
            .or()
            .eq(ObjectUtil.isNotNull(request.getEnable()), Role::getEnable, request.getEnable());
        IPage<RolePageDto> pageRet = this.page(qp, queryWrapper)
            .convert(role -> {
                RolePageDto dto = role.convert(RolePageDto.class);
                List<Long> permissionList = rolePermissionService.lambdaQuery().select(RolePermission::getPermissionId)
                    .eq(RolePermission::getRoleId, role.getId())
                    .list().stream().map(RolePermission::getPermissionId)
                    .toList();
                dto.setPermissionIds(permissionList);
                return dto;
            });

        return Page.convert(pageRet);
    }

    @Override
    public List<PermissionDto> findRolePermissions(Long id) {
        return permissionService.findByRoleId(id)
            .stream()
            .map(permission -> permission.convert(PermissionDto.class))
            .toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Long id, UpdateRoleRequest request) {
        Role role = getById(id);
        if (role == null) {
            throw new BadRequestException("角色不存在或者已删除");
        }
        if (RoleType.SUPER_ADMIN.equals(role.getCode())) {
            throw new BadRequestException("不允许修改超级管理员");
        }
        if (StrUtil.isNotBlank(request.getName())) {
            role.setName(request.getName());
        }
        if (ObjectUtil.isNotNull(request.getEnable())) {
            role.setEnable(request.getEnable());
        }
        updateById(role);
        if (request.getPermissionIds() != null) {
            rolePermissionService.lambdaUpdate().eq(RolePermission::getRoleId, id).remove();
            if (!request.getPermissionIds().isEmpty()) {
                List<RolePermission> permissionList = request.getPermissionIds().stream()
                    .map(permId -> {
                        RolePermission rolePermission = new RolePermission();
                        rolePermission.setRoleId(id);
                        rolePermission.setPermissionId(permId);
                        return rolePermission;
                    }).toList();
                rolePermissionService.saveBatch(permissionList);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRole(Long id) {
        Role role = getById(id);
        if (role == null) {
            throw new BadRequestException("角色不存在或者已删除");
        }
        if (RoleType.SUPER_ADMIN.equals(role.getCode())) {
            throw new BadRequestException("不允许修改超级管理员");
        }
        removeById(id);
        userRoleService.lambdaUpdate().eq(UserRole::getRoleId, id).remove();
        rolePermissionService.lambdaUpdate().eq(RolePermission::getRoleId, id).remove();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRolePermissions(AddRolePermissionsRequest request) {
        Role role = getById(request.getId());
        if (role == null) {
            throw new BadRequestException("角色不存在或者已删除");
        }
        if (RoleType.SUPER_ADMIN.equals(role.getCode())) {
            throw new BadRequestException("无需给超级管理员授权");
        }
        List<Long> list = rolePermissionService.lambdaQuery()
            .select(RolePermission::getPermissionId)
            .eq(RolePermission::getRoleId, role.getId())
            .list()
            .stream()
            .map(RolePermission::getPermissionId).toList();

        CollUtil.removeWithAddIf(request.getPermissionIds(), list::contains);
        List<RolePermission> permissionList = request.getPermissionIds()
            .stream()
            .map(permId -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermissionId(permId);
                return rolePermission;
            }).toList();
        rolePermissionService.saveBatch(permissionList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRoleUsers(Long roleId, AddRoleUsersRequest request) {
        boolean exists = lambdaQuery().eq(Role::getId, roleId).exists();
        if (!exists) {
            throw new BadRequestException("角色不存在或者已删除");
        }
        List<Long> list = userRoleService.lambdaQuery()
            .select(UserRole::getUserId)
            .eq(UserRole::getRoleId, roleId)
            .list()
            .stream()
            .map(UserRole::getUserId).toList();

        CollUtil.removeWithAddIf(request.getUserIds(), list::contains);
        List<UserRole> permissionList = request.getUserIds()
            .stream()
            .map(userId -> {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                return userRole;
            }).toList();
        userRoleService.saveBatch(permissionList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRoleUsers(Long roleId, RemoveRoleUsersRequest request) {
        boolean exists = lambdaQuery().eq(Role::getId, roleId).exists();
        if (!exists) {
            throw new BadRequestException("角色不存在或者已删除");
        }
        userRoleService.lambdaUpdate()
            .eq(UserRole::getRoleId, roleId)
            .in(UserRole::getUserId, request.getUserIds())
            .remove();
    }

}
