set foreign_key_checks=0;

drop table if exists tbl_user;
create table tbl_user (
  user_id   int(10) unsigned  not null auto_increment,
  username  varchar(32) default null,
  password   varchar(40) default null,
  add_time  timestamp not null default current_timestamp, -- on update current_timestamp,
  primary key (user_id)
) engine=myisam default charset=utf8;

drop table if exists tbl_review;
create table tbl_review (
  review_id   int(10) unsigned  not null auto_increment,
  user_id  int(10)   unsigned   default 0,
  username  varchar(32) default null,
  comments   varchar(40) default null,
  primary key (review_id)
  foreign key (user_id) references tbl_user (user_id) on delete no action on update no action,
) engine=myisam default charset=utf8;


create unique index  tbl_user_unique on tbl_user(username);
insert into tbl_user (user_id,username,password,add_time) values (1,'jxva','123456','2015-01-2 03:58:24');
insert into tbl_user (user_id,username,password,add_time) values (2,'tom','123456','2015-02-14 04:53:11');
insert into tbl_user (user_id,username,password,add_time) values (3,'kate','123456','2015-02-18 12:22:01');
insert into tbl_user (user_id,username,password,add_time) values (4,'lily','123456','2015-03-20 15:13:02');
insert into tbl_user (user_id,username,password,add_time) values (5,'lucy','123456','2015-04-15 20:23:51');

drop table if exists tbl_press;
create table tbl_press (
  press_id   int(10) unsigned  not null auto_increment,
  name    varchar(32) default null,
  primary key (press_id)
) engine=myisam default charset=utf8;

create unique index  tbl_press_unique on tbl_press(name);

drop table if exists tbl_book_category;
create table tbl_book_category (
  category_id   int(10) unsigned  not null auto_increment,
  name    varchar(32) default null,
  primary key (category_id)
) engine=myisam default charset=utf8;

create unique index  tbl_book_category_unique on tbl_book_category(name);

insert into tbl_book_category (category_id,name) values (1,'文学类');
insert into tbl_book_category (category_id,name) values (2,'计算机类');
insert into tbl_book_category (category_id,name) values (3,'语言类');
insert into tbl_book_category (category_id,name) values (4,'翻译类');
insert into tbl_book_category (category_id,name) values (5,'数据类');
insert into tbl_book_category (category_id,name) values (6,'天文类');

drop table if exists tbl_book;
create table tbl_book (
  book_id   int(10)   unsigned    not null auto_increment,
  press_id  int(10)   unsigned   default 0,
  category_id int(10) unsigned default 0,
  name    varchar(32) default   null,
  add_time timestamp not null default current_timestamp,
  primary key (book_id),
  foreign key (press_id) references tbl_press (press_id) on delete no action on update no action,
  foreign key (category_id) references tbl_book_category (category_id) on delete no action on update no action
) engine=myisam default charset=utf8;

drop table if exists tbl_author;
create table tbl_author (
  author_id   int(10) unsigned  not null auto_increment,
  name    varchar(32) default null,
  primary key (author_id)
) engine=myisam default charset=utf8;

drop table if exists tbl_user_book;
create table tbl_user_book (
  relation_id   int(10) unsigned    not null auto_increment,
  user_id  int(10)   unsigned   default 0,
  book_id  int(10)   unsigned   default 0,
  borrow_time datetime default null,
  primary key (relation_id),
  foreign key (user_id) references tbl_user (user_id) on delete no action on update no action,
  foreign key (book_id) references tbl_book (book_id) on delete no action on update no action
) engine=myisam default charset=ascii;


drop table if exists tbl_book_author;
create table tbl_book_author (
  relation_id   int(10) unsigned    not null auto_increment,
  book_id  int(10)   unsigned   default 0,
  author_id  int(10)   unsigned   default 0,
  primary key (relation_id),
  foreign key (book_id) references tbl_book (book_id) on delete no action on update no action,
  foreign key (author_id) references tbl_author (author_id) on delete no action on update no action
) engine=myisam default charset=ascii;

insert into tbl_press (press_id,name) values (1,'机械工业出版社');
insert into tbl_press (press_id,name) values (2,'清华大学出版社');
insert into tbl_press (press_id,name) values (3,'人民邮电出版社');
insert into tbl_press (press_id,name) values (4,'商务印书馆');
insert into tbl_press (press_id,name) values (5,'高等教育出版社');
insert into tbl_press (press_id,name) values (6,'人民教育出版社');

insert into tbl_book (book_id,press_id,category_id,name,add_time) values (1,1,2,'数值方法','2015-01-01 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (2,4,3,'汇编语言(第2版)','2015-02-12 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (3,5,4,'线性代数','2015-03-20 08:23:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (4,2,5,'高等数学','2015-03-20 08:21:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (5,3,1,'C程序设计语言(第2版)','2015-03-24 10:58:31');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (6,6,6,'深入理解计算机系统','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (7,1,1,'算法导论','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (8,4,2,'工程数值方法','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (9,5,6,'代数','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (10,1,2,'数据分析原理','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (11,6,3,'Unix网络编程','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (12,3,4,'实分析与复分析','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (13,2,5,'泛函分析','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (14,1,2,'具体数学','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (15,6,3,'数据结构C语言描述','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (16,3,2,'大学英语','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (17,4,4,'操作系统','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (18,5,5,'数据库系统','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (19,2,2,'计算机系统结构','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (20,3,1,'概率论与数理统计','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (21,1,3,'VHDL语言','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (22,2,4,'JAVA从入门到精通','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (23,4,5,'21天学会PHP','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (24,5,6,'MYSQL专家指南','2015-03-20 08:58:21');
insert into tbl_book (book_id,press_id,category_id,name,add_time) values (25,1,1,'精通ASP.NET','2015-03-20 08:58:21');


set foreign_key_checks=1;
