package cn.dhbin.isme.pms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限
 *
 * @author dhb
 */
@Data
@TableName("permission")
public class Permission {

    private Long id;

    private String name;

    private String code;

    private String type;

    @TableField("parentId")
    private Long parentId;

    private String path;

    private String redirect;

    private String icon;

    private String component;

    private String layout;

    @TableField("keepAlive")
    private Boolean keepalive;

    private String method;

    private String description;

    @TableField("`show`")
    private Boolean show;

    private Boolean enable;

    @TableField("`order`")
    private Integer order;

}
