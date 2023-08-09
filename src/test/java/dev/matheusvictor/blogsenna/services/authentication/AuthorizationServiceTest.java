package dev.matheusvictor.blogsenna.services.authentication;

import dev.matheusvictor.blogsenna.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthorizationServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private AuthorizationService authorizationService;

  @BeforeEach
  void setUp() {
    UserDetails userDetails = User.withDefaultPasswordEncoder()
            .username("test@test.com")
            .password("password")
            .roles("USER")
            .build();

    BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
            .thenReturn(userDetails);
  }

  @Test
  @DisplayName("loadUserByUsername should return UserDetails when given a valid username")
  public void should_ReturnUserDetails_When_GivenValidUsername() {
    String username = "test@test.com";

    UserDetails returnedUserDetails = authorizationService.loadUserByUsername(username);

    assertEquals(username, returnedUserDetails.getUsername());
  }

  @Test
  @DisplayName("loadUserByUsername should return null when given an invalid username")
  public void should_ThrowUsernameNotFoundException_When_GivenInvalidUsername() {
    BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
            .thenReturn(null);

    String invalidUsername = "invalid@test.com";

    assertNull(authorizationService.loadUserByUsername(invalidUsername));
  }
}
