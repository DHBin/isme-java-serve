package cn.dhbin.isme.pms.domain.request;

import java.util.List;
import lombok.Data;

/**
 * 给角色分配用户
 *
 * @author dhb
 */
@Data
public class RemoveRoleUsersRequest {

    private List<Long> userIds;

}
