package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.domain.user.User;
import dev.matheusvictor.blogsenna.domain.user.UserRole;

public class UserCreator {

  public static User createUserToBeSaved() {
    return User.builder()
            .name("User 1")
            .email("user@gmail.com")
            .password("123456")
            .role(UserRole.ADMIN)
            .build();
  }

  public static User createValidUser() {
    return User.builder()
            .id(1L)
            .name("User 1")
            .email("user@gmail.com")
            .password("123456")
            .role(UserRole.ADMIN)
            .build();

  }
}
