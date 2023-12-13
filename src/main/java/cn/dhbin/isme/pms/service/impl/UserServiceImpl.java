package cn.dhbin.isme.pms.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dhbin.isme.common.auth.SaTokenConfigure;
import cn.dhbin.isme.common.exception.BizException;
import cn.dhbin.isme.common.response.BizResponseCode;
import cn.dhbin.isme.pms.domain.dto.LoginTokenDto;
import cn.dhbin.isme.pms.domain.dto.ProfileDto;
import cn.dhbin.isme.pms.domain.dto.RoleDto;
import cn.dhbin.isme.pms.domain.dto.UserDetailDto;
import cn.dhbin.isme.pms.domain.entity.Profile;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.domain.entity.User;
import cn.dhbin.isme.pms.domain.entity.UserRole;
import cn.dhbin.isme.pms.domain.request.ChangePasswordRequest;
import cn.dhbin.isme.pms.domain.request.LoginRequest;
import cn.dhbin.isme.pms.domain.request.RegisterUserRequest;
import cn.dhbin.isme.pms.mapper.UserMapper;
import cn.dhbin.isme.pms.service.CaptchaService;
import cn.dhbin.isme.pms.service.ProfileService;
import cn.dhbin.isme.pms.service.RoleService;
import cn.dhbin.isme.pms.service.UserRoleService;
import cn.dhbin.isme.pms.service.UserService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Service impl
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final RoleService roleService;

    private final ProfileService profileService;

    private final UserRoleService userRoleService;

    private final CaptchaService captchaService;

    @Override
    public LoginTokenDto login(LoginRequest request) {
        if (StrUtil.isBlank(request.getCaptchaKey()) || !captchaService.verify(request.getCaptchaKey(), request.getCaptcha())) {
            throw new BizException(BizResponseCode.ERR_10003);
        }
        User user = lambdaQuery().eq(User::getUsername, request.getUsername()).one();
        if (user == null) {
            throw new BizException(BizResponseCode.ERR_10002);
        }
        boolean checkPw = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (checkPw) {
            // 查询用户的角色
            List<Role> roles = roleService.findRolesByUserId(user.getId());

            return generateToken(user, roles, roles.isEmpty() ? "" : roles.getFirst().getCode());
        } else {
            throw new BizException(BizResponseCode.ERR_10002);
        }
    }

    @Override
    public UserDetailDto detail(Long userId, String roleCode) {
        User user = getById(userId);
        UserDetailDto userDetailDto = user.convert(UserDetailDto.class);
        ProfileDto profileDto = profileService.findByUserId(userId).convert(ProfileDto.class);
        List<RoleDto> roleDtoList = roleService.findRolesByUserId(userId)
            .stream()
            .filter(Role::getEnable)
            .map(role -> role.convert(RoleDto.class))
            .toList();
        if (roleDtoList.isEmpty()) {
            throw new BizException(BizResponseCode.ERR_11005);
        }
        userDetailDto.setProfile(profileDto);
        userDetailDto.setRoles(roleDtoList);
        userDetailDto.setCurrentRole(roleDtoList.getFirst());
        return userDetailDto;
    }

    @Override
    public LoginTokenDto switchRole(Long userId, String roleCode) {
        User user = getById(userId);
        List<Role> roles = roleService.findRolesByUserId(userId);
        Role currentRole = null;
        for (Role role : roles) {
            if (roleCode.equals(role.getCode())) {
                currentRole = role;
            }
        }
        if (currentRole == null) {
            throw new BizException(BizResponseCode.ERR_11005);
        }
        return generateToken(user, roles, currentRole.getCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterUserRequest request) {
        boolean exists = lambdaQuery().eq(User::getUsername, request.getUsername()).exists();
        if (exists) {
            throw new BizException(BizResponseCode.ERR_10001);
        }
        User user = request.convert(User.class);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        this.save(user);
        Profile profile = request.getProfile().convert(Profile.class);
        profile.setUserId(user.getId());
        List<UserRole> roleList = request.getRoleIds().stream()
            .map(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(roleId);
                return userRole;
            }).toList();
        profileService.save(profile);
        userRoleService.saveBatch(roleList);
    }

    @Override
    public LoginTokenDto refreshToken() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        StpUtil.login(tokenInfo.getLoginId(), SaLoginConfig
            .setExtra(SaTokenConfigure.JWT_USER_ID_KEY,
                StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY))
            .setExtra(SaTokenConfigure.JWT_USERNAME_KEY,
                StpUtil.getExtra(SaTokenConfigure.JWT_USERNAME_KEY))
            .setExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY,
                StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY))
            .setExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY,
                StpUtil.getExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY))
        );
        SaTokenInfo newTokenInfo = StpUtil.getTokenInfo();
        LoginTokenDto dto = new LoginTokenDto();
        dto.setAccessToken(newTokenInfo.getTokenValue());
        return dto;
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        String username = (String) StpUtil.getExtra(SaTokenConfigure.JWT_USERNAME_KEY);
        User user = lambdaQuery().select(User::getPassword).eq(User::getUsername, username).one();
        if (!BCrypt.checkpw(user.getPassword(), request.getOldPassword())) {
            throw new BizException(BizResponseCode.ERR_10004);
        }
        user.setPassword(BCrypt.hashpw(request.getNewPassword()));
        lambdaUpdate().set(User::getPassword, BCrypt.hashpw(request.getNewPassword()))
            .eq(User::getUsername, username)
            .update();
        StpUtil.logout();
    }


    private LoginTokenDto generateToken(User user, List<Role> roles, String currentRoleCode) {
        // 密码验证成功
        StpUtil.login(user.getId(),
            SaLoginConfig.setExtra(SaTokenConfigure.JWT_USER_ID_KEY, user.getId())
                .setExtra(SaTokenConfigure.JWT_USERNAME_KEY, user.getUsername())
                .setExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY, currentRoleCode)
                .setExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY, roles));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginTokenDto dto = new LoginTokenDto();
        dto.setAccessToken(tokenInfo.getTokenValue());
        return dto;
    }

}
