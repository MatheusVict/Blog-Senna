package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.request.user.UserPutRequestBody;

public class UserPutRequestBodyCreator {
  public static UserPutRequestBody createUserPutRequestBody() {
    return UserPutRequestBody.builder()
            .name(UserCreator.createValidUser().getName())
            .email(UserCreator.createValidUser().getEmail())
            .password(UserCreator.createValidUser().getPassword())
            .role(UserCreator.createValidUser().getRole())
            .build();

  }
}
