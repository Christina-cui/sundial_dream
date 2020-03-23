drop table t_user;
create table t_user (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `user_photo` varchar(60)  NOT NULL COMMENT '用户头像',
  `gender` varchar(10)  NOT NULL COMMENT '性别',
  `birth_date` varchar(20)  NULL COMMENT '出生日期',
  `status` int(1) DEFAULT '1' COMMENT '状态，0：禁用，1：可用',
  `email` varchar(50)  NOT NULL COMMENT '邮箱',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
	 PRIMARY KEY (`id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=881 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';

TRUNCATE table t_user;

insert into t_user values
(null,18852931531,"王二狗","Aa123456","touxiang1.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,18852936261,"张思锐","Aa123456","touxiang2.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,15542698021,"赵佛","Aa123456","touxiang3.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,13514552510,"李大炮","Aa123456","touxiang4.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,18052396423,"欧阳铁牛","Aa123456","touxiang5.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,19852364523,"皇甫翠花","Aa123456","touxiang6.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,15825394785,"笑呵呵","Aa123456","touxiang7.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,12215619874,"尼古拉斯赵四","Aa123456","touxiang8.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,18926545484,"亚历山大·翠花","Aa123456","touxiang9.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,13326022520,"施瓦辛格·花呆","Aa123456","touxiang10.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,19852234466,"道格森·狗蛋","Aa123456","touxiang11.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,13526587452,"爱新觉罗·玉凤","Aa123456","touxiang12.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,15015023202,"澳门之巅谢广坤","Aa123456","touxiang13.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,13901721386,"约翰尼.刘能","Aa123456","touxiang14.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,13916279520,"辛普森·二狗","Aa123456","touxiang15.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,13661636869,"凯瑟琳·翠芬","Aa123456","touxiang16.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,13611859558,"狗剩","Aa123456","touxiang17.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,16621303597,"二柱子","Aa123456","touxiang18.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,13636663631,"虎妞","Aa123456","touxiang19.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,18918290717,"狗娃","Aa123456","touxiang20.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,19821985008,"二妮","Aa123456","touxiang21.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,15502111116,"傻妮","Aa123456","touxiang22.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,13621769168,"臭蛋","Aa123456","touxiang23.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,19821999017,"狗娃子","Aa123456","touxiang24.jpg","1998-10-27","男",1,"1195732393@qq.com",null,null,0),
(null,13166448790,"丫蛋","Aa123456","touxiang25.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0),
(null,16621313602,"傻丫","Aa123456","touxiang26.jpg","1998-10-27","女",1,"1195732393@qq.com",null,null,0);


DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(32) DEFAULT NULL,
	`is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_activity_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动类型id',
  `type_name` varchar(20)  NOT NULL COMMENT '活动类型名称',
	`is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `t_activity_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `type_id` bigint(20) NOT NULL COMMENT '活动类型',
  `title` varchar(60)  NOT NULL COMMENT '活动主题',
  `activity_photo` varchar(60)  NOT NULL COMMENT '活动图片',
  `content` varchar(5000)  NOT NULL COMMENT '活动内容',
  `activity_time` timestamp  NOT NULL COMMENT '活动时间',
	`is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `t_sign_up` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报名id',
  `activity_id` bigint(20) NOT NULL COMMENT '报名的活动',
  `user_id` bigint(20)  NOT NULL COMMENT '报名人',
  `sign_up_vow` varchar(500)  NOT NULL COMMENT '报名宣誓',
  `sign_up_time` timestamp  NULL COMMENT '报名时间',
	`is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
  PRIMARY KEY (`sign_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `t_donation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '捐款id',
  `user_id` bigint(20)  NOT NULL COMMENT '捐款人',
  `donation_money` float NOT NULL COMMENT '捐款金额',
  `donation_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP	COMMENT '捐款时间',
  `donation_memo` varchar(500)  NULL COMMENT '捐款备注',
  `shen_he_state` int(7)  NOT NULL DEFAULT 0	COMMENT '审核状态 0-审核中  1-审核失败 2-审核成功',
	`is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
  PRIMARY KEY (`donation_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `t_leaveword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `leave_title` varchar(80)  NOT NULL COMMENT '留言标题',
  `leave_content` varchar(2000)  NOT NULL COMMENT '留言内容',
  `user_id` bigint(20)  NOT NULL COMMENT '留言人',
  `leave_time` timestamp  NULL COMMENT '留言时间',
  `reply_content` varchar(1000)  NULL COMMENT '管理回复',
  `reply_time` timestamp  NULL COMMENT '回复时间',
	`is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
  PRIMARY KEY (`leave_word_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `t_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `title` varchar(80)  NOT NULL COMMENT '新闻标题',
  `new_class` varchar(30)  NOT NULL COMMENT '新闻分类',
  `content` varchar(5000)  NOT NULL COMMENT '新闻内容',
  `publish_date` timestamp  NULL COMMENT '发布时间',
	`is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

ALTER TABLE t_activity_info ADD CONSTRAINT FOREIGN KEY (type_id) REFERENCES t_activity_type(type_id);
ALTER TABLE t_sign_up ADD CONSTRAINT FOREIGN KEY (activity_id) REFERENCES t_activity_info(activity_id);
ALTER TABLE t_sign_up ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES t_user(id);
ALTER TABLE t_donation ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES t_user(id);
ALTER TABLE t_leaveword ADD CONSTRAINT FOREIGN KEY (user_id) REFERENCES t_user(id);


