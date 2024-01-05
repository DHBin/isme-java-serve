package cn.dhbin.isme.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dhbin.isme.common.auth.RoleType;
import cn.dhbin.isme.common.auth.Roles;
import cn.dhbin.isme.common.auth.SaTokenConfigure;
import cn.dhbin.isme.common.exception.BizException;
import cn.dhbin.isme.common.preview.Preview;
import cn.dhbin.isme.common.response.BizResponseCode;
import cn.dhbin.isme.common.response.Page;
import cn.dhbin.isme.common.response.R;
import cn.dhbin.isme.pms.domain.dto.UserDetailDto;
import cn.dhbin.isme.pms.domain.dto.UserPageDto;
import cn.dhbin.isme.pms.domain.request.AddUserRolesRequest;
import cn.dhbin.isme.pms.domain.request.RegisterUserRequest;
import cn.dhbin.isme.pms.domain.request.UpdatePasswordRequest;
import cn.dhbin.isme.pms.domain.request.UpdateProfileRequest;
import cn.dhbin.isme.pms.domain.request.UpdateUserRequest;
import cn.dhbin.isme.pms.domain.request.UserPageRequest;
import cn.dhbin.isme.pms.service.UserService;
import cn.hutool.core.convert.NumberWithFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * 用户Controller
 *
 * @author dhb
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户")
public class UserController {

    private final UserService userService;


    /**
     * 新建用户
     *
     * @return R
     */
    @PostMapping
    @Roles({RoleType.SUPER_ADMIN})
    @Preview
    @Operation(summary = "新增用户")
    public R<Void> create(@RequestBody @Validated RegisterUserRequest request) {
        userService.register(request);
        return R.ok();
    }


    /**
     * 获取所有
     *
     * @return R
     */
    @GetMapping
    @Operation(summary = "获取所有")
    public R<Page<UserPageDto>> findAll(UserPageRequest request) {
        Page<UserPageDto> ret = userService.queryPage(request);
        return R.ok(ret);
    }


    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN})
    @Preview
    @Operation(summary = "根据id删除")
    public R<Void> remove(@PathVariable Long id) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() == id) {
            throw new BizException(BizResponseCode.ERR_11006, "非法操作，不能删除自己！");
        }
        userService.removeUser(id);
        return R.ok();
    }


    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    @Preview
    @Operation(summary = "根据id更新")
    public R<Void> update(@PathVariable Long id, @RequestBody AddUserRolesRequest request) {
        userService.addRoles(id, request);
        return R.ok();
    }


    /**
     * 更新资料
     *
     * @param id id
     * @return R
     */
    @PatchMapping("/profile/{id}")
    @Preview
    @Operation(summary = "更新资料")
    public R<Void> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() != id) {
            throw new BizException(BizResponseCode.ERR_11004, "越权操作，用户资料只能本人修改！");
        }
        userService.updateProfile(id, request);
        return R.ok();
    }


    /**
     * 用户信息
     * {@link UserService#detail(Long, String)}
     *
     * @return R
     */
    @GetMapping("/detail")
    @Operation(summary = "用户信息")
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
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "根据用户名获取")
    public R<Void> findByUsername(@PathVariable String username) {
        throw new BizException(BizResponseCode.ERR_11006, "接口未实现");
    }


    /**
     * 查询用户的profile
     *
     * @return R
     */
    @GetMapping("/profile/{userId}")
    @Operation(summary = "查询用户的profile")
    public R<Void> getUserProfile(@PathVariable Long userId) {
        throw new BizException(BizResponseCode.ERR_11006, "接口未实现");
    }

    /**
     * 添加角色
     *
     * @param userId 用户id
     * @return R
     */
    @PostMapping("/roles/add/{userId}")
    @Preview
    @Operation(summary = "添加角色")
    public R<Object> addRoles(@PathVariable Long userId, @RequestBody @Validated AddUserRolesRequest request) {
        userService.addRoles(userId, request);
        return R.ok();
    }

    /**
     * 重置密码
     *
     * @return R
     */
    @PatchMapping("password/reset/{userId}")
    @Roles({RoleType.SUPER_ADMIN})
    @Operation(summary = "重置密码")
    public R<Object> resetPassword(@PathVariable Long userId, @RequestBody @Validated UpdatePasswordRequest request) {
        userService.resetPassword(userId, request);
        return R.ok();
    }
}
