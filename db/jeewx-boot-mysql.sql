/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50037
Source Host           : localhost:3306
Source Database       : jeewx-boot-201907

Target Server Type    : MYSQL
Target Server Version : 50037
File Encoding         : 65001

Date: 2019-07-13 09:49:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_ad
-- ----------------------------
DROP TABLE IF EXISTS `cms_ad`;
CREATE TABLE `cms_ad` (
  `id` varchar(36) NOT NULL,
  `create_name` varchar(50) default NULL COMMENT 'createName',
  `create_by` varchar(50) default NULL COMMENT 'createBy',
  `create_date` datetime default NULL COMMENT 'createDate',
  `title` varchar(20) default NULL COMMENT '标题',
  `image_href` varchar(255) default NULL COMMENT '图片地址',
  `is_show` int(1) default NULL COMMENT '是否显示，0不显示，1显示',
  `resume` varchar(255) default NULL COMMENT '简述',
  `link` varchar(500) default NULL COMMENT '链接',
  `sort` int(2) default NULL COMMENT '顺序',
  `html_link` varchar(255) default NULL COMMENT 'H5网站网址',
  `main_id` varchar(255) default NULL COMMENT '站点ID',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Records of cms_ad
-- ----------------------------
INSERT INTO `cms_ad` VALUES ('4028810c6b92fa6d016b9300cba90001', null, 'admin', '2019-06-26 16:57:11', '111', 'banner (1)1561540335790.jpg', '1', '', '111', null, '', '4028810c6b92fa6d016b92fa6d610000');

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) default NULL COMMENT '标题',
  `image_href` varchar(255) default NULL COMMENT '图片地址',
  `summary` varchar(1000) default NULL COMMENT '摘要',
  `content_html` longtext,
  `content` longtext COMMENT '内容',
  `column_id` varchar(36) default NULL COMMENT '栏目id',
  `create_name` varchar(255) default NULL COMMENT '创建人',
  `create_by` varchar(255) default NULL COMMENT '创建人id',
  `create_date` datetime default NULL COMMENT '创建日期',
  `publish` varchar(32) default NULL COMMENT '是否发布',
  `publish_date` datetime default NULL COMMENT '发布时间',
  `author` varchar(32) default NULL COMMENT '作者',
  `label` varchar(200) default NULL COMMENT '标签',
  `serial_number` int(10) default NULL COMMENT '序号',
  `code` varchar(50) default NULL COMMENT '文章编码',
  `is_link` int(1) default NULL COMMENT '是否链接，0没有链接，1链接',
  `link` varchar(300) default NULL COMMENT '链接地址',
  `is_show` int(1) default NULL COMMENT '是否在明细页面显示 0不显示，1显示',
  `main_id` varchar(255) default NULL COMMENT '站点ID',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('4028810c6b92fa6d016b93020bd10002', '幸运九宫格', 'LOGO-mini1561542160757.png', 'dddd', null, '<p>按时发达的ddddd</p>', 'A01', null, 'admin', '2019-06-26 16:58:33', 'Y', '2019-06-26 17:42:49', null, null, null, '20190626165833297963', '0', '', '0', '4028810c6b92fa6d016b92fa6d610000');

-- ----------------------------
-- Table structure for cms_menu
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) default NULL,
  `create_date` datetime default NULL,
  `create_name` varchar(255) default NULL,
  `image_href` varchar(255) default NULL COMMENT '图片地址',
  `image_name` varchar(255) default NULL COMMENT '图片名称',
  `name` varchar(20) default NULL COMMENT '栏目名称',
  `type` varchar(20) default NULL COMMENT '类型',
  `parent_code` varchar(36) default NULL COMMENT '父节点',
  `href` varchar(100) default NULL COMMENT '地址',
  `template_code` varchar(100) default NULL COMMENT '模版样式',
  `serial_number` int(10) default NULL COMMENT '序号',
  `content` varchar(1000) default NULL COMMENT '描述',
  `main_id` varchar(255) default NULL COMMENT '站点ID',
  `html_link` varchar(255) default NULL COMMENT 'H5链接地址',
  `is_show` int(1) default NULL COMMENT '是否显示0显示，1显示',
  `column_color` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表';

-- ----------------------------
-- Records of cms_menu
-- ----------------------------
INSERT INTO `cms_menu` VALUES ('A01', 'admin', '2019-06-26 16:58:00', null, '20180607175028Fn1Lq7zw1561541955598.png', null, '小说', 'coulmn', '', '', '', '0', '', '4028810c6b92fa6d016b92fa6d610000', '', '1', '#964747');

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` varchar(36) NOT NULL,
  `update_name` varchar(50) default NULL,
  `update_date` datetime default NULL,
  `create_date` datetime default NULL,
  `create_name` varchar(50) default NULL,
  `company_tel` varchar(50) default NULL COMMENT '公司电话',
  `site_logo` varchar(200) default NULL COMMENT '站点Logo',
  `site_name` varchar(100) default NULL COMMENT '站点名称',
  `site_template_style` varchar(50) default NULL COMMENT '站点模板',
  `record_information` varchar(5000) default NULL COMMENT '备案信息',
  `longitude` varchar(50) default NULL COMMENT '经度',
  `latitude` varchar(50) default NULL COMMENT '纬度',
  `jwid` varchar(64) default NULL COMMENT '微信公众号',
  `hdurl` varchar(255) default NULL COMMENT '长链接',
  `short_url` varchar(255) default NULL COMMENT '短链接',
  `project_code` varchar(32) default NULL COMMENT '项目编码',
  `share_friend_title` varchar(50) default NULL COMMENT '分享给朋友标题',
  `share_friend_desc` varchar(255) default NULL COMMENT '分享给朋友描述',
  `share_friend_circle` varchar(50) default NULL COMMENT '分享到朋友圈标题',
  `appid` varchar(100) default NULL COMMENT 'appid',
  `site_background_img` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点';

-- ----------------------------
-- Records of cms_site
-- ----------------------------
INSERT INTO `cms_site` VALUES ('4028810c6b92ed2b016b92ed2b760000', null, null, '2019-06-26 16:35:45', null, '', '', '哈喽', 'wxhome', null, '', '', null, null, null, 'cms', '', '', '', '', null);
INSERT INTO `cms_site` VALUES ('4028810c6b92fa6d016b92fa6d610000', null, '2019-06-28 18:54:13', '2019-06-26 16:50:13', 'admin', '', 'sdk1561719251197.jpg', '测试', 'default', null, '', '', 'gh_20419b74f848', null, null, 'cms', '', '', '', '', 'gh_f28e66390fc9_344 (shop)1561719247914.jpg');
INSERT INTO `cms_site` VALUES ('ff808081661e74bb01661f12de5e03d6', null, '2018-10-10 16:28:18', '2018-09-28 15:27:02', 'admin', '', 'logo1539160087728.png', '捷微官网', 'wxhome', '<p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">中国·北京·朝阳区科荟前街1号院奥林佳泰大厦9层929</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">公司：北京囯炬信息技术有限公司</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">邮编：100190</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">电话：010-64808099</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">邮箱：jeecg@sina.com</span></p><p><br/></p>', '116.381319', '40.015457', 'gh_f268aa85d1c7', 'http://www.h5huodong.com/cms/index.do?mainId=ff808081661e74bb01661f12de5e03d6', 'https://w.url.cn/s/AoUtL1H', 'cms', null, null, null, null, null);

-- ----------------------------
-- Table structure for cms_style
-- ----------------------------
DROP TABLE IF EXISTS `cms_style`;
CREATE TABLE `cms_style` (
  `id` varchar(36) NOT NULL,
  `create_date` datetime default NULL,
  `create_name` varchar(50) default NULL,
  `update_date` datetime default NULL,
  `update_name` varchar(50) default NULL,
  `template_name` varchar(100) default NULL COMMENT '模板名称',
  `review_img_url` varchar(100) default NULL COMMENT '预览图',
  `template_url` varchar(200) default NULL COMMENT '模板地址',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点模板';

-- ----------------------------
-- Records of cms_style
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_account
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_account`;
CREATE TABLE `jw_system_account` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `user_id` varchar(50) default NULL COMMENT '用户ID',
  `amount` decimal(11,2) default '0.00' COMMENT '余额',
  `status` varchar(255) default NULL COMMENT '状态',
  `remark` varchar(255) default NULL COMMENT '备注',
  `create_by` varchar(255) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  KEY `idx_userid` USING BTREE (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统账户表';

-- ----------------------------
-- Records of jw_system_account
-- ----------------------------
INSERT INTO `jw_system_account` VALUES ('003387126f2011e8beb1525400077487', 'admin', '0.00', '1', '初始创建', 'admin', '2018-06-13 23:39:46');

-- ----------------------------
-- Table structure for jw_system_act
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_act`;
CREATE TABLE `jw_system_act` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `act_id` varchar(100) NOT NULL COMMENT '活动ID',
  `act_name` varchar(500) NOT NULL COMMENT '活动名称',
  `begin_time` datetime default NULL COMMENT '活动开始时间',
  `end_time` datetime default NULL COMMENT '活动结束时间',
  `status` varchar(255) default NULL COMMENT '状态(启用/不启用)',
  `jwid` varchar(64) NOT NULL COMMENT '微信原始ID',
  `hdurl` varchar(2000) default NULL COMMENT '活动链接',
  `short_url` varchar(255) default NULL COMMENT '短链接',
  `organizer` varchar(255) default NULL COMMENT '组织单位',
  `join_num` int(11) default NULL COMMENT '参与数',
  `share_num` int(11) default NULL COMMENT '分享数',
  `create_name` varchar(50) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  `delete_flag` varchar(255) default NULL COMMENT '删除标识：0正常，1删除,2永久删除',
  `delete_time` datetime default NULL COMMENT '删除时间',
  `is_hot_act` varchar(2) default '1' COMMENT '是否为热门活动：''0''：否，''1''是',
  `project_code` varchar(30) default NULL COMMENT '活动类型',
  `table_name` varchar(255) default NULL COMMENT '表名',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_actcode` USING BTREE (`act_id`),
  KEY `idx_createtime` USING BTREE (`create_time`),
  KEY `idx_begintime` USING BTREE (`begin_time`),
  KEY `idx_endtime` USING BTREE (`end_time`),
  KEY `idx_projectcode` USING BTREE (`project_code`),
  KEY `idx_jwid` USING BTREE (`jwid`),
  KEY `idx_createname` USING BTREE (`create_name`),
  KEY `idx_ishotact` USING BTREE (`is_hot_act`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动项目表';

-- ----------------------------
-- Records of jw_system_act
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_act_analysis
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_act_analysis`;
CREATE TABLE `jw_system_act_analysis` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `act_id` varchar(100) NOT NULL COMMENT '活动ID',
  `project_code` varchar(100) default NULL COMMENT '活动类型CODE',
  `join_num` int(11) default NULL COMMENT '当日参与数',
  `share_num` int(11) default NULL COMMENT '当日分享数',
  `total_join_num` int(11) default NULL COMMENT '累计参与数',
  `total_share_num` int(11) default NULL COMMENT '累计分享数',
  `ref_date` date default NULL COMMENT '统计日期',
  `create_name` varchar(50) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  KEY `index_refdate` USING BTREE (`ref_date`),
  KEY `index_projectcode` USING BTREE (`project_code`),
  KEY `index_actid` USING BTREE (`act_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动统计分析表';

-- ----------------------------
-- Records of jw_system_act_analysis
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_act_txt
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_act_txt`;
CREATE TABLE `jw_system_act_txt` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(100) NOT NULL COMMENT '文本编码',
  `type` varchar(255) default NULL,
  `content` text NOT NULL COMMENT '文本内容',
  `discribe` varchar(100) default NULL COMMENT '文本描述',
  `act_code` varchar(32) NOT NULL COMMENT '所属活动',
  `jwid` varchar(64) default NULL COMMENT '微信原始ID',
  `creat_name` varchar(50) default NULL COMMENT '创建人',
  `creat_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_code_actcode` USING BTREE (`code`,`act_code`),
  KEY `idx_code_actcode` USING BTREE (`code`,`act_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统文本表';

-- ----------------------------
-- Records of jw_system_act_txt
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_auth
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_auth`;
CREATE TABLE `jw_system_auth` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `auth_id` varchar(32) collate utf8_bin NOT NULL default '' COMMENT '权限编码',
  `auth_name` varchar(100) collate utf8_bin default NULL COMMENT '权限名称',
  `is_logs` char(2) collate utf8_bin default NULL COMMENT '是否记录日志 0不记录 1记录',
  `auth_type` varchar(2) collate utf8_bin default NULL COMMENT '权限类型 0:菜单;1:功能',
  `auth_desc` varchar(120) collate utf8_bin default NULL COMMENT '权限说明',
  `auth_contr` varchar(256) collate utf8_bin default NULL COMMENT '权限控制',
  `parent_auth_id` varchar(32) collate utf8_bin default NULL COMMENT '上一级权限编码',
  `sort_no` int(11) default NULL COMMENT '排序',
  `biz_level` char(2) collate utf8_bin default NULL COMMENT '层级',
  `leaf_ind` char(2) collate utf8_bin default NULL COMMENT '是否叶子节点',
  `del_stat` char(2) collate utf8_bin NOT NULL default '0' COMMENT '删除标志 0未删除 1已删除',
  `icon_type` varchar(50) collate utf8_bin default NULL COMMENT '图标类型',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_authid` USING BTREE (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统权限菜单表';

-- ----------------------------
-- Records of jw_system_auth
-- ----------------------------
INSERT INTO `jw_system_auth` VALUES ('1', '21', '系统管理', null, '0', null, '', '', '900', '1', 'N', '0', 'fa-gear fa-lg');
INSERT INTO `jw_system_auth` VALUES ('2', '2101', '用户管理', null, '0', null, '/system/back/jwSystemUser/list.do', '21', '1', '2', 'Y', '0', null);
INSERT INTO `jw_system_auth` VALUES ('3', '210101', '新增用户', null, '1', '新增用户', '/system/back/jwSystemUser/doAdd.do', '2101', '1', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('4', '210102', '编辑用户', null, '1', '编辑用户', '/system/back/jwSystemUser/doEdit.do', '2101', '2', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('5', '2102', '角色管理', null, '0', null, '/system/back/jwSystemRole/list.do', '21', '2', '2', 'Y', '0', null);
INSERT INTO `jw_system_auth` VALUES ('6', '210201', '新增角色', '', '1', '新增角色', '/system/back/jwSystemRole/doAdd.do', '2102', '1', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('7', '210202', '编辑角色', '', '1', '编辑角色', '/system/back/jwSystemRole/doEdit.do', '2102', '2', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('8', '210203', '角色授权', null, '1', '角色授权', '/system/back/jwSystemRole/editRoleAuth.do', '2102', '3', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('9', '210204', '删除角色', '', '1', '删除角色', '/system/back/jwSystemRole/doDelete.do', '2102', '4', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('10', '2103', '菜单管理', null, '0', null, '/system/back/jwSystemAuth/list.do', '21', '3', '2', 'Y', '0', '');
INSERT INTO `jw_system_auth` VALUES ('11', '210301', '新增权限', null, '1', '新增权限', '/system/back/jwSystemAuth/doAdd.do', '2103', '1', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('12', '210302', '编辑权限', null, '1', '编辑权限', '/system/back/jwSystemAuth/doEdit.do', '2103', '2', '3', null, '0', null);
INSERT INTO `jw_system_auth` VALUES ('31', '2104', '项目管理', '', '0', '', '/system/back/jwSystemProject/list.do', '21', '4', '2', 'Y', '0', null);
INSERT INTO `jw_system_auth` VALUES ('118', '2106', '404配置', null, '0', '', '/system/back/jwSystemProjectErrorConfig/list.do', '21', '6', '2', 'Y', '0', null);
INSERT INTO `jw_system_auth` VALUES ('119', '2107', '首页配置', null, '0', '', '/system/back/jwSystemLogoTitle/list.do', '21', '7', '2', 'Y', '0', null);
INSERT INTO `jw_system_auth` VALUES ('218', '960106', '素材链接', null, '0', null, '/commonweixin/back/JwLinksucai/list.do', '21', '20', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533613596', 'aa735943eb4410368c0028dacd75e20f', '抽奖活动', null, '0', null, '', '', '9', '1', null, '0', 'fa-gift fa-lg');
INSERT INTO `jw_system_auth` VALUES ('1533720237', 'f5ab2658ec3c103696ca5352a33c439c', '基础配置', null, '0', null, '', '9d552546c3d011e8aa5852540003411a', '1', '2', null, '0', 'fa-weixin fa-lg');
INSERT INTO `jw_system_auth` VALUES ('1533720281', '0fff2fc4ec3d103696ca5352a33c439c', '公众号管理', null, '0', null, '/commonweixin/back/myJwWebJwid/list.do', 'f5ab2658ec3c103696ca5352a33c439c', '1', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720331', '2d8bf581ec3d103696ca5352a33c439c', '关注欢迎语', null, '0', null, '/weixin/back/weixinSubscribe/list.do', 'f5ab2658ec3c103696ca5352a33c439c', '2', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720388', '4f7ad75eec3d103696ca5352a33c439c', '未识别回复语', null, '0', null, '/weixin/back/weixinAutoresponseDefault/list.do', 'f5ab2658ec3c103696ca5352a33c439c', '3', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720411', '5d2c6911ec3d103696ca5352a33c439c', '关键字管理', null, '0', null, '/weixin/back/weixinAutoresponse/list.do', 'f5ab2658ec3c103696ca5352a33c439c', '4', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720444', '70cbf443ec3d103696ca5352a33c439c', '自定义菜单', null, '0', null, '/weixin/back/weixinMenu/list.do', 'f5ab2658ec3c103696ca5352a33c439c', '5', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720501', '92c06cd8ec3d103696ca5352a33c439c', '素材管理', null, '0', null, '', '9d552546c3d011e8aa5852540003411a', '2', '2', null, '0', 'fa-newspaper-o fa-lg');
INSERT INTO `jw_system_auth` VALUES ('1533720529', 'a37d189dec3d103696ca5352a33c439c', '文本素材', null, '0', null, '/weixin/back/weixinTexttemplate/list.do', '92c06cd8ec3d103696ca5352a33c439c', '1', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720551', 'b0b96e3dec3d103696ca5352a33c439c', '图文素材', null, '0', null, '/weixin/back/weixinNewstemplate/list.do', '92c06cd8ec3d103696ca5352a33c439c', '2', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720619', 'd931c662ec3d103696ca5352a33c439c', '用户管理', null, '0', null, '', '9d552546c3d011e8aa5852540003411a', '3', '2', null, '0', 'fa-users fa-lg');
INSERT INTO `jw_system_auth` VALUES ('1533720654', 'ee23607dec3d103696ca5352a33c439c', '关注用户信息', null, '0', null, '/weixin/back/weixinGzuser/list.do', 'd931c662ec3d103696ca5352a33c439c', '1', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1533720685', '0082475aec3e103696ca5352a33c439c', '消息管理', null, '0', null, '/weixin/back/weixinReceivetext/list.do', 'd931c662ec3d103696ca5352a33c439c', '4', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1534143366', 'f3bcd2bb9ec511e883d43ca0672e915d', '用户标签管理', null, '0', null, '/weixin/back/weixinTag/list.do', 'd931c662ec3d103696ca5352a33c439c', '3', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1538057887', '27a9519ec26011e8aa5852540003411a', '小程序官网', null, '0', null, '', '', '211', '1', null, '0', 'fa-star fa-lg');
INSERT INTO `jw_system_auth` VALUES ('1538057922', '3c6fe69cc26011e8aa5852540003411a', '站点管理', null, '0', null, '/cms/back/cmsSite/list.do', '27a9519ec26011e8aa5852540003411a', '1', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1538206989', '4f388b42c3bb11e8aa5852540003411a', '广告管理', null, '0', null, '/cms/back/cmsAd/list.do', '27a9519ec26011e8aa5852540003411a', '2', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1538207022', '6370158ac3bb11e8aa5852540003411a', '栏目管理', null, '0', null, '/cms/back/cmsMenu/list.do', '27a9519ec26011e8aa5852540003411a', '3', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1538207049', '736e3bbac3bb11e8aa5852540003411a', '文章管理', null, '0', null, '/cms/back/cmsArticle/list.do', '27a9519ec26011e8aa5852540003411a', '4', '2', null, '0', '');
INSERT INTO `jw_system_auth` VALUES ('1538216139', '9d552546c3d011e8aa5852540003411a', '公众号管理', null, '0', null, '', '', '1', '1', null, '0', 'fa-weixin fa-lg');

-- ----------------------------
-- Table structure for jw_system_auth_mutex
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_auth_mutex`;
CREATE TABLE `jw_system_auth_mutex` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `auth_id` varchar(32) NOT NULL COMMENT '权限编码',
  `mutex_auth_id` varchar(32) NOT NULL COMMENT '互斥权限编码',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限互斥表';

-- ----------------------------
-- Records of jw_system_auth_mutex
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_brush_ticket_list
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_brush_ticket_list`;
CREATE TABLE `jw_system_brush_ticket_list` (
  `data_id` varchar(50) NOT NULL,
  PRIMARY KEY  (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='刷票黑名单表';

-- ----------------------------
-- Records of jw_system_brush_ticket_list
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_logo_title
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_logo_title`;
CREATE TABLE `jw_system_logo_title` (
  `id` varchar(32) NOT NULL,
  `logo` varchar(100) default NULL COMMENT '系统的logo',
  `title1` varchar(100) default NULL COMMENT '系统名称',
  `title2` varchar(100) default NULL COMMENT '系统名称',
  `title3` varchar(100) default NULL COMMENT '系统名称',
  `login_page_head` text COMMENT '登录页的head',
  `login_page_footer` text COMMENT '登录页的footer',
  `huodong_bottom_copyright` text COMMENT '自定义广告位内容',
  `charging_desc` text COMMENT '收费说明'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统logo和title设置表';

-- ----------------------------
-- Records of jw_system_logo_title
-- ----------------------------
INSERT INTO `jw_system_logo_title` VALUES ('1', 'JeeWx -免费微信管家平台', 'JeeWx捷微管家平台', 'JeeWx捷微 3- 微信管家平台', 'JeeWx捷微1 - 微信管家平台', '<div class=\"head\" style=\"background:#fff\">\n	<div class=\"w1118\">\n		<a class=\"fl\" href=\"http://www.h5huodong.com\"><img class=\"logo\" src=\"http://www.h5huodong.com/content/base/back/common/img/logo.png\" style=\"height:100%\"></a> \n		<ul class=\"cx-nav fr\">\n			<li> <a href=\"http://www.h5huodong.com\">首页</a> </li>\n			<li> <a href=\"https://mp.weixin.qq.com/s/Rvrq3qCLYxDnv__aw-yXIw\" target=\"_blank\">产品介绍</a> </li>\n  <li> <a href=\"http://www.h5huodong.com/system/back/article/toIndex.do\" target=\"_blank\">用户指南</a> </li>\n  <li> <a href=\"http://v.qq.com/vplus/5586465b129e3d912532f783f354df07/foldervideos/ycn000201jpmngc\" target=\"_blank\">视频教程</a> </li>\n   <li> <a  style=\"background: #20b3f6\" href=\"system/login.do\" target=\"_blank\">扫描登录</a></li>\n		</ul>\n		<div class=\"clear\"></div>\n	</div> \n</div>', '<div class=\"cc-foot\">\n		<div class=\"cont ptb25 clearfix\">\n			<div class=\"main_w\">\n				<div class=\"code fl\">\n					<div class=\"logo mb20\" style=\"padding-left:0;height:auto\">\n						<a href=\"http://www.h5huodong.com/content/index.html\"> <img src=\"http://www.h5huodong.com/content/base/back/common/img/logo.png\"></a>\n					</div>\n					<ul class=\"clearfix\">\n						<li> <img src=\"http://www.h5huodong.com/content/base/back/common/img/qrcode.jpg\" width=\"101\" height=\"104\"><p>H5活动之家公众号</p></li>\n						<li> <img src=\"http://www.h5huodong.com/content/base/back/common/img/jeecg.jpg\" width=\"101\" height=\"104\"><p>JEECG官方公众号</p></li>\n					</ul>\n				</div>\n				<div class=\"link fl clearfix\">\n					<ul class=\"fl\">\n						<li><a target=\"_blank\" href=\"http://www.h5huodong.com\">H5活动之家</a></li>\n						<li><a target=\"_blank\" href=\"http://www.jeewx.com\">捷微官网</a></li>\n						<li><a target=\"_blank\" href=\"http://h5huodong.com/system/register.do\">申请入驻</a></li>\n						<li><a target=\"_blank\" href=\"http://wpa.qq.com/msgrd?v=3&uin=3102411850&site=qq&menu=yes\">联系我们</a></li>\n					</ul>\n				</div> \n				<div class=\"contact-us fr\">\n					<h2>北京国炬信息技术有限公司</h2> \n					<p>客服电话：010-64808099</p> <p>客服QQ群：413534092</p> <p>邮箱：jeecg@sina.com</p> \n				</div> \n			</div>\n		</div> \n		<div class=\"copy\">Copyright (c) 2016-2017 www.h5huodong.com. All Rights Reserved Copyright ? 2016-2017 北京国炬信息技术有限公司版权所有 京ICP备15065608号-4</div> \n	</div>', '<div class=\"copyright\" style=\"font-size: 12px;color: #fff;background: rgba(0,0,0,0.4);position: fixed;display: block;width: 100%;bottom: 0;z-index:1000;left:0;text-align:center;height:1.5rem;line-height:1.5rem\">免费创建一个活动 <i class=\"fa fa-hand-o-right\"></i> <a href=\"http://mp.weixin.qq.com/s?__biz=MzI0MzA3MjI3MA==&mid=2656414114&idx=1&sn=962977778fd377b20c5e69c821ad29d5&chksm=f2d3a5c3c5a42cd5a1255fb4858a0749a24d339d3ad17c9d075026b607699ecaea5661dca16f#rd\" style=\"font-size:12px;color:#fff;\">H5活动之家</a></div>', '<label class=\"control-label col-sm-12 line34\">  \n1. 去广告服务：自定义活动底部广告，费用为100元/月。 <br>\n2. 流量费： 活动参与人数未超过200人完全免费，超过200人会产生流量费。\n   <br>（参与人数>=200人， 流量费用50元 ）\n   <br>（参与人数>=500人， 流量费用100元 ）\n   <br>（参与人数>=1000人，流量费用300元，300元为流量费用上限 ）\n   <br> <font color=\"#48a7e7\">友情提示： 流量费用采用后收费模式，活动举办中不受任何影响。</font>\n</label>');

-- ----------------------------
-- Table structure for jw_system_project
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_project`;
CREATE TABLE `jw_system_project` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(100) NOT NULL COMMENT '项目编码',
  `name` varchar(100) NOT NULL COMMENT '项目名称',
  `logo_img` varchar(255) default NULL COMMENT 'logo图',
  `img` varchar(255) default NULL COMMENT '项目图片',
  `discribe` text COMMENT '活动描述',
  `bjurl` varchar(300) default NULL COMMENT '编辑地址',
  `hdurl` varchar(200) default NULL COMMENT '入口地址',
  `gif_url` varchar(1000) default NULL COMMENT 'gif图片地址',
  `hdzs_url` varchar(1000) default NULL COMMENT '活动真实访问地址（最终）',
  `sc_type` tinyint(10) default '1' COMMENT ' 默认值1  1授权素材/2本地素材',
  `detail` text COMMENT '详情',
  `entrance` varchar(2000) default NULL COMMENT '入口地址',
  `type` varchar(200) default NULL COMMENT '类型',
  `sort` varchar(200) default NULL COMMENT '排序',
  `project_classify_id` varchar(32) default NULL COMMENT '项目分类ID',
  `application_type` varchar(10) default NULL COMMENT '应用类型',
  `creat_name` varchar(50) default NULL COMMENT '创建人',
  `creat_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_code` USING BTREE (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统项目管理表';

-- ----------------------------
-- Records of jw_system_project
-- ----------------------------
INSERT INTO `jw_system_project` VALUES ('402880e657bd3f9a0157bd67e91a0004', 'eshop', '综合商城', 'logo.png', 'logo.png', '综合商城是经典的抽奖活动形式。在H5活动平台中商家通过配置活动名称和开始结束时间，相关的商品等信息即可发布活动，用户参与活动后，商家可在后台查看订单信息、客户信息、留言信息等。', 'eshop/back/eshopMain/toAdd.do', 'https://app.jeewx.com/jeewx/linksucai/link.do?linkid=402880e657bd3f9a0157bd67e91a0004', 'logo.png', '/eshop/toCategoryIndex.do?openid=${openid}&appid=${appid}', '2', '<p><span style=\"font-size: 18px;\">&nbsp; 参与用户进入活动首页后，选择自己需要的东西，直接购买，在个人中心可以查看物流信息等。</span></p><p><img src=\"http://h5huodong.com/upload/20161107/53741478505112379.png\" style=\"width:49%;float:left\"/><img src=\"http://h5huodong.com/upload/20161107/79871478505589568.png\" style=\"width:49%;float:left\"/></p>', 'http://w.url.cn/s/AUCgdIz', '1', '12', 'ff808081566df0a601566df251230007', '', null, null, null, null);
INSERT INTO `jw_system_project` VALUES ('4e450f396b294d94a1ff29b50adb50a4', 'cms', '微网站', null, '', '微网站', 'cms/back/cmsSite/toAdd.do', 'http://www.h5huodong.com/cms/index.do', '', '', '1', null, '', '1', '', 'ff808081566df0a601566df251230006', null, null, null, null, null);

-- ----------------------------
-- Table structure for jw_system_project_classify
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_project_classify`;
CREATE TABLE `jw_system_project_classify` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) default NULL COMMENT '分类名称',
  `base_id` varchar(32) default NULL COMMENT '父ID',
  `base_name` varchar(100) default NULL COMMENT '父分类名称',
  `sort` varchar(100) default NULL COMMENT '排序',
  `creat_name` varchar(50) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统项目分类表';

-- ----------------------------
-- Records of jw_system_project_classify
-- ----------------------------
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df0a69a0000', '抽奖类', '', '', '1', '', null, '');
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df0d4a60001', '投票类', '', '', '2', '', null, '');
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df150e50002', '红包类', '', '', '3', '', null, '');
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df19a370003', '增粉类', '', '', '4', '', null, '');
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df1d8dd0004', '互动类', '', '', '5', '', null, '');
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df216f00005', '游戏类', '', '', '6', '', null, '');
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df251230006', '其他类', '', '', '7', '', null, '');
INSERT INTO `jw_system_project_classify` VALUES ('ff808081566df0a601566df251230007', '商城类', null, null, '8', null, null, null);

-- ----------------------------
-- Table structure for jw_system_project_error_config
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_project_error_config`;
CREATE TABLE `jw_system_project_error_config` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `project_url` varchar(255) NOT NULL COMMENT '请求规则',
  `name` varchar(100) NOT NULL COMMENT '规则说明',
  `redirect_url` varchar(255) NOT NULL COMMENT '跳转地址',
  `creat_name` varchar(50) default NULL COMMENT '创建人',
  `creat_time` datetime default NULL COMMENT '创建时间',
  `update_name` varchar(50) default NULL COMMENT '修改人',
  `update_time` datetime default NULL COMMENT '修改时间',
  `jwid` varchar(64) NOT NULL COMMENT '微信公众号',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_projecturl` USING BTREE (`project_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统错误规则配置表';

-- ----------------------------
-- Records of jw_system_project_error_config
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_qrcode_record
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_qrcode_record`;
CREATE TABLE `jw_system_qrcode_record` (
  `id` varchar(32) NOT NULL,
  `jwid` varchar(50) default NULL,
  `openid` varchar(50) default NULL,
  `scene_id` varchar(32) NOT NULL COMMENT '场景id',
  `expire_seconds` int(11) default NULL COMMENT '有效时间秒',
  `qrcode_url` varchar(1000) default NULL COMMENT '图片地址',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统二维码记录表';

-- ----------------------------
-- Records of jw_system_qrcode_record
-- ----------------------------

-- ----------------------------
-- Table structure for jw_system_register
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_register`;
CREATE TABLE `jw_system_register` (
  `id` varchar(36) NOT NULL,
  `email` varchar(50) default NULL COMMENT '注册邮箱',
  `password` varchar(100) default NULL COMMENT '密码',
  `authstring` varchar(36) default NULL COMMENT '验证串',
  `registertime` datetime default NULL COMMENT '注册时间',
  `lastresendtime` datetime default NULL COMMENT '重发时间',
  `checksign` int(11) default NULL COMMENT '确认标志',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户注册表';

-- ----------------------------
-- Records of jw_system_register
-- ----------------------------
INSERT INTO `jw_system_register` VALUES ('4028810c6b97f515016b97f515980000', 'zhangdaiscott@163.com', '4297f44b13955235245b2497399d7a93', '38b7f008-282b-4627-8fa2-8c2d532dff90', '2019-06-27 16:02:29', null, '0');

-- ----------------------------
-- Table structure for jw_system_role
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_role`;
CREATE TABLE `jw_system_role` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `role_id` varchar(32) collate utf8_bin NOT NULL default '' COMMENT '角色编码',
  `role_name` varchar(100) collate utf8_bin default NULL COMMENT '角色名称',
  `crt_operator` varchar(64) collate utf8_bin default NULL COMMENT '创建人',
  `crt_dt` datetime default NULL COMMENT '创建日期',
  `role_typ` char(4) collate utf8_bin default NULL COMMENT '角色种类',
  `del_stat` char(2) collate utf8_bin default NULL COMMENT '删除标志',
  `creator` varchar(64) collate utf8_bin default NULL COMMENT '建立者',
  `editor` varchar(64) collate utf8_bin default NULL COMMENT '编辑者',
  `create_dt` timestamp NULL default NULL COMMENT '建立日期',
  `edit_dt` timestamp NULL default NULL COMMENT '编辑日期',
  `last_edit_dt` timestamp NULL default NULL COMMENT '上次修改日期',
  `record_version` char(8) collate utf8_bin default NULL COMMENT '版本号',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_roleid` USING BTREE (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统角色表';

-- ----------------------------
-- Records of jw_system_role
-- ----------------------------
INSERT INTO `jw_system_role` VALUES ('1', '00', '系统管理员', 'admin', null, null, '0', 'admin', null, '2015-12-23 02:34:05', '2015-12-23 02:34:05', '2015-12-23 02:34:05', null);

-- ----------------------------
-- Table structure for jw_system_role_auth_rel
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_role_auth_rel`;
CREATE TABLE `jw_system_role_auth_rel` (
  `role_id` varchar(32) collate utf8_bin NOT NULL default '' COMMENT '角色编码',
  `auth_id` varchar(32) collate utf8_bin NOT NULL default '' COMMENT '权限编码',
  PRIMARY KEY  (`role_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统角色权限关联表';

-- ----------------------------
-- Records of jw_system_role_auth_rel
-- ----------------------------
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '0082475aec3e103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '0fff2fc4ec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '21');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2101');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210101');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210102');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2102');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210201');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210202');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210203');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210204');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2103');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210301');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '210302');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2104');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2107');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '27a9519ec26011e8aa5852540003411a');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '2d8bf581ec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '3c6fe69cc26011e8aa5852540003411a');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '4f388b42c3bb11e8aa5852540003411a');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '4f7ad75eec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '5d2c6911ec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '6370158ac3bb11e8aa5852540003411a');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '70cbf443ec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '736e3bbac3bb11e8aa5852540003411a');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '92c06cd8ec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', '9d552546c3d011e8aa5852540003411a');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', 'a37d189dec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', 'b0b96e3dec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', 'd931c662ec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', 'ee23607dec3d103696ca5352a33c439c');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', 'f3bcd2bb9ec511e883d43ca0672e915d');
INSERT INTO `jw_system_role_auth_rel` VALUES ('00', 'f5ab2658ec3c103696ca5352a33c439c');

-- ----------------------------
-- Table structure for jw_system_user
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_user`;
CREATE TABLE `jw_system_user` (
  `id` bigint(20) unsigned NOT NULL auto_increment COMMENT '序号',
  `user_id` varchar(32) collate utf8_bin default '' COMMENT '用户编码',
  `user_name` varchar(32) collate utf8_bin default NULL COMMENT '用户名称',
  `password` varchar(64) collate utf8_bin default NULL COMMENT '密码',
  `user_erp_no` varchar(64) collate utf8_bin default NULL COMMENT '用户ERP号',
  `user_typ` char(2) collate utf8_bin default NULL COMMENT '用户种类',
  `ope_dep_id` char(12) collate utf8_bin default NULL COMMENT '部门编码',
  `ope_phone_num` varchar(20) collate utf8_bin default NULL COMMENT '手机电话',
  `email` varchar(64) collate utf8_bin default NULL COMMENT '注册邮箱',
  `ope_email_authinfo` varchar(32) collate utf8_bin default NULL COMMENT '邮箱认证信息',
  `user_icon` varchar(2000) collate utf8_bin default NULL COMMENT '企业logo',
  `ope_mobile_auth_ind` char(2) collate utf8_bin default NULL COMMENT '手机是否认证',
  `ope_email_auth_ind` char(2) collate utf8_bin default NULL COMMENT '邮箱是否认证',
  `id_num` char(20) collate utf8_bin default NULL COMMENT '证件号码',
  `id_typ` char(2) collate utf8_bin default NULL COMMENT '证件种类',
  `state` char(2) collate utf8_bin default NULL COMMENT '状态',
  `user_stat` varchar(20) collate utf8_bin default NULL COMMENT '用户状态 NORMAL:正常；INVALID：无效',
  `last_logn_dttm` datetime default NULL COMMENT '上次登录日期',
  `last_logn_ip` char(15) collate utf8_bin default NULL COMMENT '上次登录IP',
  `ope_passwd_ind` char(2) collate utf8_bin default NULL COMMENT '是否保持密码',
  `del_stat` char(2) collate utf8_bin default NULL COMMENT '删除标志',
  `creator` varchar(64) collate utf8_bin default NULL COMMENT '建立者',
  `editor` varchar(64) collate utf8_bin default NULL COMMENT '编辑者',
  `create_dt` timestamp NULL default NULL COMMENT '建立日期',
  `edit_dt` timestamp NULL default NULL COMMENT '编辑日期',
  `last_edit_dt` timestamp NULL default NULL COMMENT '上次修改日期',
  `record_version` char(8) collate utf8_bin default NULL COMMENT '版本号',
  `openid` varchar(50) collate utf8_bin default NULL,
  `agent_id` varchar(32) collate utf8_bin default NULL COMMENT '代理商id',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_userid` USING BTREE (`user_id`),
  UNIQUE KEY `unqi_openid` USING BTREE (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户表';

-- ----------------------------
-- Records of jw_system_user
-- ----------------------------
INSERT INTO `jw_system_user` VALUES ('1', 'admin', '系统管理员', 'e10adc3949ba59abbe56e057f20f883e', null, null, '', null, null, null, 'content/site/charge/back/img/defaultLogo.jpg', null, null, null, null, null, 'NORMAL', null, null, null, null, null, null, '2015-12-23 06:16:10', null, null, null, null, null);
INSERT INTO `jw_system_user` VALUES ('2', 'jeecg', 'jeecg', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, null, null, null, null, null, null, null, 'NORMAL', null, null, null, null, null, null, '2019-06-28 20:09:55', null, null, null, null, null);

-- ----------------------------
-- Table structure for jw_system_user_authorized
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_user_authorized`;
CREATE TABLE `jw_system_user_authorized` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) default NULL COMMENT '用户id',
  `rel_name` varchar(255) default NULL COMMENT '商家姓名',
  `rel_phone` varchar(11) default NULL COMMENT '商家手机号',
  `authorized_grade` varchar(25) default NULL COMMENT '授权级别',
  `review_status` varchar(1) default NULL COMMENT '审核状态   0未审核 1审核通过 2审核未通过',
  `logo_set_end_date` date default NULL COMMENT 'logo个性化设置有效截止日期',
  `logo_set_content` text COMMENT '自定义广告位内容',
  `logo_cancel_flag` int(11) default '0' COMMENT '活动底部logo取消状态位 0：不取消  1：取消',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_userId` USING BTREE (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户授权表';

-- ----------------------------
-- Records of jw_system_user_authorized
-- ----------------------------
INSERT INTO `jw_system_user_authorized` VALUES ('ff808081586d04f601586d0589ab0001', 'admin', '13439364745', '13439364745', null, null, null, null, '0');

-- ----------------------------
-- Table structure for jw_system_user_jwid
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_user_jwid`;
CREATE TABLE `jw_system_user_jwid` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户编码',
  `jwid` varchar(64) NOT NULL COMMENT '公众号',
  `default_flag` varchar(50) default NULL COMMENT '1为默认登录公共号',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq` USING BTREE (`user_id`,`jwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户公众号关联表';

-- ----------------------------
-- Records of jw_system_user_jwid
-- ----------------------------
INSERT INTO `jw_system_user_jwid` VALUES ('2a72f87feb0910378406f37e9d9928f9', 'jeecg', 'gh_f268aa85d1c7', null);
INSERT INTO `jw_system_user_jwid` VALUES ('3caf8a109d0c11e78911525400077487', 'admin', 'gh_f268aa85d1c7', null);

-- ----------------------------
-- Table structure for jw_system_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `jw_system_user_role_rel`;
CREATE TABLE `jw_system_user_role_rel` (
  `user_id` varchar(32) collate utf8_bin NOT NULL default '' COMMENT '用户id',
  `role_id` varchar(32) collate utf8_bin NOT NULL default '' COMMENT '角色id',
  PRIMARY KEY  (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统用户角色关联表';

-- ----------------------------
-- Records of jw_system_user_role_rel
-- ----------------------------
INSERT INTO `jw_system_user_role_rel` VALUES ('admin', '00');
INSERT INTO `jw_system_user_role_rel` VALUES ('jeecg', '00');

-- ----------------------------
-- Table structure for jw_web_jwid
-- ----------------------------
DROP TABLE IF EXISTS `jw_web_jwid`;
CREATE TABLE `jw_web_jwid` (
  `id` varchar(32) NOT NULL,
  `jwid` varchar(64) NOT NULL COMMENT '公众号',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `application_type` varchar(10) default NULL COMMENT '应用类型',
  `qrcodeimg` varchar(255) default NULL COMMENT '微信二维码图片',
  `create_by` varchar(50) default NULL COMMENT '创建人',
  `create_time` datetime default NULL COMMENT '创建时间',
  `weixin_number` varchar(64) default NULL COMMENT '微信号',
  `weixin_appid` varchar(50) default NULL COMMENT '微信AppId',
  `weixin_appsecret` varchar(255) default NULL COMMENT '微信AppSecret',
  `account_type` varchar(255) default NULL COMMENT '公众号类型',
  `auth_status` varchar(255) default NULL COMMENT '是否认证',
  `access_token` varchar(2000) default NULL COMMENT 'Access_Token',
  `token_gettime` datetime default NULL COMMENT 'token获取的时间',
  `apiticket` varchar(255) default NULL COMMENT 'api凭证',
  `apiticket_gettime` datetime default NULL COMMENT 'apiticket获取时间',
  `jsapiticket` varchar(255) default NULL COMMENT 'jsapiticket',
  `jsapiticket_gettime` datetime default NULL COMMENT 'jsapiticket获取时间',
  `auth_type` varchar(10) default NULL COMMENT '类型：1手动授权，2扫码授权',
  `business_info` varchar(5000) default NULL COMMENT '功能的开通状况：1代表已开通',
  `func_info` varchar(5000) default NULL COMMENT '公众号授权给开发者的权限集',
  `nick_name` varchar(200) default NULL COMMENT '授权方昵称',
  `headimgurl` varchar(1000) default NULL COMMENT '授权方头像',
  `authorization_info` varchar(5000) default NULL COMMENT '授权信息',
  `authorizer_refresh_token` varchar(500) default NULL COMMENT '刷新token',
  `token` varchar(32) default NULL COMMENT '令牌',
  `authorization_status` varchar(10) default NULL COMMENT '授权状态（1正常，2已取消）',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_jwid` USING BTREE (`jwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统公众号表';

-- ----------------------------
-- Records of jw_web_jwid
-- ----------------------------
INSERT INTO `jw_web_jwid` VALUES ('ff80808151f1628c0151f16470cb0006', 'gh_f268aa85d1c7', '捷微活动管家', '', '6669fede4e5047229b2f57c020eee13f.jpg', 'admin', null, 'guojusoft', 'wxc60a4d9cbac8092d', '?', '1', '1', 'oQwQRFwLMBfm_y2JUaEPKUhEgjvDOs74LAAv_kYyGwWGZixMqHyGSSiCFAQCC', '2019-07-12 18:30:01', 'IpK_1T69hDhZkLQTlwsAXyjkZaTEYbX_kE6IkQ8b5WYOjIwZ4krIfp2iANwfePSHRZ2dY0ccPrEg62U_P3vcHg', '2019-07-12 18:30:01', 'kgt8ON7yVITDhtdwci0qeeC2RWfOAQ1Sc_HMdjrzSlrhTCHlT3OE-6tp0NFL378JxIAuJhAjnQ2yDS_yeAiwQw', '2019-07-12 18:30:01', '', null, null, null, null, null, null, 'jeewx', '1');

-- ----------------------------
-- Table structure for weixin_autoresponse
-- ----------------------------
DROP TABLE IF EXISTS `weixin_autoresponse`;
CREATE TABLE `weixin_autoresponse` (
  `ID` varchar(32) NOT NULL,
  `keyword` varchar(255) default NULL COMMENT '关键字',
  `msg_type` varchar(255) default NULL COMMENT '消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)',
  `template_id` varchar(255) default NULL COMMENT '模板ID',
  `template_name` varchar(255) default NULL COMMENT '关联模板名称',
  `JWID` varchar(100) default NULL COMMENT '微信ID',
  `keyword_type` varchar(5) default '1' COMMENT '关键字类型1:全匹配  2：模糊匹配',
  `create_by` varchar(64) default NULL COMMENT '创建人登录名称',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '修改人登录名称',
  `update_time` datetime default NULL COMMENT '修改时间',
  `ISWORK` varchar(10) default NULL COMMENT '是否启用',
  PRIMARY KEY  (`ID`),
  KEY `normal_keyword` USING BTREE (`keyword`),
  KEY `normal_tempId` USING BTREE (`template_id`),
  KEY `normal_jwid` USING BTREE (`JWID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关键字表';

-- ----------------------------
-- Records of weixin_autoresponse
-- ----------------------------
INSERT INTO `weixin_autoresponse` VALUES ('4028810c6be55ffe016be566c7ff000a', '文档', 'text', '4028810c6be55ffe016be56315800005', '技术文档', 'gh_f268aa85d1c7', '1', 'admin', '2019-07-12 16:57:26', null, null, null);

-- ----------------------------
-- Table structure for weixin_autoresponse_default
-- ----------------------------
DROP TABLE IF EXISTS `weixin_autoresponse_default`;
CREATE TABLE `weixin_autoresponse_default` (
  `ID` varchar(50) NOT NULL,
  `JWID` varchar(40) default NULL COMMENT '微信ID',
  `ISWORK` varchar(10) default NULL COMMENT '是否启用',
  `MSG_TYPE` varchar(40) default NULL COMMENT '消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)',
  `TEMPLATE_ID` varchar(50) NOT NULL COMMENT '模板ID',
  `TEMPLATE_NAME` varchar(200) NOT NULL COMMENT '模板名称',
  `msg_trigger_type` varchar(40) default NULL COMMENT '触发类型',
  `create_by` varchar(64) default NULL COMMENT '创建人登录名称',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '修改人登录名称',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`ID`),
  KEY `normal_jwid` USING BTREE (`JWID`),
  KEY `normal_tempId` USING BTREE (`TEMPLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='未识别回复语';

-- ----------------------------
-- Records of weixin_autoresponse_default
-- ----------------------------
INSERT INTO `weixin_autoresponse_default` VALUES ('4028810c6be55ffe016be5669eee0009', 'gh_f268aa85d1c7', null, 'text', '4028810c6bb69a33016bb69a33600000', '欢迎关注', 'text', 'admin', '2019-07-12 16:57:16', null, null);

-- ----------------------------
-- Table structure for weixin_gzuser
-- ----------------------------
DROP TABLE IF EXISTS `weixin_gzuser`;
CREATE TABLE `weixin_gzuser` (
  `id` varchar(32) NOT NULL COMMENT '序号',
  `openid` varchar(32) default NULL COMMENT 'openid',
  `nickname` varchar(255) default NULL COMMENT '昵称',
  `nickname_txt` varchar(255) default NULL COMMENT '过滤后昵称',
  `bzname` varchar(255) default NULL COMMENT '备注名称',
  `headimgurl` varchar(500) default NULL COMMENT '用户头像',
  `sex` varchar(10) default NULL COMMENT '性别：''1'':男性；''2'':女性；''0'':未知',
  `subscribe` varchar(10) default NULL COMMENT '是否关注:''0'':未关注；''1'':关注',
  `subscribe_time` varchar(50) default NULL COMMENT '关注时间',
  `subscribe_scene` varchar(50) default NULL COMMENT '用户关注渠道来源',
  `mobile` varchar(20) default NULL COMMENT '手机号',
  `bind_status` varchar(10) default NULL COMMENT '绑定状态：''N'':未绑定；''Y'':已绑定；''V'':绑定中',
  `bind_time` datetime default NULL COMMENT '绑定时间',
  `tagid_list` varchar(1000) default NULL COMMENT '标签id',
  `province` varchar(255) default NULL COMMENT '省份',
  `city` varchar(255) default NULL COMMENT '城市',
  `country` varchar(255) default NULL COMMENT '地区',
  `qr_scene` varchar(20) default NULL COMMENT '二维码扫码场景',
  `qr_scene_str` varchar(255) default NULL COMMENT '二维码扫码常见描述',
  `groupid` varchar(64) default NULL COMMENT '用户所在分组id',
  `language` varchar(20) default NULL COMMENT '用户的语言，简体中文为zh_CN',
  `unionid` varchar(64) default NULL,
  `jwid` varchar(64) default NULL COMMENT '公众号原始id',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uniq_openid` USING BTREE (`openid`),
  KEY `index_mobile` USING BTREE (`mobile`),
  KEY `index_qrScene` USING BTREE (`qr_scene`),
  KEY `index_jwid` USING BTREE (`jwid`),
  KEY `index_bindTime` USING BTREE (`bind_time`),
  KEY `index_tagidList` USING BTREE (`tagid_list`(255)),
  KEY `index_createTime` USING BTREE (`create_time`),
  KEY `index_subscribeTime` USING BTREE (`subscribe_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='粉丝表';


-- ----------------------------
-- Table structure for weixin_huodong_biz_jwid
-- ----------------------------
DROP TABLE IF EXISTS `weixin_huodong_biz_jwid`;
CREATE TABLE `weixin_huodong_biz_jwid` (
  `id` varchar(32) NOT NULL COMMENT '序号',
  `table_name` varchar(255) default NULL COMMENT '表名称',
  `table_remake` varchar(255) default NULL COMMENT '表注释',
  `table_type` varchar(2) default NULL COMMENT '表类型：''1''：微信，''2''：活动',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信活动jwid表';

-- ----------------------------
-- Records of weixin_huodong_biz_jwid
-- ----------------------------
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830017', 'weixin_texttemplate', '文本模板', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830018', 'weixin_subscribe', '关注欢迎语', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830019', 'weixin_newstemplate', '图文模板表', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830020', 'weixin_menu', '微信菜单表', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830021', 'weixin_gzuser', '粉丝表', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830024', 'weixin_autoresponse_default', '未识别回复语', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830025', 'weixin_autoresponse', '关键字表', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830026', 'weixin_receivetext', '消息存储表', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830028', 'weixin_newstemplate', '图文素材表', '1');
INSERT INTO `weixin_huodong_biz_jwid` VALUES ('ff80808152a2abd20153592394830051', 'weixin_tag', '粉丝标签表', '1');

-- ----------------------------
-- Table structure for weixin_linksucai
-- ----------------------------
DROP TABLE IF EXISTS `weixin_linksucai`;
CREATE TABLE `weixin_linksucai` (
  `id` varchar(36) NOT NULL,
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '修改人名称',
  `update_date` datetime default NULL COMMENT '修改日期',
  `name` varchar(100) default NULL COMMENT '链接名称',
  `outer_link` longtext COMMENT '外部链接',
  `content` longtext COMMENT '功能描述',
  `inner_link` longtext COMMENT '内部链接',
  `transfer_sign` int(11) default NULL COMMENT '转换标志',
  `accountid` varchar(100) default NULL COMMENT '微信账户id',
  `post_code` varchar(200) default NULL COMMENT '账号邮编',
  `share_status` varchar(10) default 'N' COMMENT '分享状态',
  `is_encrypt` int(2) NOT NULL default '0' COMMENT '是否加密（0：不加密，1：加密）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信素材链接表';

-- ----------------------------
-- Records of weixin_linksucai
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_menu
-- ----------------------------
DROP TABLE IF EXISTS `weixin_menu`;
CREATE TABLE `weixin_menu` (
  `id` varchar(32) default NULL COMMENT '序号',
  `father_id` varchar(32) default NULL COMMENT '父id',
  `menu_key` varchar(64) default NULL COMMENT '菜单KEY',
  `menu_type` varchar(20) default NULL COMMENT '菜单类型',
  `menu_name` varchar(64) default NULL COMMENT '菜单名称',
  `url` varchar(2000) default NULL COMMENT '网页链接',
  `msgtype` varchar(10) default NULL COMMENT '相应消息类型',
  `orders` varchar(10) default NULL COMMENT '菜单位置',
  `template_id` varchar(32) default NULL COMMENT '关联素材id',
  `jwid` varchar(64) default NULL COMMENT '公众号原始id',
  `miniprogram_appid` varchar(64) default NULL COMMENT '小程序appid',
  `miniprogram_pagepath` varchar(255) default NULL COMMENT '小程序页面路径',
  `create_by` varchar(64) default NULL COMMENT '创建人登录名称',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '修改人登录名称',
  `update_time` datetime default NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信菜单表';

-- ----------------------------
-- Records of weixin_menu
-- ----------------------------
INSERT INTO `weixin_menu` VALUES ('4028810c6be55ffe016be55ffe350000', null, '1562921401904', 'click', '开源项目', '', 'text', '1', '4028810c6bb69a33016bb69a33600000', 'gh_f268aa85d1c7', '', '', 'admin', '2019-07-12 16:50:01', null, null);
INSERT INTO `weixin_menu` VALUES ('4028810c6be55ffe016be56031ca0001', '', '1562921415113', 'click', '技术文档', '', 'text', '2', '4028810c6be55ffe016be56315800005', 'gh_f268aa85d1c7', '', '', 'admin', '2019-07-12 16:50:15', 'admin', '2019-07-12 16:53:34');
INSERT INTO `weixin_menu` VALUES ('4028810c6be55ffe016be560c31f0002', '4028810c6be55ffe016be55ffe350000', '1562921452318', 'view', 'JeecgBoot开发平台', 'https://github.com/zhangdaiscott/jeecg-boot', 'text', '11', '4028810c6bb69a33016bb69a33600000', 'gh_f268aa85d1c7', '', '', 'admin', '2019-07-12 16:50:52', null, null);
INSERT INTO `weixin_menu` VALUES ('4028810c6be55ffe016be56105270003', '4028810c6be55ffe016be55ffe350000', '1562921469223', 'view', 'jeewx-boot微信平台', 'https://github.com/zhangdaiscott/jeewx-boot', 'text', '12', '4028810c6bb69a33016bb69a33600000', 'gh_f268aa85d1c7', '', '', 'admin', '2019-07-12 16:51:09', 'admin', '2019-07-12 16:51:16');
INSERT INTO `weixin_menu` VALUES ('4028810c6be55ffe016be561cc230004', null, '1562921520163', 'click', '官网', '', 'text', '3', '4028810c6bb69a33016bb69a33600000', 'gh_f268aa85d1c7', '', '', 'admin', '2019-07-12 16:52:00', null, null);
INSERT INTO `weixin_menu` VALUES ('4028810c6be55ffe016be5638d250006', '4028810c6be55ffe016be561cc230004', '1562921635109', 'view', 'jeecg官网', 'http://www.jeecg.com', 'text', '31', '4028810c6bb69a33016bb69a33600000', 'gh_f268aa85d1c7', '', '', 'admin', '2019-07-12 16:53:55', null, null);
INSERT INTO `weixin_menu` VALUES ('4028810c6be55ffe016be563f1ed0007', '4028810c6be55ffe016be561cc230004', '1562921660909', 'view', 'Jeewx官网', 'http://www.jeewx.com', 'text', '32', '4028810c6bb69a33016bb69a33600000', 'gh_f268aa85d1c7', '', '', 'admin', '2019-07-12 16:54:20', 'admin', '2019-07-12 16:54:35');

-- ----------------------------
-- Table structure for weixin_newsitem
-- ----------------------------
DROP TABLE IF EXISTS `weixin_newsitem`;
CREATE TABLE `weixin_newsitem` (
  `id` varchar(32) default NULL COMMENT '序号',
  `newstemplate_id` varchar(32) default NULL COMMENT '图文id',
  `thumb_media_id` varchar(64) default NULL COMMENT '图文缩略图的media_id',
  `title` varchar(200) default NULL COMMENT '标题',
  `author` varchar(100) default NULL COMMENT '作者',
  `image_path` varchar(255) default NULL COMMENT '图片路径',
  `content` longtext COMMENT '内容',
  `description` varchar(500) default NULL,
  `order_no` varchar(10) default NULL COMMENT '素材顺序',
  `new_type` varchar(10) default NULL COMMENT '图文：news；外部url：url',
  `url` varchar(2000) default NULL COMMENT '原文链接',
  `external_url` varchar(2000) default NULL COMMENT '外部链接',
  `show_cover_pic` varchar(2) default '1' COMMENT '是否显示封面：''1'':显示,''0'':不显示',
  `create_by` varchar(64) default NULL COMMENT '创建人名称',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '修改人名称',
  `update_time` datetime default NULL COMMENT '修改人时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图文模板素材表';

-- ----------------------------
-- Records of weixin_newsitem
-- ----------------------------
INSERT INTO `weixin_newsitem` VALUES ('4028810c6bb1dcc9016bb1dcc93a0000', '4028810c6bb1b80a016bb1b80abd0000', '', '测试图文', '', 'upload/files/11896a89e9304f729ebc91a12d6e0271.png', '<p><img style=\"max-width:100%\" src=\"http://localhost/jeewx/upload/20190702/74671562057149633.png\" _src=\"http://localhost/jeewx/upload/20190702/74671562057149633.png\"/></p>', '', '1', 'news', '', '', '1', 'admin', '2019-07-02 16:46:05', 'admin', '2019-07-12 16:58:03');

-- ----------------------------
-- Table structure for weixin_newstemplate
-- ----------------------------
DROP TABLE IF EXISTS `weixin_newstemplate`;
CREATE TABLE `weixin_newstemplate` (
  `id` varchar(32) default NULL,
  `template_name` varchar(200) default NULL COMMENT '模板名称',
  `template_type` varchar(10) default NULL COMMENT '模板类型',
  `media_id` varchar(64) default NULL COMMENT '图文素材媒体id',
  `jwid` varchar(64) default NULL COMMENT '公众号原始id',
  `upload_type` varchar(10) default NULL COMMENT '上传状态 "0"未上传，"1"上传中，"2"上传成功，"3"上传失败',
  `upload_time` datetime default NULL COMMENT '上传时间',
  `create_by` varchar(64) default NULL COMMENT '创建人名称',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '修改人名称',
  `update_time` datetime default NULL COMMENT '修改人名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图文模板表';

-- ----------------------------
-- Records of weixin_newstemplate
-- ----------------------------
INSERT INTO `weixin_newstemplate` VALUES ('4028810c6bb1b80a016bb1b80abd0000', '111', 'news', null, 'gh_f268aa85d1c7', '3', null, 'admin', '2019-07-02 16:05:57', 'admin', '2019-07-12 16:58:03');

-- ----------------------------
-- Table structure for weixin_receivetext
-- ----------------------------
DROP TABLE IF EXISTS `weixin_receivetext`;
CREATE TABLE `weixin_receivetext` (
  `ID` varchar(32) NOT NULL,
  `content` longtext COMMENT '消息内容',
  `create_time` datetime default NULL COMMENT '创建日期',
  `from_user_name` varchar(255) default NULL COMMENT '发送方帐号（OpenId）',
  `msg_id` varchar(255) default NULL COMMENT '消息ID',
  `msg_type` varchar(255) default NULL COMMENT '消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)消息类型',
  `to_user_name` varchar(255) default NULL COMMENT 'JWID',
  `jwid` varchar(255) default NULL COMMENT '公众号原始ID',
  `media_id` varchar(64) default NULL COMMENT '多媒体ID',
  PRIMARY KEY  (`ID`),
  KEY `index_fromname` USING BTREE (`from_user_name`),
  KEY `inde_toname` USING BTREE (`to_user_name`),
  KEY `index_mediaid` USING BTREE (`media_id`),
  KEY `index_createtime` USING BTREE (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息存储表';

-- ----------------------------
-- Records of weixin_receivetext
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_recept_msg
-- ----------------------------
DROP TABLE IF EXISTS `weixin_recept_msg`;
CREATE TABLE `weixin_recept_msg` (
  `ID` varchar(32) NOT NULL,
  `content` varchar(2000) default NULL COMMENT '内容',
  `create_time` datetime default NULL COMMENT '时间',
  `from_username` varchar(100) default NULL COMMENT '发送人',
  `to_username` varchar(50) default NULL COMMENT '接收人',
  `msgtype` varchar(20) default NULL COMMENT '消息类型',
  `mediaid` varchar(64) default NULL COMMENT '多媒体id',
  `template_id` varchar(32) default NULL COMMENT '素材ID',
  `send_stauts` varchar(2) default NULL COMMENT '发送状态，1：成功：2:失败',
  `jwid` varchar(100) default NULL COMMENT '公众号ID',
  PRIMARY KEY  (`ID`),
  KEY `idx_createtime` USING BTREE (`create_time`),
  KEY `idx_tousername` USING BTREE (`to_username`),
  KEY `idx_jwid` USING BTREE (`jwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服消息记录表';

-- ----------------------------
-- Records of weixin_recept_msg
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `weixin_subscribe`;
CREATE TABLE `weixin_subscribe` (
  `ID` varchar(32) NOT NULL,
  `JWID` varchar(255) default NULL COMMENT '微信ID',
  `msg_Type` varchar(255) default NULL COMMENT '消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)',
  `template_Id` varchar(255) default NULL COMMENT '模板ID',
  `template_Name` varchar(255) default NULL COMMENT '模板名称',
  `create_by` varchar(64) default NULL COMMENT '创建人登录名称',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '修改人登录名称',
  `update_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`ID`),
  KEY `normal_jwid` USING BTREE (`JWID`),
  KEY `normal_tempId` USING BTREE (`template_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注欢迎语';

-- ----------------------------
-- Records of weixin_subscribe
-- ----------------------------
INSERT INTO `weixin_subscribe` VALUES ('4028810c6be55ffe016be56688220008', 'gh_f268aa85d1c7', 'text', '4028810c6bb69a33016bb69a33600000', '欢迎关注', 'admin', '2019-07-12 16:57:10', null, null);

-- ----------------------------
-- Table structure for weixin_tag
-- ----------------------------
DROP TABLE IF EXISTS `weixin_tag`;
CREATE TABLE `weixin_tag` (
  `id` varchar(32) NOT NULL,
  `tagid` varchar(32) default NULL COMMENT '标签id',
  `name` varchar(255) default NULL COMMENT '标签名称',
  `jwid` varchar(32) default NULL COMMENT '标签归属公众号原始id',
  `addtime` datetime default NULL COMMENT '添加时间',
  `synctime` datetime default NULL COMMENT '同步时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='粉丝标签表';

-- ----------------------------
-- Records of weixin_tag
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_template
-- ----------------------------
DROP TABLE IF EXISTS `weixin_template`;
CREATE TABLE `weixin_template` (
  `id` varchar(36) NOT NULL,
  `create_name` varchar(50) default NULL COMMENT '创建人名称',
  `create_by` varchar(50) default NULL COMMENT '创建人登录名称',
  `create_date` datetime default NULL COMMENT '创建日期',
  `update_name` varchar(50) default NULL COMMENT '更新人名称',
  `update_by` varchar(50) default NULL COMMENT '更新人登录名称',
  `update_date` datetime default NULL COMMENT '更新日期',
  `title` varchar(32) default NULL COMMENT '标题',
  `type` varchar(32) default NULL COMMENT '类型',
  `content` longtext COMMENT '模板内容',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信文章元素模板';

-- ----------------------------
-- Records of weixin_template
-- ----------------------------
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbce9c2f00000', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '关注引导0002', 'WXGZYD', '<p style=\"margin: 0px auto; padding: 0px; border: 1px solid rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; line-height: 24px; white-space: normal; max-width: 600px; word-wrap: normal; min-height: 1em; font-size: 12px; border-top-left-radius: 2em!important; border-top-right-radius: 2em!important; border-bottom-left-radius: 2em!important; border-bottom-right-radius: 2em!important; box-sizing: border-box !important;\"><span class=\"main\" style=\"margin: 0px; padding: 2px 2px 2px 6px; border: 0px; max-width: 100%; color: rgb(255, 255, 255); border-top-left-radius: 2em!important; border-bottom-left-radius: 2em!important; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; line-height: 0px; word-wrap: break-word !important; box-sizing: border-box !important;\">?</span><img data-ratio=\"0.5\" data-w=\"22\" src=\"https://mmbiz.qlogo.cn/mmbiz/mj9u1OBZRqP8EvePIzqrRIHCHOzYM4ngF6tp3gjiaPQxwzT0ZR0XYZR7fTf4Pw5Xc6HV4Nw7WtbzOb8KuNnezJQ/0/mmbizgif\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; height: auto !important; visibility: visible !important;\">&nbsp;<strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">提示</strong>：</span><span style=\"margin: 0px; padding: 0px 0px 0px 2px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(127, 127, 127); word-wrap: break-word !important; box-sizing: border-box !important;\">点击上方</span><span style=\"margin: 0px; padding: 0px 0px 0px 2px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\"</span><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px 0px 0px 2px; border: 0px; max-width: 100%; color: rgb(0, 112, 192); word-wrap: break-word !important; box-sizing: border-box !important;\">微信</span></strong><span style=\"margin: 0px; padding: 0px 0px 0px 2px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\"</span><span style=\"margin: 0px; padding: 0px 0px 0px 2px; border: 0px; max-width: 100%; color: rgb(0, 187, 236); word-wrap: break-word !important; box-sizing: border-box !important;\">↑</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(127, 127, 127); word-wrap: break-word !important; box-sizing: border-box !important;\">免费订阅本刊</span></span></p><p><span style=\"margin: 0px; padding: 0px 0px 0px 2px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(127, 127, 127); word-wrap: break-word !important; box-sizing: border-box !important;\"><br></span></span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd0f34790001', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '关注引导0003', 'WXGZYD', '<section style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal; vertical-align: top;\"><section class=\"awb-s1\" style=\"margin: 0px 0px 0px 158.875px; padding: 0px; border-top-width: 0px; border-bottom-width: 0.8em; border-bottom-style: solid; border-bottom-color: rgb(0, 187, 236); width: 0px; border-right-width: 0.8em !important; border-left-width: 0.8em !important; border-right-style: solid !important; border-left-style: solid !important; border-top-color: transparent !important; border-right-color: transparent !important; border-left-color: transparent !important;\"></section><section class=\"awb-s2\" style=\"margin: 0px; padding: 0px; border: 0px; height: 2.5em; border-top-left-radius: 2em; border-top-right-radius: 2em; border-bottom-right-radius: 2em; border-bottom-left-radius: 2em; background-color: rgb(0, 187, 236);\"><img src=\"https://mmbiz.qlogo.cn/mmbiz/mj9u1OBZRqP8EvePIzqrRIHCHOzYM4ngZZKnvicNMiaibKzVA2ovvHnic2aQwX5oicrDlicrlJU8LfjEvURsQHCgThGA/0\" style=\"margin: 0.5em 0.6em; padding: 0px; border: 0px; height: 1.6em; vertical-align: top;\"><section style=\"margin: 0.3em 0px 0px; padding: 0px; border: 0px; display: inline-block; width: 231.6875px; text-align: center; font-size: 1.2em; white-space: nowrap; overflow: hidden;\"><section style=\"margin: 0px; padding: 0px; border: 0px; display: inline-block; color: rgb(255, 255, 255);\">点击上方</section><section style=\"margin: 0px; padding: 0px; border: 0px; display: inline-block; color: rgb(64, 84, 115);\">“蓝色字”</section><section style=\"margin: 0px; padding: 0px; border: 0px; display: inline-block; color: rgb(255, 255, 255);\">可关注我们！</section></section></section><p></p><section style=\"margin: 0.3em 0px 0px; padding: 0px; border: 0px; display: inline-block; width: 231.6875px; text-align: center; font-size: 1.2em; white-space: nowrap; overflow: hidden;\"><section style=\"margin: 0px; padding: 0px; border: 0px; display: inline-block; color: rgb(255, 255, 255);\"><br></section></section><p></p></section><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd10739d0002', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0001', 'WXBT', '<h2 class=\"brcolor\" style=\"BOX-SIZING: border-box !important; WORD-WRAP: break-word !important; FONT-SIZE: 20px; MAX-WIDTH: 100%; HEIGHT: 35px; FONT-FAMILY: 微软雅黑; WHITE-SPACE: normal; BORDER-BOTTOM: rgb(242,87,45) 1px solid; FONT-WEIGHT: normal; COLOR: rgb(48,55,64); PADDING-BOTTOM: 1px; TEXT-ALIGN: justify; PADDING-TOP: 1px; PADDING-LEFT: 0px; MARGIN: 0px; LINE-HEIGHT: 2px; PADDING-RIGHT: 0px\"><span class=\"brcolor\" style=\"BOX-SIZING: border-box !important; WORD-WRAP: break-word !important; MAX-WIDTH: 100%; BORDER-BOTTOM: rgb(242,87,45) 20px solid; FLOAT: left; PADDING-BOTTOM: 2px; PADDING-TOP: 8px; PADDING-LEFT: 8px; DISPLAY: block; PADDING-RIGHT: 8px\"><strong style=\"BOX-SIZING: border-box !important; WORD-WRAP: break-word !important; MAX-WIDTH: 100%; LINE-HEIGHT: 24px\"><strong style=\"BOX-SIZING: border-box !important; WORD-WRAP: break-word !important; MAX-WIDTH: 100%\">微</strong></strong></span><p style=\"BOX-SIZING: border-box !important; WORD-WRAP: break-word !important; MARGIN-BOTTOM: 0px; MAX-WIDTH: 100%; WHITE-SPACE: pre-wrap; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MIN-HEIGHT: 1.5em; MARGIN-TOP: 0px; LINE-HEIGHT: 2em; PADDING-RIGHT: 0px\"><span style=\"PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; BACKGROUND-COLOR: rgb(255,192,0)\"><strong>&nbsp;&nbsp;<span style=\"FONT-SIZE: 14px; COLOR: rgb(127,127,127)\">&nbsp;利器&nbsp;</span></strong></span><br style=\"BOX-SIZING: border-box !important; WORD-WRAP: break-word !important; MAX-WIDTH: 100%\"></p></h2><p><br></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd11818e0003', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0002', 'WXBT', '<fieldset class=\"brcolor\" style=\"margin: 0px; padding: 0px; border: 0px; font-size: 13px; white-space: normal; max-width: 100%; box-sizing: border-box; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; text-align: center; clear: both; word-wrap: break-word !important;\"><section class=\"main brcolor\" style=\"margin: 0px; padding: 0px; border: 0.4em solid rgb(0, 187, 236); display: inline-block; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(248, 247, 245);\"><section style=\"margin: -0.4em 0.5em; padding: 0.5em; border-width: 0.5em 0px; border-top-style: solid; border-bottom-style: solid; border-top-color: rgb(248, 247, 245); border-bottom-color: rgb(248, 247, 245); max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><section style=\"margin: 0px; padding: 0px; border: 0px; display: inline-table; max-width: 100%; vertical-align: middle; word-wrap: break-word !important; box-sizing: border-box !important;\"><section class=\"main2 bfcolor\" style=\"margin: 0px; padding: 0px; border: 0px; display: table; max-width: 100%; vertical-align: middle; line-height: 1.5; font-size: 1em; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(0, 187, 236); word-wrap: break-word !important; box-sizing: border-box !important;\"><br style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">必属精品<br style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"></section></section><section class=\"main3 bkcolor\" style=\"margin: 0px; padding: 0px; border: 0px; display: inline-block; max-width: 100%; box-sizing: border-box; vertical-align: middle; height: 3em; width: 3em; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 0px; border-bottom-left-radius: 50%; word-wrap: break-word !important; background-color: rgb(0, 187, 236);\"><section style=\"margin: 0.2em; padding: 0px; border: 0.2em solid rgb(255, 255, 255); max-width: 100%; box-sizing: border-box; height: 2.6em; width: 2.6em; border-top-left-radius: 50%; border-top-right-radius: 50%; border-bottom-right-radius: 50%; border-bottom-left-radius: 50%; word-wrap: break-word !important; background-color: transparent;\"><section style=\"margin: 0.05em 0px 0px; padding: 0px; border: 0px; max-width: 100%; line-height: 1; font-size: 2em; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(255, 255, 255); word-wrap: break-word !important; box-sizing: border-box !important;\">1</section></section></section></section><section class=\"main4 brcolor\" style=\"margin: 0.5em 0px; padding: 0px; border-width: 1px 0px 0px; border-top-style: solid; border-color: rgb(0, 187, 236); max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"></section><section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; line-height: 1; font-size: 0.9em; font-family: inherit; text-align: inherit; text-decoration: inherit; word-wrap: break-word !important; box-sizing: border-box !important;\">这里可输入标题，自适应宽度</section></section></section></fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1229670004', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0003', 'WXBT', '<p class=\"brcolor\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border-width: 2px 0px 0px; border-top-style: solid; border-top-color: rgb(0, 187, 236); font-size: 13px; white-space: normal; max-width: 100%; word-wrap: normal; min-height: 1.5em; color: rgb(62, 62, 62); line-height: 2em; font-family: 微软雅黑; box-sizing: border-box !important;\"><span class=\"bkcolor\" style=\"margin: 0px; padding: 5px 10px; border: 0px; max-width: 100%; color: rgb(255, 255, 255); display: inline-block; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\">这可输入标题</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; font-size: 13px; white-space: normal; max-width: 100%; word-wrap: normal; min-height: 1em; color: rgb(62, 62, 62); font-family: \'Helvetica Neue\', Helvetica, \'Hiragino Sans GB\', \'Microsoft YaHei\', 微软雅黑, Arial, sans-serif; line-height: 25px; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\"><br></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd12e2da0005', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0004', 'WXBT', '<h2 class=\"brcolor bfcolor\" data-page-model=\"title\" style=\"margin: 25px 0px 20px; padding: 5px 0px 10px 7px; border-width: 2px 0px 0px; border-top-style: solid; border-top-color: rgb(0, 187, 236); font-size: 22px; font-weight: 100; white-space: normal; max-width: 100%; clear: both; font-family: 微软雅黑; line-height: 35px; color: rgb(0, 187, 236); word-wrap: break-word !important; box-sizing: border-box !important; background-image: url(http://www.weituibao.com/img/editor/guide/aticletitBg.png); background-color: rgb(255, 255, 255); background-position: 0px 100%; background-repeat: repeat-x;\">一、这可输入标题</h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd13afe80006', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0005', 'WXBT', '<p></p><section class=\"main1 brcolor\" style=\"margin: 0.5em 0px; padding: 0px; border-width: 0px 0px 1px; border-bottom-style: solid; border-color: rgb(0, 187, 236); display: inline-block; font-size: 13px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 1em; overflow: hidden; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\"><section class=\"main2 bkcolor\" style=\"margin: 0px; padding: 0.2em; border: 0px; display: inline-block; max-width: 100%; height: 2.8em; line-height: 1em; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"><section class=\"main3\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(255, 255, 255); line-height: 1em; font-family: inherit; font-size: 2.5em; word-wrap: break-word !important; box-sizing: border-box !important;\">1</section></section><section class=\"main4 brcolor\" style=\"margin: 0px; padding: 0.2em; border: 0px; display: inline-block; max-width: 100%; color: rgb(42, 52, 58); line-height: 1em; font-family: inherit; font-size: 1.5em; word-wrap: break-word !important; box-sizing: border-box !important;\">这可输入标题</section></section><span style=\"color: rgb(68, 68, 68); font-family: \'Microsoft YaHei\'; font-size: 13px; line-height: 24px; background-color: rgb(255, 255, 255);\">&nbsp;</span><p></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1447400007', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0006', 'WXBT', '<section class=\"main_1\" style=\"margin: 0px; padding: 0px; border: 0px; font-size: 13px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\"><section class=\"main_2\" style=\"margin: 0.8em 0px 0.5em; padding: 0px; border: 0px; max-width: 100%; overflow: hidden; word-wrap: break-word !important; box-sizing: border-box !important;\"><section class=\"main_3\" style=\"margin: 0px; padding: 0.3em 0.5em; border: 0px; display: inline-block; max-width: 100%; height: 2em; color: white; text-align: center; font-size: 1em; line-height: 1.4em; vertical-align: top; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">第一步</strong></section><section class=\"main_4 margin-left-3\" style=\"margin: 0px; padding: 0px; border-top-width: 1em !important; border-right-width: 0px; border-bottom-width: 1em !important; border-left-width: 0.5em; border-top-style: solid !important; border-bottom-style: solid !important; border-left-style: solid; border-top-color: transparent !important; border-right-color: rgb(0, 187, 236); border-bottom-color: transparent !important; border-left-color: rgb(0, 187, 236); display: inline-block; max-width: 100%; height: 2em; width: 0.5em; vertical-align: top; word-wrap: break-word !important; box-sizing: border-box !important;\"></section></section></section><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd150f9a0008', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0007', 'WXBT', '<section style=\"margin: 0.8em 0px 0.5em; padding: 0px; border: 0px; font-size: 13px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); font-family: 微软雅黑; line-height: 25px; word-wrap: break-word !important; box-sizing: border-box !important;\"><span class=\"bkcolor\" style=\"margin: 0px; padding: 0.3em 0.5em; border: 0px; max-width: 100%; display: inline-block; border-top-left-radius: 0.5em; border-top-right-radius: 0.5em; border-bottom-right-radius: 0.5em; border-bottom-left-radius: 0.5em; color: white; text-align: center; font-size: 1em; box-shadow: rgb(165, 165, 165) 0.2em 0.2em 0.1em; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">1、这里输入标题</strong></span></section><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd15ada40009', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0008', 'WXBT', '<li>\r\n<p></p><section class=\"s0002 brcolor\" style=\"margin: 0px; padding: 0px; border-width: 1.1em 1em 1.1em 0px; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-top-color: rgb(0, 187, 236); border-right-color: transparent!important; border-bottom-color: rgb(0, 187, 236); display: inline-block; color: rgb(68, 68, 68); font-family: \'Microsoft YaHei\'; font-size: 13px; white-space: normal; height: 2em; max-width: 100%; line-height: 1em; box-sizing: border-box;\"><section style=\"margin: -1em 0px 0px; padding: 0.5em 1em; border: 0px; height: 2em; color: white; max-width: 100%; white-space: nowrap; text-overflow: ellipsis;\"> 这里输入标题</section></section><span style=\"color: rgb(68, 68, 68); font-family: \'Microsoft YaHei\'; font-size: 13px; line-height: 24px; background-color: rgb(255, 255, 255);\">&nbsp;</span><p></p><p><br></p>\r\n</li>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1633cc000a', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0009', 'WXBT', '<section style=\"margin: 0.8em 0px 0.5em; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: \'Microsoft YaHei\'; font-size: 13px; line-height: 24px; white-space: normal;\"><section class=\"s0002 bkcolor\" style=\"margin: 0px; padding: 0px; border: 0px; display: inline-block; width: 2.5em; height: 2.5em; border-top-left-radius: 2em; border-top-right-radius: 2em; border-bottom-right-radius: 2em; border-bottom-left-radius: 2em; vertical-align: top; text-align: center; background-color: rgb(0, 187, 236);\"><section style=\"margin: 0px; padding: 0px; border: 0px; display: table; width: 32px;\"><section style=\"margin: 0px; padding: 0px; border: 0px; display: table-cell; vertical-align: middle; font-weight: bolder; line-height: 1.3em; font-size: 2em; font-family: inherit; color: rgb(255, 255, 255);\">1</section></section></section><section style=\"margin: 0px 0px 0px 0.7em; padding: 0.3em 0px 0px; border: 0px; display: inline-block;\"><section class=\"s0003 bfcolor\" style=\"margin: 0px; padding: 0px; border: 0px; line-height: 1.4em; font-size: 1.5em; font-family: inherit; color: rgb(0, 187, 236);\">请输入标题</section></section></section><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd16f5ee000b', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0009', 'WXBT', '<section style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 1em; line-height: 24px; text-align: center; vertical-align: middle;\"><section class=\"s001 btcolor\" style=\"margin: 0px 1em; padding: 0px; border-width: 1.5em; border-style: solid; border-color: rgb(0, 187, 236) transparent; height: 0px;\"></section><section style=\"margin: -2.75em 1.65em; padding: 0px; border-width: 1.3em; border-style: solid; border-color: rgb(255, 255, 255) transparent; height: 0px;\"></section><section class=\"s001 btcolor\" style=\"margin: 0.45em 2.1em; padding: 0px; border-width: 1.1em; border-style: solid; border-color: rgb(0, 187, 236) transparent; height: 0px; vertical-align: middle;\"><section style=\"margin: -0.5em 0px 0px; padding: 0px 1em; border: 0px; font-size: 1.2em; line-height: 1em; color: white; max-height: 1em; overflow: hidden;\">请输入标题</section></section></section><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd19f245000c', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0010', 'WXBT', '<blockquote class=\"brcolor\" data-mce-style=\"margin: 5px 0px 0px; padding: 10px; max-width: 100%; orphans: 2; widows: 2; line-height: 25px; font-family: arial, helvetica, sans-serif; text-shadow: #225f87 0px 1px 0px; color: #ffffff; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; box-shadow: #999999 2px 2px 4px; border-left-width: 10px; border-left-style: solid; border-left-color: #fdd000; background-color: #373939; word-wrap: break-word !important;\" style=\"margin: 5px 0px 0px; padding: 10px; border-width: 0px 0px 0px 10px; border-left-style: solid; border-left-color: rgb(0, 187, 236); white-space: normal; max-width: 100%; line-height: 25px; font-size: 14px; font-family: arial, helvetica, sans-serif; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; color: rgb(255, 255, 255); box-shadow: rgb(153, 153, 153) 2px 2px 4px; text-shadow: rgb(34, 95, 135) 0px 1px 0px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(55, 57, 57);\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-family: 微软雅黑; font-size: 16px; word-wrap: break-word !important; box-sizing: border-box !important;\">1、这里输入标题</span></strong></blockquote><p><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-family: 微软雅黑; font-size: 16px; word-wrap: break-word !important; box-sizing: border-box !important;\"><br></span></strong></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1a87c5000d', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0011', 'WXBT', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; font-family: 微软雅黑; font-size: 13px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); line-height: 25px; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\"><strong style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(255, 255, 255); max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span class=\"bfcolor\" glowfont=\"display:inline-block; color:white; text-shadow:1px 0 4px #ff0000,0 1px 4px #ff0000,0 -1px 4px #ff0000,-1px 0 4px #ff0000;filter:glow(color=#ff0000,strength=3)\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; display: inline-block; text-shadow: rgb(0, 187, 236) 1px 0px 4px, rgb(0, 187, 236) 0px 1px 4px, rgb(0, 187, 236) 0px -1px 4px, rgb(0, 187, 236) -1px 0px 4px; word-wrap: break-word !important; box-sizing: border-box !important;\">请输入标题</span></strong><br style=\"margin: 0px; padding: 0px; border: 0px;\"></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1b1205000e', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0012', 'WXBT', '<p style=\"margin-top: 0px; margin-bottom: 20px; padding: 0px; border: 0px; font-family: 微软雅黑; font-size: 13px; white-space: normal; max-width: 100%; word-wrap: normal; min-height: 1em; color: rgb(62, 62, 62); line-height: 25px; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\"><span class=\"bkcolor\" style=\"margin: 0px; padding: 4px 10px; border: 0px; max-width: 100%; border-top-left-radius: 0.5em 4em; border-top-right-radius: 3em 1em; border-bottom-right-radius: 0.5em 2em; border-bottom-left-radius: 3em 1em; text-align: justify; color: rgb(255, 255, 255); font-family: 微软雅黑, sans-serif; box-shadow: rgb(165, 165, 165) 4px 4px 2px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">请输入标题</span></strong></strong></span></p><p><span class=\"bkcolor\" style=\"margin: 0px; padding: 4px 10px; border: 0px; max-width: 100%; border-top-left-radius: 0.5em 4em; border-top-right-radius: 3em 1em; border-bottom-right-radius: 0.5em 2em; border-bottom-left-radius: 3em 1em; text-align: justify; color: rgb(255, 255, 255); font-family: 微软雅黑, sans-serif; box-shadow: rgb(165, 165, 165) 4px 4px 2px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">我是段落内容</span></strong></strong></span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1c5c06000f', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0013', 'WXBT', '<h2 style=\"FONT-SIZE: 12px; HEIGHT: 32px; FONT-FAMILY: 微软雅黑, 宋体, tahoma, arial; WHITE-SPACE: normal; BORDER-BOTTOM: rgb(227,227,227) 1px solid; FONT-WEIGHT: normal; COLOR: rgb(62,62,62); PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 8px 0px 0px; LINE-HEIGHT: 18px; PADDING-RIGHT: 0px\"><span class=\"bfcolor\" style=\"FONT-SIZE: 16px; FONT-FAMILY: 微软雅黑, sans-serif !important; BORDER-BOTTOM: rgb(86,159,8) 2px solid; FLOAT: left;  COLOR: rgb(86,159,8); PADDING-BOTTOM: 3px; PADDING-TOP: 0px; PADDING-LEFT: 2px; DISPLAY: block; LINE-HEIGHT: 28px; PADDING-RIGHT: 2px\">请在这可输入标题</span><span class=\"bkcolor\" style=\"COLOR: rgb(255,255,255); PADDING-BOTTOM: 4px; PADDING-TOP: 4px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; BACKGROUND-COLOR: rgb(95,170,70);  border-radius: 80% 100% 90% 20%!important;margin-top: 0px;display: inline-block;margin-left: 5px;\">我是微信ID</span></h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1d1b420010', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0014', 'WXBT', '<h2 style=\"margin: 8px 0px 0px; padding: 0px; white-space: normal; font-size: 16px; font-weight: normal; max-width: 100%; color: rgb(62, 62, 62); height: 32px; line-height: 18px; font-family: 微软雅黑; border-bottom-color: rgb(227, 227, 227); border-bottom-width: 1px; border-bottom-style: solid; text-align: justify; word-wrap: break-word !important; \"><span class=\"brcolor\" style=\"margin: 0px; padding: 0px 2px 3px; max-width: 100%; color: rgb(0, 112, 192); line-height: 28px; border-bottom-color: rgb(0, 112, 192); border-bottom-width: 2px; border-bottom-style: solid; float: left; display: block; word-wrap: break-word !important;\"><span class=\"bkcolor brcolor\" style=\"margin: 0px 8px 0px 0px; padding: 4px 10px; max-width: 100%; border-top-left-radius: 80%!important; border-top-right-radius: 100%!important; border-bottom-right-radius: 90%!important; border-bottom-left-radius: 20%!important; color: rgb(255, 255, 255); word-wrap: break-word !important; background-color: rgb(0, 112, 192);\">1</span></span><span class=\"brcolor\" style=\"margin: 0px; padding: 0px 2px 3px; max-width: 100%; color: rgb(0, 112, 192); line-height: 28px; border-bottom-color: rgb(0, 112, 192); border-bottom-width: 2px; border-bottom-style: solid; float: left; display: block; word-wrap: break-word !important;\"><strong style=\"color: rgb(60, 117, 45); max-width: 100%; line-height: 28px; word-wrap: break-word !important;\">第一标题</strong></span></h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1d92190011', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0015', 'WXBT', '<h2 style=\"margin: 8px 0px 0px; padding: 0px; white-space: normal; font-size: 16px; font-weight: normal; max-width: 100%; color: rgb(62, 62, 62); height: 32px; line-height: 18px; font-family: 微软雅黑;text-align: justify; word-wrap: break-word !important; \"><span class=\"brcolor\" style=\"margin: 0px; padding: 0px 2px 3px; max-width: 100%; color: rgb(0, 112, 192); line-height: 28px;float: left; display: block; word-wrap: break-word !important;\"><span class=\"bkcolor brcolor\" style=\"margin: 0px 8px 0px 0px; padding: 4px 10px; max-width: 100%; border-top-left-radius: 80%!important; border-top-right-radius: 100%!important; border-bottom-right-radius: 90%!important; border-bottom-left-radius: 20%!important; color: rgb(255, 255, 255); word-wrap: break-word !important; background-color: rgb(0, 112, 192);\">1</span></span><span class=\"brcolor\" style=\"margin: 0px; padding: 0px 2px 3px; max-width: 100%; color: rgb(0, 112, 192); line-height: 28px;float: left; display: block; word-wrap: break-word !important;\"><strong style=\"color: rgb(60, 117, 45); max-width: 100%; line-height: 28px; word-wrap: break-word !important;\">第一标题</strong></span></h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1e70590012', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0016', 'WXBT', '<h2 style=\"FONT-SIZE: 16px; HEIGHT: 32px; FONT-FAMILY: 微软雅黑; WHITE-SPACE: normal; FONT-WEIGHT: normal; COLOR: rgb(62,62,62); PADDING-BOTTOM: 0px; TEXT-ALIGN: justify; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 8px 0px 0px; LINE-HEIGHT: 18px; PADDING-RIGHT: 0px\"><span style=\"FLOAT: left; COLOR: rgb(0,112,192); PADDING-BOTTOM: 3px; PADDING-TOP: 0px; PADDING-LEFT: 2px; DISPLAY: block; LINE-HEIGHT: 28px; PADDING-RIGHT: 2px\"><span class=\"bkcolor\" style=\"COLOR: rgb(255,255,255); PADDING-BOTTOM: 4px; PADDING-TOP: 4px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; BACKGROUND-COLOR: rgb(9,6,101); MARGIN-RIGHT: 8px\">2</span><strong class=\"ue_t\">请在这可输入标题</strong></span></h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1efb820013', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0017', 'WXBT', '<h2 style=\"FONT-SIZE: 16px; HEIGHT: 32px; FONT-FAMILY: 微软雅黑; WHITE-SPACE: normal; FONT-WEIGHT: normal; COLOR: rgb(62,62,62); PADDING-BOTTOM: 0px; TEXT-ALIGN: justify; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 8px 0px 0px; LINE-HEIGHT: 18px; PADDING-RIGHT: 0px\"><span style=\"FLOAT: left; COLOR: rgb(0,112,192); PADDING-BOTTOM: 3px; PADDING-TOP: 0px; PADDING-LEFT: 2px; DISPLAY: block; LINE-HEIGHT: 28px; PADDING-RIGHT: 2px\"><span class=\"bkcolor\" style=\"COLOR: rgb(255,255,255); PADDING-BOTTOM: 6px; PADDING-TOP: 6px; PADDING-LEFT: 15px; PADDING-RIGHT: 15px; BACKGROUND-COLOR: rgb(95,170,70); MARGIN-RIGHT: 8px; border-radius: 90% 100% 90% 100%!important;\">请在这可输入标题</span></span></h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd1fbb800014', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0018', 'WXBT', '<p style=\"margin-top: 0px; margin-bottom: 12px; padding: 0px; color: rgb(51, 51, 51); font-family: \'Microsoft Yahei\'; font-size: 14px; line-height: 25px; white-space: normal; text-align: center; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; max-width: 100%; line-height: 0px;\">?</span><strong style=\"color: rgb(102, 102, 102); max-width: 100%; line-height: 0px; font-family: 微软雅黑; background-color: rgb(250, 250, 240);\"><span class=\"bkcolor\" style=\"margin: 0px; padding: 4px 10px; max-width: 100%; color: rgb(255, 255, 255); border-top-left-radius: 2em 0.5em!important; border-top-right-radius: 1em 3em!important; border-bottom-right-radius: 4em 0.5em!important; border-bottom-left-radius: 1em 3em!important; box-shadow: rgb(165, 165, 165) 4px 4px 2px; background-color: #0070c0;\">欢迎使用微信编辑器！</span></strong></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd20447f0015', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0019', 'WXBT', '<p style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; WHITE-SPACE: pre-wrap; WORD-SPACING: 0px; TEXT-TRANSFORM: none; COLOR: rgb(255,255,255); PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; MIN-HEIGHT: 1.5em; LETTER-SPACING: normal; PADDING-RIGHT: 0px; TEXT-INDENT: 0px; -webkit-text-stroke-width: 0px; font-size-adjust: none; font-stretch: normal\"><strong><span class=\"bkcolor\" style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%;COLOR: rgb(255,255,255); PADDING-BOTTOM: 4px; PADDING-TOP: 4px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; BACKGROUND-COLOR: rgb(34,34,34); MARGIN-RIGHT: 8px; border-radius: 5px!important\">请在这可输入标题</span></strong></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd225cf50016', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0020', 'WXBT', '<p style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; WHITE-SPACE: pre-wrap; WORD-SPACING: 0px; TEXT-TRANSFORM: none; COLOR: rgb(255,255,255); PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; MIN-HEIGHT: 1.5em; LETTER-SPACING: normal; PADDING-RIGHT: 0px; TEXT-INDENT: 0px; -webkit-text-stroke-width: 0px; font-size-adjust: none; font-stretch: normal\"><strong><span class=\"bkcolor\" style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; FONT-FAMILY: 微软雅黑,Microsoft YaHei; COLOR: rgb(255,255,255); PADDING-BOTTOM: 4px; PADDING-TOP: 4px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; BACKGROUND-COLOR: rgb(255,3,0); MARGIN-RIGHT: 8px\">请在这可输入标题</span></strong></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd22f9ec0017', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0021', 'WXBT', '<h2 style=\"FONT-SIZE: 12px; HEIGHT: 32px; FONT-FAMILY: 微软雅黑, 宋体, tahoma, arial; WHITE-SPACE: normal; BORDER-BOTTOM: rgb(227,227,227) 1px solid; FONT-WEIGHT: normal; COLOR: rgb(62,62,62); PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 8px 0px 0px; LINE-HEIGHT: 18px; PADDING-RIGHT: 0px\"><span class=\"bfcolor\" style=\"FONT-SIZE: 14px; FONT-FAMILY: 微软雅黑, sans-serif !important; BORDER-BOTTOM: rgb(86,159,8) 2px solid; FLOAT: left; FONT-WEIGHT: bold; COLOR: rgb(86,159,8); PADDING-BOTTOM: 3px; PADDING-TOP: 0px; PADDING-LEFT: 2px; DISPLAY: block; LINE-HEIGHT: 28px; PADDING-RIGHT: 2px\">请在这可输入标题</span></h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd23719c0018', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0022', 'WXBT', '<h2 class=\"bfcolor\" style=\"FONT-SIZE: 16px; FONT-FAMILY: 微软雅黑; BORDER-RIGHT-WIDTH: 0px; WHITE-SPACE: normal; BORDER-BOTTOM-WIDTH: 0px; COLOR: green; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 10px; MARGIN: 5px 0px 13px; BORDER-LEFT: rgb(111,168,51) 5px solid; LINE-HEIGHT: 25px; PADDING-RIGHT: 10px; BORDER-TOP-WIDTH: 0px; -webkit-font-smoothing: antialiased\">请在这可输入标题</h2><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd26032d0019', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0002', 'WXNRQ', '<blockquote class=\"brcolor\" style=\"white-space: normal; border-left-color: rgb(255, 102, 0); border-left-width: 10px; border-left-style: solid; border-right-color: rgb(255, 102, 0); border-right-width: 10px; border-right-style: solid; padding: 10px; margin: 5px 0px 0px; font-family: arial, helvetica, sans-serif; max-width: 100%; font-size: 14px; box-shadow: rgb(153, 153, 153) 2px 1px 4px; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-left-radius: 4px; border-bottom-right-radius: 4px; box-sizing: border-box !important; word-wrap: break-word !important; background-color: rgba(255, 255, 255, 0.388235);\"><p>欢迎使用微信编辑器！</p></blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd270e53001a', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0003', 'WXNRQ', '<section style=\"margin: 0px; padding: 0px; border: 0px; font-family: 微软雅黑; font-size: 13px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); line-height: 25px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\">\r\n<section style=\"margin: 0px 0px 0px 1em; padding: 0px; border: 0px; max-width: 100%; line-height: 1.4em; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n<span class=\"bkcolor\" style=\"margin: 0px; padding: 0.2em 0.5em; border: 0px; max-width: 100%; font-size: 0.8em; font-family: inherit; border-top-left-radius: 0.3em; border-top-right-radius: 0.3em; border-bottom-right-radius: 0.3em; border-bottom-left-radius: 0.3em; color: rgb(255, 255, 255); text-align: center; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\">这输入标题</span>&nbsp;<span style=\"margin: 0px 0px 0px 0.3em; padding: 0.2em 0.5em; border: 0px; max-width: 100%; height: 1.2em; border-top-left-radius: 1.2em; border-top-right-radius: 1.2em; border-bottom-right-radius: 1.2em; border-bottom-left-radius: 1.2em; color: rgb(255, 255, 255); font-size: 0.8em; line-height: 1.2em; font-family: inherit; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(204, 204, 204);\">编辑器</span>\r\n</section>\r\n<section style=\"margin: -0.7em 0px 0px; padding: 1.4em 1em 1em; border: 1px solid rgb(192, 200, 209); max-width: 100%; border-top-left-radius: 0.3em; border-top-right-radius: 0.3em; border-bottom-right-radius: 0.3em; border-bottom-left-radius: 0.3em; color: rgb(51, 51, 51); font-size: 1em; font-family: inherit; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(239, 239, 239);\">\r\n可在这输入内容，微信编辑器，微信编辑首选。\r\n</section>\r\n</section>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd27abdb001b', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0004', 'WXNRQ', '<fieldset style=\"margin: 0.5em 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; white-space: normal; max-width: 100%; line-height: 25px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\">\r\n<section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; box-sizing: border-box; height: 1em; word-wrap: break-word !important;\">\r\n<section class=\"brcolor\" style=\"margin: 0px; padding: 0px; border-width: 0.4em 0px 0px 0.4em; border-top-style: solid; border-left-style: solid; border-color: rgb(0, 187, 236); max-width: 100%; height: 16px; width: 1.5em; float: left; word-wrap: break-word !important; box-sizing: border-box !important;\"></section>\r\n<section class=\"brcolor\" style=\"margin: 0px; padding: 0px; border-width: 0.4em 0.4em 0px 0px; border-top-style: solid; border-right-style: solid; border-color: rgb(0, 187, 236); max-width: 100%; height: 16px; width: 1.5em; float: right; word-wrap: break-word !important; box-sizing: border-box !important;\"></section>\r\n</section>\r\n<section class=\"brcolor\" style=\"margin: -0.8em 0.1em -0.8em 0.2em; padding: 0.8em; border: 1px solid rgb(0, 187, 236); max-width: 100%; box-sizing: border-box; border-top-left-radius: 0.3em; border-top-right-radius: 0.3em; border-bottom-right-radius: 0.3em; border-bottom-left-radius: 0.3em; word-wrap: break-word !important;\">\r\n<section style=\"margin: 0px; padding: 0px; border: none; max-width: 100%; word-wrap: break-word; line-height: 1.4; word-break: break-all; font-size: 1em; font-family: inherit; text-align: inherit; text-decoration: inherit; box-sizing: border-box !important; background-image: none;\">\r\n可在这输入内容， 微信编辑器，微信编辑首选。\r\n</section>\r\n</section>\r\n<section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; box-sizing: border-box; height: 1em; word-wrap: break-word !important;\">\r\n<section class=\"brcolor\" style=\"margin: -3px 0px 0 0; padding: 0px; border-width: 0px 0px 0.4em 0.4em; border-bottom-style: solid; border-left-style: solid; border-color: rgb(0, 187, 236); max-width: 100%; height: 16px; width: 1.5em; float: left; word-wrap: break-word !important; box-sizing: border-box !important;\"></section>\r\n<section class=\"brcolor\" style=\"margin: -3px 0px 0 0; padding: 0px; border-width: 0px 0.4em 0.4em 0px; border-right-style: solid; border-bottom-style: solid; border-color: rgb(0, 187, 236); max-width: 100%; height: 16px; width: 1.5em; float: right; word-wrap: break-word !important; box-sizing: border-box !important;\"></section>\r\n</section>\r\n</fieldset>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd284a3a001c', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0005', 'WXNRQ', '<blockquote class=\"brcolor\" style=\"margin: 0px; padding: 10px; border: 6px double rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal; word-break: break-all; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n<p class=\"ue_t\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px;\">\r\n可在这输入内容， 微信编辑器，微信编辑首选。\r\n</p>\r\n</blockquote>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd2bfaed001d', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0006', 'WXNRQ', '<fieldset style=\"margin: 0px 0px 0px 10px; padding: 0px; border: 0px; font-family: 微软雅黑; font-size: 13px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); line-height: 25px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\">\r\n<section style=\"margin: 0px 0px 0px -0.5em; padding: 0px; border: 0px; max-width: 100%; line-height: 1.4em; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n<section class=\"bkcolor\" style=\"margin: 0px; padding: 0.2em 0.5em; border: 0px; display: inline-block; max-width: 100%; border-top-left-radius: 0.3em; border-top-right-radius: 0.3em; border-bottom-right-radius: 0.3em; border-bottom-left-radius: 0.3em; color: white; font-size: 0.8em; text-align: center; -webkit-transform: rotateZ(-10deg); -webkit-transform-origin: 0% 100%; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\">\r\n我的观点\r\n</section>\r\n</section>\r\n<section class=\"brcolor\" style=\"margin: -1.5em 0px 0px; padding: 0px; border: 1px solid rgb(0, 187, 236); max-width: 100%; font-size: 1em; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n<section style=\"margin: 0px; padding: 1.4em 1em 1em; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-size: 1em; line-height: 1.2; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(253, 176, 0); word-wrap: break-word !important; box-sizing: border-box !important;\"></span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-size: 1em; line-height: 1.2; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(51, 51, 51); word-wrap: break-word !important; box-sizing: border-box !important;\">可在这输入内容， 微信编辑器，微信编辑首选。</span>\r\n</section>\r\n<p>\r\n<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-size: 1em; line-height: 1.2; font-family: inherit; text-align: inherit; text-decoration: inherit; color: rgb(51, 51, 51); word-wrap: break-word !important; box-sizing: border-box !important;\"><br></span>\r\n</p>\r\n</section>\r\n</fieldset>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd2d5b54001e', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0007', 'WXNRQ', '<p class=\"brcolor\" style=\"margin:15px;padding:20px;border:1px dotted #00bbec;font-family:微软雅黑;max-width:100%;word-wrap:normal;min-height:1.5em;white-space:pre-wrap;text-align:justify;color:#494429;line-height:2em;font-size:14px;border-bottom-right-radius:15px;border-bottom-left-radius:10px;box-sizing:border-box!important;background-color:#fff\"><span style=\"margin:0;padding:0;border:0;max-width:100%;color:#00bbec;word-wrap:break-word!important;box-sizing:border-box!important\"><strong style=\"margin:0;padding:0;border:0;max-width:100%;word-wrap:break-word!important;box-sizing:border-box!important\">请输入内容</strong></span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd2df3a0001f', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0008', 'WXNRQ', '<fieldset class=\"comment_quote\" style=\"margin: 5px 0px; padding: 5px; border: 1px solid rgb(204, 204, 204); font-family: 微软雅黑; font-size: 13px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); line-height: 25px; box-shadow: rgb(165, 165, 165) 5px 5px 2px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(248, 247, 245);\">\r\n<legend style=\"margin: 0px; padding: 0px; border-width: 0px; max-width: 100%; line-height: 1.8em; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n<strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(89, 89, 89); font-size: 18px; line-height: 42.6666679382324px; text-align: center; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;\"><span class=\"bfcolor\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(0, 187, 236); text-shadow: rgb(201, 201, 201) 5px 3px 1px; word-wrap: break-word !important; box-sizing: border-box !important;\">精彩内容</span></strong>\r\n</legend>\r\n<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;\">\r\n请输入内容<br style=\"margin: 0px; padding: 0px; border: 0px;\">\r\n</p>\r\n</fieldset>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd2ecdbc0020', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0009', 'WXNRQ', '<blockquote style=\"margin: 0.2em; padding: 10px; border: 3px solid rgb(201, 201, 201); border-image-source: none; font-family: 微软雅黑; font-size: 13px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); line-height: 25.6000003814697px; box-shadow: rgb(170, 170, 170) 0px 0px 10px; -webkit-box-shadow: rgb(170, 170, 170) 0px 0px 10px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\">\r\n<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; box-sizing: border-box !important;\">\r\n<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; line-height: 25.6000003814697px; word-wrap: break-word !important; box-sizing: border-box !important;\">可在这输入内容， 微信编辑器，微信编辑首选。</span>\r\n</p>\r\n<p>\r\n<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; line-height: 25.6000003814697px; word-wrap: break-word !important; box-sizing: border-box !important;\"><br></span>\r\n</p>\r\n</blockquote>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd2f8c6e0021', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0010', 'WXNRQ', '<blockquote class=\"bcolor\" style=\"white-space: normal; border: 1px solid rgb(225, 225, 225); margin: 5px 0px; padding: 5px 10px; overflow: hidden; font-size: 14px; line-height: 24px; font-family: 微软雅黑; color: rgb(51, 51, 51); background: rgb(241, 241, 241);\"><p style=\"border: 0px; padding: 0px; letter-spacing: 0.25px; line-height: 28px;\"><span style=\"border: 0px; margin: 0px; padding: 0px; color: rgb(127, 127, 127);\">欢迎使用微信编辑器！</span></p></blockquote><p style=\"white-space: normal;\"><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd30006f0022', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0011', 'WXNRQ', '<p class=\"brcolor\" style=\"border: 1px solid #cdcdcd;box-shadow: 0 3px 6px #999999;-webkit-border-radius: 12px!important;-moz-border-radius: 12px!important;border-radius: 12px!important;width: auto;background-color:#FFFFFF;padding: 10px;background: -webkit-gradient(linear, left top, left bottom,from(#FFFFFF), to(#FFFFFF));background-image: -mozlinear-gradient(top, #FFFFFF 0%, #FFFFFF 100%);margin: 0px auto; line-height: 1.8em; min-height: 1.5em;\"><span style=\"FONT: medium/21px 微软雅黑;letter-spacing: 0.25px; line-height: 25.200000762939453px; text-indent: 28px;\">直接选择需要的样式拷贝到需要的地方，修改其中的文字即可。</span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd307eac0023', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0012', 'WXNRQ', '<blockquote class=\"bkcolor\" style=\"BORDER-LEFT-WIDTH: 2px; BORDER-RIGHT-WIDTH: 2px; WHITE-SPACE: normal; BORDER-BOTTOM-WIDTH: 2px; WORD-SPACING: 0px; TEXT-TRANSFORM: none; COLOR: rgb(255,255,255); PADDING-BOTTOM: 10px; PADDING-TOP: 10px; FONT: 16px/24px 微软雅黑; PADDING-LEFT: 20px; MARGIN: 0px; LETTER-SPACING: normal; PADDING-RIGHT: 20px; BORDER-TOP-WIDTH: 2px; BACKGROUND-COLOR: rgb(227,108,9); TEXT-INDENT: 0px; border-radius: 3px; -webkit-text-stroke-width: 0px; font-size-adjust: none; font-stretch: normal;border:none!important;\"><p style=\"margin:0;\">欢迎使用微信编辑器！</p></blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd30fc950024', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0013', 'WXNRQ', '<blockquote class=\"brcolor\" style=\"BORDER-TOP: rgb(255,0,0) 3px dashed; BORDER-RIGHT: rgb(255,0,0) 3px dashed; BORDER-BOTTOM: rgb(255,0,0) 3px dashed; PADDING-BOTTOM: 15px; PADDING-TOP: 15px; PADDING-LEFT: 15px; MARGIN: 0px; BORDER-LEFT: rgb(255,0,0) 3px dashed; PADDING-RIGHT: 15px;border-radius:5px!important;\"><p style=\"margin:0;\">欢迎使用微信编辑器！</p></blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd3178710025', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0014', 'WXNRQ', '<blockquote class=\"brcolor\" style=\"WORD-WRAP: break-word; FONT-SIZE: 14px; MAX-WIDTH: 100%; VERTICAL-ALIGN: baseline; PADDING-BOTTOM: 15px; PADDING-TOP: 15px; PADDING-LEFT: 25px; MARGIN: 0px; BORDER-LEFT: rgb(102,102,102) 10px solid; DISPLAY: block; LINE-HEIGHT: 24px; TOP: 0px; PADDING-RIGHT: 25px; BACKGROUND-COLOR: rgb(238,238,238);\">欢迎使用微信编辑器！</blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd31daf00026', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0015', 'WXNRQ', '<blockquote class=\"brcolor\" style=\"border-top:#ccc 1px solid;border-right:#ccc 1px solid;background-image:url(http://www.weituibao.com/img/editor/content/1.png.pagespeed.ce.PhEOnHNGja.png);background-repeat:no-repeat;border-bottom:#ccc 1px solid;background-position:10px 11px;padding-bottom:15px;padding-top:20px;padding-left:48px;margin:0px;border-left:#ccc 1px solid;line-height:1.5;padding-right:15px;border-radius:5px\">欢迎使用微信编辑器！</blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd324f5e0027', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0016', 'WXNRQ', '<blockquote class=\"brcolor\" style=\"border-top:#ccc 1px solid;border-right:#ccc 1px solid;background-image:url(http://www.weituibao.com/img/editor/content/2.gif.pagespeed.ce.VtjZG5Eyg2.gif);background-repeat:no-repeat;border-bottom:#ccc 1px solid;background-position:10px 11px;padding-bottom:15px;padding-top:20px;padding-left:48px;margin:0px;border-left:#ccc 1px solid;line-height:1.5;padding-right:15px;border-radius:5px\">欢迎使用微信编辑器！</blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd3313660028', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0017', 'WXNRQ', '<fieldset class=\"brcolor\" style=\"text-align: -webkit-auto; white-space: normal; margin: 0px; padding: 5px 15px; border: 1px solid rgb(127, 63, 191); max-width: 100%; color: rgb(62, 62, 62); font-family: 宋体; line-height: 24px; orphans: 2; widows: 2; box-shadow: rgb(165, 165, 165) 1px 2px 1px; border-top-left-radius: 1em!important; border-top-right-radius: 1em!important; border-bottom-right-radius: 1em!important; border-bottom-left-radius: 1em!important; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(253, 253, 253);\"><legend style=\"padding: 0px; max-width: 100%; margin: 0px; color: rgb(0, 0, 0); word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"max-width: 100%; margin: 0px; padding: 0px; color: rgb(165, 165, 165); font-family: arial, helvetica, sans-serif; word-wrap: break-word !important; box-sizing: border-box !important;\"><strong style=\"max-width: 100%; color: rgb(102, 102, 102); word-wrap: break-word !important; box-sizing: border-box !important;\"><span class=\"bkcolor\" style=\"max-width: 100%; margin: 0px 8px 0px 0px; padding: 4px 10px; border-top-left-radius: 5px!important; border-top-right-radius: 5px!important; border-bottom-right-radius: 5px!important; border-bottom-left-radius: 5px!important; color: rgb(255, 251, 240); word-wrap: break-word !important; box-sizing: border-box !important; background: rgb(127, 63, 191);font-size: 14px;\"></span></strong></span></legend><p style=\"margin-top: 0px; margin-bottom: 12px; max-width: 100%; min-height: 1.5em; white-space: pre-wrap; padding: 0px; line-height: 2em; word-wrap: break-word !important; box-sizing: border-box !important;\"><br style=\"max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><br style=\"max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"></p></fieldset><p style=\"white-space: normal;\"><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd337dc70029', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0018', 'WXNRQ', '<fieldset class=\"brcolor\" style=\"border-radius: 0.3em!important;white-space: normal; margin: 0px; padding: 5px; border: 1px solid rgb(204, 204, 204); max-width: 100%; color: rgb(62, 62, 62); line-height: 24px; text-align: justify; font-family: 微软雅黑; box-shadow: rgb(165, 165, 165) 5px 5px 2px; background-color: rgb(250, 250, 240);\"><legend style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0); max-width: 100%; text-align: center;\"><strong style=\"max-width: 100%;\"><strong style=\"max-width: 100%; color: rgb(102, 102, 102); line-height: 20px; background-color: rgb(255, 255, 245);\"><span class=\"bkcolor\" style=\"max-width: 100%; padding: 4px 10px; border-top-left-radius: 0.5em 4em!important; border-top-right-radius: 3em 1em!important; border-bottom-right-radius: 0.5em 2em!important; border-bottom-left-radius: 3em 1em!important; text-align: justify; color: white; box-shadow: rgb(165, 165, 165) 4px 4px 2px; background-color: red;\">公告通知</span></strong></strong></legend><legend style=\"margin: 0px; padding: 0px; color: rgb(0, 0, 0); max-width: 100%; text-align: center;\"><span style=\"max-width: 100%; color: rgb(112, 48, 160); text-align: justify;\"></span></legend><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; max-width: 100%; min-height: 1.5em; line-height: 2em;\">各位小伙伴们，当小伙伴们看到这个排版技巧的时候，说明我们有缘、首先再开始之前，请使用<span style=\"color: rgb(255, 0, 0);\"><strong>谷歌浏览器</strong></span>，才能达到和手机端一致的效果，其次部分效果，在其它浏览器中会无效！以免耽误大家宝贵时间</p></fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd341735002a', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0019', 'WXNRQ', '<fieldset class=\"brcolor\" style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; BORDER-TOP: rgb(192,80,77) 1px dotted; FONT-FAMILY: 宋体; BORDER-RIGHT: rgb(192,80,77) 1px dotted; BORDER-BOTTOM: rgb(192,80,77) 1px dotted; PADDING-BOTTOM: 20px; PADDING-TOP: 20px; PADDING-LEFT: 20px; MIN-HEIGHT: 1.5em; BORDER-LEFT: rgb(192,80,77) 1px dotted; LINE-HEIGHT: 2em; PADDING-RIGHT: 20px; border-bottom-right-radius: 15px; border-bottom-left-radius: 10px\">\r\n<legend style=\"max-width: 100%; font-family: 微软雅黑; padding: 0px; text-align: center; margin: 0px; word-wrap: break-word !important;border:none;\">\r\n<p class=\"bkcolor\" style=\"word-wrap: normal; font-size: 14px; max-width: 100%; word-break: normal; color: rgb(255, 255, 255); padding: 0px 20px 4px; margin-top: 0px; margin-bottom: 0px; min-height: 1.5em; line-height: 2em; border-bottom-right-radius: 100%!important; border-bottom-left-radius: 100%!important; background-color: rgb(192, 80, 77);\"><strong class=\"ue_t\" style=\"MAX-WIDTH: 100%\">请输入标题</strong></p></legend><p style=\"word-wrap: normal; max-width: 100%; word-break: normal; padding: 0px; margin-top: 0px; margin-bottom: 0px; min-height: 1.5em; line-height: 2em;\"><span class=\"ue_t\" style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; LINE-HEIGHT: 2em;margin:0;\">欢迎使用微信编辑器！</span>\r\n</p>\r\n</fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd349ce7002b', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0020', 'WXNRQ', '<fieldset class=\"brcolor\" style=\"BORDER-TOP: rgb(0,166,191) 1px solid; BORDER-RIGHT: rgb(0,166,191) 1px solid; BORDER-BOTTOM: rgb(0,166,191) 1px solid; PADDING-BOTTOM: 5px; PADDING-TOP: 5px; PADDING-LEFT: 5px; MARGIN: 0px; BORDER-LEFT: rgb(0,166,191) 1px solid; PADDING-RIGHT: 5px\"><legend style=\"MARGIN: 0px 10px\"><span class=\"bkcolor\" style=\"FONT-SIZE: 14px; FONT-WEIGHT: bold; COLOR: rgb(255,255,255); PADDING-BOTTOM: 3px; PADDING-TOP: 3px; PADDING-LEFT: 6px; PADDING-RIGHT: 6px; BACKGROUND-COLOR: rgb(0,166,191); border-radius: 5px\"><span id=\"_baidu_bookmark_start_69\" style=\"DISPLAY: none; LINE-HEIGHT: 0px\">?</span>输入标题</span></legend><blockquote style=\"PADDING-BOTTOM: 10px; PADDING-TOP: 10px; PADDING-LEFT: 10px; MARGIN: 0px; PADDING-RIGHT: 10px;border:none;\"><p style=\"margin:0;\">欢迎使用微信编辑器！</p></blockquote></fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd352dcb002c', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0021', 'WXNRQ', '<fieldset class=\"brcolor\" style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; BORDER-TOP: rgb(204,204,204) 1px solid; BORDER-RIGHT: rgb(204,204,204) 1px solid; WHITE-SPACE: normal; WORD-SPACING: 0px; BORDER-BOTTOM: rgb(204,204,204) 1px solid; TEXT-TRANSFORM: none; COLOR: rgb(62,62,62); PADDING-BOTTOM: 15px; PADDING-TOP: 15px; FONT: 16px/24px 宋体; PADDING-LEFT: 15px; MARGIN: 0px; BORDER-LEFT: rgb(204,204,204) 1px solid; ORPHANS: 2; WIDOWS: 2; LETTER-SPACING: normal; PADDING-RIGHT: 15px; BACKGROUND-COLOR: rgb(250,250,240); TEXT-INDENT: 0px; -webkit-text-stroke-width: 0px; font-size-adjust: none; font-stretch: normal\"><legend style=\"WORD-WRAP: break-word !important; FONT-SIZE: medium; MAX-WIDTH: 100%; COLOR: rgb(0,0,0); PADDING-BOTTOM: 0px; TEXT-ALIGN: center; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px\"><span style=\"WORD-WRAP: break-word !important; FONT-SIZE: 14px; MAX-WIDTH: 100%; FONT-FAMILY: 微软雅黑\"><strong style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; COLOR: rgb(102,102,102)\"><span class=\"bkcolor\" style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; COLOR: rgb(255,255,255); PADDING-BOTTOM: 4px; PADDING-TOP: 4px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; BACKGROUND-COLOR: rgb(0,112,192); border-radius: 2em 1em 4em / 0.5em 3em\"><span id=\"_baidu_bookmark_start_20\" style=\"DISPLAY: none; LINE-HEIGHT: 0px\">?</span>欢迎使用微信编辑器！</span></strong></span></legend><span style=\"WORD-WRAP: break-word !important; MAX-WIDTH: 100%; WHITE-SPACE: normal; WORD-SPACING: 0px; TEXT-TRANSFORM: none; COLOR: rgb(89,89,89); FONT: 14px/24px 微软雅黑, Microsoft YaHei; ORPHANS: 2; WIDOWS: 2; LETTER-SPACING: normal; BACKGROUND-COLOR: rgb(250,250,240); TEXT-INDENT: 0px; -webkit-text-stroke-width: 0px; font-size-adjust: none; font-stretch: normal;\">我是IOS7风格，没阴影。</span></fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd35bbe4002d', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0022', 'WXNRQ', '<li>\r\n<blockquote class=\"bkcolor\" style=\"BORDER-LEFT-WIDTH: 0px; MAX-WIDTH: 100%; BORDER-RIGHT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-WEIGHT: bold; COLOR: rgb(255,255,255); PADDING-BOTTOM: 5px; TEXT-ALIGN: left; PADDING-TOP: 5px; PADDING-LEFT: 15px; MARGIN: 0px; LINE-HEIGHT: 25px; PADDING-RIGHT: 15px; BORDER-TOP-WIDTH: 0px; BACKGROUND-COLOR: rgb(255,0,123); border-radius: 15px 15px 0 0\"><span class=\"ue_t\">在这输入标题</span></blockquote><blockquote class=\"brcolor\" style=\"MAX-WIDTH: 100%; BORDER-TOP: rgb(255,0,123) 1px solid; BORDER-RIGHT: rgb(255,0,123) 1px solid; BORDER-BOTTOM: rgb(255,0,123) 1px solid; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; PADDING-LEFT: 15px; MARGIN: 0px; BORDER-LEFT: rgb(255,0,123) 1px solid; LINE-HEIGHT: 25px; PADDING-RIGHT: 15px; border-radius: 0 0 15px 15px\"><p class=\"ue_t\">欢迎使用微信编辑器！</p></blockquote><p><br></p>\r\n</li>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd36377e002e', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0023', 'WXNRQ', '<blockquote class=\"bkcolor\" style=\"BORDER-LEFT-WIDTH: 0px; MAX-WIDTH: 100%; BORDER-RIGHT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; FONT-WEIGHT: bold; COLOR: rgb(255,255,255); PADDING-BOTTOM: 5px; TEXT-ALIGN: left; PADDING-TOP: 5px; PADDING-LEFT: 15px; MARGIN: 0px; DISPLAY: inline-block; LINE-HEIGHT: 25px; PADDING-RIGHT: 15px; BORDER-TOP-WIDTH: 0px; BACKGROUND-COLOR: rgb(255,0,123); border-radius: 15px 15px 0 0\"><span class=\"ue_t\">请在这输入标题</span></blockquote><blockquote class=\"brcolor\" style=\"MAX-WIDTH: 100%; BORDER-TOP: rgb(255,0,123) 1px solid; BORDER-RIGHT: rgb(255,0,123) 1px solid; BORDER-BOTTOM: rgb(255,0,123) 1px solid; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; PADDING-LEFT: 15px; MARGIN: 0px; BORDER-LEFT: rgb(255,0,123) 1px solid; LINE-HEIGHT: 25px; PADDING-RIGHT: 15px\"><p class=\"ue_t\">欢迎使用微信编辑器！</p></blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbce9c2014dbd37aed6002f', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '互推0001', 'WXHTZH', '<fieldset class=\"comment_quote\" style=\"white-space: normal; margin: 0px; padding: 5px; border: 1px solid rgb(204, 204, 204); color: rgb(51, 51, 51); font-size: 14px; font-family: 宋体; widows: 2; line-height: 24px; orphans: 2; background-color: rgb(248, 247, 245);\"><legend style=\"margin: 0px; padding: 0px;font-size:14px;\"><span style=\"margin: 0px; padding: 0px; font-family: arial, helvetica, sans-serif;\"><strong style=\"color: rgb(102, 102, 102);\"><span class=\"bkcolor\" style=\"margin: 0px 8px 0px 0px; padding: 4px 10px; color: rgb(255, 255, 255); border-top-left-radius: 5px!important; border-top-right-radius: 5px!important; border-bottom-right-radius: 5px!important; border-bottom-left-radius: 5px!important; background-color: rgb(191, 0, 0);\"></span></strong><strong style=\"color: rgb(60, 117, 45);\"><span style=\"margin: 0px; padding: 0px; color: rgb(192, 0, 0);\">ID:wxid</span></strong></span></legend><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; min-height: 1.5em; word-wrap: break-word; word-break: normal; line-height: 2em; font-family: 微软雅黑; color: rgb(62, 62, 62); font-size: 12px;\"><span style=\"margin: 0px; padding: 0px; font-family: 宋体; color: rgb(217, 150, 148); font-size: 14px;\"><strong style=\"color: rgb(60, 117, 45);\"><span style=\"margin: 0px; padding: 0px; color: rgb(127, 127, 127); font-family: 微软雅黑; letter-spacing: 0.25px; line-height: 28px;\">直接选择需要的样式拷贝到需要的地方，修改其中的文字即可。</span><span style=\"margin: 0px; padding: 0px; color: rgb(127, 127, 127); font-family: 微软雅黑; letter-spacing: 0.25px; line-height: 28px;\">如果需要跟换颜色或者有其他需求</span></strong></span></p></fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd534c520000', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '互推0002', 'WXHTZH', '<fieldset class=\"brcolor\" style=\"margin: 0px; padding: 5px 15px; border-width: 1px 0px; border-style: solid; border-color: rgb(0, 187, 236); border-image-source: none; font-family: 微软雅黑; line-height: 24px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); font-size: 14px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\"><legend class=\"brcolor\" style=\"margin: 0px; padding: 4px 10px; border: 1px solid rgb(0, 187, 236); border-image-source: none; max-width: 100%; color: rgb(34, 34, 34); font-size: 16px; word-wrap: break-word !important; box-sizing: border-box !important;\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">如何关注</strong></legend><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;\">①复制“微信号或ID”，在“添加朋友”中粘贴搜索号码关注。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;\">②点击微信右上角的“+”，会出现“添加朋友”，进入“查找公众号”，输入以下公众号的名字，即可找到。</p></fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd541d300001', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '互推0003', 'WXHTZH', '<fieldset class=\"brcolor\" style=\"margin: 0px; padding: 5px; border: 1px solid rgb(0, 187, 236); font-family: 微软雅黑; line-height: 24px; white-space: normal; color: rgb(51, 51, 51); font-size: 12px; background-color: rgb(248, 247, 245);\"><legend style=\"margin: 0px 10px; padding: 0px; border-width: 0px;\"><span class=\"bkcolor\" style=\"margin: 0px; padding: 5px 10px; border: 0px; color: rgb(255, 255, 255); font-weight: bold; font-size: 14px; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; background-color: rgb(0, 187, 236);\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">点击上面微信号关注我</span><img data-s=\"300,640\" data-ratio=\"1.35\" data-w=\"20\" src=\"http://mmbiz.qlogo.cn/mmbiz/mj9u1OBZRqP8EvePIzqrRIHCHOzYM4ngPFYKXVcefcyCibmHkY6wA3BuiaLFOVAhuLTlfBzaD6MO5CT2DvJsy6JA/0\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; height: auto !important; word-wrap: break-word !important; box-sizing: border-box !important; visibility: visible !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px;\">关注我哟</span></span></legend><blockquote style=\"margin: 0px; padding: 0px; border: 0px;\"><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(255, 192, 0); font-size: 14px; word-wrap: break-word !important; box-sizing: border-box !important;\"></span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(255, 192, 0); font-size: 14px; word-wrap: break-word !important; box-sizing: border-box !important;\">?&nbsp;</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(127, 127, 127); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;\">定期推送帐号</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(255, 192, 0); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;\">本土文化</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(127, 127, 127); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">，<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(0, 176, 80); word-wrap: break-word !important; box-sizing: border-box !important;\">同城活动</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">，<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(112, 48, 160); word-wrap: break-word !important; box-sizing: border-box !important;\">吃喝玩乐</span>，<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(0, 176, 240); word-wrap: break-word !important; box-sizing: border-box !important;\">资讯八卦</span>，<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(146, 208, 80); word-wrap: break-word !important; box-sizing: border-box !important;\">商家优惠</span></span></span>等诸多优质内容，<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">最接地气</span>、<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">重服务</span>的本地微信平台！关注我们妥妥没错！（广告合作：186XXXXXXXX）</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(127, 127, 127); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;\"></span></p><p><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(127, 127, 127); line-height: 28px; white-space: pre-wrap; word-wrap: break-word !important; box-sizing: border-box !important;\"><br></span></p></blockquote></fieldset><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd54d0b60002', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '互推0004', 'WXHTZH', '<blockquote class=\"bkcolor\" style=\"margin: 0px; padding: 5px 20px; border: 2px rgb(66, 249, 255); font-family: 微软雅黑; line-height: 24px; white-space: normal; max-width: 100%; color: rgb(255, 255, 255); text-align: center; font-weight: 700; width: 180px; border-top-left-radius: 15px; border-top-right-radius: 15px; box-shadow: rgb(153, 153, 153) 0px -1px 6px; border-bottom-left-radius: 2px; border-bottom-right-radius: 2px; text-shadow: rgb(10, 10, 10) 0px -1px 2px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\">微信</blockquote><blockquote style=\"margin: 0px; padding: 10px; border: 1px solid rgb(204, 204, 204); font-family: 微软雅黑; line-height: 24px; white-space: normal; font-size: 12px; max-width: 100%; color: rgb(62, 62, 62); border-top-left-radius: 0px; border-top-right-radius: 0px; box-shadow: rgb(204, 204, 204) 0px -1px 6px; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(228, 228, 228);\"><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(0, 176, 80); word-wrap: break-word !important; box-sizing: border-box !important;\">微信号：</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-weight: 700; word-wrap: break-word !important; box-sizing: border-box !important;\"></span><span class=\"bkcolor\" style=\"margin: 0px; padding: 2px 5px; border: 0px; max-width: 100%; font-weight: 700; color: rgb(255, 255, 255); word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"></span><span class=\"bfcolor\" style=\"margin: 0px; padding: 0px 0px 0px 5px; border: 0px; max-width: 100%; color: rgb(0, 187, 236); word-wrap: break-word !important; box-sizing: border-box !important;\">(←长按复制)</span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 10px 0px 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1.5em; white-space: pre-wrap; word-break: normal; color: rgb(102, 102, 102); line-height: 2em; box-sizing: border-box !important;\">全力打造杭城微信营销第一品牌</p></blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd55894d0003', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '互推0005', 'WXHTZH', '<blockquote class=\"bfcolor\" style=\"white-space: normal; margin: 0px; padding: 10px 15px; border: 6px solid rgb(195, 54, 197); border-top-left-radius: 50px!important; border-bottom-right-radius: 50px!important; box-shadow: rgb(165, 165, 165) 5px 5px 2px; background-color: rgb(250, 250, 250);\"><p><br></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; color: rgb(195, 54, 197); line-height: 2em; font-size: 18px; min-height: 1.5em; -webkit-transform: translate(0px, -30px);\"><strong></strong></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2em; min-height: 1.5em; -webkit-transform: translate(0px, -10px);\"><strong class=\"bkcolor\" style=\"padding: 0px 15px; color: rgb(255, 255, 255); font-size: 13px; border-bottom-right-radius: 20px!important; border-bottom-left-radius: 20px!important; background-color: rgb(195, 54, 197);\">wxid</strong></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2em; min-height: 1.5em;\"><strong style=\"color: rgb(195, 54, 197); font-size: 14px;\">直接选择需要的样式拷贝到需要的地方，修改其中的文字即可。如果需要跟换颜色或者有其他需求。</strong></p><p><br></p></blockquote>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd5613e50004', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '互推0006', 'WXHTZH', '<blockquote class=\"brcolor\" style=\"BORDER-TOP: rgb(80,130,189) 3px dotted; BORDER-RIGHT: rgb(80,130,189) 3px dotted; BORDER-BOTTOM: rgb(80,130,189) 3px dotted; BORDER-LEFT: rgb(80,130,189) 3px dotted; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; PADDING-LEFT: 10px; MARGIN: 0px; PADDING-RIGHT: 10px;border-radius:5px!important;font-weight:normal;\"><h3 style=\"color:rgb(89,89,89);font-size:14px;margin:0;\"><span class=\"bkcolor\" style=\"background-color:rgb(80,130,189);color:rgb(255,255,255);padding:2px 5px;border-radius:5px!important;font-size:14px;margin-right: 15px;\">微信</span>微信号：<span class=\"bkcolor\" style=\"background-color:#5082bd;color:rgb(255,255,255);padding:2px 5px;border-radius:5px!important;font-size:14px;\">weixinhao</span></h3><p style=\"margin:10px 0 5px 0;\">欢迎使用微信编辑器！</p></blockquote><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd5754490005', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '分割线0001', 'WXFGX', '<p>\r\n<br>\r\n</p>\r\n<hr class=\"awb-s1\" style=\"margin: 0px; padding: 0px; border-width: 5px 0px 0px; border-top-style: solid; border-top-color: rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal;\">\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd58d8800006', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '分割线0002', 'WXFGX', '<p>\r\n<br>\r\n</p>\r\n<hr class=\"awb-s1\" style=\"margin: 0px; padding: 0px; border-width: 5px 0px 0px; border-top-style: dashed; border-top-color: rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal;\">\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd59d2da0007', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '分割线0003', 'WXFGX', '<p>\r\n<br>\r\n</p>\r\n<hr class=\"awb-s1\" style=\"margin: 0px; padding: 0px; border-width: 5px 0px 0px; border-top-style: dotted; border-top-color: rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal;\">\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd5a71180008', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '分割线0004', 'WXFGX', '<p>\r\n<br>\r\n</p>\r\n<hr class=\"awb-s1\" style=\"margin: 0px; padding: 0px; border-width: 5px 0px 0px; border-top-style: double; border-top-color: rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal;\">\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd5b21670009', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '分割线0005', 'WXFGX', '<p>\r\n<br>\r\n</p>\r\n<section style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal; height: 10px; background: url(http://mmbiz.qlogo.cn/mmbiz/mj9u1OBZRqP8EvePIzqrRIHCHOzYM4ngkYThFAvY5zacs8KyJDB6CpFMWLrIgQoSFaKSXfnnibP8rYHMibMibkh5Q/0) repeat-x rgb(0, 187, 236);\"></section>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd5ddb44000b', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '原文引导0001', 'WXYWYD', '<section style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal;\">\r\n<section class=\"awb-s1\" style=\"margin: 0px; padding: 0px; border: 0px; height: 0.1em; background-color: rgb(0, 187, 236);\"></section>\r\n<section class=\"awb-s1\" style=\"margin: 0.3em 0px; padding: 0px; border: 0px; height: 0.3em; background-color: rgb(0, 187, 236);\"></section>\r\n<section class=\"awb-s3\" style=\"margin: 0px; padding: 0.5em; border: 1px solid rgb(0, 187, 236); box-shadow: rgb(226, 226, 226) 0em 1em 0.1em -0.8em; font-size: 1em; line-height: 1.6em; background-color: white;\">\r\n<span style=\"margin: 0px; padding: 0px; border: 0px; color: inherit; font-size: 1em; font-family: inherit;\">点击下方</span><span style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(64, 84, 115); font-size: 1em; font-family: inherit;\">“阅读原文”</span><span style=\"margin: 0px; padding: 0px; border: 0px; color: inherit; font-size: 1em; font-family: inherit;\">查看更多</span>\r\n</section>\r\n<section class=\"awb-s4\" style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(0, 187, 236); font-size: 2em;\">\r\n↓↓↓\r\n</section>\r\n</section>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd5e75f1000c', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '原文引导0002', 'WXYWYD', '<p class=\"awb-s1\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; font-family: 微软雅黑; font-size: 13px; min-height: 1.5em; word-wrap: break-word; word-break: normal; white-space: pre-wrap; line-height: 36px; text-align: center; color: rgb(255, 255, 255); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; background-color: rgb(0, 187, 236);\">点击左下角查看更多</p>\r\n<p class=\"awb-s2\" style=\"margin-top: -5px; margin-bottom: 0px; margin-left: 50px; padding: 0px; border-right-width: 1em; border-bottom-width: 0px; border-left-width: 1em; border-right-style: solid; border-left-style: solid; border-top-color: rgb(0, 187, 236); color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal; display: inline-block; border-top-width: 1.5em !important; border-top-style: solid !important; border-right-color: transparent !important; border-left-color: transparent !important;\">\r\n<br>\r\n</p>\r\n<p>\r\n<span style=\"color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; background-color: rgb(255, 255, 255);\">&nbsp;</span>\r\n</p>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd649681000d', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '原文引导0003', 'WXYWYD', '<section style=\"margin: 0px; padding: 0.7em 0px; border-width: 1px 0px; border-top-style: solid; border-bottom-style: solid; border-top-color: rgb(63, 71, 78); border-bottom-color: rgb(63, 71, 78); font-family: 微软雅黑; font-size: 1em; white-space: normal; max-width: 100%; line-height: 1.4em; font-style: italic; color: rgb(63, 71, 78); word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-size: 12px; word-wrap: break-word !important; box-sizing: border-box !important;\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><em style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">点击“<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-size: 16px; color: rgb(192, 80, 77); word-wrap: break-word !important; box-sizing: border-box !important;\">阅读原文</span>”体验一次简单不过的微信编辑体验，不用太久，不用太难，<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-size: 16px; color: rgb(155, 187, 89); word-wrap: break-word !important; box-sizing: border-box !important;\">瞬间</span>即可！</em></strong></span>\r\n</section>\r\n<p>\r\n<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-size: 12px; word-wrap: break-word !important; box-sizing: border-box !important;\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><em style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><br></em></strong></span>\r\n</p>\r\n<p>\r\n<br>\r\n</p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd65db39000e', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '其他0001', 'WXQT', '<fieldset style=\"margin: 0px; padding: 5px; max-width: 100%; color: rgb(62, 62, 62); font-size: medium; white-space: normal; border: 1px solid rgb(204, 204, 204); border-image-source: none; line-height: 24px; font-family: 宋体; orphans: 2; widows: 2; box-sizing: border-box !important; word-wrap: break-word !important; background-color: rgb(248, 247, 245);\"><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; max-width: 100%; min-height: 1em; white-space: pre-wrap; overflow: hidden; box-sizing: border-box !important; word-wrap: break-word !important;\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; line-height: 2em; color: rgb(118, 146, 60); box-sizing: border-box !important; word-wrap: break-word !important;\">阅读本文用了： </strong><img src=\"https://mmbiz.qlogo.cn/mmbiz/ZFvUqSQ0vAk8yU7xZ8hDKgeBPSwDpcPSjawGj2ekAp1GMQLq7RibhFgicdpCXj3B8oBeMAia74e13FotEsrX7zRAw/0\" data-w=\"16\" data-ratio=\"2.25\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"><img src=\"https://mmbiz.qlogo.cn/mmbiz/ZFvUqSQ0vAk8yU7xZ8hDKgeBPSwDpcPS8UhBbKTnibWicVbs4TpCYk3u7mkKHVMwJfUXxEXeuQb0pEsicbQuyqdPw/0\" data-w=\"17\" data-ratio=\"2.1176470588235294\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"> <strong style=\"font-family: 宋体; font-size: medium; orphans: 2; white-space: pre-wrap; widows: 2; margin: 0px; padding: 0px; max-width: 100%; line-height: 2em; color: rgb(118, 146, 60); box-sizing: border-box !important; word-wrap: break-word !important; background-color: rgb(248, 247, 245);\">分</strong> <img src=\"https://mmbiz.qlogo.cn/mmbiz/ZFvUqSQ0vAk8yU7xZ8hDKgeBPSwDpcPSwpicSWvEt3jiaepM3o5l84KT8FbQyycX580BWD8hwCZxdy3VbYpV3ylw/0\" data-w=\"16\" data-ratio=\"2.25\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"><img src=\"https://mmbiz.qlogo.cn/mmbiz/ZFvUqSQ0vAk8yU7xZ8hDKgeBPSwDpcPSmhSsibSUO9VCW5icqTkCfv5fUSzia42c3cWgRrCFZ8QE3ehpm9Tzs48icw/0\" data-w=\"17\" data-ratio=\"2.1176470588235294\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; line-height: 2em; color: rgb(118, 146, 60); box-sizing: border-box !important; word-wrap: break-word !important;\">秒，转发只需1秒</strong><br style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\"></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; max-width: 100%; word-wrap: normal; min-height: 1.5em; white-space: pre-wrap; overflow: hidden; line-height: 2em; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; max-width: 100%; color: rgb(149, 55, 52); box-sizing: border-box !important; word-wrap: break-word !important;\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; line-height: 18px; font-family: simsun, sans-serif; box-sizing: border-box !important; word-wrap: break-word !important;\"><span style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\">?</strong></span>转载是一种智慧<strong style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\">?</strong>分享是一种美德?</strong></span></p></fieldset>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd66c488000f', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '其他0002', 'WXQT', '<p style=\"margin:0 auto;white-space:normal;padding:8px 20px;color:#333;font-size:14px;max-width:100%;min-height:1.5em;line-height:2em;font-family:微软雅黑;text-align:justify;word-wrap:break-word!important;background-image:-webkit-linear-gradient(left,#eeece1,#92d050);background-color:#eeece1\"><span style=\"margin:0;padding:0;font-size:32px;color:#fff\"><strong style=\"color:#3c752d\"><span style=\"margin:0;padding:0;font-size:14px;font-family:\'Microsoft Yahei\'line-height:25px\">左右渐变</span></strong></span><span style=\"margin:0;padding:0;color:#494429\"></span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd675c110010', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '其他0003', 'WXQT', '<p style=\"margin:0 auto;padding:8px 20px;font-size:14px;white-space:normal;max-width:100%;min-height:1.5em;line-height:2em;font-family:微软雅黑;text-align:justify;color:#494429;word-wrap:break-word!important;background-image:-webkit-linear-gradient(left,#f79646,#eeece1);background-color:#eeece1\"><span style=\"color:#fff\"><strong><span style=\"margin:0;padding:0;font-size:24px\"><span style=\"font-family:微软雅黑;font-size:14px;text-align:justify;margin:0;padding:0;line-height:32px\">右</span><span style=\"font-family:微软雅黑;font-size:14px;line-height:28px;text-align:justify\">左渐变</span></span></strong></span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd67e5f50011', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '其他0004', 'WXQT', '<p style=\"margin:0 auto;padding:8px 20px;color:#333;font-size:14px;white-space:normal;max-width:100%;min-height:1.5em;line-height:2em;font-family:微软雅黑;text-align:justify;word-wrap:break-word!important;background-image:-webkit-linear-gradient(bottom,#c09eae,#c22b4c);background-color:#7e722f\"><span style=\"color:#fff\"><strong style=\"color:#3c752d\"><span style=\"margin:0;padding:0;font-size:14px;font-family:\'Microsoft Yahei\'line-height:25px\">从下到上</span></strong><strong style=\"color:#3c752d\"><span style=\"margin:0;padding:0;font-size:14px;font-family:\'Microsoft Yahei\'line-height:25px\"></span></strong></span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd685d530012', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '其他0005', 'WXQT', '<p style=\"margin:0 auto;padding:8px 20px;font-size:14px;white-space:normal;max-width:100%;min-height:1.5em;line-height:2em;font-family:微软雅黑;text-align:justify;color:#494429;word-wrap:break-word!important;background-image:-webkit-linear-gradient(top,#f8f5f7,#c22b4c);background-color:#7e722f\"><span style=\"margin:0;padding:0;max-width:100%;font-size:32px;color:#fff;word-wrap:break-word!important\"><strong style=\"color:#3c752d;max-width:100%;word-wrap:break-word!important\"><span style=\"margin:0;padding:0;max-width:100%;font-size:14px;color:#fff;word-wrap:break-word!important;font-family:\'Microsoft Yahei\'line-height:25px\">从上到下，圆润</span></strong></span></p><p><br></p>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd74800a0014', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '标题0023', 'WXBT', '<section class=\"wwei-editor\"><h2 style=\"padding: 0px; margin: 8px 0px 0px; font-weight: normal; font-size: 16px; font-family: 微软雅黑; white-space: normal; height: 32px; text-align: justify; color: rgb(62, 62, 62); line-height: 18px; border-bottom-color: rgb(227, 227, 227); border-bottom-width: 1px; border-bottom-style: solid;\"><span style=\"padding: 0px 2px 3px; color: rgb(0, 112, 192); line-height: 28px; border-bottom-color: rgb(0, 187, 236); border-bottom-width: 2px; border-bottom-style: solid; float: left; display: block;\"><span style=\"padding: 4px 10px; border-top-left-radius: 80%; border-top-right-radius: 100%; border-bottom-right-radius: 90%; border-bottom-left-radius: 20%; color: rgb(255, 255, 255); margin-right: 8px; background-color: rgb(0, 187, 236);\">序号.</span><span style=\"color: rgb(0, 187, 236);\">标题党</span></span></h2></section>\r\n<p><br></p>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7673c40015', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0024', 'WXBT', '<section class=\"wwei-editor\"><blockquote style=\"padding: 10px; margin: 5px 0px 0px; white-space: normal; max-width: 100%; line-height: 25px; font-size: 14px; font-family: arial, helvetica, sans-serif; border-top-left-radius: 4px; border-top-right-radius: 4px; border-bottom-right-radius: 4px; border-bottom-left-radius: 4px; color: rgb(255, 255, 255); border-left-color: rgb(0, 187, 236); border-left-width: 10px; border-left-style: solid; box-shadow: rgb(153, 153, 153) 2px 2px 4px; text-shadow: rgb(34, 95, 135) 0px 1px 0px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(55, 57, 57);\"><span style=\"max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"><span style=\"max-width: 100%; font-family: 微软雅黑; font-size: 16px; word-wrap: break-word !important; box-sizing: border-box !important;\">1、这里输入标题</span></span></blockquote></section><p><br></p>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7739610016', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0025', 'WXBT', '<section class=\"wwei-editor\"><section style=\"max-width: 100%;margin: 0.8em 0px 0.5em; overflow: hidden; \"><section placeholder=\"请输入标题\" style=\"box-sizing: border-box !important;  height:36px;display: inline-block;color: #FFF; font-size: 16px;font-weight:bold; padding:0 10px;line-height: 36px;float: left; vertical-align: top; background-color: rgb(249, 110, 87); \" class=\"wweibrush\">请输入标题</section><section style=\"box-sizing: border-box !important; display: inline-block;height:36px; vertical-align: top; border-left-width: 9px; border-left-style: solid; border-left-color: rgb(249, 110, 87); border-top-width: 18px !important; border-top-style: solid !important; border-top-color: transparent !important; border-bottom-width: 18px !important; border-bottom-style: solid !important; border-bottom-color: transparent !important;\"></section></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd77d4880017', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0026', 'WXBT', '<section class=\"wwei-editor\"><h2 style=\"margin: 8px 0px 0px; padding: 0px; font-weight:bold;font-size:16px;line-height:28px;  max-width: 100%;color:rgb(0, 112, 192); min-height: 32px;border-bottom: 2px solid rgb(0, 112, 192); text-align: justify;\"><span class=\"autonum\" placeholder=\"1\" style=\"background-color:rgb(0, 112, 192); border-radius:80% 100% 90% 20%; color:rgb(255, 255, 255); display:block; float:left; line-height:20px; margin:0px 8px 0px 0px; max-width:100%; padding:4px 10px; word-wrap:break-word !important\">1</span><strong class=\"wweibrush\" data-brushtype=\"text\">第一标题</strong></h2></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd78b1c10018', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0027', 'WXBT', '<section class=\"wwei-editor\"><h2 class=\"wweibrush\" data-bcless=\"darken\" placeholder=\"深色边框标题\" style=\"margin: 10px 0px; padding: 10px; font-size: 16px; line-height: 25px; text-shadow: rgb(34, 95, 135) 0px 1px 0px; color: rgb(202, 251, 215); border-radius: 4px; box-shadow: rgb(153, 153, 153) 2px 2px 4px; border-left-width: 10px; border-left-style: solid; border-color: rgb(10, 137, 43); background-color: rgb(14, 184, 58);\">深色边框标题</h2></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7969b00019', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0028', 'WXBT', '<section class=\"wwei-editor\"><section style=\"margin: 2px 0.8em 1em 0; text-align: center; font-size: 1em; vertical-align: middle; white-space: nowrap;\"><section style=\"height: 0px; white-space: nowrap;  border-top: 1.5em solid rgb(71, 193, 168); border-bottom: 1.5em solid rgb(71, 193, 168); border-left-width: 1.5em ! important; border-left-style: solid ! important; border-right-width: 1.5em ! important; border-right-style: solid ! important; border-color: rgb(71, 193, 168);\"></section><section style=\"height: 0; white-space: nowrap; margin: -2.75em 1.65em;border-top: 1.3em solid #ffffff;border-bottom: 1.3em solid #ffffff;border-left: 1.3em solid transparent;border-right: 1.3em solid transparent;\"></section><section style=\"height: 0px; white-space: nowrap; margin: 0.45em 2.1em; vertical-align: middle; border-top: 1.1em solid rgb(71, 193, 168); border-bottom: 1.1em solid rgb(71, 193, 168); border-left-width: 1.1em ! important; border-left-style: solid ! important; border-right-width: 1.1em ! important; border-right-style: solid ! important; border-color: rgb(71, 193, 168);\"><section class=\"wweibrush\" data-ct=\"fix\" placeholder=\"一行短标题\" style=\"max-height: 1em; padding: 0px; margin-top: -0.5em; color: rgb(254, 255, 253); font-size: 1.2em; line-height: 1em; white-space: nowrap; overflow: hidden; font-style: normal;\">一行短标题</section></section></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd79e32e001a', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0029', 'WXBT', '<section class=\"wwei-editor\"><h2 class=\"wweibrush\" placeholder=\"请输入标题\" style=\"white-space: normal; font-size: 16px; margin: 10px 0px; padding: 10px; max-width: 100%; border-top:solid 2px;border-left:0px; border-bottom:solid 2px; line-height: 25px; color: rgb(109, 151, 200);font-weight:bold; text-align: center;\">请输入标题</h2></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7a7858001b', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0030', 'WXBT', '<section class=\"wwei-editor\"><fieldset style=\"clear: both; padding: 0px; border: 0px none; margin: 1em 0px 0.5em;\"><section style=\"border-top-width: 2px; border-top-style: solid; border-color: rgb(142, 201, 101); font-size: 1em; font-weight: inherit; text-decoration: inherit; color: rgb(255, 255, 255); box-sizing: border-box;\"><section class=\"wweibrush\" data-brushtype=\"text\" style=\"padding: 0px 0.5em; background-color: rgb(142, 201, 101); display: inline-block; font-size: 16px;\">微信编辑器</section></section></fieldset></section>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7b1a20001c', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0031', 'WXBT', '<section class=\"wwei-editor\"><section style=\"font-size: 1em; white-space: normal; margin: 1em 0px 0.8em; text-align: center; vertical-align: middle;\"><section style=\"height: 0px; margin: 0px 1em; border: 1.5em solid rgb(255, 202, 0); border-left-color: transparent !important;border-right-color: transparent !important;\"></section><section style=\"height: 0px; margin: -2.75em 1.65em; border-width: 1.3em; border-style: solid; border-color: rgb(255, 255, 255) transparent;\"></section><section style=\"height: 0px; margin: 0.45em 2.1em; vertical-align: middle; border:1.1em solid rgb(255, 202, 0); border-left-color: transparent !important;  border-right-color: transparent !important;\"><section class=\"wweibrush\" data-brushtype=\"text\" placeholder=\"一行短标题\" style=\"max-height: 1em; padding: 0px; margin-top: -0.5em; color: rgb(255, 255, 255); font-size: 1.2em; line-height: 1em; overflow: hidden;\">一行短标题</section></section></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7bb487001d', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0032', 'WXBT', '<section class=\"wwei-editor\"><section style=\"border: 3px solid rgb(255, 129, 36); padding: 5px;\"><section data-bcless=\"lighten\" style=\"border: 1px solid rgb(255, 158, 87); padding: 15px; text-align: center; color: inherit;\"><p style=\"color: inherit;\">微信编辑器</p><p style=\"color:inherit; font-size:24px\"><strong style=\"color:inherit\"><span class=\"wweibrush\" data-brushtype=\"text\" style=\"color:inherit; font-size:24px\">操作方便才是硬道理</span></strong></p></section></section></section>\r\n');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7c92d9001e', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0033', 'WXBT', '<section class=\"wwei-editor\"><p class=\"wweibrush\" placeholder=\"请输入标题\" style=\"max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap;line-height: 25px;font-size:20px;box-sizing:border-box !important;text-shadow:rgb(0, 187, 236) 1px 0px 4px, rgb(0, 187, 236) 0px 1px 4px, rgb(0, 187, 236) 0px -1px 4px, rgb(0, 187, 236) -1px 0px 4px; word-wrap:break-word !important;color:rgb(255, 255, 255);font-weight:bold;\">请输入标题</p></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7d1b20001f', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0034', 'WXBT', '<section class=\"wwei-editor\"><fieldset style=\"margin: 0.5em 0px; padding: 0px; max-width: 100%; box-sizing: border-box; color: rgb(62, 62, 62); font-size: medium; line-height: 25px; white-space: normal; border: none rgb(255, 175, 205); word-wrap: break-word !important; text-align: right;\"><section style=\"margin: 0px; padding: 1.5em 0px; max-width: 100%; box-sizing: border-box; width: 12.5em; height: 9.3em;  text-align: center; color: rgb(175, 0, 66); word-wrap: break-word !important; background-image: url(http://pro.wwei.cn/Public/wxeditor/css/0.png); background-color: rgb(255, 175, 205); background-size: cover;display:inline-block\"><section style=\"margin: 0px 0px 0px 20px; padding: 0px; max-width: 100%; box-sizing: border-box; width: 160px; overflow: hidden; -webkit-transform: rotate(-13deg); font-size: 22px;  font-weight: inherit; text-decoration: inherit; color: rgb(255, 255, 255); word-wrap: break-word !important;\"><section style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; word-wrap: break-word !important;\">肆意</section></section><section style=\"margin: 10px 20px; padding: 0px; max-width: 100%; box-sizing: border-box; width: 150px; overflow: hidden; -webkit-transform: rotate(-15deg);  font-weight: inherit; text-decoration: inherit; color: rgb(255, 255, 255); word-wrap: break-word !important;\"><section class=\"wweibrush\" data-brushtype=\"text\" style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; word-wrap: break-word !important;\"><p>青春，背景色你的地盘你做主</p></section></section></section></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7d8c2d0020', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0035', 'WXBT', '<section class=\"wwei-editor\"><fieldset style=\"border: 0px; text-align: center; box-sizing: border-box; padding: 0px;\"><section style=\"display: inline-block; box-sizing: border-box; color: inherit;\"><section class=\"wweibrush\" data-brushtype=\"text\" style=\"margin: 0.2em 0px 0px; padding: 0px 0.5em 5px; max-width: 100%; color: rgb(107, 77, 64); font-size: 1.8em; line-height: 1; border-bottom-width: 1px; border-bottom-style: solid; border-color: rgb(107, 77, 64);\">微信编辑器</section><section class=\"wweibrush\" data-brushtype=\"text\" style=\"margin: 5px 1em; font-size: 1em; line-height: 1; color: rgb(107, 77, 64); box-sizing: border-box; border-color: rgb(107, 77, 64);\">做最易用的编辑器</section></section></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7e098d0021', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0036', 'WXBT', '<section class=\"wwei-editor\"><p><span style=\"border-color:rgb(30, 155, 232); color:rgb(30, 155, 232); font-size:4em; font-weight:bolder; line-height:1em; vertical-align:middle\">“</span><span class=\"wweibrush\" data-brushtype=\"text\" style=\"color:inherit; font-size:2em; font-style:normal; line-height:1.2em; vertical-align:middle\">标题</span><span class=\"wweibrush\" data-brushtype=\"text\" style=\"border-color:rgb(30, 155, 232); color:rgb(30, 155, 232); font-size:2em; font-style:normal; line-height:1.2em; vertical-align:middle\">标题</span><span style=\"border-color:rgb(30, 155, 232); color:rgb(30, 155, 232); font-size:4em; font-weight:bolder; line-height:1em; vertical-align:middle\">”</span></p></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7e84ed0022', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0037', 'WXBT', '<section class=\"wwei-editor\"><section data-bcless=\"true\" style=\"font-size:20px; background: rgb(223, 240, 203);border:0 none;\"><span class=\"wweibrush\" style=\"background:rgb(255, 255, 255); color:rgb(150, 206, 84); display:inline-block; padding:0px 15px 0px 0px\">微信编辑器</span><span style=\"background:none repeat scroll 0 0 #fff; display:inline-block; margin-left:5px\">&nbsp;</span><span style=\"background:none repeat scroll 0 0 #fff; display:inline-block; margin-left:5px\">&nbsp;</span><span style=\"background:none repeat scroll 0 0 #fff; display:inline-block; margin-left:5px\">&nbsp;</span></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7f10190023', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0038', 'WXBT', '<section class=\"wwei-editor\"><p class=\"wweibrush\" data-brushtype=\"text\" placeholder=\"请输入标题\" style=\"max-width: 100%; line-height: 24px;font-weight:bold; background-color: rgb(244, 156, 4); color: rgb(255, 255, 255); border-radius: 45%; text-align: center; margin:10px 0px;padding: 10px 15px;\">请输入标题</p></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd7fb7420024', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0039', 'WXBT', '<section class=\"wwei-editor\"><fieldset style=\"border: 0px rgb(107, 77, 64); text-align: center; margin: 0.8em 0px 0.5em; box-sizing: border-box; padding: 0px;\"><section style=\"color: rgb(107, 77, 64); display: inline-block; width: 1em; font-size: 2.5em; font-weight: inherit; line-height: 1em; vertical-align: top; text-align: inherit; text-decoration: inherit; box-sizing: border-box; border-color: rgb(107, 77, 64);\"><section class=\"wweibrush\" data-brushtype=\"text\" style=\"box-sizing: border-box; color: inherit; border-color: rgb(107, 77, 64);\">两字</section></section><section style=\"display: inline-block; margin-left: 0.5em; margin-top: 0.2em; text-align: left; box-sizing: border-box; color: inherit;\"><section style=\"box-sizing: border-box; color: inherit;\"><section class=\"wweibrush\" data-brushtype=\"text\" style=\"background-color:rgb(107, 77, 64); border-color:rgb(107, 77, 64); box-sizing:border-box; color:rgb(224, 209, 202); display:inline-block; font-size:2em; font-weight:inherit; line-height:1; padding:0.1em; text-align:inherit; text-decoration:inherit\">副标题1</section></section><section style=\"color: rgb(107, 77, 64); margin: 0.5em 0px; font-size: 1.5em; line-height: 1em; font-weight: inherit; text-align: inherit; text-decoration: inherit; box-sizing: border-box; border-color: rgb(107, 77, 64);\"><section class=\"wweibrush\" data-brushtype=\"text\" style=\"box-sizing: border-box; color: inherit; border-color: rgb(107, 77, 64);\">副标题2</section></section></section></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8037160025', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0040', 'WXBT', '<section class=\"wwei-editor\"><h2 class=\"wweibrush\" data-brushtype=\"text\" style=\"padding-bottom: 9px;border-bottom: 1px solid #eee;argin-top: 20px;margin-bottom: 10px;font-weight: 500;line-height: 1.1;font-size: 22px;\">极简标题</h2></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd81193e0026', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0041', 'WXBT', '<section class=\"wwei-editor\" style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal; display: inline-block; height: 2em; max-width: 100%; line-height: 1em; box-sizing: border-box; border-top-width: 1.1em; border-top-style: solid; border-top-color: rgb(0, 187, 236); border-bottom-width: 1.1em; border-bottom-style: solid; border-bottom-color: rgb(0, 187, 236); border-right-width: 1em; border-right-style: solid; border-right-color: transparent;\"><section style=\"height: 2em; margin-top: -1em; color: white; padding: 0.5em 1em; max-width: 100%; white-space: nowrap; text-overflow: ellipsis;\">这里输入标题</section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd81cd570027', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '标题0042', 'WXBT', '<section class=\"wwei-editor\" style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal; text-align: center; margin: 0px 1em; line-height: 1.6em;\"><img src=\"http://pro.wwei.cn/Public/wxeditor/css/gs640.png\" style=\"border: 0px; width: 266px; vertical-align: middle; height: 36px !important;\"><section style=\"color: white; font-size: 1em; margin-top: -2.1em; white-space: nowrap;\">请输入标题</section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8628850028', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0024', 'WXNRQ', '<blockquote style=\"padding: 15px 25px; margin: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑; white-space: normal; max-width: 100%; word-wrap: break-word; top: 0px; line-height: 24px;  vertical-align: baseline; border-left-color: rgb(0, 187, 236); border-left-width: 10px; border-left-style: solid; background-color: rgb(239, 239, 239);\"><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px;\">可在这输入内容，wwei微信编辑器，微信编辑首选。</p></blockquote>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd86eb350029', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0025', 'WXNRQ', '<blockquote style=\"padding: 5px 15px; margin: 0px; font-family: 微软雅黑;  white-space: normal; max-width: 100%; color: rgb(255, 255, 255); line-height: 25px; font-weight: bold; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px; border: 0px; background-color: rgb(0, 187, 236);\"><span style=\"font-size: 16px;\">这输入标题</span></blockquote><blockquote style=\"padding: 10px 15px 20px; margin: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑;  white-space: normal; max-width: 100%; border-top-left-radius: 0px; border-top-right-radius: 0px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; border: 0px; line-height: 25px; background-color: rgb(239, 239, 239);\"><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px;\">可在这输入内容，wwei微信编辑器，微信编辑首选。</p></blockquote>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd878ac3002a', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0026', 'WXNRQ', '<section style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal; margin-top: 1.5em; display: inline-block; height: 2em; max-width: 100%; line-height: 1em; box-sizing: border-box; border-top-width: 1.1em; border-top-style: solid; border-top-color: rgb(0, 187, 236); border-bottom-width: 1.1em; border-bottom-style: solid; border-bottom-color: rgb(0, 187, 236); border-right-width: 1em; border-right-style: solid; border-right-color: transparent;\"><section style=\"height: 2em; margin-top: -1em; color: white; padding: 0.5em 1em; max-width: 100%; white-space: nowrap; text-overflow: ellipsis;\">事项1</section></section><span style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; background-color: rgb(255, 255, 255);\"></span><section style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal; padding: 1em;\"><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px;\">请输入活动内容<br>请输入活动内容<br>......</p></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd887c63002b', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0027', 'WXNRQ', '<section style=\"color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal;\"><section style=\"border: 1px solid rgb(0, 187, 236); box-shadow: rgb(165, 165, 165) 0em 1em 0.1em -0.6em; line-height: 1.6em; background-color: white;\"><section style=\"padding: 1em; text-align: center; font-size: 1.4em; font-weight: bold; line-height: 1.4em; color: white; background-color: rgb(0, 187, 236);\">请输入名称</section><section style=\"margin-top: 1.5em;\"><img src=\"http://pro.wwei.cn/Public/wxeditor/css/i640.png\" style=\"border: 0px; vertical-align: top; margin-left: 1em; width: 30px;\"><section style=\"display: inline-block; width: 108.390625px; margin-left: 0.5em; padding: 0.2em;\">时间</section></section><section style=\"margin-top: 1em;\"><img src=\"http://pro.wwei.cn/Public/wxeditor/css/s640.png\" style=\"border: 0px; vertical-align: top; margin-left: 1em; width: 30px;\"><section style=\"display: inline-block; width: 108.390625px; margin-left: 0.5em; padding: 0.2em;\">地点</section></section><br><br><br></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd89181d002c', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0028', 'WXNRQ', '<p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑; font-size: 12px; white-space: normal;\">&nbsp;</p><p></p><section style=\"color: rgb(51, 51, 51); font-size: 12px; white-space: normal; font-family: sans-serif, Arial, Verdana, \'Trebuchet MS\'; background-image: url(http://www.17sucai.com/preview/1/2014-04-08/%E6%97%B6%E9%97%B4%E8%BD%B4/images/log/line-bg.png); background-attachment: initial; background-size: initial; background-origin: initial; background-clip: initial; background-position: 104px 30px; background-repeat: repeat-y;\"><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px; line-height: 40px; font-size: 24px;\">2015年</p><h1 style=\"padding: 0px 0px 0px 60px; margin: -40px 0px 0px 85px; font-weight: normal; font-size: 24px; line-height: 40px; top: 0px; color: rgb(88, 166, 251); background-image: url(http://www.17sucai.com/preview/1/2014-04-08/%E6%97%B6%E9%97%B4%E8%BD%B4/images/log/clock.png); background-attachment: initial; background-size: initial; background-origin: initial; background-clip: initial; background-position: 0% 0%; background-repeat: no-repeat;\">wwei更新日志</h1><section style=\"clear: both; line-height: 32px;\"><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px;\">&nbsp;</p><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px; font-size: 20px;\">5月1日</p><p style=\"padding: 0px; margin-top: -42px; margin-bottom: 0px; margin-left: 90px;\"><img src=\"http://p4.qhimg.com/d/inn/05a63fc5/circle-h.png\" style=\"border: 0px; vertical-align: bottom;\"></p><section style=\"margin-left: 140px; margin-top: -32px;\"><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px; color: rgb(99, 208, 41); font-size: 20px;\">微信图文编辑</p><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px;\">微信在线编辑器</p><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px;\">&nbsp;</p></section><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px; font-size: 20px;\">3月3日</p><p style=\"padding: 0px; margin-top: -42px; margin-bottom: 0px; margin-left: 90px;\"><img src=\"http://p4.qhimg.com/d/inn/05a63fc5/circle-h.png\" style=\"border: 0px; vertical-align: bottom;\"></p><section style=\"margin-left: 140px; margin-top: -32px;\"><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px; color: rgb(99, 208, 41); font-size: 20px;\">会员VIP功能</p><p style=\"padding: 0px; margin-top: 0px; margin-bottom: 0px;\">新增了一大批功能</p></section></section></section><p></p>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd89b0a9002d', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0029', 'WXNRQ', '<fieldset style=\"border: 0px; margin: 5px 0px; box-sizing: border-box; padding: 0px;\"><section style=\"height: 1em; box-sizing: border-box;\"><section style=\"height: 100%; width: 1.5em; float: left; border-top-width: 0.4em; border-top-style: solid; border-color: rgb(249, 110, 87); border-left-width: 0.4em; border-left-style: solid; box-sizing: border-box;\"></section><section style=\"height: 100%; width: 1.5em; float: right; border-top-width: 0.4em; border-top-style: solid; border-color: rgb(249, 110, 87); border-right-width: 0.4em; border-right-style: solid; box-sizing: border-box;\"></section><section style=\"display: inline-block; color: transparent; clear: both; box-sizing: border-box;\"></section></section><section style=\"margin: -0.8em 0.1em -0.8em 0.2em; padding: 0.8em; border: 1px solid rgb(249, 110, 87); border-radius: 0.3em; box-sizing: border-box;\"><section placeholder=\"四角宽边框的样式\" style=\"color: rgb(51, 51, 51); font-size: 1em; line-height: 1.4; word-break: break-all; word-wrap: break-word;\" class=\"wweibrush\">四角宽边框的样式</section></section><section style=\"height: 1em; box-sizing: border-box;\"><section style=\"height: 100%; width: 1.5em; float: left; border-bottom-width: 0.4em; border-bottom-style: solid; border-color: rgb(249, 110, 87); border-left-width: 0.4em; border-left-style: solid; box-sizing: border-box;\"></section><section style=\"height: 100%; width: 1.5em; float: right; border-bottom-width: 0.4em; border-bottom-style: solid; border-color: rgb(249, 110, 87); border-right-width: 0.4em; border-right-style: solid; box-sizing: border-box;\"></section></section></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8a35c1002e', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0030', 'WXNRQ', '<section class=\"wwei-editor\"><blockquote class=\"wweibrush\" style=\"white-space: normal;font-size: 14px; line-height: 25px; margin: 2px 0px; padding: 10px 10px; border: 2px dashed #dedcde;max-width: 100%;\"><p placeholder=\"虚线框内容，作为摘要或段落内容。\">虚线框内容，作为摘要或段落内容。</p></blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8af659002f', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0031', 'WXNRQ', '<section class=\"wwei-editor\"><section class=\"wweibrush\" style=\"margin: 3px; padding: 15px;color: rgb(62, 62, 62); line-height: 24px;box-shadow: rgb(170, 170, 170) 0px 0px 3px; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); -webkit-box-shadow: rgb(170, 170, 170) 0px 0px 3px;\"><p>边框阴影内容区域</p></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8b911f0030', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0032', 'WXNRQ', '<blockquote style=\"border-width: 1px 1px 1px 5px; border-style: solid; border-color: rgb(238, 238, 238) rgb(238, 238, 238) rgb(238, 238, 238) rgb(159, 136, 127); border-radius: 3px; padding: 10px; margin: 10px 0px;\"><h4 class=\"wweibrush\" style=\"color: rgb(159, 136, 127); font-weight: bold; font-size: 18px; margin: 5px 0px; border-color: rgb(159, 136, 127);\">标题文字</h4><section class=\"wweibrush\" data-style=\"color:inherit;font-size:14px;\" style=\"color: inherit;font-size:14px\"><p>内容描述.</p></section></blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8c29da0031', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0033', 'WXNRQ', '<section class=\"wwei-editor\"><section style=\"color: inherit; font-size: 16px; padding: 5px 10px 0px 35px; margin-left: 27px; border-left-width: 2px; border-left-style: dotted; border-left-color: rgb(228, 228, 228);\"><section class=\"autonum\" style=\"width: 32px; height: 32px; margin-left: -53px; margin-top: 23px; color: rgb(202, 251, 215); text-align: center; line-height: 32px; border-top-left-radius: 16px; border-top-right-radius: 16px; border-bottom-right-radius: 16px; border-bottom-left-radius: 16px; background: rgb(14, 184, 58);\">1</section><section class=\"wweibrush\" style=\"margin-top: -30px;padding-bottom: 10px; color: inherit;\"><p style=\"clear: both; line-height: 1.5em; font-size: 14px; color: inherit;\"><span style=\"color:inherit; font-size:16px\"><strong style=\"color:inherit\">如何进入【微信编辑器】？</strong></span></p><p style=\"clear: both; line-height: 1.5em; font-size: 14px; color: inherit;\">网页搜索“微信编辑器”，点击第一条搜索结果即可进入编辑器页面</p></section></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8dfdc20032', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0034', 'WXNRQ', '<section class=\"wwei-editor\"><blockquote style=\"margin: 3px; padding: 10px 15px; border-width: 3px; border-color: rgb(107, 77, 64); border-top-left-radius: 50px; border-bottom-right-radius: 50px; box-shadow: rgb(165, 165, 165) 5px 5px 2px; background-color: rgb(250, 250, 250);\"><section class=\"wweibrush\" data-style=\"margin-top: 15px; margin-bottom: 0px; padding: 0px; color: rgb(107, 77, 64); line-height: 2em; font-size: 20px; border-color: rgb(107, 77, 64);font-size: 18px;font-weight:bold;\"><p style=\"margin-top: 15px; margin-bottom: 0px; padding: 0px; color: rgb(107, 77, 64); line-height: 2em; font-size: 20px; border-color: rgb(107, 77, 64);\"><span style=\"font-size:18px\"><strong style=\"border-color:rgb(107, 77, 64); color:inherit\">读而思</strong></span></p></section><p style=\"margin-top: -10px; margin-bottom: 0px; padding: 0px; line-height: 2em; min-height: 1.5em; color: inherit;\"><span style=\"font-size:12px\"><strong class=\"wweibrush\" data-brushtype=\"text\" style=\"background-color:rgb(107, 77, 64); border-bottom-left-radius:20px; border-bottom-right-radius:20px; color:rgb(224, 209, 202); font-size:13px; padding:0px 15px\">duersi</strong></span></p><section class=\"wweibrush\" data-style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2em; font-size: 14px; min-height: 1.5em; color: inherit;\"><p><span style=\"font-size:14px\">编辑完成后，将内容复制粘贴到微信后台素材管理的编辑器中即可。</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; line-height: 2em; font-size: 16px; min-height: 1.5em; color: inherit;\"><span style=\"font-size:14px\"></span></p></section></blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd8eba1f0033', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0035', 'WXNRQ', '<section class=\"wwei-editor\"><fieldset style=\"margin: 0px; padding: 5px; border: 1px solid rgb(204, 204, 204); max-width: 100%; color: rgb(62, 62, 62); line-height: 24px; text-align: justify; box-shadow: rgb(165, 165, 165) 5px 5px 2px; background-color: rgb(250, 250, 250);\"><legend style=\"margin: 0px; padding: 0px;  text-align: left;margin-left: 20px;width: auto;\"><strong><strong style=\"background-color:rgb(255, 255, 245); color:rgb(102, 102, 102); line-height:20px\"><span class=\"wweibrush\" data-brushtype=\"text\" style=\"background-color:red; border-bottom-left-radius:3em 1em; border-bottom-right-radius:0.5em 2em; border-top-left-radius:0.5em 4em; border-top-right-radius:3em 1em; box-shadow:rgb(165, 165, 165) 4px 4px 2px; color:white; max-width:100%; padding:4px 10px; text-align:justify\">公告通知</span></strong></strong>&nbsp;&nbsp;</legend><section class=\"wweibrush\" data-style=\"margin-top: 2px; margin-bottom: 0px; padding: 0px; max-width: 100%; min-height: 1.5em; line-height: 2em;font-weight:bold;\"><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; max-width: 100%; min-height: 1.5em; line-height: 2em;\">各位小伙伴们，微信图文美化编辑器正式上线了，欢迎大家多使用多提供反馈意见。使用<span style=\"color:inherit\"><strong>谷歌与火狐浏览器</strong></span>，可获得与手机端一致的显示效果。ie内核的低版本浏览器可能有不兼容的情况</p></section></fieldset></section><p><br></p>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd9117560034', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0036', 'WXNRQ', '<section class=\"wwei-editor\"><section style=\"max-width: 100%; margin: 2px; padding: 0px;\"><section style=\"max-width: 100%;margin-left:1em; line-height: 1.4em;\"><span style=\"font-size:14px\"><strong style=\"color:rgb(62, 62, 62); line-height:32px; white-space:pre-wrap\"><span class=\"wweibrush\" data-brushtype=\"text\" data-mce-style=\"color: #a5a5a5;\" placeholder=\"关于微信编辑器\" style=\"background-color:rgb(86, 159, 8); border-radius:5px; color:rgb(255, 255, 255); padding:4px 10px\">关于微信编辑器</span></strong></span>&nbsp;&nbsp;</section><section class=\"wweibrush\" data-style=\"color:rgb(89, 89, 89); font-size:14px; line-height:28px\" style=\"font-size: 1em; max-width: 100%; margin-top: -0.7em; padding: 10px 1em; border: 1px solid rgb(192, 200, 209); border-radius: 0.4em; color: rgb(51, 51, 51); background-color: rgb(239, 239, 239);\"><p><span placeholder=\"提供非常好用的微信文章编辑工具。\">非常好用的在线图文编辑工具</span>&nbsp;&nbsp;&nbsp;&nbsp;</p></section></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd92018b0035', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '微信0037', 'WXNRQ', '<section class=\"wwei-editor\"><blockquote class=\"wweibrush\" style=\"orphans: 2; white-space: normal; widows: 2; font-size: 14px; line-height: 22.39px;margin: 10px 0px; padding:15px 20px 15px 45px; outline: 0px; border: 0px currentcolor; color: rgb(62, 62, 62); vertical-align: baseline; background-image: url(http://pro.wwei.cn/Public/wxeditor/css/left_quote.jpg); background-color: rgb(241, 241, 241); background-position: 1% 5px; background-repeat: no-repeat no-repeat;\">这里插入分号引用样式的内容。</blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd9298e30036', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0038', 'WXNRQ', '<section class=\"wwei-editor\"><fieldset style=\"margin: 0.5em 0px; padding: 0px; max-width: 100%; box-sizing: border-box; color: rgb(62, 62, 62); line-height: 25px; white-space: normal; border: 0px rgb(238, 222, 176); word-wrap: break-word !important;\"><section style=\"margin: 0px; padding: 0px; width: 100%; box-sizing: border-box;display: inline-block; text-align: center; word-wrap: break-word !important;\"><img src=\"http://pro.wwei.cn/Public/wxeditor/css/06.png\" style=\"box-sizing:border-box; color:inherit; height:65px; margin:0px auto; padding:0px; visibility:visible !important; width:60px; word-wrap:break-word !important\"></section><section style=\"margin: -2.3em 0px 0px; padding: 2em 0px 0px; max-width: 100%; box-sizing: border-box; min-height: 15em; font-size: 1em;  font-weight: inherit; text-decoration: inherit; color: rgb(131, 104, 28); border-color: rgb(238, 222, 176); word-wrap: break-word !important; background-image: url(http://pro.wwei.cn/Public/wxeditor/css/07.png); background-color: rgb(238, 222, 176); background-repeat: repeat;\"><section style=\"margin: 0.3em auto; padding: 0.5em; max-width: 100%; box-sizing: border-box; width: 7em; height: 3.5em; line-height: 2em; overflow: hidden; -webkit-transform: rotate(-5deg); font-size: 32px;  font-weight: inherit; text-align: center; text-decoration: inherit; color: inherit; word-wrap: break-word !important; background-image: url(http://pro.wwei.cn/Public/wxeditor/css/08.png); background-repeat: no-repeat;background-size: contain;\"><section style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; word-wrap: break-word !important;\"><span style=\"box-sizing:border-box; color:inherit; margin:0px; max-width:100%; padding:0px; word-wrap:break-word !important\">公告</span></section></section><section style=\"margin: 0px; padding: 1em; max-width: 100%; box-sizing: border-box; word-wrap: break-word !important;\"><section class=\"wweibrush\" style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; word-wrap: break-word !important; color: inherit;\"><p>本背景可以换色哦~</p></section></section></section></fieldset><p><br></p></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd931a120037', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0039', 'WXNRQ', '<section class=\"wwei-editor\"><blockquote style=\"white-space: normal; margin: 5px 0px 0px; padding: 10px; max-width: 100%; border-left-width: 5px; border-left-style: solid; border-left-color: rgb(0, 176, 80); line-height: 25px; color: rgb(102, 102, 102);\"><p class=\"wweibrush\" placeholder=\"请输入内容内容。\">请输入内容内容。</p></blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd93dd0b0038', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0040', 'WXNRQ', '<section class=\"wwei-editor\"><blockquote style=\"white-space: normal; margin: 5px 0px 0px; padding: 10px; max-width: 100%; border-left-width: 5px; border-left-style: solid; border-left-color: rgb(0, 176, 80); line-height: 25px; color: rgb(102, 102, 102);\"><p class=\"wweibrush\" placeholder=\"请输入内容内容。\">请输入内容内容。</p></blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd94e0ca0039', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0041', 'WXNRQ', '<section class=\"wwei-editor\"><blockquote style=\"max-width: 100%; padding: 5px 15px; border: none rgb(255, 129, 36);word-wrap: break-word !important; box-sizing: border-box !important;background-size: cover; background-image: url(http://pro.wwei.cn/Public/wxeditor/css/0.gif);\"><section class=\"wweibrush\" style=\"color:#ffffff\"><p style=\"text-align: center; color: inherit;\"><br></p><p style=\"margin-top: 0px; margin-bottom: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; line-height: 1.75em; text-align: center; box-sizing: border-box !important;\"><span style=\"font-size:18px\"><strong>雪花动态背景样式，请输入文字</strong></span></p></section></blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd958d02003a', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0042', 'WXNRQ', '<section class=\"wwei-editor\"><section style=\"margin: 10px 0px; padding: 50px 0px; color: rgb(202, 251, 215); text-align: center; background-color: rgb(14, 184, 58);\"><span style=\"border:1px solid rgb(202, 251, 215); font-size:18px; line-height:42px; padding:10px 15px\">微信编辑器</span><section class=\"wweibrush\" style=\"margin-top:30px;\"><p>秒刷，最易用的图文排版工具</p></section></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd95fef4003b', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0043', 'WXNRQ', '<section class=\"wwei-editor\"><section style=\"padding: 0px 8px; border-left-width: 3px; border-left-style: solid; border-color: rgba(163, 163, 163, 0.843137); font-size: 22px; font-weight: inherit; text-align: inherit; text-decoration: inherit; box-sizing: border-box;\"><section style=\"line-height: 1.4; box-sizing: border-box; color: inherit;\"><section class=\"wweibrush\" data-brushtype=\"text\" style=\"border-color: rgb(117, 117, 118); color: rgb(117, 117, 118); font-size: 20px;\">标题</section></section><section class=\"wweibrush\" data-style=\"border-color: rgb(117, 117, 118); color: inherit; font-size: 12px;\" style=\"color: rgb(117, 117, 118); line-height: 1.4; margin-top: 5px; padding-left: 3px; font-size: 14px; font-weight: inherit; text-align: inherit; text-decoration: inherit; box-sizing: border-box; border-color: rgb(117, 117, 118);\"><p style=\"border-color: rgb(117, 117, 118); color: inherit; font-size: 12px;\">内容描述</p></section></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd9688c7003c', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0044', 'WXNRQ', '<section class=\"wwei-editor\"><p class=\"wweibrush\" data-brushtype=\"text\" style=\"margin: 2px 5px 0px 0px; padding: 0px;color:rgb(75, 92, 196); float:left; font-size:2.7em; line-height:1em; margin-right:5px\">秒刷</p><section class=\"wweibrush\" data-style=\"clear:none;\"><p style=\"clear:none;\">选择需要应用样式的文字，然后选择要使用的样式，即可实现秒刷效果。秒刷支持所有样式，如有使用遇到问题，欢迎加入QQ群<strong>390183835</strong>，将问题反馈给我们</p><p style=\"clear:none;color:red;\">回车使下沉占两行的文字独自为一个段落，然后再使用秒刷。建议下沉的为一个或者2个文字，不要多了。</p></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd97145a003d', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0045', 'WXNRQ', '<section class=\"wwei-editor\"><fieldset style=\"padding: 5px; border: 1px solid rgb(204, 204, 204); line-height: 24px; color: inherit; background-color: rgb(254, 254, 254);\"><legend style=\"margin: 0px 0px 0px 15px; padding: 0px; width: auto; font-size: 16px; color: inherit;\"><span style=\"color:inherit; margin:0px; padding:0px\"><strong style=\"color:rgb(102, 102, 102); margin:15px 8px 0px 0px\"><span class=\"wweibrush\" data-brushtype=\"text\" style=\"background-color:rgb(145, 168, 252); border-color:rgb(145, 168, 252); border-radius:5px; color:rgb(255, 255, 255); padding:4px 10px\">Wwei</span>&nbsp;</strong><span class=\"wweibrush\" data-brushtype=\"text\" style=\"border-color:rgb(145, 168, 252); color:rgb(145, 168, 252); margin:0px; padding:0px\">Wwei&nbsp;</span></span></legend><section class=\"wweibrush\" data-style=\"text-indent: 2em;;\" style=\"margin: 15px; margin-bottom: 0px; padding: 0px; line-height: 2em; color: rgb(62, 62, 62); font-size: 14px;\"><p style=\"text-indent: 2em; color: inherit;\">Wwei.cn平台是一个互联网运营平台，为运营者提供图文编辑工具，运营经验，收录公众号，定制开发微网站等服务，让运营更轻松高效。</p></section></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd97b2e0003e', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0046', 'WXNRQ', '<section class=\"wwei-editor\"><fieldset style=\"margin: 0.5em 0px; padding: 0px; max-width: 100%; box-sizing: border-box; color: rgb(62, 62, 62); font-family: inherit; font-size: medium; line-height: 25px; white-space: normal; border: none rgb(254, 253, 254); word-wrap: break-word !important;\"><section style=\"margin: 0px; padding: 3.8em 0px; max-width: 100%; box-sizing: border-box; width: 12.5em; height: 12.5em; float: right; text-align: center; word-wrap: break-word !important; background-image: url(http://pro.wwei.cn/Public/wxeditor/css/09.png); background-size: cover;\"><section style=\"margin: 0px 0px 0px 16px; padding: 0px; max-width: 100%; box-sizing: border-box; width: 140px; overflow: hidden; -webkit-transform: rotate(-13deg); font-size: 22px; font-family: inherit; font-weight: inherit; text-decoration: inherit; color: rgb(102, 102, 102); word-wrap: break-word !important;\"><section style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; word-wrap: break-word !important; color: inherit;\">Tips</section></section><section style=\"margin: 10px 20px; padding: 0px; max-width: 100%; box-sizing: border-box; width: 150px; overflow: hidden; -webkit-transform: rotate(-15deg); font-size: 14px; font-family: inherit; font-weight: inherit; text-decoration: inherit; color: rgb(102, 102, 102); word-wrap: break-word !important;\"><section class=\"wweibrush\" style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box; word-wrap: break-word !important; color: inherit;\"><p>我是图片不能换色哦</p></section></section></section></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbd983e95003f', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '内容0047', 'WXNRQ', '<section class=\"wwei-editor\"><section><section style=\"width:48%;display:inline-block;float:left;\"><section style=\"padding:20px 25px;border:1px solid #e7e7e7;text-align:center;\"><p><img src=\"http://pro.wwei.cn/Public/wxeditor/css/logo-135-web.png\" style=\"height:50px; margin-bottom:15px\"></p><h3 class=\"134title\" style=\"font-size:16px;font-weight:bold;margin:0 0 10px 0;\">微信编辑器</h3><section class=\"wweibrush\" data-style=\"clear:none;\"><p style=\"clear:none;margin:0 0;line-height:1.5em;\">生而排版</p><p style=\"clear:none;margin:0 0;line-height:1.5em;\"><span style=\"text-align:center\">为你而美</span></p></section></section></section><section style=\"margin-left:4%;width:48%;display:inline-block;text-align:center;\"><section style=\"padding:20px 25px;border:1px solid #e7e7e7;\"><p><img src=\"http://pro.wwei.cn/Public/wxeditor/css/0(6).jpg\" style=\"height:50px; margin-bottom:15px\"></p><h3 class=\"134title\" style=\"font-size:16px;font-weight:bold;margin:0 0 10px 0;\">秒刷</h3><section class=\"wweibrush\" data-style=\"clear:none;margin:0 0;line-height:1.5em;\"><p style=\"clear:none;margin:0 0;line-height:1.5em;\">一键排版</p><p style=\"clear:none;margin:0 0;line-height:1.5em;\">珍惜生命</p></section></section></section><p style=\"height:2px;clear:both;\"><br></p></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda3444c0040', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0001', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/2mbizgif.gif\">\r\n	');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda458ca0041', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0002', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/3mbizgif.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda4be320042', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0003', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/4mbizgif.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda5228c0043', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0004', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/5mbizgif.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda62cf70045', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0006', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/3zm.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda67cc30046', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0007', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda6be240047', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0008', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/640_4.png\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda721ea0048', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0009', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda768e00049', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0010', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif2.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda7bd01004a', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0011', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif3.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda81cdd004b', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0012', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif4.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda86fce004c', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0013', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/01.png\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda8f661004d', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0014', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/01.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbda9bcb2004e', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0015', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif7.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdaa18c1004f', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0016', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif8.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdaa75f70050', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0017', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/6c30531a8c718a37868c4822.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdaad1690051', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0018', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif9.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdab3b3f0052', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0019', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif1.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb012e00053', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '图片0020', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif10.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb0a9ea0054', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0021', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif18.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb0f0820055', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0022', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/02.png\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb13dbb0056', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0023', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/05.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb183e60057', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0024', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/i640.png\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb1c6d10058', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0025', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/s640.png\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb241f10059', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '图片0026', 'WXTP', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif6.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb5eb0d005a', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0004', 'WXGZYD', '<section class=\"wwei-editor\" style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal;\"><section class=\"awb-s1\" style=\"margin: 0px; padding: 0px; border: 0px; height: 0.1em; background-color: rgb(0, 187, 236);\"></section><section class=\"awb-s1\" style=\"margin: 0.3em 0px; padding: 0px; border: 0px; height: 0.3em; background-color: rgb(0, 187, 236);\"></section><section class=\"awb-s3\" style=\"margin: 0px; padding: 0.5em; border: 1px solid rgb(0, 187, 236); box-shadow: rgb(226, 226, 226) 0em 1em 0.1em -0.8em; font-size: 1em; line-height: 1.6em; background-color: white;\"><span style=\"margin: 0px; padding: 0px; border: 0px; color: inherit; font-size: 1em; font-family: inherit;\">点击下方</span><span style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(64, 84, 115); font-size: 1em; font-family: inherit;\">“阅读原文”</span><span style=\"margin: 0px; padding: 0px; border: 0px; color: inherit; font-size: 1em; font-family: inherit;\">查看更多</span></section><section class=\"awb-s4\" style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(0, 187, 236); font-size: 2em;\">↓↓↓</section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb6a806005b', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0005', 'WXGZYD', '<section class=\"wwei-editor\" style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal;\"><section style=\"margin: 0px; padding: 0px; border: 0px; height: 8em; white-space: nowrap; overflow: hidden;\"><img src=\"http://pro.wwei.cn/Public/wxeditor/images/6mbiz.png\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; max-height: 100%;\"></section><section style=\"margin: -7.2em 0.5em 5.5em; padding: 0.5em; border: 0px; height: 2em; font-size: 1em; line-height: 1.6em;\"><span style=\"margin: 0px; padding: 0px; border: 0px; color: inherit; overflow: hidden; font-size: 0.9em; font-family: inherit;\">点击下方</span><span style=\"margin: 0px; padding: 0px; border: 0px; color: rgb(64, 84, 115); overflow: hidden; font-size: 0.9em; font-family: inherit;\">“阅读原文”</span><span style=\"margin: 0px; padding: 0px; border: 0px; color: inherit; overflow: hidden; font-size: 0.9em; font-family: inherit;\">查看更多</span></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb71627005c', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0006', 'WXGZYD', '<section class=\"wwei-editor\"><fieldset style=\"margin: 0px; padding: 5px; max-width: 100%; color: rgb(62, 62, 62); font-size: medium; white-space: normal; border: 1px solid rgb(204, 204, 204); border-image-source: none; line-height: 24px; font-family: 宋体; orphans: 2; widows: 2; box-sizing: border-box !important; word-wrap: break-word !important; background-color: rgb(248, 247, 245);\"><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; max-width: 100%; min-height: 1em; white-space: pre-wrap; overflow: hidden; box-sizing: border-box !important; word-wrap: break-word !important;\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; line-height: 2em; color: rgb(118, 146, 60); box-sizing: border-box !important; word-wrap: break-word !important;\">阅读本文用了：</strong><img src=\"https://mmbiz.qlogo.cn/mmbiz/ZFvUqSQ0vAk8yU7xZ8hDKgeBPSwDpcPSjawGj2ekAp1GMQLq7RibhFgicdpCXj3B8oBeMAia74e13FotEsrX7zRAw/0\" data-w=\"16\" data-ratio=\"2.25\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"><img src=\"http://pro.wwei.cn/Public/wxeditor/images/t0.gif\" data-w=\"17\" data-ratio=\"2.1176470588235294\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"><strong style=\"font-family: 宋体; font-size: medium; orphans: 2; white-space: pre-wrap; widows: 2; margin: 0px; padding: 0px; max-width: 100%; line-height: 2em; color: rgb(118, 146, 60); box-sizing: border-box !important; word-wrap: break-word !important; background-color: rgb(248, 247, 245);\">分</strong><img src=\"http://pro.wwei.cn/Public/wxeditor/images/t0.gif\" data-w=\"16\" data-ratio=\"2.25\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"><img src=\"http://pro.wwei.cn/Public/wxeditor/images/t01.gif\" data-w=\"17\" data-ratio=\"2.1176470588235294\" width=\"16px\" style=\"margin: 0px; padding: 0px; border: 0px currentcolor; border-image-source: none; box-sizing: border-box !important; word-wrap: break-word !important; width: 16px !important; visibility: visible !important; height: auto !important; background-image: none; background-attachment: scroll; background-position: 0% 0%;\" _width=\"16px\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; line-height: 2em; color: rgb(118, 146, 60); box-sizing: border-box !important; word-wrap: break-word !important;\">秒，转发只需1秒</strong><br style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\"></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; max-width: 100%; word-wrap: normal; min-height: 1.5em; white-space: pre-wrap; overflow: hidden; line-height: 2em; box-sizing: border-box !important;\"><span style=\"margin: 0px; padding: 0px; max-width: 100%; color: rgb(149, 55, 52); box-sizing: border-box !important; word-wrap: break-word !important;\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; line-height: 18px; font-family: simsun, sans-serif; box-sizing: border-box !important; word-wrap: break-word !important;\"><span style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\"><strong style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\">?</strong></span>转载是一种智慧<strong style=\"margin: 0px; padding: 0px; max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;\">?</strong>分享是一种美德?</strong></span></p></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb79d16005d', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0007', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/6mbizgif.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb82de6005e', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0008', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/0sh.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb89e3b005f', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0009', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/2qgz.png\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb92c4d0060', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0010', 'WXGZYD', '<section class=\"wwei-editor\"><section style=\"border-color:transparent transparent rgb(0, 112, 192); border-style:solid; border-width:12px; float:none; font-size:medium; height:0px; margin:-15px auto 0px 90px; orphans:2; text-align:-webkit-auto; widows:2; width:0px\"></section><p class=\"wweibrush\" data-brushtype=\"text\" style=\"margin-top: -1px; margin-bottom: 0px;orphans: 2; widows: 2; min-height: 30px; visibility: visible; height: 30px; line-height: 30px; color: rgb(255, 255, 255); border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-right-radius: 3px; border-bottom-left-radius: 3px; box-shadow: rgba(0, 0, 0, 0.14902) 1px 1px 3px; font-size: 15px; text-align: center; background-color: rgb(0, 112, 192);font-size:14px; font-weight:bold\">点击标题下「蓝色微信名」可快速关注</p></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdb9a1750061', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '关注引导0011', 'WXGZYD', '<section class=\"wwei-editor\"><section style=\"max-width: 100%; color: rgb(62, 62, 62); margin: 0px; border-radius: 2em; height: 2.5em;line-height: 2.5em; color: rgb(255, 255, 255);  font-size: 13.33px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(189, 221, 34);\"><img src=\"http://pro.wwei.cn/Public/wxeditor/css/640_3.png\" style=\"border:0px rgb(189, 221, 34); box-sizing:border-box !important; float:left; height:auto !important; margin:5px 10px; vertical-align:top; visibility:visible !important; width:auto !important; word-wrap:break-word !important\"><p class=\"wweibrush\" style=\"max-width: 100%; color: inherit; display: inline-block; font-size:16px; \">关注一下又不会怀孕！</p></section></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbaa1b30062', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0012', 'WXGZYD', '<section class=\"wwei-editor\"><p style=\"padding: 5px 20px; margin-top: auto; margin-bottom: auto; font-family: 微软雅黑; white-space: normal; font-size: medium; max-width: 100%; word-wrap: normal; min-height: 1em; line-height: 25px; text-align: center; color: rgb(255, 255, 255); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-left-radius: 5px; border-bottom-right-radius: 5px; box-sizing: border-box !important; background-color: rgb(0, 187, 236);\"><span style=\"font-size: 12px;\"><span style=\"font-family: 微软雅黑, \'Microsoft YaHei\'; border-color: rgb(103, 61, 189);\">点击“阅读全文”，了解详情</span></span></p><p style=\"padding: 0px; margin: auto 55px; font-family: 微软雅黑; white-space: normal; font-size: medium; max-width: 100%; word-wrap: normal; min-height: 1em; color: rgb(62, 62, 62); line-height: 25px; border: 0px solid rgb(255, 0, 0); width: auto; box-sizing: border-box !important;\"><span style=\"max-width: 100%; border-color: rgb(0, 187, 236) transparent transparent; border-width: 20px; border-style: solid dashed dashed; width: 50px; bottom: -60px; height: 50px; font-size: 0px; word-wrap: break-word !important; box-sizing: border-box !important;\"></span></p></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbb88240063', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '关注引导0013', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/mbizgif.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbc61f70064', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0014', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/images/1mbizgif.gif\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbcd8eb0065', '管理员', 'admin', '2015-06-04 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '关注引导0015', 'WXGZYD', '<section class=\"wwei-editor\"><blockquote class=\"bkcolor\" style=\"margin: 0px; padding: 5px 20px; border: 2px rgb(201, 72, 121); font-family: 微软雅黑; line-height: 24px; white-space: normal; max-width: 100%; color: rgb(255, 255, 255); text-align: center; font-weight: 700; width: 180px; border-top-left-radius: 15px; border-top-right-radius: 15px; box-shadow: rgb(153, 153, 153) 0px -1px 6px; border-bottom-left-radius: 2px; border-bottom-right-radius: 2px; text-shadow: rgb(10, 10, 10) 0px -1px 2px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(201, 72, 121);\">广州教师微信</blockquote><blockquote style=\"margin: 0px; padding: 10px; border: 1px solid rgb(204, 204, 204); font-family: 微软雅黑; line-height: 24px; white-space: normal; font-size: 12px; max-width: 100%; color: rgb(62, 62, 62); border-top-left-radius: 0px; border-top-right-radius: 0px; box-shadow: rgb(204, 204, 204) 0px -1px 6px; border-bottom-left-radius: 10px; border-bottom-right-radius: 10px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(228, 228, 228);\"><span style=\"margin: 0px; padding: 0px; border: 0px rgb(201, 72, 121); max-width: 100%; color: rgb(201, 72, 121); word-wrap: break-word !important; box-sizing: border-box !important;\">微信号：</span><span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; font-weight: 700; word-wrap: break-word !important; box-sizing: border-box !important;\"></span><span class=\"bkcolor\" style=\"margin: 0px; padding: 2px 5px; border: 0px; max-width: 100%; font-weight: 700; color: rgb(255, 255, 255); word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(201, 72, 121);\">广州教师</span><span class=\"bfcolor\" style=\"margin: 0px; padding: 0px 0px 0px 5px; border: 0px rgb(201, 72, 121); max-width: 100%; color: rgb(201, 72, 121); word-wrap: break-word !important; box-sizing: border-box !important;\">(←长按复制)</span><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 10px 0px 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1.5em; white-space: pre-wrap; word-break: normal; color: rgb(102, 102, 102); line-height: 2em; box-sizing: border-box !important;\">全力打造杭城微信营销第一品牌</p></blockquote></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbe50db0066', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0016', 'WXGZYD', '<section class=\"wwei-editor\"><fieldset class=\"brcolor\" style=\"margin: 0px; padding: 5px 15px; border-width: 1px 0px; border-style: solid; border-color: rgb(0, 187, 236); border-image-source: none; font-family: 微软雅黑; line-height: 24px; white-space: normal; max-width: 100%; color: rgb(62, 62, 62); font-size: 14px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\"><legend class=\"brcolor\" style=\"margin: 0px; padding: 4px 10px; border: 1px solid rgb(0, 187, 236); border-image-source: none; max-width: 100%; color: rgb(34, 34, 34); font-size: 16px; word-wrap: break-word !important; box-sizing: border-box !important;\"><strong style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">如何关注</strong></legend><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;\">①复制“微信号或ID”，在“添加朋友”中粘贴搜索号码关注。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; box-sizing: border-box !important;\">②点击微信右上角的“+”，会出现“添加朋友”，进入“查找公众号”，输入以下公众号的名字，即可找到。</p></fieldset></section>');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbe9b710067', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0017', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/01.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbef6710068', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0018', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/02.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbf58560069', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0019', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/1d30.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdbfb636006a', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0020', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/03.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdc01c75006b', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0021', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/04.png\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdc085a3006c', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0022', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/04.jpg\">');
INSERT INTO `weixin_template` VALUES ('402880cd4dbd534c014dbdc14efc006d', '管理员', 'admin', '2015-06-04 00:00:00', null, null, null, '关注引导0023', 'WXGZYD', '<img src=\"http://pro.wwei.cn/Public/wxeditor/css/mmbizgif19.gif\">');
INSERT INTO `weixin_template` VALUES ('8af235d44db84b91014db84b91e10000', '管理员', 'admin', '2015-06-03 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '关注引导0001', 'WXGZYD', '<section style=\"margin: 15px 0px 0px; padding: 0px; border: 0px; color: rgb(68, 68, 68); font-family: 微软雅黑; font-size: 13px; line-height: 24px; white-space: normal; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n    <section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n        <section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n            <section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(34, 34, 34); font-family: 微软雅黑, arial, sans-serif; font-size: 14px; height: 75px; word-wrap: break-word !important; box-sizing: border-box !important; background-color: rgb(255, 255, 255);\">\r\n                <section class=\"main\" style=\"margin: 0px; padding: 5px; border: 2px solid rgb(0, 187, 236); max-width: 100%; border-top-left-radius: 50px; border-top-right-radius: 50px; border-bottom-right-radius: 50px; border-bottom-left-radius: 50px; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n                    <section style=\"margin: 0px; padding: 0px; border: 0px; display: inline-block; max-width: 100%; float: left; height: 60px; width: 60px; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n                        <img class=\"awb_avatar\" data-ratio=\"1\" data-w=\"60\" _width=\"60px\" src=\"https://mmbiz.qlogo.cn/mmbiz/mj9u1OBZRqP8EvePIzqrRIHCHOzYM4ngTyTSG1yHQmHf6GZ54zRFE0jKdFVTN5wictwGhmaj0XoMr8JlVglKFSg/0\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; border-top-left-radius: 30px; border-top-right-radius: 30px; border-bottom-right-radius: 30px; border-bottom-left-radius: 30px; float: left; word-wrap: break-word !important; box-sizing: border-box !important; height: auto !important; width: 60px !important; visibility: visible !important;\"/><img data-ratio=\"1\" data-w=\"20\" src=\"https://mmbiz.qlogo.cn/mmbiz/mj9u1OBZRqP8EvePIzqrRIHCHOzYM4ngVB5ussUreAlK3CMe1QzN4COMOicjefeVibtS6gXUZJww9e2NAa5EdG0A/0\" style=\"margin: -60px 0px 0px; padding: 0px; border: 0px; max-width: 100%; float: right; word-wrap: break-word !important; box-sizing: border-box !important; height: auto !important; width: auto !important; visibility: visible !important;\"/>\r\n                    </section>\r\n                    <section style=\"margin: 0px; padding: 0px 10px; border: 0px; display: inline-block; max-width: 100%; height: 60px; line-height: 30px; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n                        <section style=\"margin: 0px; padding: 0px; border-width: 0px 0px 1px; border-bottom-style: dashed; border-bottom-color: rgb(118, 118, 118); max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n                            <span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(0, 0, 0); font-weight: bold; word-wrap: break-word !important; box-sizing: border-box !important;\">点击「<span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(22, 179, 255); word-wrap: break-word !important; box-sizing: border-box !important;\">箭头所指处</span>」可快速关注</span>\r\n                        </section>\r\n                        <section style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n                            <span style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(0, 0, 0); word-wrap: break-word !important; box-sizing: border-box !important;\">微信号：<span class=\"awb_wxwechatid\" style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; color: rgb(187, 0, 0); word-wrap: break-word !important; box-sizing: border-box !important;\">XXXXXXXXX</span></span>\r\n                        </section>\r\n                    </section>\r\n                </section>\r\n                <section style=\"margin: -98px 0px 0px 80px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\">\r\n                    <p class=\"main\" style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; border-width: 12px; border-style: solid; border-color: transparent transparent rgb(0, 187, 236); max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; width: 0px; height: 0px; float: none; box-sizing: border-box !important;\">\r\n                        <br style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"/>\r\n                    </p>\r\n                    <p style=\"margin-top: -21px; margin-bottom: 0px; padding: 0px; border-width: 12px; border-style: solid; border-color: transparent transparent rgb(255, 255, 255); max-width: 100%; word-wrap: normal; min-height: 1em; white-space: pre-wrap; width: 0px; height: 0px; float: none; box-sizing: border-box !important;\">\r\n                        <br style=\"margin: 0px; padding: 0px; border: 0px; max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"/>\r\n                    </p>\r\n                </section>\r\n            </section>\r\n        </section>\r\n    </section>\r\n</section>\r\n<p>\r\n    <br/>\r\n</p>\r\n<p>\r\n    <br/>\r\n</p>');
INSERT INTO `weixin_template` VALUES ('8af235d44db84b91014db84c5aed0001', '管理员', 'admin', '2015-06-03 00:00:00', '管理员', 'admin', '2015-06-04 00:00:00', '内容0001', 'WXNRQ', '<blockquote class=\"bkcolor\" style=\"MAX-WIDTH: 100%; BORDER: rgb(225,225,225) 2px dotted;WHITE-SPACE: normal; WORD-SPACING: 0px;TEXT-TRANSFORM: none; COLOR: rgb(62,62,62); PADDING-BOTTOM: 10px; TEXT-ALIGN: justify; PADDING-TOP: 10px; FONT: medium/21px 微软雅黑; PADDING-LEFT: 20px; MARGIN: 0px; BORDER-LEFT: rgb(225,225,225) 2px dotted; ORPHANS: 2; WIDOWS: 2; LETTER-SPACING: normal; PADDING-RIGHT: 20px; BACKGROUND-COLOR: rgb(218,136,61); TEXT-INDENT: 0px; box-shadow: rgb(225, 225, 225) 2px 2px 1px;-webkit-text-size-adjust: none;border-radius:5px!important;\"><p style=\"margin:0;\">欢迎使用微信编辑器！</p></blockquote><p><br></p>\r\n');

-- ----------------------------
-- Table structure for weixin_texttemplate
-- ----------------------------
DROP TABLE IF EXISTS `weixin_texttemplate`;
CREATE TABLE `weixin_texttemplate` (
  `id` varchar(32) default NULL,
  `template_name` varchar(100) default NULL COMMENT '模板名称',
  `template_content` longtext COMMENT '模板内容',
  `jwid` varchar(64) default NULL COMMENT '公众号原始id',
  `create_by` varchar(64) default NULL COMMENT '创建人名称',
  `create_time` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '修改人名称',
  `update_time` datetime default NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文本模板表';

-- ----------------------------
-- Records of weixin_texttemplate
-- ----------------------------
INSERT INTO `weixin_texttemplate` VALUES ('4028810c6bb69a33016bb69a33600000', '欢迎关注', '欢迎您关注JEEWX，捷微管家是一款JAVA的开源微信管家，荣获2014年微信开发商大会第一名！平台涵盖：用户、消息、素材、统计、微网站、公众号、小程序官网等模块，极速满足公众号运营的各种需求，服务于万千微信公众号。\n\n JEECG论坛： www.jeecg.org\n 捷微官网 ： www.jeewx.com', 'gh_f268aa85d1c7', 'admin', '2019-07-03 14:51:27', 'admin', '2019-07-12 16:56:58');
INSERT INTO `weixin_texttemplate` VALUES ('4028810c6be55ffe016be56315800005', '技术文档', 'JeecgBoot文档:    http://jeecg-boot.mydoc.io\nJeewxBoot文档:  http://jeewx-boot.mydoc.io\n小程序开发： http://shop.jeewx.com/#/doc/rumen\n', 'gh_f268aa85d1c7', 'admin', '2019-07-12 16:53:24', null, null);



INSERT INTO `weixin_huodong_biz_jwid` (`id`, `table_name`, `table_remake`, `table_type`) VALUES ('ff80808152a2abd20153592394830052', 'jw_system_user_jwid', '用户公众号关系表', '1');
UPDATE `jw_system_project` SET  `hdurl`='${domain}/cms/index.do' WHERE (`id`='4e450f396b294d94a1ff29b50adb50a4');
delete from `jw_system_project` WHERE (`id`='402880e657bd3f9a0157bd67e91a0004');
