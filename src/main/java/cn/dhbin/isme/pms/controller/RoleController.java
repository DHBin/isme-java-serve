package cn.dhbin.isme.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dhbin.isme.common.auth.SaTokenConfigure;
import cn.dhbin.isme.common.response.R;
import cn.dhbin.isme.pms.service.RoleService;
import cn.hutool.core.lang.tree.Tree;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色Controller
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * 新建角色
     *
     * @return R
     */
    @PostMapping
    // Todo
    public R<Object> create() {
        return R.ok();
    }

    /**
     * 获取所有角色
     *
     * @return R
     */
    @GetMapping
    // todo
    public R<Object> findAll() {
        return R.ok();
    }

    /**
     * 分页
     *
     * @return R
     */
    @GetMapping("/page")
    public R<Object> findPagination() {
        return R.ok();
    }

    /**
     * 查询角色权限
     *
     * @return R
     */
    @GetMapping("/permissions")
    public R<Object> findRolePermissions() {
        return R.ok();
    }


    /**
     * 根据id获取
     *
     * @return R
     */
    @GetMapping("{id}")
    // todo
    public R<Object> findOne(@PathVariable Long id) {
        return R.ok();
    }


    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    // todo
    public R<Object> update(@PathVariable Long id) {
        return R.ok();
    }


    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    // todo
    public R<Object> remove(@PathVariable Long id) {
        return R.ok();
    }


    /**
     * 给角色添加权限
     *
     * @return R
     */
    // todo
    @PostMapping("/permissions/add")
    public R<Object> addRolePermissions() {
        return R.ok();
    }

    /**
     * 角色的权限树
     *
     * @return R
     */
    @GetMapping("/permissions/tree")
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
    public R<Object> addRoleUsers(@PathVariable Long roleId) {
        return null;
    }


    @PatchMapping("/users/remove/{roleId}")
    public R<Object> removeRoleUsers(@PathVariable Long roleId) {
        return R.ok();
    }

}
