package raisetech5.StudentManagement5.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech5.StudentManagement5.domain.StudentDetail;
import raisetech5.StudentManagement5.exception.TestException;
import raisetech5.StudentManagement5.service.StudentService;

@Validated
@RestController
@Service
public class exception {

    private final StudentService service;

    @Autowired
    public exception (StudentService service) {
      this.service = service;

  }
  

  @GetMapping("/student/List")
  public List<StudentDetail> getStudentList() throws TestException {
    throw new TestException("エラー発生しました!!");
  }

  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  public StudentService getService() {
    return service;
  }
}
