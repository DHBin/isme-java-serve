package cn.dhbin.isme.pms.mapper;

import cn.dhbin.isme.pms.domain.dto.UserPageDto;
import cn.dhbin.isme.pms.domain.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper
 *
 * @author dhb
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserPageDto> SelectByWrapperList(IPage<UserPageDto> page, @Param("ew") QueryWrapper<UserPageDto> wrapper);
}
