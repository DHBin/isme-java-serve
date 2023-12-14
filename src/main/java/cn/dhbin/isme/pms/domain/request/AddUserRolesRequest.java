package cn.dhbin.isme.pms.domain.request;

import java.util.List;
import lombok.Data;

/**
 * 给用户分配角色
 *
 * @author dhb
 */
@Data
public class AddUserRolesRequest {

    private List<Long> roleIds;

}
