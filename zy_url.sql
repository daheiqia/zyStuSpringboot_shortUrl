/*
 Navicat Premium Data Transfer

 Source Server         : zy
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : zy_springboot

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 08/07/2021 17:48:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for zy_url
-- ----------------------------
DROP TABLE IF EXISTS `zy_url`;
CREATE TABLE `zy_url`  (
  `uid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shorl_url_id` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩短后的短址id',
  `long_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原网址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_view` datetime NULL DEFAULT NULL COMMENT '上一次访问时间',
  `view_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问密码',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of zy_url
-- ----------------------------
INSERT INTO `zy_url` VALUES ('bfc970e9-2e09-49aa-97fc-12cc15120a44', '256hqM', 'https://www.baidu.com/', '2021-07-08 17:16:58', NULL, '111111');

SET FOREIGN_KEY_CHECKS = 1;
