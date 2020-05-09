/*
 Navicat Premium Data Transfer

 Source Server         : 商保通
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 47.113.91.143:31060
 Source Schema         : webcloudapp

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 16/04/2020 17:38:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布时间',
  `total` int(8) NULL DEFAULT 0 COMMENT '体验次数',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '体验地址',
  `image` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES (1, '小粉怪闪避吃钻', '刘德华', '2020-04-16 00:00:00', 12, 'http://h.4399.com/play/212540.htm', '4399_14495854703.jpg');
INSERT INTO `app` VALUES (2, '维京战士拼图', '张学友', '2020-04-16 00:00:00', 9, 'http://h.4399.com/play/212539.htm', '4399_14474986369.jpg');
INSERT INTO `app` VALUES (7, '特警营救公主', '刘雪华', '2020-04-16 12:30:59', 3, 'http://h.4399.com/play/212538.htm', '4399_14145369917.jpg');
INSERT INTO `app` VALUES (8, '恐龙直升机救援', '张学友', '2020-04-16 12:38:32', 1, 'http://h.4399.com/play/212536.htm', '4399_13490625301.jpg');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` int(11) NULL DEFAULT NULL COMMENT 'app编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论用户',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, 1, '11', '2020-4-16 10:56:02', '这个应用很好很好很好');
INSERT INTO `comment` VALUES (3, 1, '11', '2020-4-16 10:57:36', '确实不错');
INSERT INTO `comment` VALUES (4, 1, '11', '2020-4-16 11:01:56', '我很喜欢这个应用');
INSERT INTO `comment` VALUES (5, 8, '13158011462', '2020-4-16 12:41:00', '这个应用我很喜欢，效果很炫酷');
INSERT INTO `comment` VALUES (6, 1, '13158011462', '2020-4-16 16:47:04', '我很喜欢这个应用');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '11', '6512bd43d9caa6e02c990b0a82652dca');
INSERT INTO `user` VALUES (2, '111', '698d51a19d8a121ce581499d7b701668');
INSERT INTO `user` VALUES (3, '11122', '698d51a19d8a121ce581499d7b701668');
INSERT INTO `user` VALUES (4, '13158011462', '96e79218965eb72c92a549dd5a330112');
INSERT INTO `user` VALUES (5, '13158011463', 'e10adc3949ba59abbe56e057f20f883e');

SET FOREIGN_KEY_CHECKS = 1;
