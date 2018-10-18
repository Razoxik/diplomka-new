SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO avatar VALUES(1, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );
INSERT INTO avatar VALUES(2, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );
INSERT INTO avatar VALUES(3, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );

INSERT INTO role VALUES(1, '1970-01-01 00:00:01', 'USER');
INSERT INTO role VALUES(2, '2038-01-19 03:14:07', 'ADMIN');
--https://www.dailycred.com/article/bcrypt-calculator
INSERT INTO user VALUES(1, '1970-01-01 00:00:01', 'mejl', 'tomas', '1970-02-02 02:02:02','bart', 'nicknameTest', 'tomas123',1,1 );
INSERT INTO user VALUES(2, '1970-01-01 00:00:01', 'asd@praise.sun', 'pepa', '1970-02-02 02:02:02','bart', '$2a$04$TPPTbBD7EBdOoG3wn3rCAe78Bjo1971yCWSr7ftyFc1vlVohko1M.', 'razox', 1,1  );

INSERT INTO report VALUES(1, '1970-01-01 00:00:01', 'report_reason_1', 'report_text_1', '1', '2');
INSERT INTO report VALUES(2, '2038-01-19 03:14:07', 'report_reason_2', 'report_text_2', '2', '1');

INSERT INTO friend VALUES(1, '1970-01-01 00:00:01', '1', '2');
INSERT INTO friend VALUES(2, '2038-01-19 03:14:07', '2', '1');

INSERT INTO message VALUES(1, '1970-01-01 00:00:01',   'message_text_1', 'message_subject_1',  '2',1);
INSERT INTO message VALUES(2, '2038-01-19 03:14:07',   'message_text_2', 'message_subject_2',  '1',2);

INSERT INTO game_param VALUES(1, '1970-01-01 00:00:01',   'game_param_name_1', 'game_param_value_1',1 );
INSERT INTO game_param VALUES(2,'2038-01-19 03:14:07',   'game_param_name_2',  'game_param_value_2',2 );

INSERT INTO game VALUES(1, 'game_description_1', 'chess');
INSERT INTO game VALUES(2, 'game_description_2', 'football');

INSERT INTO challenge VALUES(1,'1970-01-01 00:00:01', '2038-01-19 03:14:07', '49.902238', '16.439289', 'pass',  'challenge_text_1', 1, 1, 1);
--  '50.036816', '15.759573',

INSERT INTO challenge_result VALUES(1,  '1970-01-01 00:00:01','challenge_result_description_1',0,'2:1', 1,1 );
-- INSERT INTO challenge_result VALUES(2, 2, '2:3', 'challenge_result_description_2', '2038-01-19 03:14:07', 2, 1);
insert into rating values(1,1300,1,1);
insert into rating values(2,1200,1,2);

SET FOREIGN_KEY_CHECKS = 1;
