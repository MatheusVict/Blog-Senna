package dev.matheusvictor.blogsenna.services;

import dev.matheusvictor.blogsenna.domain.user.LoginResponse;
import dev.matheusvictor.blogsenna.domain.user.RegisterResponse;
import dev.matheusvictor.blogsenna.request.user.LoginRequestBody;
import dev.matheusvictor.blogsenna.request.user.RegisterUserRequestBody;

public interface AuthenticationService {
  LoginResponse login(LoginRequestBody loginRequestBody);

  RegisterResponse register(RegisterUserRequestBody registerRequestBody);
}
