package raisetech5.StudentManagement5;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech5.StudentManagement5.repositoly.Student;
import raisetech5.StudentManagement5.repositoly.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentsCourses(String studentId);


  @Insert("INSERT INTO students(name,nickname,email,town,age,gender,furigana,remark,isDeleted)"
      + " VALUES(#{name}, #{nickname},#{email} ,#{town},#{age},#{gender},#{furigana},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses(student_id, coursName, start_date, end_date)" +
      "VALUES(#{student_id} , #{coursName}, #{start_date}, #{end_date})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);


@Update("UPDATE students SET name = #{name}, nickname = #{nickname},email = #{email} ,"
    + " town = #{town}, age = #{age}, gender = #{gender}, furigana = #{furigana}, remark = #{remark}, is_deleted = #{isDeleted} WHERE id = #{id}")
void updateStudent(Student student);

@Update("UPDATE students_courses SET coursName = #{coursName} WHERE id = #{id}")
void updateStudentsCourses(StudentsCourses studentsCourses);
}
