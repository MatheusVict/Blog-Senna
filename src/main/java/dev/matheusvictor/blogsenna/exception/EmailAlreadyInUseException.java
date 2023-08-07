package dev.matheusvictor.blogsenna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyInUseException extends RuntimeException {

  public EmailAlreadyInUseException(String message) {
    super(message);
  }
}
