SET FOREIGN_KEY_CHECKS = 0;

-- Table avatar
INSERT INTO avatar VALUES(1, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1' );
INSERT INTO avatar VALUES(2, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1' );
INSERT INTO avatar VALUES(3, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1' );

-- Table role
-- Columns [id, role value]
INSERT INTO role VALUES(1, 'USER');
INSERT INTO role VALUES(2, 'OPERATOR');
INSERT INTO role VALUES(3, 'ADMIN');

-- Table user
-- login password is same as username eg. admin/admin, operator/operator, user/user, jeff/jeff etc. Passwords are case sensitive
-- password is stored in BCrypt hash. Calculator: https://www.dailycred.com/article/bcrypt-calculator
-- Columns in order [id; created; email; first name; last login; last name; password; user name; avatar id; role id]
INSERT INTO user VALUES(1, 'Vášnivý šachista, příležitostný fotbalista.','2018-11-18 03:04:05', 'admin@letsplay.com'   , 'Tomáš'    , '2018-10-14 12:34:55', 'Bartošek'  , '$2a$04$1fZkF/a9YmeS9ViHR3PnO.1vtlyldNz7tznYakrx6Aha1T7lZH2hW', 'Admin'   , 1, 3);
INSERT INTO user VALUES(2, 'abbbbb','2018-11-18 03:04:05', 'operator@letsplay.com', 'Andulka'  , '2018-10-14 12:34:55', 'Šafářová'  , '$2a$04$yIx7t4pvzfRqci1FREUll.hhJcjflc8eFajzfrS/z2clk9msZ8Knq', 'Operator', 1, 2);
INSERT INTO user VALUES(3,'abbbbb', '2018-11-18 03:04:05', 'user@letsplay.com'    , 'František', '1970-02-02 02:02:02', 'Dobrota'   , '$2a$04$Xb1dmws19pSnkGYfg2pP2uFO/ZqfBZDrkewzwILJJ1Gd5TifchxTm', 'User'    , 1, 1);
INSERT INTO user VALUES(4,'abbbbb', '1970-01-01 00:00:01', 'jeff@amazon.com'      , 'Jeff'     , '1970-02-02 02:02:02', 'Bezos'     , '$2a$04$Zu.Erknol9RGiAAAonfn7ObWmjl0PnCGyDHUf154OK1K2srLSlA6m', 'Jeff'    , 1, 1);
INSERT INTO user VALUES(5,'abbbbb', '1970-01-01 00:00:01', 'bill@microsoft.com'   , 'Bill'     , '2019-05-14 21:17:23', 'Gates'     , '$2a$04$uPCfdVQACa.C5oAepSuoje.IGr6DEoIlU7eY6lpA1TeDLLaCRBiIK', 'Bill'    , 1, 1);

INSERT INTO report_reason VALUES (1,   'BAD_SCORE');
INSERT INTO report_reason VALUES (2,   'DO_NOT_ARRIVE');
INSERT INTO report_reason VALUES (3,   'VERBAL_ABUSE');
INSERT INTO report_reason VALUES (4,   'OTHER');

INSERT INTO report VALUES(1, '1970-01-01 00:00:01', 'report_text_1', 1, '1', '2');
INSERT INTO report VALUES(2, '2038-01-19 03:14:07', 'report_text_2', 2, '2', '1');

INSERT INTO friend VALUES(1, '2018-10-11 12:34:55', '1', '2');
INSERT INTO friend VALUES(2, '2038-01-19 03:14:07', '2', '1');
INSERT INTO friend VALUES(3, '2019-04-17 18:54:55', '1', '5');

-- Table 'message'
-- Columns [id; created; message_subject; message_text; user_from; user_to]
INSERT INTO message VALUES(1, '2018-11-19 16:31:12','Lorem ipsum', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit', 2, 1);
INSERT INTO message VALUES(2, '2038-01-19 03:14:07','message_subject_2', 'message_text_2', 1, 2);
INSERT INTO message VALUES(3, '2019-05-14 03:14:07','Aliquam ante', 'Aliquam ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Nullam faucibus mi quis velit.', 5, 1);

-- Table 'game'
-- Columns [id; created; game_description; game_name]
INSERT INTO game VALUES(1, 1, '2018-11-01 23:29:48', 'Chess game description for mock data'     , 'Chess'     ); -- 2
INSERT INTO game VALUES(2, 1, '2018-11-01 23:29:48', 'Football game description for mock data'  , 'Football'  ); -- 8
INSERT INTO game VALUES(3, 1, '2018-11-01 23:29:48', 'Badminton game description for mock data' , 'Badminton' ); -- 4
INSERT INTO game VALUES(4, 0, '2018-11-01 23:29:48', 'Checkers game description for mock data'  , 'Tictactoe' ); -- 2

-- Table 'game_param'
-- Columns [id; created; parameter name; parameter value; game id]
INSERT INTO game_param VALUES(1, '2018-11-01 23:29:48', 'number_of_players', '2', 1);
INSERT INTO game_param VALUES(2, '2018-11-01 23:29:48', 'number_of_players', '8', 2);
INSERT INTO game_param VALUES(3, '2018-11-01 23:29:48', 'number_of_players', '4', 3);
INSERT INTO game_param VALUES(4, '2018-11-01 23:29:48', 'number_of_players', '2', 4);

-- Table 'challenge_state'
-- Columns [id; challenge state value]
INSERT INTO challenge_state VALUES(1, 'CREATED');
INSERT INTO challenge_state VALUES(2, 'IN_PROGRESS');
INSERT INTO challenge_state VALUES(3, 'FINISHED');

-- Table 'challenge'
-- Columns [id; coords LAT; coords LNG; created; description; end; password; start; challenge state id; game id]
-- CT
INSERT INTO challenge VALUES(1, '49.902238', '16.439289', '1970-01-01 00:00:01','challenge_text_1',  '2019-06-16 18:45:00',  'pass',  '2019-06-16 16:45:00' ,1, 1);
INSERT INTO challenge VALUES(2, '49.9005116576071', '16.41433837070315', '1970-01-01 00:00:01','challenge_text_1',  '2038-01-19 03:14:07',  'pass',  '1970-01-01 00:00:01' ,1, 2);
-- PCE
INSERT INTO challenge VALUES(3, '50.041114975191851', '15.774616515549837', '2019-05-09 11:46:45', 'Pohodová hra šachů v Pardubickém parku.',  '2019-06-16 18:45:00',  'pass',  '2019-06-16 16:45:00' ,1, 1);
INSERT INTO challenge VALUES(4, '50.0367915062636', '15.784046047198217', '2019-05-09 11:46:45', 'Odpolední badminton.',  '2019-06-16 18:45:00',  'pass',  '2019-06-16 16:45:00' ,1, 3);
INSERT INTO challenge VALUES(5, '50.04093858085418', '15.771140603297113', '2019-05-09 11:46:45', 'Příjdte si kopnout!.',  '2019-06-16 18:45:00',  'pass',  '2019-06-16 16:45:00' ,1, 2);
INSERT INTO challenge VALUES(6, '50.03417771514792', '15.76687562038137', '2019-05-09 11:46:45', 'Jedna partie po přednáškách!.',  '2019-06-16 18:45:00',  'pass',  '2019-06-16 16:45:00' ,1, 1);
-- Finished challenges for history
INSERT INTO challenge VALUES(7, '50.041114975191851', '15.774616515549837', '2019-04-09 11:46:45', 'xx.',  '2019-03-12 13:30:00',  'pass',  '2019-04-11 12:52:00' ,3, 1);
INSERT INTO challenge VALUES(8, '50.041114975191851', '15.774616515549837', '2019-04-09 11:46:45', 'xx',  '2019-04-19 15:15:00',  'pass',  '2019-04-21 16:45:00' ,3, 3);
INSERT INTO challenge VALUES(9, '50.041114975191851', '15.774616515549837', '2019-04-09 11:46:45', 'xx',  '2019-04-19 15:15:00',  'pass',  '2019-04-26 16:45:00' ,3, 1);
INSERT INTO challenge VALUES(10, '50.041114975191851', '15.774616515549837', '2019-04-09 11:46:45', 'xx',  '2019-04-19 15:15:00',  'pass',  '2019-05-14 16:45:00' ,3, 1);
INSERT INTO challenge VALUES(11, '50.041114975191851', '15.774616515549837', '2019-04-09 11:46:45', 'xx',  '2019-04-19 15:15:00',  'pass',  '2019-05-15 16:45:00' ,3, 1);
INSERT INTO challenge VALUES(12, '50.041114975191851', '15.774616515549837', '2019-04-09 11:46:45', 'xx',  '2019-04-19 15:15:00',  'pass',  '2019-05-16 16:45:00' ,3, 1);

INSERT INTO result_state VALUES(1,'WINNER' );
INSERT INTO result_state VALUES(2,'DEFEATED' );
INSERT INTO result_state VALUES(3,'TIE' );
INSERT INTO result_state VALUES(4,'IN_PROGRESS' );

-- Table 'challenge result'
-- Columns [id; created; description; score loser; score winner; team number; challenge id; result state id; user id]
-- CT
INSERT INTO challenge_result VALUES(1, '1970-01-01 00:00:01', 'challenge_result_description', 0, 1, 1, 1, 1, 3);
INSERT INTO challenge_result VALUES(2, '1970-01-01 00:00:01', 'challenge_result_description', 0, 1, 1, 2, 4, 4);
INSERT INTO challenge_result VALUES(3, '1970-01-01 00:00:01', 'challenge_result_description', 0, 1, 1, 2, 4, 5);
-- PCE
INSERT INTO challenge_result VALUES(9, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 3, 4, 3);
INSERT INTO challenge_result VALUES(10, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 4, 4, 4);
INSERT INTO challenge_result VALUES(11, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 5, 4, 4);
INSERT INTO challenge_result VALUES(12, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 6, 4, 5);
INSERT INTO challenge_result VALUES(13, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 4, 4, 2);
INSERT INTO challenge_result VALUES(14, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 4, 4, 3);
INSERT INTO challenge_result VALUES(15, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 5, 4, 1);
INSERT INTO challenge_result VALUES(16, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 5, 4, 2);
INSERT INTO challenge_result VALUES(17, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 5, 4, 3);
INSERT INTO challenge_result VALUES(18, '1970-01-01 00:00:01', 'challenge_result_description', 0, 0, 1, 5, 4, 5);
-- Finished challenges for history
-- Chess
INSERT INTO challenge_result VALUES(19, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 1, 7, 1, 1);
INSERT INTO challenge_result VALUES(20, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 2, 7, 2, 2);
-- Chess
INSERT INTO challenge_result VALUES(25, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 1, 9, 1, 2);
INSERT INTO challenge_result VALUES(26, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 2, 9, 2, 4);
-- Chess
INSERT INTO challenge_result VALUES(27, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 1, 10, 1, 1);
INSERT INTO challenge_result VALUES(28, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 2, 10, 2, 3);
-- Chess
INSERT INTO challenge_result VALUES(29, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 1, 11, 1, 1);
INSERT INTO challenge_result VALUES(30, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 2, 11, 2, 3);
-- Chess - questionable challenge
INSERT INTO challenge_result VALUES(31, '1970-01-01 00:00:01', 'challenge_result_description',  3, 1, 1, 12, 1, 1);
INSERT INTO challenge_result VALUES(32, '1970-01-01 00:00:01', 'challenge_result_description',  0, 1, 2, 12, 2, 3);
-- Badminton
INSERT INTO challenge_result VALUES(21, '1970-01-01 00:00:01', 'challenge_result_description',  1, 2, 2, 8, 2, 1);
INSERT INTO challenge_result VALUES(22, '1970-01-01 00:00:01', 'challenge_result_description',  1, 2, 2, 8, 2, 2);
INSERT INTO challenge_result VALUES(23, '1970-01-01 00:00:01', 'challenge_result_description',  1, 2, 1, 8, 1, 3);
INSERT INTO challenge_result VALUES(24, '1970-01-01 00:00:01', 'challenge_result_description',  1, 2, 1, 8, 1, 4);

-- Table 'rating'
-- Columns 'id, created, rating, game id, user id'
-- Ratings for every user for game 1 - Chess
INSERT INTO rating VALUES(1, '2018-11-01 23:29:48', 1596, 1, 1);
INSERT INTO rating VALUES(2, '2018-11-01 23:29:48', 1509, 1, 2);
INSERT INTO rating VALUES(3, '2018-11-01 23:29:48', 1491, 1, 3);
INSERT INTO rating VALUES(4, '2018-11-01 23:29:48', 1484, 1, 4);
INSERT INTO rating VALUES(5, '2018-11-01 23:29:48', 1500, 1, 5);
-- Ratings for every user for game 2 - Football
INSERT INTO rating VALUES(11, '2018-11-01 23:29:48', 1500, 2, 1);
INSERT INTO rating VALUES(12, '2018-11-01 23:29:48', 1500, 2, 2);
INSERT INTO rating VALUES(13, '2018-11-01 23:29:48', 1500, 2, 3);
INSERT INTO rating VALUES(14, '2018-11-01 23:29:48', 1500, 2, 4);
INSERT INTO rating VALUES(15, '2018-11-01 23:29:48', 1500, 2, 5);
-- Ratings for every user for game 3 - Badminton
INSERT INTO rating VALUES(21, '2018-11-01 23:29:48', 1487, 3, 1);
INSERT INTO rating VALUES(22, '2018-11-01 23:29:48', 1484, 3, 2);
INSERT INTO rating VALUES(23, '2018-11-01 23:29:48', 1528, 3, 3);
INSERT INTO rating VALUES(24, '2018-11-01 23:29:48', 1523, 3, 4);
INSERT INTO rating VALUES(25, '2018-11-01 23:29:48', 1500, 3, 5);

SET FOREIGN_KEY_CHECKS = 1;
