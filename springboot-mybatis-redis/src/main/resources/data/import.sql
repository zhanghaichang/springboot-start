/*
Navicat MySQL Data Transfer

Source Server         : 虚拟机
Source Server Version : 50718
Source Host           : 172.16.37.70:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-28 18:16:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'zhanghaichang@163.com', null, '12345678', null, null, null, 'test1', '1');
INSERT INTO `user_info` VALUES ('2', 'zhangsan', null, '121', null, null, null, 'test2', '2');
INSERT INTO `user_info` VALUES ('3', null, null, '121', null, null, null, 'test3', '1');
INSERT INTO `user_info` VALUES ('4', null, null, '111', null, null, null, 'test4', '2');
INSERT INTO `user_info` VALUES ('5', null, null, '2222', null, null, null, 'test5', '1');