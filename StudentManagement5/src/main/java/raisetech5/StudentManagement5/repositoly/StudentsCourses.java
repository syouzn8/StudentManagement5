package raisetech5.StudentManagement5.repositoly;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentsCourses {

  private String id;
  private String student_id;
  private String coursName;
  private LocalDateTime start_date;
  private LocalDateTime end_date;


}
