package cn.dhbin.isme.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dhbin.isme.common.auth.SaTokenConfigure;
import cn.dhbin.isme.common.response.R;
import cn.dhbin.isme.pms.domain.dto.UserDetailDto;
import cn.dhbin.isme.pms.service.UserService;
import cn.hutool.core.convert.NumberWithFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 *
 * @author dhb
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


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
     * 获取所有
     *
     * @return R
     */
    @GetMapping
    // todo
    public R<Object> findAll() {
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
     * 更新资料
     *
     * @param id id
     * @return R
     */
    @PatchMapping("/profile/{id}")
    // todo
    public R<Object> updateProfile(@PathVariable Long id) {
        return R.ok();
    }


    /**
     * 用户信息
     * {@link UserService#detail(Long, String)}
     *
     * @return R
     */
    @RequestMapping("/detail")
    public R<UserDetailDto> detail() {
        NumberWithFormat userId =
            (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);
        UserDetailDto detail = userService.detail(userId.longValue(), roleCode);
        return R.ok(detail);
    }


    /**
     * 根据用户名获取
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/{username}")
    // todo
    public R<Object> findByUsername(@PathVariable String username) {
        return R.ok();
    }


    /**
     * 查询用户的profile
     *
     * @return R
     */
    @GetMapping("/profile/{userId}")
    public R<Object> getUserProfile(@PathVariable Long userId) {
        return R.ok();
    }

    /**
     * 添加角色
     *
     * @param userId 用户id
     * @return R
     */
    @PostMapping("/roles/add/{userId}")
    // todo
    public R<Object> addRoles(@PathVariable Long userId) {
        return R.ok();
    }

    /**
     * 重置密码
     *
     * @return R
     */
    @PatchMapping("password/reset/{userId}")
    // todo
    public R<Object> resetPassword(@PathVariable Long userId) {
        return R.ok();
    }
}
