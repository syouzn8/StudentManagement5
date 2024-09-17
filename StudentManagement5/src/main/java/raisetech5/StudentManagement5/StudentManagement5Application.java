package raisetech5.StudentManagement5;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import raisetech5.StudentManagement5.controller.converter.CustomExceptionHandler;

@OpenAPIDefinition(info = @Info(title = "受講生管理システム"))
@SpringBootApplication
public class StudentManagement5Application {

  public static void main(String[] args) {
    SpringApplication.run(StudentManagement5Application.class, args);

    CustomExceptionHandler handler = new CustomExceptionHandler();
    handler.handleException();
  }



}
