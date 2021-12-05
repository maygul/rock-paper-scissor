drop table if exists user_ CASCADE;
drop table if exists user_match CASCADE;

create table `user_` (
   `id` bigint PRIMARY KEY auto_increment not null,
    `create_date` timestamp,
    `user_name` varchar(255)
);

create table `user_match` (
   `id` bigint PRIMARY KEY auto_increment not null,
    `create_date` timestamp,
    `app_action` varchar(20),
    `score` integer,
    `user_action` varchar(20),
    `user_id` bigint
);

alter table `user_match`
   add constraint `FK_USER_ID_USER_MATCH`
   foreign key (`user_id`)
   references `user_`;