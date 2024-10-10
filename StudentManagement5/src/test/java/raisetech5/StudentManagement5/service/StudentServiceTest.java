package raisetech5.StudentManagement5.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import raisetech5.StudentManagement5.controller.StudentController;
import raisetech5.StudentManagement5.controller.converter.StudentConverter;
import raisetech5.StudentManagement5.data.Student;
import raisetech5.StudentManagement5.data.StudentCourse;
import raisetech5.StudentManagement5.domain.StudentDetail;
import raisetech5.StudentManagement5.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @InjectMocks
  private StudentService service;

  @BeforeEach
  void before(){
    sut = new StudentService(repository, converter);
  }
  @BeforeEach
  void setUp() {
  }


  @Test
  void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {

    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    sut.searchStudentList();


    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }
  @Test
  void 受講生詳細検索_リポジトリの処理が適切に呼び出せていること() {
    Student student = new Student();
    student.setId(String.valueOf(1L));
    student.setName("佐野太郎");

    StudentCourse course1 = new StudentCourse();
    course1.setStudent_id(String.valueOf(101L));

    StudentCourse course2 = new StudentCourse();
    course2.setStudent_id(String.valueOf(102L));

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(Arrays.asList(course1, course2));

    doNothing().when(repository).registerStudent(any(Student.class));
    doNothing().when(repository).registerStudentCourse(any(StudentCourse.class));

    StudentDetail result = service.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(2)).registerStudentCourse(any(StudentCourse.class));

    assertNotNull(result);
    assertEquals(studentDetail, result);
  }

  @Test
  void 受講生詳細の更新_studentDetailの処理が適切に呼び出せていること(){
        Student student = new Student();
        student.setId(String.valueOf(1L));
        student.setName("John Doe");

        StudentCourse course1 = new StudentCourse();
        course1.setId(String.valueOf(101L));
        course1.setCoursName("Math");

        StudentCourse course2 = new StudentCourse();
        course2.setId(String.valueOf(102L));
        course2.setCoursName("Science");

        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        studentDetail.setStudentCourseList(Arrays.asList(course1, course2));

        doNothing().when(repository).updateStudent(student);
        doNothing().when(repository).updateStudentCourse(course1);
        doNothing().when(repository).updateStudentCourse(course2);

        StudentService.updateStudent(studentDetail);

        verify(repository, times(1)).updateStudent(student);
        verify(repository, times(1)).updateStudentCourse(course1);
        verify(repository, times(1)).updateStudentCourse(course2);
      }
    }