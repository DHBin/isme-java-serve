package cn.dhbin.isme.pms.controller;

import cn.dhbin.isme.common.response.R;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限Controller
 *
 * @author dhb
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {


    /**
     * 新建权限
     *
     * @return R
     */
    @PostMapping
    // Todo
    public R<Object> create() {
        return R.ok();
    }


    /**
     * 批量创建权限
     *
     * @return R
     */
    @PostMapping("/batch")
    // todo
    public R<Object> batchCreate() {
        return R.ok();
    }

    /**
     * 获取所有权限
     *
     * @return R
     */
    @GetMapping
    // todo
    public R<Object> findAll() {
        return R.ok();
    }

    /**
     * 获取所有权限树
     *
     * @return R
     */
    @GetMapping("/tree")
    // todo
    public R<Object> findAllTree() {
        return R.ok();
    }

    /**
     * 获取菜单树
     *
     * @return R
     */
    @GetMapping("menu/tree")
    // todo
    public R<Object> findMenuTree() {
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
     * 获取
     *
     * @return R
     */
    @GetMapping("/button-and-api/:parentId")
    // todo
    public R<Object> findButtonAndApi() {
        return R.ok();
    }

    /**
     * 校验 path 存不存在menu资源内
     *
     * @return R
     */
    @GetMapping("/menu/validate")
    // todo
    public R<Object> validateMenuPath() {
        return R.ok();
    }

}
