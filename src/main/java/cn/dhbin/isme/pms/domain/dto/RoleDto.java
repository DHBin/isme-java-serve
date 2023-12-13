package cn.dhbin.isme.pms.domain.dto;

import lombok.Data;

/**
 * 角色Dto
 *
 * @author dhb
 */
@Data
public class RoleDto {

    private Long id;

    private String code;

    private String name;

    private Boolean enable;

}
