package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.dto.RolePageDto;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * role to roleDto
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface RoleToRolePageDto extends BeanConvertMapper<Role, RolePageDto> {

}
