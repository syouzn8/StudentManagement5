package raisetech5.StudentManagement5.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
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
import org.springframework.web.bind.annotation.RestController;
import raisetech5.StudentManagement5.controller.converter.CustomExceptionHandler;
import raisetech5.StudentManagement5.domain.StudentDetail;
import raisetech5.StudentManagement5.exception.TestException;
import raisetech5.StudentManagement5.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST　API として実行されるControllerです。
 */
@Validated
@RestController
public class StudentController {

  private final StudentService service;
  private final CustomExceptionHandler customExceptionHandler;  // 名前を変更

  @Autowired
  public StudentController(StudentService service, CustomExceptionHandler customExceptionHandler) {
    this.service = service;
    this.customExceptionHandler = customExceptionHandler;
  }

  @GetMapping("/test-exception")
  public String testExceptionHandling() {
    customExceptionHandler.handleException();  // 名前を変更
    return "例外処理が実行されました!!";
  }

  /**
   * 受講生詳細の一覧検索です。全件検索をおこなうので、条件指定は行いません。
   *
   * @return　受講生詳細の一覧（全件）
   */
  @Operation(summary ="一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() throws TestException {
    throw new TestException("エラー発生しました");
  }

  /**
   * 受講生詳細の登録を行います
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */
  @Operation(summary ="受講生登録", description = "受講生を登録します。")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid StudentDetail studentDetail ) {

    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の検索です。IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id　受講生ID
   * @return　受講生
   */

  @GetMapping("/student/{id}")
  public StudentDetail getStudent
  (@PathVariable @Size(min = 1,max = 3) @NotBlank @Pattern(regexp = "^\\d+$") String id){
    return service.searchStudent(id);
  }

  /**
   * 受講生詳細の更新を行います。　キャンセルフラグの更新もここで行います。（論理削除）
   * @param studentDetail　受講生詳細
   * @return　実行結果
   */
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  @ExceptionHandler(TestException.class)
  public  ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
  }



