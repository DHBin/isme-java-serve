package cn.dhbin.isme.pms.domain.request;

import cn.dhbin.mapstruct.helper.core.Convert;
import lombok.Data;

/**
 * 注册用户的用户信息
 *
 * @author dhb
 */
@Data
public class RegisterUserProfileRequest implements Convert {

    private String nickName;

    private Integer gender;

    private String avatar;

    private String address;

    private String email;


}
