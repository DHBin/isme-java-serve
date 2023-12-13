package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.pms.domain.entity.UserRole;
import cn.dhbin.isme.pms.mapper.UserRoleMapper;
import cn.dhbin.isme.pms.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * UserRoleServiceImpl
 *
 * @author dhb
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {
}
