/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50616
 Source Host           : localhost
 Source Database       : bean

 Target Server Type    : MySQL
 Target Server Version : 50616
 File Encoding         : utf-8

 Date: 08/18/2014 20:23:10 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_login_name` varchar(255) NOT NULL,
  `user_login_pwd` varchar(255) NOT NULL,
  `user_dname` varchar(255) NOT NULL,
  `user_mobile` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL,
  `item_picture1` varchar(255) NOT NULL,
  `item_picture2` varchar(255) DEFAULT NULL,
  `item_picture3` varchar(255) DEFAULT NULL,
  `item_picture4` varchar(255) DEFAULT NULL,
  `item_picture5` varchar(255) DEFAULT NULL,
  `item_description` varchar(255) NOT NULL,
  `item_used` int(11) NOT NULL,
  `item_seller_id` int(11) NOT NULL,
  `item_max_price` double NOT NULL,
  `item_min_price` double NOT NULL,
  `item_onshelf_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `item_auc_length` int(11) NOT NULL,
  `item_add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `t_rel_item_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_rel_item_user`;
CREATE TABLE `t_rel_item_user` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`relation_id`),
  FOREIGN KEY (`item_id`) REFERENCES `t_item`(`item_id`),
  FOREIGN KEY (`user_id`) REFERENCES `t_user`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('10000', 'sysman', 'sysman', 'SYSMAN', '13800000000', 'abc@def.com'), ('10001', 'buyer', 'buyer', 'buyer', '13800000000', 'abc@def.com'), ('10002', 'seller', 'seller', 'seller', '13800000000', 'abc@def.com');
COMMIT;

-- ----------------------------
--  Records of `t_item`
-- ----------------------------
BEGIN;
INSERT INTO `t_item` VALUES ('10000', '测试商品', 'http://m.52z.com/upload/201406/18/1403070323.png', null, null, null, null, '测试商品描述', '0', '10002', '100', '80', '2014-08-20 19:48:49', '3600', '2014-08-20 19:48:24'), ('10001', '测试商品2', '/user/local/pic1.png', null, null, null, null, '测试商品描述', '0', '10002', '100', '80', '2014-08-20 19:48:49', '3600', '2014-08-20 19:48:24');
COMMIT;

-- ----------------------------
--  Records of `t_category`
-- ----------------------------
BEGIN;
INSERT INTO `t_category` VALUES ('10000', '文具'), ('10001', '电脑配件'), ('10002', '书籍');
COMMIT;

-- ----------------------------
--  Records of `t_rel_item_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_rel_item_user` VALUES ('10000', '10000', '10002'), ('10001', '10001', '10000');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
