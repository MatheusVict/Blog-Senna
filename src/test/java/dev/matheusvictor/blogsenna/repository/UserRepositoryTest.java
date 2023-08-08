package dev.matheusvictor.blogsenna.repository;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.domain.user.UserRole;
import dev.matheusvictor.blogsenna.util.UserCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for UserRepository")
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("Save persists user when successful")
  public void should_PersistsUser_WhenSuccessful() {
    var userToBeSaved = UserCreator.createUserToBeSaved();
    var userSaved = this.userRepository.save(userToBeSaved);

    assertNotNull(userSaved);
    assertNotNull(userSaved.getId());
    assertEquals(userToBeSaved.getName(), userSaved.getName());
    assertEquals(userToBeSaved.getEmail(), userSaved.getEmail());
    assertEquals(userToBeSaved.getPassword(), userSaved.getPassword());
    assertEquals(userToBeSaved.getRole(), userSaved.getRole());
  }

  @Test
  @DisplayName("Save updates user when successful")
  public void should_SaveAndUpdatesUser_WhenSuccessful() {
    var userToBeSaved = UserCreator.createUserToBeSaved();
    var userSaved = this.userRepository.save(userToBeSaved);

    userSaved.setName("User 2");
    userSaved.setEmail("user@gmail.com");
    userSaved.setPassword("654321");
    userSaved.setRole(UserRole.USER);


    User userUpdated = this.userRepository.save(userSaved);

    assertNotNull(userUpdated);
    assertNotNull(userUpdated.getId());
    assertEquals(userSaved.getName(), userUpdated.getName());
    assertEquals(userSaved.getEmail(), userUpdated.getEmail());
    assertEquals(userSaved.getPassword(), userUpdated.getPassword());

  }

  @Test
  @DisplayName("Delete removes user when successful")
  public void should_RemovesUser_WhenSuccessful() {
    var userToBeSaved = UserCreator.createUserToBeSaved();
    var userSaved = this.userRepository.save(userToBeSaved);

    this.userRepository.delete(userSaved);

    var userOptional = this.userRepository.findById(userSaved.getId());

    assertTrue(userOptional.isEmpty());
  }

  @Test
  @DisplayName("Find by email returns user when successful")
  public void should_ReturnsUser_WhenSuccessful() {
    var userToBeSaved = UserCreator.createUserToBeSaved();
    var userSaved = this.userRepository.save(userToBeSaved);

    var userOptional = this.userRepository.findByEmail(userSaved.getEmail());

    assertTrue(userOptional.isEnabled());
    assertEquals(userSaved.getEmail(), userOptional.getUsername());;
    assertTrue(userOptional.isAccountNonExpired());
    assertTrue(userOptional.isAccountNonLocked());
    assertTrue(userOptional.isCredentialsNonExpired());
  }

}