package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.domain.post.Post;

public class PostCreator {

  public static Post createPostToBeSaved() {
    return Post.builder()
            .title("Post 1")
            .slug("post-1")
            .content("Post 1 content")
            .description("Post 1 description")
            .category(CategoryCreator.createValidCategory())
            .user(UserCreator.createValidUser())
            .build();
  }

  public static Post createValidPost() {
    return Post.builder()
            .id(1L)
            .title("Post 1")
            .slug("post-1")
            .content("Post 1 content")
            .description("Post 1 description")
            .category(CategoryCreator.createValidCategory())
            .user(UserCreator.createValidUser())
            .build();
  }

  public static Post createValidUpdatedPost() {
    return Post.builder()
            .id(1L)
            .title("Post 2")
            .slug("post-2")
            .content("Post 2 content")
            .description("Post 2 description")
            .category(CategoryCreator.createValidCategory())
            .user(UserCreator.createValidUser())
            .build();
  }
}
