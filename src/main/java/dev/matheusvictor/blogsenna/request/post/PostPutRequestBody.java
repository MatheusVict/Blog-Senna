package dev.matheusvictor.blogsenna.request.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostPutRequestBody {
  @NotBlank(message = "The post title cannot be blank")
  @Schema(description = "This is the post's title", example = "My first post")
  private String title;

  @NotBlank(message = "The post slug cannot be blank")
  @Schema(description = "This is the post's slug", example = "my-first-post")
  private String slug;

  @NotBlank(message = "The post description cannot be blank")
  @Schema(description = "This is the post's description", example = "This is my first post")
  private String description;

  @NotBlank(message = "The post content cannot be blank")
  @Schema(description = "This is the post's content", example = "This is my first post content")
  private String content;
}
