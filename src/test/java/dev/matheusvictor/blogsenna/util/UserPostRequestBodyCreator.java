package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.domain.user.UserRole;
import dev.matheusvictor.blogsenna.request.user.UserPostRequestBody;

public class UserPostRequestBodyCreator {
  public static UserPostRequestBody createUserPostRequestBody() {
    return UserPostRequestBody.builder()
            .name(UserCreator.createValidUser().getName())
            .email(UserCreator.createValidUser().getEmail())
            .password(UserCreator.createValidUser().getPassword())
            .role(UserCreator.createValidUser().getRole())
            .build();

  }
}
