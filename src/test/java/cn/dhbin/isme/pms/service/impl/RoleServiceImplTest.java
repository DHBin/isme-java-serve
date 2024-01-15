package cn.dhbin.isme.pms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cn.dhbin.isme.ServeApplicationTests;
import cn.dhbin.isme.pms.domain.entity.Permission;
import cn.dhbin.isme.pms.domain.entity.Role;
import cn.dhbin.isme.pms.domain.entity.RolePermission;
import cn.dhbin.isme.pms.domain.request.UpdateRoleRequest;
import cn.dhbin.isme.pms.service.PermissionService;
import cn.dhbin.isme.pms.service.RolePermissionService;
import cn.hutool.core.collection.CollUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

class RoleServiceImplTest extends ServeApplicationTests {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Test
    @Transactional
    void updateRole() {
        jdbcTemplate.execute("insert into role values (null, 'update_role', 'update_role', 1)");
        Role role = roleService.lambdaQuery().eq(Role::getCode, "update_role").one();

        UpdateRoleRequest updateRoleRequest = new UpdateRoleRequest();
        updateRoleRequest.setName("abc");
        updateRoleRequest.setEnable(false);

        roleService.updateRole(role.getId(), updateRoleRequest);

        Role role1 = roleService.getById(role.getId());

        assertEquals("abc", role1.getName());
        assertFalse(role1.getEnable());
    }

    @Test
    @Transactional
    void updateRole_1() {
        jdbcTemplate.execute("insert into role values (null, 'update_role', 'update_role', 1)");
        Role role = roleService.lambdaQuery().eq(Role::getCode, "update_role").one();

        UpdateRoleRequest updateRoleRequest = new UpdateRoleRequest();
        updateRoleRequest.setName("abc");

        roleService.updateRole(role.getId(), updateRoleRequest);

        Role role1 = roleService.getById(role.getId());

        assertEquals("abc", role1.getName());
        assertTrue(role1.getEnable());
    }

    @Test
    @Transactional
    void updateRole_all_null() {
        jdbcTemplate.execute("insert into role values (null, 'update_role', 'update_role', 1)");
        Role role = roleService.lambdaQuery().eq(Role::getCode, "update_role").one();

        UpdateRoleRequest updateRoleRequest = new UpdateRoleRequest();

        roleService.updateRole(role.getId(), updateRoleRequest);

        Role role1 = roleService.getById(role.getId());

        assertEquals("update_role", role1.getName());
        assertTrue(role1.getEnable());
    }


    @Test
    @Transactional
    void updateRole_with_permission() {
        jdbcTemplate.execute("insert into role values (null, 'update_role', 'update_role', 1)");
        Role role = roleService.lambdaQuery().eq(Role::getCode, "update_role").one();
        Permission permission = permissionService.lambdaQuery().list().getFirst();

        UpdateRoleRequest updateRoleRequest = new UpdateRoleRequest();
        updateRoleRequest.setPermissionIds(CollUtil.newArrayList(permission.getId()));

        roleService.updateRole(role.getId(), updateRoleRequest);

        Role role1 = roleService.getById(role.getId());

        assertEquals("update_role", role1.getName());
        assertTrue(role1.getEnable());

        assertEquals(1, rolePermissionService.lambdaQuery()
            .eq(RolePermission::getRoleId, role1.getId())
            .eq(RolePermission::getPermissionId, permission.getId())
            .count());

    }

    @Test
    @Transactional
    void updateRole_with_empty_permission() {
        jdbcTemplate.execute("insert into role values (null, 'update_role', 'update_role', 1)");
        Role role = roleService.lambdaQuery().eq(Role::getCode, "update_role").one();

        UpdateRoleRequest updateRoleRequest = new UpdateRoleRequest();
        updateRoleRequest.setPermissionIds(CollUtil.newArrayList());

        roleService.updateRole(role.getId(), updateRoleRequest);
        Role role1 = roleService.getById(role.getId());

        assertEquals("update_role", role1.getName());
        assertTrue(role1.getEnable());

        assertEquals(0, rolePermissionService.lambdaQuery()
            .eq(RolePermission::getRoleId, role1.getId())
            .count());

    }
}