package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.request.post.PostRequestBody;

public class PostRequestBodyCreator {
  public static PostRequestBody createPostRequestBody() {
    return PostRequestBody.builder()
            .title(PostCreator.createValidPost().getTitle())
            .description(PostCreator.createValidPost().getDescription())
            .content(PostCreator.createValidPost().getContent())
            .categoryId(PostCreator.createValidPost().getCategory().getId())
            .userId(PostCreator.createValidPost().getUser().getId())
            .build();
  }
}
