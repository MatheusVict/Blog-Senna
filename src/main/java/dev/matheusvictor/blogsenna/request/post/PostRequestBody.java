package dev.matheusvictor.blogsenna.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostRequestBody {
  @NotBlank(message = "The post title cannot be blank")
  private String title;
  @NotBlank(message = "The post description cannot be blank")
  private String description;

  @NotBlank(message = "The post content cannot be blank")
  private String content;

  @NotNull(message = "The post category id cannot be blank")
  private Long categoryId;
}
