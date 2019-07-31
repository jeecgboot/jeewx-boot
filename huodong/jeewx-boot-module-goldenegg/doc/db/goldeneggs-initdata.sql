SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `wx_act_goldeneggs`;
CREATE TABLE `wx_act_goldeneggs` (
  `id` varchar(100) NOT NULL COMMENT 'ID',
  `title` varchar(400) DEFAULT NULL COMMENT '活动名称',
  `description` text COMMENT '活动描述',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `template_code` varchar(255) DEFAULT NULL COMMENT '模板编码',
  `banner` varchar(100) DEFAULT NULL COMMENT '背景图',
  `count` int(10) DEFAULT NULL COMMENT '抽奖次数',
  `hdurl` varchar(200) DEFAULT NULL COMMENT '入口地址',
  `foucs_user_can_join` varchar(1) DEFAULT '0' COMMENT '是否关注可参加 0否 1是',
  `binding_mobile_can_join` varchar(1) DEFAULT '0' COMMENT '是否绑定手机可参加 0否 1是',
  `num_per_day` int(11) DEFAULT '0' COMMENT '每日抽奖次数',
  `prize_status` varchar(1) DEFAULT '0' COMMENT '是否中奖可参与 0：中奖可继续参与 1:中奖不可参与',
  `jwid` varchar(64) DEFAULT NULL COMMENT '公众号原始id',
  `project_code` varchar(32) DEFAULT NULL COMMENT '活动编码',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `short_url` varchar(255) DEFAULT NULL COMMENT '短链接',
  PRIMARY KEY (`id`),
  KEY `idx_createby` (`create_by`),
  KEY `idx_jwid` (`jwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砸金蛋活动表';

INSERT INTO `wx_act_goldeneggs` (`id`, `title`, `description`, `starttime`, `endtime`, `template_code`, `banner`, `count`, `hdurl`, `foucs_user_can_join`, `binding_mobile_can_join`, `num_per_day`, `prize_status`, `jwid`, `project_code`, `create_by`, `create_time`, `short_url`) VALUES ('2c9b8381552a77c901552a77c9020000', '砸金蛋', '<p><strong>一、活动时间：</strong><br/>2016年1月1日至2016年12月31日<br/><strong>二、抽奖方式：</strong><br/>每人每天可抽3次。<br/><strong>三、兑奖须知：</strong><br/>1、用户中奖后请点击领奖，填写姓名、电话、地址，或进入“我的奖品”页面点击兑奖填写。<br/>2、奖品将在10日内送出，到货以实际时间为准。<br/>3、本活动由捷微H5平台提供技术支持，最终解释权归活动主办方所有。<br/></p>', '2016-06-07 00:00:00', '2019-10-22 00:00:00', NULL, '', '1000', 'http://h5.jeecg.com/jeewx/weixinLinkController.do?link&id=ff808081551fc4fe01554cc514067e25&actId=2c9b8381552a77c901552a77c9020000&jwid=gh_f268aa85d1c7', '0', '', '10', '1', 'gh_f268aa85d1c7', 'goldeneggs', 'admin', '2016-11-07 15:18:13', NULL);

DROP TABLE IF EXISTS `wx_act_goldeneggs_awards`;
CREATE TABLE `wx_act_goldeneggs_awards` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT 'ID',
  `awards_name` varchar(200) DEFAULT NULL COMMENT '奖项名称',
  `jwid` varchar(64) DEFAULT NULL COMMENT '公众号原始ID',
  `awards_value` int(11) DEFAULT NULL COMMENT '奖项值',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_jwid_creteby_value` (`jwid`,`awards_value`,`create_by`),
  KEY `idx_createby` (`create_by`),
  KEY `idx_jwid` (`jwid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砸金蛋奖项表';

INSERT INTO `wx_act_goldeneggs_awards` (`id`, `awards_name`, `jwid`, `awards_value`, `create_by`) VALUES ('402880ee5127c20a0151281efbd00006', '一等奖', 'gh_f268aa85d1c7', '10', 'admin');
INSERT INTO `wx_act_goldeneggs_awards` (`id`, `awards_name`, `jwid`, `awards_value`, `create_by`) VALUES ('402880ee5127c20a0151281f395f000', '二等奖', 'gh_f268aa85d1c7', '50', 'admin');
INSERT INTO `wx_act_goldeneggs_awards` (`id`, `awards_name`, `jwid`, `awards_value`, `create_by`) VALUES ('402880ee5127c20a0151281f69740008', '三等奖', 'gh_f268aa85d1c7', '100', 'admin');


DROP TABLE IF EXISTS `wx_act_goldeneggs_prizes`;
CREATE TABLE `wx_act_goldeneggs_prizes` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT 'ID',
  `name` varchar(200) DEFAULT NULL COMMENT '奖品名称',
  `img` varchar(50) DEFAULT NULL COMMENT '奖品图片',
  `jwid` varchar(64) DEFAULT NULL COMMENT '公众号原始id',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砸金蛋奖品表';

INSERT INTO `wx_act_goldeneggs_prizes` (`id`, `name`, `img`, `jwid`, `create_by`) VALUES ('402880ee5127c20a01512823001d000c', 'iphone6 plus', '/content/goldeneggs/img/default_image.png', 'gh_f268aa85d1c7', 'admin');
INSERT INTO `wx_act_goldeneggs_prizes` (`id`, `name`, `img`, `jwid`, `create_by`) VALUES ('402880ee5127c20a01512823417c000d', 'iPhone6s', '/content/goldeneggs/img/default_image.png', 'gh_f268aa85d1c7', 'admin');
INSERT INTO `wx_act_goldeneggs_prizes` (`id`, `name`, `img`, `jwid`, `create_by`) VALUES ('402880ee5127c20a015128237268000e', '华为', '/content/goldeneggs/img/default_image.png', 'gh_f268aa85d1c7', 'admin');

DROP TABLE IF EXISTS `wx_act_goldeneggs_record`;
CREATE TABLE `wx_act_goldeneggs_record` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `act_id` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `openid` varchar(100) NOT NULL COMMENT '抽奖人openid',
  `nickname` varchar(200) DEFAULT NULL COMMENT '昵称',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '玩家头像',
  `create_time` datetime NOT NULL COMMENT '抽奖时间',
  `awards_id` varchar(36) DEFAULT NULL COMMENT '奖项ID',
  `realname` varchar(30) DEFAULT NULL COMMENT '收货人姓名',
  `phone` varchar(15) DEFAULT NULL COMMENT '收货人手机号',
  `address` varchar(15) DEFAULT NULL COMMENT '收货地址',
  `seq` int(10) DEFAULT NULL COMMENT '抽奖序号',
  `jwid` varchar(64) NOT NULL COMMENT '公众号原始id',
  `prizes_name` varchar(200) DEFAULT NULL COMMENT '奖品名称',
  `awards_name` varchar(200) DEFAULT NULL COMMENT '奖项名称',
  `prizes_state` varchar(32) DEFAULT NULL COMMENT '中奖状态(0未中奖，1中奖)',
  `code` varchar(100) DEFAULT NULL COMMENT '中奖码',
  `recieve_status` varchar(10) DEFAULT NULL COMMENT '核销状态（1已核销）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_actid_seq` (`act_id`,`seq`,`openid`),
  UNIQUE KEY `act_id` (`act_id`,`awards_id`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砸金蛋参与记录表';

DROP TABLE IF EXISTS `wx_act_goldeneggs_registration`;
CREATE TABLE `wx_act_goldeneggs_registration` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `act_id` varchar(32) NOT NULL COMMENT '活动id',
  `openid` varchar(100) NOT NULL COMMENT '抽奖人openid',
  `nickname` varchar(200) DEFAULT NULL COMMENT '抽奖人昵称',
  `awards_num` int(10) DEFAULT '0' COMMENT '抽奖次数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` varchar(32) DEFAULT NULL COMMENT '更新时间',
  `awards_status` varchar(2) DEFAULT '0' COMMENT '抽奖状态0:未抽奖;1:已抽奖;',
  `jwid` varchar(64) DEFAULT NULL COMMENT '公众号原始ID',
  `remain_num_day` int(11) DEFAULT NULL COMMENT '每天的剩余次数',
  PRIMARY KEY (`id`),
  KEY `idx_openidactid` (`openid`,`act_id`),
  KEY `idx_createtime` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砸金蛋参与人员表';


DROP TABLE IF EXISTS `wx_act_goldeneggs_relation`;
CREATE TABLE `wx_act_goldeneggs_relation` (
  `id` varchar(36) NOT NULL COMMENT 'ID',
  `act_id` varchar(36) DEFAULT NULL COMMENT '活动ID',
  `prize_id` varchar(36) DEFAULT NULL COMMENT '奖品ID',
  `award_id` varchar(36) DEFAULT NULL COMMENT '奖项ID',
  `amount` int(10) DEFAULT NULL COMMENT '数量',
  `remain_num` int(10) DEFAULT NULL COMMENT '剩余数量',
  `probability` double(5,2) DEFAULT '0.00' COMMENT '中奖概率',
  `jwid` varchar(64) DEFAULT NULL COMMENT '公众号原始ID',
  PRIMARY KEY (`id`),
  KEY `idx_actid` (`act_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砸金蛋奖品配置表';

INSERT INTO `wx_act_goldeneggs_relation` (`id`, `prize_id`, `act_id`, `jwid`, `award_id`, `amount`, `remain_num`, `probability`) VALUES ('402880ec58afbd900158afff43d00014', '402880ee5127c20a01512823001d000c', '2c9b8381552a77c901552a77c9020000', 'gh_f268aa85d1c7', '402880ee5127c20a0151281efbd00006', '10', '10', '0.10');
INSERT INTO `wx_act_goldeneggs_relation` (`id`, `prize_id`, `act_id`, `jwid`, `award_id`, `amount`, `remain_num`, `probability`) VALUES ('402880ec58afbd900158afff43d30015', '402880ee5127c20a01512823417c000d', '2c9b8381552a77c901552a77c9020000', 'gh_f268aa85d1c7', '402880ee5127c20a0151281f395f000', '10', '10', '0.10');
INSERT INTO `wx_act_goldeneggs_relation` (`id`, `prize_id`, `act_id`, `jwid`, `award_id`, `amount`, `remain_num`, `probability`) VALUES ('402880ec58afbd900158afff43d60016', '402880ee5127c20a015128237268000e', '2c9b8381552a77c901552a77c9020000', 'gh_f268aa85d1c7', '402880ee5127c20a0151281f69740008', '10', '10', '0.10');

-- 活动文本
delete from jw_system_act_txt where act_code='2c9b8381552a77c901552a77c9020000';
INSERT INTO `jw_system_act_txt` (`id`, `code`, `type`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('3fea074a977b11e69f2d28d2440d0560', 'index.title', 'hidden', '砸金蛋', '标题', '2c9b8381552a77c901552a77c9020000', NULL, NULL, '2016-10-08 10:54:36', NULL, NULL);
INSERT INTO `jw_system_act_txt` (`id`, `code`, `type`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('3feb9917977b11e69f2d28d2440d0560', 'statistics', 'hidden', 'var _hmt = _hmt || [];\n(function() {\n  var hm = document.createElement(\"script\");\n  hm.src = \"//hm.baidu.com/hm.js?b64fae1007b3ee9b0430e5a20b670f78\";\n  var s = document.getElementsByTagName(\"script\")[0]; \n  s.parentNode.insertBefore(hm, s);\n})();', '统计', '2c9b8381552a77c901552a77c9020000', NULL, NULL, '2016-10-08 10:54:36', NULL, '2016-08-19 10:27:05');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `type`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('3ff11ca0977b11e69f2d28d2440d0560', 'tip.shareFriendCircle', NULL, '砸出金花四溅拿大奖！积攒多年的人品终于有用了，你也快来抽奖吧！', '分享朋友圈标题', '2c9b8381552a77c901552a77c9020000', NULL, NULL, '2016-10-08 10:54:36', NULL, '2016-08-19 10:27:50');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `type`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('3ff35479977b11e69f2d28d2440d0560', 'tip.shareFriendTile', NULL, '砸出金花四溅拿大奖', '分享给朋友标题', '2c9b8381552a77c901552a77c9020000', NULL, NULL, '2016-10-08 10:54:36', NULL, '2016-08-19 10:28:01');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `type`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('3ff58a46977b11e69f2d28d2440d0560', 'tip.shareFriendDesc', NULL, '积攒多年的人品终于有用了，你也快来抽奖吧！', '分享给好友描述', '2c9b8381552a77c901552a77c9020000', NULL, NULL, '2016-10-08 10:54:36', NULL, '2016-08-19 10:28:13');
INSERT INTO `jw_system_act_txt` (`id`, `code`, `type`, `content`, `discribe`, `act_code`, `jwid`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('3ff7f0b0977b11e69f2d28d2440d0560', 'img.share', 'img', 'http://h5huodong.com/content/goldeneggs/img/fx.png', '分享小图', '2c9b8381552a77c901552a77c9020000', NULL, NULL, '2016-10-08 10:54:36', NULL, '2016-08-19 10:30:14');

-- 项目管理
DELETE FROM jw_system_project WHERE code='goldeneggs';
INSERT INTO `jw_system_project` (`id`, `code`, `name`, `img`, `discribe`, `bjurl`, `hdurl`, `gif_url`, `hdzs_url`, `sc_type`, `detail`, `entrance`, `type`, `sort`, `project_classify_id`, `application_type`, `creat_name`, `creat_time`, `update_name`, `update_time`) VALUES ('ff8080815553071d015553071d420000', 'goldeneggs', '砸金蛋', '109.jpg', '砸金蛋是在庆典活动上非常受欢迎的一个项目。此项活动可以烘托氛围，提高人们的参与度。商家可在H5活动平台中通过简单配置奖品，中奖概率即可发布活动。用户参与活动后，商家可在后台查看抽奖和中奖记录。', 'goldeneggs/back/wxActGoldeneggs/toAdd.do', '/linksucai/link.do?linkid=402880e657bd3f9a0157bd67e91a0004', '109.jpg', 'goldeneggs/new/toGoldenegg.do?openid=${openid}&appid=${appid}', '1', '<p>&nbsp;&nbsp;&nbsp;用户参与活动页面后，点击小锤即可进行抽奖活动，用户也可以查看自己的中奖记录和获奖名单</p><p><br/></p><p><img src=\"http://h5huodong.com/upload/20160822/60591471860388868.png\" style=\"width:50%;height:50%;float:left\"/><img src=\"http://h5huodong.com/upload/20160822/6911471860393889.png\" style=\"width:50%;height:50%;float:left\"/></p><p>\n	</p><p>\n	</p><p>\n	</p><p>\n	</p><p>\n	</p><p>\n	</p><p>\n	</p>', 'https://w.url.cn/s/ASIFtSi', '1', '04', 'ff808081566df0a601566df0a69a0000', '', NULL, '2016-06-15 15:47:03', NULL, NULL);

-- 菜单管理
INSERT INTO `jw_system_auth` (`id`, `auth_id`, `auth_name`, `is_logs`, `auth_type`, `auth_desc`, `auth_contr`, `parent_auth_id`, `sort_no`, `biz_level`, `leaf_ind`, `del_stat`, `icon_type`) VALUES ('1533713853', 'e9d7b3469add11e8b9ec3ca0672e915d', '砸金蛋', NULL, '0', NULL, '/goldeneggs/back/wxActGoldeneggs/list.do', 'aa735943eb4410368c0028dacd75e20f', '4', '2', NULL, '0', '');

-- 将主表信息插入微信活动jwid表
INSERT INTO `weixin_huodong_biz_jwid` (`id`, `table_name`, `table_remake`, `table_type`) VALUES ('ff80808152a2abd20153592394830005', 'wx_act_goldeneggs', '砸金蛋活动表', '2');
INSERT INTO `weixin_huodong_biz_jwid` (`id`, `table_name`, `table_remake`, `table_type`) VALUES ('ff80808152a2abd20153592394830030', 'wx_act_goldeneggs_awards', '砸金蛋奖项表', '1');
INSERT INTO `weixin_huodong_biz_jwid` (`id`, `table_name`, `table_remake`, `table_type`) VALUES ('ff80808152a2abd20153592394830031', 'wx_act_goldeneggs_prizes', '砸金蛋奖品表', '1');


-- author: liushaoqian----------date:20181010--------for:主表新增是否有额外的抽奖机会字段
ALTER TABLE `wx_act_goldeneggs`
ADD COLUMN `extra_lucky_draw`  varchar(255) NULL  COMMENT '是否有额外的抽奖机会，0：有，1没有' AFTER `short_url`;
-- author: liushaoqian----------date:20181010--------for:主表新增是否有额外的抽奖机会字段

-- author: liushaoqian----------date:20181010--------for:新增分享记录表
CREATE TABLE `wx_act_goldeneggs_share_record` (
  `id` varchar(32) NOT NULL COMMENT '序号',
  `act_id` varchar(32) DEFAULT NULL COMMENT '活动id',
  `openid` varchar(64) DEFAULT NULL COMMENT 'openid',
  `rel_date` date DEFAULT NULL COMMENT '分享时间',
  `type` varchar(10) DEFAULT NULL COMMENT '分享类型：0朋友，1朋友圈',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_actid_openid_reldate` (`act_id`,`openid`,`rel_date`),
  KEY `idx_actid_openid_reldate_type` (`act_id`,`openid`,`rel_date`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分享记录表';
-- author: liushaoqian----------date:20181010--------for:新增分享记录表

-- author: liushaoqian----------date:20181010--------for:奖品图片字段补旧数据
UPDATE wx_act_goldeneggs_prizes SET img='/content/goldeneggs/img/default_image.png' WHERE img IS NULL OR img='';
-- author: liushaoqian----------date:20181010--------for:奖品图片字段补旧数据

-- author: liushaoqian----------date:20181015--------for:地址字段太短修改
ALTER TABLE `wx_act_goldeneggs_record`
MODIFY COLUMN `address`  varchar(255)  NULL DEFAULT NULL COMMENT '收货地址' AFTER `phone`;
-- author: liushaoqian----------date:20181015--------for:地址字段太短修改

-- author: liushaoqian----------date:20181015--------for:是否分享得次数字段设置默认值
UPDATE wx_act_goldeneggs SET extra_lucky_draw='1';
-- author: liushaoqian----------date:20181015--------for:是否分享得次数字段设置默认值

-- author: liushaoqian----------date:20181024--------for:砸金蛋修改概率字段长度
ALTER TABLE `wx_act_goldeneggs_relation`
MODIFY COLUMN `probability`  decimal(10,6) NULL DEFAULT 0.000000 COMMENT '中奖概率' AFTER `remain_num`;
-- author: liushaoqian----------date:20181024--------for:砸金蛋修改概率字段长度

-- author: liushaoqian----------date:20181025--------for:增加砸金蛋审核员表
CREATE TABLE `wx_act_goldeneggs_verify` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'id',
  `act_id` varchar(32) DEFAULT NULL COMMENT '活动id',
  `openid` varchar(100) DEFAULT NULL COMMENT '核销员id',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（0启用/1未启用）',
  `headimg` varchar(500) DEFAULT NULL COMMENT '微信头像',
  `nickname` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砸金蛋审核员表';
-- author: liushaoqian----------date:20181025--------for:增加砸金蛋审核员表

-- author: liushaoqian----------date:20181025--------for:砸金蛋记录表增加字段
ALTER TABLE `wx_act_goldeneggs_record`
ADD COLUMN `recieve_time`  datetime NULL COMMENT '核销时间' AFTER `recieve_status`,
ADD COLUMN `verify_id`  varchar(32) NULL COMMENT '核销员id' AFTER `recieve_time`;
-- author: liushaoqian----------date:20181025--------for:砸金蛋记录表增加字段

-- author: liushaoqian----------date:20181122--------for:砸金蛋记录表增加字段奖项配置id
ALTER TABLE `wx_act_goldeneggs_record`
ADD COLUMN `relation_id`  varchar(32) NULL COMMENT '奖品配置id' AFTER `verify_id`;
-- author: liushaoqian----------date:20181122--------for:砸金蛋记录表增加字段奖项配置id
