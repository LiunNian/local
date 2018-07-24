/*
Navicat MySQL Data Transfer

Source Server         : this
Source Server Version : 100119
Source Host           : localhost:3306
Source Database       : cpcca

Target Server Type    : MYSQL
Target Server Version : 100119
File Encoding         : 65001

Date: 2018-06-05 15:45:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for approve_check
-- ----------------------------
DROP TABLE IF EXISTS `approve_check`;
CREATE TABLE `approve_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '论文ID',
  `pname` varchar(100) DEFAULT NULL COMMENT '论文名称',
  `author` varchar(10) DEFAULT NULL COMMENT '作者',
  `unit` varchar(40) DEFAULT NULL COMMENT '单位',
  `result` double(10,2) DEFAULT NULL COMMENT '结果',
  `coincide` double(10,2) DEFAULT NULL COMMENT '重合字数',
  `reference` double(10,2) DEFAULT NULL COMMENT '去除引用',
  `himself` double(10,2) DEFAULT NULL COMMENT '去除本人',
  `total` double(10,2) DEFAULT NULL COMMENT '总字数',
  `cname` varchar(10) DEFAULT NULL COMMENT '上传人姓名',
  `cid` int(11) DEFAULT NULL COMMENT '上传人id',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论文查重';

-- ----------------------------
-- Records of approve_check
-- ----------------------------

-- ----------------------------
-- Table structure for approve_specialist
-- ----------------------------
DROP TABLE IF EXISTS `approve_specialist`;
CREATE TABLE `approve_specialist` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `ctime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `uid` int(11) DEFAULT NULL COMMENT '专家ID',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论文审批 专家审批表';

-- ----------------------------
-- Records of approve_specialist
-- ----------------------------

-- ----------------------------
-- Table structure for approve_status
-- ----------------------------
DROP TABLE IF EXISTS `approve_status`;
CREATE TABLE `approve_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '论文ID',
  `thid` int(11) DEFAULT NULL COMMENT '主题id 关联表：themes',
  `did` int(11) DEFAULT NULL COMMENT '方向id 关联表：directions',
  `ctime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `result` varchar(10) DEFAULT NULL COMMENT '结果:通过/不通过',
  `status` varchar(10) DEFAULT NULL COMMENT '状态:待初审/初审中/待二审/二审中/....',
  `status_code` varchar(10) DEFAULT NULL COMMENT '状态码:A-1/A-2/B-1/B-2',
  `remark` varchar(50) DEFAULT NULL,
  `grade` double(4,2) DEFAULT NULL COMMENT '平均分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='论文审核状态';

-- ----------------------------
-- Records of approve_status
-- ----------------------------
