package cn.dhbin.isme.pms.domain.entity;

import cn.dhbin.mapstruct.helper.core.Convert;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户信息
 *
 * @author dhb
 */
@Data
@TableName("profile")
public class Profile implements Convert {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer gender;

    private String avatar;

    private String address;

    private String email;

    @TableField("userId")
    private Long userId;

    @TableField("nickName")
    private String nickName;

}
