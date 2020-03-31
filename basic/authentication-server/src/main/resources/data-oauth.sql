INSERT INTO `oauth_client_details`(
  `client_id`,
  `resource_ids`,
  `client_secret`,
  `scope`,
  `authorized_grant_types`,
  `web_server_redirect_uri`,
  `authorities`,
  `access_token_validity`,
  `refresh_token_validity`,
  `additional_information`,
  `autoapprove`
  )

  VALUES(
  'clientId',
  null,
  '{bcrypt}$2a$10$q7m.AcKaN18PNKCgtqGizOE0zvRUUS56CcW9EglUt4BM3CWCx.i6i',
  'read_profile,read_posts',
  'authorization_code,implicit,password,client_credentials,refresh_token',
  'http://localhost:9000/callback',
  null,
  3600,
  86400,
  null ,
  'false'
  );

-- user data
INSERT INTO `user`(
  `password`,
  `provider`,
  `id`,
  `name`,
  `nick_name`,
  `email`,
  `state`,
  `created_by`,
  `created_date`,
  `last_modified_by`,
  `last_modified_date`,
  `login_try_count`
  )
VALUES ('{bcrypt}$2a$10$W85sJWwpsSRckc5ClTRVq.z0WdimrqrvIX2qeiZaF2YDNU/MPlBha',0,
'temp_id', 'joo', 'nickname222', 'joohyung0531@gmail.com',0, 'temp_id','2019-12-04 02:05:19.058000'
,'temp_id','2019-12-04 02:05:19.058000', 0);
