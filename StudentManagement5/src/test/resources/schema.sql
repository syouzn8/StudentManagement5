CREATE TABLE IF NOT EXISTS students
(
  id int AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  nickname varchar(100) DEFAULT NULL,
  email varchar(255) NOT NULL,
  town varchar(100) DEFAULT NULL,
  age int DEFAULT NULL,
  gender varchar(100) DEFAULT NULL,
  furigana varchar(255) NOT NULL,
  remark varchar(200) DEFAULT NULL,
  isDeleted boolean DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS students_courses
(
  id int AUTO_INCREMENT,
  student_id INT DEFAULT NULL,
  coursName varchar(100) DEFAULT NULL,
  start_date datetime DEFAULT NULL,
  end_date datetime DEFAULT NULL
);
