package cn.dhbin.isme.common.auth;

import cn.dev33.satoken.stp.StpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 角色检查切面
 *
 * @author dhb
 */
@Aspect
@Component
public class RoleValidateAspect {


    @Pointcut("@annotation(Roles)")
    public void pointcut() {

    }


    /**
     * 在执行方法前处理
     *
     * @param joinPoint jp
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature methodSignature) {
            Roles roles = methodSignature.getMethod().getAnnotation(Roles.class);
            StpUtil.checkRoleOr(roles.value());
        }
    }
}
