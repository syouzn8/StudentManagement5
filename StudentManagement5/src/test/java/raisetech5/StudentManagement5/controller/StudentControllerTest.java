package raisetech5.StudentManagement5.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpInputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech5.StudentManagement5.data.Student;
import raisetech5.StudentManagement5.domain.StudentDetail;
import raisetech5.StudentManagement5.service.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Autowired
  private MockMvc mockMvc;


  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }
  @Test
  void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと(){
    Student student = new Student();
    student.setId("１");
    student.setName("榎並浩二");
    student.setFurigana("エナミコウジ");
    student.setNickname("エナミ");
    student.setEmail("test@example.com");
    student.setTown("奈良県");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);
//30より前
    assertEquals(0, violations.size());
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックに掛かること() {
    Student student = new Student();
    student.setId("テストです。");
    student.setName("榎並浩二");
    student.setFurigana("エナミコウジ");
    student.setNickname("エナミ");
    student.setEmail("test@example.com");
    student.setTown("奈良県");
    student.setGender("男性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

assertEquals(1, violations.size());
  }

}