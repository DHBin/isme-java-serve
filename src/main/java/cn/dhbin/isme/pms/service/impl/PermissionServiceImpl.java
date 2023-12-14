package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.pms.domain.dto.PermissionDto;
import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.isme.pms.domain.request.CreatePermissionRequest;
import cn.dhbin.isme.pms.mapper.PermissionMapper;
import cn.dhbin.isme.pms.service.PermissionService;
import cn.dhbin.isme.pms.util.PermissionUtil;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限服务类的实现类，主要负责权限相关的处理
 *
 * @author dhb
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService {

    @Override
    public List<Permission> findByRoleId(Long roleId) {
        return getBaseMapper().findByRoleId(roleId);
    }

    @Override
    public void create(CreatePermissionRequest request) {
        Permission permission = request.convert(Permission.class);
        this.save(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBatch(List<CreatePermissionRequest> request) {
        List<Permission> list = request.stream().map(dto -> dto.convert(Permission.class))
            .toList();
        this.saveBatch(list);
    }

    @Override
    public List<PermissionDto> findAllMenu() {
        return lambdaQuery().eq(Permission::getType, "MENU")
            .list()
            .stream()
            .map(permission -> permission.convert(PermissionDto.class))
            .toList();
    }

    @Override
    public List<Tree<Long>> findAllMenuTree() {
        List<Permission> permissions = lambdaQuery().eq(Permission::getType, "MENU")
            .orderByAsc(Permission::getOrder)
            .list()
            .stream()
            .toList();
        return PermissionUtil.toTreeNode(permissions, null);
    }

    @Override
    public List<Permission> findButtonAndApi(Long parentId) {
        return lambdaQuery().in(Permission::getType, "BUTTON", "API")
            .orderByAsc(Permission::getOrder)
            .list()
            .stream()
            .toList();
    }

    @Override
    public boolean validateMenuPath(String path) {
        return lambdaQuery().eq(Permission::getPath, path).exists();
    }

}
