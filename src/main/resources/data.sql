INSERT INTO role(id,authority) VALUES (1,'ROLE_USER'), (2,'ROLE_ADMIN')
	ON DUPLICATE KEY UPDATE role.id=LAST_INSERT_ID(id);
SET FOREIGN_KEY_CHECKS=0;
DELETE FROM user;
INSERT INTO user(id,password,username) VALUES (1,'$2a$10$UBv7HUUz1mclZ1m9J.EMEOEnNR1gh3Tur6Z4OXIUJ/yrqna6Njssy','user');
DELETE FROM _user_role;
INSERT INTO _user_role(user_id,role_id) VALUES (1,1), (1,2);
SET FOREIGN_KEY_CHECKS=1;