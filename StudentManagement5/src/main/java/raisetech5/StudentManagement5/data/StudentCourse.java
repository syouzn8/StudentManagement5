package raisetech5.StudentManagement5.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Schema(description =  "受講生コース情報")
@Setter
@Getter
public class StudentCourse {

  private String id;
  private String student_id;
  private String coursName;
  private LocalDateTime start_date;
  private LocalDateTime end_date;


}
