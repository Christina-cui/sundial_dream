drop table if EXISTS user;

create table user (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
 `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `status` int(1) DEFAULT '1' COMMENT '状态，0：禁用，1：可用',
	 `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int(1) DEFAULT '0' COMMENT '是否删除，1：删除；0：正常',
	 PRIMARY KEY (`id`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=881 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';


TRUNCATE table user;

insert into user values
(null,18852931531,"王二狗","Aa123456",1,null,null,0),
(null,18852936261,"张思锐","Aa123456",1,null,null,0),
(null,15542698021,"赵佛","Aa123456",1,null,null,0),
(null,13514552510,"李大炮","Aa123456",1,null,null,0),
(null,18052396423,"欧阳铁牛","Aa123456",1,null,null,0),
(null,19852364523,"皇甫翠花","Aa123456",1,null,null,0),
(null,15825394785,"笑呵呵","Aa123456",1,null,null,0),
(null,12215619874,"尼古拉斯赵四","Aa123456",1,null,null,0),
(null,18926545484,"亚历山大·翠花","Aa123456",1,null,null,0),
(null,13326022520,"施瓦辛格·花呆","Aa123456",1,null,null,0),
(null,19852234466,"道格森·狗蛋","Aa123456",1,null,null,0),
(null,13526587452,"爱新觉罗·玉凤","Aa123456",1,null,null,0),
(null,15015023202,"澳门之巅谢广坤","Aa123456",1,null,null,0),
(null,13901721386,"约翰尼.刘能","Aa123456",1,null,null,0),
(null,13916279520,"辛普森·二狗","Aa123456",1,null,null,0),
(null,13661636869,"凯瑟琳·翠芬","Aa123456",1,null,null,0),
(null,13611859558,"狗剩","Aa123456",1,null,null,0),
(null,16621303597,"二柱子","Aa123456",1,null,null,0),
(null,13636663631,"虎妞","Aa123456",1,null,null,0),
(null,18918290717,"狗娃","Aa123456",1,null,null,0),
(null,19821985008,"二妮","Aa123456",1,null,null,0),
(null,15502111116,"傻妮","Aa123456",1,null,null,0),
(null,13621769168,"臭蛋","Aa123456",1,null,null,0),
(null,19821999017,"狗娃子","Aa123456",1,null,null,0),
(null,13166448790,"丫蛋","Aa123456",1,null,null,0),
(null,16621313602,"傻丫","Aa123456",1,null,null,0);