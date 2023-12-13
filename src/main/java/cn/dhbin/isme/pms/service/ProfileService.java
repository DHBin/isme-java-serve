package cn.dhbin.isme.pms.service;

import cn.dhbin.isme.pms.domain.entity.Profile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ProfileService
 *
 * @author dhb
 */
public interface ProfileService extends IService<Profile> {

    /**
     * 通过用户id获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    Profile findByUserId(Long userId);

}
