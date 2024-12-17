package raisetech5.StudentManagement5.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "受講生")
public class Student {

  @Pattern(regexp = "^\\d+$" , message = "数字のみ入力するようにしてください。")
  private String id;

  @NotBlank
  private String name;

  @NotBlank
  private String nickname;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String town;

  @NotBlank
  private String gender;


  private int age;

  @NotBlank
  private String furigana;


  private String remark;
  private boolean isDeleted;

  public Student(String id, String name, String nickname, String email,
      String town, String gender, int age, String furigana,
      String remark, boolean isDeleted) {
    this.id = id;
    this.name = name;
    this.nickname = nickname;
    this.email = email;
    this.town = town;
    this.gender = gender;
    this.age = age;
    this.furigana = furigana;
    this.remark = remark;
    this.isDeleted = isDeleted;
  }

  public Student() {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return age == student.age &&
        isDeleted == student.isDeleted &&
        Objects.equals(id, student.id) &&
        Objects.equals(name, student.name) &&
        Objects.equals(nickname, student.nickname) &&
        Objects.equals(email, student.email) &&
        Objects.equals(town, student.town) &&
        Objects.equals(gender, student.gender) &&
        Objects.equals(furigana, student.furigana) &&
        Objects.equals(remark, student.remark);
  }

}


