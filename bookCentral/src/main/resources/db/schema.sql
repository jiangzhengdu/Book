

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(100) DEFAULT NULL,
                        `password` varchar(100) DEFAULT NULL,
                        `balance` float DEFAULT '1000',
                        `role`int DEFAULT 1,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `balance`,`role`) VALUES (1, 'du', '$2a$10$Pmc7guCxs1.iSxC2uIG4.OIE/85iJo0uexf54.4WxUqxTZbgBEqc6', 1000, 0);
INSERT INTO `user` (`id`, `username`, `password`, `balance`,`role`) VALUES (2, 'username', '$2a$10$PBA2VfUr9cHTsHP6fWgkdet/dWIMdfo2yM.fsRKg6jPW3VdxZVdgS', 1000,1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
