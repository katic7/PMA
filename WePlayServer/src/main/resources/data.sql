insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (1, 18, 1599404119816, "Ket Gaming Room",65,"08:00-20:00","021300350", 4.0);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (2, 40, 1599328800000, "LK net",65,"08:00-20:00","021300350", 3.5);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (3, 20, 1599328800000, "DIVIN igraonica",65,"08:00-20:00","021300350", 4.5);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (4, 60, 1599328800000, "Kum",65,"08:00-20:00","021300350", 4.5);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (5, 25, 1599328800000, "WePlay",65,"08:00-20:00","021300350", 4.5);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (6, 65, 1599328800000, "Cosmoplay",65,"08:00-20:00","021300350", 4.5);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (7, 14, 1599328800000, "After party gaming room",65,"08:00-20:00","021300350", 4.5);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (8, 30, 1599328800000, "Forum MZ",65,"08:00-20:00","021300350", 4.5);
insert into gaming_room (id, capacity, last_update_date, name,price_per_hour, working_hours, phone_number, rating) values (9, 35, 1599328800000, "Playground PS4",65,"08:00-20:00","021300350", 4.5);

insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (1,"Novi Sad","Srbija", "21000", "Stevana Musica 11", 1, 45.25005,19.8519);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (2,"Novi Sad","Srbija", "21000", "Bulevar cara Lazara 48", 2, 45.247929,19.848702);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (3,"Novi Sad","Srbija", "21000", "Stevana Musica 11", 3, 45.207771,19.716572);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (4,"Novi Sad","Srbija", "21000", "Grčkoškolska 7", 4, 45.25773,19.84645);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (5,"Novi Sad","Srbija", "21000", "Stevana Musica 11", 5, 45.25005,19.8519);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (6,"Novi Sad","Srbija", "21000", "Stevana Musica 11", 6, 45.25005,19.8519);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (7,"Novi Sad","Srbija", "21000", "Stevana Musica 11", 7, 45.25005,19.8519);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (8,"Novi Sad","Srbija", "21000", "Vojvode Mišića 3", 8, 45.251991,19.852245);
insert into address (id, city, country, postal_code, street, gaming_room_id, lat, lon) values (9,"Novi Sad","Srbija", "21000", "Stevana Musica 11", 9, 45.25005,19.8519);

--users
insert into user (id, email, enabled, first_name, gaming_skill, fcmid, last_name, last_password_reset_date, password) values (1, "katicmilan7@gmail.com",1, "Milan", 5, "id1", "Katic", "2020-01-20","123321");
insert into user (id, email, enabled, first_name, gaming_skill, fcmid, last_name, last_password_reset_date, password) values (2, "nemanjadimsic6@gmail.com",1, "Nemanja", 3, "eYfUAfJT_KY:APA91bE5CpvJ8nF6WdBsNFpmL7DAHEBIqB4av8sh2yFAPJUVnnbDETO_MUCP6wLikk0gVDT7GpP1oSl0wE5abndnvSisGm9PifZiBDX_qmdLsWVFcVrGSffUz3vxJ56NFLuR6FoNuqd5", "Dimsic", "2020-01-20","123321");

--events
insert into event (id, date_time, description, game, join_deadline, name, numb_of_players, creator_id, gaming_room_id) values (1, "2020-06-25 12:12:12", "Amaterski turnir - nagrada 10.000RSD", "CS GO", "2020-06-25 20:00:00", "CS GO Turnir", 15, 1, 1);
insert into event (id, date_time, description, game, join_deadline, name, numb_of_players, creator_id, gaming_room_id) values (2, "2020-07-20 12:12:12", "Sjajan turnir", "CS GO", "2020-07-20 20:00:00", "Counter Stirke turnir", 15, 1, 1);
--reviews
insert into review (id, comment, rating, gaming_room, user_id) values (1, "Solidna igraonica, mana je sto ima puno klinaca", 4.0, 1, 2);

--participants
--insert into participant_events (user_id, event_id) values (1,2);
--user favourites game  rooms
insert into user_game_rooms (user_id, game_room_id) values (1,1);
insert into user_game_rooms (user_id, game_room_id) values (1,2);
