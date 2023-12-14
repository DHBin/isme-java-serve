package cn.dhbin.isme.pms.domain.request;

import cn.dhbin.mapstruct.helper.core.Convert;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建权限
 *
 * @author dhb
 */
@Data
public class CreatePermissionRequest implements Convert {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String type;


    private Long parentId;

    private String path;

    private String redirect;

    private String icon;

    private String component;

    private String layout;

    private Boolean keepalive;

    private String method;

    private String description;

    private Boolean show;

    private Boolean enable;

    private Integer order;
}
