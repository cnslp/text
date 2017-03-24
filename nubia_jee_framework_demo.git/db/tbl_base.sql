set foreign_key_checks=0;

drop table if exists tbl_user;
create table tbl_user (
  user_id   int(10) unsigned  not null auto_increment,
  username  varchar(24) default null,
  password   varchar(40) default null,
  primary key (user_id)
) engine=myisam default charset=acsii;

create unique index  tbl_user_unique on tbl_user(username);
insert into tbl_user (user_id,username,password) values (1,'jxva','123456');
insert into tbl_user (user_id,username,password) values (2,'tom','123456');
insert into tbl_user (user_id,username,password) values (3,'kate','123456');
insert into tbl_user (user_id,username,password) values (4,'lily','123456');
insert into tbl_user (user_id,username,password) values (5,'lucy','123456');

set foreign_key_checks=1;
