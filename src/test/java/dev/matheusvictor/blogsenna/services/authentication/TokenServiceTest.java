package dev.matheusvictor.blogsenna.services.authentication;

import dev.matheusvictor.blogsenna.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class TokenServiceTest {

  @Mock
  private User user;

  @InjectMocks
  private TokenService tokenService;

  private String secret = "secret";

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(tokenService, "secret", secret);

    BDDMockito.when(user.getEmail())
            .thenReturn("test@test.com");
  }

  @Test
  @DisplayName("generateToken should return a valid token when given a user")
  public void should_ReturnValidToken_When_GivenUser() {
    String token = tokenService.generateToken(user);

    assertNotNull(token);

    String subject = tokenService.validateToken(token);

    assertEquals(user.getEmail(), subject);
  }

  @Test
  @DisplayName("validateToken should return an empty string when given an invalid token")
  public void should_ReturnEmptyString_When_GivenInvalidToken() {
    String invalidToken = "invalid token";

    String subject = tokenService.validateToken(invalidToken);

    assertEquals("", subject);
  }
}
