SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO avatar VALUES(1, '1970-01-01 00:00:01',  'avatar_img_1', 'avatar_name_1' );
INSERT INTO avatar VALUES(2, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1'  );
INSERT INTO avatar VALUES(3, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1'  );

-- Table role
-- Columns [id, role value]
INSERT INTO role VALUES(1, 'USER');
INSERT INTO role VALUES(2, 'OPERATOR');
INSERT INTO role VALUES(3, 'ADMIN');

--https://www.dailycred.com/article/bcrypt-calculator
-- Table user
-- 7 common testing users that you do not use
-- 1 role administrator - ID 1
-- 1 role operator - ID 2
-- 1 role user - ID 3
-- login password is same as username eg. admin/admin, operator/operator, user/user, jeff/jeff etc. ALL PASSWORDS ARE IN LOWERCASE AND THEY ARE CASE SENSITIVE!
-- password is stored in BCrypt hash
-- todo pri vkladani dat password to lowecase, sou i v db ulozeny jako lowercase
-- Columns in order [id; created; email; first name; last login; last name; password; user name; avatar id; role id]
INSERT INTO user VALUES(1, '2018-11-18 03:04:05', 'admin@letsplay.com'   , 'Tomáš'    , '2018-10-14 12:34:55', 'Bartošek'  , '$2a$04$1fZkF/a9YmeS9ViHR3PnO.1vtlyldNz7tznYakrx6Aha1T7lZH2hW', 'Admin'   , 1, 3);
INSERT INTO user VALUES(2, '2018-11-18 03:04:05', 'operator@letsplay.com', 'Andulka'  , '2018-10-14 12:34:55', 'Šafářová'  , '$2a$04$yIx7t4pvzfRqci1FREUll.hhJcjflc8eFajzfrS/z2clk9msZ8Knq', 'Operator', 1, 2);
INSERT INTO user VALUES(3, '2018-11-18 03:04:05', 'user@letsplay.com'    , 'František', '1970-02-02 02:02:02', 'Dobrota'   , '$2a$04$Xb1dmws19pSnkGYfg2pP2uFO/ZqfBZDrkewzwILJJ1Gd5TifchxTm', 'User'    , 1, 1);
INSERT INTO user VALUES(4, '1970-01-01 00:00:01', 'jeff@amazon.com'      , 'Jeff'     , '1970-02-02 02:02:02', 'Bezos'     , '$2a$04$Zu.Erknol9RGiAAAonfn7ObWmjl0PnCGyDHUf154OK1K2srLSlA6m', 'Jeff'    , 1, 1);
INSERT INTO user VALUES(5, '1970-01-01 00:00:01', 'bill@microsoft.com'   , 'Bill'     , '1970-02-02 02:02:02', 'Gates'     , '$2a$04$uPCfdVQACa.C5oAepSuoje.IGr6DEoIlU7eY6lpA1TeDLLaCRBiIK', 'Bill'    , 1, 1);
INSERT INTO user VALUES(6, '1970-01-01 00:00:01', 'warren@hathaway.us'   , 'Warren'   , '1970-02-02 02:02:02', 'Buffett'   , '$2a$04$1SN6rqOfKj4q5Ou0g4Rox.tdGSuvzCoKdnS/ikx3Y7jPtgnDiP3MK', 'Warren'  , 1, 1);
INSERT INTO user VALUES(7, '1970-01-01 00:00:01', 'bernard@lvmh.fr'      , 'Bernard'  , '1970-02-02 02:02:02', 'Arnault'   , '$2a$04$fjRdWm9vdW7kkbDAZQYzsuKCJc9dO.rWp0f0xTE1hui/08OurVcYO', 'Bernard' , 1, 1);
INSERT INTO user VALUES(8, '1970-01-01 00:00:01', 'mark@facebook.com'    , 'Mark'     , '1970-02-02 02:02:02', 'Zuckerberg', '$2a$04$jN.txy2FddjqTolSpnqXfOihBp6irRpamxAciBUCoAWdFzRZyZdKG', 'Mark'    , 1, 1);
INSERT INTO user VALUES(9, '1970-01-01 00:00:01', 'alice@walmart.us'     , 'Alice'    , '1970-02-02 02:02:02', 'Walton'    , '$2a$04$bsyAp0BUEqjJoA79XciYl.7wRNYn60rvmRkBKNnuiRaGBRHJ1yXUu', 'Alice'   , 1, 1);
INSERT INTO user VALUES(10,'1970-01-01 00:00:01', 'charles@koch.com'     , 'Charles'  , '1970-02-02 02:02:02', 'Koch'      , '$2a$04$Ec99rnfVPd9aLjb4fc0HjejQk8NTaIO6N1hD4LwoKSva5a5k7AyRO', 'Charles' , 1, 1);

INSERT INTO report_reason VALUES (1,   'feed');
INSERT INTO report_reason VALUES(2,   'DONT_COME');

INSERT INTO report VALUES(1, '1970-01-01 00:00:01',   'report_text_1',1 ,'1', '2');
INSERT INTO report VALUES(2, '2038-01-19 03:14:07',   'report_text_2', 2,'2', '1');

INSERT INTO friend VALUES(1, '1970-01-01 00:00:01', '1', '2');
INSERT INTO friend VALUES(2, '2038-01-19 03:14:07', '2', '1');

INSERT INTO message VALUES(1, '1970-01-01 00:00:01',   'message_text_1', 'message_subject_1',  '2',1);
INSERT INTO message VALUES(2, '2038-01-19 03:14:07',   'message_text_2', 'message_subject_2',  '1',2);

-- Table 'game'
-- Columns [id; created; game description; game name]
INSERT INTO game VALUES(1, 1,'2018-11-01 23:29:48', 'Chess game description for mock data'      , 'chess'     ); -- 2
 INSERT INTO game VALUES(2,1, '2018-11-01 23:29:48', 'Football game description for mock data'  , 'football'  ); -- 8
 INSERT INTO game VALUES(3,0, '2018-11-01 23:29:48', 'Badminton game description for mock data'  , 'badminton' ); -- 4
 INSERT INTO game VALUES(4,0, '2018-11-01 23:29:48', 'Hockey ball game description for mock data', 'hockeyball'); -- 6
 INSERT INTO game VALUES(5,0, '2018-11-01 23:29:48', 'Checkers game description for mock data'   , 'checkers'  ); --2

-- Table 'game_param'
-- Columns [id; created; parameter name; parameter value; game id]
INSERT INTO game_param VALUES(1, '2018-11-01 23:29:48', 'number_of_players', '2', 1);
INSERT INTO game_param VALUES(2, '2018-11-01 23:29:48', 'number_of_players', '8', 2);
INSERT INTO game_param VALUES(3, '2018-11-01 23:29:48', 'number_of_players', '4', 3);
INSERT INTO game_param VALUES(4, '2018-11-01 23:29:48', 'number_of_players', '6', 4);
INSERT INTO game_param VALUES(5, '2018-11-01 23:29:48', 'number_of_players', '2', 5);

-- Table 'challenge_state'
-- Columns [id; challenge state value]
INSERT INTO challenge_state VALUES(1, 'CREATED');
INSERT INTO challenge_state VALUES(2, 'IN_PROGRESS');
INSERT INTO challenge_state VALUES(3, 'FINISHED');


INSERT INTO challenge VALUES(1, '49.902238', '16.439289', '1970-01-01 00:00:01','challenge_text_1',  '2038-01-19 03:14:07',  'pass',  '1970-01-01 00:00:01' ,1, 1);
--  '50.036816', '15.759573',
INSERT INTO challenge VALUES(2, '49.9005116576071', '16.41433837070315', '1970-01-01 00:00:01','challenge_text_1',  '2038-01-19 03:14:07',  'pass',  '1970-01-01 00:00:01' ,1, 2);

--INSERT INTO challenge VALUES(2, '49.9005116576071', '16.41433837070315', '1970-01-01 00:00:01','challenge_text_1',  '2038-01-19 03:14:07',  'pass',  '1970-01-01 00:00:01' ,1, 2);


INSERT INTO result_state VALUES(1,'WINNER' );
INSERT INTO result_state VALUES(2,'DEFEATED' );
INSERT INTO result_state VALUES(3,'TIE' );
INSERT INTO result_state VALUES(4,'IN_PROGRESS' );

INSERT INTO challenge_result VALUES(1, '1970-01-01 00:00:01', 'challenge_result_description_1', 0, 1, 1, 1, 1, 1);
INSERT INTO challenge_result VALUES(2, '1970-01-01 00:00:01', 'challenge_result_description_1',  0, 1, 1, 2, 4, 4);
INSERT INTO challenge_result VALUES(3, '1970-01-01 00:00:01', 'challenge_result_description_1', 0, 1, 1,  2, 4, 5);
INSERT INTO challenge_result VALUES(4, '1970-01-01 00:00:01', 'challenge_result_description_1',  0, 1, 1, 2, 4, 6);
INSERT INTO challenge_result VALUES(5, '1970-01-01 00:00:01', 'challenge_result_description_1',  0, 1, 1, 2, 4, 7);
INSERT INTO challenge_result VALUES(6, '1970-01-01 00:00:01', 'challenge_result_description_1',  0, 1, 1, 2, 4, 8);
INSERT INTO challenge_result VALUES(7, '1970-01-01 00:00:01', 'challenge_result_description_1',  0, 1, 1, 2, 4, 9);
INSERT INTO challenge_result VALUES(8, '1970-01-01 00:00:01', 'challenge_result_description_1', 0, 1, 1,  2, 4, 10);

-- Table 'rating'
-- Columns 'id, created, rating, game id, user id'
-- Ratings for every user for game 1 - Chess
INSERT INTO rating VALUES(1, '2018-11-01 23:29:48', 1596, 1, 1);
INSERT INTO rating VALUES(2, '2018-11-01 23:29:48', 1125, 1, 2);
INSERT INTO rating VALUES(3, '2018-11-01 23:29:48', 1327, 1, 3);
INSERT INTO rating VALUES(4, '2018-11-01 23:29:48', 1961, 1, 4);
INSERT INTO rating VALUES(5, '2018-11-01 23:29:48', 1945, 1, 5);
INSERT INTO rating VALUES(6, '2018-11-01 23:29:48', 1747, 1, 6);
INSERT INTO rating VALUES(7, '2018-11-01 23:29:48', 1114, 1, 7);
INSERT INTO rating VALUES(8, '2018-11-01 23:29:48', 1568, 1, 8);
INSERT INTO rating VALUES(9, '2018-11-01 23:29:48', 1998, 1, 9);
INSERT INTO rating VALUES(10,'2018-11-01 23:29:48', 1254, 1, 10);
-- Ratings for every user for game 2 - Football
INSERT INTO rating VALUES(11, '2018-11-01 23:29:48', 1437, 2, 1);
INSERT INTO rating VALUES(12, '2018-11-01 23:29:48', 1634, 2, 2);
INSERT INTO rating VALUES(13, '2018-11-01 23:29:48', 1558, 2, 3);
INSERT INTO rating VALUES(14, '2018-11-01 23:29:48', 1173, 2, 4);
INSERT INTO rating VALUES(15, '2018-11-01 23:29:48', 1486, 2, 5);
INSERT INTO rating VALUES(16, '2018-11-01 23:29:48', 1596, 2, 6);
INSERT INTO rating VALUES(17, '2018-11-01 23:29:48', 1562, 2, 7);
INSERT INTO rating VALUES(18, '2018-11-01 23:29:48', 1254, 2, 8);
INSERT INTO rating VALUES(19, '2018-11-01 23:29:48', 1818, 2, 9);
INSERT INTO rating VALUES(20, '2018-11-01 23:29:48', 1315, 2, 10);

SET FOREIGN_KEY_CHECKS = 1;
