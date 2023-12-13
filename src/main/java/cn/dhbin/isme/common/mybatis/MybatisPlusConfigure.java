package cn.dhbin.isme.common.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus配置
 *
 * @author dhb
 */
@Configuration
@MapperScan("cn.dhbin.isme.*.mapper")
public class MybatisPlusConfigure {

}
