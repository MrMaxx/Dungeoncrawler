

-- CREATE TABLE IF NOT EXISTS users (username varchar(256),password varchar(256),enabled boolean);
-- CREATE TABLE IF NOT EXISTS authorities (username varchar(256),authority varchar(256));


insert into users (id, username, email, password, enabled) values (1, 'maxx', 'maximilian.hoeflich@googlemail.com', 'maxxer', true);
insert into authorities (useR_id, authority) values (1, 'ADMIN');
insert into users (id, username, email, password, enabled) values (2, 'blackscorp', 'cccpmik@gmail.com', 'backscorper', true);
insert into authorities (useR_id, authority) values (2, 'ADMIN');
