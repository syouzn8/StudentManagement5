package raisetech5.StudentManagement5.controller.converter;



    import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
    import raisetech5.StudentManagement5.data.Student;
    import raisetech5.StudentManagement5.data.StudentCourse;
    import raisetech5.StudentManagement5.domain.StudentDetail;

public class StudentConverterTest {

    @InjectMocks
    private StudentConverter studentConverter;

    @BeforeEach
    public void setUp() {
      MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertStudentDetails() {

      Student student1 = new Student();
      student1.setId(String.valueOf(1L));
      student1.setName("John Doe");

      Student student2 = new Student();
      student2.setId(String.valueOf(2L));
      student2.setName("Jane Smith");

      StudentCourse course1 = new StudentCourse();
      course1.setId(String.valueOf(1L));
      course1.setCoursName("Math");
      course1.setStudent_id(String.valueOf(1L));

      StudentCourse course2 = new StudentCourse();
      course2.setId(String.valueOf(2L));
      course2.setCoursName("Science");
      course2.setStudent_id(String.valueOf(1L));

      StudentCourse course3 = new StudentCourse();
      course3.setId(String.valueOf(3L));
      course3.setCoursName("History");
      course3.setStudent_id(String.valueOf(2L));

      List<Student> studentList = Arrays.asList(student1, student2);
      List<StudentCourse> studentCourseList = Arrays.asList(course1, course2, course3);

      List<StudentDetail> studentDetails = studentConverter.convertStudentDetails(studentList,
          studentCourseList);

      assertEquals(2, studentDetails.size());

      StudentDetail studentDetail1 = studentDetails.get(0);
      assertEquals(student1, studentDetail1.getStudent());
      assertEquals(2, studentDetail1.getStudentCourseList().size());

      StudentDetail studentDetail2 = studentDetails.get(1);
      assertEquals(student2, studentDetail2.getStudent());
      assertEquals(1, studentDetail2.getStudentCourseList().size());
    }
  }

