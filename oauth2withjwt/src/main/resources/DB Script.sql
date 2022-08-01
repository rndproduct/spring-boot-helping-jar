insert into role (id, display_name, role_name) VALUES (1, 'ADMIN','ADMIN');
insert into role (id, display_name, role_name) VALUES (2, 'USER','USER');


# $2a$12$O.3u.s2xzid729dbce36WuWOynVhgOHYCywR5Voz.ddRbpmKF0AD2 ~= 1234
insert into user values(1,'Badrul','$2a$12$O.3u.s2xzid729dbce36WuWOynVhgOHYCywR5Voz.ddRbpmKF0AD2','badrul');
insert into user values(2,'Rejaul','$2a$12$O.3u.s2xzid729dbce36WuWOynVhgOHYCywR5Voz.ddRbpmKF0AD2','rejaul');
insert into user values(3,'Ibrahim','$2a$12$O.3u.s2xzid729dbce36WuWOynVhgOHYCywR5Voz.ddRbpmKF0AD2','ibrahim');
insert into user values(4,'Rakib','$2a$12$O.3u.s2xzid729dbce36WuWOynVhgOHYCywR5Voz.ddRbpmKF0AD2','rakib');


insert into user_role values (1,1);
insert into user_role values (2,2);
insert into user_role values (3,1);
insert into user_role values (4,2);