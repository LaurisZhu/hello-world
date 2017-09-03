/*
Navicat MySQL Data Transfer

Source Server         : peter
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : studentsinfomanager

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-09-03 11:51:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `工号` char(10) NOT NULL,
  `姓名` varchar(255) NOT NULL,
  `密码` char(8) NOT NULL DEFAULT '11111111',
  PRIMARY KEY (`工号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('2015210097', '朱良伟', '11111111');
INSERT INTO `manager` VALUES ('2015210098', '曽柏云', '11111111');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `姓名` varchar(15) NOT NULL,
  `年龄` int(2) DEFAULT NULL,
  `学号` char(10) CHARACTER SET armscii8 NOT NULL,
  `专业` char(50) DEFAULT '',
  PRIMARY KEY (`学号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('陈佳豪', '21', '2015210001', '电子信息工程');
INSERT INTO `students` VALUES ('朱良伟', '24', '2015210097', '电子信息工程');
INSERT INTO `students` VALUES ('朱良伟', '21', '2015210098', '通信工程');
INSERT INTO `students` VALUES ('朱良伟', '33', '2015210099', '广播电视工程');
INSERT INTO `students` VALUES ('陈朗', '21', '2015210100', '通信工程');
INSERT INTO `students` VALUES ('程朗', '30', '2015210122', '电子信息工程');
INSERT INTO `students` VALUES ('曽柏云', '22', '2015210123', '信息工程');
INSERT INTO `students` VALUES ('陈朗', '11', '2015210233', '电子信息工程');
SET FOREIGN_KEY_CHECKS=1;
