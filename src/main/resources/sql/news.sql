/*
 Navicat Premium Data Transfer

 Source Server         : why
 Source Server Type    : MySQL
 Source Server Version : 50515 (5.5.15)
 Source Host           : localhost:3306
 Source Schema         : news

 Target Server Type    : MySQL
 Target Server Version : 50515 (5.5.15)
 File Encoding         : 65001

 Date: 02/09/2023 10:52:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '时政');
INSERT INTO `category` VALUES (2, '体育');
INSERT INTO `category` VALUES (3, '文化');
INSERT INTO `category` VALUES (4, '军事');
INSERT INTO `category` VALUES (5, '娱乐');
INSERT INTO `category` VALUES (6, '游戏');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `nid` int(11) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_uid`(`uid`) USING BTREE,
  INDEX `fk_nid`(`nid`) USING BTREE,
  CONSTRAINT `fk_nid` FOREIGN KEY (`nid`) REFERENCES `news` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cid` int(11) NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_cid`(`cid`) USING BTREE,
  CONSTRAINT `fk_cid` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` tinyint(4) NULL DEFAULT 0 COMMENT '1\"管理员\"，0\"普通用户\"',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `islocked` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0\"正常\"，1“封号”',
  `lockdays` int(11) NULL DEFAULT NULL,
  `lockdate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 166 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '202cb962ac59075b964b07152d234b70', 1, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (115, 'Duan Jialun', '202cb962ac59075b964b07152d234b70', 0, '52-071-2315', 'duajialun@gmail.com', NULL, 1, 30, '2023-09-02 09:53:42');
INSERT INTO `user` VALUES (116, 'Su Shihan', '202cb962ac59075b964b07152d234b70', 0, '90-2782-4243', 'ssu4@icloud.com', NULL, 1, 30, '2023-09-02 10:44:42');
INSERT INTO `user` VALUES (118, 'Luo Xiaoming', '202cb962ac59075b964b07152d234b70', 0, '176-3566-8964', 'luxia@outlook.com', NULL, 1, 1, '2023-09-02 09:53:55');
INSERT INTO `user` VALUES (121, 'Takeda Ryota', '202cb962ac59075b964b07152d234b70', 0, '5885 473536', 'ryotat8@icloud.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (122, 'Fu Ziyi', '202cb962ac59075b964b07152d234b70', 0, '330-179-9493', 'fuzi@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (123, 'Yuan Jialun', '202cb962ac59075b964b07152d234b70', 0, '312-478-2835', 'jialunyua510@icloud.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (124, 'Rachel Scott', '202cb962ac59075b964b07152d234b70', 0, '66-710-3683', 'scottrach1943@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (125, 'Wu Lu', '202cb962ac59075b964b07152d234b70', 0, '90-9067-3455', 'luwu@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (126, 'Ono Ayato', '202cb962ac59075b964b07152d234b70', 0, '170-1293-3740', 'onoa@hotmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (127, 'Takeuchi Sakura', '202cb962ac59075b964b07152d234b70', 0, '10-287-4761', 'tsakur8@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (128, 'Kong Zhennan', '202cb962ac59075b964b07152d234b70', 0, '5933 096386', 'kongzhennan@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (129, 'Cheng Hui Mei', '202cb962ac59075b964b07152d234b70', 0, '186-5848-2863', 'cheng1961@mail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (130, 'Hasegawa Yuto', '202cb962ac59075b964b07152d234b70', 0, '(151) 371 9205', 'yhase@mail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (131, 'Nakamura Yuna', '202cb962ac59075b964b07152d234b70', 0, '755-8666-3544', 'yunanakamura84@hotmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (132, 'Tang Wing Fat', '202cb962ac59075b964b07152d234b70', 0, '90-3560-3126', 'tang1982@icloud.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (133, 'Xia Jialun', '202cb962ac59075b964b07152d234b70', 0, '3-4909-7102', 'jialun7@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (134, 'Fung Ho Yin', '202cb962ac59075b964b07152d234b70', 0, '614-892-9000', 'funhy@hotmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (135, 'Meng Ziyi', '202cb962ac59075b964b07152d234b70', 0, '213-586-4326', 'menziyi6@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (136, 'Li Zitao', '202cb962ac59075b964b07152d234b70', 0, '11-327-9003', 'lizitao64@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (137, 'Hsuan Tin Wing', '202cb962ac59075b964b07152d234b70', 0, '20-692-4347', 'hsutw14@icloud.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (138, 'Glenn Lewis', '202cb962ac59075b964b07152d234b70', 0, '330-486-9990', 'glenlew1@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (139, 'Yue Chi Ming', '202cb962ac59075b964b07152d234b70', 0, '312-537-1307', 'yue1972@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (140, 'Chen Zhiyuan', '202cb962ac59075b964b07152d234b70', 0, '213-181-5584', 'chen830@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (141, 'Manuel Silva', '202cb962ac59075b964b07152d234b70', 0, '312-108-1765', 'silvm@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (142, 'Hu Zhennan', '202cb962ac59075b964b07152d234b70', 0, '(1865) 30 4261', 'zhennanhu@icloud.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (143, 'Kong Sze Yu', '202cb962ac59075b964b07152d234b70', 0, '28-0674-1990', 'kosy@icloud.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (144, 'Jeff Rice', '202cb962ac59075b964b07152d234b70', 0, '755-0529-1279', 'rice07@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (145, 'Mok Ka Fai', '202cb962ac59075b964b07152d234b70', 0, '330-879-6612', 'kfmo930@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (146, 'Johnny Garza', '202cb962ac59075b964b07152d234b70', 0, '312-641-7371', 'jgarza@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (147, 'Yip Tak Wah', '202cb962ac59075b964b07152d234b70', 0, '90-6419-3973', 'takwahyip@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (148, 'Glenn Wright', '202cb962ac59075b964b07152d234b70', 0, '330-149-7881', 'wrigglenn@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (149, 'Ann Watson', '202cb962ac59075b964b07152d234b70', 0, '5113 795031', 'watsonann3@mail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (150, 'Jia Zitao', '202cb962ac59075b964b07152d234b70', 0, '28-159-5500', 'jiaz@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (151, 'Ichikawa Ren', '202cb962ac59075b964b07152d234b70', 0, '169-8668-1763', 'renic111@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (152, 'Xiang Zhiyuan', '202cb962ac59075b964b07152d234b70', 0, '70-3336-7911', 'zhxiang1973@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (153, 'Jacqueline Payne', '202cb962ac59075b964b07152d234b70', 0, '755-848-5898', 'pjacqu2017@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (154, 'Fujii Sara', '202cb962ac59075b964b07152d234b70', 0, '66-995-5913', 'fujiisar@hotmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (155, 'Ikeda Mai', '202cb962ac59075b964b07152d234b70', 0, '213-697-2568', 'ikeda116@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (156, 'Yeung Fu Shing', '202cb962ac59075b964b07152d234b70', 0, '158-1462-9976', 'yeungfushing@icloud.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (157, 'Liao Ka Man', '202cb962ac59075b964b07152d234b70', 0, '838-266-1445', 'liakm2@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (158, 'Tin Ka Man', '202cb962ac59075b964b07152d234b70', 0, '70-8644-9660', 'tin712@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (159, 'Sheh Suk Yee', '202cb962ac59075b964b07152d234b70', 0, '7740 403243', 'sysheh@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (160, 'Cynthia Guzman', '202cb962ac59075b964b07152d234b70', 0, '74-373-3350', 'guzmancynthia@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (161, 'Fong Wai Man', '202cb962ac59075b964b07152d234b70', 0, '52-031-6633', 'waimanf1224@outlook.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (162, 'Phillip Porter', '202cb962ac59075b964b07152d234b70', 0, '70-0060-6317', 'porter615@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (163, 'Koon Lik Sun', '202cb962ac59075b964b07152d234b70', 0, '10-029-7485', 'liksun818@gmail.com', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES (164, 'Kwan On Na', '202cb962ac59075b964b07152d234b70', 0, '760-790-5654', 'kwanonna@outlook.com', NULL, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
