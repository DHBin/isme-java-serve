package cn.dhbin.isme.pms.domain.request;

import java.util.List;
import lombok.Data;

/**
 * 添加角色权限
 *
 * @author dhb
 */
@Data
public class AddRolePermissionsRequest {

    private Long id;

    private List<Long> permissionIds;
}
