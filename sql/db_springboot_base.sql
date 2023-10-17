/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : db_springboot_base

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 07/05/2022 10:29:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for database_bak
-- ----------------------------
DROP TABLE IF EXISTS `database_bak`;
CREATE TABLE `database_bak`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `filename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `filepath` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of database_bak
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int NOT NULL,
  `parent_id` bigint NULL DEFAULT NULL,
  `is_button` bit(1) NOT NULL,
  `is_show` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsbtnjocfrq29e8taxdwo21gic`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `FKsbtnjocfrq29e8taxdwo21gic` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (2, '2020-03-14 14:26:03', '2020-03-14 18:24:53', '系统设置', '', 'mdi-settings', 0, NULL, b'0', b'1');
INSERT INTO `menu` VALUES (3, '2020-03-14 16:58:55', '2020-03-14 18:26:02', '菜单管理', '/menu/list', 'mdi-view-list', 1, 2, b'0', b'1');
INSERT INTO `menu` VALUES (5, '2020-03-14 17:04:44', '2020-03-14 18:27:53', '新增', '/menu/add', 'mdi-plus', 2, 3, b'0', b'1');
INSERT INTO `menu` VALUES (7, '2020-03-14 17:07:43', '2020-03-15 12:11:25', '角色管理', '/role/list', 'mdi-account-settings-variant', 5, 2, b'0', b'1');
INSERT INTO `menu` VALUES (8, '2020-03-14 18:28:48', '2020-03-21 22:04:45', '编辑', '/menu/edit', 'mdi-grease-pencil', 3, 3, b'1', b'1');
INSERT INTO `menu` VALUES (9, '2020-03-14 18:30:00', '2020-03-21 22:08:20', '删除', '/menu/delete', 'mdi-close', 4, 3, b'1', b'1');
INSERT INTO `menu` VALUES (10, '2020-03-15 12:12:00', '2020-03-15 12:12:00', '添加', '/role/add', 'mdi-account-plus', 6, 7, b'0', b'1');
INSERT INTO `menu` VALUES (11, '2020-03-15 12:12:36', '2020-03-21 22:10:45', '编辑', '/role/edit', 'mdi-account-edit', 7, 7, b'1', b'1');
INSERT INTO `menu` VALUES (12, '2020-03-15 12:13:19', '2020-03-21 22:15:27', '删除', '/role/delete', 'mdi-account-remove', 8, 7, b'1', b'1');
INSERT INTO `menu` VALUES (13, '2020-03-15 12:14:52', '2020-03-15 12:17:00', '用户管理', '/user/list', 'mdi-account-multiple', 9, 2, b'0', b'1');
INSERT INTO `menu` VALUES (14, '2020-03-15 12:15:22', '2020-03-15 12:17:27', '添加', '/user/add', 'mdi-account-plus', 10, 13, b'0', b'1');
INSERT INTO `menu` VALUES (15, '2020-03-16 17:18:14', '2020-03-21 22:11:19', '编辑', '/user/edit', 'mdi-account-edit', 11, 13, b'1', b'1');
INSERT INTO `menu` VALUES (16, '2020-03-16 17:19:01', '2020-03-21 22:15:36', '删除', '/user/delete', 'mdi-account-remove', 12, 13, b'1', b'1');
INSERT INTO `menu` VALUES (19, '2020-03-22 11:24:36', '2020-03-22 11:26:00', '上传图片', '/upload/upload_photo', 'mdi-arrow-up-bold-circle', 0, 13, b'0', b'0');
INSERT INTO `menu` VALUES (20, '2020-03-22 14:09:35', '2020-03-22 14:09:47', '日志管理', '/log/list', 'mdi-tag-multiple', 13, 2, b'0', b'1');
INSERT INTO `menu` VALUES (21, '2020-03-22 14:11:39', '2020-03-22 14:11:39', '删除', '/log/delete', 'mdi-tag-remove', 14, 20, b'1', b'1');
INSERT INTO `menu` VALUES (22, '2020-03-22 14:12:57', '2020-03-22 14:46:55', '清空日志', '/log/delete_all', 'mdi-delete-circle', 15, 20, b'1', b'1');
INSERT INTO `menu` VALUES (23, '2020-03-22 14:46:40', '2020-03-22 14:47:09', '数据备份', '/database_bak/list', 'mdi-database', 16, 2, b'0', b'1');
INSERT INTO `menu` VALUES (24, '2020-03-22 14:48:07', '2020-03-22 15:13:41', '备份', '/database_bak/add', 'mdi-database-plus', 17, 23, b'1', b'1');
INSERT INTO `menu` VALUES (25, '2020-03-22 14:49:03', '2020-03-22 14:49:03', '删除', '/database_bak/delete', 'mdi-database-minus', 18, 23, b'1', b'1');
INSERT INTO `menu` VALUES (26, '2020-03-22 19:36:20', '2020-03-22 19:36:20', '还原', 'restore/database_bak/restore', 'mdi-database-minus', 19, 23, b'1', b'1');

-- ----------------------------
-- Table structure for operater_log
-- ----------------------------
DROP TABLE IF EXISTS `operater_log`;
CREATE TABLE `operater_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operator` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operater_log
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '2020-03-15 13:16:38', '2020-03-22 19:36:34', '超级管理员', '超级管理员拥有最高权限。', 1);
INSERT INTO `role` VALUES (2, '2020-03-15 13:18:57', '2020-03-21 22:18:43', '普通管理员', '普通管理员只有部分权限', 1);
INSERT INTO `role` VALUES (4, '2020-03-21 20:11:00', '2020-03-23 17:49:00', '测试角色', 'sadsa', 0);

-- ----------------------------
-- Table structure for role_authorities
-- ----------------------------
DROP TABLE IF EXISTS `role_authorities`;
CREATE TABLE `role_authorities`  (
  `role_id` bigint NOT NULL,
  `authorities_id` bigint NOT NULL,
  INDEX `FKhj7ap1o1cjrl7enr9arf5f2qp`(`authorities_id` ASC) USING BTREE,
  INDEX `FKg3xdaexmr0x1qx8omhvjtk46d`(`role_id` ASC) USING BTREE,
  CONSTRAINT `FKg3xdaexmr0x1qx8omhvjtk46d` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhj7ap1o1cjrl7enr9arf5f2qp` FOREIGN KEY (`authorities_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_authorities
-- ----------------------------
INSERT INTO `role_authorities` VALUES (2, 2);
INSERT INTO `role_authorities` VALUES (2, 3);
INSERT INTO `role_authorities` VALUES (2, 5);
INSERT INTO `role_authorities` VALUES (2, 7);
INSERT INTO `role_authorities` VALUES (2, 11);
INSERT INTO `role_authorities` VALUES (2, 13);
INSERT INTO `role_authorities` VALUES (2, 16);
INSERT INTO `role_authorities` VALUES (1, 2);
INSERT INTO `role_authorities` VALUES (1, 3);
INSERT INTO `role_authorities` VALUES (1, 5);
INSERT INTO `role_authorities` VALUES (1, 8);
INSERT INTO `role_authorities` VALUES (1, 9);
INSERT INTO `role_authorities` VALUES (1, 7);
INSERT INTO `role_authorities` VALUES (1, 10);
INSERT INTO `role_authorities` VALUES (1, 11);
INSERT INTO `role_authorities` VALUES (1, 12);
INSERT INTO `role_authorities` VALUES (1, 13);
INSERT INTO `role_authorities` VALUES (1, 14);
INSERT INTO `role_authorities` VALUES (1, 15);
INSERT INTO `role_authorities` VALUES (1, 16);
INSERT INTO `role_authorities` VALUES (1, 19);
INSERT INTO `role_authorities` VALUES (1, 20);
INSERT INTO `role_authorities` VALUES (1, 21);
INSERT INTO `role_authorities` VALUES (1, 22);
INSERT INTO `role_authorities` VALUES (1, 23);
INSERT INTO `role_authorities` VALUES (1, 24);
INSERT INTO `role_authorities` VALUES (1, 25);
INSERT INTO `role_authorities` VALUES (1, 26);
INSERT INTO `role_authorities` VALUES (4, 2);
INSERT INTO `role_authorities` VALUES (4, 13);
INSERT INTO `role_authorities` VALUES (4, 15);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head_pic` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `username` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_btsosjytrl4hu7fnm1intcpo8`(`username` ASC) USING BTREE,
  INDEX `FKg09b8o67eu61st68rv6nk8npj`(`role_id` ASC) USING BTREE,
  CONSTRAINT `FKg09b8o67eu61st68rv6nk8npj` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2020-03-18 19:18:53', '2020-03-22 12:43:54', 'ylrc@qq.com', '20200322/1584850135123.jpg', '13356565656', '123456', 1, 1, '测试账号', 1);
INSERT INTO `user` VALUES (2, '2020-03-18 19:20:36', '2020-03-21 22:18:55', 'llq@qq.com', '20200318/1584530412075.jpg', '13918655656', '123456', 1, 1, '测试账号2', 2);
INSERT INTO `user` VALUES (5, '2020-03-20 20:42:19', '2020-03-23 17:50:19', 'yw@qq.com', '20200323/1584956702094.png', '13356565656', '123456', 1, 1, '小刘同志', 4);

SET FOREIGN_KEY_CHECKS = 1;
