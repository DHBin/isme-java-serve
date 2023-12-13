package cn.dhbin.isme.pms.domain.request;

import cn.dhbin.mapstruct.helper.core.Convert;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 注册用户
 *
 * @author dhb
 */
@Data
public class RegisterUserRequest implements Convert {

    @Length(min = 6, max = 20, message = "用户名长度必须是6到20之间")
    private String username;

    @Length(min = 6, max = 20, message = "密码长度必须是6到20之间")
    private String password;

    private Boolean enable;

    private RegisterUserProfileRequest profile;

    private List<Long> roleIds;


}
