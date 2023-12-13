package cn.dhbin.isme.pms.mapper;

import cn.dhbin.isme.pms.domain.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Permission Mapper
 *
 * @author dhb
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色id获取所有权限。
     *
     * @param roleId 角色id
     * @return 权限
     */
    List<Permission> findByRoleId(@Param("roleId") Long roleId);

}
