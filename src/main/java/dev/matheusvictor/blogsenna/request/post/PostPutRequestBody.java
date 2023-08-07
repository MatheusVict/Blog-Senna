package dev.matheusvictor.blogsenna.request.post;

import jakarta.validation.constraints.NotBlank;

public class PostPutRequestBody {
  @NotBlank(message = "The post title cannot be blank")
  private String title;

  @NotBlank(message = "The post slug cannot be blank")
  private String slug;
  @NotBlank(message = "The post description cannot be blank")
  private String description;

  @NotBlank(message = "The post content cannot be blank")
  private String content;
}
