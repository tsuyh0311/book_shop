/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 04/04/2023 19:24:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p_book
-- ----------------------------
DROP TABLE IF EXISTS `p_book`;
CREATE TABLE `p_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '书的价格',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书的作者',
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片的路径',
  `discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '书的特价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_book
-- ----------------------------
INSERT INTO `p_book` VALUES (1, '测试图书', 50.00, 'winfer', 'static/img/makise.jpg', 40.00);

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sales` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `img_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discount` decimal(11, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (50, 'jspaa', 60.00, 'aaa', 100, 70, 'static/img/default.jpg', 100.00);
INSERT INTO `t_book` VALUES (52, 'jsp实用教程', 50.00, 'aaa', 105, 95, 'static/img/makise.jpg', 100.00);
INSERT INTO `t_book` VALUES (53, 'jsp实用教程', 50.00, 'aaa', 102, 98, 'static/img/makise.jpg', 100.00);
INSERT INTO `t_book` VALUES (55, 'jsp实用教程', 50.00, 'aaa', 101, 99, 'static/img/makise.jpg', 100.00);
INSERT INTO `t_book` VALUES (56, 'jsp实用教程', 50.00, 'aaa', 100, 100, 'static/img/makise.jpg', 100.00);
INSERT INTO `t_book` VALUES (57, 'jsp实用教程', 60.00, 'aaa', 102, 98, 'static/img/makise.jpg', 100.00);
INSERT INTO `t_book` VALUES (59, 'ceshi', 500.00, 'winfer', 500, 100, 'static/img/default.jpg', NULL);
INSERT INTO `t_book` VALUES (60, '1111', 500.00, 'winfer', 500, 100, 'static/img/default.jpg', NULL);
INSERT INTO `t_book` VALUES (61, '测试', 500.00, 'winfer', 500, 100, 'static/img/makise.jpg', 35.00);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('16262699039962', '2021-07-14 21:38:23', 130.00, 1, 2);
INSERT INTO `t_order` VALUES ('16262729810752', '2021-07-14 22:29:41', 78.50, 1, 2);
INSERT INTO `t_order` VALUES ('16264235967522', '2021-07-16 16:19:56', 211.05, 1, 2);
INSERT INTO `t_order` VALUES ('16322868274881', '2021-09-22 13:00:27', 48.00, 1, 1);
INSERT INTO `t_order` VALUES ('16406560049541', '2021-12-28 09:46:44', 100.00, 0, 1);
INSERT INTO `t_order` VALUES ('16554711251961', '2022-06-17 21:05:25', 200.00, 0, 1);

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES (33, '赌神', 1, 66.50, '16262699039962');
INSERT INTO `t_order_item` VALUES (34, '西游记', 1, 12.00, '16262699039962');
INSERT INTO `t_order_item` VALUES (35, 'Lua 语言程序设计', 1, 51.50, '16262699039962');
INSERT INTO `t_order_item` VALUES (36, '赌神', 1, 66.50, '16262729810752');
INSERT INTO `t_order_item` VALUES (37, '西游记', 1, 12.00, '16262729810752');
INSERT INTO `t_order_item` VALUES (38, '西游记', 2, 12.00, '16264235967522');
INSERT INTO `t_order_item` VALUES (39, '赌神', 1, 66.50, '16264235967522');
INSERT INTO `t_order_item` VALUES (40, 'Java 编程思想', 1, 99.50, '16264235967522');
INSERT INTO `t_order_item` VALUES (41, '水浒传', 1, 33.05, '16264235967522');
INSERT INTO `t_order_item` VALUES (42, '赌神', 1, 40.00, '16322868274881');
INSERT INTO `t_order_item` VALUES (43, '西游记', 1, 8.00, '16322868274881');
INSERT INTO `t_order_item` VALUES (44, 'jsp实用教程', 1, 100.00, '16406560049541');
INSERT INTO `t_order_item` VALUES (45, 'jsp实用教程', 1, 100.00, '16554711251961');
INSERT INTO `t_order_item` VALUES (46, 'jsp实用教程', 2, 100.00, '16554711251961');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', 'zjf@hutb.com');
INSERT INTO `t_user` VALUES (2, 'zjf', '123456', 'zjf@123.com');
INSERT INTO `t_user` VALUES (3, 'admin123', 'admin123', 'admin@123.com');
INSERT INTO `t_user` VALUES (6, 'zjf123', '123', 'zjf@qq.com');
INSERT INTO `t_user` VALUES (7, 'wyf123', '123456', 'wyf123@qq.com');
INSERT INTO `t_user` VALUES (8, 'admin123456', '123456', 'wyf123@qq.com');
INSERT INTO `t_user` VALUES (9, 'admin12345', '123456', '1025372079@qq.com');
INSERT INTO `t_user` VALUES (10, 'zjf123123', '123456', '1025372079@qq.com');
INSERT INTO `t_user` VALUES (11, 'zjf1231234', '123456', '1025372079@qq.com');
INSERT INTO `t_user` VALUES (12, 'maiduo666', '123456', '1025372079@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
