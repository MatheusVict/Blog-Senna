package dev.matheusvictor.blogsenna.exception.pattern;

import dev.matheusvictor.blogsenna.exception.ExceptionDetails;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {

    private final String fields;
    private final String fieldsMessage;
}