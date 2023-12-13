package cn.dhbin.isme.pms.service.impl;

import cn.dhbin.isme.pms.domain.entity.Profile;
import cn.dhbin.isme.pms.mapper.ProfileMapper;
import cn.dhbin.isme.pms.service.ProfileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * ProfileServiceImpl
 *
 * @author dhb
 */
@Service
public class ProfileServiceImpl extends ServiceImpl<ProfileMapper, Profile>
    implements ProfileService {

    @Override
    public Profile findByUserId(Long userId) {
        return lambdaQuery().eq(Profile::getUserId, userId).one();
    }

}
