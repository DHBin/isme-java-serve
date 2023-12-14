package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.domain.request.CreateRoleRequest;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * CreateRoleRequestToRole
 *
 * @author dhb
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface CreateRoleRequestToRole extends BeanConvertMapper<CreateRoleRequest, Role> {
}
