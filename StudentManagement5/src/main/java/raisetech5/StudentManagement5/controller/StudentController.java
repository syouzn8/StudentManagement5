package raisetech5.StudentManagement5.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import raisetech5.StudentManagement5.data.ApplicationStatus;
import raisetech5.StudentManagement5.data.Student;
import raisetech5.StudentManagement5.domain.StudentDetail;
import raisetech5.StudentManagement5.exception.TestException;
import raisetech5.StudentManagement5.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST　API として実行されるControllerです。
 */
@Validated
@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;

  }

  /**
   * 受講生詳細の一覧検索です。全件検索をおこなうので、条件指定は行いません。
   *
   * @return　受講生詳細の一覧（全件）
   */
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生詳細の登録を行います
   *
   * @param studentDetail 　受講生詳細
   * @return　実行結果
   */
  @Operation(summary = "受講生登録", description = "受講生を登録します。")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid StudentDetail studentDetail) {

    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の検索です。IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id 　受講生ID
   * @return　受講生
   */
  @Operation(summary = "受講生登録", description = "IDに紐づく受講生を習得します")
  @GetMapping("/student/{id}")
  public StudentDetail getStudent
  (@PathVariable @Size(min = 1, max = 3) @NotBlank @Pattern(regexp = "^\\d+$") String id) {
    return service.searchStudent(id);
  }

  /**
   * 受講生詳細の更新を行います。　キャンセルフラグの更新もここで行います。（論理削除）
   *
   * @param studentDetail 　受講生詳細
   * @return　実行結果
   */
  @Operation(summary = "受講生を更新やキャンセル", description = "受講生を更新したりキャンセルしたりします")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  @Operation(summary = "例外処理", description = "例外処理を実行します")
  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }


  //課題
  @Operation(summary = "申込状況を取得", description = "指定されたコース情報IDに紐づく申込状況を取得します。")
  @GetMapping("/application-status/{courseInfoId}")
  public ResponseEntity<ApplicationStatus> getApplicationStatus(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String courseInfoId) {
    Long id = Long.parseLong(courseInfoId);
    ApplicationStatus applicationStatus = service.searchApplicationStatus(id);
    return ResponseEntity.ok(applicationStatus);
  }

  @Operation(summary = "申込状況を登録", description = "新しい申込状況を登録します。")
  @PostMapping("/application-status")
  public ResponseEntity<ApplicationStatus> createApplicationStatus(
      @RequestBody @Valid ApplicationStatus applicationStatus) {
    ApplicationStatus registeredStatus = service.registerApplicationStatus(applicationStatus);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredStatus);
  }

  @Operation(summary = "申込状況を更新", description = "既存の申込状況を更新します。")
  @PutMapping("/application-status/{id}")
  public ResponseEntity<String> updateApplicationStatus(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id,
      @RequestBody @Valid ApplicationStatus applicationStatus) {

    applicationStatus.setId(Long.parseLong(id));
    service.updateApplicationStatus(applicationStatus);
    return ResponseEntity.ok("申込状況の更新が成功しました。");
  }


  @GetMapping("/students")
  public List<Student> searchStudents(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String town,
      @RequestParam(required = false) Integer age) {
    return service.searchStudents(name, town, age);
  }
}