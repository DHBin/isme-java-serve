package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.dto.PermissionDto;
import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * PermissionToPermissionDto
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface PermissionToPermissionDto extends BeanConvertMapper<Permission, PermissionDto> {
}
