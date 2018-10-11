SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO avatar VALUES(1, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );
INSERT INTO avatar VALUES(2, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );
INSERT INTO avatar VALUES(3, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );

INSERT INTO role VALUES(1, '1970-01-01 00:00:01', 'USER');
INSERT INTO role VALUES(2, '2038-01-19 03:14:07', 'ADMIN');

INSERT INTO user VALUES(1, 1, '1970-01-01 00:00:01', 'mejl', 'tomas', '1970-02-02 02:02:02','bart', 'nicknameTest', 'tomas123', 1);
INSERT INTO user VALUES(2, 1, '1970-01-01 00:00:01', 'asd@praise.sun', 'pepa', '1970-02-02 02:02:02','bart', 'razox', 'asd', 1);

INSERT INTO report VALUES(1, '1970-01-01 00:00:01', 'report_reason_1', 'report_text_1', '1', '2');
INSERT INTO report VALUES(2, '2038-01-19 03:14:07', 'report_reason_2', 'report_text_2', '2', '1');

INSERT INTO friend VALUES(1, '1970-01-01 00:00:01', '1', '2');
INSERT INTO friend VALUES(2, '2038-01-19 03:14:07', '2', '1');

INSERT INTO message VALUES(1, '1970-01-01 00:00:01', 1, 'message_text_1', 'message_subject_1',  '2');
INSERT INTO message VALUES(2, '2038-01-19 03:14:07', 2, 'message_text_2', 'message_subject_2',  '1');

INSERT INTO game_param VALUES(1, '1970-01-01 00:00:01', 1, 'game_param_name_1', 'game_param_value_1' );
INSERT INTO game_param VALUES(2,'2038-01-19 03:14:07', 2, 'game_param_name_2',  'game_param_value_2' );

INSERT INTO game VALUES(1, 'game_description_1', 'chess');
INSERT INTO game VALUES(2, 'game_description_2', 'football');

INSERT INTO challenge VALUES(1,'1970-01-01 00:00:01', '2038-01-19 03:14:07', 1, '49.902238', '16.439289', 1,2,'pass','challenge_text_1');

INSERT INTO challenge_result VALUES(1, 1, '1970-01-01 00:00:01','challenge_result_description_1',0,'2:1', 1 );
-- INSERT INTO challenge_result VALUES(2, 2, '2:3', 'challenge_result_description_2', '2038-01-19 03:14:07', 2, 1);
insert into rating values(1,1300,1,1);
SET FOREIGN_KEY_CHECKS = 1;
