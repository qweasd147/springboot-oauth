
-- DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `idx` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nick_name` varchar(255) NOT NULL,
  `provider` int(11) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES (2,'joohyung0531@gmail.com','local','joo','nickname222',0,0,'temp_id','2019-12-04 02:05:19.058000','temp_id','2019-12-04 02:05:19.058000','{bcrypt}$2a$10$.KMiH/4vH54IZpBFntPZZus7p95mlBLkIbr/phGiLfLccEKZEY.MK');