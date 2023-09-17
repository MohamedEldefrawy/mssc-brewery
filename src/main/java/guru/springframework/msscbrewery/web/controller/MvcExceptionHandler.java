package guru.springframework.msscbrewery.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {
  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<List<String>> handleValidationExceptionHandler(ConstraintViolationException e) {
    List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

    e.getConstraintViolations().forEach(x -> errors.add(x.getPropertyPath() + ": " + x.getMessage()));

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BindException.class)
  ResponseEntity<List<ObjectError>> handleBindException(org.springframework.validation.BindException e) {
    return new ResponseEntity<>(e.getAllErrors(), HttpStatus.BAD_REQUEST);
  }
}
