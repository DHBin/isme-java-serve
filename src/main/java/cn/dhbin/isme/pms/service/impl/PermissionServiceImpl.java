package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.isme.pms.mapper.PermissionMapper;
import cn.dhbin.isme.pms.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

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

}
