package dev.matheusvictor.blogsenna.exception.pattern;

import dev.matheusvictor.blogsenna.exception.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class EmailAlreadyExceptionDetails extends ExceptionDetails {
}
