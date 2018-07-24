/*
Navicat MySQL Data Transfer

Source Server         : MySQL_3306
Source Server Version : 100119
Source Host           : localhost:3306
Source Database       : cpcca

Target Server Type    : MYSQL
Target Server Version : 100119
File Encoding         : 65001

Date: 2018-05-31 09:09:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for train_user
-- ----------------------------
DROP TABLE IF EXISTS `train_user`;
CREATE TABLE `train_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增序号',
  `name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `phonenum` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `unitname` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `jobtitle` varchar(50) DEFAULT NULL COMMENT '职务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train_user
-- ----------------------------
