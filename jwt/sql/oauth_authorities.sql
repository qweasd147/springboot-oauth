-- DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `username` varchar(256) NOT NULL,
  `authority` varchar(256) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `authorities` VALUES ('guest','ROLE_GUEST'),('user','ROLE_USER');