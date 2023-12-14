package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.dto.UserDetailDto;
import cn.dhbin.isme.pms.domain.entity.User;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * user to userDetailDto
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface UserToUserDetailDto extends BeanConvertMapper<User, UserDetailDto> {

}
