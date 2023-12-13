package cn.dhbin.isme.pms.service;

import cn.dhbin.isme.pms.domain.entity.Role;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * RoleService
 *
 * @author dhb
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<Role> findRolesByUserId(Long userId);

    /**
     * 根据角色编码获取权限树
     *
     * @param roleCode 角色编码
     * @return 权限树
     */
    List<Tree<Long>> findRolePermissionsTree(String roleCode);

    /**
     * 根据角色编码获取角色
     *
     * @param roleCode 角色编码
     * @return 角色
     */
    Role findByCode(String roleCode);


}
