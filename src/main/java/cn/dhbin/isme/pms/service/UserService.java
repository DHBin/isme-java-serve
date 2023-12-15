package cn.dhbin.isme.pms.service;

import cn.dhbin.isme.common.response.Page;
import cn.dhbin.isme.pms.domain.dto.LoginTokenDto;
import cn.dhbin.isme.pms.domain.dto.UserDetailDto;
import cn.dhbin.isme.pms.domain.dto.UserPageDto;
import cn.dhbin.isme.pms.domain.entity.User;
import cn.dhbin.isme.pms.domain.request.AddUserRolesRequest;
import cn.dhbin.isme.pms.domain.request.ChangePasswordRequest;
import cn.dhbin.isme.pms.domain.request.LoginRequest;
import cn.dhbin.isme.pms.domain.request.RegisterUserRequest;
import cn.dhbin.isme.pms.domain.request.UpdatePasswordRequest;
import cn.dhbin.isme.pms.domain.request.UpdateProfileRequest;
import cn.dhbin.isme.pms.domain.request.UserPageRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * User Service
 *
 * @author dhb
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param request 请求
     * @return 返回token
     */
    LoginTokenDto login(LoginRequest request);

    /**
     * 用户信息
     *
     * @param userId   用户id
     * @param roleCode 用户角色编码
     * @return 用户信息
     */
    UserDetailDto detail(Long userId, String roleCode);

    /**
     * 切换角色
     *
     * @param userId   用户id
     * @param roleCode 角色编码
     * @return 切换后重新获取token
     */
    LoginTokenDto switchRole(Long userId, String roleCode);

    /**
     * 注册用户
     *
     * @param request 请求
     */
    void register(RegisterUserRequest request);

    /**
     * 刷新token
     */
    LoginTokenDto refreshToken();

    /**
     * 修改密码
     *
     * @param request 请求
     */
    void changePassword(ChangePasswordRequest request);

    /**
     * 分页查询
     *
     * @param request 请求
     * @return ret
     */
    Page<UserPageDto> queryPage(UserPageRequest request);

    /**
     * 根据id删除用户，不能删除自己
     *
     * @param id 用户id
     */
    void removeUser(Long id);

    /**
     * 重置密码
     *
     * @param userId  用户id
     * @param request 包含密码
     */
    void resetPassword(Long userId, UpdatePasswordRequest request);

    /**
     * 给用户分配角色
     *
     * @param userId  用户id
     * @param request 包含角色id
     */
    void addRoles(Long userId, AddUserRolesRequest request);

    /**
     * 更新用户信息
     *
     * @param id      用户id
     * @param request 用户信息
     */
    void updateProfile(Long id, UpdateProfileRequest request);
}
