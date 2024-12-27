package raisetech5.StudentManagement5.data;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ApplicationStatus {
  private final Long courseInfoId;

  @Enumerated(EnumType.STRING)
  @JsonDeserialize(using = StatusTypeDeserializer.class)
  private StatusType status;

  @Setter
  private Long id;

  @JsonCreator
  public ApplicationStatus(
      @JsonProperty("courseInfoId") Long courseInfoId,
      @JsonProperty("status") StatusType status
  ) {
    this.courseInfoId = courseInfoId;
    this.status = StatusType.fromString(String.valueOf(status));
  }
}



