
insert into users (id, username, email, password, enabled) values (1, 'maxx', 'maximilian.hoeflich@googlemail.com', 'maxxer', true);
insert into users (id, username, email, password, enabled) values (2, 'blackscorp', 'cccpmik@gmail.com', 'backscorper', true);


insert into authorities (user_id, authority) values (1, 'ADMIN');
insert into authorities (user_id, authority) values (2, 'ADMIN');


insert into attacker_blueprint (id, type, price, speed, max_health) values (1,'GRUNT', 200, 10, 100);
insert into attacker_blueprint (id, type, price, speed, max_health) values (2,'RUNNER', 200, 15, 100);

insert into attack_force_pattern (id, pattern_name) values (1, 'EARLY_SURPRISE');


insert into wave_blueprint (id, dispatches_after, attack_force_pattern_id, slots, delay_between_spawns) values (1, 1000, 1, 2, 1400);
insert into wave_blueprint (id, dispatches_after, attack_force_pattern_id, slots, delay_between_spawns) values (2, 5000, 1, 10, 500);


insert into dungeon_blueprint (id, name) values (1, 'HALLWAY');

insert into dungeon_node (id, dungeon_blueprint_id, x, y) values (1, 1, 2, 1);
insert into dungeon_node (id, dungeon_blueprint_id, x, y) values (2, 1, 2, 2);
insert into dungeon_node (id, dungeon_blueprint_id, x, y) values (3, 1, 2, 3);
insert into dungeon_node (id, dungeon_blueprint_id, x, y) values (4, 1, 2, 4);
insert into dungeon_node (id, dungeon_blueprint_id, x, y) values (5, 1, 2, 5);
insert into dungeon_node (id, dungeon_blueprint_id, x, y) values (6, 1, 2, 6);

insert into construction_site (id, dungeon_blueprint_id, x, y) values (1, 1, 1, 4);
insert into construction_site (id, dungeon_blueprint_id, x, y) values (2, 1, 3, 5);

insert into tower_blueprint (id, name, damage, time_to_reload, range, price) values (1, 'GATTLING', 100, 100, 100, 100);
insert into tower_blueprint (id, name, damage, time_to_reload, range, price) values (2, 'FLAMER', 800, 1500, 50, 200);

insert into attack_force (id, user_id, attack_force_pattern_id) values (1, 1, 1);
insert into attack_force (id, user_id, attack_force_pattern_id) values (2, 2, 1);

insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (1, 1, 1, 1);
insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (2, 1, 2, 2);
insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (3, 2, 2, 1);
insert into wave (id, attack_force_id, attacker_blueprint_id, wave_blueprint_id) values (4, 2, 2, 2);

insert into dungeon (id, user_id, dungeon_blueprint_id) values (1, 1, 1);
insert into dungeon (id, user_id, dungeon_blueprint_id) values (2, 2, 1);

insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (1, 1, 1, 1);
insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (2, 1, 2, 2);
insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (3, 2, 1, 2);
insert into tower (id, dungeon_id, construction_site_id, tower_blueprint_id) values (4, 2, 2, 2);

