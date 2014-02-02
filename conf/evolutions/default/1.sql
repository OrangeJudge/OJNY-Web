# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin (
  id                        integer auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_admin primary key (id))
;

create table judger (
  id                        integer auto_increment not null,
  nickname                  varchar(255),
  address                   varchar(255),
  available                 tinyint(1) default 0,
  queue                     integer,
  constraint pk_judger primary key (id))
;

create table member (
  id                        integer auto_increment not null,
  username                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  gender                    tinyint(1) default 0,
  created_date              datetime,
  last_login_date           datetime,
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
  created_date              datetime,
  last_modified_date        datetime,
  time_stamp                bigint,
  author_id                 integer,
  status                    integer,
  difficulty                double,
  vote                      integer,
  constraint pk_problem primary key (id))
;

create table problem_difficulty (
  id                        bigint auto_increment not null,
  problem_id                integer,
  member_id                 integer,
  point                     integer,
  constraint pk_problem_difficulty primary key (id))
;

create table problem_discussion (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  problem_id                integer,
  parent_id                 bigint,
  member_id                 integer,
  content                   longtext,
  created_date              datetime,
  contain_solution          tinyint(1) default 0,
  constraint pk_problem_discussion primary key (id))
;

create table problem_vote (
  id                        bigint auto_increment not null,
  problem_id                integer,
  member_id                 integer,
  up                        tinyint(1) default 0,
  constraint pk_problem_vote primary key (id))
;

create table submit (
  id                        bigint auto_increment not null,
  language                  integer,
  source                    longtext,
  problem_id                integer,
  member_id                 integer,
  problem_time_stamp        bigint,
  create_time               datetime,
  update_time               datetime,
  status                    integer,
  detail                    varchar(255),
  judger                    integer,
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
alter table problem_difficulty add constraint fk_problem_difficulty_problem_2 foreign key (problem_id) references problem (id) on delete restrict on update restrict;
create index ix_problem_difficulty_problem_2 on problem_difficulty (problem_id);
alter table problem_difficulty add constraint fk_problem_difficulty_member_3 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_problem_difficulty_member_3 on problem_difficulty (member_id);
alter table problem_discussion add constraint fk_problem_discussion_problem_4 foreign key (problem_id) references problem (id) on delete restrict on update restrict;
create index ix_problem_discussion_problem_4 on problem_discussion (problem_id);
alter table problem_discussion add constraint fk_problem_discussion_parent_5 foreign key (parent_id) references problem_discussion (id) on delete restrict on update restrict;
create index ix_problem_discussion_parent_5 on problem_discussion (parent_id);
alter table problem_discussion add constraint fk_problem_discussion_member_6 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_problem_discussion_member_6 on problem_discussion (member_id);
alter table problem_vote add constraint fk_problem_vote_problem_7 foreign key (problem_id) references problem (id) on delete restrict on update restrict;
create index ix_problem_vote_problem_7 on problem_vote (problem_id);
alter table problem_vote add constraint fk_problem_vote_member_8 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_problem_vote_member_8 on problem_vote (member_id);
alter table submit add constraint fk_submit_problem_9 foreign key (problem_id) references problem (id) on delete restrict on update restrict;
create index ix_submit_problem_9 on submit (problem_id);
alter table submit add constraint fk_submit_member_10 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_submit_member_10 on submit (member_id);
alter table submit_comment add constraint fk_submit_comment_submit_11 foreign key (submit_id) references submit (id) on delete restrict on update restrict;
create index ix_submit_comment_submit_11 on submit_comment (submit_id);
alter table submit_comment add constraint fk_submit_comment_member_12 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_submit_comment_member_12 on submit_comment (member_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table admin;

drop table judger;

drop table member;

drop table problem;

drop table problem_difficulty;

drop table problem_discussion;

drop table problem_vote;

drop table submit;

drop table submit_comment;

SET FOREIGN_KEY_CHECKS=1;

