package raisetech5.StudentManagement5.controller.converter;

import org.springframework.stereotype.Component;
import raisetech5.StudentManagement5.kadaiii.CustomException;
import raisetech5.StudentManagement5.kadaiii.ExceptionThrower;

@Component
public class CustomExceptionHandler {

  public void handleException() {
    ExceptionThrower thrower = new ExceptionThrower();
    try {
      thrower.throwCustomException();
    } catch (CustomException e) {
      System.out.println("例外をキャッチしました: " + e.getMessage());
    }
  }
}
