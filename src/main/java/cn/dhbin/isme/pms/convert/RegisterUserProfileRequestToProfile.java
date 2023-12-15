package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.entity.Profile;
import cn.dhbin.isme.pms.domain.request.RegisterUserProfileRequest;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * RegisterUserProfileRequestToProfile
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface RegisterUserProfileRequestToProfile extends BeanConvertMapper<RegisterUserProfileRequest, Profile> {
}
