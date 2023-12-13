package cn.dhbin.isme.pms.service;

import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.lang.Pair;

/**
 * 验证码服务
 *
 * @author dhb
 */
public interface CaptchaService {

    /**
     * 创建验证码
     *
     * @return key与验证码
     */
    Pair<String, ICaptcha> create();

    /**
     * 校验验证码
     *
     * @param key  验证码的key
     * @param code 验证码
     * @return 判断是一致
     */
    boolean verify(String key, String code);

}
