package cn.dhbin.isme.pms.convert;


import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.entity.Profile;
import cn.dhbin.isme.pms.domain.request.UpdateProfileRequest;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * UpdateProfileRequestToProfile
 *
 * @author dhb
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface UpdateProfileRequestToProfile extends BeanConvertMapper<UpdateProfileRequest, Profile> {
}
