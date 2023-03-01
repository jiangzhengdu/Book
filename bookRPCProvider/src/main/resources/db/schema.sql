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

 Date: 01/03/2023 16:26:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bookRESTful
-- ----------------------------
DROP TABLE IF EXISTS `bookRPC`;
CREATE TABLE `bookRPC` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) DEFAULT NULL,
                               `author` varchar(255) DEFAULT NULL,
                               `price` float NOT NULL,
                               `inventory` int NOT NULL,
                               `srouce` int NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of bookRESTful
-- ----------------------------
BEGIN;
INSERT INTO `bookRPC` (`id`, `name`, `author`, `price`, `inventory`, `srouce`) VALUES (1, 'Stone Maidens', 'Lloyd Devereux Richards ', 8.99, 5, 1);
INSERT INTO `bookRPC` (`id`, `name`, `author`, `price`, `inventory`, `srouce`) VALUES (2, 'Young Forever: The Secrets to Living Your Longest, Healthiest Life', 'Dr. Mark Hyman MD', 19.98, 10, 1);
INSERT INTO `bookRPC` (`id`, `name`, `author`, `price`, `inventory`, `srouce`) VALUES (3, 'Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones', 'Lloyd Devereux Richards ', 25, 1, 1);
INSERT INTO `bookRPC` (`id`, `name`, `author`, `price`, `inventory`, `srouce`) VALUES (4, 'Spare', 'Prince Harry The Duke of Sussex and Random House Audio', 3.1, 20, 1);
INSERT INTO `bookRPC` (`id`, `name`, `author`, `price`, `inventory`, `srouce`) VALUES (5, 'Three body', 'Liu CIXIn', 99, 2, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
