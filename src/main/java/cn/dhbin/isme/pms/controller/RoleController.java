package cn.dhbin.isme.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dhbin.isme.common.auth.RoleType;
import cn.dhbin.isme.common.auth.Roles;
import cn.dhbin.isme.common.auth.SaTokenConfigure;
import cn.dhbin.isme.common.response.Page;
import cn.dhbin.isme.common.response.R;
import cn.dhbin.isme.pms.domain.dto.PermissionDto;
import cn.dhbin.isme.pms.domain.dto.RoleDto;
import cn.dhbin.isme.pms.domain.dto.RolePageDto;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.domain.request.AddRolePermissionsRequest;
import cn.dhbin.isme.pms.domain.request.AddRoleUsersRequest;
import cn.dhbin.isme.pms.domain.request.CreateRoleRequest;
import cn.dhbin.isme.pms.domain.request.RemoveRoleUsersRequest;
import cn.dhbin.isme.pms.domain.request.RolePageRequest;
import cn.dhbin.isme.pms.domain.request.UpdateRoleRequest;
import cn.dhbin.isme.pms.service.RoleService;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色Controller
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Tag(name = "角色")
public class RoleController {

    private final RoleService roleService;

    /**
     * 新建角色
     *
     * @return R
     */
    @PostMapping
    @Roles(RoleType.SUPER_ADMIN)
    @Operation(summary = "新增角色")
    public R<Void> create(@RequestBody @Validated CreateRoleRequest request) {
        roleService.createRole(request);
        return R.ok();
    }

    /**
     * 获取所有角色
     *
     * @return R
     */
    @GetMapping
    @Operation(summary = "获取所有角色")
    public R<List<RoleDto>> findAll(
        @RequestParam(value = "enable", required = false) Boolean enable
    ) {
        List<RoleDto> roleDtoList = roleService.lambdaQuery().eq(ObjectUtil.isNotNull(enable), Role::getEnable, enable)
            .list()
            .stream()
            .map(role -> role.convert(RoleDto.class))
            .toList();
        return R.ok(roleDtoList);
    }

    /**
     * 分页
     *
     * @return R
     */
    @GetMapping("/page")
    @Operation(summary = "分页")
    public R<Page<RolePageDto>> findPagination(RolePageRequest request) {
        Page<RolePageDto> ret = roleService.queryPage(request);
        return R.ok(ret);
    }

    /**
     * 查询角色权限
     *
     * @return R
     */
    @GetMapping("/permissions")
    @Operation(summary = "查询角色权限")
    public R<List<PermissionDto>> findRolePermissions(Long id) {
        List<PermissionDto> permissionDtoList = roleService.findRolePermissions(id);
        return R.ok(permissionDtoList);
    }


    /**
     * 根据id获取
     *
     * @return R
     */
    @GetMapping("{id}")
    @Roles(RoleType.SUPER_ADMIN)
    @Operation(summary = "根据id获取")
    public R<RoleDto> findOne(@PathVariable Long id) {
        RoleDto roleDto = roleService.getById(id).convert(RoleDto.class);
        return R.ok(roleDto);
    }


    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN, RoleType.SYS_ADMIN, RoleType.ROLE_PMS})
    @Operation(summary = "根据id更新")
    public R<Void> update(@PathVariable Long id, @RequestBody UpdateRoleRequest request) {
        roleService.updateRole(id, request);
        return R.ok();
    }


    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "根据id删除")
    public R<Void> remove(@PathVariable Long id) {
        roleService.removeRole(id);
        return R.ok();
    }


    /**
     * 给角色添加权限
     *
     * @return R
     */
    @PostMapping("/permissions/add")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "给角色添加权限")
    public R<Void> addRolePermissions(@RequestBody @Validated AddRolePermissionsRequest request) {
        roleService.addRolePermissions(request);
        return R.ok();
    }

    /**
     * 角色的权限树
     *
     * @return R
     */
    @GetMapping("/permissions/tree")
    @Operation(summary = "角色的权限树")
    public R<List<Tree<Long>>> permissionTree() {
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);
        List<Tree<Long>> treeList = roleService.findRolePermissionsTree(roleCode);
        return R.ok(treeList);
    }


    /**
     * 给角色分配用户
     *
     * @return R
     */
    @PatchMapping("/users/add/{roleId}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "给角色分配用户")
    public R<Void> addRoleUsers(@PathVariable Long roleId, @RequestBody AddRoleUsersRequest request) {
        roleService.addRoleUsers(roleId, request);
        return R.ok();
    }


    @PatchMapping("/users/remove/{roleId}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "移除角色")
    public R<Void> removeRoleUsers(@PathVariable Long roleId, @RequestBody RemoveRoleUsersRequest request) {
        roleService.removeRoleUsers(roleId, request);
        return R.ok();
    }

}
