package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.entity.User;
import cn.dhbin.isme.pms.domain.request.RegisterUserRequest;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * RegisterUserRequestToUser
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface RegisterUserRequestToUser
    extends BeanConvertMapper<RegisterUserRequest, User> {

}
