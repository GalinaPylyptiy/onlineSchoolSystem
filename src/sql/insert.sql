
INSERT INTO subject VALUES(1,'Русский язык');
INSERT INTO subject VALUES(2,'Математика');
INSERT INTO subject VALUES(3,'Казахский язык');
INSERT INTO subject VALUES(4,'Английский язык');
INSERT INTO subject VALUES(5,'География');
INSERT INTO subject VALUES(6,'Физическая культура');
INSERT INTO subject VALUES(7,'Искусство');

INSERT INTO locale VALUES(1, 'ru-RU', 'РУССКИЙ');
INSERT INTO locale VALUES(2, 'en-EN', 'ENGLISH');

INSERT INTO subject_locale VALUES(1,2,'Russian language');
INSERT INTO subject_locale VALUES(2,2,'Maths');
INSERT INTO subject_locale VALUES(3,2,'Kazakh language');
INSERT INTO subject_locale VALUES(4,2,'English language');
INSERT INTO subject_locale VALUES(5,2,'Geography');
INSERT INTO subject_locale VALUES(6,2,'Physical education');
INSERT INTO subject_locale VALUES(7,2,'Art');
INSERT INTO subject_locale VALUES(1,1,'Русский язык');
INSERT INTO subject_locale VALUES(2,1,'Математика');
INSERT INTO subject_locale VALUES(3,1,'Казахский язык');
INSERT INTO subject_locale VALUES(4,1,'Английский язык');
INSERT INTO subject_locale VALUES(5,1,'География');
INSERT INTO subject_locale VALUES(6,1,'Физическая культура');
INSERT INTO subject_locale VALUES(7,1,'Искусство');

INSERT INTO student_level(id, level_name) VALUES(1, '1А');
INSERT INTO student_level(id, level_name) VALUES(2, '2А');
INSERT INTO student_level(id, level_name) VALUES(3, '3А');
INSERT INTO student_level(id, level_name) VALUES(4, '4А');
INSERT INTO student_level(id, level_name) VALUES(5, '5А');
INSERT INTO student_level(id, level_name) VALUES(6, '6А');
INSERT INTO student_level(id, level_name) VALUES(7, '7А');
INSERT INTO student_level(id, level_name) VALUES(8, '8А');
INSERT INTO student_level(id, level_name) VALUES(9, '9А');
INSERT INTO student_level(id, level_name) VALUES(10, '10А');
INSERT INTO student_level(id, level_name) VALUES(11, '11А');


INSERT INTO student(last_name, first_name, middle_name, login, password, level_id, is_active) 
VALUES('Алексеева','Дарья','Ивановна','s345t111','632f3bd3caebb8337512c74de629cdff63b3b42f6b8679299bd2b0980fa0ddb6',5,'true');
INSERT INTO student(last_name, first_name, middle_name, login, password, level_id, is_active) 
VALUES('Абдрахманов','Никита','Алексеевич','s345t112','690d7bcdf1f83567a7038c84ec0de6f7daa3f64db491955e5bc8927a4274a36c',5,'true');
INSERT INTO student(last_name, first_name, middle_name, login, password, level_id, is_active) 
VALUES('Попов','Артур','Станиславович','s345t113','6db05aaf3aa5a064ae3e2242f25eb607e1e4cfb5eee67e01497cdf2b276ffa9',5,'true');
INSERT INTO student(last_name, first_name, middle_name, login, password, level_id, is_active) 
VALUES('Самохина','Светлана','Рустамовна','s345t114','a0815f2cfbaf145a201b8023da3fb8c2f80a963f1d37e959fa1e08596a75dc48',5,'true');
INSERT INTO student(last_name, first_name, middle_name, login, password, level_id, is_active) 
VALUES('Коровина','Евгения','Сергеевна','s345t115','3a9a5930f0b3b6edcf5f66c1f3140c6b051d357ca13885d99860f30e4ae6184f',9,'true');
INSERT INTO student(last_name, first_name, middle_name, login, password, level_id, is_active) 
VALUES('Марков','Владислав','Викторович','s345t116','783eb467849d22a1cf7a6165e9dc8eb05df45852c2e96fcfec3326a908092e9b',9,'true');
INSERT INTO student(last_name, first_name, middle_name, login, password, level_id, is_active) 
VALUES('Самохвалова','Олеся','Анатольевна','s345t117','2b257d142f54f0187a1b2db5a10444331024c1dc5af22ecd920633f12295c15b',9,'true');

INSERT INTO teacher_type VALUES (1, 'Директор');
INSERT INTO teacher_type VALUES (2, 'Учитель-предметник');
INSERT INTO teacher_type VALUES (3, 'Завуч');
INSERT INTO teacher_type VALUES (4, 'Учитель начальной школы');

INSERT INTO teacher_type_locale VALUES (1,1,'Директор');
INSERT INTO teacher_type_locale VALUES (2,1,'Учитель-предметник');
INSERT INTO teacher_type_locale VALUES (3,1,'Завуч');
INSERT INTO teacher_type_locale VALUES (4,1,'Учитель начальной школы');
INSERT INTO teacher_type_locale VALUES (1,2,'School principal');
INSERT INTO teacher_type_locale VALUES (2,2,'Subject teacher');
INSERT INTO teacher_type_locale VALUES (3,2,'Vice principal');
INSERT INTO teacher_type_locale VALUES (4,2,'Primary school teacher');

INSERT INTO teacher(last_name, first_name, middle_name, login, password, teacher_type_id, is_admin, is_active) 
VALUES('Сухинина','Лариса','Анатольевна','t765s111','294b2e5fb6509885c43a0eb2c72a43aa4c61b0557c0ed80ba682f26a356486ab',1,'true','true');
INSERT INTO teacher(last_name, first_name, middle_name, login, password, teacher_type_id, is_admin, is_active) 
VALUES('Бакылжаов','Сакен','Нуркешевич', 't765s112', '13a2b8a8a03592795f7bde67092c9275355ffa74bef3c510665902c308424461', 3, 'true', 'true'); 
INSERT INTO teacher(last_name, first_name, middle_name,  login, password, teacher_type_id, is_admin, is_active) 
VALUES('Войтова','Валентина','Александровна', 't765s113', '11f45c643ee276abd8e8a1c2d06c92cc0a021f545133458b6c23254f874b6c30',4,'false','true');
INSERT INTO teacher(last_name, first_name, middle_name,  login, password, teacher_type_id, is_admin, is_active) 
VALUES('Абдрахманова','Жанна','Куанышевна', 't765s114', '46797e6203e6060e2b533ba4d9978836d6d99770ca90fb16df9ca935a073759a',2,'false','true' );
INSERT INTO teacher(last_name, first_name, middle_name,  login, password, teacher_type_id, is_admin, is_active) 
VALUES('Рощина','Диана','Валерьевна', 't765s115', '404922a34168ce33bae02d7465bf881d0fc2263cb2e9739cb514e687266a9a14',2,'false','true' );
INSERT INTO teacher(last_name, first_name, middle_name,  login, password, teacher_type_id, is_admin, is_active) 
VALUES('Дубровская','Алена','Александровна', 't765s116', '4232cf9dc588dbdb17a3084c028c1d40c5112d7107569b708d488c90a9ae2df7',3,'true','true' );  
INSERT INTO teacher(last_name, first_name, middle_name,  login, password, teacher_type_id, is_admin, is_active) 
VALUES('Гарипова','Наталья','Владимировна', 't765s117', 'c2bd06eaba4bcb7c72001575d879a6cc73ad64f6ca6ac1b10dc2a567386e68cc',2,'false','true' );

INSERT INTO teacher_subject_list VALUES (1,1);
INSERT INTO teacher_subject_list VALUES (2,3);
INSERT INTO teacher_subject_list VALUES (3,1);
INSERT INTO teacher_subject_list VALUES (3,2);
INSERT INTO teacher_subject_list VALUES (3,4);
INSERT INTO teacher_subject_list VALUES (3,7);
INSERT INTO teacher_subject_list VALUES (4,2);
INSERT INTO teacher_subject_list VALUES (5,4);
INSERT INTO teacher_subject_list VALUES (6,5);
INSERT INTO teacher_subject_list VALUES (7,7);
INSERT INTO teacher_subject_list VALUES (7,6);

INSERT INTO schedule (student_level_id, subject_id, teacher_id, time_of_the_lesson, day_of_week, is_active ) 
VALUES(5, 1, 1, '8:30:00',1,'true');
INSERT INTO schedule (student_level_id, subject_id, teacher_id, time_of_the_lesson, day_of_week, is_active ) 
VALUES(5, 2, 4, '9:00:00',2,'true');
INSERT INTO schedule (student_level_id, subject_id, teacher_id, time_of_the_lesson, day_of_week, is_active ) 
VALUES(5, 5, 6,'10:00:00',3,'true');
INSERT INTO schedule (student_level_id, subject_id, teacher_id, time_of_the_lesson, day_of_week, is_active ) 
VALUES(5, 4, 5, '12:00:00', 4 ,'true');
INSERT INTO schedule (student_level_id, subject_id, teacher_id, time_of_the_lesson, day_of_week, is_active ) 
VALUES(5, 6, 7, '11:30:00',5,'true');
INSERT INTO schedule (student_level_id, subject_id, teacher_id, time_of_the_lesson, day_of_week, is_active ) 
VALUES(9, 5, 6, '11:00:00',1,'true');


INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-03-09', 5, 5, 6, 'Географические координаты','Прочитать параграф 5', 'true');
INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-04-13', 5, 5, 6, 'Вращение земли вокруг своей оси','Ответить на вопросы стр.45', 'true');
INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-05-03', 5, 5, 6, 'Землетрясения','Ответить на вопросы стр.100', 'true');
INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-05-11', 5, 5, 6, 'Литосфера и земная кора','Прочитать параграф 9', 'true');
INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-04-11', 5, 9, 6, 'Строение земной коры','Ответить на вопросы стр.45', 'true');
INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-05-01', 5, 9, 6, 'Подземные воды, ледники','Ответить на вопросы стр.100', 'true');
INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-05-09', 5, 9, 6, 'Минеральные ресурсы','Прочитать параграф 9', 'true');
INSERT INTO curriculum (lesson_date, subject_id, student_level_id, teacher_id, lesson_theme, home_task, is_active ) 
VALUES('2022-05-09', 4, 5, 5, 'Present Simple Tense','Make up sentences', 'true');


INSERT INTO student_assessment (curriculum_id, student_id, grade, assessment_date, is_active ) 
VALUES(1, 1, 7, '2022-03-11','true');
INSERT INTO student_assessment (curriculum_id, student_id, grade, assessment_date, is_active ) 
VALUES(2, 1, 9, '2022-04-14','true');
INSERT INTO student_assessment (curriculum_id, student_id, grade, assessment_date, is_active ) 
VALUES(8, 1, 6, '2022-05-10','true');
INSERT INTO student_assessment (curriculum_id, student_id, grade, assessment_date, is_active ) 
VALUES(4, 1, 8, '2022-05-11','true');



