/*
Navicat MySQL Data Transfer

Source Server         : wata
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : smbms

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-12-21 03:45:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `s_relevance`
-- ----------------------------
DROP TABLE IF EXISTS `s_relevance`;
CREATE TABLE `s_relevance` (
  `RE_ID` varchar(32) NOT NULL COMMENT '关联表主键',
  `RE_RID` varchar(32) DEFAULT NULL COMMENT '角色id',
  `RE_SID` varchar(32) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`RE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_relevance
-- ----------------------------

-- ----------------------------
-- Table structure for `s_resource`
-- ----------------------------
DROP TABLE IF EXISTS `s_resource`;
CREATE TABLE `s_resource` (
  `S_ID` varchar(32) NOT NULL COMMENT '资源主键',
  `S_NAME` varchar(20) DEFAULT NULL COMMENT '资源名称',
  `S_LEVEL` int(2) DEFAULT NULL COMMENT '资源级别',
  `S_URL` varchar(1000) DEFAULT NULL COMMENT '资源路径',
  `S_PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父级资源   0为最根级资源',
  `S_ICON` varchar(50) DEFAULT NULL COMMENT '图标',
  `S_STATUS` int(2) DEFAULT NULL COMMENT '状态   1：正常；2：禁用',
  `S_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `S_CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `S_UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '修改者id',
  `S_UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`S_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `s_role`
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `R_ID` varchar(32) NOT NULL COMMENT '角色id',
  `R_NAME` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `R_LEVEL` int(2) DEFAULT NULL COMMENT '角色级别 0为最高级',
  `R_CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `R_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `R_UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `R_UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`R_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------

-- ----------------------------
-- Table structure for `s_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `U_ID` varchar(32) NOT NULL COMMENT '主键，用户id',
  `U_ACCOUNT` varchar(20) DEFAULT NULL COMMENT '账户',
  `U_PASSWORD` varchar(20) DEFAULT NULL COMMENT '密码',
  `U_NAME` varchar(15) DEFAULT NULL COMMENT '名称',
  `U_ROLE_ID` varchar(32) DEFAULT NULL COMMENT '角色id',
  `U_STATUS` int(2) DEFAULT NULL COMMENT '状态（1：正常；2：禁用）',
  `U_HEAD` varchar(1000) DEFAULT NULL COMMENT '头像路径',
  `U_CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `U_CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `U_UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '修改人',
  `U_UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
