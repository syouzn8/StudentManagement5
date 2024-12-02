package raisetech5.StudentManagement5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import raisetech5.StudentManagement5.controller.converter.StudentConverter;
import raisetech5.StudentManagement5.data.Student;
import raisetech5.StudentManagement5.data.StudentCourse;
import raisetech5.StudentManagement5.domain.StudentDetail;
import raisetech5.StudentManagement5.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;


@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;
  @Autowired
  private StudentRepository repository;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(0);
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("榎並浩二");
    student.setFurigana("エナミコウジ");
    student.setNickname("エナミ");
    student.setEmail("test@example.com");
    student.setTown("奈良県");
    student.setGender("男性");
    student.setAge(38);
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(1);
  }

  @Test
  void 受講生が検索できること() {

    Student student = new Student();
    student.setName("榎並浩二");
    student.setFurigana("エナミコウジ");
    student.setNickname("エナミ");
    student.setEmail("test@example.com");
    student.setTown("奈良県");
    student.setGender("男性");
    student.setAge(38);
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);
    String studentId = student.getId();

    Student actual = sut.searchStudent(studentId);

    assertThat(actual).isNotNull();
    assertThat(actual.getName()).isEqualTo("榎並浩二");
    assertThat(actual.getFurigana()).isEqualTo("エナミコウジ");
    assertThat(actual.getEmail()).isEqualTo("test@example.com");
    assertThat(actual.getTown()).isEqualTo("奈良県");
    assertThat(actual.getGender()).isEqualTo("男性");
    assertThat(actual.getAge()).isEqualTo(38);
  }

  @Test
  void 受講生コースの全件検索ができること() {

    List<StudentCourse> actual = sut.searchStudentCourseList();

    assertThat(actual.size()).isEqualTo(6);

    assertThat(actual).extracting(StudentCourse::getCoursName)
        .containsExactlyInAnyOrder("Math", "History", "Science", "コース", "Javaコース",
            "Javaコース");
  }

  @Test
  void 受講生コース情報が検索できること() {

    Student student = new Student();
    student.setName("MisakiTakahashi");
    student.setFurigana("Misaki Takahashi");
    student.setNickname("Misaki");
    student.setEmail("misaki@example.com");
    student.setTown("Fukuoka");
    student.setGender("Female");
    student.setAge(21);
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);
    String studentId = student.getId();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudent_id(studentId);
    studentCourse.setCoursName("Math");

    sut.registerStudentCourse(studentCourse);

    List<StudentCourse> actualCourses = sut.searchStudentCourse(studentId);

    assertThat(actualCourses).isNotNull();
    assertThat(actualCourses.size()).isGreaterThan(0);

    StudentCourse actualCourse = actualCourses.get(0);
    assertThat(actualCourse.getCoursName()).isEqualTo("Math");
  }

  @Test
  void 受講生を更新します() {

    Student student = new Student();
    student.setId(String.valueOf(1001));
    student.setName("MisakiTakahashi");
    student.setFurigana("Misaki Takahashi");
    student.setNickname("Misaki");
    student.setEmail("misaki@example.com");
    student.setTown("Fukuoka");
    student.setGender("Female");
    student.setAge(21);
    student.setRemark("");
    student.setDeleted(false);

    repository.updateStudent(student);

    Student updatedStudent = repository.findById(1001);
    assertEquals("MisakiTakahashi", updatedStudent.getName());
    assertEquals("Misaki", updatedStudent.getNickname());
  }


  @Test
  void 受講生コース情報のコース名を更新します() {

    Student student1 = new Student();
    student1.setId("1");
    student1.setName("Alice");

    Student student2 = new Student();
    student2.setId("2");
    student2.setName("Bob");

    List<Student> students = List.of(student1, student2);

    StudentCourse course1 = new StudentCourse();
    course1.setId("1");
    course1.setStudent_id("1");
    course1.setCoursName("Math");

    StudentCourse course2 = new StudentCourse();
    course2.setId("2");
    course2.setStudent_id("2");
    course2.setCoursName("Science");

    StudentCourse course3 = new StudentCourse();
    course3.setId("3");
    course3.setStudent_id("1");
    course3.setCoursName("History");

    List<StudentCourse> courses = List.of(course1, course2, course3);

    StudentConverter converter = new StudentConverter();
    List<StudentDetail> result = converter.convertStudentDetails(students, courses);

    assertEquals(2, result.size());
    assertEquals(2, result.get(0).getStudentCourseList().size());
    assertEquals(1, result.get(1).getStudentCourseList().size());
  }
}





