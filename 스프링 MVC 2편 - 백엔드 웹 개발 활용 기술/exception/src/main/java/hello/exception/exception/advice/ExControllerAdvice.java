package hello.exception.exception.advice;

import hello.exception.exception.ErrorResult;
import hello.exception.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult ilegaExHandeler(IllegalArgumentException e) {
        log.error("[exceptionHandler ex]", e);
        return new ErrorResult("Bad", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandeler(UserException e) {
        log.error("[ExceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("user-ex", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }
}
