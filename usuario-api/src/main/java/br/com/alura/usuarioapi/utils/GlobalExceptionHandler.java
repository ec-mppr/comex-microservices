package br.com.alura.usuarioapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(JpaObjectRetrievalFailureException.class)
  public ResponseEntity<Object> handleEntityNotFoundExcepction() {
    return ResponseEntity.notFound().build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ValidationFieldError>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<ValidationFieldError> errors = new ArrayList<ValidationFieldError>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      ValidationFieldError validationFieldError = new ValidationFieldError(fieldName, errorMessage);
      errors.add(validationFieldError);
    });
    return ResponseEntity.badRequest().body(errors);
  }
}
