package cn.dhbin.isme.pms.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 重置密码
 *
 * @author dhb
 */
@Data
public class UpdatePasswordRequest {


    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须大于6到20之间")
    private String password;

}
