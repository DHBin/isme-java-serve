package cn.dhbin.isme.pms.convert;

import static cn.dhbin.isme.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

import cn.dhbin.isme.pms.domain.dto.PermissionDto;
import cn.dhbin.isme.pms.domain.dto.ProfileDto;
import cn.dhbin.isme.pms.domain.dto.RoleDto;
import cn.dhbin.isme.pms.domain.dto.UserDetailDto;
import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.isme.pms.domain.entity.Profile;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.domain.entity.User;
import cn.dhbin.isme.pms.domain.request.CreatePermissionRequest;
import cn.dhbin.isme.pms.domain.request.RegisterUserRequest;
import cn.dhbin.isme.pms.domain.request.UpdatePermissionRequest;
import cn.dhbin.mapstruct.helper.core.BeanConvertMapper;
import org.mapstruct.Mapper;

/**
 * 定义mapstruct
 *
 * @author dhb
 */
public class MapperDefine {

    /**
     * role to roleDto
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface RoleToRoleDto extends BeanConvertMapper<Role, RoleDto> {

    }

    /**
     * user to userDetailDto
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface UserToUserDetailDto extends BeanConvertMapper<User, UserDetailDto> {

    }

    /**
     * RegisterUserRequestToUser
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface RegisterUserRequestToUser
        extends BeanConvertMapper<RegisterUserRequest, User> {

    }


    /**
     * profile to profileDto
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface ProfileToProfileDto extends BeanConvertMapper<Profile, ProfileDto> {

    }

    /**
     * ProfileDtoToProfile
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface ProfileDtoToProfile extends BeanConvertMapper<ProfileDto, Profile> {
    }


    /**
     * CreatePermissionRequestToPermission
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface CreatePermissionRequestToPermission
        extends BeanConvertMapper<CreatePermissionRequest, Permission> {

    }

    /**
     * PermissionToPermissionDto
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface PermissionToPermissionDto extends BeanConvertMapper<Permission, PermissionDto> {
    }


    /**
     * UpdatePermissionRequestToPermission
     */
    @Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
    public interface UpdatePermissionRequestToPermission
        extends BeanConvertMapper<UpdatePermissionRequest, Permission> {

    }

}
