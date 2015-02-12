
insert into users (id, username, email, password, score, enabled) values (1, 'maxx', 'maximilian.hoeflich@googlemail.com', 'maxxer', 1000, true);
insert into users (id, username, email, password, score, enabled) values (2, 'blackscorp', 'cccpmik@gmail.com', 'backscorper', 2000, true);


insert into authorities (user_id, authority) values (1, 'ADMIN');
insert into authorities (user_id, authority) values (2, 'ADMIN');


insert into attacker_blueprint (id, type, price, speed, max_health) values (1,'GRUNT', 200, 30, 100000);
insert into attacker_blueprint (id, type, price, speed, max_health) values (2,'RUNNER', 200, 50, 100000);

insert into attack_force_pattern (id, pattern_name) values (1, 'EARLY_SURPRISE');


insert into wave_blueprint (id, dispatches_after, attack_force_pattern_id, slots, delay_between_spawns) values (1, 1000, 1, 1, 1400);
insert into wave_blueprint (id, dispatches_after, attack_force_pattern_id, slots, delay_between_spawns) values (2, 5000, 1, 10, 500);


insert into dungeon_blueprint (id, name, height, width) values (1, 'SNAIL', 840, 1080);

insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (1, 1, 120, 120, 1);
insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (2, 1, 120, 640, 2);
insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (3, 1, 880, 640, 3);
insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (4, 1, 880, 120, 4);
insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (5, 1, 360, 120, 5);
insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (6, 1, 360, 440, 6);
insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (7, 1, 600, 440, 7);
insert into dungeon_node (id, dungeon_blueprint_id, x, y, check_point) values (8, 1, 600, 360, 8);


insert into construction_site (id, dungeon_blueprint_id, x, y) values (1, 1, 200, 440);
insert into construction_site (id, dungeon_blueprint_id, x, y) values (2, 1, 440, 280);
insert into construction_site (id, dungeon_blueprint_id, x, y) values (3, 1, 740, 440);

insert into tower_blueprint (id, tower_type, damage, time_to_reload, attack_range, price) values (1, 'GATTLING', 20, 100, 200, 100);
insert into tower_blueprint (id, tower_type, damage, time_to_reload, attack_range, price) values (2, 'PLASMA', 30, 300, 400, 200);

insert into attack_force (id, user_id, attack_force_pattern_id) values (1, 1, 1);
insert into attack_force (id, user_id, attack_force_pattern_id) values (2, 2, 1);

insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (1, 1, 1, 1);
--insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (2, 1, 2, 2);
insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (3, 2, 2, 1);
insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (4, 2, 2, 2);

insert into dungeon (id, user_id, dungeon_blueprint_id) values (1, 1, 1);
insert into dungeon (id, user_id, dungeon_blueprint_id) values (2, 2, 1);

insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (1, 1, 1, 1);
insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (2, 1, 2, 2);
insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (3, 2, 1, 2);
insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (4, 2, 2, 2);

insert into fight (id, dungeon_id, attack_force_id, created, fight_state, outcome, attacker_id, defender_id, events) values (1,2,1,'2015-01-16 09:42:01','ISSUED',null, 1, 2, null);
insert into fight (id, dungeon_id, attack_force_id, created, fight_state, outcome, attacker_id, defender_id, events) values (2,1,2,'2015-01-16 14:11:42','COMPLETED','ATTACKER_WON', 2, 1, '[{"type": "SPAWN","time":100,"attackerTqype":"GRUNT","id":1,"x":2,"y":2}');


-- MORE USERS FOR THE LIST :)
insert into users (id, username, email, password, score, enabled) values (3, 'DummyUser1', 'dummy1@gmail.com', '123password', 1200, true);
insert into users (id, username, email, password, score, enabled) values (4, 'DummyUser2', 'dummy2@gmail.com', '123password', 1100, true);
insert into users (id, username, email, password, score, enabled) values (5, 'DummyUser3', 'dummy3@gmail.com', '123password', 900, true);
insert into users (id, username, email, password, score, enabled) values (6, 'DummyUser4', 'dummy4@gmail.com', '123password', 800, true);
insert into users (id, username, email, password, score, enabled) values (7, 'DummyUser5', 'dummy5@gmail.com', '123password', 700, true);
insert into users (id, username, email, password, score, enabled) values (8, 'DummyUser6', 'dummy6@gmail.com', '123password', 600, true);
insert into users (id, username, email, password, score, enabled) values (9, 'DummyUser7', 'dummy7@gmail.com', '123password', 500, true);
insert into users (id, username, email, password, score, enabled) values (10, 'DummyUser8', 'dummy8@gmail.com', '123password', 400, true);
insert into users (id, username, email, password, score, enabled) values (11, 'DummyUser9', 'dummy9@gmail.com', '123password', 300, true);
insert into users (id, username, email, password, score, enabled) values (12, 'DummyUser10', 'dummy10@gmail.com', '123password', 200, true);
insert into users (id, username, email, password, score, enabled) values (13, 'DummyUser11', 'dummy11@gmail.com', '123password', 100, true);
insert into users (id, username, email, password, score, enabled) values (14, 'DummyUser12', 'dummy12@gmail.com', '123password', 1300, true);
insert into users (id, username, email, password, score, enabled) values (15, 'DummyUser13', 'dummy13@gmail.com', '123password', 1400, true);
insert into users (id, username, email, password, score, enabled) values (16, 'DummyUser14', 'dummy14@gmail.com', '123password', 1500, true);
insert into users (id, username, email, password, score, enabled) values (17, 'DummyUser15', 'dummy15@gmail.com', '123password', 1600, true);
insert into users (id, username, email, password, score, enabled) values (18, 'DummyUser16', 'dummy16@gmail.com', '123password', 1700, true);
insert into users (id, username, email, password, score, enabled) values (19, 'DummyUser17', 'dummy17@gmail.com', '123password', 1800, true);
insert into users (id, username, email, password, score, enabled) values (20, 'DummyUser18', 'dummy18@gmail.com', '123password', 1900, true);
insert into users (id, username, email, password, score, enabled) values (21, 'DummyUser19', 'dummy19@gmail.com', '123password', 2000, true);
insert into users (id, username, email, password, score, enabled) values (22, 'DummyUser20', 'dummy20@gmail.com', '123password', 2100, true);
insert into users (id, username, email, password, score, enabled) values (23, 'DummyUser21', 'dummy21@gmail.com', '123password', 2200, true);
insert into users (id, username, email, password, score, enabled) values (24, 'DummyUser22', 'dummy22@gmail.com', '123password', 2300, true);
insert into users (id, username, email, password, score, enabled) values (25, 'DummyUser23', 'dummy23@gmail.com', '123password', 2400, true);
insert into users (id, username, email, password, score, enabled) values (26, 'DummyUser24', 'dummy24@gmail.com', '123password', 2500, true);
insert into users (id, username, email, password, score, enabled) values (27, 'DummyUser25', 'dummy25@gmail.com', '123password', 2600, true);
