package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.user.LoginResponse;
import dev.matheusvictor.blogsenna.domain.user.RegisterResponse;
import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.domain.user.UserRole;
import dev.matheusvictor.blogsenna.mapper.UserMapper;
import dev.matheusvictor.blogsenna.request.user.LoginRequestBody;
import dev.matheusvictor.blogsenna.request.user.RegisterUserRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;
import dev.matheusvictor.blogsenna.services.AuthenticationService;
import dev.matheusvictor.blogsenna.services.UserService;
import dev.matheusvictor.blogsenna.services.authentication.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  private final UserService userService;

  @Override
  public LoginResponse login(LoginRequestBody loginRequestBody) {
    UsernamePasswordAuthenticationToken userNamePassword =
            new UsernamePasswordAuthenticationToken(loginRequestBody.getEmail(), loginRequestBody.getPassword());
    Authentication auth = this.authenticationManager.authenticate(userNamePassword);

    String token = tokenService.generateToken((User) auth.getPrincipal());

    return new LoginResponse(token);
  }

  @Override
  public RegisterResponse register(RegisterUserRequestBody registerRequestBody) {
    UserPostRequestBody userConverted = UserMapper.INSTANCE.toUserPostRequestBody(registerRequestBody);
    userConverted.setRole(UserRole.USER);
    User user = userService.create(userConverted);
    return new RegisterResponse(user.getName(), user.getEmail(), user.getRole());
  }
}
