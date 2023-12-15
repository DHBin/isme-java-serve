package cn.dhbin.isme.pms.domain.request;

import lombok.Data;

/**
 * 登录请求参数
 *
 * @author dhb
 */
@Data
public class LoginRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    private String captchaKey;

    private Boolean isQuick;

}
