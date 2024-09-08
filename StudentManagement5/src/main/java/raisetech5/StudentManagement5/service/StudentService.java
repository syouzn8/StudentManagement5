package raisetech5.StudentManagement5.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech5.StudentManagement5.StudentConverter;
import raisetech5.StudentManagement5.StudentDetail;
import raisetech5.StudentManagement5.StudentRepository;
import raisetech5.StudentManagement5.repositoly.Student;
import raisetech5.StudentManagement5.repositoly.StudentsCourses;

/**
 * 受講生情報をとり扱うさーびすです。
 * 受講生の検索や登録・更新処理を行います。
 */
@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;


  @Autowired
  public StudentService(StudentRepository repository ,StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生一覧検索です。
   * 全検索おこなうので、条件指定は行いません。
   *

   * @return　受講生一覧（全件）
   * */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentsCourses> studentsCoursesList = repository.searchStudentsCoursesList();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  /**
   * 受講生検索です。
   * IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を習得して設定します。
   *
   * @param id　受講生ID
   * @return　受講生
   */

  public StudentDetail searchStudent(String id) {
    Student student = repository.searchStudent(id);
    List<StudentsCourses> studentsCourses = repository.searchStudentsCourses(student.getId());
    return new StudentDetail(student,studentsCourses);
  }

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      studentsCourses.setStudent_id(studentDetail.getStudent().getId());
      studentsCourses.setStart_date(LocalDateTime.now());
      studentsCourses.setEnd_date(LocalDateTime.now().plusYears(1));
      repository.registerStudentsCourses(studentsCourses);
    }
    return studentDetail;
  }
  @Transactional
  public void updateStudent (StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for (StudentsCourses studentsCourses : studentDetail.getStudentsCourses()) {
      repository.updateStudentsCourses(studentsCourses);
    }
  }}
