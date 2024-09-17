package raisetech5.StudentManagement5.controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import raisetech5.StudentManagement5.data.Student;
import raisetech5.StudentManagement5.data.StudentCourse;
import raisetech5.StudentManagement5.domain.StudentDetail;

/**
 *　受講生詳細を受講生や受講生コース情報、もしくはその逆を行うこんばーたーです。
 */
@Component
public class StudentConverter {

  /**
   * 受講生に紐づく受講生コース情報をマッピングする。
   * 受講生コース情報は受講生に対して複数存在するのでループを回して受講生詳細情報を組み立てる。
   *
   * @param studentList　受講生一覧
   * @param studentCourseList　受講生コース情報のリスト
   * @return　受講生詳細情報のリスト
   */
  public List<StudentDetail> convertStudentDetails(List<Student> studentList,
      List<StudentCourse> studentCourseList) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    studentList.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentCourseList = studentCourseList.stream()
          .filter(studentCourse -> student.getId().equals(studentCourse.getStudent_id()))
          .collect(Collectors.toList());

      studentDetail.setStudentCourseList(convertStudentCourseList);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }

}

