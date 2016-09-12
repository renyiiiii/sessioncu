CREATE TABLE `users` (
`username`  varchar(50) NULL ,
`password`  varchar(50) NULL ,
`enabled`  varchar(50) NULL 
)
;

CREATE TABLE `authorities` (
`username`  varchar(50) NULL ,
`authority`  varchar(50) NULL 
)
;



CREATE TABLE `oauth2_client` (
`client_name`  varchar(50) NULL ,
`client_id`  varchar(50) NULL ,
`client_secret`  varchar(50) NULL 
)
;

insert into users(username,password,enabled) values ('u1','p1','1'),('u2','p2','1');


insert into authorities(username,authority) values ('u1','ROLE_USER'),('u2','ROLE_USER'),('u1','ROLE_ADMIN');



insert into oauth2_client(client_name,client_id,client_secret) values ('cn1','ci1','cs1'),('cn2','ci2','cs2');




