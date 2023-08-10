package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.request.user.RegisterUserRequestBody;

public class RegisterUserRequestBodyCreator {

  public static RegisterUserRequestBody createValidUser() {
    return RegisterUserRequestBody
            .builder()
            .name("user 1")
            .email("user@gmail.com")
            .password("123456")
            .build();
  }
}
