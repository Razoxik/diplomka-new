SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO avatar VALUES(1, '1970-01-01 00:00:01',  'avatar_img_1', 'avatar_name_1' );
INSERT INTO avatar VALUES(2, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1'  );
INSERT INTO avatar VALUES(3, '1970-01-01 00:00:01', 'avatar_img_1', 'avatar_name_1'  );

INSERT INTO role VALUES(1,   'USER');
INSERT INTO role VALUES(2,   'ADMIN');
--https://www.dailycred.com/article/bcrypt-calculator
INSERT INTO user VALUES(1, '1970-01-01 00:00:01', 'mejl', 'tomas', '1970-02-02 02:02:02','bart', 'nicknameTest', 'tomas123',1,1 );
INSERT INTO user VALUES(2, '1970-01-01 00:00:01', 'asd@praise.sun', 'pepa', '1970-02-02 02:02:02','bart', '$2a$04$TPPTbBD7EBdOoG3wn3rCAe78Bjo1971yCWSr7ftyFc1vlVohko1M.', 'razox', 1,1  );
INSERT INTO user VALUES(3, '1970-01-01 00:00:01', 'asd@praise.sun', 'pepa', '1970-02-02 02:02:02','bart', '$2a$04$TPPTbBD7EBdOoG3wn3rCAe78Bjo1971yCWSr7ftyFc1vlVohko1M.', 'razox1', 1,1  );
INSERT INTO user VALUES(4, '1970-01-01 00:00:01', 'asd@praise.sun', 'pepa', '1970-02-02 02:02:02','bart', '$2a$04$TPPTbBD7EBdOoG3wn3rCAe78Bjo1971yCWSr7ftyFc1vlVohko1M.', 'razox2', 1,1  );
INSERT INTO user VALUES(5, '1970-01-01 00:00:01', 'asd@praise.sun', 'pepa', '1970-02-02 02:02:02','bart', '$2a$04$TPPTbBD7EBdOoG3wn3rCAe78Bjo1971yCWSr7ftyFc1vlVohko1M.', 'razox3', 1,1  );

INSERT INTO report_reason VALUES (1,   'feed');
INSERT INTO report_reason VALUES(2,   'DONT_COME');

INSERT INTO report VALUES(1, '1970-01-01 00:00:01',   'report_text_1',1 ,'1', '2');
INSERT INTO report VALUES(2, '2038-01-19 03:14:07',   'report_text_2', 2,'2', '1');

INSERT INTO friend VALUES(1, '1970-01-01 00:00:01', '1', '2');
INSERT INTO friend VALUES(2, '2038-01-19 03:14:07', '2', '1');

INSERT INTO message VALUES(1, '1970-01-01 00:00:01',   'message_text_1', 'message_subject_1',  '2',1);
INSERT INTO message VALUES(2, '2038-01-19 03:14:07',   'message_text_2', 'message_subject_2',  '1',2);

INSERT INTO game VALUES(1, '1970-01-01 00:00:01','game_description_1', 'chess');
INSERT INTO game VALUES(2, '1970-01-01 00:00:01','game_description_2', 'football');

INSERT INTO game_param VALUES(1, '1970-01-01 00:00:01',   'number_of_players', '2',1 );
INSERT INTO game_param VALUES(2,'2038-01-19 03:14:07',   'number_of_players',  '8',2 );
INSERT INTO game_param VALUES(3,'2038-01-19 03:14:07',   'game_param_name_2',  'game_param_value_2',2 );
INSERT INTO game_param VALUES(4,'2038-01-19 03:14:07',   'game_param_name_3',  'game_param_value_1',1 );

INSERT INTO challenge_state VALUES(1,'CREATED' );


INSERT INTO challenge VALUES(1, '49.902238', '16.439289', '1970-01-01 00:00:01','challenge_text_1',  '2038-01-19 03:14:07',  'pass',  '1970-01-01 00:00:01' ,1, 1);
--  '50.036816', '15.759573',

INSERT INTO result_state VALUES(1,'WINNER' );
INSERT INTO result_state VALUES(2,'DEFEATED' );
INSERT INTO result_state VALUES(3,'TIE' );
INSERT INTO result_state VALUES(4,'IN_PROGRESS' );


INSERT INTO challenge_result VALUES(1, '1970-01-01 00:00:01', 'challenge_result_description_1', 0, 1, 1,1, 1);
-- INSERT INTO challenge_result VALUES(2, 2, '2:3', 'challenge_result_description_2', '2038-01-19 03:14:07', 2, 1);
insert into rating values(1,'1970-01-01 00:00:01', 1500,1,1);
insert into rating values(2,'1970-01-01 00:00:01', 1400,1,2);
insert into rating values(3,'1970-01-01 00:00:01', 1300,2,1);
insert into rating values(4,'1970-01-01 00:00:01', 1100,2,2);

SET FOREIGN_KEY_CHECKS = 1;
