package cn.dhbin.isme.pms.service;

import cn.dhbin.isme.pms.domain.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 权限服务类
 *
 * @author dhb
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据角色id获取所有权限
     *
     * @param roleId 角色id
     * @return 权限
     */
    List<Permission> findByRoleId(Long roleId);

}
