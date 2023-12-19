DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL,
    `code`        varchar(50)  NOT NULL UNIQUE,
    `type`        varchar(255) NOT NULL,
    `parentId`    int(11)      NULL     DEFAULT NULL,
    `path`        varchar(255) NULL     DEFAULT NULL,
    `redirect`    varchar(255) NULL     DEFAULT NULL,
    `icon`        varchar(255) NULL     DEFAULT NULL,
    `component`   varchar(255) NULL     DEFAULT NULL,
    `layout`      varchar(255) NULL     DEFAULT NULL,
    `keepAlive`   tinyint(4)   NULL     DEFAULT NULL,
    `method`      varchar(255) NULL     DEFAULT NULL,
    `description` varchar(255) NULL     DEFAULT NULL,
    `show`        tinyint(4)   NOT NULL DEFAULT 1 COMMENT '是否展示在页面菜单',
    `enable`      tinyint(4)   NOT NULL DEFAULT 1,
    `order`       int(11)      NULL     DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES (1, '资源管理', 'Resource_Mgt', 'MENU', 2, '/pms/resource', NULL, 'i-fe:list',
        '/src/views/pms/resource/index.vue', NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission`
VALUES (2, '系统管理', 'SysMgt', 'MENU', NULL, NULL, NULL, 'i-fe:grid', NULL, NULL, NULL, NULL, NULL, 1, 1, 2);
INSERT INTO `permission`
VALUES (3, '角色管理', 'RoleMgt', 'MENU', 2, '/pms/role', NULL, 'i-fe:user-check', '/src/views/pms/role/index.vue',
        NULL, NULL, NULL, NULL, 1, 1, 2);
INSERT INTO `permission`
VALUES (4, '用户管理', 'UserMgt', 'MENU', 2, '/pms/user', NULL, 'i-fe:user', '/src/views/pms/user/index.vue', NULL, 1,
        NULL, NULL, 1, 1, 3);
INSERT INTO `permission`
VALUES (5, '分配用户', 'RoleUser', 'MENU', 3, '/pms/role/user/:roleId', NULL, 'i-fe:user-plus',
        '/src/views/pms/role/role-user.vue', NULL, NULL, NULL, NULL, 0, 1, 1);
INSERT INTO `permission`
VALUES (6, '业务示例', 'Demo', 'MENU', NULL, NULL, NULL, 'i-fe:grid', NULL, NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission`
VALUES (7, '图片上传', 'ImgUpload', 'MENU', 6, '/demo/upload', NULL, 'i-fe:image', '/src/views/demo/upload/index.vue',
        NULL, 1, NULL, NULL, 1, 1, 2);
INSERT INTO `permission`
VALUES (8, '个人资料', 'UserProfile', 'MENU', NULL, '/profile', NULL, 'i-fe:user', '/src/views/profile/index.vue', NULL,
        NULL, NULL, NULL, 0, 1, 99);
INSERT INTO `permission`
VALUES (9, '基础功能', 'Base', 'MENU', NULL, '/base', NULL, 'i-fe:grid', NULL, NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `permission`
VALUES (10, '基础组件', 'BaseComponents', 'MENU', 9, '/base/components', NULL, 'i-me:awesome',
        '/src/views/base/index.vue', NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission`
VALUES (11, 'Unocss', 'Unocss', 'MENU', 9, '/base/unocss', NULL, 'i-me:awesome', '/src/views/base/unocss.vue', NULL,
        NULL, NULL, NULL, 1, 1, 2);
INSERT INTO `permission`
VALUES (12, 'KeepAlive', 'KeepAlive', 'MENU', 9, '/base/keep-alive', NULL, 'i-me:awesome',
        '/src/views/base/keep-alive.vue', NULL, 1, NULL, NULL, 1, 1, 3);
INSERT INTO `permission`
VALUES (13, '创建新用户', 'AddUser', 'BUTTON', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `permission`
VALUES (14, '图标 Icon', 'Icon', 'MENU', 9, '/base/icon', NULL, 'i-fe:feather', '/src/views/base/unocss-icon.vue',
        NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `permission`
VALUES (15, 'MeModal', 'TestModal', 'MENU', 9, '/testModal', NULL, 'i-me:dialog', '/src/views/base/test-modal.vue',
        NULL, NULL, NULL, NULL, 1, 1, 5);

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile`
(
    `id`       int(11)      NOT NULL AUTO_INCREMENT,
    `gender`   int(11)      NULL     DEFAULT NULL,
    `avatar`   varchar(255) NOT NULL DEFAULT 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80',
    `address`  varchar(255) NULL     DEFAULT NULL,
    `email`    varchar(255) NULL     DEFAULT NULL,
    `userId`   int(11)      NOT NULL UNIQUE,
    `nickName` varchar(10)  NULL     DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of profile
-- ----------------------------
INSERT INTO `profile`
VALUES (1, NULL, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80', NULL,
        NULL, 1, 'Admin');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`     int(11)     NOT NULL AUTO_INCREMENT,
    `code`   varchar(50) NOT NULL,
    `name`   varchar(50) NOT NULL,
    `enable` tinyint(4)  NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    CONSTRAINT unique_code_name unique (`code`, `name`)
);

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES (1, 'SUPER_ADMIN', '超级管理员', 1);
INSERT INTO `role`
VALUES (2, 'ROLE_QA', '质检员', 1);

-- ----------------------------
-- Table structure for role_permissions_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permissions_permission`;
CREATE TABLE `role_permissions_permission`
(
    `roleId`       int(11) NOT NULL,
    `permissionId` int(11) NOT NULL,
    PRIMARY KEY (`roleId`, `permissionId`),
    CONSTRAINT unique_roleId_permissionId unique (`roleId`, `permissionId`)
);

-- ----------------------------
-- Records of role_permissions_permission
-- ----------------------------
INSERT INTO `role_permissions_permission`
VALUES (2, 1);
INSERT INTO `role_permissions_permission`
VALUES (2, 2);
INSERT INTO `role_permissions_permission`
VALUES (2, 3);
INSERT INTO `role_permissions_permission`
VALUES (2, 4);
INSERT INTO `role_permissions_permission`
VALUES (2, 5);
INSERT INTO `role_permissions_permission`
VALUES (2, 9);
INSERT INTO `role_permissions_permission`
VALUES (2, 10);
INSERT INTO `role_permissions_permission`
VALUES (2, 11);
INSERT INTO `role_permissions_permission`
VALUES (2, 12);
INSERT INTO `role_permissions_permission`
VALUES (2, 14);
INSERT INTO `role_permissions_permission`
VALUES (2, 15);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `username`   varchar(50)  NOT NULL unique,
    `password`   varchar(255) NOT NULL,
    `enable`     tinyint(4)   NOT NULL DEFAULT 1,
    `createTime` datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updateTime` datetime(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'admin', '$2a$10$FsAafxTTVVGXfIkJqvaiV.1vPfq4V9HW298McPldJgO829PR52a56', 1, '2023-11-18 16:18:59.150632',
        '2023-11-18 16:18:59.150632');

-- ----------------------------
-- Table structure for user_roles_role
-- ----------------------------
DROP TABLE IF EXISTS `user_roles_role`;
CREATE TABLE `user_roles_role`
(
    `userId` int(11) NOT NULL,
    `roleId` int(11) NOT NULL,
    PRIMARY KEY (`userId`, `roleId`),
    CONSTRAINT unique_userId_roleId unique (`userId`, `roleId`)
);

-- ----------------------------
-- Records of user_roles_role
-- ----------------------------
INSERT INTO `user_roles_role`
VALUES (1, 1);
INSERT INTO `user_roles_role`
VALUES (1, 2);
