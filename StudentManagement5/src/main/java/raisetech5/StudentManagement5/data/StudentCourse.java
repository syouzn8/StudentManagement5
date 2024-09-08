package raisetech5.StudentManagement5.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentCourse {

  private String id;
  private String student_id;
  private String coursName;
  private LocalDateTime start_date;
  private LocalDateTime end_date;


}
