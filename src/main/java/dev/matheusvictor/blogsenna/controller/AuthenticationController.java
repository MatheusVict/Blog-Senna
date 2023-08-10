package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.request.user.LoginRequestBody;
import dev.matheusvictor.blogsenna.request.user.RegisterUserRequestBody;
import dev.matheusvictor.blogsenna.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  @Operation(summary = "Login", description = "You can login with your email and password")
  @Tag(name = "auth")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequestBody loginRequestBody) {
    return ResponseEntity.ok(authenticationService.login(loginRequestBody));
  }

  @PostMapping("/register")
  @Operation(summary = "Register", description = "You can register with your name, email and password")
  @Tag(name = "auth")
  @ApiResponse(responseCode = "201", description = "Successful registration", useReturnTypeSchema = true)
  public ResponseEntity<?> register(@RequestBody @Valid RegisterUserRequestBody registerUserRequestBody) {
    return ResponseEntity.ok(authenticationService.register(registerUserRequestBody));
  }
}
