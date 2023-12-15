package cn.dhbin.isme.pms.controller;

import cn.dhbin.isme.common.preview.Preview;
import cn.dhbin.isme.common.response.R;
import cn.dhbin.isme.pms.domain.dto.PermissionDto;
import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.isme.pms.domain.request.CreatePermissionRequest;
import cn.dhbin.isme.pms.domain.request.UpdatePermissionRequest;
import cn.dhbin.isme.pms.service.PermissionService;
import cn.hutool.core.lang.tree.Tree;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限Controller
 *
 * @author dhb
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Tag(name = "权限")
public class PermissionController {


    private final PermissionService permissionService;

    /**
     * 新建权限
     *
     * @return R
     */
    @PostMapping
    @Preview
    public R<Void> create(@RequestBody @Validated CreatePermissionRequest request) {
        permissionService.create(request);
        return R.ok();
    }


    /**
     * 批量创建权限
     *
     * @return R
     */
    @PostMapping("/batch")
    @Preview
    public R<Void> batchCreate(@RequestBody @Validated List<CreatePermissionRequest> request) {
        permissionService.createBatch(request);
        return R.ok();
    }

    /**
     * 获取所有权限
     *
     * @return R
     */
    @GetMapping
    public R<List<PermissionDto>> findAll() {
        List<PermissionDto> menu = permissionService.findAllMenu();
        return R.ok(menu);
    }

    /**
     * 获取所有权限树
     *
     * @return R
     */
    @GetMapping("/tree")
    public R<List<Tree<Long>>> findAllTree() {
        List<Tree<Long>> tree = permissionService.findAllMenuTree();
        return R.ok(tree);
    }

    /**
     * 获取菜单树
     *
     * @return R
     */
    @GetMapping("menu/tree")
    public R<List<Tree<Long>>> findMenuTree() {
        List<Tree<Long>> tree = permissionService.findAllMenuTree();
        return R.ok(tree);
    }

    /**
     * 根据id获取
     *
     * @return R
     */
    @GetMapping("{id}")
    public R<PermissionDto> findOne(@PathVariable Long id) {
        PermissionDto permissionDto = permissionService.getById(id).convert(PermissionDto.class);
        return R.ok(permissionDto);
    }

    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    public R<Object> update(@PathVariable Long id, @RequestBody UpdatePermissionRequest request) {
        Permission permission = request.convert(Permission.class);
        permission.setId(id);
        permissionService.updateById(permission);
        return R.ok();
    }

    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    public R<Object> remove(@PathVariable Long id) {
        permissionService.removeById(id);
        return R.ok();
    }


    /**
     * 获取
     *
     * @return R
     */
    @GetMapping("/button-and-api/{parentId}")
    public R<List<Permission>> findButtonAndApi(@PathVariable Long parentId) {
        List<Permission> permissions = permissionService.findButtonAndApi(parentId);
        return R.ok(permissions);
    }

    /**
     * 校验 path 存不存在menu资源内
     *
     * @return R
     */
    @GetMapping("/menu/validate")
    public R<Object> validateMenuPath(String path) {
        boolean b = permissionService.validateMenuPath(path);
        return R.ok(b);
    }

}
