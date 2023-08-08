package dev.matheusvictor.blogsenna.util;

import dev.matheusvictor.blogsenna.request.post.PostPutRequestBody;

public class PostPutRequestBodyCreator {
  public static PostPutRequestBody createPostPutRequestBody() {
    return PostPutRequestBody.builder()
            .title(PostCreator.createValidPost().getTitle())
            .slug(PostCreator.createValidPost().getSlug())
            .description(PostCreator.createValidPost().getDescription())
            .content(PostCreator.createValidPost().getContent())
            .build();
  }
}
