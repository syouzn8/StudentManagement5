INSERT INTO students (id, name, nickname, email, town, age, gender, furigana)
VALUES 
  (2, 'MisakiTakahashi', 'Misaki', 'misaki@example.com', 'Fukuoka', 21, 'Female', 'Misaki Takahashi'),
  (3, 'KojiSato', 'Kou', 'kouji@example.com', 'Kagawa', 27, 'Male', 'Koji Sato'),
  (23, '佐野', 'sasaサ', 'sano@example.com', '東京', 34, '男', 'サノエイジ'),
  (24, '鈴木 コルト', 'コルト', 'koruto@example.com', '長野', 20, '男', 'スズキ コルト');

INSERT INTO students_courses (id, student_id, coursName, start_date, end_date)
VALUES 
  (1, '1001', 'Math', '2024-09-01 00:00:00', '2025-01-15 00:00:00'),
  (2, '1002', 'History', '2024-09-01 00:00:00', '2025-04-15 00:00:00'),
  (3, '1003', 'Science', '2024-08-01 00:00:00', '2025-01-15 00:00:00'),
  (8, '23', 'コース', '2024-08-31 14:52:22', '2025-08-31 14:52:22'),
  (9, '24', 'Javaコース', '2024-08-31 22:08:52', '2025-08-31 22:08:52'),
  (13, '28', 'Javaコース', '2024-09-03 20:50:53', '2025-09-03 20:50:53');