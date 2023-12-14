package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.common.auth.PmsConstant;
import cn.dhbin.isme.common.exception.BadRequestException;
import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.mapper.RoleMapper;
import cn.dhbin.isme.pms.service.PermissionService;
import cn.dhbin.isme.pms.service.RoleService;
import cn.dhbin.isme.pms.util.PermissionUtil;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RoleServiceImpl
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final PermissionService permissionService;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return getBaseMapper().findRolesByUserId(userId);
    }

    @Override
    public List<Tree<Long>> findRolePermissionsTree(String roleCode) {
        Role role = this.findByCode(roleCode);
        if (role == null) {
            throw new BadRequestException("当前角色不存在或者已删除");
        }
        List<Permission> permissions =
            PmsConstant.ROLE_ADMIN.equals(roleCode) ? permissionService.list()
                : permissionService.findByRoleId(role.getId());

        return PermissionUtil.toTreeNode(permissions, null);
    }

    @Override
    public Role findByCode(String roleCode) {
        return lambdaQuery().eq(Role::getCode, roleCode).one();
    }

}
