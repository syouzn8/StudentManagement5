package raisetech5.StudentManagement5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagement5Application {

  public static void main(String[] args) {
    SpringApplication.run(StudentManagement5Application.class, args);
  }

  


  @GetMapping("/Name")
  public String name() {
    return "watasinonamaeha,taroudesu.";

  }

  



}
