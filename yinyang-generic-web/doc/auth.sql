drop table if exists td_users;
create table td_users (
  id bigint auto_increment,
  username varchar(50),
  password varchar(50),
  plaintext varchar(50),
  primary key(id)
) charset=utf8 ENGINE=InnoDB;

drop table if exists oauth2_app;
create table oauth2_app (
  id bigint auto_increment,
  app_key varchar(100),
  app_secret varchar(100),
  app_name varchar(100),
  primary key(id)
) charset=utf8 ENGINE=InnoDB;


drop table if exists token;
create table oauth2_token (
  id bigint auto_increment,
  app_key varchar(100),
  app_token varchar(100),
  create_time datetime,
  primary key(id)
) charset=utf8 ENGINE=InnoDB;



