package raisetech5.StudentManagement5;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech5.StudentManagement5.repositoly.Student;
import raisetech5.StudentManagement5.repositoly.StudentsCourses;

@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;

}

