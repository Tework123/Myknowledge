--INSERT INTO users (username, password, active)
--VALUES ('admin1', '$2a$08$1ZT0qnik1lJlfDwYS.eDMuLlyDTPZfX8kMaWy07pxXs.h/vH9Ea5K', true);



--DELETE FROM user_role WHERE user_id=3;
--
--
--INSERT INTO user_role (user_id, roles)
--VALUES (3, 'ROLE_USER');
--
--INSERT INTO user_role (user_id, roles)
--VALUES (6, 'ROLE_ADMIN');


UPDATE users
SET active=true;