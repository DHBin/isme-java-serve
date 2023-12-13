package cn.dhbin.isme.pms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色与权限关系
 *
 * @author dhb
 */
@Data
@TableName("role_permissions_permission")
public class RolePermission {

    @TableField("roleId")
    private Long roleId;

    @TableField("permissionId")
    private Long permissionId;

}
