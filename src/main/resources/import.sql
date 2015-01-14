
insert into users (id, username, email, password, enabled) values (1, 'maxx', 'maximilian.hoeflich@googlemail.com', 'maxxer', true);
insert into users (id, username, email, password, enabled) values (2, 'blackscorp', 'cccpmik@gmail.com', 'backscorper', true);


insert into authorities (user_id, authority) values (1, 'ADMIN');
insert into authorities (user_id, authority) values (2, 'ADMIN');


insert into attacker_blueprint (id, type, damage, time_to_reload, range, price, speed, max_health) values (1,'GRUNT', 10, 1000,100,200, 10, 100);
insert into attacker_blueprint (id, type, damage, time_to_reload, range, price, speed, max_health) values (2,'RUNNER', 10, 1000,100,200, 15, 100);

insert into attack_force_pattern (id, pattern_name) values (1, 'EARLY_SURPRISE');


insert into wave_blueprint (id, dispatches_after, attack_force_pattern_id, slots, delay_between_spawns) values (1, 1000, 1, 2, 1400);
insert into wave_blueprint (id, dispatches_after, attack_force_pattern_id, slots, delay_between_spawns) values (2, 5000, 1, 10, 500);


