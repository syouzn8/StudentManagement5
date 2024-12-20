package raisetech5.StudentManagement5.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech5.StudentManagement5.data.Student;
import raisetech5.StudentManagement5.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */

@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行います。
   *
   * @return　受講生一覧（全体）
   */
  List<Student> search();//

  /**
   * 受講生の検索を行います。
   *
   * @param id 　受講生ID
   * @return　受講生
   */
  Student searchStudent(String id);//

  /**
   * 受講生のコース情報の全件検索をおこないます。
   *
   * @return　受講生のコース情報（全件）
   */
  List<StudentCourse> searchStudentCourseList();//

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param studentId 　じゅこうせいID
   * @return　受講生IDに紐づく受講生コース情報
   */
  List<StudentCourse> searchStudentCourse(String studentId);//

  /**
   * 受講生を新規登録します。IDに関しては自動採番を行う。
   *
   * @param student 　受講生
   */
  void registerStudent(Student student);//

  /**
   * 受講生コース情報を新規登録します。IDに関しては自動採番を行う
   *
   * @param studentCourse 　受講生コース情報
   */
  void registerStudentCourse(StudentCourse studentCourse);//


  /**
   * 受講生を更新します。
   *
   * @param student 　受講生
   */
  void updateStudent(Student student);//

  /**
   * 受講生コース情報のコース名を更新します。
   *
   * @param studentCourse 　受講生コース情報
   */
  void updateStudentCourse(StudentCourse studentCourse);


  @Select("SELECT * FROM students WHERE id = #{id}")//
  Student findById(int id);









}

