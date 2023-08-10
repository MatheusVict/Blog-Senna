package dev.matheusvictor.blogsenna.controller;

import dev.matheusvictor.blogsenna.domain.user.LoginResponse;
import dev.matheusvictor.blogsenna.domain.user.RegisterResponse;
import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.user.LoginRequestBody;
import dev.matheusvictor.blogsenna.request.user.RegisterUserRequestBody;
import dev.matheusvictor.blogsenna.services.impl.AuthenticationServiceImpl;
import dev.matheusvictor.blogsenna.util.RegisterResponseCreator;
import dev.matheusvictor.blogsenna.util.RegisterUserRequestBodyCreator;
import dev.matheusvictor.blogsenna.util.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {

  @InjectMocks
  AuthenticationController authenticationController;

  @Mock
  AuthenticationServiceImpl authenticationService;

  @BeforeEach
  void setUp() {
    BDDMockito.when(authenticationService.login(ArgumentMatchers.any(LoginRequestBody.class)))
            .thenReturn(new LoginResponse("token"));

    BDDMockito.when(authenticationService.register(ArgumentMatchers.any(RegisterUserRequestBody.class)))
            .thenReturn(RegisterResponseCreator.createARegisterResponse());
  }

  @Test
  @DisplayName("Login should make login when successful")
  public void should_MakeLogin_WhenSuccess() {
    LoginResponse expectedResponseToken = new LoginResponse("token");
    LoginRequestBody userToLogin = new LoginRequestBody("user@email.com", "123456");

    ResponseEntity<?> login = authenticationController.login(userToLogin);

    assertNotNull(login);
    assertEquals(login.getBody(), expectedResponseToken);
    assertTrue(login.getStatusCode().is2xxSuccessful());
  }

  @Test
  @DisplayName("Register should saves a User")
  public void should_RegisterUser_WhenSuccess() {
    RegisterUserRequestBody userToBeRegister = RegisterUserRequestBodyCreator.createValidUser();
    RegisterResponse userExpected = RegisterResponseCreator.createARegisterResponse();

    ResponseEntity<?> userRegister = authenticationController.register(userToBeRegister);

    assertNotNull(userRegister);
    assertEquals(userRegister.getBody(), userExpected);
    assertTrue(userRegister.getStatusCode().is2xxSuccessful());
  }

}