/*
Navicat MySQL Data Transfer

Source Server         : 118.89.223.144(支付)
Source Server Version : 50173
Source Host           : 118.89.223.144:3306
Source Database       : h5huodong

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-10-10 17:51:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_ad
-- ----------------------------
DROP TABLE IF EXISTS `cms_ad`;
CREATE TABLE `cms_ad` (
  `id` varchar(36) NOT NULL,
  `create_name` varchar(50) DEFAULT NULL COMMENT 'createName',
  `create_by` varchar(50) DEFAULT NULL COMMENT 'createBy',
  `create_date` datetime DEFAULT NULL COMMENT 'createDate',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `image_href` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `is_show` int(1) DEFAULT NULL COMMENT '是否显示，0不显示，1显示',
  `resume` varchar(255) DEFAULT NULL COMMENT '简述',
  `link` varchar(500) DEFAULT NULL COMMENT '链接',
  `sort` int(2) DEFAULT NULL COMMENT '顺序',
  `html_link` varchar(255) DEFAULT NULL COMMENT 'H5网站网址',
  `main_id` varchar(255) DEFAULT NULL COMMENT '站点ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `image_href` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `summary` varchar(1000) DEFAULT NULL COMMENT '摘要',
  `content_html` longtext,
  `content` longtext COMMENT '内容',
  `column_id` varchar(36) DEFAULT NULL COMMENT '栏目id',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `publish` varchar(32) DEFAULT NULL COMMENT '是否发布',
  `publish_date` datetime DEFAULT NULL COMMENT '发布时间',
  `author` varchar(32) DEFAULT NULL COMMENT '作者',
  `label` varchar(200) DEFAULT NULL COMMENT '标签',
  `serial_number` int(10) DEFAULT NULL COMMENT '序号',
  `code` varchar(50) DEFAULT NULL COMMENT '文章编码',
  `is_link` int(1) DEFAULT NULL COMMENT '是否链接，0没有链接，1链接',
  `link` varchar(300) DEFAULT NULL COMMENT '链接地址',
  `is_show` int(1) DEFAULT NULL COMMENT '是否在明细页面显示 0不显示，1显示',
  `main_id` varchar(255) DEFAULT NULL COMMENT '站点ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Table structure for cms_menu
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `id` varchar(36) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_name` varchar(255) DEFAULT NULL,
  `image_href` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `image_name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `name` varchar(20) DEFAULT NULL COMMENT '栏目名称',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `parent_code` varchar(36) DEFAULT NULL COMMENT '父节点',
  `href` varchar(100) DEFAULT NULL COMMENT '地址',
  `template_code` varchar(100) DEFAULT NULL COMMENT '模版样式',
  `serial_number` int(10) DEFAULT NULL COMMENT '序号',
  `content` varchar(1000) DEFAULT NULL COMMENT '描述',
  `main_id` varchar(255) DEFAULT NULL COMMENT '站点ID',
  `html_link` varchar(255) DEFAULT NULL COMMENT 'H5链接地址',
  `is_show` int(1) DEFAULT NULL COMMENT '是否显示0显示，1显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表';

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` varchar(36) NOT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_name` varchar(50) DEFAULT NULL,
  `company_tel` varchar(50) DEFAULT NULL COMMENT '公司电话',
  `site_logo` varchar(200) DEFAULT NULL COMMENT '站点Logo',
  `site_name` varchar(100) DEFAULT NULL COMMENT '站点名称',
  `site_template_style` varchar(50) DEFAULT NULL COMMENT '站点模板',
  `record_information` varchar(5000) DEFAULT NULL COMMENT '备案信息',
  `longitude` varchar(50) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(50) DEFAULT NULL COMMENT '纬度',
  `jwid` varchar(64) DEFAULT NULL COMMENT '微信公众号',
  `hdurl` varchar(255) DEFAULT NULL COMMENT '长链接',
  `short_url` varchar(255) DEFAULT NULL COMMENT '短链接',
  `project_code` varchar(32) DEFAULT NULL COMMENT '项目编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点';

-- ----------------------------
-- Table structure for cms_style
-- ----------------------------
DROP TABLE IF EXISTS `cms_style`;
CREATE TABLE `cms_style` (
  `id` varchar(36) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_name` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_name` varchar(50) DEFAULT NULL,
  `template_name` varchar(100) DEFAULT NULL COMMENT '模板名称',
  `review_img_url` varchar(100) DEFAULT NULL COMMENT '预览图',
  `template_url` varchar(200) DEFAULT NULL COMMENT '模板地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点模板';


-- 项目管理
DELETE FROM jw_system_project WHERE code='cms';
INSERT INTO `jw_system_project` (`id`, `code`, `name`, `img`, `discribe`, `bjurl`, `hdurl`, `gif_url`, `hdzs_url`, `sc_type`, `detail`, `entrance`, `type`, `sort`, `project_classify_id`, `application_type`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('4e450f396b294d94a1ff29b50adb50a4', 'cms', '微网站', '', '微网站', 'cms/back/cmsSite/toAdd.do', 'http://www.h5huodong.com/cms/index.do', '', '', '1', NULL, '', '1', '', 'ff808081566df0a601566df251230006', NULL, NULL, NULL, NULL, NULL);

-- 菜单
INSERT INTO `jw_system_auth` (`id`, `auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`, `icon_type`) VALUES ('1538057887', '27a9519ec26011e8aa5852540003411a', '微网站', NULL, '0', NULL, '', '', '211', '1', NULL, '0', 'fa-star fa-lg');
INSERT INTO `jw_system_auth` (`id`, `auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`, `icon_type`) VALUES ('1538057922', '3c6fe69cc26011e8aa5852540003411a', '站点管理', NULL, '0', NULL, '/cms/back/cmsSite/list.do', '27a9519ec26011e8aa5852540003411a', '1', '2', NULL, '0', '');
INSERT INTO `jw_system_auth` (`id`, `auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`, `icon_type`) VALUES ('1538206989', '4f388b42c3bb11e8aa5852540003411a', '广告管理', NULL, '0', NULL, '/cms/back/cmsAd/list.do', '27a9519ec26011e8aa5852540003411a', '2', '2', NULL, '0', '');
INSERT INTO `jw_system_auth` (`id`, `auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`, `icon_type`) VALUES ('1538207022', '6370158ac3bb11e8aa5852540003411a', '栏目管理', NULL, '0', NULL, '/cms/back/cmsMenu/list.do', '27a9519ec26011e8aa5852540003411a', '3', '2', NULL, '0', '');
INSERT INTO `jw_system_auth` (`id`, `auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`, `icon_type`) VALUES ('1538207049', '736e3bbac3bb11e8aa5852540003411a', '文章管理', NULL, '0', NULL, '/cms/back/cmsArticle/list.do', '27a9519ec26011e8aa5852540003411a', '4', '2', NULL, '0', '');

-- 演示官网
INSERT INTO `cms_site` (`id`, `update_name`, `update_date`, `create_date`, `create_name`, `company_tel`, `site_logo`, `site_name`, `site_template_style`, `record_information`, `longitude`, `latitude`, `jwid`, `hdurl`, `short_url`, `project_code`) VALUES ('ff808081661e74bb01661f12de5e03d6', NULL, '2018-10-10 16:28:18', '2018-09-28 15:27:02', 'admin', '', 'logo1539160087728.png', '捷微官网', 'wxhome', '<p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">中国·北京·朝阳区科荟前街1号院奥林佳泰大厦9层929</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">公司：北京囯炬信息技术有限公司</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">邮编：100190</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">电话：010-64808099</span></p><p style=\"color: rgb(0, 0, 0); white-space: normal;\"><span lucida=\"\" courier=\"\" font-size:=\"\" white-space:=\"\" background-color:=\"\" style=\"color: rgb(34, 34, 34);\">邮箱：jeecg@sina.com</span></p><p><br/></p>', '116.381319', '40.015457', 'gh_f268aa85d1c7', 'http://www.h5huodong.com/cms/index.do?mainId=ff808081661e74bb01661f12de5e03d6', 'https://w.url.cn/s/AoUtL1H', 'cms');


-- author:taoYan date:20181015 for：分享配置 增加字段
ALTER TABLE `cms_site`
ADD COLUMN `share_friend_title`  varchar(50) NULL COMMENT '分享给朋友标题' AFTER `project_code`,
ADD COLUMN `share_friend_desc`  varchar(255) NULL COMMENT '分享给朋友描述' AFTER `share_friend_title`,
ADD COLUMN `share_friend_circle`  varchar(50) NULL COMMENT '分享到朋友圈标题' AFTER `share_friend_desc`,
ADD COLUMN `appid`  varchar(100) NULL COMMENT 'appid' AFTER `share_friend_circle`;
-- author:taoYan date:20181015 for：分享配置 增加字段


-- author:zhaofei date:20190625 for：背景颜色 增加字段
ALTER TABLE `cms_site`
ADD COLUMN `site_background_img`  varchar(255) NULL COMMENT '站点背景图片'AFTER `appid`;
ALTER TABLE `cms_menu`
ADD COLUMN `column_color`  varchar(255) NULL COMMENT '背景颜色'AFTER `is_show`;
-- author:zhaofei date:20190626 for：背景颜色 增加字段

-- author:zhaofei date:20190822 for：修改cms_site表的hdurl
UPDATE `cms_site` SET `hdurl`='${domain}/cms/index.do&actId=ff808081661e74bb01661f12de5e03d6&jwid=gh_f268aa85d1c7' WHERE (`id`='ff808081661e74bb01661f12de5e03d6');
-- author:zhaofei date:20190822 for：修改cms_site表的hdurl

-- author:zhaofei date:20190822 for：修改jw_system_project表的hdurl
UPDATE `jw_system_project` SET `hdurl`='${domain}/cms/index.do' WHERE (`id`='4e450f396b294d94a1ff29b50adb50a4');
-- author:zhaofei date:20190822 for：修改jw_system_project表的hdurl

-- author:zhaofei date:20191024 for：更新项目默认图片
UPDATE `jw_system_project` SET  `logo_img`='http://static.h5huodong.com/upload/img/system/e163d5a22291419a9b13ca1d4f84e6bc.jpg', `img`='http://static.h5huodong.com/upload/img/system/aa3233c5fc6c45bf9ebaa83fd9f4fb13.jpg', `gif_url`='http://static.h5huodong.com/upload/img/system/aa3233c5fc6c45bf9ebaa83fd9f4fb13.jpg' WHERE (`id`='4e450f396b294d94a1ff29b50adb50a4');
-- author:zhaofei date:20191024 for：更新项目默认图片

-- 将主表信息插入微信活动jwid表
INSERT INTO `weixin_huodong_biz_jwid` (`id`, `table_name`, `table_remake`,`table_type` ) VALUES ('a875c7881bce4cb8a11b1ec5c22446d1', 'cms_site', '微网站', '2');

