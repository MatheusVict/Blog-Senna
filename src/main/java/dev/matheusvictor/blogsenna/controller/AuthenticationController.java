package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.user.LoginResponse;
import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.user.LoginRequestBody;
import dev.matheusvictor.blogsenna.services.authentication.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  @PostMapping("/login")
  @Operation(summary = "Login", description = "You can login with your email and password")
  @Tag(name = "auth")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequestBody loginRequestBody) {
    UsernamePasswordAuthenticationToken userNamePassword =
            new UsernamePasswordAuthenticationToken(loginRequestBody.getEmail(), loginRequestBody.getPassword());
    Authentication auth = this.authenticationManager.authenticate(userNamePassword);

    String token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponse(token));
  }
}
