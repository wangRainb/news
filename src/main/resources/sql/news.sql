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

 Date: 06/09/2023 08:59:13
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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '时政');
INSERT INTO `category` VALUES (2, '体育');
INSERT INTO `category` VALUES (4, '军事');
INSERT INTO `category` VALUES (12, '娱乐');
INSERT INTO `category` VALUES (13, '财经');
INSERT INTO `category` VALUES (14, '科技');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `nid` int(11) NOT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (91, 141, 9, 'Manuel Silva', '2023-09-05 15:40:11');
INSERT INTO `comment` VALUES (92, 141, 3, 'Manuel Silva', '2023-09-05 15:40:43');
INSERT INTO `comment` VALUES (93, 141, 3, 'Manuel SilvaManuel Silva', '2023-09-05 15:40:48');
INSERT INTO `comment` VALUES (94, 143, 1, 'Kong Sze Yu', '2023-09-05 15:41:43');
INSERT INTO `comment` VALUES (95, 143, 1, 'Kong Sze Yu', '2023-09-05 15:42:00');
INSERT INTO `comment` VALUES (96, 143, 4, 'Kong Sze Yu', '2023-09-05 15:46:46');
INSERT INTO `comment` VALUES (97, 143, 4, 'Kong Sze Yu', '2023-09-05 15:46:46');
INSERT INTO `comment` VALUES (98, 146, 3, '支持', '2023-09-05 16:40:43');

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
  `views` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '篮球世界杯｜拉脱维亚队爆冷击败卫冕冠军西班牙队', '　　<p>新华社雅加达9月1日电（记者何磊静、汪奥娜、陆晓平）在1日举行的2023年篮球世界杯第二阶段小组赛的一场比赛中，卫冕冠军西班牙队以69:74不敌首次登上世界杯舞台的拉脱维亚队。</p>\r\n\r\n　　<p>赛前，拉脱维亚队主教练班奇展望这场比赛时说：“我们无惧任何对手的挑战。”果不其然，一开场拉脱维亚就展现出强悍的身体对抗，逼迫西班牙队频繁出现失误，拉脱维亚队以17:16领先结束首节。</p>\r\n\r\n　　<p>依靠埃尔南戈麦斯内线进攻频频得手，慢热的西班牙队开始逐渐“苏醒”，内突外投多次打出小高潮，最终以58:47领先进入末节决战。</p>\r\n\r\n　　<p>本场比赛，拉脱维亚队和西班牙队双方对抗异常激烈，合计犯规达50次。大批拉脱维亚球迷来现场助威，无疑给他们主队添了些额外动力，拉脱维亚队在末节关键时刻仍然拼劲十足，依靠防守反击连续命中三分，最终稳稳控住节奏拿下比赛。</p>\r\n\r\n　　<p>“最后一节我们让他们打得太轻松了，尤其是挡拆换防没做好，我们需要打得更有效率。但还是要恭喜拉脱维亚队，他们打得更好，更值得这场胜利。”西班牙队主教练斯卡廖洛说。</p>\r\n\r\n　　<p>拉脱维亚队的贝尔坦斯拿到全场最高的16分。“这场比赛从一开始就对抗十足，但很高兴我们坚持下来了。”</p>\r\n\r\n　　<p>赢下这场比赛后，拉脱维亚队重新回到八强门票争夺者的行列，在3日进行的小组赛最后一轮中，拉脱维亚队将面对巴西队，西班牙队将对阵本届赛事夺冠热门之一的加拿大队。</p>', 2, '', '2023-09-02 15:28:06', '贾紫来 ', 5);
INSERT INTO `news` VALUES (2, '乌媒爆：乌防长最早可能于下周撤换，并被调任乌驻英国大使', ' <p>据美国有线电视新闻网（CNN）、“今日俄罗斯”（RT）等媒体报道，乌克兰总统泽连斯基8月31日夸赞乌克兰生产的新型远程武器，称“成功命中700公里外目标”。RT对此表示，这是乌克兰方面威胁对俄实施远程打击。 报道称，泽连斯基在社交平台Telegram上发文称：“我们成功使用了远程武器：命中700公里外目标！”泽连斯基指的是乌克兰战略工业部的一次演习，但他没有具体说明所使用的武器和击中目标具体是什么。<p>\r\n\r\n    <p>此后不久，乌国家安全和国防委员会秘书奥列克西·达尼洛夫在社交平台X上（原推特）发布了一段20秒的视频，该视频显示一个看似是导弹的东西被发射到夜空中。达尼洛夫称：“乌克兰总统的导弹计划正在实施。测试是成功的，执行是有效的，”他补充说：“塞瓦斯托波尔在等待，堪察加在等待，喀琅施塔得在等待……”</p>\r\n\r\n    <p>RT报道称，这听起来像是对俄罗斯海军基地甚至是对远东半岛的威胁。</p>\r\n\r\n    <p>报道提到，“700公里”可能指的是俄罗斯西北部城市普斯科夫，该城市距离乌克兰边境正好700公里。几架军用运输机本周三（30日）早上在普斯科夫机场受损，地方官员称这是十多架无人机的袭击，但双方都没有正式确认在袭击中使用了哪种无人机，以及它们来自哪里。</p>\r\n\r\n    <p>对于乌方近来对俄本土的袭击，RT称仅对俄造成轻微财产损失，克宫称这些袭击是“绝望之举”，是想掩盖基辅在战场上的失败。据美国官员的说法，这些袭击旨在“鼓舞乌克兰民众和军队的士气”，并表明基辅“有能力反击”。 </p>\r\n\r\n', 4, '2f40ad93-1e93-496c-846a-21510bfd814d.png', '2023-09-02 16:56:36', '王金志', 5);
INSERT INTO `news` VALUES (3, '八省份受灾地区学校如期开学 学生安置、教师返岗、教材“课前到人”等落实到位', '<p>记者从教育部获悉：本次汛情共造成北京、天津、河北、吉林、黑龙江、福建、河南、重庆8省（市）3286所学校不同程度受损。教育部建立学校灾后工作台账，指导受灾地区加快校舍场地安全鉴定、修复加固和设备维修、卫生监测等工作。截至9月1日，多数受灾地区学校已完成修复、原址开学，暂不具备开学条件的通过转移至其他学校等方式，实现所有受灾地区学校如期开学。</p>\r\n\r\n　　<p>北京做好高质量开学准备，精心组织开学典礼、开学第一课等教育活动，科学谋划新生入学教育和安全教育；河北开展受灾家庭经济困难学生救助，全省高校发放救助款787万元，中小学发放50.9万元，拟纳入开学国家资助政策范围的中小学生4.1万人；黑龙江明确受灾地区学校重建工作完成时限，哈尔滨等部分地区普通高中8月15日起新生陆续报到和军训，并于22日开学，小学初中9月1日开学……连日来，相关地方教育部门深入受灾地区学校逐一研究、精准施策，完成校舍清淤消杀、校园修缮恢复，学生安置、教师返岗、教材“课前到人”等工作落实到位。</p>\r\n\r\n　　<p>当前，正值秋季开学返校高峰。教育部相关负责人表示，将进一步督促受灾地区学校健全校园安全常规检查机制，持续深入排查整改各类安全隐患，有效防范原发、诱发性自然灾害和次生安全风险，真正做到正常、安全开学。开学后，扎实做好安置慰问、补偿救助等工作，全力保障秋季学期教育教学平稳有序。（记者 赵婀娜、黄超）</p>', 1, '', '2023-09-02 16:57:51', '吴京泽', 29);
INSERT INTO `news` VALUES (4, '酷玩乐队遭前经纪人起诉 索赔金额超1000万英镑', '<p>新浪娱乐讯 据媒体报道，酷玩乐队遭前经纪人Dave Holmes起诉，Holmes向英国高等法院提起诉讼，称酷玩违背了有关第十张和第十一张专辑的承诺合同，经纪人本应获得佣金。</p>\r\n\r\n　　<p>酷玩乐队为尚未发行的第十张专辑预付了 3500 万英镑，为第十一张和第十二张专辑预付了 3000 万英镑，Holmes表示他应该就此获得佣金。根据之前的合同，他获得了 8% 到 13% 的佣金，其中涵盖了乐队的第八张《Everyday Life》和和第九张专辑《Music of the Spheres》。</p>\r\n\r\n　　<p>Holmes在诉讼中表示，除了管理专辑准备和录制的后勤工作，从准备预算和安排在伦敦、阿斯彭和牙买加的录音会议，到与音乐制作人Max Martin联络和授权样本，他还参与了乐队的巡演工作。他声称，乐队不仅拒绝续约，还想将他从经纪人降级为巡演主管。</p>\r\n\r\n　　<p>他要求英国法院申明第十张和第十一张专辑的合同有效，并命令乐队支付未付的佣金。他的律师评估索赔金额超过 1000 万英镑。</p>\r\n\r\n　　<p>Holmes的文件还显示，在他提起诉讼之前的法律信件中，酷玩乐队威胁要在任何辩护的同时提出“重大反诉”。</p>\r\n\r\n　　<p>Holmes的发言人Phil Sherrell 告诉记者：“Dave Holmes成功经营Coldplay超过 22 年，带领他们成为音乐史上最成功的乐队之一。现在，正如法律案件所示，酷玩乐队拒绝履行Dave的管理合同并支付他所欠的费用。”</p>', 12, 'a69e53fc-9906-4297-b6b3-f7e09b97d9fa.png', '2023-09-02 17:24:36', '张梅', 48);
INSERT INTO `news` VALUES (8, '一箭三星！我国成功发射遥感三十九号卫星', '<p>　　新华社西昌8月31日电（李国利、胡煦劼）8月31日15时36分，我国在西昌卫星发射中心使用长征二号丁运载火箭，采取“一箭三星”方式，成功将遥感三十九号卫星发射升空，卫星顺利进入预定轨道，发射任务获得圆满成功。</p>\r\n<p>　　这次任务是长征系列运载火箭第485次飞行。</p>', 14, '4a9dce3a-21f9-441c-9c53-f6acbb8de767.png', '2023-09-03 15:54:29', '陈听雨', 1);
INSERT INTO `news` VALUES (9, '三部门联合部署开展防止返贫就业攻坚行动', '<p>　　近日，人力资源社会保障部、国家发展改革委、农业农村部印发通知，部署开展防止返贫就业攻坚行动，进一步做好脱贫人口就业帮扶工作，全力稳定脱贫人口务工规模和务工收入，坚决守住不发生因失业导致规模性返贫的底线。</p>\r\n<p></p>\r\n<p>　　行动明确了七个方面重点任务。一是拓展完善劳务协作对接机制。组建区域间劳务协作联盟，培育推介一批劳务品牌，强化“季节性”有组织劳务输出。二是大力扶持就业帮扶车间健康发展。按照“升级一批”“扶持一批”“新建一批”“调整一批”的原则，分类扶持就业帮扶车间发展，探索推广“企业+”“联合体+”等模式。三是全力挖潜以工代赈就业带动能力。协同推进项目立项、招工组织、用工保障和劳务报酬发放，将脱贫人口就近务工需求作为安排以工代赈项目的重要依据，优先组织脱贫人口参与项目建设，切实保障劳动者合法权益。四是切实发挥乡村公益性岗位兜底作用。科学设定岗位数量和类别，完善相关政策管理规定，将乡村公益性岗位资金支出使用情况作为乡村振兴领域不正之风和腐败问题专项整治的重点内容。五是持续加大脱贫人口就业保障力度。将脱贫人口纳入本地稳岗政策扶持范围，健全就业跟踪服务机制，针对性组织开展“订单、定向、定岗”式培训，开辟劳动仲裁“绿色通道”。六是积极帮扶脱贫家庭青年群体就业。继续实施雨露计划，深入开展“雨露计划+”就业促进行动，优先为脱贫家庭未就业高校毕业生以及长期失业青年提供职业指导、职业介绍等服务。七是坚持抓好重点地区倾斜支持。加大对国家乡村振兴重点县、易地搬迁安置区政策、资金、项目等支持力度，组织就业服务示范活动，落实易地扶贫搬迁安置区按比例安排就业机制。（记者 李欣 唐蕾）</p>\r\n', 1, NULL, '2023-09-04 17:08:38', '王雪', 189);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '欢迎使用X新闻网', '欢迎使用X新闻网', '2023-09-04 09:49:02');

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
INSERT INTO `user` VALUES (123, 'Yuan Jialun', '202cb962ac59075b964b07152d234b70', 0, '312-478-2835', 'jialunyua510@icloud.com', NULL, 1, 30, '2023-09-03 11:06:04');
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
INSERT INTO `user` VALUES (165, '海阔天空', '202cb962ac59075b964b07152d234b70', 0, NULL, NULL, NULL, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
