/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 27/02/2023 13:59:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bookRESTful
-- ----------------------------
DROP TABLE IF EXISTS `bookRESTful`;
CREATE TABLE `bookRESTful` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) DEFAULT NULL,
                               `price` float NOT NULL,
                               `inventory` int NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of bookRESTful
-- ----------------------------
BEGIN;
INSERT INTO `bookRESTful` (`id`, `name`, `price`, `inventory`) VALUES (1, 'Stone Maidens', 8.99, 5);
INSERT INTO `bookRESTful` (`id`, `name`, `price`, `inventory`) VALUES (2, 'Young Forever: The Secrets to Living Your Longest, Healthiest Life', 19.98, 10);
INSERT INTO `bookRESTful` (`id`, `name`, `price`, `inventory`) VALUES (3, 'Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones', 25, 1);
INSERT INTO `bookRESTful` (`id`, `name`, `price`, `inventory`) VALUES (4, 'Spare', 3.1, 20);
INSERT INTO `bookRESTful` (`id`, `name`, `price`, `inventory`) VALUES (5, 'Three body', 99, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
