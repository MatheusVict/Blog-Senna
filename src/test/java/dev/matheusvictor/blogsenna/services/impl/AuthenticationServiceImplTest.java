package dev.matheusvictor.blogsenna.services.impl;

import dev.matheusvictor.blogsenna.domain.user.LoginResponse;
import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.request.user.LoginRequestBody;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;
import dev.matheusvictor.blogsenna.services.UserService;
import dev.matheusvictor.blogsenna.services.authentication.TokenService;
import dev.matheusvictor.blogsenna.util.UserCreator;
import dev.matheusvictor.blogsenna.util.UserPostRequestBodyCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AuthenticationServiceImplTest {

  @InjectMocks
  AuthenticationServiceImpl authenticationService;

  @Mock
  AuthenticationManager authenticationManager;

  @Mock
  TokenService tokenService;
  @Mock
  UserService userService;

  @BeforeEach
  void setUp() {
    BDDMockito.when(userService.create(ArgumentMatchers.any(UserPostRequestBody.class)))
            .thenReturn(UserCreator.createValidUser());
  }

  @Test
  @DisplayName("Login should make login when successful")
  public void should_MakeLogin_WhenSuccess() {

    String expectedToken = "expectedToken";
    LoginRequestBody loginRequestBody = new LoginRequestBody("testEmail", "testPassword");

    Authentication authMock = mock(Authentication.class);

    User testUser = new User();

    when(authMock.getPrincipal()).thenReturn(testUser);
    when(authenticationManager.authenticate(any())).thenReturn(authMock);
    when(tokenService.generateToken(any())).thenReturn(expectedToken);

    LoginResponse result = authenticationService.login(loginRequestBody);

    assertEquals(expectedToken, result.token());
  }

  @Test
  @DisplayName("Register should saves a User")
  public void should_RegisterUser_WhenSuccess() {
    User userToBeSaved = UserCreator.createUserToBeSaved();

    User userSaved = userService.create(UserPostRequestBodyCreator.createUserPostRequestBody());

    assertNotNull(userSaved.getId());
    assertEquals(userToBeSaved.getName(), userSaved.getName());
    assertEquals(userSaved.getName(), userToBeSaved.getName());

  }

}