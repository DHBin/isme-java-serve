package cn.dhbin.isme.common.auth;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SaToken的配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    public static final String JWT_USER_ID_KEY = "userId";

    public static final String JWT_USERNAME_KEY = "username";

    public static final String JWT_ROLE_LIST_KEY = "roleCodes";

    public static final String JWT_CURRENT_ROLE_KEY = "currentRoleCode";

    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForStateless();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
            .addPathPatterns("/**")
            .excludePathPatterns("/auth/login")
            .excludePathPatterns("/auth/captcha")
            .excludePathPatterns("/doc.html")
            .excludePathPatterns("/webjars/**")
            .excludePathPatterns("/favicon.ico")
            .excludePathPatterns("/v3/api-docs/**")
        ;
    }

}
