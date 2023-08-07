package dev.matheusvictor.blogsenna.handler;

import dev.matheusvictor.blogsenna.exception.BadRequestException;
import dev.matheusvictor.blogsenna.exception.EmailAlreadyInUseException;
import dev.matheusvictor.blogsenna.exception.pattern.BadRequestExceptionDetails;
import dev.matheusvictor.blogsenna.exception.pattern.EmailAlreadyExceptionDetails;
import dev.matheusvictor.blogsenna.exception.pattern.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class RestExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
    return ResponseEntity.badRequest().body(BadRequestExceptionDetails.builder()
            .title("Bad Request Exception, check the documentation")
            .status(400)
            .details(bre.getMessage())
            .timestamp(System.currentTimeMillis())
            .developerMessage(bre.getClass().getName())
            .build());
  }

  @ExceptionHandler(EmailAlreadyInUseException.class)
  public ResponseEntity<EmailAlreadyExceptionDetails> handlerEmailAlreadyInUseException(EmailAlreadyInUseException bre) {
    return ResponseEntity.badRequest().body(EmailAlreadyExceptionDetails.builder()
            .title("Conflict Exception: Email already in use")
            .status(409)
            .details(bre.getMessage())
            .timestamp(System.currentTimeMillis())
            .developerMessage(bre.getClass().getName())
            .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exception) {
    List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
    String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
    String fieldMessage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
    return ResponseEntity.badRequest().body(
            ValidationExceptionDetails.builder()
                    .title("Bad Request Exception, Invalid Fields")
                    .status(400)
                    .details("Check the field(s) error")
                    .timestamp(System.currentTimeMillis())
                    .developerMessage(exception.getClass().getName())
                    .fields(fields)
                    .fieldsMessage(fieldMessage)
                    .build());
  }
}