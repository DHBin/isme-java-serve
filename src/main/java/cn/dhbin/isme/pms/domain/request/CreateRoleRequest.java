package cn.dhbin.isme.pms.domain.request;

import cn.dhbin.mapstruct.helper.core.Convert;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;

/**
 * 创建角色
 *
 * @author dhb
 */
@Data
public class CreateRoleRequest implements Convert {

    @NotBlank(message = "角色编码不能为空")
    private String code;

    @NotBlank(message = "角色名不能为空")
    private String name;

    private List<Long> permissionIds;

    private Boolean enable;

}
