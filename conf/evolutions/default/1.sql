# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  id                        integer auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_admin primary key (id))
;

create table member (
  id                        integer auto_increment not null,
  username                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  created_date              datetime,
  constraint pk_member primary key (id))
;

create table problem (
  id                        integer auto_increment not null,
  title                     varchar(255),
  description               longtext,
  tags                      varchar(255),
  source                    varchar(255),
  time_limit                integer,
  memory_limit              integer,
  special_judge             tinyint(1) default 0,
  special_judge_command     varchar(255),
  created_date              datetime,
  last_modified_date        datetime,
  time_stamp                bigint,
  author_id                 integer,
  status                    integer,
  constraint pk_problem primary key (id))
;

create table problem_discussion (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  problem_id                integer,
  parent_id                 bigint,
  member_id                 integer,
  content                   longtext,
  created_date              datetime,
  constraint pk_problem_discussion primary key (id))
;

create table submit (
  id                        bigint auto_increment not null,
  language                  integer,
  source                    longtext,
  problem_id                integer,
  member_id                 integer,
  problem_time_stamp        bigint,
  constraint pk_submit primary key (id))
;

create table submit_comment (
  id                        bigint auto_increment not null,
  submit_id                 bigint,
  member_id                 integer,
  content                   longtext,
  created_date              datetime,
  constraint pk_submit_comment primary key (id))
;

alter table problem add constraint fk_problem_author_1 foreign key (author_id) references member (id) on delete restrict on update restrict;
create index ix_problem_author_1 on problem (author_id);
alter table problem_discussion add constraint fk_problem_discussion_problem_2 foreign key (problem_id) references problem (id) on delete restrict on update restrict;
create index ix_problem_discussion_problem_2 on problem_discussion (problem_id);
alter table problem_discussion add constraint fk_problem_discussion_parent_3 foreign key (parent_id) references problem_discussion (id) on delete restrict on update restrict;
create index ix_problem_discussion_parent_3 on problem_discussion (parent_id);
alter table problem_discussion add constraint fk_problem_discussion_member_4 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_problem_discussion_member_4 on problem_discussion (member_id);
alter table submit add constraint fk_submit_problem_5 foreign key (problem_id) references problem (id) on delete restrict on update restrict;
create index ix_submit_problem_5 on submit (problem_id);
alter table submit add constraint fk_submit_member_6 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_submit_member_6 on submit (member_id);
alter table submit_comment add constraint fk_submit_comment_submit_7 foreign key (submit_id) references submit (id) on delete restrict on update restrict;
create index ix_submit_comment_submit_7 on submit_comment (submit_id);
alter table submit_comment add constraint fk_submit_comment_member_8 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_submit_comment_member_8 on submit_comment (member_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table admin;

drop table member;

drop table problem;

drop table problem_discussion;

drop table submit;

drop table submit_comment;

SET FOREIGN_KEY_CHECKS=1;

