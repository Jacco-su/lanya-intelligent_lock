/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50717
Source Host           : localhost:33306
Source Database       : lanya

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-11 10:28:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dept_copy
-- ----------------------------
DROP TABLE IF EXISTS `t_dept_copy`;
CREATE TABLE `t_dept_copy` (
  `id` varchar(255) NOT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `orgId` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  `treeShow` int(11) DEFAULT NULL,
  `bk` varchar(255) DEFAULT NULL,
  `haskh` int(11) DEFAULT '0',
  `qgorgId` varchar(32) DEFAULT NULL,
  `areacode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept_copy
-- ----------------------------
INSERT INTO `t_dept_copy` VALUES ('21', null, '管理区', '0', null, '2c92768b533bd8ff01533cdc43c70005', null, null, '0', '21', '1');
INSERT INTO `t_dept_copy` VALUES ('297ebe0e54b73a2f0154be29a94f0048', null, '领导', '1', null, '4028824a3734af2f013734b0fcc80001', '1', null, '0', '4028815652d9c52a0152d9c52a7f0000', '41');
INSERT INTO `t_dept_copy` VALUES ('2c92768b533bd8ff01533cdc43c70005', null, '中原区', '30', null, 'null', '1', null, '0', '4028815652d9c52a0152d9c52a7f0001', '41');
INSERT INTO `t_dept_copy` VALUES ('2c92768b533bd8ff01533cdf59880006', null, '门区', '001', null, '2c92768b533bd8ff01533cdc43c70005', '1', null, '0', '4028815652d9c52a0152d9c52a7f0001', '4101');
INSERT INTO `t_dept_copy` VALUES ('2c92768b533bd8ff01533cdf96b00007', null, '监控', '002', null, '2c92768b533bd8ff01533cdc43c70005', '1', null, '0', '4028815652d9c52a0152d9c52a7f0001', '4101');
INSERT INTO `t_dept_copy` VALUES ('402881ea53a0ca830153a125c3fe0006', null, '库房', '5', null, '4028824a3734af2f013734b0fcc80001', '1', null, '0', '4028815652d9c52a0152d9c52a7f0000', '4101');
INSERT INTO `t_dept_copy` VALUES ('4028824a3734af2f013734b0fcc80001', null, '郑州市', '29', null, 'null', '1', null, '0', '4028815652d9c52a0152d9c52a7f0000', '4101');
INSERT INTO `t_dept_copy` VALUES ('402883e95ff251d1015ff2d123140001', null, '查看', '12', null, 'null', '1', null, '0', '4028815652d9c52a0152d9c52a7f0000', '4101');
INSERT INTO `t_dept_copy` VALUES ('402883e95ff251d1015ff2d24b800002', null, '晚间', '13', null, '402883e95ff251d1015ff2d123140001', '1', null, '0', '402883e95fd8bb98015fd8bcfc9b0000', '410102001');
INSERT INTO `t_dept_copy` VALUES ('402883f85fa01a0c015fa01db7f30000', null, '电采部', '2', null, 'null', '1', null, '0', '4028815652d9c52a0152d9c52a7f0008', '41');
SET FOREIGN_KEY_CHECKS=1;
