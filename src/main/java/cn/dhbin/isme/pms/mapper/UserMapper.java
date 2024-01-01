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
    @Select("select x1.*,x2.\"gender\", x2.\"avatar\" , x2.\"address\", x2.\"email\" " +
            " from \"user\" x1 " +
            "    left join \"profile\" x2  on x1.\"id\" = x2.\"userid\"\n" +
            " ${ew.customSqlSegment}")
    List<UserPageDto> SelectByWrapperList(IPage<UserPageDto> page, @Param("ew") QueryWrapper<UserPageDto> wrapper);
}
