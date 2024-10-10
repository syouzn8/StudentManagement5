 CREATE TABLE  IF NOT EXISTS students
 (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  nickname varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  town varchar(100) DEFAULT NULL,
  age int DEFAULT NULL,
  gender varchar(100) DEFAULT NULL,
  furigana varchar(100) DEFAULT NULL,
  remark varchar(200) DEFAULT NULL,
  isDeleted tinyint(1) DEFAULT NULL,
);

  CREATE TABLE  IF NOT EXISTS students_courses
   (
  id int NOT NULL AUTO_INCREMENT,
  student_id varchar(1000) DEFAULT NULL,
  coursName varchar(100) DEFAULT NULL,
  start_date datetime DEFAULT NULL,
  end_date datetime DEFAULT NULL,
  );