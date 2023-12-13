package cn.dhbin.isme.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dhbin.isme.common.auth.SaTokenConfigure;
import cn.dhbin.isme.common.response.R;
import cn.dhbin.isme.pms.domain.dto.LoginTokenDto;
import cn.dhbin.isme.pms.domain.request.ChangePasswordRequest;
import cn.dhbin.isme.pms.domain.request.LoginRequest;
import cn.dhbin.isme.pms.domain.request.RegisterUserRequest;
import cn.dhbin.isme.pms.service.CaptchaService;
import cn.dhbin.isme.pms.service.UserService;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.lang.Pair;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 鉴权相关的Controller.
 *
 * @author dhb
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    /**
     * user service.
     */
    private final UserService userService;

    private final CaptchaService captchaService;

    private static final String CAPTCHA_KEY = "captchaKey";

    /**
     * 用户登录接口.
     *
     * @param request 请求
     * @return 返回token
     */
    @PostMapping("/login")
    public R<LoginTokenDto> login(@RequestBody final LoginRequest request,
                                  HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String captchaKey = (String) session.getAttribute(CAPTCHA_KEY);
        if (captchaKey != null) {
            request.setCaptchaKey(captchaKey);
        }
        LoginTokenDto tokenDto = userService.login(request);
        return R.ok(tokenDto);
    }

    /**
     * 注册
     *
     * @return R
     */
    @PostMapping("/register")
    public R<Void> register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return R.ok();
    }

    /**
     * 刷新token
     *
     * @return 新的token
     */
    @GetMapping("/refresh/token")
    public R<LoginTokenDto> refreshToken() {
        LoginTokenDto dto = userService.refreshToken();
        return R.ok(dto);
    }


    /**
     * 切换角色
     *
     * @param roleCode 角色代码
     * @return R
     */
    @PostMapping("/current-role/switch/{roleCode}")
    public R<LoginTokenDto> switchRole(@PathVariable String roleCode) {
        NumberWithFormat userId =
            (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        LoginTokenDto tokenDto = userService.switchRole(userId.longValue(), roleCode);
        return R.ok(tokenDto);
    }

    /**
     * 登出，当使用无状态的jwt时，注销登录不需要任何操作
     *
     * @return R
     */
    @PostMapping("/logout")
    public R<Object> logout() {
        StpUtil.logout();
        return R.ok();
    }

    /**
     * 图形验证码
     */
    @GetMapping("/captcha")
    // todo
    public void captcha(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        Pair<String, ICaptcha> captchaPair = captchaService.create();
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA_KEY, captchaPair.getKey());
        captchaPair.getValue().write(response.getOutputStream());
    }


    /**
     * 修改密码
     *
     * @return R
     */
    @PostMapping("/password")
    public R<Object> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return R.ok();
    }

}
