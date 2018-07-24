/*
Navicat MySQL Data Transfer

Source Server         : this
Source Server Version : 100119
Source Host           : localhost:3306
Source Database       : cpcca

Target Server Type    : MYSQL
Target Server Version : 100119
File Encoding         : 65001

Date: 2018-06-05 15:45:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for train_apply
-- ----------------------------
DROP TABLE IF EXISTS `train_apply`;
CREATE TABLE `train_apply` (
  `id` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `mid` int(11) NOT NULL COMMENT '培训信息ID',
  `name` varchar(10) DEFAULT NULL COMMENT '用户名',
  `nation` varchar(15) DEFAULT NULL COMMENT '民族',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `province` varchar(25) DEFAULT NULL COMMENT '省份',
  `city` varchar(30) DEFAULT NULL COMMENT '市',
  `birth` varchar(12) DEFAULT NULL COMMENT '出生年月日',
  `idcard` varchar(25) DEFAULT NULL COMMENT '省份号',
  `duty` varchar(20) DEFAULT NULL COMMENT '职务',
  `unitname` varchar(30) DEFAULT NULL COMMENT '单位名称',
  `unitaddress` varchar(200) DEFAULT NULL COMMENT '单位地址',
  `price` double(10,2) NOT NULL COMMENT '价格',
  `ismember` int(3) DEFAULT NULL COMMENT '是否会员单位',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `officephone` varchar(20) DEFAULT NULL COMMENT '办公电话',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='培训报名表';

-- ----------------------------
-- Records of train_apply
-- ----------------------------

-- ----------------------------
-- Table structure for train_memberunit
-- ----------------------------
DROP TABLE IF EXISTS `train_memberunit`;
CREATE TABLE `train_memberunit` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `address` varchar(150) DEFAULT NULL COMMENT '地址',
  `level` int(3) DEFAULT NULL COMMENT '级别',
  `stopDate` datetime DEFAULT NULL COMMENT '到期时间',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员单位信息';

-- ----------------------------
-- Records of train_memberunit
-- ----------------------------

-- ----------------------------
-- Table structure for train_mesaage
-- ----------------------------
DROP TABLE IF EXISTS `train_mesaage`;
CREATE TABLE `train_mesaage` (
  `id` int(11) NOT NULL COMMENT 'id',
  `classes` varchar(50) DEFAULT NULL COMMENT '班次',
  `intro` varchar(500) DEFAULT NULL COMMENT '简介',
  `level` int(3) DEFAULT NULL COMMENT '级别:预报名/正式报名',
  `startDate` datetime DEFAULT NULL COMMENT '开始时间',
  `stopDate` datetime DEFAULT NULL COMMENT '结束时间',
  `imgpath` varchar(120) DEFAULT NULL COMMENT '图片地址',
  `price` double(10,2) DEFAULT NULL COMMENT '价格',
  `memberprice` double(10,2) DEFAULT NULL COMMENT '会员价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='培训信息';

-- ----------------------------
-- Records of train_mesaage
-- ----------------------------

-- ----------------------------
-- Table structure for train_user
-- ----------------------------
DROP TABLE IF EXISTS `train_user`;
CREATE TABLE `train_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增序号',
  `name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `phonenum` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `password` varchar(50) DEFAULT NULL,
  `pwd` varchar(16) DEFAULT NULL COMMENT '原密码',
  `unitname` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `jobtitle` varchar(50) DEFAULT NULL COMMENT '职务',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='培训用户表';

-- ----------------------------
-- Records of train_user
-- ----------------------------
