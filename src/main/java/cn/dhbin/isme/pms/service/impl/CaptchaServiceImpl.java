package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.pms.service.CaptchaService;
import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.UUID;
import org.springframework.stereotype.Service;

/**
 * 验证码，单机模式
 *
 * @author dhb
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private final Cache<String, ICaptcha> captchaList = new TimedCache<>(60000);

    @Override
    public Pair<String, ICaptcha> create() {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(80, 40, 4, 4);
        String key = UUID.randomUUID().toString(true);
        captchaList.put(key, captcha);
        return Pair.of(key, captcha);
    }

    @Override
    public boolean verify(String key, String code) {
        ICaptcha captcha = captchaList.get(key);
        if (captcha == null) {
            return false;
        }
        captchaList.remove(key);
        return captcha.verify(code);
    }
}
