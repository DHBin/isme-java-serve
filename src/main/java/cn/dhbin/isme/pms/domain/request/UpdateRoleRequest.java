package cn.dhbin.isme.pms.domain.request;

import java.util.List;
import lombok.Data;

/**
 * 更新角色
 *
 * @author dhb
 */
@Data
public class UpdateRoleRequest {

    private String name;

    private Boolean enable;

    private List<Long> permissionIds;


}
