SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO avatar VALUES(1, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );
INSERT INTO avatar VALUES(2, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );
INSERT INTO avatar VALUES(3, 'avatar_img_1', 'avatar_name_1', '1970-01-01 00:00:01' );

INSERT INTO role VALUES(1, '1970-01-01 00:00:01', 'USER');
INSERT INTO role VALUES(2, '2038-01-19 03:14:07', 'ADMIN');

INSERT INTO user VALUES(1, '1970-01-01 00:00:01', 'mejl', 'tomas', '1970-02-02 02:02:02','bart', 'AsylumDemon@praise.sun', 'tomas123', '123',  '1', '1');
INSERT INTO user VALUES(2, '1970-01-01 00:00:01', 'asdasddas', 'pepa', '1970-02-02 02:02:02','bart', 'asd@praise.sun', 'asd', '23',  '2', '2');

INSERT INTO report VALUES(1, '1970-01-01 00:00:01', 'report_reason_1', 'report_text_1', '1', '2');
INSERT INTO report VALUES(2, '2038-01-19 03:14:07', 'report_reason_2', 'report_text_2', '2', '1');

INSERT INTO friend VALUES(1, '1970-01-01 00:00:01', '1', '2');
INSERT INTO friend VALUES(2, '2038-01-19 03:14:07', '2', '1');

INSERT INTO message VALUES(1, '1970-01-01 00:00:01', 'message_text_1', 'message_subject_1', '1', '2');
INSERT INTO message VALUES(2, '2038-01-19 03:14:07', 'message_text_2', 'message_subject_2', '2', '1');

INSERT INTO game_param VALUES(1, '1970-01-01 00:00:01', 'game_param_name_1', 'game_param_value_1', '1');
INSERT INTO game_param VALUES(2,'2038-01-19 03:14:07', 'game_param_name_2',  'game_param_value_2', '2');

INSERT INTO game VALUES(1, 'game_description_1', 'chess');
INSERT INTO game VALUES(2, 'game_description_2', 'football');

INSERT INTO league VALUES(1, 'Centipede Deamon League', 'league_description_1', 1);
INSERT INTO league VALUES(2, 'Moonlight Butterfly League', 'league_description_2', 2);

INSERT INTO team VALUES(1, '1970-01-01 00:00:01', 'Nito team', 'team_description_1', '1', 1);
INSERT INTO team VALUES(2, '2038-01-19 03:14:07', 'Ornstein and Smough', 'team_description_2', '2', 2);

INSERT INTO team_user VALUES(1, 1, 1);
INSERT INTO team_user VALUES(2, 2, 2);

INSERT INTO challenge VALUES(1,'1970-01-01 00:00:01', '2038-01-19 03:14:07', '49.902238', '16.439289',  'challenge_text_1', 1, 2);
INSERT INTO challenge VALUES(2,'1970-01-01 00:00:01', '2038-01-19 03:14:07', '49.904809', '16.440984', 'challenge_text_1', 2, 1);
INSERT INTO challenge VALUES(3,'1970-01-01 00:00:01', '2038-01-19 03:14:07', '50.039969', '15.773887', 'challenge_text_1', 2, 1);
INSERT INTO challenge VALUES(4,'1970-01-01 00:00:01', '2038-01-19 03:14:07', '50.040915', '15.769183', 'challenge_text_1', 2, 1);
INSERT INTO challenge VALUES(5,'1970-01-01 00:00:01', '2038-01-19 03:14:07', '50.040240', '15.760171', 'challenge_text_1', 2, 1);

-- INSERT INTO challenge VALUES(2, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '49.904753', '16.445780');
-- INSERT INTO challenge VALUES(3, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '49.904028', '16.445855');
-- INSERT INTO challenge VALUES(4, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '49.904809', '16.440984');
-- INSERT INTO challenge VALUES(5, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '49.903233', '16.446971');
-- INSERT INTO challenge VALUES(6, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '50.039279', '15.767583');
-- INSERT INTO challenge VALUES(7, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '50.040165', '15.768265');
-- INSERT INTO challenge VALUES(8, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '50.036581', '15.770325');
-- INSERT INTO challenge VALUES(9, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '50.033852', '15.767514');
-- INSERT INTO challenge VALUES(10, 2, 1, '1970-01-01 00:00:01', '2038-01-19 03:14:07', 'challenge_text_2', '50.034611', '15.770003');

INSERT INTO challenge_result VALUES(1, '1970-01-01 00:00:01','2:1', 1, 'challenge_result_description_1',  1, 0);
-- INSERT INTO challenge_result VALUES(2, 2, '2:3', 'challenge_result_description_2', '2038-01-19 03:14:07', 2, 1);

SET FOREIGN_KEY_CHECKS = 1;
