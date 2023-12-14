package cn.dhbin.isme.pms.service;

import cn.dhbin.isme.common.response.Page;
import cn.dhbin.isme.pms.domain.dto.PermissionDto;
import cn.dhbin.isme.pms.domain.dto.RolePageDto;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.domain.request.AddRolePermissionsRequest;
import cn.dhbin.isme.pms.domain.request.AddRoleUsersRequest;
import cn.dhbin.isme.pms.domain.request.CreateRoleRequest;
import cn.dhbin.isme.pms.domain.request.RemoveRoleUsersRequest;
import cn.dhbin.isme.pms.domain.request.RolePageRequest;
import cn.dhbin.isme.pms.domain.request.UpdateRoleRequest;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * RoleService
 *
 * @author dhb
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return 用户角色列表
     */
    List<Role> findRolesByUserId(Long userId);

    /**
     * 根据角色编码获取权限树
     *
     * @param roleCode 角色编码
     * @return 权限树
     */
    List<Tree<Long>> findRolePermissionsTree(String roleCode);

    /**
     * 根据角色编码获取角色
     *
     * @param roleCode 角色编码
     * @return 角色
     */
    Role findByCode(String roleCode);


    /**
     * 创建角色
     *
     * @param request req
     */
    void createRole(CreateRoleRequest request);

    /**
     * 分页查询
     *
     * @param request 请求
     * @return dto
     */
    Page<RolePageDto> queryPage(RolePageRequest request);

    /**
     * 查询角色权限
     *
     * @param id 角色id
     * @return 角色权限
     */
    List<PermissionDto> findRolePermissions(Long id);

    /**
     * 更新角色，当角色标识是管理员时，不给修改
     *
     * @param id      角色id
     * @param request req
     */
    void updateRole(Long id, UpdateRoleRequest request);

    /**
     * 删除角色，不能删除管理员
     *
     * @param id 角色id
     */
    void removeRole(Long id);

    /**
     * 给角色添加权限
     *
     * @param request req
     */
    void addRolePermissions(AddRolePermissionsRequest request);

    /**
     * 给角色分配用户
     *
     * @param roleId  角色id
     * @param request req
     */
    void addRoleUsers(Long roleId, AddRoleUsersRequest request);

    /**
     * 给角色移除用户
     *
     * @param roleId  角色id
     * @param request req
     */
    void removeRoleUsers(Long roleId, RemoveRoleUsersRequest request);
}
