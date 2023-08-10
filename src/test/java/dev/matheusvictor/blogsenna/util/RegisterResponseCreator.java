package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.domain.user.RegisterResponse;
import dev.matheusvictor.blogsenna.domain.user.UserRole;
import dev.matheusvictor.blogsenna.request.user.RegisterUserRequestBody;

public class RegisterResponseCreator {
  public static RegisterResponse createARegisterResponse() {
    RegisterUserRequestBody userCreated = RegisterUserRequestBodyCreator.createValidUser();
    return new RegisterResponse(userCreated.getName(), userCreated.getEmail(), UserRole.USER);
  }
}
