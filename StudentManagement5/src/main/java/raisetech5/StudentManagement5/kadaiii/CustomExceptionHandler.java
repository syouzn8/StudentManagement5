package raisetech5.StudentManagement5.kadaiii;

import org.springframework.stereotype.Component;

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
