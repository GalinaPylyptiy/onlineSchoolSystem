
CREATE DATABASE online_school;


CREATE TABLE subject (
id INTEGER PRIMARY KEY,
subject_name VARCHAR(100) NOT NULL);

CREATE TABLE locale (
id INTEGER PRIMARY KEY, 
short_name VARCHAR (10) NOT NULL,
full_name VARCHAR(20) NOT NULL);

CREATE TABLE subject_locale (
subject_id INTEGER REFERENCES subject(id),
locale_id INTEGER REFERENCES locale(id),
subject_locale_name VARCHAR(100));

CREATE TABLE student_level(
id INTEGER PRIMARY KEY,
level_name VARCHAR(5) NOT NULL);


CREATE TABLE student(
id BIGSERIAL PRIMARY KEY,
last_name VARCHAR(100) NOT NULL,
first_name VARCHAR(70) NOT NULL,
middle_name VARCHAR(100),
login VARCHAR(50) NOT NULL,
password VARCHAR(100) NOT NULL,
level_id INTEGER NOT NULL REFERENCES student_level(id),
is_active BOOLEAN NOT NULL );


CREATE TABLE teacher_type(
id INTEGER PRIMARY KEY,
type_name VARCHAR(50) NOT NULL);


CREATE TABLE teacher_type_locale(
teacher_type_id INTEGER NOT NULL REFERENCES teacher_type(id),
locale_id INTEGER NOT NULL REFERENCES locale(id),
locale_type_name VARCHAR(100) NOT NULL);


CREATE TABLE teacher(
id BIGSERIAL PRIMARY KEY,
last_name VARCHAR(100) NOT NULL,
first_name VARCHAR(70) NOT NULL,
middle_name VARCHAR(100),
login VARCHAR(50) NOT NULL,
password VARCHAR(100) NOT NULL,
teacher_type_id INTEGER NOT NULL REFERENCES teacher_type(id),
is_admin BOOLEAN NOT NULL,
is_active BOOLEAN NOT NULL);


CREATE TABLE teacher_subject_list(
teacher_id INTEGER NOT NULL REFERENCES teacher(id),
subject_id INTEGER NOT NULL REFERENCES subject(id));


CREATE TABLE schedule(
id BIGSERIAL PRIMARY KEY,
time_of_the_lesson TIME NOT NULL,
student_level_id INTEGER NOT NULL REFERENCES student_level(id),
subject_id INTEGER NOT NULL REFERENCES subject(id),
teacher_id INTEGER NOT NULL REFERENCES teacher(id),
day_of_week INTEGER NOT NULL,
is_active BOOLEAN NOT NULL);


CREATE TABLE curriculum (
id BIGSERIAL PRIMARY KEY,
lesson_date DATE NOT NULL,
subject_id INTEGER  NOT NULL REFERENCES subject(id),
student_level_id INTEGER NOT NULL REFERENCES student_level(id),
teacher_id INTEGER NOT NULL REFERENCES teacher(id),
lesson_theme VARCHAR(200) NOT NULL,
home_task VARCHAR(200) NOT NULL,
is_active BOOLEAN NOT NULL);


CREATE TABLE student_assessment (
id BIGSERIAL PRIMARY KEY,
curriculum_id BIGINT NOT NULL REFERENCES curriculum(id),
student_id BIGINT NOT NULL REFERENCES student(id),
grade INTEGER NOT NULL,
assessment_date DATE NOT NULL,
is_active BOOLEAN NOT NULL);