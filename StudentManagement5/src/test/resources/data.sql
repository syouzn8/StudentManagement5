INSERT INTO students (id, name, nickname, email, town, age, gender, furigana)
VALUES
  (1001, 'MisakiTakahashi', 'Misaki', 'misaki@example.com', 'Fukuoka', 21, 'Female', 'Misaki Takahashi'),
  (1002, 'KojiSato', 'Kou', 'kouji@example.com', 'Kagawa', 27, 'Male', 'Koji Sato'),
  (1003, '佐野', 'sasaサ', 'sano@example.com', '東京', 34, '男', 'サノエイジ'),
  (1004, '鈴木 コルト', 'コルト', 'koruto@example.com', '長野', 20, '男', 'スズキ コルト');

INSERT INTO students_courses (student_id, coursName, start_date, end_date)
VALUES
  (1001, 'Math', '2024-09-01 00:00:00', '2025-01-15 00:00:00'),
  (1002, 'History', '2024-09-01 00:00:00', '2025-04-15 00:00:00'),
  (1003, 'Science', '2024-08-01 00:00:00', '2025-01-15 00:00:00'),
  (1004, 'コース', '2024-08-31 14:52:22', '2025-08-31 14:52:22'),
  (1004, 'Javaコース', '2024-08-31 22:08:52', '2025-08-31 22:08:52'),
  (1004, 'Javaコース', '2024-09-03 20:50:53', '2025-09-03 20:50:53');

INSERT INTO application_statuses (course_info_id, status)
VALUES
(1, 'tentative application'),
(2, 'confirmed application'),
(3, 'in progress'),
(8, 'completed'),
(9, 'tentative application'),
(13, 'confirmed application');