package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.dto.ProfileDto;
import cn.dhbin.isme.pms.domain.entity.Profile;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * ProfileDtoToProfile
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface ProfileDtoToProfile extends BeanConvertMapper<ProfileDto, Profile> {
}
